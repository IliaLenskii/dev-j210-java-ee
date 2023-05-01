package lessons2;

public class Lessons2 {

    public static void main(String[] args) {
        
        Tree tree = new Tree();

        tree.insert(10, 101);
        tree.insert(23, 230);
        tree.insert(26, 260);
        tree.insert(24, 240);
        tree.insert(9, 90);
        tree.insert(4, 40);
        tree.insert(3, 30);
        tree.insert(1, 10);
        tree.insert(999, 9990);
        
        System.out.println("__________");
        
        tree.symmetricTraversal();
        
        System.out.println("__________");
        
        System.out.println("min: "+ tree.getMinKey().getData() +", max: "+ tree.getMaxKey().getData());
        
        System.out.println("__________");
                
        tree.delete(26);
        
        tree.symmetricTraversal();
    }
}
