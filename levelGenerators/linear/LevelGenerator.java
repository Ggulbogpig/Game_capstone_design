/*     */ package levelGenerators.linear;
/*     */ 
/*     */ import engine.core.MarioLevelGenerator;
/*     */ import engine.core.MarioLevelModel;
/*     */ import engine.core.MarioTimer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class LevelGenerator
/*     */   implements MarioLevelGenerator {
/*  11 */   private final int GROUND_PADDING = 5;
/*  12 */   private final int GROUND_LENGTH = 8;
/*  13 */   private final int GAP_LENGTH = 6;
/*  14 */   private final float GAP_PROB = 0.1F;
/*  15 */   private final float PIPE_PROB = 0.75F;
/*  16 */   private final int GROUND_PIPE_LENGTH = 10;
/*  17 */   private final int PIPE_HEIGHT = 6;
/*  18 */   private final float COLLECTIBLE_PROB = 0.75F;
/*  19 */   private final int GROUND_COLLECTIBLE_LENGTH = 6;
/*  20 */   private final int GROUND_ENEMY_LENGTH = 2;
/*     */   
/*     */   private Random rnd;
/*     */   
/*     */   private void placePipe(MarioLevelModel model, int x, int y, int height) {
/*  25 */     char pipeType = 't';
/*  26 */     if (this.rnd.nextDouble() < 0.2D) {
/*  27 */       pipeType = 'T';
/*     */     }
/*  29 */     model.setRectangle(x, y - height + 1, 2, height, pipeType);
/*     */   }
/*     */   
/*     */   private void placeInterestingArrangement(MarioLevelModel model, int x, int y, int width) {
/*  33 */     for (int i = 0; i < width / 2; i++) {
/*  34 */       char type = MarioLevelModel.getBumpableTiles()[this.rnd.nextInt((MarioLevelModel.getBumpableTiles()).length)];
/*  35 */       model.setBlock(x + i, y, type);
/*  36 */       model.setBlock(x + width - 1 - i, y, type);
/*     */     } 
/*     */     
/*  39 */     if (width % 2 == 1 && this.rnd.nextDouble() < 0.25D) {
/*  40 */       char type = MarioLevelModel.getBumpableTiles()[this.rnd.nextInt((MarioLevelModel.getBumpableTiles()).length)];
/*  41 */       model.setBlock(x + width / 2, y, type);
/*     */     } 
/*     */     
/*  44 */     if (y > 4 && this.rnd.nextDouble() < 0.25D) {
/*  45 */       placeInterestingArrangement(model, x + width / 4, y - 3 - this.rnd.nextInt(3), width / 2);
/*     */     }
/*     */   }
/*     */   
/*     */   private void placeEnemy(MarioLevelModel model, int x1, int x2, int y) {
/*  50 */     boolean winged = (this.rnd.nextDouble() < 0.1D);
/*  51 */     char enemyType = MarioLevelModel.getEnemyCharacters(false)[this.rnd.nextInt((MarioLevelModel.getEnemyCharacters(false)).length)];
/*  52 */     enemyType = MarioLevelModel.getWingedEnemyVersion(enemyType, winged);
/*  53 */     int xStart = x1 + this.rnd.nextInt(x2 - x1);
/*  54 */     int length = 1 + this.rnd.nextInt(3);
/*  55 */     if (length > x2 - x1 - 1) {
/*  56 */       length = x2 - x1 - 1;
/*     */     }
/*  58 */     for (int i = 0; i < length; i++) {
/*  59 */       if (model.getBlock(xStart + i, y) == '-') {
/*  60 */         model.setBlock(xStart + i, y, enemyType);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
/*  66 */     this.rnd = new Random();
/*  67 */     model.clearMap();
/*     */     
/*  69 */     ArrayList<Integer> groundArea = new ArrayList<>();
/*  70 */     groundArea.add(Integer.valueOf(0));
/*  71 */     int groundLength = 4 + this.rnd.nextInt(4);
/*  72 */     int gapLength = 0;
/*     */ 
/*     */     
/*  75 */     for (int x = 0; x < model.getWidth(); x++) {
/*  76 */       if (groundLength > 0 || gapLength == 0 || x < 5 || x > model.getWidth() - 1 - 5) {
/*  77 */         model.setBlock(x, model.getHeight() - 1, 'X');
/*  78 */         model.setBlock(x, model.getHeight() - 2, 'X');
/*  79 */         groundLength--;
/*  80 */         if (groundLength <= 0 && this.rnd.nextDouble() < 0.10000000149011612D) {
/*  81 */           gapLength = 3 + this.rnd.nextInt(3);
/*     */         }
/*  83 */         if (groundArea.size() % 2 == 0) {
/*  84 */           groundArea.add(Integer.valueOf(x));
/*     */         }
/*     */       } else {
/*  87 */         gapLength--;
/*  88 */         if (gapLength <= 0) {
/*  89 */           groundLength = 4 + this.rnd.nextInt(4);
/*     */         }
/*  91 */         if (groundArea.size() % 2 == 1) {
/*  92 */           groundArea.add(Integer.valueOf(x));
/*     */         }
/*     */       } 
/*     */     } 
/*  96 */     groundArea.add(Integer.valueOf(model.getWidth() - 1));
/*     */ 
/*     */     
/*  99 */     ArrayList<Integer> newAreas = new ArrayList<>(); int i;
/* 100 */     for (i = 0; i < groundArea.size() / 2; i++) {
/* 101 */       groundLength = ((Integer)groundArea.get(2 * i + 1)).intValue() - ((Integer)groundArea.get(2 * i)).intValue();
/* 102 */       if (groundLength > 10 && this.rnd.nextDouble() < 0.75D) {
/* 103 */         int j = ((Integer)groundArea.get(2 * i)).intValue() + this.rnd.nextInt(groundLength / 4) + 3;
/* 104 */         placePipe(model, j, model.getHeight() - 3, this.rnd.nextInt(4) + 2);
/* 105 */         newAreas.add(groundArea.get(2 * i));
/* 106 */         newAreas.add(Integer.valueOf(j - 1));
/* 107 */         newAreas.add(Integer.valueOf(j + 2));
/* 108 */         newAreas.add(groundArea.get(2 * i + 1));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 113 */     groundArea.clear();
/* 114 */     for (i = 0; i < newAreas.size() / 2; i++) {
/* 115 */       groundLength = ((Integer)newAreas.get(2 * i + 1)).intValue() - ((Integer)newAreas.get(2 * i)).intValue();
/* 116 */       groundArea.add(newAreas.get(2 * i));
/* 117 */       groundArea.add(Integer.valueOf(model.getHeight() - 3));
/* 118 */       groundArea.add(newAreas.get(2 * i + 1));
/* 119 */       groundArea.add(Integer.valueOf(model.getHeight() - 3));
/* 120 */       if (groundLength > 6 && this.rnd.nextDouble() < 0.75D) {
/* 121 */         int j = ((Integer)newAreas.get(2 * i)).intValue() + this.rnd.nextInt(groundLength / 3) + 1;
/* 122 */         int y = model.getHeight() - 5 - this.rnd.nextInt(3);
/* 123 */         int length = 1 + this.rnd.nextInt(groundLength / 3);
/* 124 */         placeInterestingArrangement(model, j, y, length);
/* 125 */         groundArea.add(Integer.valueOf(j + 1));
/* 126 */         groundArea.add(Integer.valueOf(y - 1));
/* 127 */         groundArea.add(Integer.valueOf(j + length - 1));
/* 128 */         groundArea.add(Integer.valueOf(y - 1));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 133 */     for (i = 1; i < groundArea.size() / 4; i++) {
/* 134 */       groundLength = ((Integer)groundArea.get(4 * i + 2)).intValue() - ((Integer)groundArea.get(4 * i)).intValue();
/* 135 */       if (groundLength > 2) {
/* 136 */         placeEnemy(model, ((Integer)groundArea.get(4 * i)).intValue(), ((Integer)groundArea.get(4 * i + 2)).intValue(), ((Integer)groundArea.get(4 * i + 1)).intValue());
/*     */       }
/*     */     } 
/*     */     
/* 140 */     model.setBlock(1, model.getHeight() - 3, 'M');
/* 141 */     model.setBlock(model.getWidth() - 2, model.getHeight() - 3, '#');
/* 142 */     model.setBlock(model.getWidth() - 2, model.getHeight() - 4, 'F');
/* 143 */     return model.getMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGeneratorName() {
/* 148 */     return "LinearLevelGenerator";
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\levelGenerators\linear\LevelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */