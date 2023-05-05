package 春招实习笔试.小红书.笔试0326;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-03-26 13:29
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] str = new String[2];
        for (int i = 0; i < 2; i++) {
            str[i] = scanner.nextLine();
        }
        int N = Integer.parseInt(str[0]);
        String s = str[1];
        char[] res = new char[N];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') res[i] = 'x';
            else if (s.charAt(i) == 'b') res[i] = 'y';
            else if (s.charAt(i) == 'c') res[i] = 'z';
            else {
                res[i] = (char)(s.charAt(i) - 3);
            }
        }
        String s1 = String.valueOf(res);
        System.out.println(s1);

    }


}
