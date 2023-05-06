package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-11-02 8:13
 */

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 */
public class Offer49 {

    public static void main(String[] args) {
        Offer49 offer = new Offer49();
        System.out.println(offer.nthUglyNumber(10));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/chou-shu-lcof/solution/mian-shi-ti-49-chou-shu-dong-tai-gui-hua-qing-xi-t/
     * 题解一：
     *      丑数的递推性质： 丑数只包含因子 2, 3, 5，因此有 “丑数 == 某较小丑数 × 某因子” （例如：10 = 5×2）。
     *      递推公式：x(n+1) = min(xa*2,xb*3,xc*5) a∈[1,n],b∈[1,n],c∈[1,n]
     *      由于 x(n+1) 是最接近 xn 的丑数，则索引 a，b，c满足：
     *        xa*2 > xn > x(a-1)*2,即xa为首个乘以2后大于xn的丑数
     *        xb*3 > xn > x(b-1)*3,即xb为首个乘以3后大于xn的丑数
     *        xc*5 > xn > x(c-1)*5,即xc为首个乘以5后大于xn的丑数
     * 复杂度分析：
     *      时间复杂度 O(N) ： 其中 N = n ，动态规划需遍历计算 dp 列表。
     *      空间复杂度 O(N) ： 长度为 N 的 dp 列表使用 O(N) 的额外空间。
     * @param n
     * @return
     */
    /*
    此题关键：丑数的递推性质：丑数 == 某较小丑数 × 某因子
    对代码的理解：
    设置3个索引a, b, c，分别记录前几个数已经被乘2， 乘3， 乘5了，
    比如a表示前(a-1)个数都已经乘过一次2了，下次应该乘2的是第a个数；
    b表示前(b-1)个数都已经乘过一次3了，下次应该乘3的是第b个数；
    c表示前(c-1)个数都已经乘过一次5了，下次应该乘5的是第c个数；
    对于某个状态下的丑数序列，我们知道此时第a个数还没有乘2(有没有乘3或者乘5不知道），
    第b个数还没有乘3(有没有乘2或者乘5不知道），
    第c个数还没有乘5(有没有乘2或者乘3不知道),
    下一个丑数一定是从第a丑数乘2， 第b个数乘3， 第c个数乘5中获得，他们三者最小的那个就是下个丑数。
    求得下个丑数后就得判断这个丑数是谁，是某个数通过乘2得到的，还是某个数乘3得到的，又或是说某个数通过乘5得到的。
    我们可以比较一下这个新的丑数等于究竟是等于第a个丑数乘2, 还是第b个数乘3， 还是第c个数乘5，
    通过比较我们肯定可以知道这个新的丑数到底是哪个数通过乘哪个数得到的。
    假设这个新的丑数是通过第a个数乘2得到的，说明此时第a个数已经通过乘2得到了一个新的丑数，那下个通过乘2得到一个新的丑数的数应该是第(a+1)个数，此时我们可以说前 a 个数都已经乘过一次2了，下次应该乘2的是第 （a+1） 个数, 所以a++；
    如果新的丑数是通过第b个数乘3得到的, 说明此时第 b个数已经通过乘3得到了一个新的丑数，那下个需要通过乘3得到一个新的丑数的数应该是第(b+1)个数，此时我们可以说前 b 个数都已经乘过一次3了，下次应该乘3的是第 （b+1） 个数, 所以 b++；
    同理，如果这个这个新的丑数是通过第c个数乘5得到的, 那么c++;
    但是注意，如果第a个数乘2后等于第b个数乘3，或者等于第c个数乘5， 说明这个新的丑数是有两种或者三种方式可以得到，这时应该给得到这个新丑数的组合对应的索引都加一，
    比如新丑数是第a个数乘2后和第b个数乘3得到的，那么 a 和 b都应该加一， 因为此时第a个数已经通过乘2得到了一个新的丑数，第b个数已经通过乘3得到了一个新的丑数, 只不过这两个数相等而已。
    所以我们给计数器加一的时候不能使用 if else else if， 而应该使用if, if, if, 这样才不会把应该加一的计数器漏掉  经过n次循环，就能得到第n 个丑数了。
     */
    public int nthUglyNumber(int n) {
        int a = 0,b = 0,c = 0;
        //动态规划列表dp，dp[i]代表第i+1个丑数
        int[] dp = new int[n];
        //初始状态dp[0],即第一个丑数为1
        dp[0] = 1;
        //状态转移
        for (int i = 1; i < n; i++) {
             /*
            此题的关键在于丑数的性质，一个丑数总是可以写成另一个较小的丑数和质因数2、3或5中的一个的乘积,具体来说就是xn = min(xa*2,xb*3,xc*5)。
            至于说为什么取三者的最小值，可以换一个思路，一系列的丑数其实就是由这三个乘积依次构造的，因为要保证递增关系且不能遗漏，所以每次都取三个构造丑数乘积中最小的那一个，并且当xn等于某个值时，其对应的索引就会相应增加1，
            比如xn=xb*3，那么b便会增加1，以保证对后续丑数的持续生成，且a,b,c的增加并不互斥，比如当xn = xb*3且xn=xa*2时，a和b可以同时增加1。
            */
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n-1];
    }


    /**
     * 题解链接：https://leetcode.cn/problems/chou-shu-lcof/solution/chou-shu-by-leetcode-solution-0e5i/
     * 方法二：最小堆
     * 复杂度分析
     *      时间复杂度：O(nlogn)。得到第 n 个丑数需要进行 n 次循环，每次循环都要从最小堆中取出 1 个元素以及向最小堆中加入最多 3 个元素，
     *      因此每次循环的时间复杂度是 $O(\log (3n) + 3 \log (3n))，总时间复杂度是 O(nlogn)。
     *      空间复杂度：O(n)。空间复杂度主要取决于最小堆和哈希集合的大小，最小堆和哈希集合的大小都不会超过 3n。
     * @param n
     * @return
     */
    /*public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        //初始时堆为空，首先将最小的丑数1加入堆
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            //每次取出堆顶元素x，则x是堆中最小的丑数，2x，3x，5x也是丑数
            //因此每次将2x，3x，5x加入堆中。即每次向堆中加入3个元素
            //set结合用于重复，有时相乘会出现相同的数，避免重复元素加入堆
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }*/
}