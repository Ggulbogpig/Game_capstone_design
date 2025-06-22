/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioGame;
/*    */ import engine.core.MarioResult;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Paths;
/*    */ 
/*    */ 
/*    */ public class MarioProxy
/*    */ {
/* 11 */   private MarioGame game = new MarioGame();
/*    */   
/*    */   private static void printResults(MarioResult result) {
/* 14 */     System.out.println("****************************************************************");
/* 15 */     System.out.println("Game Status: " + result.getGameStatus().toString() + " Percentage Completion: " + 
/* 16 */         result.getCompletionPercentage());
/* 17 */     System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() + 
/* 18 */         " Remaining Time: " + (int)Math.ceil((result.getRemainingTime() / 1000.0F)));
/* 19 */     System.out.println("Mario State: " + result.getMarioMode() + " (Mushrooms: " + result.getNumCollectedMushrooms() + 
/* 20 */         " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
/* 21 */     System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() + 
/* 22 */         " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() + " Falls: " + 
/* 23 */         result.getKillsByFall() + ")");
/* 24 */     System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() + 
/* 25 */         " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
/* 26 */     System.out.println("****************************************************************");
/*    */   }
/*    */   
/*    */   private String getLevel(String filepath) {
/* 30 */     String content = "";
/*    */     try {
/* 32 */       content = new String(Files.readAllBytes(Paths.get(filepath, new String[0])));
/* 33 */     } catch (IOException e) {
/* 34 */       System.out.println("Cannot open " + filepath);
/*    */     } 
/* 36 */     return content;
/*    */   }
/*    */ 
/*    */   
/*    */   public MarioResult playGameFromTxt(String filepath) {
/* 41 */     MarioResult res = this.game.playGame(getLevel(filepath), 200, 0);
/* 42 */     printResults(res);
/* 43 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public MarioResult playGame(String level) {
/* 48 */     MarioResult res = this.game.playGame(level, 200, 0);
/* 49 */     printResults(res);
/* 50 */     return res;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MarioResult simulateGame(String level, MarioAgent agent, boolean render, long realTimeLimitMs, int fps) {
/* 56 */     int time = level.length() / 100 + 10;
             System.out.println(">>> Java simulateGame 시작");
/* 57 */     MarioResult res = this.game.runGameWithRealTimeLimits(agent, level, time, 0, render, realTimeLimitMs, fps);
/* 58 */     System.out.println(">>> Java simulateGame 끝");
             return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public MarioResult simulateWithRealTimeSuspension(String level, MarioAgent agent, float k, long b) {
/* 63 */     int time = level.length() / 100 + 10;
/* 64 */     MarioResult res = this.game.runWithRealTimeSuspension(level, agent, time, k, b);
/* 65 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\MarioProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */