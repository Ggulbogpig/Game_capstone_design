/*    */ package engine.graphics;
/*    */ 
/*    */ import engine.helper.TileFeature;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MarioTilemap
/*    */   extends MarioGraphics
/*    */ {
/*    */   public Image[][] sheet;
/*    */   public int[][] currentIndeces;
/*    */   public int[][] indexShift;
/*    */   public float[][] moveShift;
/*    */   public int animationIndex;
/*    */   
/*    */   public MarioTilemap(Image[][] sheet, int[][] currentIndeces) {
/* 18 */     this.sheet = sheet;
/* 19 */     this.currentIndeces = currentIndeces;
/* 20 */     this.indexShift = new int[currentIndeces.length][(currentIndeces[0]).length];
/* 21 */     this.moveShift = new float[currentIndeces.length][(currentIndeces[0]).length];
/* 22 */     this.animationIndex = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Graphics og, int x, int y) {
/* 27 */     this.animationIndex = (this.animationIndex + 1) % 5;
/*    */     
/* 29 */     int xMin = x / 16 - 1;
/* 30 */     int yMin = y / 16 - 1;
/* 31 */     int xMax = (x + 256) / 16 + 1;
/* 32 */     int yMax = (y + 256) / 16 + 1;
/*    */     
/* 34 */     for (int xTile = xMin; xTile <= xMax; xTile++) {
/* 35 */       for (int yTile = yMin; yTile <= yMax; yTile++) {
/* 36 */         if (xTile >= 0 && yTile >= 0 && xTile < this.currentIndeces.length && yTile < (this.currentIndeces[0]).length) {
/*    */ 
/*    */           
/* 39 */           if (this.moveShift[xTile][yTile] > 0.0F) {
/* 40 */             this.moveShift[xTile][yTile] = this.moveShift[xTile][yTile] - 1.0F;
/* 41 */             if (this.moveShift[xTile][yTile] < 0.0F) {
/* 42 */               this.moveShift[xTile][yTile] = 0.0F;
/*    */             }
/*    */           } 
/* 45 */           ArrayList<TileFeature> features = TileFeature.getTileType(this.currentIndeces[xTile][yTile]);
/* 46 */           if (features.contains(TileFeature.ANIMATED)) {
/* 47 */             if (this.animationIndex == 0) {
/* 48 */               this.indexShift[xTile][yTile] = (this.indexShift[xTile][yTile] + 1) % 3;
/*    */             }
/*    */           } else {
/* 51 */             this.indexShift[xTile][yTile] = 0;
/*    */           } 
/* 53 */           int index = this.currentIndeces[xTile][yTile] + this.indexShift[xTile][yTile];
/* 54 */           int move = (int)this.moveShift[xTile][yTile];
/* 55 */           Image img = this.sheet[index % 8][index / 8];
/* 56 */           og.drawImage(img, xTile * 16 - x, yTile * 16 - y - move, null);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\graphics\MarioTilemap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */