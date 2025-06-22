/*    */ package agents.runner;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import engine.helper.MarioActions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/*    */   private boolean[] action;
/*    */   private AStarTree tree;
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 20 */     this.action = new boolean[MarioActions.numberOfActions()];
/* 21 */     this.tree = new AStarTree();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 26 */     this.action = this.tree.optimise(model, timer);
/* 27 */     return this.action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 32 */     return "RunnerAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\runner\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */