package SpellChecker;

class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left, right;
        Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, T value) {
        if (node == null) return new Node(value);
        if (value.compareTo(node.data) < 0)
            node.left = insertRec(node.left, value);
        else if (value.compareTo(node.data) > 0)
            node.right = insertRec(node.right, value);
        return node;
    }

    public boolean contains(T value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node node, T value) {
        if (node == null) return false;
        if (value.compareTo(node.data) == 0) return true;
        return value.compareTo(node.data) < 0 ?
                containsRec(node.left, value) :
                containsRec(node.right, value);
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }
}
