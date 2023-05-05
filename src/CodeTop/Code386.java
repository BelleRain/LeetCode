package CodeTop;

/**
 * @author mxy
 * @create 2023-03-22 14:59
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数   链接：https://leetcode.cn/problems/lexicographical-numbers
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 *  
 * 提示：
 * 1 <= n <= 5 * 10^4
 *
 */
public class Code386 {

    public static void main(String[] args) {
        Code386 code386 = new Code386();
        System.out.println(code386.lexicalOrder1(113).toString());
        System.out.println(code386.lexicalOrder2(13).toString());
    }

    /**
     * 题解链接:https://leetcode.cn/problems/lexicographical-numbers/solution/by-ac_oier-ktn7/
     */

    /**
     * 递归：
     * @param n
     * @return
     */
    List<Integer> res = new ArrayList<>();
    public  List<Integer> lexicalOrder1(int n) {
        for (int i = 1; i < 10; i++) {
            dfs(i,n);
        }
        return res;
    }

    private void dfs(int cur, int n) {
        if (cur > n) return;
        res.add(cur);
        for (int i = 0; i < 10; i++) {
            dfs(10 * cur + i, n);
        }
    }

    /**
     * 迭代：
     * @param n
     * @return
     */
    public  List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();
        // i 为 循环次数
        for (int i = 0, j = 1; i < n; i++) {
            if (j <= n){
                res.add(j);
            }
            if (j * 10 <= n){ //判断 j * 10 <= n,决定是否进入下一层；否则，直接令 j++；
                j = j * 10;
            }else {
                //j%10 == 9:说明该节点处的10个子节点已遍历完成
                //j+1 > n: 说明该分支已越界
                while (j%10 == 9 || j+1 > n) j = j / 10;
                j++; //进入下一分支
            }
        }
        return res;
    }

}
