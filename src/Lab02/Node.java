package Lab02;

public class Node {

    private String token;
    private Node leftToken;
    private Node rightToken;

    public Node(String token){
        this.token = token;
    }

    public void setLeftToken(Node node){
        leftToken = node;
    }

    public void setRightToken(Node node){
        rightToken = node;
    }

    public Node getLeftToken(){
        return leftToken;
    }

    public Node getRightToken(){
        return rightToken;
    }

    @Override
    public String toString() {
        return token;
    }
}
