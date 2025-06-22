package agents.robinBaumgarten;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import java.util.ArrayList;

public class AStarTree
{
    public AStarTree() {
        System.out.println(">>> AStarTree created");
    }
    
    public SearchNode bestPosition;
    public SearchNode furthestPosition;
    float currentSearchStartingMarioXPos;
    ArrayList<SearchNode> posPool;
    ArrayList<int[]> visitedStates = (ArrayList)new ArrayList<>();
    private boolean requireReplanning = false;

    //추가
    private float killWeight = 0;
    private float collectWeight = -3;
    private float jumpWeight = -3;
    private float timeWeight = 0;
    private float winWeight = -10;
    private float loseWeight = 10;

    private ArrayList<boolean[]> currentActionPlan;
    int ticksBeforeReplanning = 0;

    //추가
    public int SearchedStates = 0;
    public int SearchedLose = 0;
    public int maxTreeSize = 0;

    private MarioForwardModel search(MarioTimer timer) {

        /* 
        MarioTimer testTimer = new MarioTimer(100L);
        System.out.println("TestTimer(100ms) 남은 시간: " + testTimer.getRemainingTime() + "ms");
        try {
            Thread.sleep(10); // 10ms 소모
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("10ms 후 남은 시간: " + testTimer.getRemainingTime() + "ms");
        */

        System.out.println("[DEBUG] AStarTree.search() called!");
        // Thread.dumpStack();  // 호출 위치 스택 추적


        SearchNode current = this.bestPosition;
        boolean currentGood = false;
        int maxRight = 176;
        int loopCounter = 0;
        
        /* 
        System.out.println("posPool.size(): " + this.posPool.size());
        System.out.println("bestX: " + this.bestPosition.sceneSnapshot.getMarioFloatPos()[0]);
        System.out.println("startX: " + this.currentSearchStartingMarioXPos);
        System.out.println("deltaX: " + (this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos));
        System.out.println("currentGood: " + currentGood);
        System.out.println("timer.remainingTime: " + timer.getRemainingTime());
        */

        while (this.posPool.size() != 0 && (
            this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight || !currentGood) && 
            timer.getRemainingTime() > 0L) {

            loopCounter++;
            System.out.println("========== 루프 반복: " + loopCounter + " ==========");
            System.out.println("현재 posPool size: " + this.posPool.size());
            System.out.println("현재 bestPosition X: " + this.bestPosition.sceneSnapshot.getMarioFloatPos()[0]);

            current = pickBestPos(this.posPool);
            System.out.println("currentGood: " + currentGood);

            if (current == null) {
                System.out.println("current is null! 루프 종료");
                return null;
            }
            
            System.out.println("current == bestPosition? " + (current == this.bestPosition));
            System.out.println("current.parentPos: " + current.parentPos);
            System.out.println("current.parentPos.sceneSnapshot: " + (current.parentPos != null ? current.parentPos.sceneSnapshot : "null"));

            if (current.sceneSnapshot == null) {
                throw new IllegalStateException("[search] current.sceneSnapshot == null (선택된 current 노드)");
            }

            // System.out.println("선택된 current X: " + current.sceneSnapshot.getMarioFloatPos()[0]); // crash
            // System.out.println("선택된 current 노드. parent X: " + current.parentPos.sceneSnapshot.getMarioFloatPos()[0]);

            if (current.parentPos == null || current.parentPos.sceneSnapshot == null) {
                throw new IllegalStateException("[search] current 또는 parent의 sceneSnapshot이 null");
            }
            // 안전한 방식으로 출력
            float safeX = -1;
            try {
                safeX = current.parentPos.sceneSnapshot.getMarioFloatPos()[0];
            } catch (Exception e) {
                throw new IllegalStateException("[search] getMarioFloatPos() 호출 중 내부 필드 오류", e);
            }
            System.out.println("선택된 current 노드의 parent X: " + safeX);

            currentGood = false;

            float realRemainingTime = current.simulatePos();


            // 상태 카운트
            SearchedStates++;
            System.out.println("SearchedStates: " + SearchedStates);

            if (realRemainingTime < 0.0F) {
                System.out.println("realRemainingTime < 0.0F -> continue");
                continue;
            }

            if (!current.isInVisitedList && isInVisited((int)current.sceneSnapshot.getMarioFloatPos()[0], 
                (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed)) {
            
                realRemainingTime += 1500.0F;
                current.isInVisitedList = true;
                current.remainingTime = realRemainingTime;
                current.remainingTimeEstimated = realRemainingTime;
                this.posPool.add(current);

                System.out.println("이미 방문된 노드! posPool에 재추가됨");
            } 
            else if ((realRemainingTime - current.remainingTimeEstimated) > 0.1D) {
                current.remainingTimeEstimated = realRemainingTime;
                this.posPool.add(current);
                System.out.println("remainingTime 업데이트 -> posPool에 재추가됨");
            } 
            else {
                currentGood = true;
                visited((int)current.sceneSnapshot.getMarioFloatPos()[0], (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed);

                ArrayList<SearchNode> children = current.generateChildren();
                this.posPool.addAll(children);

                System.out.println("자식 노드 생성! children.size: " + children.size());
            }

            if (currentGood) {
                if (this.bestPosition.getRemainingTime() > current.getRemainingTime()) {
                    System.out.println("bestPosition 갱신!");
                    this.bestPosition = current;
                }
                if (current.sceneSnapshot.getMarioFloatPos()[0] > this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0]) {
                    System.out.println("furthestPosition 갱신!");
                    this.furthestPosition = current;
                }
            }

            this.maxTreeSize = Math.max(this.maxTreeSize, this.posPool.size() + this.visitedStates.size());
            System.out.println("maxTreeSize: " + this.maxTreeSize);
        }   

        System.out.println("루프 종료!");
        System.out.println("최종 bestPosition X: " + this.bestPosition.sceneSnapshot.getMarioFloatPos()[0]);
        System.out.println("최종 furthestPosition X: " + this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0]);
        System.out.println("최종 posPool size: " + this.posPool.size());
    
        return current.sceneSnapshot;
    }


