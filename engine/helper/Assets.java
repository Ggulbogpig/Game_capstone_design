/*    */ package engine.helper;
/*    */ 
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.ImageObserver;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.imageio.ImageReader;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ 
/*    */ 
/*    */ public class Assets
/*    */ {
/*    */   public static Image[][] mario;
/*    */   public static Image[][] smallMario;
/*    */   public static Image[][] fireMario;
/*    */   public static Image[][] enemies;
/*    */   public static Image[][] items;
/*    */   public static Image[][] level;
/*    */   public static Image[][] particles;
/*    */   public static Image[][] font;
/*    */   public static Image[][] map;
/* 27 */   static final String curDir = System.getProperty("user.dir");
/* 28 */   static final String img = String.valueOf(curDir) + "/img/";
/*    */   
/*    */   public static void init(GraphicsConfiguration gc) {
/*    */     try {
/* 32 */       mario = cutImage(gc, "mariosheet.png", 32, 32);
/* 33 */       smallMario = cutImage(gc, "smallmariosheet.png", 16, 16);
/* 34 */       fireMario = cutImage(gc, "firemariosheet.png", 32, 32);
/* 35 */       enemies = cutImage(gc, "enemysheet.png", 16, 32);
/* 36 */       items = cutImage(gc, "itemsheet.png", 16, 16);
/* 37 */       level = cutImage(gc, "mapsheet.png", 16, 16);
/* 38 */       particles = cutImage(gc, "particlesheet.png", 16, 16);
/* 39 */       font = cutImage(gc, "font.gif", 8, 8);
/* 40 */     } catch (Exception e) {
/* 41 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static Image getImage(GraphicsConfiguration gc, String imageName) throws IOException {
/* 47 */     BufferedImage source = null;
/*    */     try {
/* 49 */       source = ImageIO.read(Assets.class.getResourceAsStream(imageName));
/* 50 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 53 */     if (source == null) {
/* 54 */       imageName = String.valueOf(img) + imageName;
/* 55 */       File file = new File(imageName);
/* 56 */       source = ImageIO.read(file);
/*    */     } 
/* 58 */     if (source == null) {
/* 59 */       File file = new File(imageName);
/* 60 */       ImageInputStream iis = ImageIO.createImageInputStream(file);
/* 61 */       String suffix = imageName.substring(imageName.length() - 3);
/* 62 */       ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
/* 63 */       reader.setInput(iis, true);
/* 64 */       source = reader.read(0);
/*    */     } 
/* 66 */     Image image = gc.createCompatibleImage(source.getWidth(), source.getHeight(), 2);
/* 67 */     Graphics2D g = (Graphics2D)image.getGraphics();
/* 68 */     g.setComposite(AlphaComposite.Src);
/* 69 */     g.drawImage(source, 0, 0, (ImageObserver)null);
/* 70 */     g.dispose();
/* 71 */     return image;
/*    */   }
/*    */   
/*    */   private static Image[][] cutImage(GraphicsConfiguration gc, String imageName, int xSize, int ySize) throws IOException {
/* 75 */     Image source = getImage(gc, imageName);
/* 76 */     Image[][] images = new Image[source.getWidth(null) / xSize][source.getHeight(null) / ySize];
/* 77 */     for (int x = 0; x < source.getWidth(null) / xSize; x++) {
/* 78 */       for (int y = 0; y < source.getHeight(null) / ySize; y++) {
/* 79 */         Image image = gc.createCompatibleImage(xSize, ySize, 2);
/* 80 */         Graphics2D g = (Graphics2D)image.getGraphics();
/* 81 */         g.setComposite(AlphaComposite.Src);
/* 82 */         g.drawImage(source, -x * xSize, -y * ySize, (ImageObserver)null);
/* 83 */         g.dispose();
/* 84 */         images[x][y] = image;
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     return images;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\Assets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */