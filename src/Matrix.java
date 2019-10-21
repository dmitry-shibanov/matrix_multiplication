
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private interface Operation {
        double execute(double x, double y);
    }

    private List<CustomRow> matrixList;
    private int rowCount;
    private int columnCount;

    public Matrix(List<CustomRow> matrixList, int rowCount, int columnCount) {
        this.matrixList = matrixList;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public Matrix(List<CustomRow> matrixList) {
        this.matrixList = matrixList;
    }

    public List<CustomRow> getMatrixList() {
        return matrixList;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws NotMultiplyException {
        Matrix newMatrix;
        List<CustomRow> list = new ArrayList<>();
        if (matrix1.columnCount != matrix2.rowCount) {
            if (matrix1.rowCount == matrix2.columnCount) {
                return multiply(matrix2, matrix1);
            } else {
                throw new NotMultiplyException("Матрицы не могут перемножены так как количество столбцов первой не совпадает с количеством строк 2 и наоборот");
            }
        }
        List<CustomRow> list1 = matrix1.getMatrixList();
        List<CustomRow> list2 = matrix2.getMatrixList();
        int row = 0;
        List<Node> nodes = new ArrayList<>();

        for (CustomRow item:
             list2) {
            nodes.add(item.getFirst());
        }

        while(list1.size()>row){
            Node first = list1.get(row).getFirst();
            Node current = first;
            CustomRow customRow = new CustomRow();
            for (int i = 0;i<list2.size();i++){

            }
            row++;
        }



        newMatrix = new Matrix(das, matrix2.columnCount, matrix1.rowCount);
        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix, double value) {
        List<CustomRow> matixBegin = new ArrayList<>();
        for (CustomRow item : matrix.getMatrixList()) {
            Node first = item.getFirst();
            CustomRow customRow = new CustomRow(new Node(first.getValue() * value, first.getRow(), first.getColumn()));

            while (first.hasNext()) {
                first = first.next();

                customRow.addNode(first);
            }
        }

        return new Matrix(matixBegin, matrix.rowCount, matrix.columnCount);
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) throws NotEqualException {
        if (matrix1.columnCount != matrix2.columnCount || matrix1.rowCount != matrix2.rowCount) {
            throw new NotEqualException("Матрицы не совпадайют по количеству строк и количеству столбцов");
        }
        List<CustomRow> list = new ArrayList<>();
        List<CustomRow> list1 = matrix1.getMatrixList();
        List<CustomRow> list2 = matrix2.getMatrixList();

        for (int i = 0; i < list1.size(); i++) {
            Node start1 = list1.get(i).getFirst();
            Node start2 = list2.get(i).getFirst();
            CustomRow customRow = new CustomRow();
            do {
                if (start1.getColumn() == start2.getColumn()) {
                    Node node = new Node(start1.getValue() + start2.getValue(), i, start1.getColumn());
                    customRow.addNode(node);
                    start1 = start1.next();
                    start2 = start2.next();
                } else if (start1.getColumn() > start2.getColumn()) {
                    Node node = new Node(start2.getValue(), i, start2.getColumn());
                    customRow.addNode(node);
                    start2 = start2.next();
                } else {
                    Node node = new Node(start1.getValue(), i, start1.getColumn());
                    customRow.addNode(node);
                    start1 = start1.next();
                }
            } while (start1.hasNext() && start2.hasNext());

            while (start1.hasNext()) {
                start1 = start1.next();
                customRow.addNode(start1);
            }

            while (start2.hasNext()) {
                start2 = start2.next();
                customRow.addNode(start2);
            }
        }


        return new Matrix(list, matrix1.getRowCount(), matrix1.getColumnCount());
    }


}