    private void startSearch(MarioForwardModel model, int repetitions) {
        System.out.println(">>> startSearch called");
        SearchNode startPos = new SearchNode(null, repetitions, null);
        startPos.initializeRoot(model);
        this.posPool = new ArrayList<>();
        this.visitedStates.clear();
        this.posPool.addAll(startPos.generateChildren());
        this.currentSearchStartingMarioXPos = model.getMarioFloatPos()[0];
        this.bestPosition = startPos;
        this.furthestPosition = startPos;
    }

    private ArrayList<boolean[]> extractPlan() {
        ArrayList<boolean[]> actions = (ArrayList)new ArrayList<>();
        if (this.bestPosition == null) {
            for (int i = 0; i < 10; i++) {
                actions.add(Helper.createAction(false, true, false, false, true));
            }
            return actions;
        } 

        SearchNode current = this.bestPosition;
        while (current.parentPos != null) {
            for (int i = 0; i < current.repetitions; i++)
                actions.add(0, current.action); 
            if (current.hasBeenHurt) {
                this.requireReplanning = true;
            }
            current = current.parentPos;
        } 
        return actions;
    }

    private SearchNode pickBestPos(ArrayList<SearchNode> posPool) {
        System.out.println("pickBestPos 진입");
        SearchNode bestPos = null;
        float bestPosCost = 1.0E7F;
        for (SearchNode current : posPool) {
/* 
            float currentCost = current.getRemainingTime() + current.timeElapsed * 0.9F;
            if (currentCost < bestPosCost) {
                bestPos = current;
                bestPosCost = currentCost;
            } 
*/
            if(!current.check){
                current.check = true;
                SearchedStates++;
                if(current.ifLose() == 1){
                    SearchedLose++;
                }
            }
            float currentCost = killWeight * current.getkillrate() 
            + collectWeight * current.getCollectRate() 
            + jumpWeight * current.getJumpTimeRatio() 
            + timeWeight * current.getRemainingTimeRatio() 
            + winWeight * current.ifWin() 
            + loseWeight * current.ifLose();
            //System.out.println("Mario killed: " + current.getkilled() + " CurrentCost: "+ currentCost);
            if (currentCost < bestPosCost) {
                bestPos = current;
                bestPosCost = currentCost;
            }
        }    
        posPool.remove(bestPos);
        System.out.println("current 결과: " + bestPos);
        return bestPos;
    }

    public boolean[] optimise(MarioForwardModel model, MarioTimer timer) {
        int planAhead = 2;
        int stepsPerSearch = 2;
        MarioForwardModel originalModel = model.clone();

        this.ticksBeforeReplanning--;
        this.requireReplanning = false;
        if (this.ticksBeforeReplanning <= 0 || this.currentActionPlan.size() == 0 || this.requireReplanning) {
            this.currentActionPlan = extractPlan();
            if (this.currentActionPlan.size() < planAhead) {
                planAhead = this.currentActionPlan.size();
            }
            for (int i = 0; i < planAhead; i++) {
                model.advance(this.currentActionPlan.get(i));
            }
            startSearch(model, stepsPerSearch);
            this.ticksBeforeReplanning = planAhead;
        } 
        if (model.getGameStatus() == GameStatus.LOSE) {
            startSearch(originalModel, stepsPerSearch);
        }
        search(timer);
        boolean[] action = new boolean[5];
        if (this.currentActionPlan.size() > 0)
            action = this.currentActionPlan.remove(0); 
        return action;
    }

    private void visited(int x, int y, int t) {
        this.visitedStates.add(new int[] { x, y, t });
    }

    private boolean isInVisited(int x, int y, int t) {
        int timeDiff = 5;
        int xDiff = 2;
        int yDiff = 2;
        for (int[] v : this.visitedStates) {
            if (Math.abs(v[0] - x) < xDiff && Math.abs(v[1] - y) < yDiff && Math.abs(v[2] - t) < timeDiff && 
            t >= v[2]) {
                return true;
            }
        } 
        return false;
    }
}


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\robinBaumgarten\AStarTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */

 // /*     */   private MarioForwardModel search(MarioTimer timer) {
// /*  23 */     SearchNode current = this.bestPosition;
// /*  24 */     boolean currentGood = false;
// /*  25 */     int maxRight = 176;
// /*  26 */     while (this.posPool.size() != 0 && (
// /*  27 */       this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight || !currentGood) && 
// /*  28 */       timer.getRemainingTime() > 0L) {
// /*  29 */       current = pickBestPos(this.posPool);
// /*  30 */       if (current == null) {
// /*  31 */         return null;
// /*     */       }
// /*  33 */       currentGood = false;
// /*  34 */       float realRemainingTime = current.simulatePos();
// /*     */       //추가
//                 SearchedStates++;
//                 System.out.println("posPool size: " + this.posPool.size());

// /*  36 */       if (realRemainingTime < 0.0F)
// /*     */         continue; 
// /*  38 */       if (!current.isInVisitedList && isInVisited((int)current.sceneSnapshot.getMarioFloatPos()[0], 
// /*  39 */           (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed)) {
// /*  40 */         realRemainingTime += 1500.0F;
// /*  41 */         current.isInVisitedList = true;
// /*  42 */         current.remainingTime = realRemainingTime;
// /*  43 */         current.remainingTimeEstimated = realRemainingTime;
// /*  44 */         this.posPool.add(current);
// /*  45 */       } else if ((realRemainingTime - current.remainingTimeEstimated) > 0.1D) {
// /*     */         
// /*  47 */         current.remainingTimeEstimated = realRemainingTime;
// /*  48 */         this.posPool.add(current);
// /*     */       } else {
// /*  50 */         currentGood = true;
// /*  51 */         visited((int)current.sceneSnapshot.getMarioFloatPos()[0], (int)current.sceneSnapshot.getMarioFloatPos()[1], current.timeElapsed);
// /*  52 */         this.posPool.addAll(current.generateChildren());
// /*     */       } 
// /*  54 */       if (currentGood) {
// /*  55 */         if (this.bestPosition.getRemainingTime() > current.getRemainingTime())
// /*  56 */           this.bestPosition = current; 
// /*  57 */         if (current.sceneSnapshot.getMarioFloatPos()[0] > this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0]) {
// /*  58 */           this.furthestPosition = current;
// /*     */         }
// /*     */       } 
// /*  61 */       this.maxTreeSize = Math.max(this.maxTreeSize, this.posPool.size() + this.visitedStates.size());
// /*     */     } 
// /*     */ 
// /*     */     
// /*  65 */     if (current.sceneSnapshot.getMarioFloatPos()[0] - this.currentSearchStartingMarioXPos < maxRight && 
// /*  66 */       this.furthestPosition.sceneSnapshot.getMarioFloatPos()[0] > this.bestPosition.sceneSnapshot.getMarioFloatPos()[0] + 20.0F)
// /*     */     {
// /*  68 */       this.bestPosition = this.furthestPosition;
// /*     */     }
// /*  70 */     return current.sceneSnapshot;
// /*     */   }