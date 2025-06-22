/*    */ package agents.doNothing;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import engine.helper.MarioActions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {}
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 16 */     return new boolean[MarioActions.numberOfActions()];
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 21 */     return "DoNothingAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\doNothing\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */