package Guest;

import java.util.Scanner;

public class Guest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVL_Tree tree = new AVL_Tree();
        String s = null;
        System.out.print("\nInstruction:");
        s = sc.next();
        while (s.equals("search") || s.equals("insert") || s.equals("delete") || s.equals("showTree")){
            if (s.equals("search")){
                int key = sc.nextInt();
                Node node = tree.search(tree.root,key);
                if (node != null)
                    System.out.println("name: "+node.name);
                else
                    System.out.println("not found");
            }
            else if (s.equals("insert")){
                String name = sc.next();
                int key = sc.nextInt();
                tree.root = tree.insert(tree.root,key,name);
            }
            else if (s.equals("delete")){
                String name = sc.next();
                int key = tree.preOrder(tree.root,name);
                if (key != 0)
                    tree.root = tree.delete(tree.root,key);
            }
            else if (s.equals("showTree")){
                tree.printBinaryTree(tree.root);
            }
            else
                break;
            System.out.print("Instruction:");
            s = sc.next();
        }
    }
}
