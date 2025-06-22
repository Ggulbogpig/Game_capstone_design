/*     */ package engine.sprites;
/*     */ 
/*     */ import engine.core.MarioSprite;
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.SpriteType;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ 
/*     */ public class Fireball
/*     */   extends MarioSprite
/*     */ {
/*     */   private static final float GROUND_INERTIA = 0.89F;
/*     */   private static final float AIR_INERTIA = 0.89F;
/*     */   private boolean onGround = false;
/*     */   private MarioImage graphics;
/*  17 */   private int anim = 0;
/*     */   
/*     */   public Fireball(boolean visuals, float x, float y, int facing) {
/*  20 */     super(x, y, SpriteType.FIREBALL);
/*  21 */     this.facing = facing;
/*  22 */     this.ya = 4.0F;
/*  23 */     this.width = 4;
/*  24 */     this.height = 8;
/*     */     
/*  26 */     if (visuals) {
/*  27 */       this.graphics = new MarioImage(Assets.particles, 24);
/*  28 */       this.graphics.originX = 8;
/*  29 */       this.graphics.originY = 8;
/*  30 */       this.graphics.width = 16;
/*  31 */       this.graphics.height = 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  37 */     Fireball f = new Fireball(false, this.x, this.y, this.facing);
/*  38 */     f.xa = this.xa;
/*  39 */     f.ya = this.ya;
/*  40 */     f.initialCode = this.initialCode;
/*  41 */     f.width = this.width;
/*  42 */     f.height = this.height;
/*  43 */     f.onGround = this.onGround;
/*  44 */     return f;
/*     */   }
/*     */   
/*     */   private boolean move(float xa, float ya) {
/*  48 */     while (xa > 8.0F) {
/*  49 */       if (!move(8.0F, 0.0F))
/*  50 */         return false; 
/*  51 */       xa -= 8.0F;
/*     */     } 
/*  53 */     while (xa < -8.0F) {
/*  54 */       if (!move(-8.0F, 0.0F))
/*  55 */         return false; 
/*  56 */       xa += 8.0F;
/*     */     } 
/*  58 */     while (ya > 8.0F) {
/*  59 */       if (!move(0.0F, 8.0F))
/*  60 */         return false; 
/*  61 */       ya -= 8.0F;
/*     */     } 
/*  63 */     while (ya < -8.0F) {
/*  64 */       if (!move(0.0F, -8.0F))
/*  65 */         return false; 
/*  66 */       ya += 8.0F;
/*     */     } 
/*     */     
/*  69 */     boolean collide = false;
/*  70 */     if (ya > 0.0F)
/*  71 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, 0.0F)) {
/*  72 */         collide = true;
/*  73 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya, xa, 0.0F)) {
/*  74 */         collide = true;
/*  75 */       } else if (isBlocking(this.x + xa - this.width, this.y + ya + 1.0F, xa, ya)) {
/*  76 */         collide = true;
/*  77 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya + 1.0F, xa, ya)) {
/*  78 */         collide = true;
/*     */       }  
/*  80 */     if (ya < 0.0F)
/*  81 */       if (isBlocking(this.x + xa, this.y + ya - this.height, xa, ya)) {
/*  82 */         collide = true;
/*  83 */       } else if (collide || isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya)) {
/*  84 */         collide = true;
/*  85 */       } else if (collide || isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya)) {
/*  86 */         collide = true;
/*     */       }  
/*  88 */     if (xa > 0.0F) {
/*  89 */       if (isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya))
/*  90 */         collide = true; 
/*  91 */       if (isBlocking(this.x + xa + this.width, this.y + ya - (this.height / 2), xa, ya))
/*  92 */         collide = true; 
/*  93 */       if (isBlocking(this.x + xa + this.width, this.y + ya, xa, ya))
/*  94 */         collide = true; 
/*     */     } 
/*  96 */     if (xa < 0.0F) {
/*  97 */       if (isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya))
/*  98 */         collide = true; 
/*  99 */       if (isBlocking(this.x + xa - this.width, this.y + ya - (this.height / 2), xa, ya))
/* 100 */         collide = true; 
/* 101 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, ya)) {
/* 102 */         collide = true;
/*     */       }
/*     */     } 
/* 105 */     if (collide) {
/* 106 */       if (xa < 0.0F) {
/* 107 */         this.x = ((int)((this.x - this.width) / 16.0F) * 16 + this.width);
/* 108 */         this.xa = 0.0F;
/*     */       } 
/* 110 */       if (xa > 0.0F) {
/* 111 */         this.x = ((int)((this.x + this.width) / 16.0F + 1.0F) * 16 - this.width - 1);
/* 112 */         this.xa = 0.0F;
/*     */       } 
/* 114 */       if (ya < 0.0F) {
/* 115 */         this.y = ((int)((this.y - this.height) / 16.0F) * 16 + this.height);
/* 116 */         this.ya = 0.0F;
/*     */       } 
/* 118 */       if (ya > 0.0F) {
/* 119 */         this.y = ((int)(this.y / 16.0F + 1.0F) * 16 - 1);
/* 120 */         this.onGround = true;
/*     */       } 
/* 122 */       return false;
/*     */     } 
/* 124 */     this.x += xa;
/* 125 */     this.y += ya;
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlocking(float _x, float _y, float xa, float ya) {
/* 131 */     int x = (int)(_x / 16.0F);
/* 132 */     int y = (int)(_y / 16.0F);
/* 133 */     if (x == (int)(this.x / 16.0F) && y == (int)(this.y / 16.0F)) {
/* 134 */       return false;
/*     */     }
/* 136 */     boolean blocking = this.world.level.isBlocking(x, y, xa, ya);
/*     */     
/* 138 */     return blocking;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 143 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 147 */     if (this.facing != 0) {
/* 148 */       this.anim++;
/*     */     }
/* 150 */     float sideWaysSpeed = 8.0F;
/* 151 */     if (this.xa > 2.0F) {
/* 152 */       this.facing = 1;
/*     */     }
/* 154 */     if (this.xa < -2.0F) {
/* 155 */       this.facing = -1;
/*     */     }
/* 157 */     this.xa = this.facing * sideWaysSpeed;
/*     */     
/* 159 */     this.world.checkFireballCollide(this);
/*     */     
/* 161 */     if (!move(this.xa, 0.0F)) {
/* 162 */       this.world.removeSprite(this);
/*     */       
/*     */       return;
/*     */     } 
/* 166 */     this.onGround = false;
/* 167 */     move(0.0F, this.ya);
/* 168 */     if (this.onGround) {
/* 169 */       this.ya = -10.0F;
/*     */     }
/* 171 */     this.ya *= 0.95F;
/* 172 */     if (this.onGround) {
/* 173 */       this.xa *= 0.89F;
/*     */     } else {
/* 175 */       this.xa *= 0.89F;
/*     */     } 
/*     */     
/* 178 */     if (!this.onGround) {
/* 179 */       this.ya = (float)(this.ya + 1.5D);
/*     */     }
/*     */     
/* 182 */     if (this.graphics != null) {
/* 183 */       this.graphics.flipX = (this.facing == -1);
/* 184 */       this.graphics.index = 24 + this.anim % 4;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/* 190 */     super.render(og);
/* 191 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\Fireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */