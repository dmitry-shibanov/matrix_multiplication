public class CustomRow {

    private Node first;
    private Node last;
    private Node current;
    private int columnCount;
    private int length;

    public CustomRow(Node first){
        this.first = first;
        current = first;
        last = first;
        length = 1;
    }

    public CustomRow(){}

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public Node addNode(Node node){
        if(first == null){
            first = node;
            current = first;
            length = 1;
            return current;
        }
        current.set_next(node);
        node.set_previous(current);
        node.set_next(null);
        last = node;
        current = node;
        length++;
        return last;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }
}
