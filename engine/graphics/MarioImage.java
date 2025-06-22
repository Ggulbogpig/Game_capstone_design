/*    */ package engine.graphics;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ 
/*    */ public class MarioImage
/*    */   extends MarioGraphics {
/*    */   public Image[][] sheet;
/*    */   public int index;
/*    */   
/*    */   public MarioImage(Image[][] sheet, int index) {
/* 12 */     this.sheet = sheet;
/* 13 */     this.index = index;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, int x, int y) {
/* 18 */     if (!this.visible)
/*    */       return; 
/* 20 */     int xPixel = x - this.originX;
/* 21 */     int yPixel = y - this.originY;
/* 22 */     Image image = this.sheet[this.index % this.sheet.length][this.index / this.sheet.length];
/*    */     
/* 24 */     og.drawImage(image, xPixel + (this.flipX ? this.width : 0), yPixel + (this.flipY ? this.height : 0), this.flipX ? -this.width : this.width, this.flipY ? -this.height : this.height, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\graphics\MarioImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */