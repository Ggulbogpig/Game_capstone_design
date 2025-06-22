/*    */ package agents.spencerSchumann;
/*    */ 
/*    */ import engine.core.MarioForwardModel;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemySimulator
/*    */ {
/* 20 */   public ArrayList<Enemy> enemies = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public EnemySimulator clone() {
/* 25 */     EnemySimulator es = new EnemySimulator();
/* 26 */     for (Enemy e : this.enemies) {
/* 27 */       es.enemies.add(e.clone());
/*    */     }
/* 29 */     return es;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(Scene scene) {
/* 34 */     for (Enemy enemy : this.enemies)
/*    */     {
/* 36 */       enemy.x += enemy.vx;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(MarioForwardModel model) {
/* 43 */     float[] ep = model.getEnemiesFloatPos();
/*    */     
/* 45 */     ArrayList<Enemy> newEnemies = new ArrayList<>();
/* 46 */     for (int i = 0; i < ep.length; i += 3) {
/* 47 */       int type = (int)ep[i];
/* 48 */       float x = ep[i + 1];
/* 49 */       float y = ep[i + 2];
/* 50 */       boolean found = false;
/* 51 */       for (Enemy enemy : this.enemies) {
/* 52 */         if (type == enemy.type && y == enemy.y) {
/* 53 */           float xdiff = Math.abs(enemy.x - x);
/* 54 */           if (xdiff == 0.0F) {
/*    */             
/* 56 */             newEnemies.add(enemy);
/* 57 */             this.enemies.remove(enemy);
/* 58 */             found = true; break;
/*    */           } 
/* 60 */           if (xdiff < 5.0F) {
/* 61 */             enemy.vx += x - enemy.x;
/* 62 */             enemy.x = x;
/* 63 */             newEnemies.add(enemy);
/* 64 */             this.enemies.remove(enemy);
/* 65 */             found = true;
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 70 */       if (!found)
/*    */       {
/* 72 */         newEnemies.add(new Enemy(type, x, y));
/*    */       }
/*    */     } 
/* 75 */     this.enemies = newEnemies;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\EnemySimulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */