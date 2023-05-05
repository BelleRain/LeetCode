package Swordoffer.BitCal;

/**
 * @author mxy
 * @create 2022-10-18 9:49
 */

/**
 * 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 */
public class Offer56_1 {

    //异或满足交换律、结合律
    /**
     * 题解一： 原文链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
     * 1、如果除了一个数字以外，其他数字都出现了两次，那么如何找到出现一次的数字？
     *    全员进行异或操作,那么在计算过程中，成对出现的数字的所有位会两两抵消为 0，最终得到的结果就是那个出现了一次的数字。
     *
     * @param nums
     * @return
     */
    //public int[] singleNumbers(int[] nums) {
    //    int ret = 0;
    //    for (int n : nums) {
    //        ret ^= n;
    //    }
    //    int div = 1;
    //    //寻找第一个不为 0 的 xi
    //    //xi = 0 ：ai 和 bi 相等
    //    //xi = 1 ：ai 和 bi 不等
    //    while ((div & ret) == 0){
    //        div <<= 1;
    //    }
    //    int a = 0,b = 0;
    //    //分组：1.两个只出现一次的数字在不同的组中；2.相同的数字会被分到相同的组中
    //    //这样，对两组分别进行异或操作，即可得到答案的两个数字
    //    for (int n : nums) {
    //        if ((div & n) != 0){
    //            a ^= n;
    //        }else {
    //            b ^= n;
    //        }
    //    }
    //    return new int[]{a,b};
    //}

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/jian-zhi-offer-56-i-shu-zu-zhong-shu-zi-tykom/
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int x = 0,y = 0,n = 0,m =1;
        for (int num : nums) {  //遍历异或
            n ^= num;
        }
        //获取 异或结果的 首位1，即获取a，b之间第一个不相同的位
        while ((n&m) ==0){ //循环左移，计算m
            m <<= 1;
        }
        for (int num : nums) {//遍历nums分组
            if ((num&m) != 0) x^=num;  //当 num&m != 0
            else y^=num;               //当 num&m == 0
        }
        return new int[]{x,y}; //返回出现一次的数字
    }

    /**小细节：& 的优先级 高于 ==   a = 0⊕a    0 = a⊕a
     * 分组原理：n为全员异或的结果，通过 while 循环得到 m，m代表在这一位，a,b在这一位的数不同，例：若 a在该位为1，则 b 在该位为 0
     * 所以，可以利用该位进行 分组。对原数组进行循环遍历，
     * 如果 （num&m） == 0，证明 所有的 在该位 为 0 的数字被分到了一组，
     * 若 (num&m) != 0) ，证明 所有的 在该位为 1 的数字被分到了一组。
     * 由上分析，a，b两个只出现一次的数字 肯定 被分到了两组 ，而且 相同的数字 必然 被分到了一组
     * 所以，每组数字的搭配为 只出现一次的数字 + 多个出现多次的数字
     * 两组分别异或，相同的数字 异或 结果 为 0，异或的结果均为 只出现一次的数字 ， a，b即得
     * 例：nums=[3,3,4,4,1,6] ---》 n = 1⊕6 ---》 m = 1
     * （num&m） == 0 ：4,4,6
     * (num&m) != 0)：3,3,1
     */

    /*
    例子： 1 4 6 4 如果全员异或，相同的4肯定没了，留下1 和 6异或的结果，但是光异或我没法返回啊
              哪我就想到了一个很赞的做法
              1 和 6 肯定是不同的吧，异或结果有一位肯定是 1，比如说第2位异或为1
                          => 也就是说 1 和 6 ，第二位一个为1一个为0
              那么我就分组，将第二位为1的分一组，全员异或
                          将第二位为0的分一组，全员异或
              这就是最终结果了
              你可能还有疑问，哪相同的元素怎么办，不会干扰结果吗？
                 当然不会，比如4 和 4，第二位都为0，肯定都被分到一组，最后异或为0，不影响结果
     */
}

























