package Lab02;

public class Tree {

    Node node;

    public Tree(){
        createTree();
        printNode(node);
    }

    public void createTree(){

        Node node1 = new Node("repeat ");
        Node node2 = new Node("n");
        Node node3 = new Node(":=");
        Node node4 = new Node("n");
        Node node5 = new Node("-");
        Node node6 = new Node("1");

        node2.setLeftToken(node1);
        node3.setLeftToken(node2);
        node5.setLeftToken(node4);
        node5.setRightToken(node6);
        node3.setRightToken(node5);

        Node node12 = new Node(" or ");

        Node node8 = new Node("(");
        Node node9 = new Node("n");
        Node node10 = new Node("=");
        Node node11 = new Node("0");

        node9.setLeftToken(node8);
        node10.setLeftToken(node9);
        node10.setRightToken(node11);
        node12.setLeftToken(node10);

        Node node13 = new Node("b");
        Node node14 = new Node("!=");
        Node node15 = new Node("a");
        Node node16 = new Node("[");
        Node node17 = new Node("n");
        Node node18 = new Node("]");
        Node node19 = new Node(")");
        Node node20 = new Node(";");

        node16.setLeftToken(node15);
        node17.setLeftToken(node16);
        node19.setRightToken(node20);
        node18.setRightToken(node19);
        node17.setRightToken(node18);
        node14.setLeftToken(node13);
        node14.setRightToken(node17);

        node12.setRightToken(node14);

        Node node7 = new Node(" until ");

        node7.setRightToken(node12);
        node7.setLeftToken(node3);

        node = node7;
    }

    public void printNode(Node node) {
        if(node.getLeftToken() != null){
            printNode(node.getLeftToken());
        }
        System.out.print(node);
        if(node.getRightToken() != null){
            printNode(node.getRightToken());
        }
    }
}
