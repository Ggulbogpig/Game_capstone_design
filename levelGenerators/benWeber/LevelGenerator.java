/*     */ package levelGenerators.benWeber;
/*     */ 
/*     */ import engine.core.MarioLevelGenerator;
/*     */ import engine.core.MarioLevelModel;
/*     */ import engine.core.MarioTimer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class LevelGenerator
/*     */   implements MarioLevelGenerator
/*     */ {
/*     */   private int maxGaps;
/*     */   private int maxTurtles;
/*     */   private int maxCoinBlocks;
/*  15 */   private double CHANCE_BLOCK_POWER_UP = 0.1D;
/*  16 */   private double CHANCE_BLOCK_COIN = 0.3D;
/*  17 */   private double CHANCE_BLOCK_ENEMY = 0.2D;
/*  18 */   private double CHANCE_WINGED = 0.5D;
/*  19 */   private double CHANCE_COIN = 0.2D;
/*  20 */   private double COIN_HEIGHT = 5.0D;
/*  21 */   private double CHANCE_PLATFORM = 0.1D;
/*  22 */   private double CHANCE_END_PLATFORM = 0.1D;
/*  23 */   private int PLATFORM_HEIGHT = 4;
/*  24 */   private double CHANCE_ENEMY = 0.1D;
/*  25 */   private double CHANCE_PIPE = 0.1D;
/*  26 */   private int PIPE_MIN_HEIGHT = 2;
/*  27 */   private double PIPE_HEIGHT = 3.0D;
/*  28 */   private int minX = 5;
/*  29 */   private double CHANCE_HILL = 0.1D;
/*  30 */   private double CHANCE_END_HILL = 0.3D;
/*  31 */   private double CHANCE_HILL_ENEMY = 0.3D;
/*  32 */   private double HILL_HEIGHT = 4.0D;
/*  33 */   private int GAP_LENGTH = 5;
/*  34 */   private double CHANGE_GAP = 0.1D;
/*  35 */   private double CHANGE_HILL_CHANGE = 0.1D;
/*  36 */   private double GAP_OFFSET = -5.0D;
/*  37 */   private double GAP_RANGE = 10.0D;
/*  38 */   private int GROUND_MAX_HEIGHT = 5;
/*     */ 
/*     */   
/*     */   Random rand;
/*     */ 
/*     */   
/*  44 */   int gapCount = 0;
/*  45 */   int turtleCount = 0;
/*  46 */   int coinBlockCount = 0;
/*     */   
/*  48 */   int xExit = 0;
/*  49 */   int yExit = 0;
/*     */   
/*     */   public LevelGenerator() {
/*  52 */     this(10, 7, 10);
/*     */   }
/*     */   
/*     */   public LevelGenerator(int maxGaps, int maxTurtles, int maxCoinBlocks) {
/*  56 */     this.maxGaps = maxGaps;
/*  57 */     this.maxTurtles = maxTurtles;
/*  58 */     this.maxCoinBlocks = maxCoinBlocks;
/*     */   }
/*     */ 
/*     */   
/*     */   private void placeBlock(MarioLevelModel model, int x, int y) {
/*  63 */     if (this.rand.nextDouble() < this.CHANCE_BLOCK_POWER_UP) {
/*  64 */       model.setBlock(x, y, 'U');
/*  65 */     } else if (this.rand.nextDouble() < this.CHANCE_BLOCK_COIN && this.coinBlockCount < this.maxCoinBlocks) {
/*  66 */       model.setBlock(x, y, 'C');
/*  67 */       this.coinBlockCount++;
/*     */     } else {
/*  69 */       model.setBlock(x, y, 'S');
/*     */     } 
/*     */ 
/*     */     
/*  73 */     if (this.rand.nextDouble() < this.CHANCE_BLOCK_ENEMY) {
/*  74 */       char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt((MarioLevelModel.getEnemyCharacters(false)).length)];
/*     */       
/*  76 */       if (t == 'k' || t == 'r') {
/*  77 */         if (this.turtleCount < this.maxTurtles) {
/*  78 */           this.turtleCount++;
/*     */         } else {
/*  80 */           t = 'g';
/*     */         } 
/*     */       }
/*  83 */       boolean winged = (this.rand.nextDouble() < this.CHANCE_WINGED);
/*  84 */       model.setBlock(x, y - 1, MarioLevelModel.getWingedEnemyVersion(t, winged));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void placePipe(MarioLevelModel model, int x, int y, int height) {
/*  89 */     model.setRectangle(x, y - height, 2, height, 't');
/*     */   }
/*     */   
/*     */   private void setGroundHeight(MarioLevelModel model, int x, int y, int lastY, int nextY) {
/*  93 */     int mapHeight = model.getHeight();
/*  94 */     model.setRectangle(x, y + 1, 1, mapHeight - 1 - y, 'X');
/*     */     
/*  96 */     if (y < lastY) {
/*  97 */       model.setBlock(x, y, 'X');
/*  98 */       for (int i = y + 1; i <= lastY; i++) {
/*  99 */         model.setBlock(x, i, 'X');
/*     */       }
/* 101 */     } else if (y < nextY) {
/* 102 */       model.setBlock(x, y, 'X');
/* 103 */       for (int i = y + 1; i <= nextY; i++) {
/* 104 */         model.setBlock(x, i, 'X');
/*     */       }
/*     */     } else {
/* 107 */       model.setBlock(x, y, 'X');
/*     */     } 
/*     */ 
/*     */     
/* 111 */     if (x == model.getWidth() - 5) {
/* 112 */       this.yExit = y - 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
/* 117 */     this.rand = new Random();
/* 118 */     model.clearMap();
/*     */     
/* 120 */     ArrayList<Integer> ground = new ArrayList<>();
/*     */ 
/*     */     
/* 123 */     int lastY = this.GROUND_MAX_HEIGHT + (int)(this.rand.nextDouble() * (model.getHeight() - 1 - this.GROUND_MAX_HEIGHT));
/* 124 */     int y = lastY;
/* 125 */     int nextY = y;
/* 126 */     boolean justChanged = false;
/* 127 */     int length = 0;
/* 128 */     int landHeight = model.getHeight() - 1;
/*     */     
/*     */     int x;
/* 131 */     for (x = 0; x < model.getWidth(); x++) {
/*     */ 
/*     */       
/* 134 */       if (length > this.GAP_LENGTH && y >= model.getHeight()) {
/* 135 */         nextY = landHeight;
/* 136 */         justChanged = true;
/* 137 */         length = 1;
/*     */       
/*     */       }
/* 140 */       else if (x > this.minX && this.rand.nextDouble() < this.CHANGE_HILL_CHANGE && !justChanged) {
/* 141 */         nextY += (int)(this.GAP_OFFSET + this.GAP_RANGE * this.rand.nextDouble());
/* 142 */         nextY = Math.min(model.getHeight() - 2, nextY);
/* 143 */         nextY = Math.max(5, nextY);
/* 144 */         justChanged = true;
/* 145 */         length = 1;
/*     */       
/*     */       }
/* 148 */       else if (x > this.minX && y < model.getHeight() && this.rand.nextDouble() < this.CHANGE_GAP && !justChanged && 
/* 149 */         this.gapCount < this.maxGaps) {
/* 150 */         landHeight = Math.min(model.getHeight() - 1, lastY);
/* 151 */         nextY = model.getHeight();
/* 152 */         justChanged = true;
/* 153 */         length = 1;
/* 154 */         this.gapCount++;
/*     */       } else {
/* 156 */         length++;
/* 157 */         justChanged = false;
/*     */       } 
/*     */       
/* 160 */       setGroundHeight(model, x, y, lastY, nextY);
/* 161 */       ground.add(Integer.valueOf(y));
/*     */       
/* 163 */       lastY = y;
/* 164 */       y = nextY;
/*     */     } 
/*     */ 
/*     */     
/* 168 */     x = 0;
/* 169 */     y = model.getHeight();
/* 170 */     for (Integer h : ground) {
/* 171 */       if (y == model.getHeight()) {
/* 172 */         if (x > 10 && this.rand.nextDouble() < this.CHANCE_HILL) {
/* 173 */           y = (int)(this.HILL_HEIGHT + this.rand.nextDouble() * (h.intValue() - this.HILL_HEIGHT));
/* 174 */           model.setBlock(x, y, '%');
/* 175 */           for (int i = y + 1; i < h.intValue(); i++) {
/* 176 */             model.setBlock(x, i, '|');
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 181 */       else if (y >= h.intValue()) {
/* 182 */         y = model.getHeight();
/* 183 */       } else if (this.rand.nextDouble() < this.CHANCE_END_HILL) {
/* 184 */         model.setBlock(x, y, '%');
/* 185 */         for (int i = y + 1; i < h.intValue(); i++) {
/* 186 */           model.setBlock(x, i, '|');
/*     */         }
/*     */         
/* 189 */         y = model.getHeight();
/*     */       } else {
/* 191 */         model.setBlock(x, y, '%');
/* 192 */         for (int i = y + 1; i < h.intValue(); i++) {
/* 193 */           model.setBlock(x, i, '|');
/*     */         }
/*     */         
/* 196 */         if (this.rand.nextDouble() < this.CHANCE_HILL_ENEMY) {
/* 197 */           char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt((MarioLevelModel.getEnemyCharacters(false)).length)];
/*     */           
/* 199 */           if (t == 'k' || t == 'r') {
/* 200 */             if (this.turtleCount < this.maxTurtles) {
/* 201 */               this.turtleCount++;
/*     */             } else {
/* 203 */               t = 'g';
/*     */             } 
/*     */           }
/* 206 */           boolean winged = (this.rand.nextDouble() < this.CHANCE_WINGED);
/* 207 */           model.setBlock(x, y - 1, MarioLevelModel.getWingedEnemyVersion(t, winged));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 212 */       x++;
/*     */     } 
/*     */ 
/*     */     
/* 216 */     lastY = 0;
/* 217 */     int lastlastY = 0;
/* 218 */     x = 0;
/* 219 */     int lastX = 0;
/* 220 */     for (Integer h : ground) {
/* 221 */       if (x > this.minX && this.rand.nextDouble() < this.CHANCE_PIPE && 
/* 222 */         h.intValue() == lastY && lastlastY <= lastY && x > lastX + 1) {
/* 223 */         int height = this.PIPE_MIN_HEIGHT + (int)(Math.random() * this.PIPE_HEIGHT);
/* 224 */         placePipe(model, x - 1, h.intValue(), height);
/* 225 */         lastX = x;
/*     */       } 
/*     */ 
/*     */       
/* 229 */       lastlastY = lastY;
/* 230 */       lastY = h.intValue();
/* 231 */       x++;
/*     */     } 
/*     */ 
/*     */     
/* 235 */     x = 0;
/* 236 */     for (Integer h : ground) {
/* 237 */       if (x > this.minX && this.rand.nextDouble() < this.CHANCE_ENEMY) {
/* 238 */         char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt((MarioLevelModel.getEnemyCharacters(false)).length)];
/*     */         
/* 240 */         if (t == 'k' || t == 'r') {
/* 241 */           if (this.turtleCount < this.maxTurtles) {
/* 242 */             this.turtleCount++;
/*     */           } else {
/* 244 */             t = 'g';
/*     */           } 
/*     */         }
/* 247 */         boolean winged = (this.rand.nextDouble() < this.CHANCE_WINGED);
/* 248 */         char tile = model.getBlock(x, h.intValue() - 1);
/* 249 */         if (tile == '-') {
/* 250 */           model.setBlock(x, h.intValue() - 1, MarioLevelModel.getWingedEnemyVersion(t, winged));
/*     */         }
/*     */       } 
/* 253 */       x++;
/*     */     } 
/*     */ 
/*     */     
/* 257 */     x = 0;
/* 258 */     y = model.getHeight();
/* 259 */     for (Integer h : ground) {
/* 260 */       int max = 0;
/*     */ 
/*     */       
/* 263 */       for (max = 0; max < h.intValue(); max++) {
/* 264 */         int tile = model.getBlock(x, max);
/* 265 */         if (tile != 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 270 */       if (y == model.getHeight()) {
/* 271 */         if (x > this.minX && this.rand.nextDouble() < this.CHANCE_PLATFORM) {
/* 272 */           y = max - this.PLATFORM_HEIGHT;
/*     */           
/* 274 */           if (y >= 1 && h.intValue() - max > 1) {
/* 275 */             placeBlock(model, x, y);
/*     */           } else {
/* 277 */             y = model.getHeight();
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 282 */       } else if (y >= max + 1) {
/* 283 */         y = model.getHeight();
/* 284 */       } else if (this.rand.nextDouble() < this.CHANCE_END_PLATFORM) {
/* 285 */         placeBlock(model, x, y);
/* 286 */         y = model.getHeight();
/*     */       } else {
/* 288 */         placeBlock(model, x, y);
/*     */       } 
/*     */       
/* 291 */       x++;
/*     */     } 
/*     */ 
/*     */     
/* 295 */     x = 0;
/* 296 */     for (Integer h : ground) {
/* 297 */       if (x > 5 && this.rand.nextDouble() < this.CHANCE_COIN) {
/* 298 */         y = h.intValue() - (int)(1.0D + Math.random() * this.COIN_HEIGHT);
/*     */         
/* 300 */         char tile = model.getBlock(x, y);
/* 301 */         if (tile == '-') {
/* 302 */           model.setBlock(x, y, 'o');
/*     */         }
/*     */       } 
/*     */       
/* 306 */       x++;
/*     */     } 
/*     */     
/* 309 */     this.xExit = model.getWidth() - 5;
/* 310 */     model.setBlock(this.xExit, this.yExit, 'F');
/* 311 */     return model.getMap();
/*     */   }
/*     */   
/*     */   public String getGeneratorName() {
/* 315 */     return "BenWeberLevelGenerator";
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\levelGenerators\benWeber\LevelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */