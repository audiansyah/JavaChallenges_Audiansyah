package Day02_JavaChallenges;

import java.util.ArrayList;
import java.util.List;

public class Arrays {

    public static void main(String[] args) {
        //array no 1
        int[] arr = { 9, 3, 8, 4, 6, 5 };
        OrderEvenBeforeOdd(arr);
        for (int j : arr) {
            System.out.print(j + "  ");
        }

//        //array no 2
        String[] array = {"true","false","false"};
        System.out.println(IsPalindrome(array));

//        array no 3
//        int[] arr = {9, 9, 9, 9};
//        int[] res = AddOnePlus(arr);
//        for (int i : res) {
//            System.out.print(i);
//        }


    }

    //array no 1
    public static void OrderEvenBeforeOdd (int[] arr){
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();

        for (int j : arr) {
            if (j % 2 == 0) {
                even.add(j);
            } else {
                odd.add(j);
            }
        }

        asc(even);
        asc(odd);

        int i = 0;

        for (Integer integer : even) {
            arr[i] = integer;
            i++;
        }

        for (Integer integer : odd) {
            arr[i] = integer;
            i++;
        }
    }

    public static void asc(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n -1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if(list.get(j) > list.get(j + 1)){
                    int temp = list.get(j);
                    list.set(j, list.get(j +1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    //array no 2
    public static boolean IsPalindrome(String[] array) {
        for (int i = 0; i < array.length/2; i++) {
            if (array[i] != array[array.length - i - 1])
                return false;
        }
        return true;
    }

    //array no3
    public static int[] AddOnePlus (int[] arr) {
        int a = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int sum = arr[i] + a;
            arr[i] = sum % 10;
            a = sum/10;
        }
        if (a > 0) {
            int [] newArr = new int [arr.length + 1];
            newArr[0] = a;
            System.arraycopy(arr, 0, newArr, 1, arr.length);
            return newArr;
        }
        return arr;
    }
}

