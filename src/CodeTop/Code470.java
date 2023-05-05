package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 9:04
 */

/**
 * 470. 用 Rand7() 实现 Rand10()   链接：https://leetcode.cn/problems/implement-rand10-using-rand7
 * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。
 * 请注意，这不是传递给 rand10() 的参数。
 *
 * 示例 1:
 * 输入: 1
 * 输出: [2]
 *
 * 示例 2:
 * 输入: 2
 * 输出: [2,8]
 *
 * 示例 3:
 * 输入: 3
 * 输出: [3,8,10]
 *  
 *
 * 提示:
 * 1 <= n <= 10^5
 *  
 * 进阶:
 * rand7()调用次数的 期望值 是多少 ?
 * 你能否尽量少调用 rand7() ?
 *
 */
public class Code470 {

    public static void main(String[] args) {

    }

    //无用处，只用来防止报错
    public int rand7(){
        return 0;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
     * 本题解析详见 题解链接，及评论区补充
     * 一定要看题解 ！！！
     *
     * 帮大家总结了一下知识点：
     *      1.如何利用一个小范围随机数，得到一个大范围等概率随机数？
     *            采用随机数的 k 进制，对于 randN，采用 N 进制，即：(randN - 1) * N + randN 得到了一个 N*N 范围的等概率随机数，
     *            如果还不够大，可以继续在 randN 或生成的 randN*N 上使用这个
     *      2.如何利用一个小范围随机数，得到一个确定范围的等概率随机数？
     *           先采用随机数的 k 进制，得到一个不小于确定范围的随机数 randK，然后对超过确定范围数进行拒绝即可。
     *           注意，如果 K 比确定范围大太多，拒绝策略效率可能就会比较低（经常生成要拒绝的随机数），
     *           此时可以把要拒绝的随机数看成一个新的 randM，
     *           然后针对这个 randM 再思考怎么用这三个方法也得到确定范围的随机数
     *      3.补充技能  对于随机数 randN，只要 K 是 N 的约数（或者说 N 是 K 的整数倍），都可以通过 randN 一步得到 randK：
     *                 randK = (randN % K) + 1;
     * @return
     */
    //public int rand10() {
    //    while (true){
    //        int num = (rand7() - 1) * 7 + rand7(); //等概率生成[1,49]范围的随机数
    //        if (num <= 40) return num%10 + 1; //拒绝采样，并返回[1,10]范围的随机数
    //    }
    //}

    /**
     * 优化：
     * @return
     */
    public int rand10() {
        while (true){
            int a = rand7();
            int b = rand7();
            int num = (a - 1) * 7 + b; // rand49
            if (num <= 40) return num%10 + 1;

            a = num - 40; //rand9
            b = rand7();
            num = (a - 1) * 7 + b; //rand63
            if (num <= 60) return num%10 + 1;

            a = num - 60; //rand3
            b = rand7();
            num = (a - 1) * 7 + b; //rand21
            if (num <= 20) return num%10 + 1;
        }
    }
}


































