/*    */ package engine.helper;
/*    */ 
/*    */ public enum EventType {
/*  4 */   BUMP(1),
/*  5 */   STOMP_KILL(2),
/*  6 */   FIRE_KILL(3),
/*  7 */   SHELL_KILL(4),
/*  8 */   FALL_KILL(5),
/*  9 */   JUMP(6),
/* 10 */   LAND(7),
/* 11 */   COLLECT(8),
/* 12 */   HURT(9),
/* 13 */   KICK(10),
/* 14 */   LOSE(11),
/* 15 */   WIN(12);
/*    */   
/*    */   private int value;
/*    */   
/*    */   EventType(int newValue) {
/* 20 */     this.value = newValue;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 24 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\EventType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */