package Day02_JavaChallenges;

public class Matrix {
    public static void main(String[] args) {

        System.out.println("Nomor 1");
        displayMatrix(Matrix1(5));

        System.out.println("Nomor 2");
        displayMatrix(Matrix2(5, 5));

        System.out.println("Nomor 3");
        displayMatrix(Matrix3(8));

        System.out.println("nomor 4");
        displayMatrix(Matrix4(8));


    }

    public static void displayMatrix(int[][] matrix){
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix[row].length ; col++) {
                System.out.printf("%3d", matrix[row][col]);
            }
            System.out.println();
        }

    }

    public static int[][] Matrix1(int n){
        int[][]matrix = new int[n][n];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == col){
                    matrix[row][col]= row+1;
                }
                if (col > row){
                    matrix[row][col]=10;
                }else if (row > col){
                    matrix[row][col]=20;
                }
            }
        }
        return matrix;
    }

    public static int[][] Matrix2(int n, int m){
        int[][] matrix = new int[n][m];
        int counter = n;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(row == col){
                    matrix[row][col] = counter;
                    counter--;
                } else if (row > col) {
                    matrix[row][col] = 10;
                } else {
                    matrix[row][col] = 20;
                }

            }
        }
        return matrix;
    }

    public static int[][] Matrix3(int n){
        int[][]matrix = new int[n][n];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == 0) {
                    matrix[row][col] = col;
                } else if (col == 0) {
                    matrix[row][col] = row;
                } else if (row == matrix.length -1) {
                    matrix[row][col] = row + col;
                } else if (col == matrix.length -1){
                    matrix[row][col] = row + col;
                }
            }

        }
        return matrix;
    }

    public static int[][] Matrix4(int n){
        int[][] matrix = new int[n][n];
        for (int row = 0; row < n - 1; row++) {
            for (int col = 0; col < n - 1; col++) {
                matrix[row][col] = row + col;
                matrix[row][n-1] += matrix[row][col];
                matrix[n-1][row] += matrix[row][col];
            }
        }

//        matrix[n-1][n-1] = matrix[n-2][n-1]*2;
        for (int i = 0; i < n - 1; i++) {
            matrix[n-1][n-1] += matrix[n-1][i];
        }
        return matrix;
    }
}

