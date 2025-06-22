/*    */ package agents.robinBaumgarten;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import engine.helper.MarioActions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/*    */   private boolean[] action;
/*    */   public AStarTree tree;
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 18 */     this.action = new boolean[MarioActions.numberOfActions()];
/* 19 */     this.tree = new AStarTree();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 24 */     this.action = this.tree.optimise(model, timer);
/* 25 */     return this.action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 30 */     return "RobinBaumgartenAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\robinBaumgarten\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */