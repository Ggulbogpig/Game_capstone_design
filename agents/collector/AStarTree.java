/*     */ package agents.collector;
/*     */ 
/*     */ import engine.core.MarioForwardModel;
/*     */ import engine.core.MarioTimer;
/*     */ import engine.helper.GameStatus;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class AStarTree
/*     */ {
/*     */   public SearchNode bestPosition;
/*     */   public SearchNode furthestPosition;
/*     */   float currentSearchStartingMarioXPos;
/*     */   ArrayList<SearchNode> posPool;
/*  14 */   ArrayList<int[]> visitedStates = (ArrayList)new ArrayList<>();
/*     */   
/*     */   private boolean requireReplanning = false;
/*     */   private ArrayList<boolean[]> currentActionPlan;
/*  18 */   int ticksBeforeReplanning = 0;
/*  19 */   public int SearchedStates = 0;
/*  20 */   public int SearchedLose = 0;
/*     */   private MarioForwardModel search(MarioTimer timer) {
/*  22 */     SearchNode current = this.bestPosition;
/*  23 */     boolean currentGood = false;
/*  24 */     int maxRight = 176;
/*  25 */     while (this.posPool.size() != 0 && (
/*  26 */       this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight || !currentGood) && 
/*  27 */       timer.getRemainingTime() > 0L) {
/*  28 */       current = pickBestPos(this.posPool);
/*  29 */       if (current == null) {
/*  30 */         return null;
/*     */       }
/*  32 */       currentGood = false;
/*  33 */       float realRemainingTime = current.simulatePos();
/*  34 */       this.SearchedStates++;
/*  35 */       if (current.ifLose() == 1) {
/*  36 */         this.SearchedLose++;
/*     */       }
/*  38 */       if (realRemainingTime < 0.0F)
/*     */         continue; 
/*  40 */       if (!current.isInVisitedList && isInVisited((int)current.sceneSnapshot.getMarioFloatPos()[0], 
/*  41 */           (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed)) {
/*  42 */         realRemainingTime += 1500.0F;
/*  43 */         current.isInVisitedList = true;
/*  44 */         current.remainingTime = realRemainingTime;
/*  45 */         current.remainingTimeEstimated = realRemainingTime;
/*  46 */         this.posPool.add(current);
/*  47 */       } else if ((realRemainingTime - current.remainingTimeEstimated) > 0.1D) {
/*     */         
/*  49 */         current.remainingTimeEstimated = realRemainingTime;
/*  50 */         this.posPool.add(current);
/*     */       } else {
/*  52 */         currentGood = true;
/*  53 */         visited((int)current.sceneSnapshot.getMarioFloatPos()[0], (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed);
/*  54 */         this.posPool.addAll(current.generateChildren());
/*     */       } 
/*  56 */       if (currentGood) {
/*  57 */         if (this.bestPosition.getRemainingTime() > current.getRemainingTime())
/*  58 */           this.bestPosition = current; 
/*  59 */         if (current.sceneSnapshot.getMarioFloatPos()[0] > this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0])
/*  60 */           this.furthestPosition = current; 
/*     */       } 
/*     */     } 
/*  63 */     if (current.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight && 
/*  64 */       this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0] > this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] + 20.0F)
/*     */     {
/*  66 */       this.bestPosition = this.furthestPosition;
/*     */     }
/*  68 */     return current.sceneSnapshot;
/*     */   }
/*     */   
/*     */   private void startSearch(MarioForwardModel model, int repetitions) {
/*  72 */     SearchNode startPos = new SearchNode(null, repetitions, null);
/*  73 */     startPos.initializeRoot(model);
/*     */     
/*  75 */     this.posPool = new ArrayList<>();
/*  76 */     this.visitedStates.clear();
/*     */     
/*  78 */     ArrayList<SearchNode> tempPool = startPos.generateChildren();
/*     */     
/*  80 */     this.SearchedStates += tempPool.size();
/*     */     
/*  82 */     this.posPool.addAll(tempPool);
/*  83 */     this.currentSearchStartingMarioXPos = model.getMarioFloatPos()[0];
/*     */     
/*  85 */     this.bestPosition = startPos;
/*  86 */     this.furthestPosition = startPos;
/*     */   }
/*     */   
/*     */   private ArrayList<boolean[]> extractPlan() {
/*  90 */     ArrayList<boolean[]> actions = (ArrayList)new ArrayList<>();
/*     */ 
/*     */     
/*  93 */     if (this.bestPosition == null) {
/*     */ 
/*     */       
/*  96 */       actions.add(Helper.createAction(false, true, false, true, true));
/*     */       
/*  98 */       return actions;
/*     */     } 
/*     */     
/* 101 */     SearchNode current = this.bestPosition;
/* 102 */     while (current.parentPos != null) {
/* 103 */       for (int i = 0; i < current.repetitions; i++)
/* 104 */         actions.add(0, current.action); 
/* 105 */       if (current.hasBeenHurt) {
/* 106 */         this.requireReplanning = true;
/*     */       }
/* 108 */       current = current.parentPos;
/*     */     } 
/* 110 */     return actions;
/*     */   }
/*     */   
/*     */   private SearchNode pickBestPos(ArrayList<SearchNode> posPool) {
/* 114 */     SearchNode bestPos = null;
/* 115 */     float bestPosCost = 1.0E7F;
/* 116 */     for (SearchNode current : posPool) {
/*     */       
/* 118 */       if (!current.check) {
/* 119 */         current.check = true;
/* 120 */         this.SearchedStates++;
/* 121 */         if (current.ifLose() == 1) {
/* 122 */           this.SearchedLose++;
/*     */         }
/*     */       } 
/* 125 */       float currentCost = -10000.0F * current.getCollectRate() - (5000 * current.ifWin()) + (100000 * current.ifLose());
/*     */       
/* 127 */       if (currentCost < bestPosCost) {
/* 128 */         bestPos = current;
/* 129 */         bestPosCost = currentCost;
/*     */       } 
/*     */     } 
/* 132 */     posPool.remove(bestPos);
/* 133 */     return bestPos;
/*     */   }
/*     */   
/*     */   public boolean[] optimise(MarioForwardModel model, MarioTimer timer) {
/* 137 */     int planAhead = 2;
/* 138 */     int stepsPerSearch = 4;
/*     */     
/* 140 */     MarioForwardModel originalModel = model.clone();
/* 141 */     this.ticksBeforeReplanning--;
/* 142 */     this.requireReplanning = false;
/* 143 */     if (this.ticksBeforeReplanning <= 0 || this.currentActionPlan.size() == 0 || this.requireReplanning) {
/* 144 */       this.currentActionPlan = extractPlan();
/* 145 */       if (this.currentActionPlan.size() < planAhead) {
/* 146 */         planAhead = this.currentActionPlan.size();
/*     */       }
/*     */ 
/*     */       
/* 150 */       for (int i = 0; i < planAhead; i++) {
/* 151 */         model.advance(this.currentActionPlan.get(i));
/*     */       }
/* 153 */       startSearch(model, stepsPerSearch);
/* 154 */       this.ticksBeforeReplanning = planAhead;
/*     */     } 
/* 156 */     if (model.getGameStatus() == GameStatus.LOSE) {
/* 157 */       startSearch(originalModel, stepsPerSearch);
/*     */     }
/* 159 */     search(timer);
/*     */     
/* 161 */     boolean[] action = new boolean[5];
/* 162 */     if (this.currentActionPlan.size() > 0)
/* 163 */       action = this.currentActionPlan.remove(0); 
/* 164 */     return action;
/*     */   }
/*     */   
/*     */   private void visited(int x, int y, int t) {
/* 168 */     this.visitedStates.add(new int[] { x, y, t });
/*     */   }
/*     */   
/*     */   private boolean isInVisited(int x, int y, int t) {
/* 172 */     int timeDiff = 5;
/* 173 */     int xDiff = 2;
/* 174 */     int yDiff = 2;
/* 175 */     for (int[] v : this.visitedStates) {
/* 176 */       if (Math.abs(v[0] - x) < xDiff && Math.abs(v[1] - y) < yDiff && Math.abs(v[2] - t) < timeDiff && 
/* 177 */         t >= v[2]) {
/* 178 */         return true;
/*     */       }
/*     */     } 
/* 181 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\collector\AStarTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */