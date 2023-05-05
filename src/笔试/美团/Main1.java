package 笔试.美团;

import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-03-17 17:00
 */
public class Main1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int[] nums = new int[2];
            nums[0] = scanner.nextInt();
            nums[1] = scanner.nextInt();
            sum(nums);
            //Math.pow(, )
        }
    }

    public static void sum(int[] nums){
        double sum = nums[0];
        double temp = nums[0];
        for(int i = 1;i < nums[1] ; i++){
            temp = Math.sqrt(temp);
            sum = sum + temp;
        }
        System.out.printf("%.2f",sum);
        System.out.println();
    }
}
