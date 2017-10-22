/**
 * System programming
 * Lab 2. CONSTRUCTION AND USE OF OBJECTS OF TREE-LIKE
 * AND HIERARCHIC GRAPHS
 *
 * @variant: 16
 * @author: Anna Doroshenko
 * @group: IO-52
 * @date: 09.10.2017
 */

package Lab02;

public class Lab02 {

    public static void main(String[] args) {
        State startState = State.STATE2;
        MooreMachine machine = new MooreMachine(startState);
        machine.executeMachine(Signal.CFR);
        machine.executeMachine(Signal.CFR);
        machine.executeMachine(Signal.DLM);
        machine.executeMachine(Signal.DLM);
        machine.executeMachine(Signal.LTR);
        machine.executeMachine(Signal.LTR);
        machine.executeMachine(Signal.DLM);
        machine.executeMachine(Signal.CFR);
        machine.executeMachine(Signal.DLM);
        System.out.println(machine);

        System.out.println("\t");

        Tree tree = new Tree();

        System.out.println("\t");
    }
}
