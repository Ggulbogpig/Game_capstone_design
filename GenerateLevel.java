/*    */         //추가
        import engine.core.MarioTimer;
        import engine.core.MarioAgent;

        import agents.robinBaumgarten.Agent;
/*    */ import engine.core.MarioGame;
/*    */ import engine.core.MarioLevelModel;
/*    */ import engine.core.MarioResult;
/*    */ import levelGenerators.notch.LevelGenerator;

/*    */ 
/*    */ public class GenerateLevel {
/*    */   public static void printResults(MarioResult result) {
/*  9 */     System.out.println("****************************************************************");
/* 10 */     System.out.println("Game Status: " + result.getGameStatus().toString() + 
/* 11 */         " Percentage Completion: " + result.getCompletionPercentage());
/* 12 */     System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() + 
/* 13 */         " Remaining Time: " + (int)Math.ceil((result.getRemainingTime() / 1000.0F)));
/* 14 */     System.out.println("Mario State: " + result.getMarioMode() + 
/* 15 */         " (Mushrooms: " + result.getNumCollectedMushrooms() + " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
/* 16 */     System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() + 
/* 17 */         " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() + 
/* 18 */         " Falls: " + result.getKillsByFall() + ")");
/* 19 */     System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() + 
/* 20 */         " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
/* 21 */     System.out.println("****************************************************************");
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 25 */     LevelGenerator levelGenerator = new LevelGenerator();
/* 26 */     String level = levelGenerator.getGeneratedLevel(new MarioLevelModel(150, 16), new MarioTimer(18000000L));
/* 27 */     MarioGame game = new MarioGame();
/*    */     
/* 29 */     printResults(game.runGame((MarioAgent)new Agent(), level, 20, 0, true));
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\GenerateLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */