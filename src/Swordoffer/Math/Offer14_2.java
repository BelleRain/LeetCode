package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-11-02 18:50
 */

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *  
 *
 * 提示：
 * 2 <= n <= 1000
 */
public class Offer14_2 {

    /**
     * 题解链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
     * 题解一：此题关键：大数求余解法
     * 复杂度分析：
     *    时间复杂度O(log(2)N):其中N = a，二分法为对数级别复杂度，每轮仅有求整、求余、次方运算。
     *       求整和求余运算：资料提到不超过机器数的整数可以看作是O(1)；
     *       幂运算：查阅资料，提到浮点取幂为O(1)。
     *    空间复杂度O(1):变量a，b，p，x，rem使用常数大小额外空间。
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        //极大值点为3
        if(n <= 3) return n-1;
        int b = n % 3, p = 1000000007;
        long rem = 1,x = 3;
        //当b==1时，需要提取一个3，故为n/3 - 1,例如，n=10时，3,3,3,1 乘积的幂则为 10/3 - 1
        //快速幂求余
        for (int a = n/3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) rem = (rem * x) % p;
            x = (x*x)%p;
        }
        if (b == 0) return (int)(rem * 3 % p);
        if (b == 1) return (int)(rem * 4 % p);
        return (int)(rem * 6 % p);
    }

    /*
    大数求余解法：
        大数越界： 当 a 增大时，最后返回的 3^a 大小以指数级别增长，可能超出 int32 甚至 int64 的取值范围，导致返回值错误。
    大数求余问题： 在仅使用 int32 类型存储的前提下，正确计算 x^a 对 p 求余（即 x^a⊙p ）的值。
    解决方案： 循环求余 、 快速幂求余 ，其中后者的时间复杂度更低，两种方法均基于以下求余运算规则推出：
            (xy)⊙p=[(x⊙p)(y⊙p)]⊙p
     */
    /**
     * 求 (x^a) % p —— 循环求余法。固定搭配建议背诵 ⊙：求余符号
     * 根据求余运算性质推出（∵ 本题中 x < p，∴ x⊙p=x ）：
     *      x^a⊙p=[(x^{a−1}⊙p)(x⊙p)]⊙p=[(x^{a−1}⊙p)x]⊙p
     * 解析： 利用此公式，可通过循环操作依次求 x^1, x^2, ..., x^{a-1}, x^a对 p 的余数，保证每轮中间值 rem 都在 int32 取值范围中。封装方法代码如下所示。
     * 时间复杂度 O(N) ： 其中 N=a ，即循环的线性复杂度。
     * @param x 底数
     * @param a 幂
     * @param p 要取的模
     * @return
     */
    public long  remainder(int x,int a,int p){  //x为底数，a为幂，p为要取的模
        long rem = 1 ;
        for (int i = 0; i < a; i++) {
            rem = (rem * x) % p ;
        }
        return rem;
    }

    /**
     * 快速幂算法求余：根据求余运算性质可知：
     *          x^a⊙p=(x^2)^(a/2)⊙p=(x^2⊙p)^(a/2)⊙p
     *  当 a 为奇数时，a/2不是整数，因此分为以下两种情况：（"//"代表向下取整的除法）:
     *
     *  x^a⊙p =  (x^2⊙p)^(a//2)⊙p  ,     a为偶数
     *         [(x^{a−1}⊙p)(x⊙p)]⊙p = [x{(x^2⊙p)^(a//2)}]⊙p , a为奇数
     * 解析： 利用以上公式，可通过循环操作每次把指数 a 问题降低至指数 a//2 问题，
     * 只需循环 log_2(N)次，因此可将复杂度降低至对数级别。封装方法代码如下所示。
     * @param x 底数
     * @param a 幂
     * @param p 要取的模
     * @return
     */
    public long  remainderQuickPower(int x,int a,int p){
        long rem = 1;
        while (a > 0){
            if ((a & 1) == 1) rem = (rem * x) % p;
            x = x * x % p;
            a >>= 1;
        }
        return rem;
    }

    public static void main(String[] args) {
        Offer14_2 offer = new Offer14_2();
        System.out.println(offer.remainderQuickPower(2, 3, 4));
    }
}

















