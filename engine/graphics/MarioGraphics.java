/*    */ package engine.graphics;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MarioGraphics
/*    */ {
/*    */   public boolean visible = true;
/* 14 */   public float alpha = 1.0F; public int originY;
/* 15 */   public int originX = this.originY = 0;
/* 16 */   public boolean flipX = this.flipY = false; public boolean flipY;
/* 17 */   public int width = this.height = 32;
/*    */   public int height;
/*    */   
/*    */   public abstract void render(Graphics paramGraphics, int paramInt1, int paramInt2);
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\graphics\MarioGraphics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */