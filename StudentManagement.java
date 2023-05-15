import java.util.*;

public class StudentManagement {
    private AVL tree;
    private Stack<Node> undoState;
    private Stack<Node> redoState;
    private AVL CopyTree = new AVL();
    private Node newRoot = new Node();

    public StudentManagement() {
        this.tree = new AVL();
        this.undoState = new Stack<Node>();
        this.redoState = new Stack<Node>();
    }

    public AVL getTree() {
        return this.tree;
    }

    // Requirement 1
    public boolean addStudent(Student st) {
        // code here
        ArrayList<Node> listStudent = tree.NLR();
        for (Node student : listStudent) {
            if (student.getData().compareTo(st) == 0) {
                return false;
            }
        }
        newRoot = CopyTree.cloneTree(tree.getRoot());
        undoState.push(newRoot);
        tree.insert(st);
        return true;
    }

    // Requirement 2
    public Student searchStudentById(int id) {
        // code here
        Node data = tree.search(id);
        return (data == null) ? null : data.getData();
    }

    // Requirement 3
    public boolean removeStudent(int id) {
        // code here
        Node data = tree.search(id);
        if (data == null) {
            return false;
        } else {
            newRoot = CopyTree.cloneTree(tree.getRoot());
            undoState.push(newRoot);
            tree.delete(data.getData());
            return true;
        }
    }

    // Requirement 4
    public void undo() {
        // code here
        newRoot = CopyTree.cloneTree(tree.getRoot());
        redoState.push(newRoot);
        if (!undoState.isEmpty()) {
            tree.setRoot(undoState.pop());
        }
    }

    // Requirement 5
    public void redo() {
        // code here
        newRoot = CopyTree.cloneTree(tree.getRoot());
        undoState.push(newRoot);
        if (!redoState.isEmpty()) {
            tree.setRoot(redoState.pop());
        }
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL tree) {
        // code here
        ScoreAVL scoreTree = new ScoreAVL();
        ArrayList<Node> listStudent = tree.levelOrder();
        for (Node s : listStudent) {
            scoreTree.insert(s.getData());
        }

        return scoreTree;
    }
}
