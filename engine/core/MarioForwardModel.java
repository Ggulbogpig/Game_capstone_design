/*      */ package engine.core;
/*      */ 
/*      */ import engine.helper.EventType;
/*      */ import engine.helper.GameStatus;
/*      */ import engine.helper.SpriteType;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MarioForwardModel
/*      */ {
/*      */   private static final int OBS_SCENE_SHIFT = 16;
/*      */   public static final int OBS_NONE = 0;
/*      */   public static final int OBS_UNDEF = -42;
/*      */   public static final int OBS_SOLID = 17;
/*      */   public static final int OBS_BRICK = 22;
/*      */   public static final int OBS_QUESTION_BLOCK = 24;
/*      */   public static final int OBS_COIN = 31;
/*      */   public static final int OBS_PYRAMID_SOLID = 18;
/*      */   public static final int OBS_PIPE_BODY_RIGHT = 37;
/*      */   public static final int OBS_PIPE_BODY_LEFT = 36;
/*      */   public static final int OBS_PIPE_TOP_RIGHT = 35;
/*      */   public static final int OBS_PIPE_TOP_LEFT = 34;
/*      */   public static final int OBS_USED_BLOCK = 30;
/*      */   public static final int OBS_BULLET_BILL_BODY = 21;
/*      */   public static final int OBS_BULLET_BILL_NECT = 20;
/*      */   public static final int OBS_BULLET_BILL_HEAD = 19;
/*      */   public static final int OBS_BACKGROUND = 63;
/*      */   public static final int OBS_PLATFORM_SINGLE = 59;
/*      */   public static final int OBS_PLATFORM_LEFT = 60;
/*      */   public static final int OBS_PLATFORM_RIGHT = 61;
/*      */   public static final int OBS_PLATFORM_CENTER = 62;
/*      */   public static final int OBS_PLATFORM = 59;
/*      */   public static final int OBS_CANNON = 19;
/*      */   public static final int OBS_PIPE = 34;
/*      */   public static final int OBS_SCENE_OBJECT = 100;
/*      */   public static final int OBS_FIREBALL = 16;
/*      */   public static final int OBS_GOOMBA = 2;
/*      */   public static final int OBS_GOOMBA_WINGED = 3;
/*      */   public static final int OBS_RED_KOOPA = 4;
/*      */   public static final int OBS_RED_KOOPA_WINGED = 5;
/*      */   public static final int OBS_GREEN_KOOPA = 6;
/*      */   public static final int OBS_GREEN_KOOPA_WINGED = 7;
/*      */   public static final int OBS_SPIKY = 8;
/*      */   public static final int OBS_SPIKY_WINGED = 9;
/*      */   public static final int OBS_BULLET_BILL = 10;
/*      */   public static final int OBS_ENEMY_FLOWER = 11;
/*      */   public static final int OBS_MUSHROOM = 12;
/*      */   public static final int OBS_FIRE_FLOWER = 13;
/*      */   public static final int OBS_SHELL = 14;
/*      */   public static final int OBS_LIFE_MUSHROOM = 15;
/*      */   public static final int OBS_STOMPABLE_ENEMY = 2;
/*      */   public static final int OBS_NONSTOMPABLE_ENEMY = 8;
/*      */   public static final int OBS_SPECIAL_ITEM = 12;
/*      */   public static final int OBS_ENEMY = 1;
/*      */   
/*      */   public static int getSpriteTypeGeneralization(SpriteType sprite, int detail) {
/*  712 */     switch (detail) {
/*      */       case 0:
/*  714 */         switch (sprite) {
/*      */           case MARIO:
/*  716 */             return 0;
/*      */         } 
/*  718 */         sprite.getValue();
/*      */       
/*      */       case 1:
/*  721 */         switch (sprite) {
/*      */           case MARIO:
/*  723 */             return 0;
/*      */           case FIREBALL:
/*  725 */             return 16;
/*      */           case MUSHROOM:
/*      */           case FIRE_FLOWER:
/*      */           case LIFE_MUSHROOM:
/*  729 */             return 12;
/*      */           case GOOMBA:
/*      */           case GOOMBA_WINGED:
/*      */           case RED_KOOPA:
/*      */           case RED_KOOPA_WINGED:
/*      */           case GREEN_KOOPA:
/*      */           case GREEN_KOOPA_WINGED:
/*      */           //case null:
/*      */           case SHELL:
/*  738 */             return 2;
/*      */           case SPIKY:
/*      */           case SPIKY_WINGED:
/*      */           case ENEMY_FLOWER:
/*  742 */             return 8;
                    default:
                        return OBS_NONE;
/*      */         } 
/*  744 */         
/*      */       
/*      */       case 2:
/*  747 */         switch (sprite) {
/*      */           case MARIO:
/*      */           case FIREBALL:
/*      */           case MUSHROOM:
/*      */           case FIRE_FLOWER:
/*      */           case LIFE_MUSHROOM:
/*  753 */             return 0;
                    default:
                        return OBS_ENEMY;
/*      */         } 
/*  755 */         
/*      */     } 
/*      */     
/*  758 */     return -42;
/*      */   }
/*      */   
/*      */   public static int getBlockValueGeneralization(int tile, int detail) {
/*  762 */     if (tile == 0) {
/*  763 */       return 0;
/*      */     }
/*  765 */     switch (detail) {
/*      */       case 0:
/*  767 */         switch (tile) {
/*      */           
/*      */           case 48:
/*      */           case 49:
/*  771 */             return 0;
/*      */           
/*      */           case 6:
/*      */           case 7:
/*      */           case 50:
/*      */           case 51:
/*  777 */             return 22;
/*      */           
/*      */           case 8:
/*      */           case 11:
/*  781 */             return 24;
/*      */         } 
/*  783 */         return tile + 16;
/*      */       case 1:
/*  785 */         switch (tile) {
/*      */ 
/*      */           
/*      */           case 47:
/*      */           case 48:
/*      */           case 49:
/*  791 */             return 0;
/*      */           
/*      */           case 1:
/*      */           case 2:
/*      */           case 14:
/*  796 */             return 17;
/*      */           
/*      */           case 3:
/*      */           case 4:
/*      */           case 5:
/*  801 */             return 19;
/*      */           
/*      */           case 18:
/*      */           case 19:
/*      */           case 20:
/*      */           case 21:
/*  807 */             return 34;
/*      */           
/*      */           case 6:
/*      */           case 7:
/*      */           case 50:
/*      */           case 51:
/*  813 */             return 22;
/*      */           
/*      */           case 8:
/*      */           case 11:
/*  817 */             return 24;
/*      */           
/*      */           case 15:
/*  820 */             return 31;
/*      */           
/*      */           case 44:
/*      */           case 45:
/*      */           case 46:
/*  825 */             return 59;
/*      */         } 
/*  827 */         return 0;
/*      */       case 2:
/*  829 */         switch (tile) {
/*      */ 
/*      */           
/*      */           case 47:
/*      */           case 48:
/*      */           case 49:
/*  835 */             return 0;
/*      */         } 
/*      */         
/*  838 */         return 100;
/*      */     } 
/*  840 */     return -42;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  846 */   public final int obsGridWidth = 16;
/*      */ 
/*      */ 
/*      */   
/*  850 */   public final int obsGridHeight = 16;
/*      */   
/*      */   private MarioWorld world;
/*      */   
/*      */   private int fallKill;
/*      */   
/*      */   private int stompKill;
/*      */   
/*      */   private int fireKill;
/*      */   
/*      */   private int shellKill;
/*      */   
/*      */   private int mushrooms;
/*      */   
/*      */   private int flowers;
/*      */   
/*      */   private int breakBlock;

            //추가
            private int cumulativeJumpTime = 0;
            private boolean wasJumping = false;
            private int totalFrames = 0;
/*      */ 
/*      */   
/*      */   public MarioForwardModel(MarioWorld world) {
/*  870 */     this.world = world;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MarioForwardModel(MarioWorld world, ArrayList<MarioEvent> gameEvents) {
/*  881 */     this.world = world;
/*  882 */     for (MarioEvent e : gameEvents) {
/*  883 */       if (e.getEventType() == EventType.FIRE_KILL.getValue()) {
/*  884 */         this.fireKill++;
/*      */       }
/*  886 */       if (e.getEventType() == EventType.STOMP_KILL.getValue()) {
/*  887 */         this.stompKill++;
/*      */       }
/*  889 */       if (e.getEventType() == EventType.FALL_KILL.getValue()) {
/*  890 */         this.fallKill++;
/*      */       }
/*  892 */       if (e.getEventType() == EventType.SHELL_KILL.getValue()) {
/*  893 */         this.shellKill++;
/*      */       }
/*  895 */       if (e.getEventType() == EventType.COLLECT.getValue()) {
/*  896 */         if (e.getEventParam() == SpriteType.FIRE_FLOWER.getValue()) {
/*  897 */           this.flowers++;
/*      */         }
/*  899 */         if (e.getEventParam() == SpriteType.MUSHROOM.getValue()) {
/*  900 */           this.mushrooms++;
/*      */         }
/*      */       } 
/*  903 */       if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == 22 && 
/*  904 */         e.getMarioState() > 0) {
/*  905 */         this.breakBlock++;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MarioForwardModel clone() {
/*  916 */     MarioForwardModel model = new MarioForwardModel(this.world.clone());
/*  917 */     model.fallKill = this.fallKill;
/*  918 */     model.stompKill = this.stompKill;
/*  919 */     model.fireKill = this.fireKill;
/*  920 */     model.shellKill = this.shellKill;
/*  921 */     model.mushrooms = this.mushrooms;
/*  922 */     model.flowers = this.flowers;
/*  923 */     model.breakBlock = this.breakBlock;
/*  924 */     return model;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void advance(boolean[] actions) {
/*  933 */     this.world.update(actions);
/*  934 */     for (MarioEvent e : this.world.lastFrameEvents) {
/*  935 */       if (e.getEventType() == EventType.FIRE_KILL.getValue()) {
/*  936 */         this.fireKill++;
/*      */       }
/*  938 */       if (e.getEventType() == EventType.STOMP_KILL.getValue()) {
/*  939 */         this.stompKill++;
/*      */       }
/*  941 */       if (e.getEventType() == EventType.FALL_KILL.getValue()) {
/*  942 */         this.fallKill++;
/*      */       }
/*  944 */       if (e.getEventType() == EventType.SHELL_KILL.getValue()) {
/*  945 */         this.shellKill++;
/*      */       }
/*  947 */       if (e.getEventType() == EventType.COLLECT.getValue()) {
/*  948 */         if (e.getEventParam() == SpriteType.FIRE_FLOWER.getValue()) {
/*  949 */           this.flowers++;
/*      */         }
/*  951 */         if (e.getEventParam() == SpriteType.MUSHROOM.getValue()) {
/*  952 */           this.mushrooms++;
/*      */         }
/*      */       } 
/*  955 */       if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == 22 && 
/*  956 */         e.getMarioState() > 0) {
/*  957 */         this.breakBlock++;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GameStatus getGameStatus() {
/*  968 */     return this.world.gameStatus;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCompletionPercentage() {
/*  978 */     return this.world.mario.x / (this.world.level.exitTileX * 16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float[] getLevelFloatDimensions() {
/*  987 */     return new float[] { this.world.level.width, this.world.level.height };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRemainingTime() {
/*  996 */     return this.world.currentTimer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float[] getMarioFloatPos() {
/* 1005 */     return new float[] { this.world.mario.x, this.world.mario.y };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float[] getMarioFloatVelocity() {
/* 1014 */     return new float[] { this.world.mario.xa, this.world.mario.ya };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getMarioCanJumpHigher() {
/* 1023 */     return (this.world.mario.jumpTime > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMarioMode() {
/* 1032 */     int value = 0;
/* 1033 */     if (this.world.mario.isLarge) {
/* 1034 */       value = 1;
/*      */     }
/* 1036 */     if (this.world.mario.isFire) {
/* 1037 */       value = 2;
/*      */     }
/* 1039 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMarioOnGround() {
/* 1048 */     return this.world.mario.onGround;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean mayMarioJump() {
/* 1057 */     return this.world.mario.mayJump;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float[] getEnemiesFloatPos() {
/* 1067 */     ArrayList<MarioSprite> enemiesAlive = this.world.getEnemies();
/* 1068 */     float[] enemyPos = new float[enemiesAlive.size() * 3];
/* 1069 */     for (int i = 0; i < enemiesAlive.size(); i++) {
/* 1070 */       enemyPos[3 * i] = ((MarioSprite)enemiesAlive.get(i)).type.getValue();
/* 1071 */       enemyPos[3 * i + 1] = ((MarioSprite)enemiesAlive.get(i)).x;
/* 1072 */       enemyPos[3 * i + 2] = ((MarioSprite)enemiesAlive.get(i)).y;
/*      */     } 
/* 1074 */     return enemyPos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalEnemies() {
/* 1084 */     return this.world.getEnemies().size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKillsTotal() {
/* 1093 */     return this.fallKill + this.fireKill + this.shellKill + this.stompKill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKillsByFire() {
/* 1102 */     return this.fireKill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKillsByStomp() {
/* 1111 */     return this.stompKill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKillsByShell() {
/* 1120 */     return this.shellKill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKillsByFall() {
/* 1129 */     return this.fallKill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumLives() {
/* 1138 */     return this.world.lives;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumCollectedMushrooms() {
/* 1147 */     return this.mushrooms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumCollectedFireflower() {
/* 1156 */     return this.flowers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumCollectedCoins() {
/* 1165 */     return this.world.coins;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalCoins() {
/* 1174 */     return this.world.level.totalCoins;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumDestroyedBricks() {
/* 1182 */     return this.breakBlock;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getMarioScreenTilePos() {
/* 1191 */     return new int[] { (int)((this.world.mario.x - this.world.cameraX) / 16.0F), (int)(this.world.mario.y / 16.0F) };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenCompleteObservation() {
/* 1201 */     return getScreenCompleteObservation(1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenEnemiesObservation() {
/* 1213 */     return getScreenEnemiesObservation(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenSceneObservation() {
/* 1225 */     return getScreenSceneObservation(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioCompleteObservation() {
/* 1235 */     return getMarioCompleteObservation(1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioEnemiesObservation() {
/* 1247 */     return getMarioEnemiesObservation(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioSceneObservation() {
/* 1259 */     return getMarioSceneObservation(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenCompleteObservation(int sceneDetail, int enemyDetail) {
/* 1272 */     return this.world.getMergedObservation(this.world.cameraX + 128.0F, 128.0F, 
/* 1273 */         sceneDetail, enemyDetail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenEnemiesObservation(int detail) {
/* 1287 */     return this.world.getEnemiesObservation(this.world.cameraX + 128.0F, 128.0F, detail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getScreenSceneObservation(int detail) {
/* 1301 */     return this.world.getSceneObservation(this.world.cameraX + 128.0F, 128.0F, detail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioCompleteObservation(int sceneDetail, int enemyDetail) {
/* 1314 */     return this.world.getMergedObservation(this.world.mario.x, this.world.mario.y, sceneDetail, enemyDetail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioEnemiesObservation(int detail) {
/* 1327 */     return this.world.getEnemiesObservation(this.world.mario.x, this.world.mario.y, detail);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getMarioSceneObservation(int detail) {
/* 1341 */     return this.world.getSceneObservation(this.world.mario.x, this.world.mario.y, detail);
/*      */   }

            //점프 시간 비율 계산 함수 추가
            private void trackJumpTime() {
                if (this.world.mario.jumpTime > 0) {
                    cumulativeJumpTime++;
                    wasJumping = true;
                } else if (wasJumping) {
                    wasJumping = false;
                }
            }
            
            public int getCumulativeJumpTime() {
                return this.cumulativeJumpTime;
            }
        
            //점프 시간 비율 계산하는 함수 추가
            public float getJumpTimeRatio() {
                if (totalFrames == 0) return 0f;
                return (float) cumulativeJumpTime / totalFrames;
            }
/*      */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioForwardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */