/*    */ package agents.spencerSchumann;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Enemy
/*    */ {
/*    */   public int type;
/*    */   public float x;
/*    */   public float y;
/*    */   public float vx;
/*    */   public float height;
/*    */   public float width;
/*    */   public boolean winged;
/*    */   public boolean safeTop;
/*    */   public static final int KIND_NONE = 0;
/*    */   public static final int KIND_GOOMBA = 2;
/*    */   public static final int KIND_GOOMBA_WINGED = 3;
/*    */   public static final int KIND_RED_KOOPA = 4;
/*    */   public static final int KIND_RED_KOOPA_WINGED = 5;
/*    */   public static final int KIND_GREEN_KOOPA = 6;
/*    */   public static final int KIND_GREEN_KOOPA_WINGED = 7;
/*    */   public static final int KIND_BULLET_BILL = 10;
/*    */   public static final int KIND_SPIKEY = 8;
/*    */   public static final int KIND_SPIKEY_WINGED = 9;
/*    */   public static final int KIND_ENEMY_FLOWER = 11;
/*    */   public static final int KIND_SHELL = 14;
/*    */   public static final int KIND_MUSHROOM = 12;
/*    */   public static final int KIND_FIRE_FLOWER = 13;
/*    */   public static final int KIND_FIREBALL = 16;
/*    */   
/*    */   public Enemy(int type, float x, float y) {
/* 39 */     this.type = type;
/* 40 */     this.x = x;
/* 41 */     this.y = y;
/*    */     
/* 43 */     this.height = 24.0F;
/* 44 */     this.width = 16.0F;
/* 45 */     if (type == 10) {
/* 46 */       this.height = 12.0F;
/* 47 */       this.width = 24.0F;
/*    */     } 
/*    */     
/* 50 */     switch (type) {
/*    */       case 3:
/*    */       case 5:
/*    */       case 7:
/*    */       case 9:
/* 55 */         this.winged = true;
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 61 */     switch (type) {
/*    */       case 8:
/*    */       case 9:
/*    */       case 11:
/* 65 */         this.safeTop = false;
/*    */         return;
/*    */     } 
/* 68 */     this.safeTop = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Enemy clone() {
/* 75 */     Enemy e = new Enemy(this.type, this.x, this.y);
/* 76 */     e.vx = this.vx;
/* 77 */     return e;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\Enemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */