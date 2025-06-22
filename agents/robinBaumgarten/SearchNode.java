package agents.robinBaumgarten;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchNode
{
    public int timeElapsed = 0;
    public float remainingTimeEstimated = 0.0F;
    public float remainingTime = 0.0F;

    // 추가
    // SearchNode 클래스에 이 필드를 추가
    public boolean check = false;

  
    public SearchNode parentPos = null;
    public MarioForwardModel sceneSnapshot = null;
    public int distanceFromOrigin = 0;

    public boolean hasBeenHurt = false;
    public boolean isInVisitedList = false;
    boolean[] action;
    int repetitions = 1;

    public float calcRemainingTime(float marioX, float marioXA) {
        return (100000.0F - maxForwardMovement(marioXA, 1000) + marioX) / 10.909091F - 1000.0F;
    }

    public float getRemainingTime() {
        if (this.remainingTime > 0.0F) {
            return this.remainingTime;
        }
        return this.remainingTimeEstimated;
    }

    public float estimateRemainingTimeChild(boolean[] action, int repetitions) {
        float[] childbehaviorDistanceAndSpeed = Helper.estimateMaximumForwardMovement(
        this.sceneSnapshot.getMarioFloatVelocity()[0], action, repetitions);
        return calcRemainingTime(this.sceneSnapshot.getMarioFloatPos()[0] + childbehaviorDistanceAndSpeed[0], 
        childbehaviorDistanceAndSpeed[1]);
    }

    public SearchNode(boolean[] action, int repetitions, SearchNode parent) {
        this.parentPos = parent;
        this.action = action;
        this.repetitions = repetitions;
        
        if (parent != null) {
            this.remainingTimeEstimated = parent.estimateRemainingTimeChild(action, repetitions);
            parent.distanceFromOrigin++;
            parent.timeElapsed += repetitions;

            // sceneSnapshot 복사 추가
            if (parent.sceneSnapshot == null) {
                throw new IllegalStateException("부모 노드의 sceneSnapshot이 null입니다. 루트 노드를 initializeRoot() 하지 않고 자식 노드를 만들고 있습니다.");
            }
            this.sceneSnapshot = parent.sceneSnapshot.clone();
        } else {
            this.timeElapsed = 0;
        }
        if (this.parentPos != null && this.parentPos.sceneSnapshot == null) {
            throw new IllegalStateException("부모 노드의 sceneSnapshot이 null입니다. 루트 노드를 initializeRoot() 하지 않고 자식 노드를 만들고 있습니다.");
        }
    }

    

    public void initializeRoot(MarioForwardModel model) {
        if (this.parentPos == null) {
            this.sceneSnapshot = model.clone();
            this.remainingTimeEstimated = calcRemainingTime(model.getMarioFloatPos()[0], 0.0F);
        } 
    }
  
    public float simulatePos() {
        System.out.println(">>> simulatePos() 진입");

        if (this.sceneSnapshot == null) {
            throw new IllegalStateException("simulatePos 호출 시 sceneSnapshot이 null입니다. 자식 노드 생성 시 초기화 누락");
        }

        if (this.parentPos == null) {
            throw new IllegalStateException("simulatePos() 호출: parentPos가 null입니다.");
        }
        if (this.parentPos.sceneSnapshot == null) {
            throw new IllegalStateException("simulatePos() 호출: parentPos.sceneSnapshot이 null입니다.");
        }

        this.sceneSnapshot = this.parentPos.sceneSnapshot.clone();
        System.out.println("simulatePos: action = " + Arrays.toString(this.action));
        System.out.println("before advance: x = " + this.sceneSnapshot.getMarioFloatPos()[0]);
        for (int i = 0; i < this.repetitions; i++) {
            this.sceneSnapshot.advance(this.action);
        }
        System.out.println("after advance: x = " + this.sceneSnapshot.getMarioFloatPos()[0]);
        int marioDamage = Helper.getMarioDamage(this.sceneSnapshot, this.parentPos.sceneSnapshot);
        this.remainingTime = 
            calcRemainingTime(this.sceneSnapshot.getMarioFloatPos()[0], this.sceneSnapshot.getMarioFloatVelocity()[0]) + (
            marioDamage * (1000000 - 100 * this.distanceFromOrigin));
        if (this.isInVisitedList)
            this.remainingTime += 1500.0F; 
        this.hasBeenHurt = (marioDamage != 0);
     
        return this.remainingTime;
    }
   
    public ArrayList<SearchNode> generateChildren() {
        System.out.println(">>> generateChildren 호출됨");
        if (this.sceneSnapshot == null) {
            throw new IllegalStateException("sceneSnapshot이 null입니다. generateChildren() 호출 전에 반드시 initializeRoot()를 호출해야 합니다.");
        }
        ArrayList<SearchNode> list = new ArrayList<>();
        ArrayList<boolean[]> possibleActions = Helper.createPossibleActions(this);
        if (isLeafNode()) {
            possibleActions.clear();
        }
        for (boolean[] action : possibleActions) {
            list.add(new SearchNode(action, this.repetitions, this));
        }
        System.out.println("생성된 children 수: " + list.size());
        return list;
    }
   
    public boolean isLeafNode() {
        if (this.sceneSnapshot == null) {
            return false;
        }
        return (this.sceneSnapshot.getGameStatus() != GameStatus.RUNNING);
    }
   
    private float maxForwardMovement(float initialSpeed, int ticks) {
        float y = ticks;
        float s0 = initialSpeed;
        return (float)(99.17355373D * Math.pow(0.89D, (y + 1.0F)) - 9.090909091D * s0 * Math.pow(0.89D, (y + 1.0F)) + 10.90909091D * y - 
        88.26446282D + 9.090909091D * s0);
    }

            //추가
            public float getRemainingTimeRatio() {
                return 1 - getRemainingTime() / 2500f;
            }
        
            public int getkilled() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                return this.sceneSnapshot.getKillsTotal();
            }
        
            public float getkillrate() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                if(this.sceneSnapshot.getTotalEnemies() == 0){
                    return 0;
                }
                return (float) this.sceneSnapshot.getKillsTotal()/ (float) this.sceneSnapshot.getTotalEnemies();
            }
        
            public int getTotalEnemies() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                return this.sceneSnapshot.getTotalEnemies();
            }
        
            public float getCollectRate() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                if(this.sceneSnapshot.getTotalCoins() == 0){
                    return 0;
                }
                return (float)this.sceneSnapshot.getNumCollectedCoins()/(float) this.sceneSnapshot.getTotalCoins();
            }
        
            public float getJumpTimeRatio() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                return this.sceneSnapshot.getJumpTimeRatio();
            }
        
            public int ifWin() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                if(this.sceneSnapshot.getGameStatus() == GameStatus.WIN){
                    return 1;
                }
                return 0;
            }
        
            public int ifLose() {
                if (this.sceneSnapshot == null) {
                    return 0;
                }
                if(this.sceneSnapshot.getGameStatus() == GameStatus.LOSE){
                    return 1;
                }
                return 0;
            }
}


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\agents\robinBaumgarten\SearchNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */