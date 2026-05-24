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

    public boolean insert(PacketRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("A regra não pode ser nula.");
        }

        Node parent = NIL;
        Node current = root;

        while (current != NIL) {
            parent = current;

            if (rule.getId() < current.rule.getId()) {
                current = current.left;
            } else if (rule.getId() > current.rule.getId()) {
                current = current.right;
            } else {
                current.rule = rule;
                return false;
            }
        }

        Node newNode = new Node(rule, Color.RED);
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.parent = parent;

        if (parent == NIL) {
            root = newNode;
        } else if (rule.getId() < parent.rule.getId()) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
        fixInsert(newNode);

        return true;
    }

    public PacketRule search(int id) {
        Node current = root;

        while (current != NIL) {
            if (id < current.rule.getId()) {
                current = current.left;
            } else if (id > current.rule.getId()) {
                current = current.right;
            } else {
                return current.rule;
            }
        }

        return null;
    }

    public boolean contains(int id) {
        return search(id) != null;
    }

    public boolean delete(int id) {
        Node node = findNode(id);

        if (node == NIL) {
            return false;
        }

        delete(node);
        size--;
        return true;
    }

    private Node findNode(int id) {
        Node current = root;

        while (current != NIL) {
            if (id < current.rule.getId()) {
                current = current.left;
            } else if (id > current.rule.getId()) {
                current = current.right;
            } else {
                return current;
            }
        }

        return NIL;
    }

    private void delete(Node node) {
        Node removedNode = node;
        Color originalColor = removedNode.color;
        Node replacement;

        if (node.left == NIL) {
            replacement = node.right;
            transplant(node, node.right);
        } else if (node.right == NIL) {
            replacement = node.left;
            transplant(node, node.left);
        } else {
            removedNode = findMin(node.right);
            originalColor = removedNode.color;
            replacement = removedNode.right;

            if (removedNode.parent == node) {
                replacement.parent = removedNode;
            } else {
                transplant(removedNode, removedNode.right);
                removedNode.right = node.right;
                removedNode.right.parent = removedNode;
            }

            transplant(node, removedNode);
            removedNode.left = node.left;
            removedNode.left.parent = removedNode;
            removedNode.color = node.color;
        }

        if (originalColor == Color.BLACK) {
            fixDelete(replacement);
        }
    }

    private void fixInsert(Node node) {
        while (node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }

                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;

                if (uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }

                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }

        root.color = Color.BLACK;
    }

    private void fixDelete(Node node) {
        while (node != root && node.color == Color.BLACK) {
            if (node == node.parent.left) {
                Node sibling = node.parent.right;

                if (sibling == NIL) {
                    node = node.parent;
                    continue;
                }

                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }

                if (sibling.left.color == Color.BLACK && sibling.right.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    if (sibling.right.color == Color.BLACK) {
                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                Node sibling = node.parent.left;

                if (sibling == NIL) {
                    node = node.parent;
                    continue;
                }

                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }

                if (sibling.right.color == Color.BLACK && sibling.left.color == Color.BLACK) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    if (sibling.left.color == Color.BLACK) {
                        sibling.right.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }

        node.color = Color.BLACK;
    }

    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == NIL) {
            root = newNode;
        } else if (oldNode == oldNode.parent.left) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }

        newNode.parent = oldNode.parent;
    }

    private Node findMin(Node node) {
        Node current = node;

        while (current.left != NIL) {
            current = current.left;
        }

        return current;
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