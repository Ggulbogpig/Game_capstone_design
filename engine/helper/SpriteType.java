/*    */ package engine.helper;
/*    */ 
/*    */ import engine.core.MarioSprite;
/*    */ import engine.sprites.Enemy;
/*    */ import engine.sprites.FlowerEnemy;
/*    */ 
/*    */ public enum SpriteType {
/*  8 */   NONE(
/*  9 */     0),
/* 10 */   UNDEF(-42),
/* 11 */   MARIO(-31),
/* 12 */   FIREBALL(16),
/* 13 */   GOOMBA(2, 16),
/* 14 */   GOOMBA_WINGED(3, 16),
/* 15 */   RED_KOOPA(4, 0),
/* 16 */   RED_KOOPA_WINGED(5, 0),
/* 17 */   GREEN_KOOPA(6, 8),
/* 18 */   GREEN_KOOPA_WINGED(7, 8),
/* 19 */   SPIKY(8, 24),
/* 20 */   SPIKY_WINGED(9, 24),
/* 21 */   BULLET_BILL(10, 40),
/* 22 */   ENEMY_FLOWER(11, 48),
/* 23 */   MUSHROOM(12),
/* 24 */   FIRE_FLOWER(13),
/* 25 */   SHELL(14),
/* 26 */   LIFE_MUSHROOM(15);
/*    */   
/*    */   private int value;
/*    */   private int startIndex;
/*    */   
/*    */   SpriteType(int newValue) {
/* 32 */     this.value = newValue;
/*    */   }
/*    */   
/*    */   SpriteType(int newValue, int newIndex) {
/* 36 */     this.value = newValue;
/* 37 */     this.startIndex = newIndex;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 41 */     return this.value;
/*    */   }
/*    */   
/*    */   public int getStartIndex() {
/* 45 */     return this.startIndex;
/*    */   }
/*    */   
/*    */   public MarioSprite spawnSprite(boolean visuals, int xTile, int yTile, int dir) {
/* 49 */     if (this == ENEMY_FLOWER) {
/* 50 */       return (MarioSprite)new FlowerEnemy(visuals, (xTile * 16 + 17), (yTile * 16 + 18));
/*    */     }
/* 52 */     return (MarioSprite)new Enemy(visuals, (xTile * 16 + 8), (yTile * 16 + 15), dir, this);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\SpriteType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */