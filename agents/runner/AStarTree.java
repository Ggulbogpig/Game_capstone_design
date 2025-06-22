/*     */ package agents.runner;
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
/*     */   private boolean requireReplanning = false;
/*  16 */   int maxRight = 176;
/*     */   
/*  18 */   public int SearchedStates = 0;
/*  19 */   public int SearchedLose = 0;
/*     */   
/*     */   private ArrayList<boolean[]> currentActionPlan;
/*  22 */   int ticksBeforeReplanning = 0;
/*     */   
/*     */   private MarioForwardModel search(MarioTimer timer) {
/*  25 */     SearchNode current = this.bestPosition;
/*  26 */     boolean currentGood = false;
/*  27 */     while (this.posPool.size() != 0 && (
/*  28 */       this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < this.maxRight || !currentGood) && 
/*  29 */       timer.getRemainingTime() > 0L) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  37 */       current = pickBestPos(this.posPool);
/*  38 */       if (current == null) {
/*  39 */         return null;
/*     */       }
/*  41 */       currentGood = false;
/*  42 */       float realRemainingTime = current.simulatePos();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  48 */       if (realRemainingTime < 0.0F)
/*     */         continue; 
/*  50 */       if (!current.isInVisitedList && isInVisited((int)current.sceneSnapshot.getMarioFloatPos()[0], 
/*  51 */           (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed)) {
/*  52 */         realRemainingTime += 1500.0F;
/*  53 */         current.isInVisitedList = true;
/*  54 */         current.remainingTime = realRemainingTime;
/*  55 */         current.remainingTimeEstimated = realRemainingTime;
/*  56 */         this.posPool.add(current);
/*  57 */       } else if ((realRemainingTime - current.remainingTimeEstimated) > 0.1D) {
/*     */         
/*  59 */         current.remainingTimeEstimated = realRemainingTime;
/*  60 */         this.posPool.add(current);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  65 */         currentGood = true;
/*  66 */         visited((int)current.sceneSnapshot.getMarioFloatPos()[0], (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed);
/*  67 */         this.posPool.addAll(current.generateChildren());
/*     */       } 
/*  69 */       if (currentGood) {
/*  70 */         if (this.bestPosition.getRemainingTime() > current.getRemainingTime()) {
/*  71 */           this.bestPosition = current;
/*     */         }
/*  73 */         if (current.sceneSnapshot.getMarioFloatPos()[0] > this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0]) {
/*  74 */           this.furthestPosition = current;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     if (current.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < this.maxRight && 
/*  80 */       this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0] > this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] + 20.0F)
/*     */     {
/*  82 */       this.bestPosition = this.furthestPosition;
/*     */     }
/*  84 */     return current.sceneSnapshot;
/*     */   }
/*     */   
/*     */   private void startSearch(MarioForwardModel model, int repetitions) {
/*  88 */     SearchNode startPos = new SearchNode(null, repetitions, null);
/*  89 */     startPos.initializeRoot(model);
/*     */     
/*  91 */     this.posPool = new ArrayList<>();
/*  92 */     this.visitedStates.clear();
/*  93 */     this.posPool.addAll(startPos.generateChildren());
/*  94 */     this.currentSearchStartingMarioXPos = model.getMarioFloatPos()[0];
/*     */     
/*  96 */     this.bestPosition = startPos;
/*  97 */     this.furthestPosition = startPos;
/*     */   }
/*     */   
/*     */   private ArrayList<boolean[]> extractPlan() {
/* 101 */     ArrayList<boolean[]> actions = (ArrayList)new ArrayList<>();
/*     */ 
/*     */     
/* 104 */     if (this.bestPosition == null) {
/* 105 */       for (int i = 0; i < 10; i++) {
/* 106 */         actions.add(Helper.createAction(false, true, false, false, true));
/*     */       }
/* 108 */       return actions;
/*     */     } 
/*     */     
/* 111 */     SearchNode current = this.bestPosition;
/* 112 */     while (current.parentPos != null) {
/* 113 */       for (int i = 0; i < current.repetitions; i++)
/* 114 */         actions.add(0, current.action); 
/* 115 */       if (current.hasBeenHurt) {
/* 116 */         this.requireReplanning = true;
/*     */       }
/* 118 */       current = current.parentPos;
/*     */     } 
/* 120 */     return actions;
/*     */   }
/*     */   
/*     */   private SearchNode pickBestPos(ArrayList<SearchNode> posPool) {
/* 124 */     SearchNode bestPos = null;
/* 125 */     float bestPosCost = 1.0E7F;
/* 126 */     for (SearchNode current : posPool) {
/* 127 */       if (!current.check) {
/* 128 */         current.check = true;
/* 129 */         this.SearchedStates++;
/* 130 */         if (current.ifLose() == 1) {
/* 131 */           this.SearchedLose++;
/*     */         }
/*     */       } 
/* 134 */       float currentCost = current.getRemainingTime() + current.timeElapsed * 0.9F;
/* 135 */       if (currentCost < bestPosCost) {
/* 136 */         bestPos = current;
/* 137 */         bestPosCost = currentCost;
/*     */       } 
/*     */     } 
/* 140 */     posPool.remove(bestPos);
/* 141 */     return bestPos;
/*     */   }
/*     */   
/*     */   public boolean[] optimise(MarioForwardModel model, MarioTimer timer) {
/* 145 */     int planAhead = 2;
/* 146 */     int stepsPerSearch = 2;
/*     */     
/* 148 */     MarioForwardModel originalModel = model.clone();
/* 149 */     this.ticksBeforeReplanning--;
/* 150 */     this.requireReplanning = false;
/* 151 */     if (this.ticksBeforeReplanning <= 0 || this.currentActionPlan.size() == 0 || this.requireReplanning) {
/* 152 */       this.currentActionPlan = extractPlan();
/* 153 */       if (this.currentActionPlan.size() < planAhead) {
/* 154 */         planAhead = this.currentActionPlan.size();
/*     */       }
/*     */ 
/*     */       
/* 158 */       for (int i = 0; i < planAhead; i++) {
/* 159 */         model.advance(this.currentActionPlan.get(i));
/*     */       }
/* 161 */       startSearch(model, stepsPerSearch);
/* 162 */       this.ticksBeforeReplanning = planAhead;
/*     */     } 
/* 164 */     if (model.getGameStatus() == GameStatus.LOSE) {
/* 165 */       startSearch(originalModel, stepsPerSearch);
/*     */     }
/* 167 */     search(timer);
/*     */     
/* 169 */     boolean[] action = new boolean[5];
/* 170 */     if (this.currentActionPlan.size() > 0)
/* 171 */       action = this.currentActionPlan.remove(0); 
/* 172 */     return action;
/*     */   }
/*     */   
/*     */   private void visited(int x, int y, int t) {
/* 176 */     this.visitedStates.add(new int[] { x, y, t });
/*     */   }
/*     */   
/*     */   private boolean isInVisited(int x, int y, int t) {
/* 180 */     int timeDiff = 5;
/* 181 */     int xDiff = 2;
/* 182 */     int yDiff = 2;
/* 183 */     for (int[] v : this.visitedStates) {
/* 184 */       if (Math.abs(v[0] - x) < xDiff && Math.abs(v[1] - y) < yDiff && Math.abs(v[2] - t) < timeDiff && 
/* 185 */         t >= v[2]) {
/* 186 */         return true;
/*     */       }
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\runner\AStarTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */