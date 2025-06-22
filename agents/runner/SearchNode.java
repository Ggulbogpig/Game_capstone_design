/*     */ package agents.runner;
/*     */ 
/*     */ import engine.core.MarioForwardModel;
/*     */ import engine.helper.GameStatus;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class SearchNode
/*     */ {
/*   9 */   public int timeElapsed = 0;
/*  10 */   public float remainingTimeEstimated = 0.0F;
/*  11 */   public float remainingTime = 0.0F;
/*     */   
/*  13 */   public SearchNode parentPos = null;
/*  14 */   public MarioForwardModel sceneSnapshot = null;
/*  15 */   public int distanceFromOrigin = 0;
/*     */   
/*     */   public boolean hasBeenHurt = false;
/*     */   public boolean isInVisitedList = false;
/*     */   public boolean check = false;
/*     */   boolean[] action;
/*  21 */   int repetitions = 1;
/*     */   
/*     */   public float calcRemainingTime(float marioX, float marioXA) {
/*  24 */     return (100000.0F - maxForwardMovement(marioXA, 1000) + marioX) / 10.909091F - 1000.0F;
/*     */   }
/*     */   
/*     */   public float getRemainingTime() {
/*  28 */     if (this.remainingTime > 0.0F) {
/*  29 */       return this.remainingTime;
/*     */     }
/*  31 */     return this.remainingTimeEstimated;
/*     */   }
/*     */   
/*     */   public float estimateRemainingTimeChild(boolean[] action, int repetitions) {
/*  35 */     float[] childbehaviorDistanceAndSpeed = Helper.estimateMaximumForwardMovement(
/*  36 */         this.sceneSnapshot.getMarioFloatVelocity()[0], action, repetitions);
/*  37 */     return calcRemainingTime(this.sceneSnapshot.getMarioFloatPos()[0] + childbehaviorDistanceAndSpeed[0], 
/*  38 */         childbehaviorDistanceAndSpeed[1]);
/*     */   }
/*     */   
/*     */   public SearchNode(boolean[] action, int repetitions, SearchNode parent) {
/*  42 */     this.parentPos = parent;
/*  43 */     if (parent != null) {
/*  44 */       this.remainingTimeEstimated = parent.estimateRemainingTimeChild(action, repetitions);
/*  45 */       parent.distanceFromOrigin++;
/*  46 */       this.sceneSnapshot = this.parentPos.sceneSnapshot.clone();
/*  47 */       for (int i = 0; i < repetitions; i++) {
/*  48 */         this.sceneSnapshot.advance(action);
/*     */       }
/*     */     } 
/*  51 */     this.action = action;
/*  52 */     this.repetitions = repetitions;
/*  53 */     if (parent != null) {
/*  54 */       parent.timeElapsed += repetitions;
/*     */     } else {
/*  56 */       this.timeElapsed = 0;
/*     */     } 
/*     */   }
/*     */   public void initializeRoot(MarioForwardModel model) {
/*  60 */     if (this.parentPos == null) {
/*  61 */       this.sceneSnapshot = model.clone();
/*  62 */       this.remainingTimeEstimated = calcRemainingTime(model.getMarioFloatPos()[0], 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float simulatePos() {
/*  71 */     int marioDamage = Helper.getMarioDamage(this.sceneSnapshot, this.parentPos.sceneSnapshot);
/*  72 */     this.remainingTime = 
/*  73 */       calcRemainingTime(this.sceneSnapshot.getMarioFloatPos()[0], this.sceneSnapshot.getMarioFloatVelocity()[0]) + (
/*  74 */       marioDamage * (1000000 - 100 * this.distanceFromOrigin));
/*  75 */     if (this.isInVisitedList)
/*  76 */       this.remainingTime += 1500.0F; 
/*  77 */     this.hasBeenHurt = (marioDamage != 0);
/*     */     
/*  79 */     return this.remainingTime;
/*     */   }
/*     */   
/*     */   public ArrayList<SearchNode> generateChildren() {
/*  83 */     ArrayList<SearchNode> list = new ArrayList<>();
/*  84 */     ArrayList<boolean[]> possibleActions = Helper.createPossibleActions(this);
/*  85 */     if (isLeafNode()) {
/*  86 */       possibleActions.clear();
/*     */     }
/*  88 */     for (boolean[] action : possibleActions) {
/*  89 */       list.add(new SearchNode(action, this.repetitions, this));
/*     */     }
/*  91 */     return list;
/*     */   }
/*     */   
/*     */   public boolean isLeafNode() {
/*  95 */     if (this.sceneSnapshot == null) {
/*  96 */       return false;
/*     */     }
/*  98 */     return (this.sceneSnapshot.getGameStatus() != GameStatus.RUNNING);
/*     */   }
/*     */   
/*     */   public int ifLose() {
/* 102 */     if (this.sceneSnapshot == null) {
/* 103 */       return 0;
/*     */     }
/* 105 */     if (this.sceneSnapshot.getGameStatus() == GameStatus.LOSE) {
/* 106 */       return 1;
/*     */     }
/* 108 */     return 0;
/*     */   }
/*     */   
/*     */   private float maxForwardMovement(float initialSpeed, int ticks) {
/* 112 */     float y = ticks;
/* 113 */     float s0 = initialSpeed;
/* 114 */     return (float)(99.17355373D * Math.pow(0.89D, (y + 1.0F)) - 9.090909091D * s0 * Math.pow(0.89D, (y + 1.0F)) + 10.90909091D * y - 
/* 115 */       88.26446282D + 9.090909091D * s0);
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\runner\SearchNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */