/*     */ package engine.sprites;
/*     */ 
/*     */ import engine.core.MarioEffect;
/*     */ import engine.core.MarioSprite;
/*     */ import engine.effects.DeathEffect;
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.SpriteType;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class Shell
/*     */   extends MarioSprite {
/*     */   private static final float GROUND_INERTIA = 0.89F;
/*     */   private static final float AIR_INERTIA = 0.89F;
/*  16 */   private int shellType = 0;
/*     */   
/*     */   private boolean onGround = false;
/*     */   private MarioImage graphics;
/*     */   
/*     */   public Shell(boolean visuals, float x, float y, int shellType, String spriteCode) {
/*  22 */     super(x, y, SpriteType.SHELL);
/*     */     
/*  24 */     this.width = 4;
/*  25 */     this.height = 12;
/*  26 */     this.facing = 0;
/*  27 */     this.ya = -5.0F;
/*  28 */     this.shellType = shellType;
/*  29 */     this.initialCode = spriteCode;
/*     */     
/*  31 */     if (visuals) {
/*  32 */       this.graphics = new MarioImage(Assets.enemies, shellType * 8 + 3);
/*  33 */       this.graphics.originX = 8;
/*  34 */       this.graphics.originY = 31;
/*  35 */       this.graphics.width = 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  41 */     Shell sprite = new Shell(false, this.x, this.y, this.shellType, this.initialCode);
/*  42 */     sprite.xa = this.xa;
/*  43 */     sprite.ya = this.ya;
/*  44 */     sprite.width = this.width;
/*  45 */     sprite.height = this.height;
/*  46 */     sprite.facing = this.facing;
/*  47 */     sprite.onGround = this.onGround;
/*  48 */     return sprite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  53 */     if (!this.alive)
/*     */       return; 
/*  55 */     super.update();
/*     */     
/*  57 */     float sideWaysSpeed = 11.0F;
/*     */     
/*  59 */     if (this.xa > 2.0F) {
/*  60 */       this.facing = 1;
/*     */     }
/*  62 */     if (this.xa < -2.0F) {
/*  63 */       this.facing = -1;
/*     */     }
/*     */     
/*  66 */     this.xa = this.facing * sideWaysSpeed;
/*     */     
/*  68 */     if (this.facing != 0) {
/*  69 */       this.world.checkShellCollide(this);
/*     */     }
/*     */     
/*  72 */     if (!move(this.xa, 0.0F)) {
/*  73 */       this.facing = -this.facing;
/*     */     }
/*  75 */     this.onGround = false;
/*  76 */     move(0.0F, this.ya);
/*     */     
/*  78 */     this.ya *= 0.85F;
/*  79 */     if (this.onGround) {
/*  80 */       this.xa *= 0.89F;
/*     */     } else {
/*  82 */       this.xa *= 0.89F;
/*     */     } 
/*     */     
/*  85 */     if (!this.onGround) {
/*  86 */       this.ya += 2.0F;
/*     */     }
/*     */     
/*  89 */     if (this.graphics != null) {
/*  90 */       this.graphics.flipX = (this.facing == -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/*  96 */     super.render(og);
/*  97 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */   }
/*     */   
/*     */   public boolean fireballCollideCheck(Fireball fireball) {
/* 101 */     if (!this.alive) return false;
/*     */     
/* 103 */     float xD = fireball.x - this.x;
/* 104 */     float yD = fireball.y - this.y;
/*     */     
/* 106 */     if (xD > -16.0F && xD < 16.0F && 
/* 107 */       yD > -this.height && yD < fireball.height) {
/* 108 */       if (this.facing != 0) {
/* 109 */         return true;
/*     */       }
/* 111 */       this.xa = (fireball.facing * 2);
/* 112 */       this.ya = -5.0F;
/* 113 */       if (this.graphics != null) {
/* 114 */         this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y, this.graphics.flipX, 41 + this.shellType, -5.0F));
/*     */       }
/* 116 */       this.world.removeSprite(this);
/* 117 */       return true;
/*     */     } 
/*     */     
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public void collideCheck() {
/* 124 */     if (!this.alive)
/*     */       return; 
/* 126 */     float xMarioD = this.world.mario.x - this.x;
/* 127 */     float yMarioD = this.world.mario.y - this.y;
/* 128 */     if (xMarioD > -16.0F && xMarioD < 16.0F && 
/* 129 */       yMarioD > -this.height && yMarioD < this.world.mario.height) {
/* 130 */       if (this.world.mario.ya > 0.0F && yMarioD <= 0.0F && (!this.world.mario.onGround || !this.world.mario.wasOnGround)) {
/* 131 */         this.world.mario.stomp(this);
/* 132 */         if (this.facing != 0) {
/* 133 */           this.xa = 0.0F;
/* 134 */           this.facing = 0;
/*     */         } else {
/* 136 */           this.facing = this.world.mario.facing;
/*     */         }
/*     */       
/* 139 */       } else if (this.facing != 0) {
/* 140 */         this.world.addEvent(EventType.HURT, this.type.getValue());
/* 141 */         this.world.mario.getHurt();
/*     */       } else {
/* 143 */         this.world.addEvent(EventType.KICK, this.type.getValue());
/* 144 */         this.world.mario.kick(this);
/* 145 */         this.facing = this.world.mario.facing;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean move(float xa, float ya) {
/* 153 */     while (xa > 8.0F) {
/* 154 */       if (!move(8.0F, 0.0F))
/* 155 */         return false; 
/* 156 */       xa -= 8.0F;
/*     */     } 
/* 158 */     while (xa < -8.0F) {
/* 159 */       if (!move(-8.0F, 0.0F))
/* 160 */         return false; 
/* 161 */       xa += 8.0F;
/*     */     } 
/* 163 */     while (ya > 8.0F) {
/* 164 */       if (!move(0.0F, 8.0F))
/* 165 */         return false; 
/* 166 */       ya -= 8.0F;
/*     */     } 
/* 168 */     while (ya < -8.0F) {
/* 169 */       if (!move(0.0F, -8.0F))
/* 170 */         return false; 
/* 171 */       ya += 8.0F;
/*     */     } 
/*     */     
/* 174 */     boolean collide = false;
/* 175 */     if (ya > 0.0F)
/* 176 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, 0.0F)) {
/* 177 */         collide = true;
/* 178 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya, xa, 0.0F)) {
/* 179 */         collide = true;
/* 180 */       } else if (isBlocking(this.x + xa - this.width, this.y + ya + 1.0F, xa, ya)) {
/* 181 */         collide = true;
/* 182 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya + 1.0F, xa, ya)) {
/* 183 */         collide = true;
/*     */       }  
/* 185 */     if (ya < 0.0F)
/* 186 */       if (isBlocking(this.x + xa, this.y + ya - this.height, xa, ya)) {
/* 187 */         collide = true;
/* 188 */       } else if (collide || isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya)) {
/* 189 */         collide = true;
/* 190 */       } else if (collide || isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya)) {
/* 191 */         collide = true;
/*     */       }  
/* 193 */     if (xa > 0.0F) {
/* 194 */       if (isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya))
/* 195 */         collide = true; 
/* 196 */       if (isBlocking(this.x + xa + this.width, this.y + ya - (this.height / 2), xa, ya))
/* 197 */         collide = true; 
/* 198 */       if (isBlocking(this.x + xa + this.width, this.y + ya, xa, ya)) {
/* 199 */         collide = true;
/*     */       }
/*     */     } 
/* 202 */     if (xa < 0.0F) {
/* 203 */       if (isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya))
/* 204 */         collide = true; 
/* 205 */       if (isBlocking(this.x + xa - this.width, this.y + ya - (this.height / 2), xa, ya))
/* 206 */         collide = true; 
/* 207 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, ya)) {
/* 208 */         collide = true;
/*     */       }
/*     */     } 
/*     */     
/* 212 */     if (collide) {
/* 213 */       if (xa < 0.0F) {
/* 214 */         this.x = ((int)((this.x - this.width) / 16.0F) * 16 + this.width);
/* 215 */         this.xa = 0.0F;
/*     */       } 
/* 217 */       if (xa > 0.0F) {
/* 218 */         this.x = ((int)((this.x + this.width) / 16.0F + 1.0F) * 16 - this.width - 1);
/* 219 */         this.xa = 0.0F;
/*     */       } 
/* 221 */       if (ya < 0.0F) {
/* 222 */         this.y = ((int)((this.y - this.height) / 16.0F) * 16 + this.height);
/* 223 */         this.ya = 0.0F;
/*     */       } 
/* 225 */       if (ya > 0.0F) {
/* 226 */         this.y = ((int)(this.y / 16.0F + 1.0F) * 16 - 1);
/* 227 */         this.onGround = true;
/*     */       } 
/* 229 */       return false;
/*     */     } 
/* 231 */     this.x += xa;
/* 232 */     this.y += ya;
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlocking(float _x, float _y, float xa, float ya) {
/* 238 */     int x = (int)(_x / 16.0F);
/* 239 */     int y = (int)(_y / 16.0F);
/* 240 */     if (x == (int)(this.x / 16.0F) && y == (int)(this.y / 16.0F)) {
/* 241 */       return false;
/*     */     }
/* 243 */     boolean blocking = this.world.level.isBlocking(x, y, xa, ya);
/*     */     
/* 245 */     if (blocking && ya == 0.0F && xa != 0.0F) {
/* 246 */       this.world.bump(x, y, true);
/*     */     }
/*     */     
/* 249 */     return blocking;
/*     */   }
/*     */   
/*     */   public void bumpCheck(int xTile, int yTile) {
/* 253 */     if (!this.alive)
/*     */       return; 
/* 255 */     if (this.x + this.width > (xTile * 16) && this.x - this.width < (xTile * 16 + 16) && yTile == (int)((this.y - 1.0F) / 16.0F)) {
/* 256 */       this.facing = -this.world.mario.facing;
/* 257 */       this.ya = -10.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean shellCollideCheck(Shell shell) {
/* 262 */     if (!this.alive) return false;
/*     */     
/* 264 */     float xD = shell.x - this.x;
/* 265 */     float yD = shell.y - this.y;
/*     */     
/* 267 */     if (xD > -16.0F && xD < 16.0F && 
/* 268 */       yD > -this.height && yD < shell.height) {
/* 269 */       this.world.addEvent(EventType.SHELL_KILL, this.type.getValue());
/* 270 */       if (this != shell) {
/* 271 */         this.world.removeSprite(shell);
/*     */       }
/* 273 */       this.world.removeSprite(this);
/* 274 */       return true;
/*     */     } 
/*     */     
/* 277 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\Shell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */