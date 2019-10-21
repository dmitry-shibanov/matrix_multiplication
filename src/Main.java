import java.io.*;
import java.util.*;

public class Main {

    private static CustomRow changeLineMatrix(String line, int row) {
        String[] elements = line.split(" ");
        CustomRow rowObject = new CustomRow(new Node(Double.parseDouble(elements[0]), row, 0));
        try {
            for (int i = 1; i < elements.length; i++) {
                rowObject.addNode(new Node(Double.parseDouble(elements[i]), row, i));
            }
        } catch (ClassCastException exp) {
            System.out.println("На вход получено не число");
        }

        return rowObject;
    }

    private static List<CustomRow> readFile(String fileName) {
        List<CustomRow> mListMatrix = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int row = 0;
            while ((strLine = br.readLine()) != null) {
                mListMatrix.add(changeLineMatrix(strLine, row));
                row++;
            }
        } catch (FileNotFoundException exp) {
            System.out.println(String.format("%s файл не найден", fileName));
            System.exit(1);
        } catch (IOException exp) {
            System.out.println("Упс что-то не так с input/reader, повторите позже");
            System.exit(1);
        }


        return mListMatrix;
    }

    private static void multiply(Matrix matrix1, Matrix matrix2){
        try {
            Matrix.multiply(matrix1, matrix2);
        } catch (NotEqualException exp) {
            System.out.println(exp.getMessage());
        }
    }

    private static void multiply(Matrix matrix, double value){
            Matrix.multiply(matrix, value);
    }

    private static void sum(Matrix matrix1, Matrix matrix2){
        try {
            Matrix.sum(matrix1, matrix2);
        } catch (NotEqualException exp) {
            System.out.println(exp.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла с 1 матрицой");
        String fileName1 = scanner.nextLine();
        System.out.println("Введите название файла с 2 матрицой");
        String fileName2 = scanner.nextLine();

        List<CustomRow> list1 = readFile(fileName1);
        List<CustomRow> list2 = readFile(fileName2);

        Matrix matrix1 = new Matrix(list1);
        Matrix matrix2 = new Matrix(list2);

    }
}
