package 春招实习笔试.米哈游.笔试0415;

import java.util.*;

/**
 * @author mxy
 * @create 2023-04-15 18:39
 */
// https://exam.nowcoder.com/cts/17272725/summary?id=5E460FF7FC3299715D2B1AAD67C4DCC3
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int[] b = new int[a - 1];
        for (int i = 0; i < b.length; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(solu(b));
    }

    private static int solu(int[] nums) {
        if (nums.length == 0) return 0;
        Set<List<Integer>> res = new HashSet<>();
        for (int i = 1; i < nums[0]; i++) {
            List<Integer> list = new ArrayList<>();
            if (nums[0] - i == 0) break;
            list.add(i);
            list.add(nums[0] - i);
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] - list.get(j) == 0) break;
                list.add(nums[j] - list.get(j));
            }
            if (list.size() == nums.length + 1) {
                res.add(list);
            }

        }
        return res.size();
    }
}






























