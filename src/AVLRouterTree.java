public class AVLRouterTree {
    private Node root;
    private int size;
    private long rotationCount;

    private static class Node {
        private PacketRule rule;
        private Node left;
        private Node right;
        private int height;

        private Node(PacketRule rule) {
            this.rule = rule;
            this.height = 1;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    public long getRotationCount() {
        return rotationCount;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        Node movedSubtree = newRoot.right;

        newRoot.right = node;
        node.left = movedSubtree;

        updateHeight(node);
        updateHeight(newRoot);

        rotationCount++;
        return newRoot;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        Node movedSubtree = newRoot.left;

        newRoot.left = node;
        node.right = movedSubtree;

        updateHeight(node);
        updateHeight(newRoot);

        rotationCount++;
        return newRoot;
    }

    private Node rebalance(Node node) {
        updateHeight(node);

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        return node;
    }
}