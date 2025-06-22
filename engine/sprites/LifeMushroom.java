/*     */ package engine.sprites;
/*     */ 
/*     */ import engine.core.MarioSprite;
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.SpriteType;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class LifeMushroom
/*     */   extends MarioSprite
/*     */ {
/*     */   private boolean onGround = false;
/*     */   private int life;
/*     */   private MarioImage graphics;
/*     */   private static final float GROUND_INERTIA = 0.89F;
/*     */   private static final float AIR_INERTIA = 0.89F;
/*     */   
/*     */   public LifeMushroom(boolean visuals, float x, float y) {
/*  20 */     super(x, y, SpriteType.LIFE_MUSHROOM);
/*  21 */     this.width = 4;
/*  22 */     this.height = 12;
/*  23 */     this.facing = 1;
/*  24 */     this.life = 0;
/*     */     
/*  26 */     if (visuals) {
/*  27 */       this.graphics = new MarioImage(Assets.items, 3);
/*  28 */       this.graphics.width = 16;
/*  29 */       this.graphics.height = 16;
/*  30 */       this.graphics.originX = 8;
/*  31 */       this.graphics.originY = 15;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  37 */     LifeMushroom m = new LifeMushroom(false, this.x, this.y);
/*  38 */     m.xa = this.xa;
/*  39 */     m.ya = this.ya;
/*  40 */     m.initialCode = this.initialCode;
/*  41 */     m.width = this.width;
/*  42 */     m.height = this.height;
/*  43 */     m.facing = this.facing;
/*  44 */     m.life = this.life;
/*  45 */     m.onGround = this.onGround;
/*  46 */     return m;
/*     */   }
/*     */   
/*     */   public void collideCheck() {
/*  50 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/*  54 */     float xMarioD = this.world.mario.x - this.x;
/*  55 */     float yMarioD = this.world.mario.y - this.y;
/*  56 */     if (xMarioD > -16.0F && xMarioD < 16.0F && 
/*  57 */       yMarioD > -this.height && yMarioD < this.world.mario.height) {
/*  58 */       this.world.addEvent(EventType.COLLECT, this.type.getValue());
/*  59 */       this.world.mario.collect1Up();
/*  60 */       this.world.removeSprite(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlocking(float _x, float _y, float xa, float ya) {
/*  66 */     int x = (int)(_x / 16.0F);
/*  67 */     int y = (int)(_y / 16.0F);
/*  68 */     if (x == (int)(this.x / 16.0F) && y == (int)(this.y / 16.0F)) {
/*  69 */       return false;
/*     */     }
/*  71 */     boolean blocking = this.world.level.isBlocking(x, y, xa, ya);
/*  72 */     return blocking;
/*     */   }
/*     */   
/*     */   public void bumpCheck(int xTile, int yTile) {
/*  76 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/*  80 */     if (this.x + this.width > (xTile * 16) && this.x - this.width < (xTile * 16 + 16) && yTile == (int)((this.y - 1.0F) / 16.0F)) {
/*  81 */       this.facing = -this.world.mario.facing;
/*  82 */       this.ya = -10.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean move(float xa, float ya) {
/*  87 */     while (xa > 8.0F) {
/*  88 */       if (!move(8.0F, 0.0F))
/*  89 */         return false; 
/*  90 */       xa -= 8.0F;
/*     */     } 
/*  92 */     while (xa < -8.0F) {
/*  93 */       if (!move(-8.0F, 0.0F))
/*  94 */         return false; 
/*  95 */       xa += 8.0F;
/*     */     } 
/*  97 */     while (ya > 8.0F) {
/*  98 */       if (!move(0.0F, 8.0F))
/*  99 */         return false; 
/* 100 */       ya -= 8.0F;
/*     */     } 
/* 102 */     while (ya < -8.0F) {
/* 103 */       if (!move(0.0F, -8.0F))
/* 104 */         return false; 
/* 105 */       ya += 8.0F;
/*     */     } 
/*     */     
/* 108 */     boolean collide = false;
/* 109 */     if (ya > 0.0F)
/* 110 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, 0.0F)) {
/* 111 */         collide = true;
/* 112 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya, xa, 0.0F)) {
/* 113 */         collide = true;
/* 114 */       } else if (isBlocking(this.x + xa - this.width, this.y + ya + 1.0F, xa, ya)) {
/* 115 */         collide = true;
/* 116 */       } else if (isBlocking(this.x + xa + this.width, this.y + ya + 1.0F, xa, ya)) {
/* 117 */         collide = true;
/*     */       }  
/* 119 */     if (ya < 0.0F)
/* 120 */       if (isBlocking(this.x + xa, this.y + ya - this.height, xa, ya)) {
/* 121 */         collide = true;
/* 122 */       } else if (collide || isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya)) {
/* 123 */         collide = true;
/* 124 */       } else if (collide || isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya)) {
/* 125 */         collide = true;
/*     */       }  
/* 127 */     if (xa > 0.0F) {
/* 128 */       if (isBlocking(this.x + xa + this.width, this.y + ya - this.height, xa, ya))
/* 129 */         collide = true; 
/* 130 */       if (isBlocking(this.x + xa + this.width, this.y + ya - (this.height / 2), xa, ya))
/* 131 */         collide = true; 
/* 132 */       if (isBlocking(this.x + xa + this.width, this.y + ya, xa, ya))
/* 133 */         collide = true; 
/*     */     } 
/* 135 */     if (xa < 0.0F) {
/* 136 */       if (isBlocking(this.x + xa - this.width, this.y + ya - this.height, xa, ya))
/* 137 */         collide = true; 
/* 138 */       if (isBlocking(this.x + xa - this.width, this.y + ya - (this.height / 2), xa, ya))
/* 139 */         collide = true; 
/* 140 */       if (isBlocking(this.x + xa - this.width, this.y + ya, xa, ya)) {
/* 141 */         collide = true;
/*     */       }
/*     */     } 
/* 144 */     if (collide) {
/* 145 */       if (xa < 0.0F) {
/* 146 */         this.x = ((int)((this.x - this.width) / 16.0F) * 16 + this.width);
/* 147 */         this.xa = 0.0F;
/*     */       } 
/* 149 */       if (xa > 0.0F) {
/* 150 */         this.x = ((int)((this.x + this.width) / 16.0F + 1.0F) * 16 - this.width - 1);
/* 151 */         this.xa = 0.0F;
/*     */       } 
/* 153 */       if (ya < 0.0F) {
/* 154 */         this.y = ((int)((this.y - this.height) / 16.0F) * 16 + this.height);
/* 155 */         this.ya = 0.0F;
/*     */       } 
/* 157 */       if (ya > 0.0F) {
/* 158 */         this.y = ((int)(this.y / 16.0F + 1.0F) * 16 - 1);
/* 159 */         this.onGround = true;
/*     */       } 
/* 161 */       return false;
/*     */     } 
/* 163 */     this.x += xa;
/* 164 */     this.y += ya;
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 171 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/* 175 */     if (this.life < 9) {
/* 176 */       this.y--;
/* 177 */       this.life++;
/*     */       return;
/*     */     } 
/* 180 */     float sideWaysSpeed = 1.75F;
/* 181 */     if (this.xa > 2.0F) {
/* 182 */       this.facing = 1;
/*     */     }
/* 184 */     if (this.xa < -2.0F) {
/* 185 */       this.facing = -1;
/*     */     }
/*     */     
/* 188 */     this.xa = this.facing * sideWaysSpeed;
/*     */     
/* 190 */     if (!move(this.xa, 0.0F))
/* 191 */       this.facing = -this.facing; 
/* 192 */     this.onGround = false;
/* 193 */     move(0.0F, this.ya);
/*     */     
/* 195 */     this.ya *= 0.85F;
/* 196 */     if (this.onGround) {
/* 197 */       this.xa *= 0.89F;
/*     */     } else {
/* 199 */       this.xa *= 0.89F;
/*     */     } 
/*     */     
/* 202 */     if (!this.onGround) {
/* 203 */       this.ya += 2.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/* 209 */     super.render(og);
/*     */     
/* 211 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\LifeMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */