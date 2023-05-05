package 春招实习笔试.美的.笔试0410;

import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-10 18:42
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = scanner.nextInt();
        }

        int n1 = nums[6];
        int n2 = nums[7];
        int n3 = nums[8];

        for (int i = 5; i >= 0; i--) {
            nums[i + 3] = nums[i];
        }
        nums[0] = n1;
        nums[1] = n2;
        nums[2] = n3;

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
