/*    */ package engine.helper;
/*    */ 
/*    */ public enum MarioActions {
/*  4 */   LEFT(0, "Left"),
/*  5 */   RIGHT(1, "Right"),
/*  6 */   DOWN(2, "Down"),
/*  7 */   SPEED(3, "Speed"),
/*  8 */   JUMP(4, "Jump");
/*    */   
/*    */   private int value;
/*    */   private String name;
/*    */   
/*    */   MarioActions(int newValue, String newName) {
/* 14 */     this.value = newValue;
/* 15 */     this.name = newName;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 19 */     return this.value;
/*    */   }
/*    */   
/*    */   public String getString() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public static int numberOfActions() {
/* 27 */     return (values()).length;
/*    */   }
/*    */   
/*    */   public static MarioActions getAction(int value) {
/* 31 */     return values()[value];
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\MarioActions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */