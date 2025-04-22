import java.util.Scanner;

public class Iterations {
    public static void main(String[] args) {
        System.out.println("Nomer 1");
        num1(5);
        System.out.println("Nomer 2");
        num2(5);
        System.out.println("Nomer 3");
        num3(5);
        System.out.println("Nomer 4");
        num4(5);
        System.out.println("Nomer 5");
        num5(5);
        System.out.println("Nomer 6");
        num6(5);
        System.out.println("Nomer 7");
        num7();
        System.out.println("Nomer 9");
        num9(9);
        System.out.println("Nomer 10");
        num10(9);
    }

    public static void num1(int n) {
        int a = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d ", a++);
            }
            System.out.println();
        }
    }

    public static void num2(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d ", i + j+ 1);
            }
            System.out.println();
        }
    }

    public static void num3(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.printf("%4d ", j + 1);
            }
            System.out.println();
        }
    }

    public static void num4(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j >= i)
                    System.out.print( j  );
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void num5(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    System.out.printf("%4d ", i + 1);
                else if (j > i)
                    System.out.printf("%4d ", 10);
                else
                    System.out.printf("%4d ", 20);
            }
            System.out.println();
        }
    }

    public static void num6(int n) {
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                if (i == j)
                    System.out.printf("%4d ", j);
                else if (i < j)
                    System.out.printf("%4d ", 10);
                else
                    System.out.printf("%4d ", 20);
            }
            System.out.println();
        }
    }

    public static void num7() {
        Scanner x = new Scanner(System.in);
        System.out.print("Input Jumlah baris piramid: ");
        int n = x.nextInt();

        for (int i = n; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.printf("%2d ", j);
            }
            for (int j = 2; j <= i; j++) {
                System.out.printf("%2d ", j);
            }
            System.out.println();
        }
    }

    public static void num9(int n){
        for(int i = 0; i < n; i ++){
            if (i % 2 == 0){
                for (int j = n; j >= 1; j--){
                    System.out.printf("%4d ", j);
                }
            }else {
                for (int j = 1; j <= n; j++){
                    System.out.printf("%4d ", j);
                }
            }
            System.out.println();
        }
    }

    public static void num10(int n){
        for(int i = 0; i < n; i ++){
            if (i % 2 == 0){
                for (int j = 1; j <= n; j++){
                    if (j % 2 == 0){
                        System.out.print( j );
                    }else{
                        System.out.print(" - ");
                    }
                }
            }else {
                for (int j = 1; j <= n; j++){
                    if (j % 2 == 0){
                        System.out.print(" - ");
                    }else{
                        System.out.print( j );
                    }
                }
            }
            System.out.println();
        }
    }
}
