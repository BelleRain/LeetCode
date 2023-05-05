package CodeTop;

/**
 * @author mxy
 * @create 2023-04-22 9:40
 */

/**
 * 69. x 的平方根   链接：https://leetcode.cn/problems/sqrtx
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *  
 * 提示：
 * 0 <= x <= 2^31 - 1
 *
 */
public class Code69 {

    public static void main(String[] args) {
        System.out.println(mySqrt1(4));
    }

    /**
     * 题解一：二分查找 https://leetcode.cn/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/
     * @param x
     * @return
     */
    public static int mySqrt1(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        int l = 1, r = x/2;
        while (l < r){
            int mid = l + (r - l + 1)/2; //mid + 1 ，否则出现死循环
            if (mid == x / mid){ //防止乘法溢出改用除法
                return mid;
            }else if (mid < x/mid){
                l = mid;
            }else {
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 二分法：https://leetcode.cn/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        //0,1时 mid = 0,则 x/mid会报错
        int l = 0, r = x, ans = -1;
        while (l <= r){
            int mid = l + (r - l)/2;
            if (mid <= x/mid){
                ans = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return ans;
    }


    /**
     * 牛顿迭代法：https://leetcode.cn/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
     * @param x
     * @return
     */
    public static int mySqrt3(int x) {
        if (x == 0) return 0;
        double C = x,x0 = x;
        while (true){
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7){
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

}











































