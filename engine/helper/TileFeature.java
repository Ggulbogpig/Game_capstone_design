/*    */ package engine.helper;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public enum TileFeature {
/*  6 */   BLOCK_UPPER,
/*  7 */   BLOCK_ALL,
/*  8 */   BLOCK_LOWER,
/*  9 */   SPECIAL,
/* 10 */   LIFE,
/* 11 */   BUMPABLE,
/* 12 */   BREAKABLE,
/* 13 */   PICKABLE,
/* 14 */   ANIMATED,
/* 15 */   SPAWNER;
/*    */   
/*    */   public static ArrayList<TileFeature> getTileType(int index) {
/* 18 */     ArrayList<TileFeature> features = new ArrayList<>();
/* 19 */     switch (index) {
/*    */       case 1:
/*    */       case 2:
/*    */       case 4:
/*    */       case 5:
/*    */       case 14:
/*    */       case 18:
/*    */       case 19:
/*    */       case 20:
/*    */       case 21:
/*    */       case 52:
/*    */       case 53:
/* 31 */         features.add(BLOCK_ALL);
/*    */         break;
/*    */       case 43:
/*    */       case 44:
/*    */       case 45:
/*    */       case 46:
/* 37 */         features.add(BLOCK_LOWER);
/*    */         break;
/*    */       case 48:
/* 40 */         features.add(BLOCK_UPPER);
/* 41 */         features.add(LIFE);
/* 42 */         features.add(BUMPABLE);
/*    */         break;
/*    */       case 49:
/* 45 */         features.add(BUMPABLE);
/* 46 */         features.add(BLOCK_UPPER);
/*    */         break;
/*    */       case 3:
/* 49 */         features.add(BLOCK_ALL);
/* 50 */         features.add(SPAWNER);
/*    */         break;
/*    */       case 8:
/* 53 */         features.add(BLOCK_ALL);
/* 54 */         features.add(SPECIAL);
/* 55 */         features.add(BUMPABLE);
/* 56 */         features.add(ANIMATED);
/*    */         break;
/*    */       case 11:
/* 59 */         features.add(BLOCK_ALL);
/* 60 */         features.add(BUMPABLE);
/* 61 */         features.add(ANIMATED);
/*    */         break;
/*    */       case 6:
/* 64 */         features.add(BLOCK_ALL);
/* 65 */         features.add(BREAKABLE);
/*    */         break;
/*    */       case 7:
/* 68 */         features.add(BLOCK_ALL);
/* 69 */         features.add(BUMPABLE);
/*    */         break;
/*    */       case 15:
/* 72 */         features.add(PICKABLE);
/* 73 */         features.add(ANIMATED);
/*    */         break;
/*    */       case 50:
/* 76 */         features.add(BLOCK_ALL);
/* 77 */         features.add(SPECIAL);
/* 78 */         features.add(BUMPABLE);
/*    */         break;
/*    */       case 51:
/* 81 */         features.add(BLOCK_ALL);
/* 82 */         features.add(LIFE);
/* 83 */         features.add(BUMPABLE);
/*    */         break;
/*    */     } 
/* 86 */     return features;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\helper\TileFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */