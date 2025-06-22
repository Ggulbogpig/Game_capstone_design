/*     */ package engine.sprites;
/*     */ 
/*     */ import engine.core.MarioEffect;
/*     */ import engine.core.MarioSprite;
/*     */ import engine.effects.DeathEffect;
/*     */ import engine.effects.SquishEffect;
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.SpriteType;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class Enemy
/*     */   extends MarioSprite
/*     */ {
/*     */   private static final float GROUND_INERTIA = 0.89F;
/*     */   private static final float AIR_INERTIA = 0.89F;
/*     */   protected boolean onGround = false;
/*     */   protected boolean avoidCliffs = true;
/*     */   protected boolean winged = true;
/*     */   protected boolean noFireballDeath;
/*     */   protected float runTime;
/*  23 */   protected int wingTime = 0;
/*     */   protected MarioImage wingGraphics;
/*     */   protected MarioImage graphics;
/*     */   
/*     */   public Enemy(boolean visuals, float x, float y, int dir, SpriteType type) {
/*  28 */     super(x, y, type);
/*  29 */     this.width = 4;
/*  30 */     this.height = 24;
/*  31 */     if (this.type != SpriteType.RED_KOOPA && this.type != SpriteType.GREEN_KOOPA && 
/*  32 */       this.type != SpriteType.RED_KOOPA_WINGED && this.type != SpriteType.GREEN_KOOPA_WINGED) {
/*  33 */       this.height = 12;
/*     */     }
/*  35 */     this.winged = (this.type.getValue() % 2 == 1);
/*  36 */     this.avoidCliffs = !(this.type != SpriteType.RED_KOOPA && this.type != SpriteType.RED_KOOPA_WINGED);
/*  37 */     this.noFireballDeath = !(this.type != SpriteType.SPIKY && this.type != SpriteType.SPIKY_WINGED);
/*  38 */     this.facing = dir;
/*  39 */     if (this.facing == 0) {
/*  40 */       this.facing = 1;
/*     */     }
/*     */     
/*  43 */     if (visuals) {
/*  44 */       this.graphics = new MarioImage(Assets.enemies, this.type.getStartIndex());
/*  45 */       this.graphics.originX = 8;
/*  46 */       this.graphics.originY = 31;
/*  47 */       this.graphics.width = 16;
/*     */       
/*  49 */       this.wingGraphics = new MarioImage(Assets.enemies, 32);
/*  50 */       this.wingGraphics.originX = 16;
/*  51 */       this.wingGraphics.originY = 31;
/*  52 */       this.wingGraphics.width = 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  58 */     Enemy e = new Enemy(false, this.x, this.y, this.facing, this.type);
/*  59 */     e.xa = this.xa;
/*  60 */     e.ya = this.ya;
/*  61 */     e.initialCode = this.initialCode;
/*  62 */     e.width = this.width;
/*  63 */     e.height = this.height;
/*  64 */     e.onGround = this.onGround;
/*  65 */     e.winged = this.winged;
/*  66 */     e.avoidCliffs = this.avoidCliffs;
/*  67 */     e.noFireballDeath = this.noFireballDeath;
/*  68 */     return e;
/*     */   }
/*     */   
/*     */   public void collideCheck() {
/*  72 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     float xMarioD = this.world.mario.x - this.x;
/*  77 */     float yMarioD = this.world.mario.y - this.y;
/*  78 */     if (xMarioD > (-this.width * 2 - 4) && xMarioD < (this.width * 2 + 4) && 
/*  79 */       yMarioD > -this.height && yMarioD < this.world.mario.height) {
/*  80 */       if (this.type != SpriteType.SPIKY && this.type != SpriteType.SPIKY_WINGED && this.type != SpriteType.ENEMY_FLOWER && 
/*  81 */         this.world.mario.ya > 0.0F && yMarioD <= 0.0F && (!this.world.mario.onGround || !this.world.mario.wasOnGround)) {
/*  82 */         this.world.mario.stomp(this);
/*  83 */         if (this.winged) {
/*  84 */           this.winged = false;
/*  85 */           this.ya = 0.0F;
/*     */         } else {
/*  87 */           if (this.type == SpriteType.GREEN_KOOPA || this.type == SpriteType.GREEN_KOOPA_WINGED) {
/*  88 */             this.world.addSprite(new Shell((this.graphics != null), this.x, this.y, 1, this.initialCode));
/*  89 */           } else if (this.type == SpriteType.RED_KOOPA || this.type == SpriteType.RED_KOOPA_WINGED) {
/*  90 */             this.world.addSprite(new Shell((this.graphics != null), this.x, this.y, 0, this.initialCode));
/*  91 */           } else if ((this.type == SpriteType.GOOMBA || this.type == SpriteType.GOOMBA_WINGED) && 
/*  92 */             this.graphics != null) {
/*  93 */             this.world.addEffect((MarioEffect)new SquishEffect(this.x, this.y - 7.0F));
/*     */           } 
/*     */           
/*  96 */           this.world.addEvent(EventType.STOMP_KILL, this.type.getValue());
/*  97 */           this.world.removeSprite(this);
/*     */         } 
/*     */       } else {
/* 100 */         this.world.addEvent(EventType.HURT, this.type.getValue());
/* 101 */         this.world.mario.getHurt();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateGraphics() {
/* 108 */     this.wingTime++;
/* 109 */     this.wingGraphics.index = 32 + this.wingTime / 4 % 2;
/*     */     
/* 111 */     this.graphics.flipX = (this.facing == -1);
/* 112 */     this.runTime += Math.abs(this.xa) + 5.0F;
/*     */     
/* 114 */     int runFrame = (int)(this.runTime / 20.0F) % 2;
/*     */     
/* 116 */     if (!this.onGround) {
/* 117 */       runFrame = 1;
/*     */     }
/* 119 */     if (this.winged) {
/* 120 */       runFrame = this.wingTime / 4 % 2;
/*     */     }
/* 122 */     this.graphics.index = this.type.getStartIndex() + runFrame;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 127 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 131 */     float sideWaysSpeed = 1.75F;
/*     */     
/* 133 */     if (this.xa > 2.0F) {
/* 134 */       this.facing = 1;
/*     */     }
/* 136 */     if (this.xa < -2.0F) {
/* 137 */       this.facing = -1;
/*     */     }
/*     */     
/* 140 */     this.xa = this.facing * sideWaysSpeed;
/*     */     
/* 142 */     if (!move(this.xa, 0.0F))
/* 143 */       this.facing = -this.facing; 
/* 144 */     this.onGround = false;
/* 145 */     move(0.0F, this.ya);
/*     */     
/* 147 */     this.ya *= this.winged ? 0.95F : 0.85F;
/* 148 */     if (this.onGround) {
/* 149 */       this.xa *= 0.89F;
/*     */     } else {
/* 151 */       this.xa *= 0.89F;
/*     */     } 
/*     */     
/* 154 */     if (!this.onGround) {
/* 155 */       if (this.winged) {
/* 156 */         this.ya += 0.6F;
/*     */       } else {
/* 158 */         this.ya += 2.0F;
/*     */       } 
/* 160 */     } else if (this.winged) {
/* 161 */       this.ya = -10.0F;
/*     */     } 
/*     */     
/* 164 */     if (this.graphics != null) {
/* 165 */       updateGraphics();
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean move(float xa, float ya) {
/* 170 */     while (xa > 8.0F) {
/* 171 */       if (!move(8.0F, 0.0F))
/* 172 */         return false; 
/* 173 */       xa -= 8.0F;
/*     */     } 
/* 175 */     while (xa < -8.0F) {
/* 176 */       if (!move(-8.0F, 0.0F))
/* 177 */         return false; 
/* 178 */       xa += 8.0F;
/*     */     } 
/* 180 */     while (ya > 8.0F) {
/* 181 */       if (!move(0.0F, 8.0F))
/* 182 */         return false; 
/* 183 */       ya -= 8.0F;
/*     */     } 
/* 185 */     while (ya < -8.0F) {
/* 186 */       if (!move(0.0F, -8.0F))
/* 187 */         return false; 
/* 188 */       ya += 8.0F;
/*     */     } 
/*     */     
/* 191 */     boolean collide = false;
/* 192 */     if (ya > 0.0F)
/* 193 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, 0.0F)) {
/* 194 */         collide = true;
/* 195 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya, xa, 0.0F)) {
/* 196 */         collide = true;
/* 197 */       } else if (isBlocking(this.x + xa - this.width, this.y + ya + 1.0F, xa, ya)) {
/* 198 */         collide = true;
/* 199 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya + 1.0F, xa, ya)) {
/* 200 */         collide = true;
/*     */       }  
/* 202 */     if (ya < 0.0F)
/* 203 */       if (isBlocking(this.x + xa, this.y + ya - this.height, xa, ya)) {
/* 204 */         collide = true;
/* 205 */       } else if (collide || isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya)) {
/* 206 */         collide = true;
/* 207 */       } else if (collide || isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya)) {
/* 208 */         collide = true;
/*     */       }  
/* 210 */     if (xa > 0.0F) {
/* 211 */       if (isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya))
/* 212 */         collide = true; 
/* 213 */       if (isBlocking(this.x + xa + this.width, this.y + ya - (this.height / 2), xa, ya))
/* 214 */         collide = true; 
/* 215 */       if (isBlocking(this.x + xa + this.width, this.y + ya, xa, ya)) {
/* 216 */         collide = true;
/*     */       }
/* 218 */       if (this.avoidCliffs && this.onGround && 
/* 219 */         !this.world.level.isBlocking((int)((this.x + xa + this.width) / 16.0F), (int)(this.y / 16.0F + 1.0F), xa, 1.0F))
/* 220 */         collide = true; 
/*     */     } 
/* 222 */     if (xa < 0.0F) {
/* 223 */       if (isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya))
/* 224 */         collide = true; 
/* 225 */       if (isBlocking(this.x + xa - this.width, this.y + ya - (this.height / 2), xa, ya))
/* 226 */         collide = true; 
/* 227 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, ya)) {
/* 228 */         collide = true;
/*     */       }
/* 230 */       if (this.avoidCliffs && this.onGround && 
/* 231 */         !this.world.level.isBlocking((int)((this.x + xa - this.width) / 16.0F), (int)(this.y / 16.0F + 1.0F), xa, 1.0F)) {
/* 232 */         collide = true;
/*     */       }
/*     */     } 
/* 235 */     if (collide) {
/* 236 */       if (xa < 0.0F) {
/* 237 */         this.x = ((int)((this.x - this.width) / 16.0F) * 16 + this.width);
/* 238 */         this.xa = 0.0F;
/*     */       } 
/* 240 */       if (xa > 0.0F) {
/* 241 */         this.x = ((int)((this.x + this.width) / 16.0F + 1.0F) * 16 - this.width - 1);
/* 242 */         this.xa = 0.0F;
/*     */       } 
/* 244 */       if (ya < 0.0F) {
/* 245 */         this.y = ((int)((this.y - this.height) / 16.0F) * 16 + this.height);
/* 246 */         this.ya = 0.0F;
/*     */       } 
/* 248 */       if (ya > 0.0F) {
/* 249 */         this.y = ((int)(this.y / 16.0F + 1.0F) * 16 - 1);
/* 250 */         this.onGround = true;
/*     */       } 
/* 252 */       return false;
/*     */     } 
/* 254 */     this.x += xa;
/* 255 */     this.y += ya;
/* 256 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlocking(float _x, float _y, float xa, float ya) {
/* 261 */     int x = (int)(_x / 16.0F);
/* 262 */     int y = (int)(_y / 16.0F);
/* 263 */     if (x == (int)(this.x / 16.0F) && y == (int)(this.y / 16.0F)) {
/* 264 */       return false;
/*     */     }
/* 266 */     boolean blocking = this.world.level.isBlocking(x, y, xa, ya);
/*     */     
/* 268 */     return blocking;
/*     */   }
/*     */   
/*     */   public boolean shellCollideCheck(Shell shell) {
/* 272 */     if (!this.alive) {
/* 273 */       return false;
/*     */     }
/*     */     
/* 276 */     float xD = shell.x - this.x;
/* 277 */     float yD = shell.y - this.y;
/*     */     
/* 279 */     if (xD > -16.0F && xD < 16.0F && 
/* 280 */       yD > -this.height && yD < shell.height) {
/* 281 */       this.xa = (shell.facing * 2);
/* 282 */       this.ya = -5.0F;
/* 283 */       this.world.addEvent(EventType.SHELL_KILL, this.type.getValue());
/* 284 */       if (this.graphics != null) {
/* 285 */         if (this.type == SpriteType.GREEN_KOOPA || this.type == SpriteType.GREEN_KOOPA_WINGED) {
/* 286 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 42, -5.0F));
/* 287 */         } else if (this.type == SpriteType.RED_KOOPA || this.type == SpriteType.RED_KOOPA_WINGED) {
/* 288 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 41, -5.0F));
/* 289 */         } else if (this.type == SpriteType.GOOMBA || this.type == SpriteType.GOOMBA_WINGED) {
/* 290 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 44, -5.0F));
/* 291 */         } else if (this.type == SpriteType.SPIKY || this.type == SpriteType.SPIKY_WINGED) {
/* 292 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 45, -5.0F));
/*     */         } 
/*     */       }
/* 295 */       this.world.removeSprite(this);
/* 296 */       return true;
/*     */     } 
/*     */     
/* 299 */     return false;
/*     */   }
/*     */   
/*     */   public boolean fireballCollideCheck(Fireball fireball) {
/* 303 */     if (!this.alive) {
/* 304 */       return false;
/*     */     }
/*     */     
/* 307 */     float xD = fireball.x - this.x;
/* 308 */     float yD = fireball.y - this.y;
/*     */     
/* 310 */     if (xD > -16.0F && xD < 16.0F && 
/* 311 */       yD > -this.height && yD < fireball.height) {
/* 312 */       if (this.noFireballDeath) {
/* 313 */         return true;
/*     */       }
/* 315 */       this.xa = (fireball.facing * 2);
/* 316 */       this.ya = -5.0F;
/* 317 */       this.world.addEvent(EventType.FIRE_KILL, this.type.getValue());
/* 318 */       if (this.graphics != null) {
/* 319 */         if (this.type == SpriteType.GREEN_KOOPA || this.type == SpriteType.GREEN_KOOPA_WINGED) {
/* 320 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 42, -5.0F));
/* 321 */         } else if (this.type == SpriteType.RED_KOOPA || this.type == SpriteType.RED_KOOPA_WINGED) {
/* 322 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 41, -5.0F));
/* 323 */         } else if (this.type == SpriteType.GOOMBA || this.type == SpriteType.GOOMBA_WINGED) {
/* 324 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 44, -5.0F));
/*     */         } 
/*     */       }
/* 327 */       this.world.removeSprite(this);
/* 328 */       return true;
/*     */     } 
/*     */     
/* 331 */     return false;
/*     */   }
/*     */   
/*     */   public void bumpCheck(int xTile, int yTile) {
/* 335 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 339 */     if (this.x + this.width > (xTile * 16) && this.x - this.width < (xTile * 16 + 16) && yTile == (int)((this.y - 1.0F) / 16.0F)) {
/* 340 */       this.xa = (-this.world.mario.facing * 2);
/* 341 */       this.ya = -5.0F;
/* 342 */       if (this.graphics != null) {
/* 343 */         if (this.type == SpriteType.GREEN_KOOPA || this.type == SpriteType.GREEN_KOOPA_WINGED) {
/* 344 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 42, -5.0F));
/* 345 */         } else if (this.type == SpriteType.RED_KOOPA || this.type == SpriteType.RED_KOOPA_WINGED) {
/* 346 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 41, -5.0F));
/* 347 */         } else if (this.type == SpriteType.GOOMBA || this.type == SpriteType.GOOMBA_WINGED) {
/* 348 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 44, -5.0F));
/* 349 */         } else if (this.type == SpriteType.SPIKY || this.type == SpriteType.SPIKY_WINGED) {
/* 350 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 45, -5.0F));
/*     */         } 
/*     */       }
/* 353 */       this.world.removeSprite(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/* 359 */     if (this.winged && 
/* 360 */       this.type != SpriteType.RED_KOOPA && this.type != SpriteType.GREEN_KOOPA && this.type != SpriteType.RED_KOOPA_WINGED && 
/* 361 */       this.type != SpriteType.GREEN_KOOPA_WINGED) {
/* 362 */       this.wingGraphics.flipX = false;
/* 363 */       this.wingGraphics.render(og, (int)(this.x - this.world.cameraX - 6.0F), (int)(this.y - this.world.cameraY - 6.0F));
/* 364 */       this.wingGraphics.flipX = true;
/* 365 */       this.wingGraphics.render(og, (int)(this.x - this.world.cameraX + 22.0F), (int)(this.y - this.world.cameraY - 6.0F));
/*     */     } 
/*     */ 
/*     */     
/* 369 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */     
/* 371 */     if (this.winged && (
/* 372 */       this.type == SpriteType.RED_KOOPA || this.type == SpriteType.GREEN_KOOPA || this.type == SpriteType.RED_KOOPA_WINGED || 
/* 373 */       this.type == SpriteType.GREEN_KOOPA_WINGED)) {
/* 374 */       int shiftX = -1;
/* 375 */       if (this.graphics.flipX) {
/* 376 */         shiftX = 17;
/*     */       }
/* 378 */       this.wingGraphics.flipX = this.graphics.flipX;
/* 379 */       this.wingGraphics.render(og, (int)(this.x - this.world.cameraX + shiftX), (int)(this.y - this.world.cameraY - 8.0F));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\Enemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */