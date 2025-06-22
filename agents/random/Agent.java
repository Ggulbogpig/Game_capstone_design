/*    */ package agents.random;
/*    */ 
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioForwardModel;
/*    */ import engine.core.MarioTimer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Agent
/*    */   implements MarioAgent
/*    */ {
/*    */   private Random rnd;
/*    */   private ArrayList<boolean[]> choices;
/*    */   
/*    */   public void initialize(MarioForwardModel model, MarioTimer timer) {
/* 16 */     this.rnd = new Random();
/* 17 */     this.choices = (ArrayList)new ArrayList<>();
/*    */     
/* 19 */     this.choices.add(new boolean[] { false, true, true });
/* 20 */     this.choices.add(new boolean[] { false, true, true });
/* 21 */     this.choices.add(new boolean[] { false, true, true });
/* 22 */     this.choices.add(new boolean[] { false, true, true });
/* 23 */     this.choices.add(new boolean[] { false, true, true });
/* 24 */     this.choices.add(new boolean[] { false, true, true });
/* 25 */     this.choices.add(new boolean[] { false, true, true });
/* 26 */     this.choices.add(new boolean[] { false, true, true });
/*    */     
/* 28 */     this.choices.add(new boolean[] { false, true, true, true });
/* 29 */     this.choices.add(new boolean[] { false, true, true, true });
/* 30 */     this.choices.add(new boolean[] { false, true, true, true });
/* 31 */     this.choices.add(new boolean[] { false, true, true, true });
/* 32 */     this.choices.add(new boolean[] { false, true, true, true });
/* 33 */     this.choices.add(new boolean[] { false, true, true, true });
/* 34 */     this.choices.add(new boolean[] { false, true, true, true });
/* 35 */     this.choices.add(new boolean[] { false, true, true, true });
/*    */     
/* 37 */     this.choices.add(new boolean[] { false, true });
/* 38 */     this.choices.add(new boolean[] { false, true });
/* 39 */     this.choices.add(new boolean[] { false, true });
/* 40 */     this.choices.add(new boolean[] { false, true });
/*    */     
/* 42 */     this.choices.add(new boolean[] { false, true, true });
/* 43 */     this.choices.add(new boolean[] { false, true, true });
/* 44 */     this.choices.add(new boolean[] { false, true, true });
/* 45 */     this.choices.add(new boolean[] { false, true, true });
/*    */     
/* 47 */     this.choices.add(new boolean[] { true });
/*    */     
/* 49 */     this.choices.add(new boolean[] { true, true });
/*    */     
/* 51 */     this.choices.add(new boolean[] { true, true });
/*    */     
/* 53 */     this.choices.add(new boolean[] { true, true, true });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
/* 58 */     return this.choices.get(this.rnd.nextInt(this.choices.size()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAgentName() {
/* 63 */     return "RandomAgent";
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\random\Agent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */