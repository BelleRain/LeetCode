package CodeTop;

/**
 * @author mxy
 * @create 2023-03-22 16:06
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 字节跳动常考：
 * 440. 字典序的第K小数字   链接：https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 *
 * 示例 1:
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 *
 * 示例 2:
 * 输入: n = 1, k = 1
 * 输出: 1
 *
 * 提示:
 * 1 <= k <= n <= 10^9
 */
public class Code440 {

    public static void main(String[] args) {
        Code440 code440 = new Code440();
        System.out.println(code440.findKthNumber(2, 2));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/solution/by-ac_oier-m3zl/
     */

    /**
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int ans = 1;
        while (k > 1){
            int cnt = getCnt(ans, n);
            if (cnt < k){
                k-=cnt;
                ans++;
            }else {
                k--;
                ans = ans * 10;
            }
        }
        return ans;
    }

    /**
     * 求 [1,limit] 内 以 x 为前缀的数的个数
     * @param x
     * @param limit
     * @return
     */
    public int getCnt(int x,int limit){
        String a = String.valueOf(x);
        String b = String.valueOf(limit);
        int n = a.length();
        int m = b.length();
        int k = m - n;
        int ans = 0;
        int u = Integer.parseInt(b.substring(0, n));
        for (int i = 0; i < k; i++) {
            ans = ans + (int)Math.pow(10, i);
        }
        if (u > x) ans = ans + (int)Math.pow(10, k);
        //[x0...0,limit] 为合法区间
        else if (u == x) ans = ans + limit - x * (int)Math.pow(10, k) + 1;
        return ans;
    }

    //官方题解  https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/solution/zi-dian-xu-de-di-kxiao-shu-zi-by-leetcod-bfy0/
    public int findKthNumber1(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0){
            int steps = getSteps(curr, n);
            if (steps <= k){
                k -= steps;
                curr++;
            }else{
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n){
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n){
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

}
