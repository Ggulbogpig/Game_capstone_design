/*    */ package engine.core;
/*    */ 
/*    */ public class MarioAgentEvent {
/*    */   private boolean[] actions;
/*    */   private float marioX;
/*    */   private float marioY;
/*    */   private int marioState;
/*    */   private boolean marioOnGround;
/*    */   private int time;
/*    */   
/*    */   public MarioAgentEvent(boolean[] actions, float marioX, float marioY, int marioState, boolean marioOnGround, int time) {
/* 12 */     this.actions = actions;
/* 13 */     this.marioX = marioX;
/* 14 */     this.marioY = marioY;
/* 15 */     this.marioState = marioState;
/* 16 */     this.marioOnGround = marioOnGround;
/* 17 */     this.time = time;
/*    */   }
/*    */   
/*    */   public boolean[] getActions() {
/* 21 */     return this.actions;
/*    */   }
/*    */   
/*    */   public float getMarioX() {
/* 25 */     return this.marioX;
/*    */   }
/*    */   
/*    */   public float getMarioY() {
/* 29 */     return this.marioY;
/*    */   }
/*    */   
/*    */   public int getMarioState() {
/* 33 */     return this.marioState;
/*    */   }
/*    */   
/*    */   public boolean getMarioOnGround() {
/* 37 */     return this.marioOnGround;
/*    */   }
/*    */   
/*    */   public int getTime() {
/* 41 */     return this.time;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioAgentEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */