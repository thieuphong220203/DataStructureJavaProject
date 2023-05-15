public class ScoreAVL extends AVL {

    public ScoreAVL() {
        super();
    }

    public void insert(Student key) {
        this.root = insert(this.root, key);
    }

    private Node insert(Node node, Student key) {
        // code here
        if (node == null) {
            return new Node(key);
        }

        if (node.getData().compareToByScore(key) > 0) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (node.getData().compareToByScore(key) < 0) {
            node.setRight((insert(node.getRight(), key)));
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        return balance(node);
    }
}
