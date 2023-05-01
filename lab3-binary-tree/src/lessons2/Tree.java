
package lessons2;

public class Tree {
    
    private Node root;
    
    public Node find(int k) {
        int data;
        Node curr = root;

        while((data = curr.getData()) != k) {
            
            if(data < k)
                curr = curr.getLeft();
            else
                curr = curr.getRight();
            
            if(curr == null) // ???
                return null;
        }
        
        return curr;
    }
    
    public Node insert(int k, int iData) { // add generics from Node - <T>
        Node nNode = new Node(k, iData);
        
        int c = 0;
        
        if(root == null) {

            root = nNode;
            
            System.out.println("root: "+ c);
            
            return root;
        }
        
        Node curr = root;
        Node parr;

        while(true) {
            
            ++c;
            
            parr = curr;
            
            if(k < curr.getKey()) {
                
                curr = curr.getLeft();
                
                if(curr == null) {
                    
                    System.out.println("left: "+ c +", parr: "+ parr.getKey());
                    
                    return parr.setLeft(nNode);
                }

            } else {
                
                curr = curr.getRight();
                
                if(curr == null) {
                    
                    System.out.println("right: "+ c +", parr: "+ parr.getKey());
                    
                    return parr.setRight(nNode);
                }
            }
        }
    }
    
    public boolean delete(int k) {
        Node curr = root;
        Node parr = root;
        boolean isLeftChild = true;
        int currKey;
        
        while((currKey = curr.getKey()) != k) {
            
            parr = curr;
            
            if(k < currKey) {
                
                curr = curr.getLeft();
                isLeftChild = true;
                
            } else {
                
                curr = curr.getRight();
                isLeftChild = false;
            }
            
            if(curr == null)
                return false;
        }
        
        // leaf
        if(curr.getLeft() == null && curr.getRight() == null) {
            
            if(curr == root)
                root = null;
            else if(isLeftChild)
                parr.setLeft(null);
            else
                parr.setRight(null);

        } else if(curr.getRight() == null) {
            
            if(curr == root)
                root = curr.getLeft();
            else if(isLeftChild)
                parr.setLeft( curr.getLeft() );
            else
                parr.setRight( curr.getLeft() );

        } else if(curr.getLeft() == null) {

            if(curr == root)
                root = curr.getRight();
            else if(isLeftChild)
                parr.setLeft( curr.getRight() );
            else
                parr.setRight( curr.getRight() );

        } else { // two childs

            Node successor = getSuccessor(curr);
            
            if(curr == root)
                root = successor;
            else if(isLeftChild)
                parr.setLeft( successor );
            else
                parr.setRight( successor );

            successor.setLeft( curr.getLeft() );
        }

        return true;
    }

    private Node getSuccessor(Node remNode) {

        Node successorParent = remNode;
        Node successor = remNode;
        Node curr = remNode.getRight();

        while(curr != null) {

            successorParent = successor;
            successor = curr;
            curr = curr.getRight();
        }        

        if(successor != remNode.getRight()) {

            successorParent.setLeft( successor.getRight() );
            successor.setRight( remNode.getRight() );
        }

        return successor;
    }

    public Node getMinKey() {
        Node last = null;
        Node curr = root;

        while(curr != null) {

            last = curr;
            curr = curr.getLeft();
        }

        return last;
    }
    
    public Node getMaxKey() {
        Node last = null;
        Node curr = root;

        while(curr != null) {

            last = curr;
            curr = curr.getRight();
        }

        return last;
    }
    
    public void symmetricTraversal() {
        
        inOrder(root);
    }
    
    private void inOrder(Node root) {
        
        if(root == null)
            return;

        inOrder(root.getLeft());
        
        System.out.println(root.getKey() +" - "+ root.getData());
        
        inOrder(root.getRight());
    }
}


