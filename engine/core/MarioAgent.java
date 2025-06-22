package engine.core;

public interface MarioAgent {
  void initialize(MarioForwardModel paramMarioForwardModel, MarioTimer paramMarioTimer);
  
  boolean[] getActions(MarioForwardModel paramMarioForwardModel, MarioTimer paramMarioTimer);
  
  String getAgentName();
}


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */