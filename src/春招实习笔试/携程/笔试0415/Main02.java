package 春招实习笔试.携程.笔试0415;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-15 19:41
 */
public class Main02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long N = scanner.nextLong();
        int[] nums = new int[Math.toIntExact(N)];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }
        List<List<Integer>> solu = solu(nums);
        for (List<Integer> list : solu) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> solu(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> temp = new ArrayList<>();
            int max = 0,tmp = 0;
            for (int j = 1; j <= nums[i] / 2; j++) {
                int k = nums[i] - j;
                if (k % j == 0){
                    if (k > max){
                        tmp = j;
                        max = k;
                    }
                }else {
                    if (k * j >= max){
                        tmp = j;
                        max = k * j;
                    }
                }
            }
            temp.add(tmp);
            temp.add(nums[i] - tmp);
            res.add(temp);
        }
        return res;
    }
}
































