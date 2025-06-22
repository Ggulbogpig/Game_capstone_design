/*     */ package agents.trondEllingsen;
/*     */ 
/*     */ import engine.core.MarioAgent;
/*     */ import engine.core.MarioForwardModel;
/*     */ import engine.core.MarioTimer;
/*     */ import engine.helper.MarioActions;
/*     */ 
/*     */ public class Agent implements MarioAgent {
/*     */   private enum JumpType {
/*  10 */     ENEMY, GAP, WALL, NONE; }
/*     */   
/*     */   private class Rectangle {
/*     */     private float x;
/*     */     private float y;
/*     */     
/*     */     public Rectangle(float x, float y, float width, float height) {
/*  17 */       this.x = x;
/*  18 */       this.y = y;
/*  19 */       this.width = width;
/*  20 */       this.height = height;
/*     */     }
/*     */     private float width; private float height;
/*     */     public boolean contains(float x, float y) {
/*  24 */       return (x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height);
/*     */     }
/*     */   }
/*     */   
/*  28 */   private JumpType jumpType = JumpType.NONE;
/*  29 */   private int jumpCount = 0; private int jumpSize = -1;
/*  30 */   private float prevY = 0.0F;
/*     */   
/*     */   private boolean[] action;
/*     */   
/*     */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/*  35 */     this.action = new boolean[MarioActions.numberOfActions()];
/*  36 */     this.action[MarioActions.RIGHT.getValue()] = true;
/*  37 */     this.action[MarioActions.SPEED.getValue()] = true;
/*     */   }
/*     */   
/*     */   private int getWallHeight(int tileX, int tileY, int[][] levelScene) {
/*  41 */     int y = tileY + 1, wallHeight = 0;
/*  42 */     while (y-- > 0 && levelScene[tileX + 1][y] != 0) {
/*  43 */       wallHeight++;
/*     */     }
/*  45 */     return wallHeight;
/*     */   }
/*     */   
/*     */   private boolean dangerOfGap(int tileX, int tileY, int[][] levelScene) {
/*  49 */     for (int y = tileY + 1; y < (levelScene[0]).length; y++) {
/*  50 */       if (levelScene[tileX + 1][y] != 0) {
/*  51 */         return false;
/*     */       }
/*     */     } 
/*  54 */     return true;
/*     */   }
/*     */   
/*     */   private boolean enemyInRange(MarioForwardModel e, Rectangle r) {
/*  58 */     for (int i = 0; i < (e.getEnemiesFloatPos()).length; i += 3) {
/*  59 */       if (r.contains(e.getEnemiesFloatPos()[i + 1] - e.getMarioFloatPos()[0], 
/*  60 */           e.getMarioFloatPos()[1] - e.getEnemiesFloatPos()[i + 2])) {
/*  61 */         return true;
/*     */       }
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   private final void setJump(JumpType type, int size) {
/*  68 */     this.jumpType = type;
/*  69 */     this.jumpSize = size;
/*  70 */     this.jumpCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/*  75 */     float marioSpeed = model.getMarioFloatVelocity()[0];
/*  76 */     boolean dangerOfEnemy = enemyInRange(model, new Rectangle(-13.0F, -57.0F, 105.0F, 87.0F));
/*  77 */     boolean dangerOfEnemyAbove = enemyInRange(model, new Rectangle(-28.0F, 28.0F, 58.0F, 45.0F));
/*  78 */     boolean dangerOfGap = dangerOfGap(model.getMarioScreenTilePos()[0], model.getMarioScreenTilePos()[1], 
/*  79 */         model.getScreenSceneObservation());
/*  80 */     if ((model.isMarioOnGround() || model.mayMarioJump()) && !this.jumpType.equals(JumpType.NONE)) {
/*  81 */       setJump(JumpType.NONE, -1);
/*  82 */     } else if (model.mayMarioJump()) {
/*  83 */       int wallHeight = getWallHeight(model.getMarioScreenTilePos()[0], model.getMarioScreenTilePos()[1], 
/*  84 */           model.getScreenSceneObservation());
/*  85 */       if (dangerOfGap && marioSpeed > 0.0F) {
/*  86 */         setJump(JumpType.GAP, (marioSpeed < 6.0F) ? (int)(9.0F - marioSpeed) : 1);
/*  87 */       } else if (marioSpeed <= 1.0F && !dangerOfEnemyAbove && wallHeight > 0) {
/*  88 */         setJump(JumpType.WALL, (wallHeight >= 4) ? (wallHeight + 3) : wallHeight);
/*  89 */       } else if (dangerOfEnemy && (!dangerOfEnemyAbove || marioSpeed <= 2.0F)) {
/*  90 */         setJump(JumpType.ENEMY, 7);
/*     */       } 
/*     */     } else {
/*  93 */       this.jumpCount++;
/*     */     } 
/*  95 */     boolean isFalling = (this.prevY < model.getMarioFloatPos()[1] && this.jumpType.equals(JumpType.NONE));
/*  96 */     this.action[MarioActions.LEFT.getValue()] = (isFalling && ((dangerOfEnemy && dangerOfEnemyAbove) || dangerOfGap));
/*  97 */     this.action[MarioActions.RIGHT.getValue()] = (!isFalling && (!dangerOfEnemyAbove || this.jumpType != JumpType.WALL));
/*  98 */     this.action[MarioActions.JUMP.getValue()] = (!this.jumpType.equals(JumpType.NONE) && this.jumpCount < this.jumpSize);
/*  99 */     this.action[MarioActions.SPEED.getValue()] = !(this.jumpType.equals(JumpType.ENEMY) && this.action[MarioActions.SPEED.getValue()] && model.getMarioMode() == 2);
/* 100 */     this.prevY = model.getMarioFloatPos()[1];
/* 101 */     return this.action;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAgentName() {
/* 106 */     return "TrondEllingsen";
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\trondEllingsen\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */