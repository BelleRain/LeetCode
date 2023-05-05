package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-09-30 8:00
 */

/**
 * 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？.
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 *
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class Offer13 {

    public static void main(String[] args) {
        Offer13 offer = new Offer13();
        int count = offer.movingCount(2,3,1);
        System.out.println("最后的结果为：" + count);
    }

    /**
     * 解法一：
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(m,n,visited,k,0,0);
    }
    private int dfs(int m,int n,boolean[][] visited,int k,int i,int j){
        if(i>=m || j>=n || visited[i][j] || bitSum(i)+bitSum(j)>k) return 0;
        visited[i][j] = true;
        //int p = dfs(m,n,visited,k,i+1,j);
        //int q = dfs(m,n,visited,k,i,j+1);
        //System.out.println("p= " + p + "q= " + q);
        //return 1+p+q;
        return 1+dfs(m,n,visited,k,i+1,j)+dfs(m,n,visited,k,i,j+1);
    }
    private int bitSum(int x){
        int sum = 0;
        while(x!=0){
            sum+=x%10;
            x=x/10;
        }
        return sum;
    }

    /**
     * 解法二：
     * @param m
     * @param n
     * @param k
     * @return
     */
    //public int movingCount(int m, int n, int k) {
    //    int[][] a = new int[m][n];
    //    int[] res = new int[1];
    //    process(a,0,0,k,res);
    //    return res[0];
    //}
    //public static void process(int[][] a, int i, int j, int k, int[] res){
    //    if(i<0 || j<0 || i>a.length-1 || j>a[0].length-1 || a[i][j]==1 || count(i,j)>k)
    //        return;
    //    res[0]++;
    //    a[i][j] = 1;
    //    process(a,i+1,j,k,res);
    //    process(a,i-1,j,k,res);
    //    process(a,i,j+1,k,res);
    //    process(a,i,j-1,k,res);
    //}
    //public static int count(int m, int n){
    //    int t = 0;
    //    while(m != 0){
    //        t += m%10;
    //        m /= 10;
    //    }
    //    while(n != 0){
    //        t += n%10;
    //        n /= 10;
    //    }
    //    return t;
    //}

    /**
     * 方法三：广度优先搜索 原文链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/
     * 思路和算法:
     *  1、我们将行坐标和列坐标数位之和大于 k 的格子看作障碍物，那么这道题就是一道很传统的搜索题目，我们可以使用广度优先搜索或者深度优先搜索来解决它，本文选择使用广度优先搜索的方法来讲解。
     *  2、那么如何计算一个数的数位之和呢？我们只需要对数 x 每次对 10 取余，就能知道数 x 的个位数是多少，然后再将 x 除 10，这个操作等价于将 x 的十进制数向右移一位，删除个位数（类似于二进制中的 >> 右移运算符），不断重复直到 x 为 0 时结束。
     *  3、同时这道题还有一个隐藏的优化：我们在搜索的过程中搜索方向可以缩减为向右和向下，而不必再向上和向左进行搜索。
     *   如下图，我们展示了 16 * 16 的地图随着限制条件 k 的放大，可行方格的变化趋势，每个格子里的值为行坐标和列坐标的数位之和，蓝色方格代表非障碍方格，即其值小于等于当前的限制条件 k。
     *   我们可以发现随着限制条件 k 的增大，(0, 0) 所在的蓝色方格区域内新加入的非障碍方格都可以由上方或左方的格子移动一步得到。而其他不连通的蓝色方格区域会随着 k 的增大而连通，且连通的时候也是由上方或左方的格子移动一步得到，
     *   因此我们可以将我们的搜索方向缩减为向右或向下。
     *
     * 复杂度分析：
     *  时间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。考虑所有格子都能进入，那么搜索的时候一个格子最多会被访问的次数为常数，所以时间复杂度为 O(2mn)=O(mn)。
     *  空间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。搜索的时候需要一个大小为 O(mn) 的标记结构用来标记每个格子是否被走过。
     */
    //public int movingCount(int m, int n, int k) {
    //    if (k == 0) {
    //        return 1;
    //    }
    //    Queue<int[]> queue = new LinkedList<int[]>();
    //    // 向右和向下的方向数组
    //    int[] dx = {0, 1};
    //    int[] dy = {1, 0};
    //    boolean[][] vis = new boolean[m][n];
    //    queue.offer(new int[]{0, 0});
    //    vis[0][0] = true;
    //    int ans = 1;
    //    while (!queue.isEmpty()) {
    //        int[] cell = queue.poll();
    //        int x = cell[0], y = cell[1]; //（x,y）代表当前坐标
    //        //i = 0，dx[0] = 0,dy[0] = 1,向右移动
    //        //i = 1, dx[1] = 1,dy[1] = 0,向下移动
    //        for (int i = 0; i < 2; ++i) {
    //            int tx = dx[i] + x;
    //            int ty = dy[i] + y;
    //            if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || get(tx) + get(ty) > k) {
    //                continue;
    //            }
    //            queue.offer(new int[]{tx, ty});
    //            vis[tx][ty] = true;
    //            ans++;
    //        }
    //    }
    //    return ans;
    //}
    //
    //private int get(int x) {
    //    int res = 0;
    //    while (x != 0) {
    //        res += x % 10;
    //        x /= 10;
    //    }
    //    return res;
    //}

    /**
     * 方法四：递推  原文链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/
     * 思路
     * 考虑到方法一提到搜索的方向只需要朝下或朝右，我们可以得出一种递推的求解方法。
     * 算法
     *    1、定义 vis[i][j] 为 (i, j) 坐标是否可达，如果可达返回 1，否则返回 0。
     *    2、首先 (i, j) 本身需要可以进入，因此需要先判断 i 和 j 的数位之和是否大于 k ，如果大于的话直接设置 vis[i][j] 为不可达即可。
     *          否则，前面提到搜索方向只需朝下或朝右，因此 (i, j) 的格子只会从 (i - 1, j) 或者 (i, j - 1) 两个格子走过来（不考虑边界条件），那么 vis[i][j] 是否可达的状态则可由如下公式计算得到：
     *       vis[i][j]=vis[i−1][j] or vis[i][j−1]
     *     即只要有一个格子可达，那么 (i, j) 这个格子就是可达的，因此我们只要遍历所有格子，递推计算出它们是否可达然后用变量 ans 记录可达的格子数量即可。
     *    3、初始条件 vis[i][j] = 1 ，递推计算的过程中注意边界的处理。
     * 复杂度分析
     *    时间复杂度：O(mn)，其中 m 为方格的行数， n 为方格的列数。一共有 O(mn) 个状态需要计算，每个状态递推计算的时间复杂度为 O(1)，所以总时间复杂度为 O(mn)。
     *    空间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。我们需要 O(mn) 大小的结构来记录每个位置是否可达。
     */
    //public int movingCount(int m, int n, int k) {
    //    if (k == 0) {
    //        return 1;
    //    }
    //    boolean[][] vis = new boolean[m][n];
    //    int ans = 1;
    //    vis[0][0] = true;
    //    for (int i = 0; i < m; ++i) {
    //        for (int j = 0; j < n; ++j) {
    //            if ((i == 0 && j == 0) || get(i) + get(j) > k) {
    //                continue;
    //            }
    //            // 边界判断
    //            if (i - 1 >= 0) {
    //                vis[i][j] |= vis[i - 1][j];
    //            }
    //            if (j - 1 >= 0) {
    //                vis[i][j] |= vis[i][j - 1];
    //            }
    //            ans += vis[i][j] ? 1 : 0;
    //        }
    //    }
    //    return ans;
    //}
    //
    //private int get(int x) {
    //    int res = 0;
    //    while (x != 0) {
    //        res += x % 10;
    //        x /= 10;
    //    }
    //    return res;
    //}
}





































