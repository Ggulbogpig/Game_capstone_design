/*    */ package engine.effects;
/*    */ 
/*    */ import engine.core.MarioEffect;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class CoinEffect
/*    */   extends MarioEffect {
/*    */   public CoinEffect(float x, float y) {
/*  9 */     super(x, y, 0.0F, -8.0F, 0.0F, 1.0F, 0, 16);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, float cameraX, float cameraY) {
/* 14 */     this.graphics.index = this.startingIndex + this.life & 0x3;
/* 15 */     super.render(og, cameraX, cameraY);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\effects\CoinEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */