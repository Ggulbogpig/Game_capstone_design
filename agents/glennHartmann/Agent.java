/*     */ package agents.glennHartmann;
/*     */ 
/*     */ import engine.core.MarioAgent;
/*     */ import engine.core.MarioForwardModel;
/*     */ import engine.core.MarioTimer;
/*     */ import engine.helper.MarioActions;
/*     */ 
/*     */ public class Agent implements MarioAgent {
/*   9 */   private boolean[] action = new boolean[MarioActions.numberOfActions()];
/*  10 */   private int jumpCount = 0;
/*  11 */   private int speedCount = 0;
/*     */ 
/*     */   
/*     */   private boolean safeToJumpFromEnemies(byte[][] enemiesFromBitmap) {
/*  15 */     for (int y = 5; y <= 9; y++) {
/*  16 */       for (int x = 11; x <= 14; x++) {
/*  17 */         if ((x != 8 || y != 8) && enemiesFromBitmap[x][y] == 1) {
/*  18 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  23 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean safeToJumpFromGaps(byte[][] levelSceneFromBitmap) {
/*  28 */     for (int y = 9; y <= 9; y++) {
/*  29 */       boolean b = false;
/*  30 */       for (int x = 11; x <= 14; x++) {
/*  31 */         if (levelSceneFromBitmap[x][y] == 1) {
/*  32 */           b = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  36 */       if (!b) {
/*  37 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean dangerFromEnemies(byte[][] enemiesFromBitmap) {
/*  47 */     for (int y = 7; y <= 9; y++) {
/*  48 */       for (int x = 8; x <= 12; x++) {
/*  49 */         if ((x != 8 || y != 8) && enemiesFromBitmap[x][y] == 1) {
/*  50 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean dangerFromGaps(byte[][] levelSceneFromBitmap) {
/*  61 */     for (int y = 9; y <= 10; y++) {
/*  62 */       for (int x = 9; x <= 12; x++) {
/*  63 */         if (levelSceneFromBitmap[x][y] == 0) {
/*  64 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean safeToJump(byte[][] levelSceneFromBitmap, byte[][] enemiesFromBitmap) {
/*  74 */     return (safeToJumpFromGaps(levelSceneFromBitmap) && safeToJumpFromEnemies(enemiesFromBitmap));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean block(byte[][] levelSceneFromBitmap) {
/*  80 */     for (int y = 8; y <= 8; y++) {
/*  81 */       for (int x = 9; x <= 12; x++) {
/*  82 */         if (levelSceneFromBitmap[x][y] == 1) {
/*  83 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[][] decode(MarioForwardModel model, int[][] state) {
/*  93 */     model.getClass(); model.getClass(); byte[][] dstate = new byte[16][16];
/*  94 */     for (int i = 0; i < dstate.length; i++) {
/*  95 */       for (int j = 0; j < (dstate[0]).length; j++)
/*  96 */         dstate[i][j] = 2; 
/*     */     } 
/*  98 */     for (int x = 0; x < state.length; x++) {
/*  99 */       for (int y = 0; y < (state[x]).length; y++) {
/* 100 */         if (state[x][y] != 0) {
/* 101 */           dstate[x][y] = 1;
/*     */         } else {
/* 103 */           dstate[x][y] = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/* 107 */     return dstate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 112 */     this.action = new boolean[MarioActions.numberOfActions()];
/* 113 */     this.action[MarioActions.RIGHT.getValue()] = true;
/* 114 */     this.action[MarioActions.SPEED.getValue()] = true;
/* 115 */     this.action[MarioActions.JUMP.getValue()] = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 120 */     byte[][] levelSceneFromBitmap = decode(model, model.getMarioSceneObservation());
/* 121 */     byte[][] enemiesFromBitmap = decode(model, model.getMarioEnemiesObservation());
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (this.action[MarioActions.JUMP.getValue()] && this.jumpCount >= 8) {
/* 126 */       this.action[MarioActions.JUMP.getValue()] = false;
/* 127 */       this.jumpCount = 0;
/*     */     
/*     */     }
/* 130 */     else if (this.action[MarioActions.JUMP.getValue()]) {
/* 131 */       this.jumpCount++;
/*     */ 
/*     */     
/*     */     }
/* 135 */     else if ((((dangerFromEnemies(enemiesFromBitmap) || block(levelSceneFromBitmap)) && 
/* 136 */       safeToJump(levelSceneFromBitmap, enemiesFromBitmap)) || dangerFromGaps(levelSceneFromBitmap)) && 
/* 137 */       model.mayMarioJump()) {
/* 138 */       this.action[MarioActions.JUMP.getValue()] = true;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     if (this.action[MarioActions.SPEED.getValue()] && this.speedCount >= 10) {
/* 143 */       this.action[MarioActions.SPEED.getValue()] = false;
/* 144 */       this.speedCount = 0;
/* 145 */     } else if (this.action[MarioActions.SPEED.getValue()]) {
/* 146 */       this.speedCount++;
/*     */     } else {
/* 148 */       this.action[MarioActions.SPEED.getValue()] = true;
/*     */     } 
/*     */     
/* 151 */     return this.action;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAgentName() {
/* 156 */     return "GlennHartmannAgent";
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\glennHartmann\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */