/*    */ package agents.spencerSchumann;
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
/*    */   private Tiles tiles;
/*    */   private MarioState mario;
/*    */   private EnemySimulator enemySim;
/*    */   private boolean manualOverride = false;
/*    */   private PlanRunner planRunner;
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 21 */     this.tiles = new Tiles();
/* 22 */     this.mario = new MarioState();
/* 23 */     this.planRunner = new PlanRunner();
/* 24 */     this.enemySim = new EnemySimulator();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 29 */     float[] marioPos = model.getMarioFloatPos();
/* 30 */     this.tiles.addObservation(model);
/* 31 */     int mx = (int)(marioPos[0] / 16.0F);
/* 32 */     int my = (int)(marioPos[1] / 16.0F);
/*    */ 
/*    */     
/* 35 */     model.getClass(); model.getClass();
/* 36 */     model.getClass(); model.getClass(); int[][] scene = this.tiles.getScene(mx - 16 / 2, my - 16 / 2, 16, 16);
/* 37 */     this.mario.update(model);
/* 38 */     Scene sanitizedScene = new Scene(model, scene);
/* 39 */     this.enemySim.update(sanitizedScene);
/* 40 */     this.enemySim.update(model);
/*    */     
/* 42 */     boolean[] action = null;
/* 43 */     if (this.planRunner.isDone() || this.planRunner.isLastAction() || this.manualOverride) {
/* 44 */       MovementPlanner planner = new MovementPlanner(sanitizedScene, this.mario, this.enemySim.clone());
/* 45 */       PlanRunner plan = planner.planMovement();
/* 46 */       if (plan != null) {
/*    */         
/* 48 */         this.planRunner = plan;
/*    */       } else {
/* 50 */         action = new boolean[5];
/* 51 */         action[MarioActions.RIGHT.getValue()] = true;
/* 52 */         action[MarioActions.JUMP.getValue()] = !(
/* 53 */           !model.mayMarioJump() && model.isMarioOnGround()); action[MarioActions.SPEED.getValue()] = !(!model.mayMarioJump() && model.isMarioOnGround());
/*    */       } 
/*    */     } 
/* 56 */     if (action == null)
/* 57 */       action = this.planRunner.nextAction(); 
/* 58 */     return action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 63 */     return "SpencerShumannAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */