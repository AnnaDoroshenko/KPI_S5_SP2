package Lab02;

import java.util.ArrayList;

public class MooreMachine {
    private State state;
    private State currentState;
    private ArrayList statesAndSigns = new ArrayList();

    public MooreMachine(State state) {
        this.state = state;
        currentState = state;
        statesAndSigns.add(currentState.name());
    }


    public void executeMachine(Signal signal) {
        switch (signal) {
            case DLM:
                if (currentState == State.STATE5) {
                    currentState = State.STATE7;
                } else if (state != State.STATE9) {
                    currentState = State.getNextState(currentState);
                }
                break;

            case CFR:
                if (currentState == State.STATE3) {
                    currentState = State.STATE3;
                } else if (state != State.STATE9) {
                    currentState = State.getNextState(currentState);
                }
                break;

            case LTR:
                if (currentState == State.STATE9) {
                    currentState = State.STATE4;
                } else if (state != State.STATE9) {
                    currentState = State.getNextState(currentState);
                }
                break;
        }
        statesAndSigns.add(signal.name());
        if (currentState == State.STATE9){
            statesAndSigns.add("The final state: " + currentState.name());
        } else {
            statesAndSigns.add(currentState.name());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object str : statesAndSigns) {
            sb.append(str);
            sb.append(" -> ");
        }
        sb.append("end");

        return sb.toString();
    }
}
