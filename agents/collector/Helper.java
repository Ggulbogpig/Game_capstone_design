/*     */ package agents.collector;
/*     */ 
/*     */ import engine.core.MarioForwardModel;
/*     */ import engine.helper.GameStatus;
/*     */ import engine.helper.MarioActions;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Helper
/*     */ {
/*     */   public static final int visitedListPenalty = 1500;
/*     */   public static final float maxMarioSpeed = 10.909091F;
/*     */   
/*     */   public static int getMarioDamage(MarioForwardModel model, MarioForwardModel prevModel) {
/*  14 */     int damage = 0;
/*  15 */     if (prevModel.getMarioMode() > model.getMarioMode()) {
/*  16 */       damage++;
/*     */     }
/*  18 */     if (model.getGameStatus() == GameStatus.LOSE) {
/*  19 */       if (model.getMarioFloatPos()[1] > model.getLevelFloatDimensions()[1] - 20.0F) {
/*  20 */         damage += 5;
/*     */       } else {
/*  22 */         damage += 2;
/*     */       } 
/*     */     }
/*  25 */     return damage;
/*     */   }
/*     */   
/*     */   public static String getActionString(boolean[] action) {
/*  29 */     String s = "";
/*  30 */     if (action[MarioActions.RIGHT.getValue()])
/*  31 */       s = String.valueOf(s) + "Forward "; 
/*  32 */     if (action[MarioActions.LEFT.getValue()])
/*  33 */       s = String.valueOf(s) + "Backward "; 
/*  34 */     if (action[MarioActions.SPEED.getValue()])
/*  35 */       s = String.valueOf(s) + "Speed "; 
/*  36 */     if (action[MarioActions.JUMP.getValue()])
/*  37 */       s = String.valueOf(s) + "Jump "; 
/*  38 */     if (action[MarioActions.DOWN.getValue()])
/*  39 */       s = String.valueOf(s) + "Duck"; 
/*  40 */     if (s.length() == 0) {
/*  41 */       s = "[NONE]";
/*     */     }
/*  43 */     return s;
/*     */   }
/*     */   
/*     */   public static float[] estimateMaximumForwardMovement(float currentAccel, boolean[] action, int ticks) {
/*  47 */     float dist = 0.0F;
/*  48 */     float runningSpeed = action[MarioActions.SPEED.getValue()] ? 1.2F : 0.6F;
/*  49 */     int dir = 0;
/*  50 */     if (action[MarioActions.LEFT.getValue()])
/*  51 */       dir = -1; 
/*  52 */     if (action[MarioActions.RIGHT.getValue()])
/*  53 */       dir = 1; 
/*  54 */     for (int i = 0; i < ticks; i++) {
/*  55 */       currentAccel += runningSpeed * dir;
/*  56 */       dist += currentAccel;
/*  57 */       currentAccel *= 0.89F;
/*     */     } 
/*  59 */     float[] ret = new float[2];
/*  60 */     ret[0] = dist;
/*  61 */     ret[1] = currentAccel;
/*  62 */     return ret;
/*     */   }
/*     */   
/*     */   public static boolean[] createAction(boolean left, boolean right, boolean down, boolean jump, boolean speed) {
/*  66 */     boolean[] action = new boolean[5];
/*  67 */     action[MarioActions.DOWN.getValue()] = down;
/*  68 */     action[MarioActions.JUMP.getValue()] = jump;
/*  69 */     action[MarioActions.LEFT.getValue()] = left;
/*  70 */     action[MarioActions.RIGHT.getValue()] = right;
/*  71 */     action[MarioActions.SPEED.getValue()] = speed;
/*  72 */     return action;
/*     */   }
/*     */   
/*     */   public static boolean canJumpHigher(SearchNode node, boolean checkParent) {
/*  76 */     if (node.parentPos != null && checkParent && canJumpHigher(node.parentPos, false))
/*  77 */       return true; 
/*  78 */     return !(!node.sceneSnapshot.mayMarioJump() && !node.sceneSnapshot.getMarioCanJumpHigher());
/*     */   }
/*     */   
/*     */   public static ArrayList<boolean[]> createPossibleActions(SearchNode node) {
/*  82 */     ArrayList<boolean[]> possibleActions = (ArrayList)new ArrayList<>();
/*     */     
/*  84 */     if (canJumpHigher(node, true))
/*  85 */       possibleActions.add(createAction(false, false, false, true, false)); 
/*  86 */     if (canJumpHigher(node, true)) {
/*  87 */       possibleActions.add(createAction(false, false, false, true, true));
/*     */     }
/*     */     
/*  90 */     possibleActions.add(createAction(false, true, false, false, true));
/*  91 */     if (canJumpHigher(node, true))
/*  92 */       possibleActions.add(createAction(false, true, false, true, true)); 
/*  93 */     possibleActions.add(createAction(false, true, false, false, false));
/*  94 */     if (canJumpHigher(node, true)) {
/*  95 */       possibleActions.add(createAction(false, true, false, true, false));
/*     */     }
/*     */     
/*  98 */     possibleActions.add(createAction(true, false, false, false, false));
/*  99 */     if (canJumpHigher(node, true))
/* 100 */       possibleActions.add(createAction(true, false, false, true, false)); 
/* 101 */     possibleActions.add(createAction(true, false, false, false, true));
/* 102 */     if (canJumpHigher(node, true)) {
/* 103 */       possibleActions.add(createAction(true, false, false, true, true));
/*     */     }
/* 105 */     return possibleActions;
/*     */   }
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\collector\Helper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */