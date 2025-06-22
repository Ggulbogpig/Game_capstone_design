/*    */ import agents.robinBaumgarten.Agent;
/*    */ import engine.core.MarioAgent;
/*    */ import engine.core.MarioGame;
/*    */ import engine.core.MarioResult;
/*    */ import java.io.IOException;
         import java.nio.file.Paths;
/*    */ import java.nio.file.Files;
/*    */ 
/*    */ public class PlayLevel {
/*    */   public static void printResults(MarioResult result) {
/* 10 */     System.out.println("****************************************************************");
/* 11 */     System.out.println("Game Status: " + result.getGameStatus().toString() + " Percentage Completion: " + 
/* 12 */         result.getCompletionPercentage());
/* 13 */     System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() + 
/* 14 */         " Remaining Time: " + (int)Math.ceil((result.getRemainingTime() / 1000.0F)));
/* 15 */     System.out.println("Mario State: " + result.getMarioMode() + " (Mushrooms: " + result.getNumCollectedMushrooms() + 
/* 16 */         " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
/* 17 */     System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() + 
/* 18 */         " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() + " Falls: " + 
/* 19 */         result.getKillsByFall() + ")");
/* 20 */     System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() + 
/* 21 */         " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
/* 22 */     System.out.println("****************************************************************");
/*    */   }
/*    */   
/*    */   public static String getLevel(String filepath) {
/* 26 */     String content = "";
/*    */     try {
/* 28 */       content = new String(Files.readAllBytes(Paths.get(filepath, new String[0])));
/* 29 */     } catch (IOException e) {
/* 30 */       System.out.println("Cannot open " + filepath);
/*    */     } 
/* 32 */     return content;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 36 */     MarioGame game = new MarioGame();
/*    */     
/* 38 */     Agent agent = new Agent();
/* 39 */     for (int i = 0; i < 20; i++) {
/* 40 */       game.runGame((MarioAgent)agent, getLevel("C:/Users/hbsss/Downloads/MFEDRL-master_250601/MFEDRL-master/exp_analysis/endless_gen/leveltest/agent3_level_54.txt"), 200, 0, true);
/* 41 */       
            String level = getLevel("C:/Users/hbsss/Downloads/MFEDRL-master_250601/MFEDRL-master/exp_analysis/endless_gen/leveltest/agent1_level_54.txt");

             MarioResult result = game.runGame(agent, level, 200, 0);
             
             PlayLevel.printResults(result);

            System.out.println(agent.tree.maxTreeSize);
            System.out.println("SearchedStates: " + agent.tree.SearchedStates);
            
            System.out.println("Searched States: " + agent.tree.SearchedStates);
            System.out.println("Searched Lose: " + agent.tree.SearchedLose);
//////////////////////////////////////////////////////////////
           
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\PlayLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */