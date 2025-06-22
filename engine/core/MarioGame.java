/*     */ package engine.core;
/*     */ 
/*     */ import agents.human.Agent;
/*     */ import engine.helper.GameStatus;
/*     */ import engine.helper.MarioActions;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.image.VolatileImage;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JFrame;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarioGame
/*     */ {
/*     */   public static final long maxTime = 40L;
/*     */   public static final long graceTime = 10L;
/*     */   public static final int width = 256;
/*     */   public static final int height = 256;
/*     */   public static final int tileWidth = 16;
/*     */   public static final int tileHeight = 16;
/*     */   public static final boolean verbose = false;
/*     */   public boolean pause = false;
/*     */   private MarioEvent[] killEvents;
/*  55 */   private JFrame window = null;
/*  56 */   private MarioRender render = null;
/*  57 */   private MarioAgent agent = null;
/*  58 */   private MarioWorld world = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioGame() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioGame(MarioEvent[] killEvents) {
/*  73 */     this.killEvents = killEvents;
/*     */   }
/*     */   
/*     */   private int getDelay(int fps) {
/*  77 */     if (fps <= 0) {
/*  78 */       return 0;
/*     */     }
/*  80 */     return 1000 / fps;
/*     */   }
/*     */   
/*     */   private void setAgent(MarioAgent agent) {
/*  84 */     this.agent = agent;
/*  85 */     if (agent instanceof KeyAdapter) {
/*  86 */       this.render.addKeyListener((KeyAdapter)this.agent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioResult playGame(String level, int timer) {
/*  98 */     return runGame((MarioAgent)new Agent(), level, timer, 0, true, 30, 2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioResult playGame(String level, int timer, int marioState) {
              System.out.println(">>> runGame 시작");
/* 110 */     MarioResult result = runGame((MarioAgent)new Agent(), level, timer, marioState, true, 30, 2.0F);
              System.out.println(">>> runGame 끝");
              return result;
/*     */   }
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
/*     */   public MarioResult playGame(String level, int timer, int marioState, int fps) {
/* 123 */     return runGame((MarioAgent)new Agent(), level, timer, marioState, true, fps, 2.0F);
/*     */   }
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
/*     */   public MarioResult playGame(String level, int timer, int marioState, int fps, float scale) {
/* 137 */     return runGame((MarioAgent)new Agent(), level, timer, marioState, true, fps, scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MarioResult runGame(MarioAgent agent, String level, int timer) {
/* 149 */     return runGame(agent, level, timer, 0, false, 0, 2.0F);
/*     */   }
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
/*     */   public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState) {
/* 162 */     return runGame(agent, level, timer, marioState, false, 0, 2.0F);
/*     */   }
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
/*     */   public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals) {
/* 176 */     return runGame(agent, level, timer, marioState, visuals, visuals ? 30 : 0, 2.0F);
/*     */   }
/*     */   
/*     */   public MarioResult runGameWithRealTimeLimits(MarioAgent agent, String level, int timer, int marioState, boolean visuals, long realTimeLimitMs, int fps) {
/* 180 */     if (visuals) {
/* 181 */       this.window = new JFrame("Mario AI Framework");
/* 182 */       this.render = new MarioRender(2.0F);
/* 183 */       this.window.setContentPane(this.render);
/* 184 */       this.window.pack();
/* 185 */       this.window.setResizable(false);
/* 186 */       this.window.setDefaultCloseOperation(3);
/* 187 */       this.render.init();
/* 188 */       this.window.setVisible(true);
/*     */     } 
/* 190 */     setAgent(agent);
/* 191 */     return gameLoop(level, timer, marioState, visuals, fps, realTimeLimitMs);
/*     */   }
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
/*     */   
/*     */   public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps) {
/* 207 */     return runGame(agent, level, timer, marioState, visuals, fps, 2.0F);
/*     */   }
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
/*     */   
/*     */   public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps, float scale) {
/* 223 */     if (visuals) {
/* 224 */       this.window = new JFrame("Mario AI Framework");
/* 225 */       this.render = new MarioRender(scale);
/* 226 */       this.window.setContentPane(this.render);
/* 227 */       this.window.pack();
/* 228 */       this.window.setResizable(false);
/* 229 */       this.window.setDefaultCloseOperation(3);
/* 230 */       this.render.init();
/* 231 */       this.window.setVisible(true);
/*     */     } 
/* 233 */     setAgent(agent);
/* 234 */     return gameLoop(level, timer, marioState, visuals, fps);
/*     */   }
/*     */   
/*     */   private MarioResult gameLoop(String level, int timer, int marioState, boolean visual, int fps) {
/* 238 */     this.world = new MarioWorld(this.killEvents);
/* 239 */     this.world.visuals = visual;
/* 240 */     this.world.initializeLevel(level, 1000 * timer);
/* 241 */     if (visual) {
/* 242 */       this.world.initializeVisuals(this.render.getGraphicsConfiguration());
/*     */     }
/* 244 */     this.world.mario.isLarge = (marioState > 0);
/* 245 */     this.world.mario.isFire = (marioState > 1);
/* 246 */     this.world.update(new boolean[MarioActions.numberOfActions()]);
/* 247 */     long currentTime = System.currentTimeMillis();
/*     */ 
/*     */     
/* 250 */     VolatileImage renderTarget = null;
/* 251 */     Graphics backBuffer = null;
/* 252 */     Graphics currentBuffer = null;
/* 253 */     if (visual) {
/* 254 */       renderTarget = this.render.createVolatileImage(256, 256);
/* 255 */       backBuffer = this.render.getGraphics();
/* 256 */       currentBuffer = renderTarget.getGraphics();
/* 257 */       this.render.addFocusListener(this.render);
/*     */     } 
/*     */     
/* 260 */     MarioTimer agentTimer = new MarioTimer(40L);
/* 261 */     this.agent.initialize(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */     
/* 263 */     ArrayList<MarioEvent> gameEvents = new ArrayList<>();
/* 264 */     ArrayList<MarioAgentEvent> agentEvents = new ArrayList<>();
/* 265 */     while (this.world.gameStatus == GameStatus.RUNNING) {
/* 266 */       if (!this.pause) {
/*     */         
/* 268 */         agentTimer = new MarioTimer(40L);
/* 269 */         boolean[] actions = this.agent.getActions(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 277 */         this.world.update(actions);
/* 278 */         gameEvents.addAll(this.world.lastFrameEvents);
/* 279 */         agentEvents.add(new MarioAgentEvent(actions, this.world.mario.x, 
/* 280 */               this.world.mario.y, (this.world.mario.isLarge ? 1 : 0) + (this.world.mario.isFire ? 1 : 0), 
/* 281 */               this.world.mario.onGround, this.world.currentTick));
/*     */       } 
/*     */ 
/*     */       
/* 285 */       if (visual) {
/* 286 */         this.render.renderWorld(this.world, renderTarget, backBuffer, currentBuffer);
/*     */       }
/*     */       
/* 289 */       if (getDelay(fps) > 0) {
/*     */         try {
/* 291 */           currentTime += getDelay(fps);
/* 292 */           Thread.sleep(Math.max(0L, currentTime - System.currentTimeMillis()));
/* 293 */         } catch (InterruptedException e) {
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/* 298 */     return new MarioResult(this.world, gameEvents, agentEvents);
/*     */   }
/*     */   
/*     */   private MarioResult gameLoop(String level, int timer, int marioState, boolean visual, int fps, long realTimeLimitMs) {
/* 302 */     this.world = new MarioWorld(this.killEvents);
/* 303 */     this.world.visuals = visual;
/* 304 */     this.world.initializeLevel(level, 1000 * timer);
/* 305 */     if (visual) {
/* 306 */       this.world.initializeVisuals(this.render.getGraphicsConfiguration());
/*     */     }
/* 308 */     this.world.mario.isLarge = (marioState > 0);
/* 309 */     this.world.mario.isFire = (marioState > 1);
/* 310 */     this.world.update(new boolean[MarioActions.numberOfActions()]);
/* 311 */     long currentTime = System.currentTimeMillis();
/*     */ 
/*     */     
/* 314 */     VolatileImage renderTarget = null;
/* 315 */     Graphics backBuffer = null;
/* 316 */     Graphics currentBuffer = null;
/* 317 */     if (visual) {
/* 318 */       renderTarget = this.render.createVolatileImage(256, 256);
/* 319 */       backBuffer = this.render.getGraphics();
/* 320 */       currentBuffer = renderTarget.getGraphics();
/* 321 */       this.render.addFocusListener(this.render);
/*     */     } 
/*     */     
/* 324 */     MarioTimer agentTimer = new MarioTimer(40L);
/* 325 */     this.agent.initialize(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */     
/* 327 */     ArrayList<MarioEvent> gameEvents = new ArrayList<>();
/* 328 */     ArrayList<MarioAgentEvent> agentEvents = new ArrayList<>();
/* 329 */     long startTimeMs = System.currentTimeMillis();
/* 330 */     while (this.world.gameStatus == GameStatus.RUNNING) {
/* 331 */       if (!this.pause) {
/*     */         
/* 333 */         agentTimer = new MarioTimer(40L);
/* 334 */         boolean[] actions = this.agent.getActions(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 342 */         this.world.update(actions);
/* 343 */         gameEvents.addAll(this.world.lastFrameEvents);
/* 344 */         agentEvents.add(new MarioAgentEvent(actions, this.world.mario.x, 
/* 345 */               this.world.mario.y, (this.world.mario.isLarge ? 1 : 0) + (this.world.mario.isFire ? 1 : 0), 
/* 346 */               this.world.mario.onGround, this.world.currentTick));
/*     */       } 
/*     */ 
/*     */       
/* 350 */       if (visual) {
/* 351 */         this.render.renderWorld(this.world, renderTarget, backBuffer, currentBuffer);
/*     */       }
/*     */       
/* 354 */       if (getDelay(fps) > 0) {
/*     */         try {
/* 356 */           currentTime += getDelay(fps);
/* 357 */           Thread.sleep(Math.max(0L, currentTime - System.currentTimeMillis()));
/* 358 */         } catch (InterruptedException e) {
/*     */           break;
/*     */         } 
/*     */       }
/*     */       
/* 363 */       if (System.currentTimeMillis() - startTimeMs >= realTimeLimitMs) {
/* 364 */         this.world.gameStatus = GameStatus.TIME_OUT;
/*     */       }
/*     */     } 
/* 367 */     return new MarioResult(this.world, gameEvents, agentEvents);
/*     */   }
/*     */   
/*     */   public MarioResult runWithRealTimeSuspension(String level, MarioAgent agent, int timer, float k, long b) {
/* 371 */     setAgent(agent);
/* 372 */     this.world = new MarioWorld(this.killEvents);
/* 373 */     this.world.visuals = false;
/* 374 */     this.world.initializeLevel(level, 1000 * timer);
/* 375 */     this.world.mario.isLarge = false;
/* 376 */     this.world.mario.isFire = false;
/* 377 */     this.world.update(new boolean[MarioActions.numberOfActions()]);
/* 378 */     MarioTimer agentTimer = new MarioTimer(40L);
/* 379 */     this.agent.initialize(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */     
/* 381 */     ArrayList<MarioEvent> gameEvents = new ArrayList<>();
/* 382 */     ArrayList<MarioAgentEvent> agentEvents = new ArrayList<>();
/* 383 */     long startTimeMs = System.currentTimeMillis();
/* 384 */     float rightMost = 0.0F;
/* 385 */     long rightMostTime = 0L;
/*     */     
/* 387 */     while (this.world.gameStatus == GameStatus.RUNNING) {
/* 388 */       if (!this.pause) {
/*     */         
/* 390 */         agentTimer = new MarioTimer(40L);
/* 391 */         boolean[] actions = this.agent.getActions(new MarioForwardModel(this.world.clone()), agentTimer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 399 */         this.world.update(actions);
/* 400 */         gameEvents.addAll(this.world.lastFrameEvents);
/* 401 */         agentEvents.add(new MarioAgentEvent(actions, this.world.mario.x, 
/* 402 */               this.world.mario.y, (this.world.mario.isLarge ? 1 : 0) + (this.world.mario.isFire ? 1 : 0), 
/* 403 */               this.world.mario.onGround, this.world.currentTick));
/*     */       } 
/*     */ 
/*     */       
/* 407 */       if (System.currentTimeMillis() - startTimeMs >= (long)(k * this.world.mario.x) + b) {
/* 408 */         this.world.gameStatus = GameStatus.TIME_OUT;
/*     */       }
/* 410 */       if (this.world.mario.x > rightMost) {
/* 411 */         rightMost = this.world.mario.x;
/* 412 */         rightMostTime = System.currentTimeMillis();
/*     */         continue;
/*     */       } 
/* 415 */       if (System.currentTimeMillis() - rightMostTime > 1000L) {
/* 416 */         this.world.gameStatus = GameStatus.TIME_OUT;
/*     */       }
/*     */     } 
/*     */     
/* 420 */     return new MarioResult(this.world, gameEvents, agentEvents);
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */