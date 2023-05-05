package 春招实习笔试.腾讯笔试.笔试0326;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-03-26 21:16
 */
public class Main1 {

    public static void main(String[] args) {
        Main1 main1 = new Main1();
        //Scanner scanner = new Scanner(System.in);
        //int N = scanner.nextInt();
        //int[] nums = new int[N];
        //for (int i = 0; i < N; i++) {
        //    nums[i] = scanner.nextInt();
        //}
        int[] nums = {1,2,1};
        System.out.println(main1.result(nums));
    }

    public int result(int[] nums){
        if (nums.length == 0 || nums.length == 1) return nums.length;
        int count = nums.length;
        List<List> res = new ArrayList<>();
        int left = 0, right = 1;
        while (left < right && right < nums.length){
            List<Integer> list = new ArrayList<>();
            list.add(nums[left]);
            int mul = nums[left];
            int x = nums[left];
            while (left < right && right < nums.length){
                list.add(nums[right]);
                mul = mul * nums[right];
                x = x ^ nums[right];
                if (mul == x){
                    res.add(list);
                }
                right++;
            }
            left++;
            right = left + 1;
        }
        count = res.size() + count;
        return count;
    }
}
