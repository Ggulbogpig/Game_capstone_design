/*    */ package engine.effects;
/*    */ 
/*    */ import engine.core.MarioEffect;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class FireballEffect
/*    */   extends MarioEffect {
/*    */   public FireballEffect(float x, float y) {
/*  9 */     super(x, y, 0.0F, 0.0F, 0.0F, 0.0F, 32, 8);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, float cameraX, float cameraY) {
/* 14 */     this.graphics.index = this.startingIndex + 8 - this.life;
/* 15 */     super.render(og, cameraX, cameraY);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\effects\FireballEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */