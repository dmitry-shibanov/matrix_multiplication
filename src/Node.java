public class Node {
    private double value;
    private int row;
    private int column;

    public Node(double value, int row, int column){
        this.value = value;
        this.row = row;
        this.column = column;
    }

    public Node(Node node, double greater){
        this.value = greater * node.getValue();
        this.row = node.getRow();
        this.column = node.getColumn();
    }

    public double getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private Node _next;
    private Node _previous;

    public Node(){}

    public Node(Node previous, Node next){
        _previous = previous;
        _next = next;
    }

    public void set_next(Node _next) {
        this._next = _next;
    }

    public void set_previous(Node _previous) {
        this._previous = _previous;
    }

    public Node next(){
        return _next;
    }

    public Node previous(){
        return _previous;
    }

    public boolean hasNext(){
        return _next == null ? false : true;
    }

    public boolean hasPrevious(){
        return _previous == null ? false : true;
    }
}
