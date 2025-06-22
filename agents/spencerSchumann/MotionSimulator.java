/*     */ package agents.spencerSchumann;
/*     */ 
/*     */ import engine.helper.MarioActions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MotionSimulator
/*     */ {
/*     */   private Scene scene;
/*     */   public MarioState mario;
/*     */   public boolean collision;
/*     */   private int ticks;
/*     */   public boolean leftWorldEdge = true;
/*     */   
/*     */   public MotionSimulator(Scene scene, MarioState mario) {
/*  22 */     this.scene = scene.clone();
/*  23 */     this.mario = mario.clone();
/*     */   }
/*     */   
/*     */   public void updateScene(Scene scene) {
/*  27 */     this.scene.update(scene);
/*     */   }
/*     */   
/*     */   private void handleHorizontalInput(boolean[] action) {
/*  31 */     float xSpeed = action[MarioActions.SPEED.getValue()] ? 1.2F : 0.6F;
/*  32 */     if (action[MarioActions.LEFT.getValue()])
/*  33 */       this.mario.vx -= xSpeed; 
/*  34 */     if (action[MarioActions.RIGHT.getValue()]) {
/*  35 */       this.mario.vx += xSpeed;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleJumpInput(boolean[] action) {
/*  43 */     this.mario.vy *= 0.85F;
/*  44 */     if (!this.mario.onGround) {
/*  45 */       this.mario.vy += 3.0F;
/*     */     }
/*     */     
/*  48 */     if (!action[MarioActions.JUMP.getValue()]) {
/*  49 */       this.mario.jumpTime = 0;
/*  50 */       if (this.mario.onGround) {
/*  51 */         this.mario.mayJump = true;
/*     */       }
/*     */     } 
/*  54 */     if (action[MarioActions.JUMP.getValue()]) {
/*  55 */       if (this.mario.jumpTime > 0) {
/*  56 */         this.mario.vy = -1.9F * this.mario.jumpTime--;
/*  57 */       } else if (this.mario.mayJump) {
/*  58 */         this.mario.jumpTime = 7;
/*  59 */         this.mario.vy = -1.9F * this.mario.jumpTime;
/*     */       } 
/*  61 */       this.mario.mayJump = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(boolean[] action) {
/*  67 */     handleHorizontalInput(action);
/*  68 */     handleJumpInput(action);
/*     */     
/*  70 */     moveHorizontally();
/*  71 */     moveVertically();
/*     */     
/*  73 */     this.ticks++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float goofyAdd(float a, float b) {
/*  79 */     while (b > 8.0F) {
/*  80 */       b -= 8.0F;
/*  81 */       a += 8.0F;
/*     */     } 
/*  83 */     while (b < -8.0F) {
/*  84 */       b += 8.0F;
/*  85 */       a -= 8.0F;
/*     */     } 
/*  87 */     return a + b;
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveHorizontally() {
/*  92 */     if (Math.abs(this.mario.vx) < 0.5F) {
/*  93 */       this.mario.vx = 0.0F;
/*     */     }
/*  95 */     float newX = goofyAdd(this.mario.x, this.mario.vx);
/*     */     
/*  97 */     for (Edge e : this.scene.walls) {
/*     */ 
/*     */ 
/*     */       
/* 101 */       if (e.y1 <= this.mario.y && 
/* 102 */         e.y2 >= this.mario.y - this.mario.height) {
/*     */         
/* 104 */         if (this.mario.x + 4.0F <= e.x1 && e.x1 <= newX + 4.0F) {
/* 105 */           this.mario.x = e.x1 - 5.0F;
/* 106 */           this.mario.vx = 0.0F;
/* 107 */           this.collision = true;
/*     */           
/*     */           return;
/*     */         } 
/* 111 */         if (newX - 4.0F <= e.x1 && e.x1 <= this.mario.x - 4.0F) {
/* 112 */           this.mario.x = e.x1 + 4.0F;
/* 113 */           this.mario.vx = 0.0F;
/* 114 */           this.collision = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 119 */     this.mario.x = newX;
/* 120 */     this.mario.vx *= 0.89F;
/*     */     
/* 122 */     if (this.leftWorldEdge && this.mario.x < 0.0F) {
/* 123 */       this.mario.x = 0.0F;
/* 124 */       this.mario.vx = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveVertically() {
/* 130 */     float newY = goofyAdd(this.mario.y, this.mario.vy);
/*     */     
/* 132 */     if (this.mario.vy >= 0.0F) {
/* 133 */       for (Edge e : this.scene.floors) {
/*     */         
/* 135 */         if (e.x1 < this.mario.x + 4.0F && 
/* 136 */           e.x2 > this.mario.x - 4.0F && 
/* 137 */           this.mario.y <= e.y1 && e.y1 - 1.0F <= newY) {
/* 138 */           this.mario.y = e.y1 - 1.0F;
/* 139 */           this.mario.onGround = true;
/* 140 */           this.mario.jumpTime = 0;
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 146 */     } else if (this.mario.vy < 0.0F) {
/* 147 */       for (Edge e : this.scene.ceilings) {
/*     */         
/* 149 */         if (e.x1 < this.mario.x + 4.0F && 
/* 150 */           e.x2 > this.mario.x - 4.0F && 
/* 151 */           this.mario.y - this.mario.height >= e.y1 && 
/* 152 */           e.y1 >= newY - this.mario.height) {
/* 153 */           this.mario.y = e.y1 + this.mario.height;
/* 154 */           this.mario.vy = 0.0F;
/* 155 */           this.mario.jumpTime = 0;
/* 156 */           this.collision = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     this.mario.onGround = false;
/* 162 */     this.mario.mayJump = false;
/* 163 */     this.mario.y = newY;
/*     */   }
/*     */   
/*     */   public Scene getScene() {
/* 167 */     return this.scene;
/*     */   }
/*     */   
/*     */   public float getX() {
/* 171 */     return this.mario.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/* 175 */     this.mario.x = x;
/*     */   }
/*     */   
/*     */   public float getVX() {
/* 179 */     return this.mario.vx;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 183 */     return this.mario.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/* 187 */     this.mario.y = y;
/*     */   }
/*     */   
/*     */   public int getTicks() {
/* 191 */     return this.ticks;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\MotionSimulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */