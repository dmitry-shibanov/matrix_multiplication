public class CustomRow {

    private Node first;
    private Node last;
    private Node current;

    public CustomRow(Node first){
        this.first = first;
        current = first;
    }

    public CustomRow(){}

    public Node addNode(Node node){
        current.set_next(node);
        node.set_previous(current);
        node.set_next(null);
        last = node;
        return node;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }
}
