public class StudentBST {
    private class Node {
        Student student;
        Node left, right;
        Node(Student s) { student = s; }
    }

    private Node root;

    public void insert(Student s) {
        root = insertRecursive(root, s);
    }

    private Node insertRecursive(Node node, Student s) {
        if (node == null) return new Node(s);
        if (s.compareTo(node.student) < 0)
            node.left = insertRecursive(node.left, s);
        else if (s.compareTo(node.student) > 0)
            node.right = insertRecursive(node.right, s);
        return node;
    }

    public Student search(int id) {
        return searchRecursive(root, id);
    }

    private Student searchRecursive(Node node, int id) {
        if (node == null) return null;
        if (node.student.getId() == id) return node.student;
        if (id < node.student.getId())
            return searchRecursive(node.left, id);
        else
            return searchRecursive(node.right, id);
    }

    public Student findHighestGrade() {
        return findExtremeGrade(root, true, null);
    }

    public Student findLowestGrade() {
        return findExtremeGrade(root, false, null);
    }

    private Student findExtremeGrade(Node node, boolean findMax, Student current) {
        if (node == null) return current;
        if (current == null || (findMax && node.student.getGrade() > current.getGrade()) || (!findMax && node.student.getGrade() < current.getGrade())) {
            current = node.student;
        }
        current = findExtremeGrade(node.left, findMax, current);
        return findExtremeGrade(node.right, findMax, current);
    }
}
