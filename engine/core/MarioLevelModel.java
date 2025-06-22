/*     */ package engine.core;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarioLevelModel
/*     */ {
/*     */   public static final char MARIO_START = 'M';
/*     */   public static final char MARIO_EXIT = 'F';
/*     */   public static final char EMPTY = '-';
/*     */   public static final char GROUND = 'X';
/*     */   public static final char PYRAMID_BLOCK = '#';
/*     */   public static final char NORMAL_BRICK = 'S';
/*     */   public static final char COIN_BRICK = 'C';
/*     */   public static final char LIFE_BRICK = 'L';
/*     */   public static final char SPECIAL_BRICK = 'U';
/*     */   public static final char SPECIAL_QUESTION_BLOCK = '@';
/*     */   public static final char COIN_QUESTION_BLOCK = '!';
/*     */   public static final char COIN_HIDDEN_BLOCK = '2';
/*     */   public static final char LIFE_HIDDEN_BLOCK = '1';
/*     */   public static final char USED_BLOCK = 'D';
/*     */   public static final char COIN = 'o';
/*     */   public static final char PIPE = 't';
/*     */   public static final char PIPE_FLOWER = 'T';
/*     */   public static final char BULLET_BILL = '*';
/*     */   public static final char PLATFORM_BACKGROUND = '|';
/*     */   public static final char PLATFORM = '%';
/*     */   public static final char GOOMBA = 'g';
/*     */   public static final char GOOMBA_WINGED = 'G';
/*     */   public static final char RED_KOOPA = 'r';
/*     */   public static final char RED_KOOPA_WINGED = 'R';
/*     */   public static final char GREEN_KOOPA = 'k';
/*     */   public static final char GREEN_KOOPA_WINGED = 'K';
/*     */   public static final char SPIKY = 'y';
/*     */   public static final char SPIKY_WINGED = 'Y';
/*     */   private char[][] map;
/*     */   
/*     */   public static char[] getEnemyTiles() {
/*  44 */     return new char[] { '*', 'T' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getBumpableTiles() {
/*  53 */     return new char[] { 'S', 'C', 'L', 'U', 
/*  54 */         '@', '!' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getBlockTiles() {
/*  63 */     return new char[] { 'X', '#', 'D', 
/*  64 */         'S', 'C', 'L', 'U', 
/*  65 */         '@', '!', 
/*  66 */         't', 'T', '*' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getBlockNonSpecialTiles() {
/*  75 */     return new char[] { 'X', '#', 'D', 't' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getNonBlockingTiles() {
/*  84 */     return new char[] { 'o', '2', '1', '|' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getCollectablesTiles() {
/*  93 */     return new char[] { 'o', 
/*  94 */         'C', 'L', 'U', 
/*  95 */         '@', '!', 
/*  96 */         '2', '1' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char getWingedEnemyVersion(char enemy, boolean winged) {
/* 107 */     if (!winged) {
/* 108 */       if (enemy == 'G') {
/* 109 */         return 'g';
/*     */       }
/* 111 */       if (enemy == 'K') {
/* 112 */         return 'k';
/*     */       }
/* 114 */       if (enemy == 'R') {
/* 115 */         return 'r';
/*     */       }
/* 117 */       if (enemy == 'Y') {
/* 118 */         return 'y';
/*     */       }
/* 120 */       return enemy;
/*     */     } 
/* 122 */     if (enemy == 'g') {
/* 123 */       return 'G';
/*     */     }
/* 125 */     if (enemy == 'k') {
/* 126 */       return 'K';
/*     */     }
/* 128 */     if (enemy == 'r') {
/* 129 */       return 'R';
/*     */     }
/* 131 */     if (enemy == 'y') {
/* 132 */       return 'Y';
/*     */     }
/* 134 */     return enemy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getEnemyCharacters() {
/* 143 */     return new char[] { 'g', 'G', 'r', 'R', 
/* 144 */         'k', 'K', 'y', 'Y' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getEnemyCharacters(boolean wings) {
/* 154 */     if (wings) {
/* 155 */       return new char[] { 'G', 'R', 'K', 'Y' };
/*     */     }
/* 157 */     return new char[] { 'g', 'r', 'k', 'y' };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioLevelModel(int levelWidth, int levelHeight) {
/* 172 */     this.map = new char[levelWidth][levelHeight];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioLevelModel clone() {
/* 179 */     MarioLevelModel model = new MarioLevelModel(getWidth(), getHeight());
/* 180 */     for (int x = 0; x < model.getWidth(); x++) {
/* 181 */       for (int y = 0; y < model.getHeight(); y++) {
/* 182 */         model.map[x][y] = this.map[x][y];
/*     */       }
/*     */     } 
/* 185 */     return model;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 194 */     return this.map.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 203 */     return (this.map[0]).length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getBlock(int x, int y) {
/* 214 */     int currentX = x;
/* 215 */     int currentY = y;
/* 216 */     if (x < 0) currentX = 0; 
/* 217 */     if (y < 0) currentY = 0; 
/* 218 */     if (x > this.map.length - 1) currentX = this.map.length - 1; 
/* 219 */     if (y > (this.map[0]).length - 1) currentY = (this.map[0]).length - 1; 
/* 220 */     return this.map[currentX][currentY];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlock(int x, int y, char value) {
/* 231 */     if (x < 0 || y < 0 || x > this.map.length - 1 || y > (this.map[0]).length - 1)
/* 232 */       return;  this.map[x][y] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRectangle(int startX, int startY, int width, int height, char value) {
/* 245 */     for (int x = 0; x < width; x++) {
/* 246 */       for (int y = 0; y < height; y++) {
/* 247 */         setBlock(startX + x, startY + y, value);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFromString(String level) {
/* 258 */     copyFromString(0, 0, 0, 0, getWidth(), getHeight(), level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFromString(int targetX, int targetY, int sourceX, int sourceY, int width, int height, String level) {
/* 273 */     String[] lines = level.split("\n");
/* 274 */     for (int y = 0; y < height; y++) {
/* 275 */       for (int x = 0; x < width; x++) {
/* 276 */         int maxWidth = lines[0].length();
/* 277 */         int maxHeight = lines.length;
/* 278 */         setBlock(x + targetX, y + targetY, lines[Math.min(y + sourceY, maxHeight - 1)].charAt(Math.min(x + sourceX, maxWidth - 1)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearMap() {
/* 287 */     setRectangle(0, 0, getWidth(), getHeight(), '-');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMap() {
/* 296 */     String result = "";
/* 297 */     for (int y = 0; y < (this.map[0]).length; y++) {
/* 298 */       for (int x = 0; x < this.map.length; x++) {
/* 299 */         result = String.valueOf(result) + this.map[x][y];
/*     */       }
/* 301 */       result = String.valueOf(result) + "\n";
/*     */     } 
/* 303 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioResult testALevelWithAgent(MarioAgent agent, int timer) {
/* 314 */     MarioGame game = new MarioGame();
/* 315 */     return game.runGame(agent, getMap(), timer);
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioLevelModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */