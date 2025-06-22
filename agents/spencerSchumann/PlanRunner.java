/*    */ package agents.spencerSchumann;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class PlanRunner
/*    */ {
/*    */   private int index;
/*    */   private int maxTime;
/*    */   
/*    */   private class Event {
/*    */     public int key;
/*    */     public boolean pressed;
/*    */     
/*    */     Event(int key, boolean pressed) {
/* 16 */       this.key = key;
/* 17 */       this.pressed = pressed;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 23 */   private boolean[] action = new boolean[5];
/*    */   private HashMap<Integer, ArrayList<Event>> events;
/*    */   
/*    */   PlanRunner() {
/* 27 */     this.maxTime = -1;
/* 28 */     this.events = new HashMap<>();
/* 29 */     rewind();
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/* 33 */     return (this.index > this.maxTime);
/*    */   }
/*    */   
/*    */   public boolean isLastAction() {
/* 37 */     return (this.index == this.maxTime);
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 41 */     return this.index;
/*    */   }
/*    */   
/*    */   public int getLength() {
/* 45 */     return this.maxTime;
/*    */   }
/*    */   
/*    */   public void rewind() {
/* 49 */     this.index = 0;
/*    */   }
/*    */   
/*    */   public void addKey(int key) {
/* 53 */     addKey(key, 0);
/*    */   }
/*    */   
/*    */   public void addKey(int key, int timeStep) {
/* 57 */     addKeyEvent(key, timeStep, true);
/*    */   }
/*    */   
/*    */   public void addKey(int key, int timeStep, int duration) {
/* 61 */     addKeyEvent(key, timeStep, true);
/* 62 */     addKeyEvent(key, timeStep + duration, false);
/*    */   }
/*    */   
/*    */   private void addKeyEvent(int key, int timeStep, boolean pressed) {
/* 66 */     ArrayList<Event> keys = this.events.get(Integer.valueOf(timeStep));
/* 67 */     if (keys == null) {
/* 68 */       keys = new ArrayList<>();
/* 69 */       this.events.put(Integer.valueOf(timeStep), keys);
/*    */     } 
/* 71 */     keys.add(new Event(key, pressed));
/* 72 */     this.maxTime = Math.max(this.maxTime, timeStep);
/*    */   }
/*    */   
/*    */   public boolean[] nextAction() {
/* 76 */     ArrayList<Event> keys = this.events.get(Integer.valueOf(this.index));
/* 77 */     if (keys != null) {
/* 78 */       for (Event e : keys) {
/* 79 */         this.action[e.key] = e.pressed;
/*    */       }
/*    */     }
/* 82 */     this.index++;
/* 83 */     return this.action;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\PlanRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */