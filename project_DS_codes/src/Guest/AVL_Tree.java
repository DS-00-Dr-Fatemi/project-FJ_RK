package Guest;

  import java.util.LinkedList;

public class AVL_Tree {
    Node root;
    int heightOfNode(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(heightOfNode(y.left), heightOfNode(y.right)) + 1;
        x.height = Math.max(heightOfNode(x.left), heightOfNode(x.right)) + 1;
        return x;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(heightOfNode(x.left), heightOfNode(x.right)) + 1;
        y.height = Math.max(heightOfNode(y.left), heightOfNode(y.right)) + 1;
        return y;
    }
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return heightOfNode(node.left) - heightOfNode(node.right);
    }
    Node search(Node node, int key){
        if (node == null)
            return null;
        if (key < node.key) {
            node.left = search(node.left, key);
            return node.left;
        }
        else if (key > node.key) {
            node.right = search(node.right, key);
            return node.right;
        }

        else
            return node;
    }
    Node insert(Node node, int key,String name){
        if (node == null)
            return (new Node(key,name));

        if (key < node.key)
            node.left = insert(node.left, key,name);
        else if (key > node.key)
            node.right = insert(node.right, key,name);
        else
            return node;

        node.height = 1 + Math.max(heightOfNode(node.left),
                heightOfNode(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }
    Node delete(Node node, int key){
        if (node == null)
            return node;

        if (key < node.key)
            node.left = delete(node.left, key);

        else if (key > node.key)
            node.right = delete(node.right, key);

        else
        {
            if ((node.left == null) || (node.right == null))
            {
                Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                if (temp == null)
                {
                    temp = node;
                    node = null;
                }
                else
                    node = temp;
            }
            else
            {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.name = temp.name;
                node.right = delete(node.right, temp.key);
            }
        }
        if (node == null)
            return node;

        node.height = Math.max(heightOfNode(node.left), heightOfNode(node.right)) + 1;

        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.right) > 0)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    int preOrder(Node node,String name) {
        if (node != null) {
            if (node.name.equals(name))
                return node.key;
            preOrder(node.left,name);
            preOrder(node.right,name);
        }
        return 0;
    }
    void printBinaryTree(Node node) {
        LinkedList<Node> treeLevel = new LinkedList<Node>();
        treeLevel.add(node);
        LinkedList<Node> temp = new LinkedList<Node>();
        int counter = 0;
        int height = heightOfTree(node)-1;
        double numberOfElements = (Math.pow(2 , (height + 1)) - 1);
        while (counter <= height) {
            Node remove_node = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements / Math.pow(2 , counter + 1), remove_node);
            } else {
                printSpace(numberOfElements / Math.pow(2 , counter), remove_node);
            }
            if (remove_node == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(remove_node.left);
                temp.add(remove_node.right);
            }

            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }

        }
    }
    public static void  printSpace(double m, Node removed){
        for(double n=m;n>0;n--) {
             System.out.print("\t");
        }
        if(removed == null){
            System.out.print(" ");
        }
        else {
            System.out.print(removed.name+"-"+removed.key);
        }
    }
    public static int heightOfTree(Node root){
        if(root==null){
            return 0;
        }
        return 1+ Math.max(heightOfTree(root.left),heightOfTree(root.right));
    }
}
class Node {
    int key, height;
    Node left, right;
    String name;
    Node(int d,String name) {
        key = d;
        this.name = name;
        height = 1;
        left = null;
        right = null;
    }
}


