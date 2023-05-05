package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-10-24 9:13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 * 限制：
 * 1 <= target <= 10^5
 *
 */
public class Offer57_2 {

    /**
     * 题解链接：https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/jian-zhi-offer-57-ii-he-wei-s-de-lian-xu-t85z/
     */

    /**
     * 方法一：求和公式 target = ((i+j)* (j-i+1))/2 i:左边界，j：右边界，注意原题：连续正整数序列
     * 当确定 i 和 target时，将上述等式转为 一元二次方程 求解 j 的值
     * 当 j 满足以下两个条件时记录结果：
     *  1、j 为 整数 ：符合题目所求「连续正整数序列」；
     *  2、i < j ：满足题目要求「至少含有两个数」；
     * 复杂度分析：
     *    时间复杂度 O(N) ： 其中 N = target；连续整数序列至少有两个数字，而 i < j 恒成立，因此至多循环 tartget/2 次，使用 O(N) 时间；循环内，计算 j 使用 O(1) 时间；
     *          当 i = 1 时，达到最大序列长度 （-1+(1+8s)^(1/2)）/2,考虑到解的稀疏性，将列表构建时间简化考虑为O(1);
     *    空间复杂度 O(1) ： 变量 i , j 使用常数大小的额外空间。
     * @param target
     * @return
     * 代码：
     * 计算公式中 i^2项可能超过 int 类型取值范围，因此在 Java, C++ 中需要转化成 long 类型。
     */
    //public int[][] findContinuousSequence(int target) {
    //    int i = 1;
    //    double j = 2.0;
    //    //res，结果数组集合
    //    List<int[]> res = new ArrayList<>();
    //    //i < j ：满足题目要求「至少含有两个数」
    //    while (i<j){
    //        j = (-1 + Swordoffer.Math.sqrt(1+4*(2*target + (long)i*i - i)))/2;
    //        if (i<j && j == (int)j){
    //            //构建结果数组ans，数组长度为 j-i+1
    //            int[] ans = new int[(int)j - i + 1];
    //            // k = i,i+1,...,j
    //            //k-i = 0,1,...,j-i
    //            for (int k = i; k <= (int)j; k++) {
    //                ans[k-i] = k;
    //            }
    //            res.add(ans);
    //        }
    //        i++;
    //    }
    //    return res.toArray(new int[0][]);
    //}

    /**
     * 方法二：滑动窗口（双指针）
     * 复杂度分析：
     *   时间复杂度 O(N) ： 其中 N = target ；连续整数序列至少有两个数字，而 i < j 恒成立，因此至多循环 target 次（ i , j 都移动到 (target/2),使用O(N)时间；
     *   当 i = 1 时，达到最大序列长度 （-1+(1+8s)^(1/2)）/2,，考虑到解的稀疏性，将列表构建时间简化考虑为 O(1)；
     *   空间复杂度 O(1) ： 变量 i , j , s 使用常数大小的额外空间。
     * @param target
     * @return
     * 代码：
     * 观察本文的算法流程发现，当 s = target 和 s > target 的移动边界操作相同，因此可以合并，代码如下所示。
     */
    public int[][] findContinuousSequence(int target) {
        //初始化，左边界i = 1，右边界j = 2，元素和s = 3
        int i = 1,j = 2,s = 3;
        List<int[]> res = new ArrayList<>();
        while (i < j){
            if (s == target) {
                //记录连续整数序列
                int[] ans = new int[j-i+1];
                for (int k = i; k <= j; k++) {
                    ans[k-i] = k;
                }
                res.add(ans);
            }
            if (s >= target){
                //左边界向右移动 i++，并更新元素和s；
                s = s - i;
                i++;
            }else {
                //向右移动右边界 j++，并更新元素和s；
                j++;
                s = s + j;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/mian-shi-ti-57-ii-he-wei-sde-lian-xu-zheng-shu-x-2/
     * 方法三：枚举 + 暴力
     * 由于题目要求序列长度至少大于 2，所以枚举的上界为 (target/2)向下取整
     * @param target
     * @return
     */
    //public int[][] findContinuousSequence(int target) {
    //    List<int[]> vec = new ArrayList<>();
    //    int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
    //    for (int i = 1; i <= limit; ++i) {
    //        for (int j = i;; ++j) {
    //            sum += j;
    //            if (sum > target) {
    //                sum = 0;
    //                break;
    //            } else if (sum == target) {
    //                int[] res = new int[j - i + 1];
    //                for (int k = i; k <= j; ++k) {
    //                    res[k - i] = k;
    //                }
    //                vec.add(res);
    //                sum = 0;
    //                break;
    //            }
    //        }
    //    }
    //    return vec.toArray(new int[vec.size()][]);
    //}
}



































