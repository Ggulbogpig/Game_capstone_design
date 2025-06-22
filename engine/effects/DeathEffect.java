/*   */ package engine.effects;
/*   */ 
/*   */ import engine.core.MarioEffect;
/*   */ 
/*   */ public class DeathEffect extends MarioEffect {
/*   */   public DeathEffect(float x, float y, boolean flipX, int startIndex, float yv) {
/* 7 */     super(x, y, 0.0F, yv, 0.0F, 1.0F, startIndex, 30);
/* 8 */     this.graphics.flipX = flipX;
/*   */   }
/*   */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\effects\DeathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */