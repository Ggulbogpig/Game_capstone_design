/*     */ package agents.spencerSchumann;
/*     */ 
/*     */ import engine.helper.MarioActions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MovementPlanner
/*     */ {
/*     */   Scene scene;
/*     */   MarioState mario;
/*     */   EnemySimulator enemySim;
/*     */   Edge targetFloor;
/*     */   public float[] projectedX;
/*     */   public float[] projectedY;
/*     */   
/*     */   MovementPlanner(Scene scene, MarioState mario, EnemySimulator enemySim) {
/*  29 */     this.scene = scene;
/*  30 */     this.mario = mario;
/*  31 */     this.enemySim = enemySim;
/*     */   }
/*     */   
/*     */   private int flightTimeForJump(int jumpTime, float height) {
/*  35 */     Scene simScene = new Scene(0.0F, 0.0F);
/*  36 */     simScene.floors.add(new Edge(-10.0F, height, 10.0F, height));
/*  37 */     MarioState simMario = new MarioState();
/*  38 */     simMario.mayJump = true;
/*  39 */     simMario.onGround = true;
/*  40 */     MotionSimulator sim = new MotionSimulator(simScene, simMario);
/*     */ 
/*     */     
/*  43 */     boolean[] jump = new boolean[5];
/*  44 */     jump[MarioActions.JUMP.getValue()] = true;
/*  45 */     for (int i = 0; i < jumpTime; i++) {
/*  46 */       sim.update(jump);
/*     */     }
/*  48 */     boolean[] coast = new boolean[5];
/*     */     while (true) {
/*  50 */       sim.update(coast);
/*  51 */       if (sim.mario.onGround)
/*  52 */         return sim.getTicks(); 
/*  53 */       if (sim.mario.vy > 0.0F && sim.mario.y > height)
/*  54 */         return -1; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int ticksToPos(float pos) {
/*  59 */     Scene simScene = new Scene(0.0F, 0.0F);
/*  60 */     simScene.floors.add(new Edge(-1000.0F, 1.0F, pos, 1.0F));
/*  61 */     MarioState simMario = new MarioState();
/*  62 */     simMario.vx = this.mario.vx;
/*  63 */     MotionSimulator sim = new MotionSimulator(simScene, simMario);
/*  64 */     sim.leftWorldEdge = false;
/*     */     
/*  66 */     boolean[] run = new boolean[5];
/*  67 */     run[MarioActions.RIGHT.getValue()] = true;
/*  68 */     run[MarioActions.SPEED.getValue()] = true;
/*     */     while (true) {
/*  70 */       sim.update(run);
/*  71 */       if (sim.mario.x >= pos)
/*  72 */         return sim.getTicks(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private float posFromTicks(int ticks) {
/*  77 */     Scene simScene = new Scene(0.0F, 0.0F);
/*  78 */     simScene.floors.add(new Edge(-1000.0F, 1.0F, ticks * 100.0F, 1.0F));
/*  79 */     MarioState simMario = new MarioState();
/*  80 */     simMario.vx = this.mario.vx;
/*  81 */     MotionSimulator sim = new MotionSimulator(simScene, simMario);
/*  82 */     sim.leftWorldEdge = false;
/*     */ 
/*     */     
/*  85 */     boolean[] run = new boolean[5];
/*  86 */     run[MarioActions.RIGHT.getValue()] = true;
/*  87 */     run[MarioActions.SPEED.getValue()] = true;
/*  88 */     for (int i = 0; i < ticks; i++) {
/*  89 */       sim.update(run);
/*     */     }
/*  91 */     return sim.mario.x;
/*     */   }
/*     */   
/*     */   private boolean checkPlan(PlanRunner plan, Edge targetFloor) {
/*  95 */     MotionSimulator sim = new MotionSimulator(this.scene, this.mario);
/*  96 */     this.projectedX = new float[plan.getLength() + 1];
/*  97 */     this.projectedY = new float[plan.getLength() + 1];
/*  98 */     this.targetFloor = targetFloor;
/*  99 */     EnemySimulator es = this.enemySim.clone();
/* 100 */     while (!plan.isDone()) {
/* 101 */       es.update(this.scene);
/* 102 */       sim.update(plan.nextAction());
/* 103 */       this.projectedX[plan.getIndex() - 1] = sim.mario.x;
/* 104 */       this.projectedY[plan.getIndex() - 1] = sim.mario.y;
/* 105 */       for (Enemy e : es.enemies) {
/* 106 */         if (sim.mario.x >= e.x - e.width / 2.0F - 4.0F && 
/* 107 */           sim.mario.x <= e.x + e.width / 2.0F + 4.0F && 
/* 108 */           sim.mario.y >= e.y - e.height && 
/* 109 */           sim.mario.y - sim.mario.height <= e.y) {
/* 110 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 116 */     plan.rewind();
/* 117 */     return (sim.mario.x > targetFloor.x1 - 4.0F && 
/* 118 */       sim.mario.x < targetFloor.x2 + 4.0F && 
/* 119 */       sim.mario.y == targetFloor.y1 - 1.0F);
/*     */   }
/*     */   
/*     */   private PlanRunner planJump(Edge currentFloor, Edge targetFloor) {
/* 123 */     float ydiff = targetFloor.y1 - currentFloor.y1;
/*     */ 
/*     */     
/* 126 */     float xdiff = targetFloor.x1 - this.mario.x + 4.0F;
/* 127 */     if (xdiff < 0.0F) {
/* 128 */       xdiff = targetFloor.x2 - this.mario.x - 3.0F;
/*     */     }
/* 130 */     int ticks = ticksToPos(xdiff);
/*     */     
/* 132 */     int flightTime = 0;
/*     */     
/* 134 */     for (int jumpTime = 0; jumpTime <= 7; jumpTime++) {
/* 135 */       flightTime = flightTimeForJump(jumpTime, ydiff);
/* 136 */       if (flightTime < 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 140 */       PlanRunner plan = new PlanRunner();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       if (ticks <= flightTime) {
/*     */ 
/*     */         
/* 149 */         plan.addKey(MarioActions.SPEED.getValue());
/* 150 */         plan.addKey(MarioActions.JUMP.getValue(), 0, jumpTime);
/* 151 */         plan.addKey(MarioActions.RIGHT.getValue(), flightTime - ticks, ticks);
/*     */       } else {
/* 153 */         int timeUntilJump = ticks - flightTime + 1;
/* 154 */         float posUntilJump = posFromTicks(timeUntilJump);
/* 155 */         if (posUntilJump + this.mario.x >= currentFloor.x2 + 4.0F) {
/*     */           continue;
/*     */         }
/*     */         
/* 159 */         plan.addKey(MarioActions.SPEED.getValue());
/* 160 */         plan.addKey(MarioActions.RIGHT.getValue(), 0, ticks);
/* 161 */         plan.addKey(MarioActions.JUMP.getValue(), timeUntilJump, jumpTime);
/*     */       } 
/*     */ 
/*     */       
/* 165 */       if (checkPlan(plan, targetFloor)) {
/* 166 */         return plan;
/*     */       }
/*     */       continue;
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */   
/*     */   class BestTarget implements Comparator<Edge> {
/*     */     public int compare(Edge o1, Edge o2) {
/* 175 */       if (o1.x2 > o2.x2)
/* 176 */         return -1; 
/* 177 */       if (o1.x2 < o2.x2)
/* 178 */         return 1; 
/* 179 */       if (o1.y1 > o2.y1)
/* 180 */         return -1; 
/* 181 */       if (o1.y1 < o2.y1)
/* 182 */         return 1; 
/* 183 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<Edge> findTargetFloors(Edge currentFloor) {
/* 190 */     ArrayList<Edge> targets = new ArrayList<>();
/* 191 */     Collections.sort(this.scene.floors, new BestTarget());
/* 192 */     for (Edge e : this.scene.floors) {
/*     */       
/* 194 */       if (e.x2 > this.mario.x)
/*     */       {
/* 196 */         if (e.y1 + 64.0F >= currentFloor.y1)
/*     */         {
/*     */           
/* 199 */           targets.add(e);
/*     */         }
/*     */       }
/*     */     } 
/* 203 */     return targets;
/*     */   }
/*     */   
/*     */   private Edge findCurrentFloor() {
/* 207 */     for (Edge e : this.scene.floors) {
/* 208 */       if (this.mario.y == e.y1 - 1.0F && 
/* 209 */         this.mario.x >= e.x1 - 4.0F && 
/* 210 */         this.mario.x <= e.x2 + 4.0F) {
/* 211 */         return e;
/*     */       }
/*     */     } 
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PlanRunner planMovement() {
/* 220 */     Edge currentFloor = findCurrentFloor();
/* 221 */     if (currentFloor == null)
/*     */     {
/* 223 */       return null;
/*     */     }
/* 225 */     ArrayList<Edge> targetFloors = findTargetFloors(currentFloor);
/* 226 */     if (!targetFloors.isEmpty()) {
/* 227 */       for (Edge target : targetFloors) {
/* 228 */         if (target == currentFloor)
/*     */           continue; 
/* 230 */         PlanRunner plan = planJump(currentFloor, target);
/* 231 */         if (plan != null)
/*     */         {
/*     */           
/* 234 */           return plan;
/*     */         }
/*     */       } 
/*     */     }
/* 238 */     return null;
/*     */   }
/*     */   
/*     */   public Edge getTargetFloor() {
/* 242 */     return this.targetFloor;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\MovementPlanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */