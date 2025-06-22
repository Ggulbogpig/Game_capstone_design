/*    */ package agents.spencerSchumann;
/*    */ 
/*    */ import engine.core.MarioForwardModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MarioState
/*    */ {
/*    */   public float x;
/*    */   public float y;
/*    */   public float vx;
/*    */   public float vy;
/*    */   public int mode;
/*    */   public float height;
/*    */   public boolean onGround;
/*    */   public boolean mayJump;
/*    */   public int jumpTime;
/*    */   private boolean first = true;
/*    */   
/*    */   public void update(MarioForwardModel model) {
/* 23 */     this.mode = model.getMarioMode();
/* 24 */     if (this.mode > 0) {
/* 25 */       this.height = 24.0F;
/*    */     } else {
/* 27 */       this.height = 12.0F;
/*    */     } 
/* 29 */     this.onGround = model.isMarioOnGround();
/* 30 */     this.mayJump = model.mayMarioJump();
/*    */     
/* 32 */     float[] pos = model.getMarioFloatPos();
/* 33 */     if (this.first) {
/* 34 */       this.first = false;
/* 35 */       this.x = pos[0];
/* 36 */       this.y = pos[1];
/*    */     } 
/* 38 */     float newVx = pos[0] - this.x;
/* 39 */     float newVy = pos[1] - this.y;
/* 40 */     this.vx = newVx;
/* 41 */     this.vy = newVy;
/* 42 */     this.x = pos[0];
/* 43 */     this.y = pos[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public MarioState clone() {
/* 48 */     MarioState m = new MarioState();
/* 49 */     m.x = this.x;
/* 50 */     m.y = this.y;
/* 51 */     m.vx = this.vx;
/* 52 */     m.vy = this.vy;
/* 53 */     m.first = this.first;
/* 54 */     m.mode = this.mode;
/* 55 */     m.height = this.height;
/* 56 */     m.onGround = this.onGround;
/* 57 */     m.mayJump = this.mayJump;
/* 58 */     m.jumpTime = this.jumpTime;
/* 59 */     return m;
/*    */   }
/*    */   
/*    */   public boolean equals(MarioState other) {
/* 63 */     return (this.x == other.x && 
/* 64 */       this.y == other.y && 
/* 65 */       this.first == other.first && 
/* 66 */       this.mode == other.mode && 
/* 67 */       this.height == other.height && 
/* 68 */       this.jumpTime == other.jumpTime && 
/* 69 */       this.mayJump == other.mayJump && 
/* 70 */       this.onGround == other.onGround);
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\MarioState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */