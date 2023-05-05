package 笔试.美团;

import java.util.*;

/**
 * @author mxy
 * @create 2023-03-18 11:18
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] nums = new int[N];
        while (scanner.hasNextInt()){  //8 3   1 2 3 2 1 4 5 1
            for (int i = 0; i < N; i++) {
               nums[i] = scanner.nextInt();
            }
        }
        solute(nums, K);
    }

    public static void solute(int[] nums,int K){
        int maxLength = 0;
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                res.add(nums[j]);
                if (res.size() > K) break;
                count++;
            }
            res.clear();
            maxLength = Math.max(count, maxLength);
        }
        System.out.println(maxLength);
    }

}
