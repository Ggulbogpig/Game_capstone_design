/*     */ package engine.core;
/*     */ 
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.GameStatus;
/*     */ import engine.helper.SpriteType;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarioResult
/*     */ {
/*     */   private MarioWorld world;
/*     */   private ArrayList<MarioEvent> gameEvents;
/*     */   private ArrayList<MarioAgentEvent> agentEvents;
/*     */   
/*     */   public MarioResult(MarioWorld world, ArrayList<MarioEvent> gameEvents, ArrayList<MarioAgentEvent> agentEvents) {
/*  22 */     this.world = world;
/*  23 */     this.gameEvents = gameEvents;
/*  24 */     this.agentEvents = agentEvents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameStatus getGameStatus() {
/*  33 */     return this.world.gameStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCompletionPercentage() {
/*  42 */     return this.world.mario.x / (this.world.level.exitTileX * 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRemainingTime() {
/*  51 */     return this.world.currentTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMarioMode() {
/*  60 */     int value = 0;
/*  61 */     if (this.world.mario.isLarge) {
/*  62 */       value = 1;
/*     */     }
/*  64 */     if (this.world.mario.isFire) {
/*  65 */       value = 2;
/*     */     }
/*  67 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MarioEvent> getGameEvents() {
/*  76 */     return this.gameEvents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MarioAgentEvent> getAgentEvents() {
/*  85 */     return this.agentEvents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKillsTotal() {
/*  94 */     int kills = 0;
/*  95 */     for (MarioEvent e : this.gameEvents) {
/*  96 */       if (e.getEventType() == EventType.STOMP_KILL.getValue() || e.getEventType() == EventType.FIRE_KILL.getValue() || 
/*  97 */         e.getEventType() == EventType.FALL_KILL.getValue() || e.getEventType() == EventType.SHELL_KILL.getValue()) {
/*  98 */         kills++;
/*     */       }
/*     */     } 
/* 101 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKillsByFire() {
/* 110 */     int kills = 0;
/* 111 */     for (MarioEvent e : this.gameEvents) {
/* 112 */       if (e.getEventType() == EventType.FIRE_KILL.getValue()) {
/* 113 */         kills++;
/*     */       }
/*     */     } 
/* 116 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKillsByStomp() {
/* 125 */     int kills = 0;
/* 126 */     for (MarioEvent e : this.gameEvents) {
/* 127 */       if (e.getEventType() == EventType.STOMP_KILL.getValue()) {
/* 128 */         kills++;
/*     */       }
/*     */     } 
/* 131 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKillsByShell() {
/* 140 */     int kills = 0;
/* 141 */     for (MarioEvent e : this.gameEvents) {
/* 142 */       if (e.getEventType() == EventType.SHELL_KILL.getValue()) {
/* 143 */         kills++;
/*     */       }
/*     */     } 
/* 146 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMarioNumKills(int enemyType) {
/* 156 */     int kills = 0;
/* 157 */     for (MarioEvent e : this.gameEvents) {
/* 158 */       if ((e.getEventType() == EventType.SHELL_KILL.getValue() || 
/* 159 */         e.getEventType() == EventType.FIRE_KILL.getValue() || 
/* 160 */         e.getEventType() == EventType.STOMP_KILL.getValue()) && e.getEventParam() == enemyType) {
/* 161 */         kills++;
/*     */       }
/*     */     } 
/* 164 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMarioNumHurts() {
/* 173 */     int hurt = 0;
/* 174 */     for (MarioEvent e : this.gameEvents) {
/* 175 */       if (e.getEventType() == EventType.HURT.getValue()) {
/* 176 */         hurt++;
/*     */       }
/*     */     } 
/* 179 */     return hurt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumBumpQuestionBlock() {
/* 188 */     int bump = 0;
/* 189 */     for (MarioEvent e : this.gameEvents) {
/* 190 */       if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == 24) {
/* 191 */         bump++;
/*     */       }
/*     */     } 
/* 194 */     return bump;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumBumpBrick() {
/* 203 */     int bump = 0;
/* 204 */     for (MarioEvent e : this.gameEvents) {
/* 205 */       if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == 22) {
/* 206 */         bump++;
/*     */       }
/*     */     } 
/* 209 */     return bump;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKillsByFall() {
/* 218 */     int kills = 0;
/* 219 */     for (MarioEvent e : this.gameEvents) {
/* 220 */       if (e.getEventType() == EventType.FALL_KILL.getValue()) {
/* 221 */         kills++;
/*     */       }
/*     */     } 
/* 224 */     return kills;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumJumps() {
/* 233 */     int jumps = 0;
/* 234 */     for (MarioEvent e : this.gameEvents) {
/* 235 */       if (e.getEventType() == EventType.JUMP.getValue()) {
/* 236 */         jumps++;
/*     */       }
/*     */     } 
/* 239 */     return jumps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxXJump() {
/* 248 */     float maxXJump = 0.0F;
/* 249 */     float startX = -100.0F;
/* 250 */     for (MarioEvent e : this.gameEvents) {
/* 251 */       if (e.getEventType() == EventType.JUMP.getValue()) {
/* 252 */         startX = e.getMarioX();
/*     */       }
/* 254 */       if (e.getEventType() == EventType.LAND.getValue() && 
/* 255 */         Math.abs(e.getMarioX() - startX) > maxXJump) {
/* 256 */         maxXJump = Math.abs(e.getMarioX() - startX);
/*     */       }
/*     */     } 
/*     */     
/* 260 */     return maxXJump;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxJumpAirTime() {
/* 269 */     int maxAirJump = 0;
/* 270 */     int startTime = -100;
/* 271 */     for (MarioEvent e : this.gameEvents) {
/* 272 */       if (e.getEventType() == EventType.JUMP.getValue()) {
/* 273 */         startTime = e.getTime();
/*     */       }
/* 275 */       if (e.getEventType() == EventType.LAND.getValue() && 
/* 276 */         e.getTime() - startTime > maxAirJump) {
/* 277 */         maxAirJump = e.getTime() - startTime;
/*     */       }
/*     */     } 
/*     */     
/* 281 */     return maxAirJump;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentLives() {
/* 290 */     return this.world.lives;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentCoins() {
/* 299 */     return this.world.coins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumCollectedMushrooms() {
/* 308 */     int collect = 0;
/* 309 */     for (MarioEvent e : this.gameEvents) {
/* 310 */       if (e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == SpriteType.MUSHROOM.getValue()) {
/* 311 */         collect++;
/*     */       }
/*     */     } 
/* 314 */     return collect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumCollectedFireflower() {
/* 323 */     int collect = 0;
/* 324 */     for (MarioEvent e : this.gameEvents) {
/* 325 */       if (e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == SpriteType.FIRE_FLOWER.getValue()) {
/* 326 */         collect++;
/*     */       }
/*     */     } 
/* 329 */     return collect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumCollectedTileCoins() {
/* 338 */     int collect = 0;
/* 339 */     for (MarioEvent e : this.gameEvents) {
/* 340 */       if (e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == 31) {
/* 341 */         collect++;
/*     */       }
/*     */     } 
/* 344 */     return collect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumDestroyedBricks() {
/* 353 */     int bricks = 0;
/* 354 */     for (MarioEvent e : this.gameEvents) {
/* 355 */       if (e.getEventType() == EventType.BUMP.getValue() && 
/* 356 */         e.getEventParam() == 22 && e.getMarioState() > 0) {
/* 357 */         bricks++;
/*     */       }
/*     */     } 
/* 360 */     return bricks;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */