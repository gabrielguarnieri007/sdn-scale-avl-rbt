public class RedBlackRouterTree {
    private enum Color {
        RED,
        BLACK
    }

    private final Node NIL = new Node(null, Color.BLACK);
    private Node root = NIL;
    private int size;
    private long rotationCount;

    private static class Node {
        private PacketRule rule;
        private Color color;
        private Node left;
        private Node right;
        private Node parent;

        private Node(PacketRule rule, Color color) {
            this.rule = rule;
            this.color = color;
        }
    }

    public RedBlackRouterTree() {
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;
        root = NIL;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == NIL;
    }

    public long getRotationCount() {
        return rotationCount;
    }

    public int height() {
        return height(root);
    }

    public String getRootColor() {
        if (root == NIL) {
            return "BLACK";
        }

        return root.color.name();
    }

    private int height(Node node) {
        if (node == NIL) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;

        node.right = rightChild.left;

        if (rightChild.left != NIL) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == NIL) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;

        rotationCount++;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;

        node.left = leftChild.right;

        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == NIL) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;

        rotationCount++;
    }
}