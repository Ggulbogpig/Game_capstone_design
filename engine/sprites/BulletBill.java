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
/*     */ public class BulletBill extends MarioSprite {
/*     */   private MarioImage graphics;
/*     */   
/*     */   public BulletBill(boolean visuals, float x, float y, int dir) {
/*  16 */     super(x, y, SpriteType.BULLET_BILL);
/*  17 */     this.width = 4;
/*  18 */     this.height = 12;
/*  19 */     this.ya = -5.0F;
/*  20 */     this.facing = dir;
/*     */     
/*  22 */     if (visuals) {
/*  23 */       this.graphics = new MarioImage(Assets.enemies, 40);
/*  24 */       this.graphics.originX = 8;
/*  25 */       this.graphics.originY = 31;
/*  26 */       this.graphics.width = 16;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MarioSprite clone() {
/*  32 */     BulletBill sprite = new BulletBill(false, this.x, this.y, this.facing);
/*  33 */     sprite.xa = this.xa;
/*  34 */     sprite.ya = this.ya;
/*  35 */     sprite.width = this.width;
/*  36 */     sprite.height = this.height;
/*  37 */     return sprite;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  42 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/*  46 */     super.update();
/*  47 */     float sideWaysSpeed = 4.0F;
/*  48 */     this.xa = this.facing * sideWaysSpeed;
/*  49 */     move(this.xa, 0.0F);
/*  50 */     if (this.graphics != null) {
/*  51 */       this.graphics.flipX = (this.facing == -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Graphics og) {
/*  57 */     super.render(og);
/*  58 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*     */   }
/*     */   
/*     */   public void collideCheck() {
/*  62 */     if (!this.alive) {
/*     */       return;
/*     */     }
/*     */     
/*  66 */     float xMarioD = this.world.mario.x - this.x;
/*  67 */     float yMarioD = this.world.mario.y - this.y;
/*  68 */     if (xMarioD > -16.0F && xMarioD < 16.0F && 
/*  69 */       yMarioD > -this.height && yMarioD < this.world.mario.height) {
/*  70 */       if (this.world.mario.ya > 0.0F && yMarioD <= 0.0F && (!this.world.mario.onGround || !this.world.mario.wasOnGround)) {
/*  71 */         this.world.mario.stomp(this);
/*  72 */         if (this.graphics != null) {
/*  73 */           this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y - 7.0F, this.graphics.flipX, 43, 0.0F));
/*     */         }
/*  75 */         this.world.removeSprite(this);
/*     */       } else {
/*  77 */         this.world.addEvent(EventType.HURT, this.type.getValue());
/*  78 */         this.world.mario.getHurt();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean move(float xa, float ya) {
/*  85 */     this.x += xa;
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   public boolean fireballCollideCheck(Fireball fireball) {
/*  90 */     if (!this.alive) {
/*  91 */       return false;
/*     */     }
/*     */     
/*  94 */     float xD = fireball.x - this.x;
/*  95 */     float yD = fireball.y - this.y;
/*     */     
/*  97 */     if (xD > -16.0F && xD < 16.0F) {
/*  98 */       return (yD > -this.height && yD < fireball.height);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public boolean shellCollideCheck(Shell shell) {
/* 104 */     if (!this.alive) {
/* 105 */       return false;
/*     */     }
/*     */     
/* 108 */     float xD = shell.x - this.x;
/* 109 */     float yD = shell.y - this.y;
/*     */     
/* 111 */     if (xD > -16.0F && xD < 16.0F && 
/* 112 */       yD > -this.height && yD < shell.height) {
/* 113 */       if (this.graphics != null) {
/* 114 */         this.world.addEffect((MarioEffect)new DeathEffect(this.x, this.y - 7.0F, this.graphics.flipX, 43, -1.0F));
/*     */       }
/* 116 */       this.world.addEvent(EventType.SHELL_KILL, this.type.getValue());
/* 117 */       this.world.removeSprite(this);
/* 118 */       return true;
/*     */     } 
/*     */     
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\BulletBill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */