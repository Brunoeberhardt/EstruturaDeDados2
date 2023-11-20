
import java.util.Random;

class TreeNode {

    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Função para realizar a rotação direita
    private TreeNode rightRotate(TreeNode root) {
    if (root == null || root.left == null) {
        return root;
    }

    TreeNode newRoot = root.left;
    root.left = newRoot.right;

    if (newRoot.right != null) {
        newRoot.right = root;
    }

    return newRoot;
}

    private TreeNode leftRotate(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    public void balanceDSW() {
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;

        int n = countNodes(dummy.right);
        int m = (int) Math.pow(2, (int) (Math.log(n + 1) / Math.log(2))) - 1;

        TreeNode current = dummy;

        for (int i = 0; i < n - m; i++) {
            current = rightRotate(current);
            if (current.right != null) {
                current = current.right;
            }
        }

        while (m > 1) {
            m /= 2;
            current = dummy;

            for (int i = 0; i < n - m; i++) {
                current = rightRotate(current);
                if (current.right != null) {
                    current = current.right;
                }
            }
        }

        root = dummy.right;
    }


    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Random random = new Random();

       
        for (int i = 0; i < 100; i++) {
            tree.insert(random.nextInt(101));
        }

        System.out.println("arvore original:");
        tree.inOrderTraversal(tree.root);
        System.out.println();

    
        tree.balanceDSW();

        System.out.println("arvore balanceada:");
        tree.inOrderTraversal(tree.root);
        System.out.println();


        for (int i = 0; i < 20; i++) {
            tree.insert(random.nextInt(101));
        }

        System.out.println("arvore com 20 numeros adicionados:");
        tree.inOrderTraversal(tree.root);
        System.out.println();


        tree.balanceDSW();

        System.out.println("arvore balanceada apos adicao de 20 numeros:");
        tree.inOrderTraversal(tree.root);
    }
}
