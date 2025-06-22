/*     */ package agents.spencerSchumann;
/*     */ 
/*     */ import engine.core.MarioForwardModel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Scene
/*     */ {
/*  12 */   public ArrayList<Edge> floors = new ArrayList<>();
/*  13 */   public ArrayList<Edge> walls = new ArrayList<>();
/*  14 */   public ArrayList<Edge> ceilings = new ArrayList<>();
/*  15 */   public ArrayList<BumpableEdge> bumpables = new ArrayList<>();
/*  16 */   public ArrayList<Edge> enemyEmitters = new ArrayList<>();
/*     */   
/*     */   public long constructTime;
/*     */   
/*     */   public float originX;
/*     */   
/*     */   public float originY;
/*     */ 
/*     */   
/*     */   public Scene clone() {
/*  26 */     Scene s = new Scene();
/*  27 */     s.update(this);
/*  28 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(Scene scene) {
/*  33 */     clearEdges();
/*  34 */     add(scene);
/*  35 */     this.originX = scene.originX;
/*  36 */     this.originY = scene.originY;
/*     */   }
/*     */   
/*     */   public void clearEdges() {
/*  40 */     this.floors.clear();
/*  41 */     this.walls.clear();
/*  42 */     this.ceilings.clear();
/*  43 */     this.bumpables.clear();
/*  44 */     this.enemyEmitters.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scene(float originX, float originY) {
/*  51 */     this.originX = originX;
/*  52 */     this.originY = originY;
/*     */   }
/*     */   
/*     */   public Scene(MarioForwardModel model, int[][] scene) {
/*  56 */     long startTime = System.nanoTime();
/*     */     
/*  58 */     float[] marioPos = model.getMarioFloatPos();
/*  59 */     model.getClass(); this.originX = (float)Math.floor((marioPos[0] / 16.0F)) * 16.0F - (16 / 2) * 16.0F;
/*  60 */     model.getClass(); this.originY = (float)Math.floor((marioPos[1] / 16.0F)) * 16.0F - (16 / 2) * 16.0F;
/*     */     
/*  62 */     boolean[][] visited = new boolean[scene.length][(scene[0]).length];
/*     */     
/*  64 */     for (int x = 0; x < scene.length; x++) {
/*  65 */       for (int y = 0; y < (scene[x]).length; y++) {
/*  66 */         int tile = scene[x][y];
/*  67 */         if (tile != 7)
/*     */         {
/*  69 */           if (!visited[y][x]) {
/*  70 */             if (Tiles.isWall(tile)) {
/*  71 */               Scene block = new Scene(this.originX, this.originY);
/*  72 */               block.expandWall(scene, visited, x, y);
/*  73 */               add(block);
/*  74 */             } else if (tile == 2) {
/*  75 */               Scene ledge = new Scene(this.originX, this.originY);
/*  76 */               ledge.expandLedge(scene, visited, x, y);
/*  77 */               add(ledge);
/*     */             } 
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*  83 */     this.constructTime = System.nanoTime() - startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void expandWall(int[][] scene, boolean[][] visited, int x, int y) {
/*  89 */     if (visited[y][x]) {
/*     */       return;
/*     */     }
/*  92 */     visited[y][x] = true;
/*     */     
/*  94 */     if (x > 0) {
/*  95 */       if (Tiles.isWall(scene[x - 1][y])) {
/*  96 */         expandWall(scene, visited, x - 1, y);
/*     */       } else {
/*  98 */         this.walls.add(new Edge(this.originX + x * 16.0F, this.originY + y * 16.0F, 
/*  99 */               this.originX + x * 16.0F, this.originY + (y + 1) * 16.0F));
/*     */       } 
/*     */     }
/*     */     
/* 103 */     if (x < scene.length - 1) {
/* 104 */       if (Tiles.isWall(scene[x + 1][y])) {
/* 105 */         expandWall(scene, visited, x + 1, y);
/*     */       } else {
/* 107 */         this.walls.add(new Edge(this.originX + (x + 1) * 16.0F, this.originY + y * 16.0F, 
/* 108 */               this.originX + (x + 1) * 16.0F, this.originY + (y + 1) * 16.0F));
/*     */       } 
/*     */     }
/*     */     
/* 112 */     if (y > 0) {
/* 113 */       if (Tiles.isWall(scene[x][y - 1])) {
/* 114 */         expandWall(scene, visited, x, y - 1);
/*     */       } else {
/* 116 */         this.floors.add(new Edge(this.originX + x * 16.0F, this.originY + y * 16.0F, 
/* 117 */               this.originX + (x + 1) * 16.0F, this.originY + y * 16.0F));
/*     */       } 
/*     */     }
/*     */     
/* 121 */     if (y < (scene[x]).length - 1) {
/* 122 */       if (Tiles.isWall(scene[x][y + 1])) {
/* 123 */         expandWall(scene, visited, x, y + 1);
/*     */       } else {
/* 125 */         this.ceilings.add(new Edge(this.originX + x * 16.0F, this.originY + (y + 1) * 16.0F, 
/* 126 */               this.originX + (x + 1) * 16.0F, this.originY + (y + 1) * 16.0F));
/*     */       } 
/*     */     }
/* 129 */     coalesce();
/*     */   }
/*     */ 
/*     */   
/*     */   private void expandLedge(int[][] scene, boolean[][] visited, int x, int y) {
/* 134 */     if (visited[y][x]) {
/*     */       return;
/*     */     }
/* 137 */     visited[y][x] = true;
/* 138 */     int startx = x;
/* 139 */     int endx = x;
/*     */     
/* 141 */     while (startx > 0 && scene[startx - 1][y] == 2) {
/* 142 */       startx--;
/* 143 */       visited[y][startx] = true;
/*     */     } 
/*     */     
/* 146 */     while (endx < scene.length - 1 && scene[endx + 1][y] == 2) {
/* 147 */       endx++;
/* 148 */       visited[y][endx] = true;
/*     */     } 
/* 150 */     this.floors.add(new Edge(this.originX + startx * 16.0F, this.originY + y * 16.0F, 
/* 151 */           this.originX + (endx + 1) * 16.0F, this.originY + y * 16.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void coalesce() {
/* 159 */     coalesce(this.walls);
/* 160 */     coalesce(this.ceilings);
/* 161 */     coalesce(this.floors);
/* 162 */     coalesce(this.enemyEmitters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void coalesce(ArrayList<Edge> edges) {
/* 172 */     boolean foundOne = true;
/* 173 */     while (foundOne) {
/* 174 */       foundOne = false;
/* 175 */       for (Edge a : edges) {
/* 176 */         for (Edge b : edges) {
/* 177 */           if (a == b) {
/*     */             continue;
/*     */           }
/* 180 */           foundOne = true;
/* 181 */           if (a.x1 == b.x1 && a.y1 == b.y1)
/*     */           {
/* 183 */             throw new RuntimeException("Overlapping edges!"); } 
/* 184 */           if (a.x1 == b.x2 && a.y1 == b.y2) {
/* 185 */             a.x1 = b.x1;
/* 186 */             a.y1 = b.y1;
/* 187 */           } else if (a.x2 == b.x1 && a.y2 == b.y1) {
/* 188 */             a.x2 = b.x2;
/* 189 */             a.y2 = b.y2;
/*     */           } else {
/* 191 */             foundOne = false;
/*     */           } 
/*     */           
/* 194 */           if (foundOne) {
/* 195 */             edges.remove(b);
/*     */             break;
/*     */           } 
/*     */         } 
/* 199 */         if (foundOne) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void add(Scene subscene) {
/* 208 */     this.floors.addAll(subscene.floors);
/* 209 */     this.walls.addAll(subscene.walls);
/* 210 */     this.ceilings.addAll(subscene.ceilings);
/* 211 */     this.bumpables.addAll(subscene.bumpables);
/* 212 */     this.enemyEmitters.addAll(subscene.enemyEmitters);
/*     */   }
/*     */   
/*     */   private Scene() {}
/*     */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\spencerSchumann\Scene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */