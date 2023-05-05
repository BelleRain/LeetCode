package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-12 9:13
 */

/**
 * 1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 *
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 */
public class Offer64 {

    public static void main(String[] args) {
        Offer64  offer = new Offer64();
        System.out.println(offer.sumNums(100));
    }

    //public int sumNums(int n) {
    //    if (n == 1) return 1;
    //    return sumNums(n-1) + n;
    //}

    /**
     * 题解：链接：https://leetcode.cn/problems/qiu-12n-lcof/solution/mian-shi-ti-64-qiu-1-2-nluo-ji-fu-duan-lu-qing-xi-/
     */

    /**
     * 方法一： 逻辑符短路
     * 一、解题思路：
     *  本题在简单问题上做了许多限制，需要使用排除法一步步导向答案。
     *   1+2+...+(n-1)+n1+2+...+(n−1)+n 的计算方法主要有三种：平均计算、迭代、递归。
     *  方法一： 平均计算
     *  问题： 此计算必须使用 乘除法 ，因此本方法不可取，直接排除。
     *     public int sumNums(int n) {
     *        return (1 + n) * n / 2;
     * }
     *  方法二： 迭代
     *  问题： 循环必须使用 while 或 for，因此本方法不可取，直接排除。
     * public int sumNums(int n) {
     *     int res = 0;
     *     for(int i = 1; i <= n; i++)
     *         res += i;
     *     return res;
     * }
     *  方法三： 递归
     *  问题： 终止条件需要使用 if ，因此本方法不可取。
     *  思考： 除了 if 和 switch 等判断语句外，是否有其他方法可用来终止递归？
     *  public int sumNums(int n) {
     *     if(n == 1) return 1;
     *     n += sumNums(n - 1);
     *     return n;
     * }
     *
     * 二、逻辑运算符的短路效应：
     *   常见的逻辑运算符有三种，即 “与 && ”，“或 || ”，“非 ! ” ；而其有重要的短路效应，如下所示：
     *   if(A && B)  // 若 A 为 false ，则 B 的判断不会执行（即短路），直接判定 A && B 为 false
     *   if(A || B) // 若 A 为 true ，则 B 的判断不会执行（即短路），直接判定 A || B 为 true
     *   本题需要实现 “当 n = 1 时终止递归” 的需求，可通过短路效应实现。
     *    n > 1 && sumNums(n - 1) // 当 n = 1 时 n > 1 不成立 ，此时 “短路” ，终止后续递归
     * 三、复杂度分析：
     * 时间复杂度 O(n) ： 计算 n + (n-1) + ... + 2 + 1 需要开启 n 个递归函数。
     * 空间复杂度 O(n) ： 递归深度达到 n ，系统使用 O(n) 大小的额外空间。
     * 四、代码：
     * Java 中，为构成语句，需加一个辅助布尔量 x ，否则会报错；
     * Java 中，开启递归函数需改写为 sumNums(n - 1) > 0 ，此整体作为一个布尔量输出，否则会报错；
     * 初始化变量 res 记录结果。（ Java 可使用第二栏的简洁写法，不用借助变量 res ）。
     * @param n
     * @return
     */
    //int res = 0;
    //public int sumNums(int n) {
    //    boolean x = n > 1 && sumNums(n-1) > 0;
    //    res = res + n;
    //    return res;
    //}
    //简化写法
    public int sumNums(int n) {
        boolean x = n > 1 && (n+=sumNums(n-1)) > 0;
        return n;
    }
}




































