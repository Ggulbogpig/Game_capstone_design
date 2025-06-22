/*    */ package agents.sergeyKarakovskiy;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import engine.helper.MarioActions;
/*    */ 
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/* 12 */   private boolean[] actions = null;
/*    */ 
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 16 */     this.actions = new boolean[MarioActions.numberOfActions()];
/* 17 */     this.actions[MarioActions.RIGHT.getValue()] = true;
/* 18 */     this.actions[MarioActions.SPEED.getValue()] = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 23 */     this.actions[MarioActions.JUMP.getValue()] = !(!model.mayMarioJump() && model.isMarioOnGround()); this.actions[MarioActions.SPEED.getValue()] = !(!model.mayMarioJump() && model.isMarioOnGround());
/* 24 */     return this.actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 29 */     return "SergeyKarakovskiyAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\sergeyKarakovskiy\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */