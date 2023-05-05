package 春招实习笔试.网易雷火.笔试0422;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author mxy
 * @create 2023-04-22 13:19
 */
public class Main01 {

    public static void main(String[] args) {
        Main01 main01 = new Main01();
        int[] price = {2,3,5,4};
        //int[] price = {2,3};
        System.out.println(main01.putGems(price, 2));
    }

    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public int putGems (int[] price, int k) {
        if (price.length == 0 || k == 0) return 0;
        Deque<Deque<Integer>> res = new ArrayDeque<>();
        dfs(price,res,0,k);
        return max - min;
    }

    private void dfs(int[] price, Deque<Deque<Integer>> res, int begin, int k) {
        if (k == 0){
            int sum = 0;
            for (Deque<Integer> re : res) {
                int s = re.peekFirst() + re.peekLast();
                sum = sum + s;
            }
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = begin; i < begin + price.length - k + 1; i++) {
            Deque<Integer> path = new ArrayDeque<>();
            for (int j = begin; j <= i; j++) {
                path.addLast(price[j]);
            }
            res.addLast(path);
            dfs(price, res,i + 1, k - 1);
            res.removeLast();
        }
    }
}





























