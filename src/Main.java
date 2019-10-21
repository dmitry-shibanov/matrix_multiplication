import java.io.*;
import java.util.*;

public class Main {

    private static CustomRow changeLineMatrix(String line, int row) {
        String[] elements = line.split(" ");
        CustomRow rowObject = new CustomRow();
        try {
            for (int i = 0; i < elements.length; i++) {
                double value = Double.parseDouble(elements[i]);
                if (value != 0) {
                    rowObject.addNode(new Node(Double.parseDouble(elements[i]), row, i));
                }
            }
            rowObject.setColumnCount(elements.length);
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
            Matrix.multiply(matrix1, matrix2).show();
        } catch (NotMultiplyException exp) {
            System.out.println(exp.getMessage());
        }
    }

    private static void multiply(Matrix matrix, double value) {
        Matrix.multiply(matrix, value).show();
    }

    private static void sum(Matrix matrix1, Matrix matrix2) {
        try {
            Matrix.sum(matrix1, matrix2).show();
        } catch (NotEqualException exp) {
            System.out.println(exp.getMessage());
        }
    }

    private static boolean checkForSpace(List<CustomRow> list) {
        int count = 0;
        int total = list.size() * list.get(0).getLength();
        for (CustomRow item :
                list) {
            count += item.getLength();
        }

        return total / 2 >= count;
    }

    private static boolean checkForValidColumns(List<CustomRow> list) throws NotValidMatrix {
        int count = list.get(0).getColumnCount();
        for (CustomRow item :
                list) {
            if (item.getColumnCount() != count) {
                throw new NotValidMatrix("Матрица не валидна");
            }
        }
        return true;
    }

    private static void subtraction(Matrix matrix1, Matrix matrix2) {
        try {
            Matrix.subtraction(matrix1, matrix2).show();
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

        try {
            checkForValidColumns(list1);
            checkForValidColumns(list2);
        } catch (NotValidMatrix exp) {
            System.out.println(exp.getMessage());
            System.exit(1);
        }

//        if(!(checkForSpace(list1) && checkForSpace(list2))){
//            System.out.println("Матрицы не являются разряженными");
//            System.exit(1);
//        }


        Matrix matrix1 = new Matrix(list1, list1.size(), list1.get(0).getColumnCount());
        Matrix matrix2 = new Matrix(list2, list2.size(), list2.get(0).getColumnCount());

        System.out.println("Суммирование матриц");
        sum(matrix1, matrix2);

        System.out.println("Вычитание матриц");
        subtraction(matrix1, matrix2);

        System.out.println("Умножение матриц");
        multiply(matrix1,matrix2);

        System.out.println("Умножение матриц на константу");
        multiply(matrix1,2);

    }
}
