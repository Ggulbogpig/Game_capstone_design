/*     */ package agents.spencerSchumann;
/*     */ 
/*     */ import engine.core.MarioForwardModel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tiles
/*     */ {
/*     */   public static final byte EMPTY = 0;
/*     */   public static final byte SOLID = 1;
/*     */   public static final byte JUMPTHROUGH = 2;
/*     */   public static final byte BRICK = 5;
/*     */   public static final byte QUESTION = 6;
/*     */   public static final byte COIN = 7;
/*     */   public static final byte UNKNOWN = -1;
/*     */   ArrayList<Column> columns;
/*     */   
/*     */   public static boolean isWall(int tile) {
/*  21 */     switch (tile) {
/*     */       case 1:
/*     */       case 5:
/*     */       case 6:
/*  25 */         return true;
/*     */     } 
/*  27 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private class Column
/*     */   {
/*  33 */     public int startRow = 0;
/*  34 */     int[] tiles = null;
/*     */     
/*     */     public void setTile(int y, int tile) {
/*  37 */       if (this.tiles == null) {
/*  38 */         this.tiles = new int[1];
/*  39 */         this.tiles[0] = tile;
/*  40 */         this.startRow = y;
/*     */       } else {
/*  42 */         if (this.startRow > y) {
/*  43 */           int expansion = this.startRow - y;
/*  44 */           int[] newTiles = new int[this.tiles.length + expansion];
/*  45 */           System.arraycopy(this.tiles, 0, newTiles, expansion, this.tiles.length);
/*     */           
/*  47 */           for (int i = 0; i < this.startRow; i++) {
/*  48 */             newTiles[i] = -1;
/*     */           }
/*  50 */           this.tiles = newTiles;
/*  51 */           this.startRow = y;
/*  52 */         } else if (y >= this.startRow + this.tiles.length) {
/*  53 */           int expansion = y - this.startRow - this.tiles.length + 1;
/*  54 */           int[] newTiles = new int[this.tiles.length + expansion];
/*  55 */           System.arraycopy(this.tiles, 0, newTiles, 0, this.tiles.length);
/*     */           
/*  57 */           for (int i = this.tiles.length; i < newTiles.length; i++) {
/*  58 */             newTiles[i] = -1;
/*     */           }
/*  60 */           this.tiles = newTiles;
/*     */         } 
/*  62 */         this.tiles[y - this.startRow] = tile;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int getTile(int y) {
/*  67 */       if (y < this.startRow || y >= this.startRow + this.tiles.length) {
/*  68 */         return -1;
/*     */       }
/*  70 */       return this.tiles[y - this.startRow];
/*     */     }
/*     */ 
/*     */     
/*     */     private Column() {}
/*     */   }
/*     */   
/*     */   public Tiles() {
/*  78 */     this.columns = new ArrayList<>();
/*     */   }
/*     */   
/*     */   private void setTile(int x, int y, int tile) {
/*  82 */     if (x < 0) {
/*     */       return;
/*     */     }
/*  85 */     while (x >= this.columns.size()) {
/*  86 */       this.columns.add(null);
/*     */     }
/*  88 */     Column c = this.columns.get(x);
/*  89 */     if (c == null) {
/*  90 */       c = new Column();
/*  91 */       this.columns.set(x, c);
/*     */     } 
/*  93 */     c.setTile(y, tile);
/*     */   }
/*     */   
/*     */   public int getTile(int x, int y) {
/*  97 */     if (x < 0)
/*  98 */       return 0; 
/*  99 */     if (x >= this.columns.size()) {
/* 100 */       return -1;
/*     */     }
/* 102 */     Column c = this.columns.get(x);
/* 103 */     if (c == null) {
/* 104 */       return -1;
/*     */     }
/* 106 */     return c.getTile(y);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[][] getScene(int x, int y, int width, int height) {
/* 111 */     int[][] scene = new int[height][width];
/*     */     
/* 113 */     for (int row = 0; row < height; row++) {
/* 114 */       for (int col = 0; col < width; col++) {
/* 115 */         scene[row][col] = getTile(col + x, row + y);
/*     */       }
/*     */     } 
/* 118 */     return scene;
/*     */   }
/*     */   
/*     */   public void addObservation(MarioForwardModel model) {
/* 122 */     int[][] scene = model.getMarioSceneObservation();
/*     */     
/* 124 */     float[] marioPos = model.getMarioFloatPos();
/* 125 */     int offsetX = (int)(marioPos[0] / 16.0F);
/* 126 */     int offsetY = (int)(marioPos[1] / 16.0F);
/* 127 */     model.getClass(); offsetX -= 16 / 2;
/* 128 */     model.getClass(); offsetY -= 16 / 2;
/*     */ 
/*     */     
/* 131 */     for (int x = 0; x < scene.length; x++) {
/* 132 */       for (int y = 0; y < (scene[x]).length; y++) {
/* 133 */         int tile = scene[x][y];
/* 134 */         switch (tile) {
/*     */           case 0:
/* 136 */             tile = 0;
/*     */             break;
/*     */           case 17:
/* 139 */             tile = 1;
/*     */             break;
/*     */           case 23:
/* 142 */             tile = 5;
/*     */             break;
/*     */           case 24:
/* 145 */             tile = 6;
/*     */             break;
/*     */           case 31:
/* 148 */             tile = 7;
/*     */             break;
/*     */           case 59:
/* 151 */             tile = 2;
/*     */             break;
/*     */           default:
/* 154 */             tile = -1;
/*     */             break;
/*     */         } 
/* 157 */         if (tile != -1)
/* 158 */           setTile(x + offsetX, y + offsetY, tile); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\Tiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */