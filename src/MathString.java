public class MathString {
    public static void main(String[] args) {
        System.out.println(Reverse(4563));
        System.out.println(IsPalindrome(121));
        System.out.println(IsPalindrome(110));
        System.out.println(Capitalize("this is a very special title"));
        System.out.println(IsNoDuplicateChar("codeid"));
        System.out.println(IsBrace("(()())"));
    }
    public static int Reverse(int n) {
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed;
    }

    public static boolean IsPalindrome(int n) {
        return n == Reverse(n);
    }

    public static String Capitalize(String input) {
        String[] a = input.split(" ");
        StringBuilder x = new StringBuilder();

        for (String y : a) {
            if (!y.isEmpty()) {
                x.append(Character.toUpperCase(y.charAt(0)))
                        .append(y.substring(1)).append(" ");
            }
        }

        return x.toString().trim();
    }

    public static boolean IsNoDuplicateChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }


//    public static boolean IsBrace(String s) {
//        int a = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '(') a++;
//            else if (c == ')') a--;
//
//            if (a < 0) return false;
//        }
//
//        return a == 0;
//    }

    public static boolean IsBrace(String s) {
        while (s.contains("()")) {
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }


}
