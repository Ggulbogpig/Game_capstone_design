/*    */ package levelGenerators.sampler;
/*    */ 
/*    */ import engine.core.MarioLevelGenerator;
/*    */ import engine.core.MarioLevelModel;
/*    */ import engine.core.MarioTimer;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class LevelGenerator
/*    */   implements MarioLevelGenerator {
/* 14 */   private int sampleWidth = 10;
/* 15 */   private String folderName = "levels/original/";
/*    */   
/*    */   private Random rnd;
/*    */   
/*    */   public LevelGenerator() {
/* 20 */     this("levels/original/", 10);
/*    */   }
/*    */   
/*    */   public LevelGenerator(String sampleFolder) {
/* 24 */     this(sampleFolder, 10);
/*    */   }
/*    */   
/*    */   public LevelGenerator(String sampleFolder, int sampleWidth) {
/* 28 */     this.sampleWidth = sampleWidth;
/* 29 */     this.folderName = sampleFolder;
/*    */   }
/*    */   
/*    */   private String getRandomLevel() throws IOException {
/* 33 */     File[] listOfFiles = (new File(this.folderName)).listFiles();
/* 34 */     List<String> lines = Files.readAllLines(listOfFiles[this.rnd.nextInt(listOfFiles.length)].toPath());
/* 35 */     String result = "";
/* 36 */     for (int i = 0; i < lines.size(); i++) {
/* 37 */       result = String.valueOf(result) + (String)lines.get(i) + "\n";
/*    */     }
/* 39 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
/* 44 */     this.rnd = new Random();
/* 45 */     model.clearMap();
/* 46 */     for (int i = 0; i < model.getWidth() / this.sampleWidth; i++) {
/*    */       try {
/* 48 */         model.copyFromString(i * this.sampleWidth, 0, i * this.sampleWidth, 0, this.sampleWidth, model.getHeight(), getRandomLevel());
/* 49 */       } catch (IOException e) {
/* 50 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 53 */     return model.getMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGeneratorName() {
/* 58 */     return "SamplerLevelGenerator";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\levelGenerators\sampler\LevelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */