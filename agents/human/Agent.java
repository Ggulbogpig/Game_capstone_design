/*    */ package agents.human;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import engine.helper.MarioActions;
/*    */ import java.awt.event.KeyAdapter;
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ public class Agent
/*    */   extends KeyAdapter implements MarioAgent {
/* 12 */   private boolean[] actions = null;
/*    */ 
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 16 */     this.actions = new boolean[MarioActions.numberOfActions()];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 21 */     return this.actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 26 */     return "HumanAgent";
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyPressed(KeyEvent e) {
/* 31 */     toggleKey(e.getKeyCode(), true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyReleased(KeyEvent e) {
/* 36 */     toggleKey(e.getKeyCode(), false);
/*    */   }
/*    */   
/*    */   private void toggleKey(int keyCode, boolean isPressed) {
/* 40 */     if (this.actions == null) {
/*    */       return;
/*    */     }
/* 43 */     switch (keyCode) {
/*    */       case 37:
/* 45 */         this.actions[MarioActions.LEFT.getValue()] = isPressed;
/*    */         break;
/*    */       case 39:
/* 48 */         this.actions[MarioActions.RIGHT.getValue()] = isPressed;
/*    */         break;
/*    */       case 40:
/* 51 */         this.actions[MarioActions.DOWN.getValue()] = isPressed;
/*    */         break;
/*    */       case 83:
/* 54 */         this.actions[MarioActions.JUMP.getValue()] = isPressed;
/*    */         break;
/*    */       case 65:
/* 57 */         this.actions[MarioActions.SPEED.getValue()] = isPressed;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\human\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */