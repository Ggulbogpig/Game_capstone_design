/*     */ package engine.core;
/*     */ import engine.effects.BrickEffect;
/*     */ import engine.effects.CoinEffect;
/*     */ import engine.graphics.MarioBackground;
/*     */ import engine.helper.EventType;
/*     */ import engine.helper.GameStatus;
/*     */ import engine.helper.SpriteType;
/*     */ import engine.helper.TileFeature;
/*     */ import engine.sprites.Fireball;
/*     */ import engine.sprites.Mario;
/*     */ import engine.sprites.Shell;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.util.ArrayList;
        //추가
        import engine.sprites.BulletBill;
        import engine.sprites.Fireball;
        import engine.sprites.Mushroom;
        import engine.sprites.FireFlower;
        import engine.sprites.LifeMushroom;
        import engine.effects.FireballEffect;

/*     */ 
/*     */ public class MarioWorld {
/*  17 */   public int pauseTimer = 0; public GameStatus gameStatus;
/*  18 */   public int fireballsOnScreen = 0;
/*  19 */   public int currentTimer = -1;
/*     */   
/*     */   public float cameraX;
/*     */   
/*     */   public float cameraY;
/*     */   
/*     */   public Mario mario;
/*     */   public MarioLevel level;
/*     */   public boolean visuals;
/*     */   public int currentTick;
/*     */   public int coins;
/*     */   public int lives;
/*     */   public ArrayList<MarioEvent> lastFrameEvents;
/*     */   private MarioEvent[] killEvents;
/*     */   private ArrayList<MarioSprite> sprites;
/*     */   private ArrayList<Shell> shellsToCheck;
/*     */   private ArrayList<Fireball> fireballsToCheck;
/*     */   private ArrayList<MarioSprite> addedSprites;
/*     */   private ArrayList<MarioSprite> removedSprites;
/*     */   private ArrayList<MarioEffect> effects;
/*  39 */   private MarioBackground[] backgrounds = new MarioBackground[2];
/*     */   
/*     */   public MarioWorld(MarioEvent[] killEvents) {
/*  42 */     this.pauseTimer = 0;
/*  43 */     this.gameStatus = GameStatus.RUNNING;
/*  44 */     this.sprites = new ArrayList<>();
/*  45 */     this.shellsToCheck = new ArrayList<>();
/*  46 */     this.fireballsToCheck = new ArrayList<>();
/*  47 */     this.addedSprites = new ArrayList<>();
/*  48 */     this.removedSprites = new ArrayList<>();
/*  49 */     this.effects = new ArrayList<>();
/*  50 */     this.lastFrameEvents = new ArrayList<>();
/*  51 */     this.killEvents = killEvents;
/*     */   }
/*     */   
/*     */   public void initializeVisuals(GraphicsConfiguration graphicsConfig) {
/*  55 */     int[][] tempBackground = { 
/*  56 */         { 42
/*  57 */         }, { 42
/*  58 */         }, { 42
/*  59 */         }, { 42
/*  60 */         }, { 42
/*  61 */         }, { 42
/*  62 */         }, { 42
/*  63 */         }, { 42
/*  64 */         }, { 42
/*  65 */         }, { 42 }, 
/*  66 */         { 42
/*  67 */         }, { 42
/*  68 */         }, { 42
/*  69 */         }, { 42
/*  70 */         }, { 42
/*  71 */         }, { 42 } };
/*     */     
/*  73 */     this.backgrounds[0] = new MarioBackground(graphicsConfig, 256, tempBackground);
/*  74 */     tempBackground = new int[][] {
/*  75 */         new int[16], 
/*  76 */         new int[16], {
/*  77 */           31, 32, 33
/*  78 */         }, { 34, 35, 36
/*  79 */         }, new int[16], { 
/*  80 */           0, 0, 0, 0, 0, 0, 0, 0, 31, 32, 33 }, { 
/*  81 */           0, 0, 0, 0, 0, 0, 0, 0, 34, 35, 36
/*  82 */         }, new int[16], 
/*  83 */         new int[16]
/*     */       };
/*  85 */     this.backgrounds[1] = new MarioBackground(graphicsConfig, 256, tempBackground);
/*     */   }
/*     */   
/*     */   public void initializeLevel(String level, int timer) {
/*  89 */     this.currentTimer = timer;
/*  90 */     this.level = new MarioLevel(level, this.visuals);
/*     */     
/*  92 */     this.mario = new Mario(this.visuals, (this.level.marioTileX * 16), (this.level.marioTileY * 16));
/*  93 */     this.mario.alive = true;
/*  94 */     this.mario.world = this;
/*  95 */     this.sprites.add(this.mario);
/*     */   }
/*     */   
/*     */   public ArrayList<MarioSprite> getEnemies() {
/*  99 */     ArrayList<MarioSprite> enemies = new ArrayList<>();
/* 100 */     for (MarioSprite sprite : this.sprites) {
/* 101 */       if (isEnemy(sprite)) {
/* 102 */         enemies.add(sprite);
/*     */       }
/*     */     } 
/* 105 */     return enemies;
/*     */   }
/*     */   
/*     */   public MarioWorld clone() {
/* 109 */     MarioWorld world = new MarioWorld(this.killEvents);
/* 110 */     world.visuals = false;
/* 111 */     world.cameraX = this.cameraX;
/* 112 */     world.cameraY = this.cameraY;
/* 113 */     world.fireballsOnScreen = this.fireballsOnScreen;
/* 114 */     world.gameStatus = this.gameStatus;
/* 115 */     world.pauseTimer = this.pauseTimer;
/* 116 */     world.currentTimer = this.currentTimer;
/* 117 */     world.currentTick = this.currentTick;
/* 118 */     world.level = this.level.clone();
/* 119 */     for (MarioSprite sprite : this.sprites) {
/* 120 */       MarioSprite cloneSprite = sprite.clone();
/* 121 */       cloneSprite.world = world;
/* 122 */       if (cloneSprite.type == SpriteType.MARIO) {
/* 123 */         world.mario = (Mario)cloneSprite;
/*     */       }
/* 125 */       world.sprites.add(cloneSprite);
/*     */     } 
/* 127 */     if (world.mario == null) {
/* 128 */       world.mario = (Mario)this.mario.clone();
/*     */     }
/*     */     
/* 131 */     world.coins = this.coins;
/* 132 */     world.lives = this.lives;
/* 133 */     return world;
/*     */   }
/*     */   
/*     */   public void addEvent(EventType eventType, int eventParam) {
/* 137 */     int marioState = 0;
/* 138 */     if (this.mario.isLarge) {
/* 139 */       marioState = 1;
/*     */     }
/* 141 */     if (this.mario.isFire) {
/* 142 */       marioState = 2;
/*     */     }
/* 144 */     this.lastFrameEvents.add(new MarioEvent(eventType, eventParam, this.mario.x, this.mario.y, marioState, this.currentTick));
/*     */   }
/*     */   
/*     */   public void addEffect(MarioEffect effect) {
/* 148 */     this.effects.add(effect);
/*     */   }
/*     */   
/*     */   public void addSprite(MarioSprite sprite) {
/* 152 */     this.addedSprites.add(sprite);
/* 153 */     sprite.alive = true;
/* 154 */     sprite.world = this;
/* 155 */     sprite.added();
/* 156 */     sprite.update();
/*     */   }
/*     */   
/*     */   public void removeSprite(MarioSprite sprite) {
/* 160 */     this.removedSprites.add(sprite);
/* 161 */     sprite.alive = false;
/* 162 */     sprite.removed();
/* 163 */     sprite.world = null;
/*     */   }
/*     */   
/*     */   public void checkShellCollide(Shell shell) {
/* 167 */     this.shellsToCheck.add(shell);
/*     */   }
/*     */   
/*     */   public void checkFireballCollide(Fireball fireball) {
/* 171 */     this.fireballsToCheck.add(fireball);
/*     */   }
/*     */   
/*     */   public void win() {
/* 175 */     addEvent(EventType.WIN, 0);
/* 176 */     this.gameStatus = GameStatus.WIN;
/*     */   }
/*     */   
/*     */   public void lose() {
/* 180 */     addEvent(EventType.LOSE, 0);
/* 181 */     this.gameStatus = GameStatus.LOSE;
/* 182 */     this.mario.alive = false;
/*     */   }
/*     */   
/*     */   public void timeout() {
/* 186 */     this.gameStatus = GameStatus.TIME_OUT;
/* 187 */     this.mario.alive = false;
/*     */   }
/*     */   
/*     */   public int[][] getSceneObservation(float centerX, float centerY, int detail) {
/* 191 */     int[][] ret = new int[16][16];
/* 192 */     int centerXInMap = (int)centerX / 16;
/* 193 */     int centerYInMap = (int)centerY / 16;
/*     */     
/* 195 */     for (int y = centerYInMap - 8, obsY = 0; y < centerYInMap + 8; y++, obsY++) {
/* 196 */       for (int x = centerXInMap - 8, obsX = 0; x < centerXInMap + 8; x++, obsX++) {
/* 197 */         int currentX = x;
/* 198 */         if (currentX < 0) {
/* 199 */           currentX = 0;
/*     */         }
/* 201 */         if (currentX > this.level.tileWidth - 1) {
/* 202 */           currentX = this.level.tileWidth - 1;
/*     */         }
/* 204 */         int currentY = y;
/* 205 */         if (currentY < 0) {
/* 206 */           currentY = 0;
/*     */         }
/* 208 */         if (currentY > this.level.tileHeight - 1) {
/* 209 */           currentY = this.level.tileHeight - 1;
/*     */         }
/* 211 */         ret[obsX][obsY] = MarioForwardModel.getBlockValueGeneralization(this.level.getBlock(currentX, currentY), detail);
/*     */       } 
/*     */     } 
/* 214 */     return ret;
/*     */   }
/*     */   
/*     */   public int[][] getEnemiesObservation(float centerX, float centerY, int detail) {
/* 218 */     int[][] ret = new int[16][16];
/* 219 */     int centerXInMap = (int)centerX / 16;
/* 220 */     int centerYInMap = (int)centerY / 16;
/*     */     
/* 222 */     for (int w = 0; w < ret.length; w++) {
/* 223 */       for (int h = 0; h < (ret[0]).length; h++)
/* 224 */         ret[w][h] = 0; 
/*     */     } 
/* 226 */     for (MarioSprite sprite : this.sprites) {
/* 227 */       if (sprite.type == SpriteType.MARIO)
/*     */         continue; 
/* 229 */       if (sprite.getMapX() >= 0 && 
/* 230 */         sprite.getMapX() > centerXInMap - 8 && 
/* 231 */         sprite.getMapX() < centerXInMap + 8 && 
/* 232 */         sprite.getMapY() >= 0 && 
/* 233 */         sprite.getMapY() > centerYInMap - 8 && 
/* 234 */         sprite.getMapY() < centerYInMap + 8) {
/* 235 */         int obsX = sprite.getMapX() - centerXInMap + 8;
/* 236 */         int obsY = sprite.getMapY() - centerYInMap + 8;
/* 237 */         ret[obsX][obsY] = MarioForwardModel.getSpriteTypeGeneralization(sprite.type, detail);
/*     */       } 
/*     */     } 
/* 240 */     return ret;
/*     */   }
/*     */   
/*     */   public int[][] getMergedObservation(float centerX, float centerY, int sceneDetail, int enemiesDetail) {
/* 244 */     int[][] ret = new int[16][16];
/* 245 */     int centerXInMap = (int)centerX / 16;
/* 246 */     int centerYInMap = (int)centerY / 16;
/*     */     
/* 248 */     for (int y = centerYInMap - 8, obsY = 0; y < centerYInMap + 8; y++, obsY++) {
/* 249 */       for (int x = centerXInMap - 8, obsX = 0; x < centerXInMap + 8; x++, obsX++) {
/* 250 */         int currentX = x;
/* 251 */         if (currentX < 0) {
/* 252 */           currentX = 0;
/*     */         }
/* 254 */         if (currentX > this.level.tileWidth - 1) {
/* 255 */           currentX = this.level.tileWidth - 1;
/*     */         }
/* 257 */         int currentY = y;
/* 258 */         if (currentY < 0) {
/* 259 */           currentY = 0;
/*     */         }
/* 261 */         if (currentY > this.level.tileHeight - 1) {
/* 262 */           currentY = this.level.tileHeight - 1;
/*     */         }
/* 264 */         ret[obsX][obsY] = MarioForwardModel.getBlockValueGeneralization(this.level.getBlock(x, y), sceneDetail);
/*     */       } 
/*     */     } 
/*     */     
/* 268 */     for (MarioSprite sprite : this.sprites) {
/* 269 */       if (sprite.type == SpriteType.MARIO)
/*     */         continue; 
/* 271 */       if (sprite.getMapX() >= 0 && 
/* 272 */         sprite.getMapX() > centerXInMap - 8 && 
/* 273 */         sprite.getMapX() < centerXInMap + 8 && 
/* 274 */         sprite.getMapY() >= 0 && 
/* 275 */         sprite.getMapY() > centerYInMap - 8 && 
/* 276 */         sprite.getMapY() < centerYInMap + 8) {
/* 277 */         int obsX = sprite.getMapX() - centerXInMap + 8;
/* 278 */         int i = sprite.getMapY() - centerYInMap + 8;
/* 279 */         int tmp = MarioForwardModel.getSpriteTypeGeneralization(sprite.type, enemiesDetail);
/* 280 */         if (tmp != SpriteType.NONE.getValue()) {
/* 281 */           ret[obsX][i] = tmp;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     return ret;
/*     */   }
/*     */   
/*     */   private boolean isEnemy(MarioSprite sprite) {
/* 290 */     return !(!(sprite instanceof engine.sprites.Enemy) && !(sprite instanceof engine.sprites.FlowerEnemy) && !(sprite instanceof BulletBill));
/*     */   }
/*     */   
/*     */   public void update(boolean[] actions) {
/* 294 */     if (this.gameStatus != GameStatus.RUNNING) {
/*     */       return;
/*     */     }
/* 297 */     if (this.pauseTimer > 0) {
/* 298 */       this.pauseTimer--;
/* 299 */       if (this.visuals) {
/* 300 */         this.mario.updateGraphics();
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 305 */     if (this.currentTimer > 0) {
/* 306 */       this.currentTimer -= 30;
/* 307 */       if (this.currentTimer <= 0) {
/* 308 */         this.currentTimer = 0;
/* 309 */         timeout();
/*     */         return;
/*     */       } 
/*     */     } 
/* 313 */     this.currentTick++;
/* 314 */     this.cameraX = this.mario.x - 128.0F;
/* 315 */     if (this.cameraX + 256.0F > this.level.width) {
/* 316 */       this.cameraX = (this.level.width - 256);
/*     */     }
/* 318 */     if (this.cameraX < 0.0F) {
/* 319 */       this.cameraX = 0.0F;
/*     */     }
/* 321 */     this.cameraY = this.mario.y - 128.0F;
/* 322 */     if (this.cameraY + 256.0F > this.level.height) {
/* 323 */       this.cameraY = (this.level.height - 256);
/*     */     }
/* 325 */     if (this.cameraY < 0.0F) {
/* 326 */       this.cameraY = 0.0F;
/*     */     }
/*     */     
/* 329 */     this.lastFrameEvents.clear();
/*     */     
/* 331 */     this.fireballsOnScreen = 0;
/* 332 */     for (MarioSprite sprite : this.sprites) {
/* 333 */       if (sprite.x < this.cameraX - 64.0F || sprite.x > this.cameraX + 256.0F + 64.0F || sprite.y > (this.level.height + 32)) {
/* 334 */         if (sprite.type == SpriteType.MARIO) {
/* 335 */           lose();
/*     */         }
/* 337 */         removeSprite(sprite);
/* 338 */         if (isEnemy(sprite) && sprite.y > 288.0F) {
/* 339 */           addEvent(EventType.FALL_KILL, sprite.type.getValue());
/*     */         }
/*     */         continue;
/*     */       } 
/* 343 */       if (sprite.type == SpriteType.FIREBALL) {
/* 344 */         this.fireballsOnScreen++;
/*     */       }
/*     */     } 
/* 347 */     this.level.update((int)this.cameraX, (int)this.cameraY);
/*     */     
/* 349 */     for (int x = (int)this.cameraX / 16 - 1; x <= (int)(this.cameraX + 256.0F) / 16 + 1; x++) {
/* 350 */       for (int y = (int)this.cameraY / 16 - 1; y <= (int)(this.cameraY + 256.0F) / 16 + 1; y++) {
/* 351 */         int dir = 0;
/* 352 */         if ((x * 16 + 8) > this.mario.x + 16.0F)
/* 353 */           dir = -1; 
/* 354 */         if ((x * 16 + 8) < this.mario.x - 16.0F) {
/* 355 */           dir = 1;
/*     */         }
/* 357 */         SpriteType type = this.level.getSpriteType(x, y);
/* 358 */         if (type != SpriteType.NONE) {
/* 359 */           String spriteCode = this.level.getSpriteCode(x, y);
/* 360 */           boolean found = false;
/* 361 */           for (MarioSprite sprite : this.sprites) {
/* 362 */             if (sprite.initialCode.equals(spriteCode)) {
/* 363 */               found = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 367 */           if (!found && 
/* 368 */             this.level.getLastSpawnTick(x, y) != this.currentTick - 1) {
/* 369 */             MarioSprite sprite = type.spawnSprite(this.visuals, x, y, dir);
/* 370 */             sprite.initialCode = spriteCode;
/* 371 */             addSprite(sprite);
/*     */           } 
/*     */           
/* 374 */           this.level.setLastSpawnTick(x, y, this.currentTick);
/*     */         } 
/*     */         
/* 377 */         if (dir != 0) {
/* 378 */           ArrayList<TileFeature> features = TileFeature.getTileType(this.level.getBlock(x, y));
/* 379 */           if (features.contains(TileFeature.SPAWNER) && 
/* 380 */             this.currentTick % 100 == 0) {
/* 381 */             addSprite((MarioSprite)new BulletBill(this.visuals, (x * 16 + 8 + dir * 8), (y * 16 + 15), dir));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 388 */     this.mario.actions = actions;
/* 389 */     for (MarioSprite sprite : this.sprites) {
/* 390 */       if (!sprite.alive) {
/*     */         continue;
/*     */       }
/* 393 */       sprite.update();
/*     */     } 
/* 395 */     for (MarioSprite sprite : this.sprites) {
/* 396 */       if (!sprite.alive) {
/*     */         continue;
/*     */       }
/* 399 */       sprite.collideCheck();
/*     */     } 
/*     */     
/* 402 */     for (Shell shell : this.shellsToCheck) {
/* 403 */       for (MarioSprite sprite : this.sprites) {
/* 404 */         if (sprite != shell && shell.alive && sprite.alive && 
/* 405 */           sprite.shellCollideCheck(shell)) {
/* 406 */           removeSprite(sprite);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 411 */     this.shellsToCheck.clear();
/*     */     
/* 413 */     for (Fireball fireball : this.fireballsToCheck) {
/* 414 */       for (MarioSprite sprite : this.sprites) {
/* 415 */         if (sprite != fireball && fireball.alive && sprite.alive && 
/* 416 */           sprite.fireballCollideCheck(fireball)) {
/* 417 */           if (this.visuals) {
/* 418 */             this.addEffect(new FireballEffect(fireball.x, fireball.y));
/*     */           }
/* 420 */           removeSprite((MarioSprite)fireball);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 425 */     this.fireballsToCheck.clear();
/*     */     
/* 427 */     this.sprites.addAll(0, this.addedSprites);
/* 428 */     this.sprites.removeAll(this.removedSprites);
/* 429 */     this.addedSprites.clear();
/* 430 */     this.removedSprites.clear();
/*     */ 
/*     */     
/* 433 */     if (this.killEvents != null) {
/* 434 */       byte b; int i; MarioEvent[] arrayOfMarioEvent; for (i = (arrayOfMarioEvent = this.killEvents).length, b = 0; b < i; ) { MarioEvent k = arrayOfMarioEvent[b];
/* 435 */         if (this.lastFrameEvents.contains(k))
/* 436 */           lose(); 
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public void bump(int xTile, int yTile, boolean canBreakBricks) {
/* 443 */     int block = this.level.getBlock(xTile, yTile);
/* 444 */     ArrayList<TileFeature> features = TileFeature.getTileType(block);
/*     */     
/* 446 */     if (features.contains(TileFeature.BUMPABLE)) {
/* 447 */       bumpInto(xTile, yTile - 1);
/* 448 */       addEvent(EventType.BUMP, 24);
/* 449 */       this.level.setBlock(xTile, yTile, 14);
/* 450 */       this.level.setShiftIndex(xTile, yTile, 4);
/*     */       
/* 452 */       if (features.contains(TileFeature.SPECIAL)) {
/* 453 */         if (!this.mario.isLarge) {
/* 454 */           addSprite((MarioSprite)new Mushroom(this.visuals, (xTile * 16 + 9), (yTile * 16 + 8)));
/*     */         } else {
/* 456 */           addSprite((MarioSprite)new FireFlower(this.visuals, (xTile * 16 + 9), (yTile * 16 + 8)));
/*     */         } 
/* 458 */       } else if (features.contains(TileFeature.LIFE)) {
/* 459 */         addSprite((MarioSprite)new LifeMushroom(this.visuals, (xTile * 16 + 9), (yTile * 16 + 8)));
/*     */       } else {
/* 461 */         this.mario.collectCoin();
/* 462 */         if (this.visuals) {
/* 463 */           addEffect((MarioEffect)new CoinEffect((xTile * 16 + 8), (yTile * 16)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 468 */     if (features.contains(TileFeature.BREAKABLE)) {
/* 469 */       bumpInto(xTile, yTile - 1);
/* 470 */       if (canBreakBricks) {
/* 471 */         addEvent(EventType.BUMP, 22);
/* 472 */         this.level.setBlock(xTile, yTile, 0);
/* 473 */         if (this.visuals) {
/* 474 */           for (int xx = 0; xx < 2; xx++) {
/* 475 */             for (int yy = 0; yy < 2; yy++) {
/* 476 */               addEffect((MarioEffect)new BrickEffect((xTile * 16 + xx * 8 + 4), (yTile * 16 + yy * 8 + 4), ((
/* 477 */                     xx * 2 - 1) * 4), ((yy * 2 - 1) * 4 - 8)));
/*     */             }
/*     */           } 
/*     */         }
/*     */       } else {
/* 482 */         this.level.setShiftIndex(xTile, yTile, 4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void bumpInto(int xTile, int yTile) {
/* 488 */     int block = this.level.getBlock(xTile, yTile);
/* 489 */     if (TileFeature.getTileType(block).contains(TileFeature.PICKABLE)) {
/* 490 */       addEvent(EventType.COLLECT, block);
/* 491 */       this.mario.collectCoin();
/* 492 */       this.level.setBlock(xTile, yTile, 0);
/* 493 */       if (this.visuals) {
/* 494 */         addEffect((MarioEffect)new CoinEffect((xTile * 16 + 8), (yTile * 16 + 8)));
/*     */       }
/*     */     } 
/*     */     
/* 498 */     for (MarioSprite sprite : this.sprites)
/* 499 */       sprite.bumpCheck(xTile, yTile); 
/*     */   }
/*     */   
/*     */   public void render(Graphics og) {
/*     */     int i;
/* 504 */     for (i = 0; i < this.backgrounds.length; i++) {
/* 505 */       this.backgrounds[i].render(og, (int)this.cameraX, (int)this.cameraY);
/*     */     }
/* 507 */     for (MarioSprite sprite : this.sprites) {
/* 508 */       if (sprite.type == SpriteType.MUSHROOM || sprite.type == SpriteType.LIFE_MUSHROOM || 
/* 509 */         sprite.type == SpriteType.FIRE_FLOWER || sprite.type == SpriteType.ENEMY_FLOWER) {
/* 510 */         sprite.render(og);
/*     */       }
/*     */     } 
/* 513 */     this.level.render(og, (int)this.cameraX, (int)this.cameraY);
/* 514 */     for (MarioSprite sprite : this.sprites) {
/* 515 */       if (sprite.type != SpriteType.MUSHROOM && sprite.type != SpriteType.LIFE_MUSHROOM && 
/* 516 */         sprite.type != SpriteType.FIRE_FLOWER && sprite.type != SpriteType.ENEMY_FLOWER) {
/* 517 */         sprite.render(og);
/*     */       }
/*     */     } 
/* 520 */     for (i = 0; i < this.effects.size(); i++) {
/* 521 */       if (((MarioEffect)this.effects.get(i)).life <= 0) {
/* 522 */         this.effects.remove(i);
/* 523 */         i--;
/*     */       } else {
/*     */         
/* 526 */         ((MarioEffect)this.effects.get(i)).render(og, this.cameraX, this.cameraY);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */