/*    */ package levelGenerators.random;
/*    */ 
/*    */ import engine.core.MarioLevelGenerator;
/*    */ import engine.core.MarioLevelModel;
/*    */ import engine.core.MarioTimer;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class LevelGenerator
/*    */   implements MarioLevelGenerator {
/* 10 */   private final int GROUND_Y_LOCATION = 13;
/* 11 */   private final float GROUND_PROB = 0.4F;
/* 12 */   private final int OBSTACLES_LOCATION = 10;
/* 13 */   private final float OBSTACLES_PROB = 0.1F;
/* 14 */   private final int COLLECTIBLE_LOCATION = 3;
/* 15 */   private final float COLLECTIBLE_PROB = 0.05F;
/* 16 */   private final float ENMEY_PROB = 0.1F;
/* 17 */   private final int FLOOR_PADDING = 3;
/*    */ 
/*    */   
/*    */   public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
/* 21 */     Random random = new Random();
/* 22 */     model.clearMap();
/* 23 */     for (int x = 0; x < model.getWidth(); x++) {
/* 24 */       for (int y = 0; y < model.getHeight(); y++) {
/* 25 */         model.setBlock(x, y, '-');
/* 26 */         if (y > 13) {
/* 27 */           if (random.nextDouble() < 0.4000000059604645D) {
/* 28 */             model.setBlock(x, y, 'X');
/*    */           }
/* 30 */         } else if (y > 10) {
/* 31 */           if (random.nextDouble() < 0.10000000149011612D) {
/* 32 */             model.setBlock(x, y, '#');
/* 33 */           } else if (random.nextDouble() < 0.10000000149011612D) {
/* 34 */             model.setBlock(x, y, 
/* 35 */                 MarioLevelModel.getEnemyCharacters()[random.nextInt((MarioLevelModel.getEnemyCharacters()).length)]);
/*    */           } 
/* 37 */         } else if (y > 3 && 
/* 38 */           random.nextDouble() < 0.05000000074505806D) {
/* 39 */           model.setBlock(x, y, 
/* 40 */               MarioLevelModel.getCollectablesTiles()[random.nextInt((MarioLevelModel.getCollectablesTiles()).length)]);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     model.setRectangle(0, 14, 3, 2, 'X');
/* 46 */     model.setRectangle(model.getWidth() - 1 - 3, 14, 3, 2, 'X');
/* 47 */     model.setBlock(1, 13, 'M');
/* 48 */     model.setBlock(model.getWidth() - 1 - 1, 13, 'F');
/* 49 */     return model.getMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGeneratorName() {
/* 54 */     return "RandomLevelGenerator";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\levelGenerators\random\LevelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */