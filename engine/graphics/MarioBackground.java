/*    */ package engine.graphics;
/*    */ 
/*    */ import engine.helper.Assets;
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.Image;
/*    */ 
/*    */ 
/*    */ public class MarioBackground
/*    */   extends MarioGraphics
/*    */ {
/*    */   private Image image;
/*    */   private Graphics2D g;
/*    */   private int screenWidth;
/*    */   
/*    */   public MarioBackground(GraphicsConfiguration graphicsConfiguration, int screenWidth, int[][] indeces) {
/* 20 */     this.width = (indeces[0]).length * 16;
/* 21 */     this.height = indeces.length * 16;
/* 22 */     this.screenWidth = screenWidth;
/*    */     
/* 24 */     this.image = graphicsConfiguration.createCompatibleImage(this.width, this.height, 2);
/* 25 */     this.g = (Graphics2D)this.image.getGraphics();
/* 26 */     this.g.setComposite(AlphaComposite.Src);
/*    */     
/* 28 */     updateArea(indeces);
/*    */   }
/*    */   
/*    */   private void updateArea(int[][] indeces) {
/* 32 */     this.g.setBackground(new Color(0, 0, 0, 0));
/* 33 */     this.g.clearRect(0, 0, this.width, this.height);
/* 34 */     for (int y = 0; y < indeces.length; y++)  {
/* 35 */       for (int x = 0; x < indeces[y].length; x++) {
/* 36 */         int xTile = indeces[y][x] % 8;
/* 37 */         int yTile = indeces[y][x] / 8;
/* 38 */         this.g.drawImage(Assets.level[xTile][yTile], x * 16, y * 16, 16, 16, null);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, int x, int y) {
/* 45 */     int xOff = x % this.width;
/* 46 */     for (int i = -1; i < this.screenWidth / this.width + 1; i++)
/* 47 */       og.drawImage(this.image, -xOff + i * this.width, 0, null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\graphics\MarioBackground.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */