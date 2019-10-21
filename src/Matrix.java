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

    public Matrix(double[][] mas){
        this.rowCount = mas.length;
        this.columnCount = mas[0].length;
        this.matrixList = new ArrayList<>();
        for (int i = 0;i<rowCount;i++){
            CustomRow row = new CustomRow();
            for (int j = 0;j<columnCount;j++){
                if(mas[i][j]!=0){
                    row.addNode(new Node(mas[i][j],i,j));
                }
            }
            this.matrixList.add(row);
        }
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
        if (matrix1.columnCount != matrix2.rowCount) {
            if (matrix1.rowCount == matrix2.columnCount) {
                return multiply(matrix2, matrix1);
            } else {
                throw new NotMultiplyException("Матрицы не могут перемножены так как количество столбцов первой не совпадает с количеством строк 2 и наоборот");
            }
        }
        List<CustomRow> list1 = matrix1.getMatrixList();
        List<CustomRow> list2 = matrix2.getMatrixList();

        double[][] array = new double[matrix1.rowCount][matrix2.columnCount];
        for (double[] item :
                array) {
            Arrays.fill(item, 0);
        }

        for (int row = 0; row < list1.size(); row++) {
            Node current = list1.get(row).getFirst();

            while(current!=null) {
                Node node = list2.get(current.getColumn()).getFirst();
                while (node != null) {
                    array[row][node.getColumn()] += node.getValue() * current.getValue();
                    node = node.next();
                }
                current = current.next();
            }
        }


        newMatrix = new Matrix(array);
        return newMatrix;
    }


    public static Matrix multiply(Matrix matrix, double value) {
        List<CustomRow> matixBegin = new ArrayList<>();
        for (CustomRow item : matrix.getMatrixList()) {
            Node node = item.getFirst();
            CustomRow customRow = new CustomRow();
            customRow.setColumnCount(item.getColumnCount());

            while (node != null) {
                customRow.addNode(new Node(node.getValue()*value,node.getRow(),node.getColumn()));
                node = node.next();
            }
            matixBegin.add(customRow);
        }

        return new Matrix(matixBegin, matrix.getRowCount(), matrix.getColumnCount());
    }

    private static Matrix sum_sub(Matrix matrix1, Matrix matrix2, Operation action) {
        List<CustomRow> rows = new ArrayList<>();

        double[][] array = new double[matrix1.rowCount][matrix2.columnCount];
        for (double[] item :
                array) {
            Arrays.fill(item, 0);
        }

        List<CustomRow> list1 = matrix1.getMatrixList();
        List<CustomRow> list2 = matrix2.getMatrixList();

        for (int row = 0;row<list1.size();row++){
            Node node = list1.get(row).getFirst();
            while (node!=null){
                array[row][node.getColumn()]+=node.getValue();
                node = node.next();
            }
        }

        for (int row = 0;row<list1.size();row++){
            Node node = list2.get(row).getFirst();
            while (node!=null){
                array[row][node.getColumn()] = action.excecute(array[row][node.getColumn()],node.getValue());
                node = node.next();
            }
        }

        Matrix matrix = new Matrix(array);

        return matrix;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) throws NotEqualException {
        if (matrix1.columnCount != matrix2.columnCount || matrix1.rowCount != matrix2.rowCount) {
            throw new NotEqualException("Матрицы не совпадайют по количеству строк и количеству столбцов");
        }
        Matrix matrix = sum_sub(matrix1, matrix2, Double::sum);

        return matrix;
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) throws NotEqualException {
        if (matrix1.columnCount != matrix2.columnCount || matrix1.rowCount != matrix2.rowCount) {
            throw new NotEqualException("Матрицы не совпадайют по количеству строк и количеству столбцов");
        }
        Matrix matrix = sum_sub(matrix1, matrix2, (x, y) -> x - y);

        return matrix;
    }

    public void show() {
        for (CustomRow item :
                matrixList) {
            int index = 0;
            Node node = item.getFirst();
            if(node == null){
                for(int i = 0;i<item.getColumnCount();i++){
                    System.out.print(String.format("0 "));
                    System.out.println();
                }
                continue;
            }
            while (index != columnCount) {
                if (node != null && node.getColumn() == index) {
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
