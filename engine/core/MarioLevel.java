/*     */ package engine.core;
/*     */ 
/*     */ import engine.graphics.MarioImage;
/*     */ import engine.graphics.MarioTilemap;
/*     */ import engine.helper.Assets;
/*     */ import engine.helper.SpriteType;
/*     */ import engine.helper.TileFeature;
/*     */ import java.awt.Graphics;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarioLevel
/*     */ {
/* 404 */   public int width = 256;
/* 405 */   public int tileWidth = 16;
/* 406 */   public int height = 256;
/* 407 */   public int tileHeight = 16;
/* 408 */   public int totalCoins = 0;
/* 409 */   public int totalEnemies = 0;
/*     */   
/*     */   public int marioTileX;
/*     */   
/*     */   public int marioTileY;
/*     */   
/*     */   public int exitTileX;
/*     */   public int exitTileY;
/*     */   
/*     */   public MarioLevel(String level, boolean visuals) {
/* 419 */     if (level.trim().length() == 0) {
/* 420 */       this.tileWidth = 0;
/* 421 */       this.width = 0;
/* 422 */       this.tileHeight = 0;
/* 423 */       this.height = 0;
/*     */       return;
/*     */     } 
/* 426 */     String[] lines = level.split("\\r?\\n");
/* 427 */     this.tileWidth = lines[0].length();
/* 428 */     this.width = this.tileWidth * 16;
/* 429 */     this.tileHeight = lines.length;
/* 430 */     this.height = this.tileHeight * 16;
/*     */     
/* 432 */     this.levelTiles = new int[lines[0].length()][lines.length];
/* 433 */     this.spriteTemplates = new SpriteType[lines[0].length()][lines.length];
/* 434 */     this.lastSpawnTime = new int[lines[0].length()][lines.length];
/* 435 */     for (int y = 0; y < lines.length; y++) {
/* 436 */       for (int x = 0; x < lines[y].length(); x++) {
/* 437 */         this.levelTiles[x][y] = 0;
/* 438 */         this.spriteTemplates[x][y] = SpriteType.NONE;
/* 439 */         this.lastSpawnTime[x][y] = -40;
/*     */       } 
/*     */     } 
/*     */     
/* 443 */     boolean marioLocInit = false;
/* 444 */     boolean exitLocInit = false; int i;
/* 445 */     for (i = 0; i < lines.length; i++) {
/* 446 */       for (int x = 0; x < lines[i].length(); x++) {
/* 447 */         int tempIndex; boolean singlePipe; Character c = Character.valueOf(lines[i].charAt(x));
/* 448 */         switch (c.charValue()) {
/*     */           case 'M':
/* 450 */             this.marioTileX = x;
/* 451 */             this.marioTileY = i;
/* 452 */             marioLocInit = true;
/*     */             break;
/*     */           case 'F':
/* 455 */             this.exitTileX = x;
/* 456 */             this.exitTileY = i;
/* 457 */             exitLocInit = true;
/*     */             break;
/*     */           case 'y':
/* 460 */             this.spriteTemplates[x][i] = SpriteType.SPIKY;
/* 461 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'Y':
/* 464 */             this.spriteTemplates[x][i] = SpriteType.SPIKY_WINGED;
/* 465 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'E':
/*     */           case 'g':
/* 469 */             this.spriteTemplates[x][i] = SpriteType.GOOMBA;
/* 470 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'G':
/* 473 */             this.spriteTemplates[x][i] = SpriteType.GOOMBA_WINGED;
/* 474 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'k':
/* 477 */             this.spriteTemplates[x][i] = SpriteType.GREEN_KOOPA;
/* 478 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'K':
/* 481 */             this.spriteTemplates[x][i] = SpriteType.GREEN_KOOPA_WINGED;
/* 482 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'r':
/* 485 */             this.spriteTemplates[x][i] = SpriteType.RED_KOOPA;
/* 486 */             this.totalEnemies++;
/*     */             break;
/*     */           case 'R':
/* 489 */             this.spriteTemplates[x][i] = SpriteType.RED_KOOPA_WINGED;
/* 490 */             this.totalEnemies++;
/*     */             break;
/*     */           
/*     */           case 'X':
/* 494 */             this.levelTiles[x][i] = 1;
/*     */             break;
/*     */           
/*     */           case '#':
/* 498 */             this.levelTiles[x][i] = 2;
/*     */             break;
/*     */           
/*     */           case '%':
/* 502 */             tempIndex = 0;
/* 503 */             if (x > 0 && lines[i].charAt(x - 1) == '%') {
/* 504 */               tempIndex += 2;
/*     */             }
/* 506 */             if (x < this.levelTiles.length - 1 && lines[i].charAt(x + 1) == '%') {
/* 507 */               tempIndex++;
/*     */             }
/* 509 */             this.levelTiles[x][i] = 43 + tempIndex;
/*     */             break;
/*     */           
/*     */           case '|':
/* 513 */             this.levelTiles[x][i] = 47;
/*     */             break;
/*     */           
/*     */           case '*':
/* 517 */             tempIndex = 0;
/* 518 */             if (i > 0 && lines[i - 1].charAt(x) == '*') {
/* 519 */               tempIndex++;
/*     */             }
/* 521 */             if (i > 1 && lines[i - 2].charAt(x) == '*') {
/* 522 */               tempIndex++;
/*     */             }
/* 524 */             this.levelTiles[x][i] = 3 + tempIndex;
/*     */             break;
/*     */           
/*     */           case 'B':
/* 528 */             this.levelTiles[x][i] = 3;
/*     */             break;
/*     */           
/*     */           case 'b':
/* 532 */             tempIndex = 0;
/* 533 */             if (i > 1 && lines[i - 2].charAt(x) == 'B') {
/* 534 */               tempIndex++;
/*     */             }
/* 536 */             this.levelTiles[x][i] = 4 + tempIndex;
/*     */             break;
/*     */           
/*     */           case '?':
/*     */           case '@':
/* 541 */             this.levelTiles[x][i] = 8;
/*     */             break;
/*     */           
/*     */           case '!':
/*     */           case 'Q':
/* 546 */             this.totalCoins++;
/* 547 */             this.levelTiles[x][i] = 11;
/*     */             break;
/*     */           
/*     */           case '1':
/* 551 */             this.levelTiles[x][i] = 48;
/*     */             break;
/*     */           
/*     */           case '2':
/* 555 */             this.totalCoins++;
/* 556 */             this.levelTiles[x][i] = 49;
/*     */             break;
/*     */           
/*     */           case 'D':
/* 560 */             this.levelTiles[x][i] = 14;
/*     */             break;
/*     */           
/*     */           case 'S':
/* 564 */             this.levelTiles[x][i] = 6;
/*     */             break;
/*     */           
/*     */           case 'C':
/* 568 */             this.totalCoins++;
/* 569 */             this.levelTiles[x][i] = 7;
/*     */             break;
/*     */           
/*     */           case 'U':
/* 573 */             this.levelTiles[x][i] = 50;
/*     */             break;
/*     */           
/*     */           case 'L':
/* 577 */             this.levelTiles[x][i] = 51;
/*     */             break;
/*     */           
/*     */           case 'o':
/* 581 */             this.totalCoins++;
/* 582 */             this.levelTiles[x][i] = 15;
/*     */             break;
/*     */           
/*     */           case 't':
/* 586 */             tempIndex = 0;
/* 587 */             singlePipe = false;
/* 588 */             if (x < lines[i].length() - 1 && Character.toLowerCase(lines[i].charAt(x + 1)) != 't' && 
/* 589 */               x > 0 && Character.toLowerCase(lines[i].charAt(x - 1)) != 't') {
/* 590 */               singlePipe = true;
/*     */             }
/* 592 */             if (x > 0 && (this.levelTiles[x - 1][i] == 18 || this.levelTiles[x - 1][i] == 20)) {
/* 593 */               tempIndex++;
/*     */             }
/* 595 */             if (i > 0 && Character.toLowerCase(lines[i - 1].charAt(x)) == 't') {
/* 596 */               if (singlePipe) {
/* 597 */                 tempIndex++;
/*     */               } else {
/* 599 */                 tempIndex += 2;
/*     */               } 
/*     */             }
/* 602 */             if (singlePipe) {
/* 603 */               this.levelTiles[x][i] = 52 + tempIndex; break;
/*     */             } 
/* 605 */             this.levelTiles[x][i] = 18 + tempIndex;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 'T':
/* 610 */             tempIndex = 0;
/* 611 */             singlePipe = (x < lines[i].length() - 1 && Character.toLowerCase(lines[i].charAt(x + 1)) != 't' && 
/* 612 */               x > 0 && Character.toLowerCase(lines[i].charAt(x - 1)) != 't');
/* 613 */             if (x > 0 && (this.levelTiles[x - 1][i] == 18 || this.levelTiles[x - 1][i] == 20)) {
/* 614 */               tempIndex++;
/*     */             }
/* 616 */             if (i > 0 && Character.toLowerCase(lines[i - 1].charAt(x)) == 't') {
/* 617 */               if (singlePipe) {
/* 618 */                 tempIndex++;
/*     */               } else {
/* 620 */                 tempIndex += 2;
/*     */               } 
/*     */             }
/* 623 */             if (singlePipe) {
/* 624 */               this.levelTiles[x][i] = 52 + tempIndex; break;
/*     */             } 
/* 626 */             if (tempIndex == 0) {
/* 627 */               this.spriteTemplates[x][i] = SpriteType.ENEMY_FLOWER;
/*     */             }
/* 629 */             this.levelTiles[x][i] = 18 + tempIndex;
/*     */             break;
/*     */ 
/*     */           
/*     */           case '<':
/* 634 */             this.levelTiles[x][i] = 18;
/*     */             break;
/*     */           
/*     */           case '>':
/* 638 */             this.levelTiles[x][i] = 19;
/*     */             break;
/*     */           
/*     */           case '[':
/* 642 */             this.levelTiles[x][i] = 20;
/*     */             break;
/*     */           
/*     */           case ']':
/* 646 */             this.levelTiles[x][i] = 21;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 651 */     if (!marioLocInit) {
/* 652 */       this.marioTileX = 0;
/* 653 */       this.marioTileY = findFirstFloor(lines, this.marioTileX);
/*     */     } 
/* 655 */     if (!exitLocInit) {
/* 656 */       this.exitTileX = lines[0].length() - 1;
/* 657 */       this.exitTileY = findFirstFloor(lines, this.exitTileX);
/*     */     } 
/* 659 */     for (i = this.exitTileY; i > Math.max(1, this.exitTileY - 11); i--) {
/* 660 */       this.levelTiles[this.exitTileX][i] = 40;
/*     */     }
/* 662 */     this.levelTiles[this.exitTileX][Math.max(1, this.exitTileY - 11)] = 39;
/*     */     
/* 664 */     if (visuals) {
/* 665 */       this.graphics = new MarioTilemap(Assets.level, this.levelTiles);
/* 666 */       this.flag = new MarioImage(Assets.level, 41);
/* 667 */       this.flag.width = 16;
/* 668 */       this.flag.height = 16;
/*     */     } 
/*     */   }
/*     */   private int[][] levelTiles; private SpriteType[][] spriteTemplates; private int[][] lastSpawnTime; private MarioTilemap graphics; private MarioImage flag;
/*     */   public MarioLevel clone() {
/* 673 */     MarioLevel level = new MarioLevel("", false);
/* 674 */     level.width = this.width;
/* 675 */     level.height = this.height;
/* 676 */     level.tileWidth = this.tileWidth;
/* 677 */     level.tileHeight = this.tileHeight;
/* 678 */     level.totalCoins = this.totalCoins;
/* 679 */     level.totalEnemies = this.totalEnemies;
/* 680 */     level.marioTileX = this.marioTileX;
/* 681 */     level.marioTileY = this.marioTileY;
/* 682 */     level.exitTileX = this.exitTileX;
/* 683 */     level.exitTileY = this.exitTileY;
/* 684 */     level.levelTiles = new int[this.levelTiles.length][(this.levelTiles[0]).length];
/* 685 */     level.lastSpawnTime = new int[this.levelTiles.length][(this.levelTiles[0]).length];
/* 686 */     for (int x = 0; x < level.levelTiles.length; x++) {
/* 687 */       for (int y = 0; y < (level.levelTiles[x]).length; y++) {
/* 688 */         level.levelTiles[x][y] = this.levelTiles[x][y];
/* 689 */         level.lastSpawnTime[x][y] = this.lastSpawnTime[x][y];
/*     */       } 
/*     */     } 
/* 692 */     level.spriteTemplates = this.spriteTemplates;
/* 693 */     return level;
/*     */   }
/*     */   
/*     */   public boolean isBlocking(int xTile, int yTile, float xa, float ya) {
/* 697 */     int block = getBlock(xTile, yTile);
/* 698 */     ArrayList<TileFeature> features = TileFeature.getTileType(block);
/* 699 */     boolean blocking = features.contains(TileFeature.BLOCK_ALL);
/* 700 */     int i = (blocking ? 1:0) | ((ya < 0.0F && features.contains(TileFeature.BLOCK_UPPER)) ? 1 : 0);
/* 701 */     i |= (ya > 0.0F && features.contains(TileFeature.BLOCK_LOWER)) ? 1 : 0;
/*     */     
/* 703 */     return i>0;
/*     */   }
/*     */   
/*     */   public int getBlock(int xTile, int yTile) {
/* 707 */     if (xTile < 0) {
/* 708 */       xTile = 0;
/*     */     }
/* 710 */     if (xTile > this.tileWidth - 1) {
/* 711 */       xTile = this.tileWidth - 1;
/*     */     }
/* 713 */     if (yTile < 0 || yTile > this.tileHeight - 1) {
/* 714 */       return 0;
/*     */     }
/* 716 */     return this.levelTiles[xTile][yTile];
/*     */   }
/*     */   
/*     */   public void setBlock(int xTile, int yTile, int index) {
/* 720 */     if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
/*     */       return;
/*     */     }
/* 723 */     this.levelTiles[xTile][yTile] = index;
/*     */   }
/*     */   
/*     */   public void setShiftIndex(int xTile, int yTile, int shift) {
/* 727 */     if (this.graphics == null || xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
/*     */       return;
/*     */     }
/* 730 */     this.graphics.moveShift[xTile][yTile] = shift;
/*     */   }
/*     */   
/*     */   public SpriteType getSpriteType(int xTile, int yTile) {
/* 734 */     if (xTile < 0 || yTile < 0 || xTile >= this.tileWidth || yTile >= this.tileHeight) {
/* 735 */       return SpriteType.NONE;
/*     */     }
/* 737 */     return this.spriteTemplates[xTile][yTile];
/*     */   }
/*     */   
/*     */   public int getLastSpawnTick(int xTile, int yTile) {
/* 741 */     if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
/* 742 */       return 0;
/*     */     }
/* 744 */     return this.lastSpawnTime[xTile][yTile];
/*     */   }
/*     */   
/*     */   public void setLastSpawnTick(int xTile, int yTile, int tick) {
/* 748 */     if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
/*     */       return;
/*     */     }
/* 751 */     this.lastSpawnTime[xTile][yTile] = tick;
/*     */   }
/*     */   
/*     */   public String getSpriteCode(int xTile, int yTile) {
/* 755 */     return String.valueOf(xTile) + "_" + yTile + "_" + getSpriteType(xTile, yTile).getValue();
/*     */   }
/*     */   
/*     */   private boolean isSolid(char c) {
/* 759 */     return !(c != 'X' && c != '#' && c != '@' && c != '!' && c != 'B' && c != 'C' && 
/* 760 */       c != 'Q' && c != '<' && c != '>' && c != '[' && c != ']' && c != '?' && 
/* 761 */       c != 'S' && c != 'U' && c != 'D' && c != '%' && c != 't' && c != 'T');
/*     */   }
/*     */   
/*     */   private int findFirstFloor(String[] lines, int x) {
/* 765 */     boolean skipLines = true;
/* 766 */     for (int i = lines.length - 1; i >= 0; i--) {
/* 767 */       Character c = Character.valueOf(lines[i].charAt(x));
/* 768 */       if (isSolid(c.charValue())) {
/* 769 */         skipLines = false;
/*     */       
/*     */       }
/* 772 */       else if (!skipLines && !isSolid(c.charValue())) {
/* 773 */         return i;
/*     */       } 
/*     */     } 
/* 776 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(int cameraX, int cameraY) {}
/*     */ 
/*     */   
/*     */   public void render(Graphics og, int cameraX, int cameraY) {
/* 784 */     this.graphics.render(og, cameraX, cameraY);
/* 785 */     if (cameraX + 256 >= this.exitTileX * 16)
/* 786 */       this.flag.render(og, this.exitTileX * 16 - 8 - cameraX, Math.max(1, this.exitTileY - 11) * 16 + 16 - cameraY); 
/*     */   }

            //추가
            public int[][] getLevelTiles() {
            return this.levelTiles;
            }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */