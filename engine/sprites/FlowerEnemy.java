/*    */ package engine.sprites;
/*    */ 
/*    */ import engine.core.MarioSprite;
/*    */ import engine.helper.SpriteType;
/*    */ 
/*    */ public class FlowerEnemy
/*    */   extends Enemy {
/*    */   private float yStart;
/*    */   
/*    */   public FlowerEnemy(boolean visuals, float x, float y) {
/* 11 */     super(visuals, x, y, 0, SpriteType.ENEMY_FLOWER);
/* 12 */     this.winged = false;
/* 13 */     this.noFireballDeath = false;
/* 14 */     this.width = 2;
/* 15 */     this.yStart = this.y;
/* 16 */     this.ya = -1.0F;
/* 17 */     this.y--;
/* 18 */     for (int i = 0; i < 4; i++) {
/* 19 */       update();
/*    */     }
/*    */     
/* 22 */     if (visuals) {
/* 23 */       this.graphics.originY = 24;
/* 24 */       this.tick = 0;
/*    */     } 
/*    */   }
/*    */   private int tick; private int waitTime;
/*    */   
/*    */   public MarioSprite clone() {
/* 30 */     FlowerEnemy sprite = new FlowerEnemy(false, this.x, this.y);
/* 31 */     sprite.xa = this.xa;
/* 32 */     sprite.ya = this.ya;
/* 33 */     sprite.initialCode = this.initialCode;
/* 34 */     sprite.width = this.width;
/* 35 */     sprite.height = this.height;
/* 36 */     sprite.onGround = this.onGround;
/* 37 */     sprite.winged = this.winged;
/* 38 */     sprite.avoidCliffs = this.avoidCliffs;
/* 39 */     sprite.noFireballDeath = this.noFireballDeath;
/* 40 */     sprite.yStart = this.yStart;
/* 41 */     sprite.waitTime = this.waitTime;
/* 42 */     return sprite;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 47 */     if (!this.alive) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     if (this.ya > 0.0F) {
/* 52 */       if (this.y >= this.yStart) {
/* 53 */         this.y = this.yStart;
/* 54 */         int xd = (int)Math.abs(this.world.mario.x - this.x);
/* 55 */         this.waitTime++;
/* 56 */         if (this.waitTime > 40 && xd > 24) {
/* 57 */           this.waitTime = 0;
/* 58 */           this.ya = -1.0F;
/*    */         } 
/*    */       } 
/* 61 */     } else if (this.ya < 0.0F && 
/* 62 */       this.yStart - this.y > 20.0F) {
/* 63 */       this.y = this.yStart - 20.0F;
/* 64 */       this.waitTime++;
/* 65 */       if (this.waitTime > 40) {
/* 66 */         this.waitTime = 0;
/* 67 */         this.ya = 1.0F;
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     this.y += this.ya;
/*    */     
/* 73 */     if (this.graphics != null) {
/* 74 */       this.tick++;
/* 75 */       this.graphics.index = this.type.getStartIndex() + (this.tick / 2 & 0x1) * 2 + (this.tick / 6 & 0x1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\FlowerEnemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */