/*    */ package engine.core;
/*    */ 
/*    */ import engine.helper.SpriteType;
/*    */ import engine.sprites.Fireball;
/*    */ import engine.sprites.Mario;
/*    */ import engine.sprites.Shell;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public abstract class MarioSprite {
/* 10 */   public SpriteType type = SpriteType.UNDEF;
/*    */   
/*    */   public String initialCode;
/*    */   public float x;
/*    */   public float y;
/*    */   public float xa;
/*    */   public float ya;
/*    */   
/*    */   public MarioSprite(float x, float y, SpriteType type) {
/* 19 */     this.initialCode = "";
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.xa = 0.0F;
/* 23 */     this.ya = 0.0F;
/* 24 */     this.facing = 1;
/* 25 */     this.alive = true;
/* 26 */     this.world = null;
/* 27 */     this.width = 16;
/* 28 */     this.height = 16;
/* 29 */     this.type = type;
/*    */   }
/*    */   public int width; public int height; public int facing; public boolean alive; public MarioWorld world;
/*    */   public MarioSprite clone() {
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void added() {}
/*    */ 
/*    */   
/*    */   public void removed() {}
/*    */ 
/*    */   
/*    */   public int getMapX() {
/* 45 */     return (int)(this.x / 16.0F);
/*    */   }
/*    */   
/*    */   public int getMapY() {
/* 49 */     return (int)(this.y / 16.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Graphics og) {}
/*    */ 
/*    */   
/*    */   public void update() {}
/*    */ 
/*    */   
/*    */   public void collideCheck() {}
/*    */ 
/*    */   
/*    */   public void bumpCheck(int xTile, int yTile) {}
/*    */ 
/*    */   
/*    */   public boolean shellCollideCheck(Shell shell) {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void release(Mario mario) {}
/*    */   
/*    */   public boolean fireballCollideCheck(Fireball fireball) {
/* 74 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioSprite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */