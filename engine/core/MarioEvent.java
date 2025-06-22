/*    */ package engine.core;
/*    */ 
/*    */ import engine.helper.EventType;
/*    */ 
/*    */ public class MarioEvent {
/*    */   private EventType eventType;
/*    */   private int eventParam;
/*    */   private float marioX;
/*    */   private float marioY;
/*    */   private int marioState;
/*    */   private int time;
/*    */   
/*    */   public MarioEvent(EventType eventType) {
/* 14 */     this.eventType = eventType;
/* 15 */     this.eventParam = 0;
/* 16 */     this.marioX = 0.0F;
/* 17 */     this.marioY = 0.0F;
/* 18 */     this.marioState = 0;
/* 19 */     this.time = 0;
/*    */   }
/*    */   
/*    */   public MarioEvent(EventType eventType, int eventParam) {
/* 23 */     this.eventType = eventType;
/* 24 */     this.eventParam = eventParam;
/* 25 */     this.marioX = 0.0F;
/* 26 */     this.marioY = 0.0F;
/* 27 */     this.marioState = 0;
/* 28 */     this.time = 0;
/*    */   }
/*    */   
/*    */   public MarioEvent(EventType eventType, float x, float y, int state, int time) {
/* 32 */     this.eventType = eventType;
/* 33 */     this.eventParam = 0;
/* 34 */     this.marioX = x;
/* 35 */     this.marioY = y;
/* 36 */     this.marioState = state;
/* 37 */     this.time = time;
/*    */   }
/*    */   
/*    */   public MarioEvent(EventType eventType, int eventParam, float x, float y, int state, int time) {
/* 41 */     this.eventType = eventType;
/* 42 */     this.eventParam = eventParam;
/* 43 */     this.marioX = x;
/* 44 */     this.marioY = y;
/* 45 */     this.marioState = state;
/* 46 */     this.time = time;
/*    */   }
/*    */   
/*    */   public int getEventType() {
/* 50 */     return this.eventType.getValue();
/*    */   }
/*    */   
/*    */   public int getEventParam() {
/* 54 */     return this.eventParam;
/*    */   }
/*    */   
/*    */   public float getMarioX() {
/* 58 */     return this.marioX;
/*    */   }
/*    */   
/*    */   public float getMarioY() {
/* 62 */     return this.marioY;
/*    */   }
/*    */   
/*    */   public int getMarioState() {
/* 66 */     return this.marioState;
/*    */   }
/*    */   
/*    */   public int getTime() {
/* 70 */     return this.time;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 75 */     MarioEvent otherEvent = (MarioEvent)obj;
/* 76 */     return (this.eventType == otherEvent.eventType && (
/* 77 */       this.eventParam == 0 || this.eventParam == otherEvent.eventParam));
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */