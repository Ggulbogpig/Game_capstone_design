/*    */ package engine.effects;
/*    */ 
/*    */ import engine.core.MarioEffect;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class BrickEffect
/*    */   extends MarioEffect
/*    */ {
/*    */   public BrickEffect(float x, float y, float xv, float yv) {
/* 10 */     super(x, y, xv, yv, 0.0F, 3.0F, 16, 10);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, float cameraX, float cameraY) {
/* 15 */     this.graphics.index = this.startingIndex + this.life % 4;
/* 16 */     this.ya *= 0.95F;
/* 17 */     super.render(og, cameraX, cameraY);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\effects\BrickEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */