
package lessons2;

public class Node {
    
    private int iData; // add generic - <T>
    private int key;
    
    private Node left;
    private Node right;
    
    private String GUID;

    public Node(int key, int iData) {

        this.key = key;
        this.iData = iData;
        
        GUID = java.util.UUID.randomUUID().toString();
    }
    
    public int getKey() {
        
        return key;
    }

    public int getData() {
        
        return iData;
    }
    
    public Node getLeft() {
        
        return left;
    }
    
    public Node getRight() {
        
        return right;
    }

    public Node setLeft(Node l) {
        
        return left = l;
    }
    
    public Node setRight(Node r) {
        
        return right = r;
    }
    
    public String getGUID() {
        
        return GUID;
    }
}
