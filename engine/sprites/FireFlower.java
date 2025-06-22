/*    */ package engine.sprites;
/*    */ 
/*    */ import engine.core.MarioSprite;
/*    */ import engine.graphics.MarioImage;
/*    */ import engine.helper.Assets;
/*    */ import engine.helper.EventType;
/*    */ import engine.helper.SpriteType;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class FireFlower
/*    */   extends MarioSprite {
/*    */   private MarioImage graphics;
/*    */   private int life;
/*    */   
/*    */   public FireFlower(boolean visuals, float x, float y) {
/* 16 */     super(x, y, SpriteType.FIRE_FLOWER);
/* 17 */     this.width = 4;
/* 18 */     this.height = 12;
/* 19 */     this.facing = 1;
/* 20 */     this.life = 0;
/* 21 */     if (visuals) {
/* 22 */       this.graphics = new MarioImage(Assets.items, 1);
/* 23 */       this.graphics.originX = 8;
/* 24 */       this.graphics.originY = 15;
/* 25 */       this.graphics.width = 16;
/* 26 */       this.graphics.height = 16;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MarioSprite clone() {
/* 32 */     FireFlower f = new FireFlower(false, this.x, this.y);
/* 33 */     f.xa = this.xa;
/* 34 */     f.ya = this.ya;
/* 35 */     f.initialCode = this.initialCode;
/* 36 */     f.width = this.width;
/* 37 */     f.height = this.height;
/* 38 */     f.facing = this.facing;
/* 39 */     f.life = this.life;
/* 40 */     return f;
/*    */   }
/*    */ 
/*    */   
/*    */   public void collideCheck() {
/* 45 */     if (!this.alive) {
/*    */       return;
/*    */     }
/*    */     
/* 49 */     float xMarioD = this.world.mario.x - this.x;
/* 50 */     float yMarioD = this.world.mario.y - this.y;
/* 51 */     if (xMarioD > -16.0F && xMarioD < 16.0F && 
/* 52 */       yMarioD > -this.height && yMarioD < this.world.mario.height) {
/* 53 */       this.world.addEvent(EventType.COLLECT, this.type.getValue());
/* 54 */       this.world.mario.getFlower();
/* 55 */       this.world.removeSprite(this);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 62 */     if (!this.alive) {
/*    */       return;
/*    */     }
/*    */     
/* 66 */     super.update();
/* 67 */     this.life++;
/* 68 */     if (this.life < 9) {
/* 69 */       this.y--;
/*    */       return;
/*    */     } 
/* 72 */     if (this.graphics != null) {
/* 73 */       this.graphics.index = 1 + this.life / 2 % 2;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og) {
/* 79 */     super.render(og);
/* 80 */     this.graphics.render(og, (int)(this.x - this.world.cameraX), (int)(this.y - this.world.cameraY));
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\sprites\FireFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */