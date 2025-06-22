package engine.core;
 
 
public class MarioTimer
{
    private long startTimer;
    private long remainingTime;

    public MarioTimer(long remainingTime) {
        this.startTimer = System.currentTimeMillis();
        this.remainingTime = remainingTime;
    }

    public long getRemainingTime() {
        long elapsed = System.currentTimeMillis() - this.startTimer;
        return Math.max(0L, this.remainingTime - elapsed);
    } // add 250601
}
 
/* 
   public long getRemainingTime() {
     return Math.max(0L, this.remainingTime - System.currentTimeMillis() - this.startTimer);
   }
*/



          

/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */