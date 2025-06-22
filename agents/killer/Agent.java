/*    */ package agents.killer;
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
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/*    */   private boolean[] action;
/*    */   private AStarTree tree;
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 21 */     this.action = new boolean[MarioActions.numberOfActions()];
/* 22 */     this.tree = new AStarTree();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 28 */     this.action = this.tree.optimise(model, timer);
/* 29 */     return this.action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 34 */     return "KillerAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\killer\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */