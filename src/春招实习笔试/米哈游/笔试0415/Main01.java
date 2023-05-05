package 春招实习笔试.米哈游.笔试0415;

import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-15 21:33
 */
public class Main01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = 0, i = 0;
        while (x < n){
            x = (int) Math.pow(3, i);
            i++;
        }
        if (x == n) System.out.println(n);
        if (Math.abs(Math.pow(3, i) - n) > Math.abs(Math.pow(3, i - 1) - n)){
            i = i - 1;
        }
        x = (int)Math.pow(3, i);
        System.out.print(x);
        while (x != n){
            int j = 0;
            i = i - 1;
            if (x > n){
                x = x - (int) Math.pow(3, i);
                System.out.print(-(int) Math.pow(3, i));
            }else if (x < n){
                x = x + (int)Math.pow(3, j);
            }
        }
    }
}
































