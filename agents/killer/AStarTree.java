/*     */ package agents.killer;
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
/*  34 */       if (realRemainingTime < 0.0F)
/*     */         continue; 
/*  36 */       if (!current.isInVisitedList && isInVisited((int)current.sceneSnapshot.getMarioFloatPos()[0], 
/*  37 */           (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed)) {
/*  38 */         realRemainingTime += 1500.0F;
/*  39 */         current.isInVisitedList = true;
/*  40 */         current.remainingTime = realRemainingTime;
/*  41 */         current.remainingTimeEstimated = realRemainingTime;
/*  42 */         this.posPool.add(current);
/*  43 */       } else if ((realRemainingTime - current.remainingTimeEstimated) > 0.1D) {
/*     */         
/*  45 */         current.remainingTimeEstimated = realRemainingTime;
/*  46 */         this.posPool.add(current);
/*     */       } else {
/*  48 */         currentGood = true;
/*  49 */         visited((int)current.sceneSnapshot.getMarioFloatPos()[0], (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed);
/*  50 */         this.posPool.addAll(current.generateChildren());
/*     */       } 
/*  52 */       if (currentGood) {
/*  53 */         if (this.bestPosition.getRemainingTime() > current.getRemainingTime())
/*  54 */           this.bestPosition = current; 
/*  55 */         if (current.sceneSnapshot.getMarioFloatPos()[0] > this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0])
/*  56 */           this.furthestPosition = current; 
/*     */       } 
/*     */     } 
/*  59 */     if (current.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight && 
/*  60 */       this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0] > this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] + 20.0F)
/*     */     {
/*  62 */       this.bestPosition = this.furthestPosition;
/*     */     }
/*  64 */     return current.sceneSnapshot;
/*     */   }
/*     */   
/*     */   private void startSearch(MarioForwardModel model, int repetitions) {
/*  68 */     SearchNode startPos = new SearchNode(null, repetitions, null);
/*  69 */     startPos.initializeRoot(model);
/*     */     
/*  71 */     this.posPool = new ArrayList<>();
/*  72 */     this.visitedStates.clear();
/*     */     
/*  74 */     ArrayList<SearchNode> tempPool = startPos.generateChildren();
/*     */     
/*  76 */     this.posPool.addAll(tempPool);
/*  77 */     this.currentSearchStartingMarioXPos = model.getMarioFloatPos()[0];
/*     */     
/*  79 */     this.bestPosition = startPos;
/*  80 */     this.furthestPosition = startPos;
/*     */   }
/*     */   
/*     */   private ArrayList<boolean[]> extractPlan() {
/*  84 */     ArrayList<boolean[]> actions = (ArrayList)new ArrayList<>();
/*     */ 
/*     */     
/*  87 */     if (this.bestPosition == null) {
/*     */ 
/*     */       
/*  90 */       actions.add(Helper.createAction(false, true, false, true, true));
/*     */       
/*  92 */       return actions;
/*     */     } 
/*     */     
/*  95 */     SearchNode current = this.bestPosition;
/*  96 */     while (current.parentPos != null) {
/*  97 */       for (int i = 0; i < current.repetitions; i++)
/*  98 */         actions.add(0, current.action); 
/*  99 */       if (current.hasBeenHurt) {
/* 100 */         this.requireReplanning = true;
/*     */       }
/* 102 */       current = current.parentPos;
/*     */     } 
/* 104 */     return actions;
/*     */   }
/*     */   
/*     */   private SearchNode pickBestPos(ArrayList<SearchNode> posPool) {
/* 108 */     SearchNode bestPos = null;
/* 109 */     float bestPosCost = 1.0E7F;
/* 110 */     for (SearchNode current : posPool) {
/*     */       
/* 112 */       float[] Enemies = current.getEnemiesFloatPos();
/* 113 */       float distance = 0.0F;
/* 114 */       float mario_x = current.getMarioX();
/* 115 */       float mario_y = current.getMarioY();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       if (!current.check) {
/* 129 */         current.check = true;
/* 130 */         this.SearchedStates++;
/* 131 */         if (current.ifLose() == 1) {
/* 132 */           this.SearchedLose++;
/*     */         }
/*     */       } 
/* 135 */       float currentCost = -2.0F * current.getkillrate() - current.ifWin() + (20 * current.ifLose());
/*     */       
/* 137 */       if (currentCost < bestPosCost) {
/* 138 */         bestPos = current;
/* 139 */         bestPosCost = currentCost;
/*     */       } 
/*     */     } 
/* 142 */     posPool.remove(bestPos);
/* 143 */     return bestPos;
/*     */   }
/*     */   
/*     */   public boolean[] optimise(MarioForwardModel model, MarioTimer timer) {
/* 147 */     int planAhead = 2;
/* 148 */     int stepsPerSearch = 4;
/*     */     
/* 150 */     MarioForwardModel originalModel = model.clone();
/* 151 */     this.ticksBeforeReplanning--;
/* 152 */     this.requireReplanning = false;
/* 153 */     if (this.ticksBeforeReplanning <= 0 || this.currentActionPlan.size() == 0 || this.requireReplanning) {
/* 154 */       this.currentActionPlan = extractPlan();
/* 155 */       if (this.currentActionPlan.size() < planAhead) {
/* 156 */         planAhead = this.currentActionPlan.size();
/*     */       }
/*     */ 
/*     */       
/* 160 */       for (int i = 0; i < planAhead; i++) {
/* 161 */         model.advance(this.currentActionPlan.get(i));
/*     */       }
/* 163 */       startSearch(model, stepsPerSearch);
/* 164 */       this.ticksBeforeReplanning = planAhead;
/*     */     } 
/* 166 */     if (model.getGameStatus() == GameStatus.LOSE) {
/* 167 */       startSearch(originalModel, stepsPerSearch);
/*     */     }
/* 169 */     search(timer);
/*     */     
/* 171 */     boolean[] action = new boolean[5];
/* 172 */     if (this.currentActionPlan.size() > 0)
/* 173 */       action = this.currentActionPlan.remove(0); 
/* 174 */     return action;
/*     */   }
/*     */   
/*     */   private void visited(int x, int y, int t) {
/* 178 */     this.visitedStates.add(new int[] { x, y, t });
/*     */   }
/*     */   
/*     */   private boolean isInVisited(int x, int y, int t) {
/* 182 */     int timeDiff = 5;
/* 183 */     int xDiff = 2;
/* 184 */     int yDiff = 2;
/* 185 */     for (int[] v : this.visitedStates) {
/* 186 */       if (Math.abs(v[0] - x) < xDiff && Math.abs(v[1] - y) < yDiff && Math.abs(v[2] - t) < timeDiff && 
/* 187 */         t >= v[2]) {
/* 188 */         return true;
/*     */       }
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\killer\AStarTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */