/*    */ package engine.helper;
/*    */ 
/*    */ public enum GameStatus {
/*  4 */   RUNNING("RUNNING"),
/*  5 */   WIN("WIN"),
/*  6 */   LOSE("LOSE"),
/*  7 */   TIME_OUT("TIME_OUT");
/*    */   
/*    */   private String name;
/*    */   
/*    */   GameStatus(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 16 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\GameStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */