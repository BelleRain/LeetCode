package 春招实习笔试.美的.笔试0410;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-10 19:35
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        String[] str = s.split(",");
        Arrays.sort(str,(x,y) -> (x+y).compareTo(y+x));
        for (String s1 : str) {
            builder.append(s1);
        }
        System.out.println(builder.toString());
    }

}
