/*     */ package levelGenerators.notch;
/*     */ 
/*     */ import engine.core.MarioLevelGenerator;
/*     */ import engine.core.MarioLevelModel;
/*     */ import engine.core.MarioTimer;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class LevelGenerator
/*     */   implements MarioLevelGenerator
/*     */ {
/*     */   private static final int ODDS_STRAIGHT = 0;
/*     */   private static final int ODDS_HILL_STRAIGHT = 1;
/*     */   private static final int ODDS_TUBES = 2;
/*     */   private static final int ODDS_JUMP = 3;
/*     */   private static final int ODDS_CANNONS = 4;
/*  16 */   private int[] odds = new int[5];
/*     */   private int totalOdds;
/*     */   private int difficulty;
/*     */   private int type;
/*     */   private Random random;
/*     */   
/*     */   public LevelGenerator() {
/*  23 */     this.random = new Random();
/*  24 */     this.type = this.random.nextInt(3);
/*  25 */     this.difficulty = this.random.nextInt(5);
/*     */   }
/*     */   
/*     */   public LevelGenerator(int type, int difficulty) {
/*  29 */     this.random = new Random();
/*  30 */     this.type = type;
/*  31 */     this.difficulty = difficulty;
/*     */   }
/*     */   
/*     */   private int buildZone(MarioLevelModel model, int x, int maxLength) {
/*  35 */     int t = this.random.nextInt(this.totalOdds);
/*  36 */     int type = 0;
/*  37 */     for (int i = 0; i < this.odds.length; i++) {
/*  38 */       if (this.odds[i] <= t) {
/*  39 */         type = i;
/*     */       }
/*     */     } 
/*     */     
/*  43 */     switch (type) {
/*     */       case 0:
/*  45 */         return buildStraight(model, x, maxLength, false);
/*     */       case 1:
/*  47 */         return buildHillStraight(model, x, maxLength);
/*     */       case 2:
/*  49 */         return buildTubes(model, x, maxLength);
/*     */       case 3:
/*  51 */         return buildJump(model, x, maxLength);
/*     */       case 4:
/*  53 */         return buildCannons(model, x, maxLength);
/*     */     } 
/*  55 */     return 0;
/*     */   }
/*     */   
/*     */   private int buildJump(MarioLevelModel model, int xo, int maxLength) {
/*  59 */     int js = this.random.nextInt(4) + 2;
/*  60 */     int jl = this.random.nextInt(2) + 2;
/*  61 */     int length = js * 2 + jl;
/*     */     
/*  63 */     boolean hasStairs = (this.random.nextInt(3) == 0);
/*     */     
/*  65 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/*  66 */     for (int x = xo; x < xo + length; x++) {
/*  67 */       if (x < xo + js || x > xo + length - js - 1) {
/*  68 */         for (int y = 0; y < model.getHeight(); y++) {
/*  69 */           if (y >= floor) {
/*  70 */             model.setBlock(x, y, 'X');
/*  71 */           } else if (hasStairs) {
/*  72 */             if (x < xo + js) {
/*  73 */               if (y >= floor - x - xo + 1) {
/*  74 */                 model.setBlock(x, y, 'X');
/*     */               }
/*     */             }
/*  77 */             else if (y >= floor - xo + length - x + 2) {
/*  78 */               model.setBlock(x, y, 'X');
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return length;
/*     */   }
/*     */   
/*     */   private int buildCannons(MarioLevelModel model, int xo, int maxLength) {
/*  90 */     int length = this.random.nextInt(10) + 2;
/*  91 */     if (length > maxLength) {
/*  92 */       length = maxLength;
/*     */     }
/*  94 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/*  95 */     int xCannon = xo + 1 + this.random.nextInt(4);
/*  96 */     for (int x = xo; x < xo + length; x++) {
/*  97 */       if (x > xCannon) {
/*  98 */         xCannon += 2 + this.random.nextInt(4);
/*     */       }
/* 100 */       if (xCannon == xo + length - 1)
/* 101 */         xCannon += 10; 
/* 102 */       int cannonHeight = floor - this.random.nextInt(4) - 1;
/*     */       
/* 104 */       for (int y = 0; y < model.getHeight(); y++) {
/* 105 */         if (y >= floor) {
/* 106 */           model.setBlock(x, y, 'X');
/*     */         }
/* 108 */         else if (x == xCannon && y >= cannonHeight) {
/* 109 */           model.setBlock(x, y, '*');
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 115 */     return length;
/*     */   }
/*     */   
/*     */   private int buildHillStraight(MarioLevelModel model, int xo, int maxLength) {
/* 119 */     int length = this.random.nextInt(10) + 10;
/* 120 */     if (length > maxLength) {
/* 121 */       length = maxLength;
/*     */     }
/* 123 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/* 124 */     for (int x = xo; x < xo + length; x++) {
/* 125 */       for (int y = 0; y < model.getHeight(); y++) {
/* 126 */         if (y >= floor) {
/* 127 */           model.setBlock(x, y, 'X');
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     addEnemyLine(model, xo + 1, xo + length - 1, floor - 1);
/*     */     
/* 134 */     int h = floor;
/*     */     
/* 136 */     boolean keepGoing = true;
/*     */     
/* 138 */     boolean[] occupied = new boolean[length];
/* 139 */     while (keepGoing) {
/* 140 */       h = h - 2 - this.random.nextInt(3);
/*     */       
/* 142 */       if (h <= 0) {
/* 143 */         keepGoing = false; continue;
/*     */       } 
/* 145 */       int l = this.random.nextInt(5) + 3;
/* 146 */       int xxo = this.random.nextInt(length - l - 2) + xo + 1;
/*     */       
/* 148 */       if (occupied[xxo - xo] || occupied[xxo - xo + l] || occupied[xxo - xo - 1] || 
/* 149 */         occupied[xxo - xo + l + 1]) {
/* 150 */         keepGoing = false; continue;
/*     */       } 
/* 152 */       occupied[xxo - xo] = true;
/* 153 */       occupied[xxo - xo + l] = true;
/* 154 */       addEnemyLine(model, xxo, xxo + l, h - 1);
/* 155 */       if (this.random.nextInt(4) == 0) {
/* 156 */         decorate(model, xxo - 1, xxo + l + 1, h);
/* 157 */         keepGoing = false;
/*     */       } 
/* 159 */       for (int i = xxo; i < xxo + l; i++) {
/* 160 */         for (int y = h; y < floor; y++) {
/* 161 */           int yy = 9;
/* 162 */           if (y == h)
/* 163 */             yy = 8; 
/* 164 */           if (model.getBlock(i, y) == '-') {
/* 165 */             if (yy == 8) {
/* 166 */               model.setBlock(i, y, '%');
/*     */             } else {
/* 168 */               model.setBlock(i, y, '|');
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 177 */     return length;
/*     */   }
/*     */   
/*     */   private void addEnemyLine(MarioLevelModel model, int x0, int x1, int y) {
/* 181 */     char[] enemies = { 'g', 
/* 182 */         'k', 
/* 183 */         'r', 
/* 184 */         'y' };
/* 185 */     for (int x = x0; x < x1; x++) {
/* 186 */       if (this.random.nextInt(35) < this.difficulty + 1) {
/* 187 */         int type = this.random.nextInt(4);
/* 188 */         if (this.difficulty < 1) {
/* 189 */           type = 0;
/* 190 */         } else if (this.difficulty < 3) {
/* 191 */           type = 1 + this.random.nextInt(3);
/*     */         } 
/* 193 */         model.setBlock(x, y, MarioLevelModel.getWingedEnemyVersion(enemies[type], (this.random.nextInt(35) < this.difficulty)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int buildTubes(MarioLevelModel model, int xo, int maxLength) {
/* 199 */     int length = this.random.nextInt(10) + 5;
/* 200 */     if (length > maxLength) {
/* 201 */       length = maxLength;
/*     */     }
/* 203 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/* 204 */     int xTube = xo + 1 + this.random.nextInt(4);
/* 205 */     int tubeHeight = floor - this.random.nextInt(2) - 2;
/* 206 */     for (int x = xo; x < xo + length; x++) {
/* 207 */       if (x > xTube + 1) {
/* 208 */         xTube += 3 + this.random.nextInt(4);
/* 209 */         tubeHeight = floor - this.random.nextInt(2) - 2;
/*     */       } 
/* 211 */       if (xTube >= xo + length - 2) {
/* 212 */         xTube += 10;
/*     */       }
/* 214 */       char tubeType = 't';
/* 215 */       if (x == xTube && this.random.nextInt(11) < this.difficulty + 1) {
/* 216 */         tubeType = 'T';
/*     */       }
/*     */       
/* 219 */       for (int y = 0; y < model.getHeight(); y++) {
/* 220 */         if (y >= floor) {
/* 221 */           model.setBlock(x, y, 'X');
/*     */         }
/* 223 */         else if ((x == xTube || x == xTube + 1) && y >= tubeHeight) {
/* 224 */           model.setBlock(x, y, tubeType);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 230 */     return length;
/*     */   }
/*     */   
/*     */   private int buildStraight(MarioLevelModel model, int xo, int maxLength, boolean safe) {
/* 234 */     int length = this.random.nextInt(10) + 2;
/* 235 */     if (safe)
/* 236 */       length = 10 + this.random.nextInt(5); 
/* 237 */     if (length > maxLength) {
/* 238 */       length = maxLength;
/*     */     }
/* 240 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/* 241 */     for (int x = xo; x < xo + length; x++) {
/* 242 */       for (int y = 0; y < model.getHeight(); y++) {
/* 243 */         if (y >= floor) {
/* 244 */           model.setBlock(x, y, 'X');
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     if (!safe && 
/* 250 */       length > 5) {
/* 251 */       decorate(model, xo, xo + length, floor);
/*     */     }
/*     */ 
/*     */     
/* 255 */     return length;
/*     */   }
/*     */   
/*     */   private void decorate(MarioLevelModel model, int x0, int x1, int floor) {
/* 259 */     if (floor < 1) {
/*     */       return;
/*     */     }
/* 262 */     boolean rocks = true;
/* 263 */     addEnemyLine(model, x0 + 1, x1 - 1, floor - 1);
/*     */     
/* 265 */     int s = this.random.nextInt(4);
/* 266 */     int e = this.random.nextInt(4);
/*     */     
/* 268 */     if (floor - 2 > 0 && 
/* 269 */       x1 - 1 - e - x0 + 1 + s > 1) {
/* 270 */       for (int x = x0 + 1 + s; x < x1 - 1 - e; x++) {
/* 271 */         model.setBlock(x, floor - 2, 'o');
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 276 */     s = this.random.nextInt(4);
/* 277 */     e = this.random.nextInt(4);
/*     */     
/* 279 */     if (floor - 4 > 0 && 
/* 280 */       x1 - 1 - e - x0 + 1 + s > 2) {
/* 281 */       for (int x = x0 + 1 + s; x < x1 - 1 - e; x++) {
/* 282 */         if (rocks) {
/* 283 */           if (x != x0 + 1 && x != x1 - 2 && this.random.nextInt(3) == 0) {
/* 284 */             if (this.random.nextInt(4) == 0) {
/* 285 */               model.setBlock(x, floor - 4, 'S');
/*     */             } else {
/* 287 */               model.setBlock(x, floor - 4, 'S');
/*     */             } 
/* 289 */           } else if (this.random.nextInt(4) == 0) {
/* 290 */             if (this.random.nextInt(4) == 0) {
/* 291 */               model.setBlock(x, floor - 4, 'o');
/*     */             } else {
/* 293 */               model.setBlock(x, floor - 4, 'o');
/*     */             } 
/*     */           } else {
/* 296 */             model.setBlock(x, floor - 4, 'o');
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
/* 306 */     model.clearMap();
/*     */     
/* 308 */     this.odds[0] = 20;
/* 309 */     this.odds[1] = 10;
/* 310 */     this.odds[2] = 2 + 1 * this.difficulty;
/* 311 */     this.odds[3] = 2 * this.difficulty;
/* 312 */     this.odds[4] = -10 + 5 * this.difficulty;
/*     */     
/* 314 */     if (this.type > 0) {
/* 315 */       this.odds[1] = 0;
/*     */     }
/*     */     
/* 318 */     for (int i = 0; i < this.odds.length; i++) {
/* 319 */       if (this.odds[i] < 0)
/* 320 */         this.odds[i] = 0; 
/* 321 */       this.totalOdds += this.odds[i];
/* 322 */       this.odds[i] = this.totalOdds - this.odds[i];
/*     */     } 
/*     */     
/* 325 */     int length = 0;
/* 326 */     length += buildStraight(model, 0, model.getWidth(), true);
/* 327 */     while (length < model.getWidth()) {
/* 328 */       length += buildZone(model, length, model.getWidth() - length);
/*     */     }
/*     */     
/* 331 */     int floor = model.getHeight() - 1 - this.random.nextInt(4);
/*     */     
/* 333 */     for (int x = length; x < model.getWidth(); x++) {
/* 334 */       for (int y = 0; y < model.getHeight(); y++) {
/* 335 */         if (y >= floor) {
/* 336 */           model.setBlock(x, y, 'X');
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 341 */     if (this.type > 0) {
/* 342 */       int ceiling = 0;
/* 343 */       int run = 0;
/* 344 */       for (int j = 0; j < model.getWidth(); j++) {
/* 345 */         if (run-- <= 0 && j > 4) {
/* 346 */           ceiling = this.random.nextInt(4);
/* 347 */           run = this.random.nextInt(4) + 4;
/*     */         } 
/* 349 */         for (int y = 0; y < model.getHeight(); y++) {
/* 350 */           if ((j > 4 && y <= ceiling) || j < 1) {
/* 351 */             model.setBlock(j, y, 'S');
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 356 */     return model.getMap();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGeneratorName() {
/* 361 */     return "NotchLevelGenerator";
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\levelGenerators\notch\LevelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */