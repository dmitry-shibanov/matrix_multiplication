import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {

    private List<CustomRow> matrixList;
    private int rowCount;
    private int columnCount;

    private interface Operation {
        double excecute(double x, double y);
    }

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
        double [][] array = new double[matrix1.rowCount][matrix2.columnCount];
        for (double[] item:
             array) {
            Arrays.fill(item,0);
        }
        List<Node> nodesFirst = new ArrayList<>();

        for (CustomRow item:
             list2) {
            nodesFirst.add(item.getFirst());
        }



        

        newMatrix = new Matrix(list, matrix2.columnCount, matrix1.rowCount);
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

    private static List<CustomRow> sum_sub(Matrix matrix1, Matrix matrix2, Operation action) {
        List<CustomRow> list = new ArrayList<>();
        List<CustomRow> list1 = matrix1.getMatrixList();
        List<CustomRow> list2 = matrix2.getMatrixList();

        for (int i = 0; i < list1.size(); i++) {
            Node start1 = list1.get(i).getFirst();
            Node start2 = list2.get(i).getFirst();
            CustomRow customRow = new CustomRow();
            do {
                if (start1.getColumn() == start2.getColumn()) {
                    double result = action.excecute(start1.getValue(), start2.getValue());
                    if(result!=0) {
                        Node node = new Node(result, i, start1.getColumn());
                        customRow.addNode(node);
                    }
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
            } while (start1 != null && start2 != null);

            list.add(customRow);
        }

        return list;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) throws NotEqualException {
        if (matrix1.columnCount != matrix2.columnCount || matrix1.rowCount != matrix2.rowCount) {
            throw new NotEqualException("Матрицы не совпадайют по количеству строк и количеству столбцов");
        }
        List<CustomRow> list = new ArrayList<>();

        list = sum_sub(matrix1, matrix2, Double::sum);

        return new Matrix(list, matrix1.getRowCount(), matrix1.getColumnCount());
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) throws NotEqualException {
        if (matrix1.columnCount != matrix2.columnCount || matrix1.rowCount != matrix2.rowCount) {
            throw new NotEqualException("Матрицы не совпадайют по количеству строк и количеству столбцов");
        }
        List<CustomRow> list = new ArrayList<>();

        list = sum_sub(matrix1, matrix2, (x,y)->x-y);

        return new Matrix(list, matrix1.getRowCount(), matrix1.getColumnCount());
    }

    public void show() {
        for (CustomRow item :
                matrixList) {
            int index = 0;
            Node node = item.getFirst();
            while (index != columnCount) {
                if (node!=null && node.getColumn() == index) {
                    System.out.print(String.format("%f ", node.getValue()));
                    node = node.next();
                } else {
                    System.out.print(String.format("%d ", 0));
                }
                index++;
            }
            System.out.println();
        }
    }
}
