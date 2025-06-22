/*     */ package engine.sprites;
/*     */ 
/*     */ import engine.core.MarioSprite;
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.MarioActions;
/*     */ import engine.helper.SpriteType;
/*     */ import engine.helper.TileFeature;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class Mario extends MarioSprite {
/*     */   public boolean isLarge;
/*     */   public boolean isFire;
/*     */   public boolean onGround;
/*  16 */   public boolean[] actions = null; public boolean wasOnGround; public boolean isDucking; public boolean canShoot; public boolean mayJump;
/*  17 */   public int jumpTime = 0;
/*     */   private float xJumpSpeed;
/*  19 */   private float yJumpSpeed = 0.0F;
/*  20 */   private int invulnerableTime = 0;
/*     */   
/*  22 */   private float marioFrameSpeed = 0.0F; private boolean oldLarge;
/*     */   private boolean oldFire = false;
/*  24 */   private MarioImage graphics = null;
/*     */ 
/*     */   
/*  27 */   private float xJumpStart = -100.0F;
/*     */   
/*  29 */   private final float GROUND_INERTIA = 0.89F;
/*  30 */   private final float AIR_INERTIA = 0.89F;
/*  31 */   private final int POWERUP_TIME = 3;
/*     */   
/*     */   public Mario(boolean visuals, float x, float y) {
/*  34 */     super(x + 8.0F, y + 15.0F, SpriteType.MARIO);
/*  35 */     this.isLarge = this.oldLarge = false;
/*  36 */     this.isFire = this.oldFire = false;
/*  37 */     this.width = 4;
/*  38 */     this.height = 24;
/*  39 */     if (visuals) {
/*  40 */       this.graphics = new MarioImage(Assets.smallMario, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  46 */     Mario sprite = new Mario(false, this.x - 8.0F, this.y - 15.0F);
/*  47 */     sprite.xa = this.xa;
/*  48 */     sprite.ya = this.ya;
/*  49 */     sprite.initialCode = this.initialCode;
/*  50 */     sprite.width = this.width;
/*  51 */     sprite.height = this.height;
/*  52 */     sprite.facing = this.facing;
/*  53 */     sprite.isLarge = this.isLarge;
/*  54 */     sprite.isFire = this.isFire;
/*  55 */     sprite.wasOnGround = this.wasOnGround;
/*  56 */     sprite.onGround = this.onGround;
/*  57 */     sprite.isDucking = this.isDucking;
/*  58 */     sprite.canShoot = this.canShoot;
/*  59 */     sprite.mayJump = this.mayJump;
/*  60 */     sprite.actions = new boolean[this.actions.length];
/*  61 */     for (int i = 0; i < this.actions.length; i++) {
/*  62 */       sprite.actions[i] = this.actions[i];
/*     */     }
/*  64 */     sprite.xJumpSpeed = this.xJumpSpeed;
/*  65 */     sprite.yJumpSpeed = this.yJumpSpeed;
/*  66 */     sprite.invulnerableTime = this.invulnerableTime;
/*  67 */     sprite.jumpTime = this.jumpTime;
/*  68 */     sprite.xJumpStart = this.xJumpStart;
/*  69 */     return sprite;
/*     */   }
/*     */   
/*     */   private boolean move(float xa, float ya) {
/*  73 */     while (xa > 8.0F) {
/*  74 */       if (!move(8.0F, 0.0F))
/*  75 */         return false; 
/*  76 */       xa -= 8.0F;
/*     */     } 
/*  78 */     while (xa < -8.0F) {
/*  79 */       if (!move(-8.0F, 0.0F))
/*  80 */         return false; 
/*  81 */       xa += 8.0F;
/*     */     } 
/*  83 */     while (ya > 8.0F) {
/*  84 */       if (!move(0.0F, 8.0F))
/*  85 */         return false; 
/*  86 */       ya -= 8.0F;
/*     */     } 
/*  88 */     while (ya < -8.0F) {
/*  89 */       if (!move(0.0F, -8.0F))
/*  90 */         return false; 
/*  91 */       ya += 8.0F;
/*     */     } 
/*     */     
/*  94 */     boolean collide = false;
/*  95 */     if (ya > 0.0F)
/*  96 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, 0.0F)) {
/*  97 */         collide = true;
/*  98 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya, xa, 0.0F)) {
/*  99 */         collide = true;
/* 100 */       } else if (isBlocking(this.x + xa - this.width, this.y + ya + 1.0F, xa, ya)) {
/* 101 */         collide = true;
/* 102 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya + 1.0F, xa, ya)) {
/* 103 */         collide = true;
/*     */       }  
/* 105 */     if (ya < 0.0F)
/* 106 */       if (isBlocking(this.x + xa, this.y + ya - this.height, xa, ya)) {
/* 107 */         collide = true;
/* 108 */       } else if (collide || isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya)) {
/* 109 */         collide = true;
/* 110 */       } else if (collide || isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya)) {
/* 111 */         collide = true;
/*     */       }  
/* 113 */     if (xa > 0.0F) {
/* 114 */       if (isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya))
/* 115 */         collide = true; 
/* 116 */       if (isBlocking(this.x + xa + this.width, this.y + ya - (this.height / 2), xa, ya))
/* 117 */         collide = true; 
/* 118 */       if (isBlocking(this.x + xa + this.width, this.y + ya, xa, ya))
/* 119 */         collide = true; 
/*     */     } 
/* 121 */     if (xa < 0.0F) {
/* 122 */       if (isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya))
/* 123 */         collide = true; 
/* 124 */       if (isBlocking(this.x + xa - this.width, this.y + ya - (this.height / 2), xa, ya))
/* 125 */         collide = true; 
/* 126 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, ya))
/* 127 */         collide = true; 
/*     */     } 
/* 129 */     if (collide) {
/* 130 */       if (xa < 0.0F) {
/* 131 */         this.x = ((int)((this.x - this.width) / 16.0F) * 16 + this.width);
/* 132 */         this.xa = 0.0F;
/*     */       } 
/* 134 */       if (xa > 0.0F) {
/* 135 */         this.x = ((int)((this.x + this.width) / 16.0F + 1.0F) * 16 - this.width - 1);
/* 136 */         this.xa = 0.0F;
/*     */       } 
/* 138 */       if (ya < 0.0F) {
/* 139 */         this.y = ((int)((this.y - this.height) / 16.0F) * 16 + this.height);
/* 140 */         this.jumpTime = 0;
/* 141 */         this.ya = 0.0F;
/*     */       } 
/* 143 */       if (ya > 0.0F) {
/* 144 */         this.y = ((int)((this.y - 1.0F) / 16.0F + 1.0F) * 16 - 1);
/* 145 */         this.onGround = true;
/*     */       } 
/* 147 */       return false;
/*     */     } 
/* 149 */     this.x += xa;
/* 150 */     this.y += ya;
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlocking(float _x, float _y, float xa, float ya) {
/* 156 */     int xTile = (int)(_x / 16.0F);
/* 157 */     int yTile = (int)(_y / 16.0F);
/* 158 */     if (xTile == (int)(this.x / 16.0F) && yTile == (int)(this.y / 16.0F)) {
/* 159 */       return false;
/*     */     }
/* 161 */     boolean blocking = this.world.level.isBlocking(xTile, yTile, xa, ya);
/* 162 */     int block = this.world.level.getBlock(xTile, yTile);
/*     */     
/* 164 */     if (TileFeature.getTileType(block).contains(TileFeature.PICKABLE)) {
/* 165 */       this.world.addEvent(EventType.COLLECT, block);
/* 166 */       collectCoin();
/* 167 */       this.world.level.setBlock(xTile, yTile, 0);
/*     */     } 
/* 169 */     if (blocking && ya < 0.0F) {
/* 170 */       this.world.bump(xTile, yTile, this.isLarge);
/*     */     }
/* 172 */     return blocking;
/*     */   }
/*     */   public void updateGraphics() {
/*     */     boolean currentLarge, currentFire;
/* 176 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 181 */     if (this.world.pauseTimer > 0) {
/* 182 */       currentLarge = (this.world.pauseTimer / 2 % 2 == 0) ? this.oldLarge : this.isLarge;
/* 183 */       currentFire = (this.world.pauseTimer / 2 % 2 == 0) ? this.oldFire : this.isFire;
/*     */     } else {
/* 185 */       currentLarge = this.isLarge;
/* 186 */       currentFire = this.isFire;
/*     */     } 
/* 188 */     if (currentLarge) {
/* 189 */       this.graphics.sheet = Assets.mario;
/* 190 */       if (currentFire) {
/* 191 */         this.graphics.sheet = Assets.fireMario;
/*     */       }
/*     */       
/* 194 */       this.graphics.originX = 16;
/* 195 */       this.graphics.originY = 31;
/* 196 */       this.graphics.width = this.graphics.height = 32;
/*     */     } else {
/* 198 */       this.graphics.sheet = Assets.smallMario;
/* 199 */       this.graphics.originX = 8;
/* 200 */       this.graphics.originY = 15;
/* 201 */       this.graphics.width = this.graphics.height = 16;
/*     */     } 
/*     */     
/* 204 */     this.marioFrameSpeed += Math.abs(this.xa) + 5.0F;
/* 205 */     if (Math.abs(this.xa) < 0.5F) {
/* 206 */       this.marioFrameSpeed = 0.0F;
/*     */     }
/*     */     
/* 209 */     this.graphics.visible = ((this.invulnerableTime / 2 & 0x1) == 0);
/* 210 */     this.graphics.flipX = (this.facing == -1);
/*     */     
/* 212 */     int frameIndex = 0;
/* 213 */     if (currentLarge) {
/* 214 */       frameIndex = (int)(this.marioFrameSpeed / 20.0F) % 4;
/* 215 */       if (frameIndex == 3)
/* 216 */         frameIndex = 1; 
/* 217 */       if (Math.abs(this.xa) > 10.0F)
/* 218 */         frameIndex += 3; 
/* 219 */       if (!this.onGround)
/* 220 */         if (Math.abs(this.xa) > 10.0F) {
/* 221 */           frameIndex = 6;
/*     */         } else {
/* 223 */           frameIndex = 5;
/*     */         }  
/*     */     } else {
/* 226 */       frameIndex = (int)(this.marioFrameSpeed / 20.0F) % 2;
/* 227 */       if (Math.abs(this.xa) > 10.0F)
/* 228 */         frameIndex += 2; 
/* 229 */       if (!this.onGround) {
/* 230 */         if (Math.abs(this.xa) > 10.0F) {
/* 231 */           frameIndex = 5;
/*     */         } else {
/* 233 */           frameIndex = 4;
/*     */         } 
/*     */       }
/*     */     } 
/* 237 */     if (this.onGround && ((this.facing == -1 && this.xa > 0.0F) || (this.facing == 1 && this.xa < 0.0F)) && (
/* 238 */       this.xa > 1.0F || this.xa < -1.0F)) {
/* 239 */       frameIndex = currentLarge ? 8 : 7;
/*     */     }
/*     */     
/* 242 */     if (currentLarge && this.isDucking) {
/* 243 */       frameIndex = 13;
/*     */     }
/*     */     
/* 246 */     this.graphics.index = frameIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 251 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 255 */     if (this.invulnerableTime > 0) {
/* 256 */       this.invulnerableTime--;
/*     */     }
/* 258 */     this.wasOnGround = this.onGround;
/*     */     
/* 260 */     float sideWaysSpeed = this.actions[MarioActions.SPEED.getValue()] ? 1.2F : 0.6F;
/*     */     
/* 262 */     if (this.onGround) {
/* 263 */       this.isDucking = (this.actions[MarioActions.DOWN.getValue()] && this.isLarge);
/*     */     }
/*     */     
/* 266 */     if (this.isLarge) {
/* 267 */       this.height = this.isDucking ? 12 : 24;
/*     */     } else {
/* 269 */       this.height = 12;
/*     */     } 
/*     */     
/* 272 */     if (this.xa > 2.0F) {
/* 273 */       this.facing = 1;
/*     */     }
/* 275 */     if (this.xa < -2.0F) {
/* 276 */       this.facing = -1;
/*     */     }
/*     */     
/* 279 */     if (this.actions[MarioActions.JUMP.getValue()] || (this.jumpTime < 0 && !this.onGround)) {
/* 280 */       if (this.jumpTime < 0) {
/* 281 */         this.xa = this.xJumpSpeed;
/* 282 */         this.ya = -this.jumpTime * this.yJumpSpeed;
/* 283 */         this.jumpTime++;
/* 284 */       } else if (this.onGround && this.mayJump) {
/* 285 */         this.xJumpSpeed = 0.0F;
/* 286 */         this.yJumpSpeed = -1.9F;
/* 287 */         this.jumpTime = 7;
/* 288 */         this.ya = this.jumpTime * this.yJumpSpeed;
/* 289 */         this.onGround = false;
/* 290 */         if (!isBlocking(this.x, this.y - 4.0F - this.height, 0.0F, -4.0F) && !isBlocking(this.x - this.width, this.y - 4.0F - this.height, 0.0F, -4.0F) && 
/* 291 */           !isBlocking(this.x + this.width, this.y - 4.0F - this.height, 0.0F, -4.0F)) {
/* 292 */           this.xJumpStart = this.x;
/* 293 */           this.world.addEvent(EventType.JUMP, 0);
/*     */         } 
/* 295 */       } else if (this.jumpTime > 0) {
/* 296 */         this.xa += this.xJumpSpeed;
/* 297 */         this.ya = this.jumpTime * this.yJumpSpeed;
/* 298 */         this.jumpTime--;
/*     */       } 
/*     */     } else {
/* 301 */       this.jumpTime = 0;
/*     */     } 
/*     */     
/* 304 */     if (this.actions[MarioActions.LEFT.getValue()] && !this.isDucking) {
/* 305 */       this.xa -= sideWaysSpeed;
/* 306 */       if (this.jumpTime >= 0) {
/* 307 */         this.facing = -1;
/*     */       }
/*     */     } 
/* 310 */     if (this.actions[MarioActions.RIGHT.getValue()] && !this.isDucking) {
/* 311 */       this.xa += sideWaysSpeed;
/* 312 */       if (this.jumpTime >= 0) {
/* 313 */         this.facing = 1;
/*     */       }
/*     */     } 
/* 316 */     if (this.actions[MarioActions.SPEED.getValue()] && this.canShoot && this.isFire && this.world.fireballsOnScreen < 2) {
/* 317 */       this.world.addSprite(new Fireball((this.graphics != null), this.x + (this.facing * 6), this.y - 20.0F, this.facing));
/*     */     }
/*     */     
/* 320 */     this.canShoot = !this.actions[MarioActions.SPEED.getValue()];
/*     */     
/* 322 */     this.mayJump = (this.onGround && !this.actions[MarioActions.JUMP.getValue()]);
/*     */     
/* 324 */     if (Math.abs(this.xa) < 0.5F) {
/* 325 */       this.xa = 0.0F;
/*     */     }
/*     */     
/* 328 */     this.onGround = false;
/* 329 */     move(this.xa, 0.0F);
/* 330 */     move(0.0F, this.ya);
/* 331 */     if (!this.wasOnGround && this.onGround && this.xJumpStart >= 0.0F) {
/* 332 */       this.world.addEvent(EventType.LAND, 0);
/* 333 */       this.xJumpStart = -100.0F;
/*     */     } 
/*     */     
/* 336 */     if (this.x < 0.0F) {
/* 337 */       this.x = 0.0F;
/* 338 */       this.xa = 0.0F;
/*     */     } 
/*     */     
/* 341 */     if (this.x > (this.world.level.exitTileX * 16)) {
/* 342 */       this.x = (this.world.level.exitTileX * 16);
/* 343 */       this.xa = 0.0F;
/* 344 */       this.world.win();
/*     */     } 
/*     */     
/* 347 */     this.ya *= 0.85F;
/* 348 */     if (this.onGround) {
/* 349 */       this.xa *= 0.89F;
/*     */     } else {
/* 351 */       this.xa *= 0.89F;
/*     */     } 
/*     */     
/* 354 */     if (!this.onGround) {
/* 355 */       this.ya += 3.0F;
/*     */     }
/*     */     
/* 358 */     if (this.graphics != null) {
/* 359 */       updateGraphics();
/*     */     }
/*     */   }
/*     */   
/*     */   public void stomp(Enemy enemy) {
/* 364 */     if (!this.alive) {
/*     */       return;
/*     */     }
/* 367 */     float targetY = enemy.y - (enemy.height / 2);
/* 368 */     move(0.0F, targetY - this.y);
/*     */     
/* 370 */     this.xJumpSpeed = 0.0F;
/* 371 */     this.yJumpSpeed = -1.9F;
/* 372 */     this.jumpTime = 8;
/* 373 */     this.ya = this.jumpTime * this.yJumpSpeed;
/* 374 */     this.onGround = false;
/* 375 */     this.invulnerableTime = 1;
/*     */   }
/*     */   
/*     */   public void stomp(Shell shell) {
/* 379 */     if (!this.alive) {
/*     */       return;
/*     */     }
/* 382 */     float targetY = shell.y - (shell.height / 2);
/* 383 */     move(0.0F, targetY - this.y);
/*     */     
/* 385 */     this.xJumpSpeed = 0.0F;
/* 386 */     this.yJumpSpeed = -1.9F;
/* 387 */     this.jumpTime = 8;
/* 388 */     this.ya = this.jumpTime * this.yJumpSpeed;
/* 389 */     this.onGround = false;
/* 390 */     this.invulnerableTime = 1;
/*     */   }
/*     */   
/*     */   public void getHurt() {
/* 394 */     if (this.invulnerableTime > 0 || !this.alive) {
/*     */       return;
/*     */     }
/* 397 */     if (this.isLarge) {
/* 398 */       this.world.pauseTimer = 9;
/* 399 */       this.oldLarge = this.isLarge;
/* 400 */       this.oldFire = this.isFire;
/* 401 */       if (this.isFire) {
/* 402 */         this.isFire = false;
/*     */       } else {
/* 404 */         this.isLarge = false;
/*     */       } 
/* 406 */       this.invulnerableTime = 32;
/*     */     }
/* 408 */     else if (this.world != null) {
/* 409 */       this.world.lose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void getFlower() {
/* 415 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 419 */     if (!this.isFire) {
/* 420 */       this.world.pauseTimer = 9;
/* 421 */       this.oldFire = this.isFire;
/* 422 */       this.oldLarge = this.isLarge;
/* 423 */       this.isFire = true;
/* 424 */       this.isLarge = true;
/*     */     } else {
/* 426 */       collectCoin();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getMushroom() {
/* 431 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 435 */     if (!this.isLarge) {
/* 436 */       this.world.pauseTimer = 9;
/* 437 */       this.oldFire = this.isFire;
/* 438 */       this.oldLarge = this.isLarge;
/* 439 */       this.isLarge = true;
/*     */     } else {
/* 441 */       collectCoin();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void kick(Shell shell) {
/* 446 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 450 */     this.invulnerableTime = 1;
/*     */   }
/*     */   
/*     */   public void stomp(BulletBill bill) {
/* 454 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 458 */     float targetY = bill.y - (bill.height / 2);
/* 459 */     move(0.0F, targetY - this.y);
/*     */     
/* 461 */     this.xJumpSpeed = 0.0F;
/* 462 */     this.yJumpSpeed = -1.9F;
/* 463 */     this.jumpTime = 8;
/* 464 */     this.ya = this.jumpTime * this.yJumpSpeed;
/* 465 */     this.onGround = false;
/* 466 */     this.invulnerableTime = 1;
/*     */   }
/*     */   
/*     */   public String getMarioType() {
/* 470 */     if (this.isFire) {
/* 471 */       return "fire";
/*     */     }
/* 473 */     if (this.isLarge) {
/* 474 */       return "large";
/*     */     }
/* 476 */     return "small";
/*     */   }
/*     */   
/*     */   public void collect1Up() {
/* 480 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 484 */     this.world.lives++;
/*     */   }
/*     */   
/*     */   public void collectCoin() {
/* 488 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 492 */     this.world.coins++;
/* 493 */     if (this.world.coins % 100 == 0) {
/* 494 */       collect1Up();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/* 500 */     super.render(og);
/*     */     
/* 502 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\Mario.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */