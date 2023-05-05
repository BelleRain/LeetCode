package 春招实习笔试.小红书.笔试0326;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-03-26 17:16
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }
        int M = scanner.nextInt();
        int[] L = new int[M];
        for (int i = 0; i < M; i++) {
            L[i] = scanner.nextInt();
        }
        int[] R = new int[M];
        for (int i = 0; i < M; i++) {
            R[i] = scanner.nextInt();
        }


    }

}
