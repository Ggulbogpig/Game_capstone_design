/*    */ package engine.effects;
/*    */ 
/*    */ import engine.core.MarioEffect;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class DustEffect
/*    */   extends MarioEffect {
/*    */   public DustEffect(float x, float y) {
/*  9 */     super(x, y, (float)(Math.random() * 2.0D - 1.0D), (float)Math.random() * -1.0F, 0.0F, 0.0F, 8 + (int)(Math.random() * 2.0D), 10 + (int)(Math.random() * 5.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, float cameraX, float cameraY) {
/* 14 */     if (this.life > 10) {
/* 15 */       this.graphics.index = 7;
/*    */     } else {
/* 17 */       this.graphics.index = this.startingIndex + (10 - this.life) * 4 / 10;
/*    */     } 
/* 19 */     super.render(og, cameraX, cameraY);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\effects\DustEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */