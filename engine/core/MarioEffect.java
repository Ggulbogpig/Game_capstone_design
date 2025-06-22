/*    */ package engine.core;
/*    */ 
/*    */ import engine.graphics.MarioImage;
/*    */ import engine.helper.Assets;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public abstract class MarioEffect {
/*    */   public float x;
/*    */   public float y;
/*    */   public float xv;
/*    */   public float yv;
/*    */   
/*    */   public MarioEffect(float x, float y, float xv, float yv, float xa, float ya, int startIndex, int life) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.xv = xv;
/* 17 */     this.yv = yv;
/* 18 */     this.xa = xa;
/* 19 */     this.ya = ya;
/* 20 */     this.life = life;
/*    */     
/* 22 */     this.graphics = new MarioImage(Assets.particles, startIndex);
/* 23 */     this.graphics.width = 16;
/* 24 */     this.graphics.height = 16;
/* 25 */     this.graphics.originX = 8;
/* 26 */     this.graphics.originY = 8;
/* 27 */     this.startingIndex = startIndex;
/*    */   }
/*    */   public float xa; public float ya; public int life; public int startingIndex; protected MarioImage graphics;
/*    */   public void render(Graphics og, float cameraX, float cameraY) {
/* 31 */     if (this.life <= 0) {
/*    */       return;
/*    */     }
/* 34 */     this.life--;
/* 35 */     this.x += this.xv;
/* 36 */     this.y += this.yv;
/* 37 */     this.xv += this.xa;
/* 38 */     this.yv += this.ya;
/*    */     
/* 40 */     this.graphics.render(og, (int)(this.x - cameraX), (int)(this.y - cameraY));
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */