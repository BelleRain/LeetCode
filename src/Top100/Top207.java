package Top100;

/**
 * @author mxy
 * @create 2022-12-07 10:46
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表     链接：https://leetcode.cn/problems/course-schedule
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *  
 *
 * 提示：
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 */
public class Top207 {

    public static void main(String[] args) {
        Top207 top207 = new Top207();
        int[][] prerequisites = {
                {1,0}
                //{0,1}
        };
        System.out.println(top207.canFinish(2, prerequisites));
    }

    /**
     * 题解链接： 文字题解：https://leetcode.cn/problems/course-schedule/solution/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/
     *                   https://leetcode.cn/problems/course-schedule/solution/tuo-bu-pai-xu-by-liweiwei1419/
     *           代码题解：https://leetcode.cn/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
     */

    /**
     * （重要）方法一：BFS，「广度优先遍历 + 贪心」的思想是常考的，重要的知识点。
     * 1、BFS 前的准备工作：每门课的入度需要被记录，我们关心入度值的变化。
     *                   课程之间的依赖关系也要被记录，我们关心选当前课会减小哪些课的入度。
     * 2、因此我们需要选择合适的数据结构，去存这些数据：
     *      入度数组：课号 0 到 n - 1 作为索引，通过遍历先决条件表求出对应的初始入度。
     *      邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
     *              key：课号
     *              value：依赖这门课的后续课（数组）
     * 3、 邻接表：通过结点的索引，我们能够得到这个结点的后继结点；
     *     入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
     * 4、这里为什么要使用队列？
     *     如果不使用队列，要想得到当前入度为 0 的结点，就得遍历一遍入度数组。使用队列即用空间换时间。
     * 5、举例： [[3, 0], [3, 1], [4, 1], [4, 2], [5, 3], [5, 4]]
     *          0
     *              -> 3
     *          1          -> 5
     *              -> 4
     *          2
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //存放每个点的入度
        int[] indegrees = new int[numCourses];
        //存放邻接点
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        //对每个点，建立邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        //1、获取每门课的入度和邻接点
        for (int[] cp : prerequisites) { //图中边的指向方向 cp[1] -> cp[0]
            indegrees[cp[0]]++;  //对于[a,b]，a依赖于b，方向 b -> a ,每遍历到cp[0],其入度加1
            adjacency.get(cp[1]).add(cp[0]); //将 cp[1] 的后继节点 cp[0] 加入 cp[1] 的邻接表中
        }
        //2、获取所有入度为 0 的课程
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.add(i); //将所有入度为0的节点入队
        }
        //3、BFS,通过入度为 0 的点，寻找其他点
        while (!queue.isEmpty()){    //队列中存放的都是入度为0的点
            int pre = queue.poll();  //每次出队，则总课程数 numCourse - 1
            numCourses--;
            for (int cur : adjacency.get(pre)) { //获取 pre 的邻接点cur，并将 cur 的入度减 1
                if (--indegrees[cur] == 0) queue.add(cur); //如果 邻接点 cur 的 入度为0 ，则入队
            }
        }
        //点的入度为0，证明该点不依赖其他点，即该门课程不依赖其他课程，可以完成。若图中出现环，则无法完成所有课程。
        //判断出队的 入度为0 的 点数是否 为 numCourse，如果numCourses不为0，则证明还有入度不为 0 的点，说明点与点还有关联，即图中出现了环
        return numCourses == 0;
    }

    /**
     * 方法二：DFS
     *    通过 DFS 判断图中是否有环。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    /*public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency,flags,i))
                return false;
        }
        return true;
    }*/

    /*private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j))
                return false;
        }
        flags[i] = -1;
        return true;
    }*/

    /* DFS代码解释：
    记住这三个标志位对应的状态
    i == 0 ： 干净的，未被 DFS 访问
    i == -1：其他节点启动的 DFS 访问过了，路径没问题，不需要再访问了
    i == 1  ：本节点启动的 DFS 访问过了，一旦遇到了也说明有环了

    有0、1、2、3、4、5  共6门课程。 [[5,3],[5,4],[3,0],[3,1],[4,1],[4,2]]。学5之前要先学3，学5之前要先学4......

    0          邻接表
       \       下标  0     1     2     3    4    5   同时也表示要先学的课程
       /   3        [3]  [3,4]  [4]   [5]  [5]  [ ]  学完上面的课程才能学的课程，例如学完0才能学3，学完1才能学3和4
    1        \
       \     /  5     这个邻接表的创建是通过        for(int[] cp : prerequisites)
          4                                           adjacency.get(cp[1]).add(cp[0]);         实现的
       /
    2

    然后就是遍历+标记判断有无环：

    1、从课程0启动DFS，先判断下，哦，节点0还没被访问，将flag[0]置1，表明被当前节点启动的dfs访问过了，在访问0时
    就通过以下代码一连串把3、5都访问了,返回true之前标志位置-1，这样从其他节点进来看到标志位-1时就无需再访问了，
    直接返回true.

    if(flags[i] == 1) return false;  //先判断再修改标志位
    if(flags[i] == -1) return true;  //别的dfs路径访问过了，我不需要访问了
    flags[i] = 1;   //只有这个标志位是干净的，别人还没有动过，我才能标记为1，说明本次dfs我遍历过它
    for(Integer j : adjacency.get(i))
       if(!dfs(adjacency, flags, j))
           return false;
    flags[i] = -1;    //只有一次DFS完整结束了，才能执行到这一步，标记为-1，说明这条路没问题，再遇到不需要遍历了
    return true;

    2.从课程1启动DFS一样的道理，3、5上一步已经访问过了标志位为-1，这一步就不访问了，只要访问4，并把标志位置为-1,
    3、剩下的同理。这里是举了个无环的情况，那有环是什么样子呢？
    下标  0     1     2     3    4    5   先学的课程
         [3]  [3,4]  [4]   [5]  [5]  [3 ]  这里多加了一个，先学5才能学3，这回肯定就有问题了

    在第一步从课程0启动DFS，一连串访问3，5，从5又遍历到3时，由于我们本节点启动的dfs已经访问过3了，标志位为1，
    所以碰到标志位为1，说明有环了，直接返回false;一旦某次df返回了false,整个遍历就结束了，返回最终结果false
     */
}
