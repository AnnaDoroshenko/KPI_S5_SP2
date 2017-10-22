package Lab02;

import java.util.Arrays;

public enum State {
    STATE2, STATE3, STATE4, STATE5,
    STATE6, STATE7, STATE8, STATE9;

    public static State[] STATEARRAY = new State[] {null, null, STATE2,
            STATE3, STATE4, STATE5, STATE6, STATE7, STATE8, STATE9};

    public static int getIndex(State state) {
        return Arrays.asList(STATEARRAY).indexOf(state);
    }

    public static State getNextState(State currentState){
        int index = getIndex(currentState);

        if(index != 9){
            index += 1;
        }

        return STATEARRAY[index];
    }
}


