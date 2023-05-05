package Swordoffer.BitCal;

/**
 * @author mxy
 * @create 2022-10-18 11:27
 */

/**
 * 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 */
public class Offer56_2 {
    public static void main(String[] args) {
        //System.out.println(0 << 1);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
     *                      3 = 0 0 1 1
     *                      3 = 0 0 1 1
     *                      3 = 0 0 1 1
     *                      5 = 0 1 0 1
     *各二进制位中 1 的个数       0 1 3 4
     *           对 3 求余       0 1 0 1 = 5
     *   -对于出现三次的数字，各位出现的次数都是 3 的倍数
     *   -统计所有数字的各二进制中的 1 的个数，并对 3 求余
     *    结果为只出现一次的数字
     */

    /**
     * 方法一：有限状态自动机 由于涉及状态转换相关知识，详细讲解见原文链接
     * @param nums
     * @return
     */
    //public int singleNumber(int[] nums) {
    //    int ones = 0, twos = 0;
    //    for(int num : nums){
    //        ones = ones ^ num & ~twos;
    //        twos = twos ^ num & ~ones;
    //    }
    //    return ones;
    //}


    /**
     * 方法二：遍历统计
     * @param nums
     * @return
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 位数组 nums 的长度；遍历数组占用 O(N) ，每轮中的常数个位运算操作占用 O(1) 。
     * 空间复杂度 O(1) ： 数组 counts 长度恒为 32 ，占用常数大小的额外空间。
     * 代码：
     * 实际上，只需要修改求余数值 m ，即可实现解决 除了一个数字以外，其余数字都出现 m 次 的通用问题。
     *
     */
    public int singleNumber(int[] nums) {
        //counts 记录所有数字的各二进制位的1的出现次数
        int[] counts = new int[32];
        /*
        例：3 = 0 0 1 1；3 = 0 0 1 1；3 = 0 0 1 1；5 = 0 1 0 1
          counts[] = 4 3 1 0
         */
        for (int num : nums) {
            //counts 累加 获取 所有数字 各二进制位 1 出现的次数
            //若该二进制位为1，则累加
            for (int j = 0; j < 32; j++) {
                //获取num的最右一位
                counts[j] += num & 1;
                //配合 无符号右移 操作，获取num所有位的值
                num >>>= 1;
            }
        }
        // counts[] = 4 3 1 0
        int res = 0,m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;  //左移 1 位，将 i-1位前移，以确定第 i 位
            //因为之前获取的的是num的最右一位，倒序遍历 counts
            res |= counts[31-i] % m; //配合左移一位，恢复第 i 位的值到 res
        }
        return res;
    }
}



























