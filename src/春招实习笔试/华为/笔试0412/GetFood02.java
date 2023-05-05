package 春招实习笔试.华为.笔试0412;

/**
 * @author mxy
 * @create 2023-04-13 10:13
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247485291&idx=1&sn=af014f4a5bc4902ab31bdf7d1e765f51&chksm=fc52b6cfcb253fd92bb7d4e51aeb45668466e3bd5ce4f6e2954134db7fc2580cc7e3f6a33fae&cur_album_id=2828382526552391682&scene=190#rd
 * 2.获取最多食物
 * 主办方设计了一个获取食物的游戏。游戏的地图由N个方格组成，每个方格上至多2个传送门，
 * 通过传送门可将参与者传送至指定的其它方格。
 * 同时，每个方格上标注了三个数字:
 * (1) 第一个数字id:代表方格的编号，从0到N-1，每个方格各不相同
 * (2) 第二个数字 parent-id:代表从编号为 parent-id 的方格可以通过传送门传送到当前方格
 *      (-1则表示没有任何方格可以通过传送门传送到此方格，这样的方格在地图中有且仅有一个);
 * (3) 第三个数字value: 取值在[-100，100]的整数值，正整数代表参与者得到相队取值单位的食物，
 * 负整数代表失去相应数值单位的食物(参与者可能存在临时持有食物为负数的情况)，0则代表无变化。
 * 此外，地图设计时保证了参与者不可能到达相同的方格两次，并且至少有一个方格的value是正整数。
 * 游戏开始后，参与者任意选择一个方格作为出发点，当遇到下列情况之一退出游戏:
 *      (1) 参与者当前所处的方格无传送门:
 *      (2) 参与者在任意方格上手动宣布退出游戏 请计算参与者退出游戏后，最多可以获得多少单位的食物
 * 第一行:方块个数 N (N<10000)
 *
 * 样例1输入:
 * 7
 * 0 1 8
 * 1 -1 -2
 * 2 1 9
 * 4 0 -2
 * 5 4 3
 * 3 0 -3
 * 6 2 -3
 * 输出:
 * 9
 * 解释:
 * 参与者从方格0出发，通过传送门到达方格4，再通过传送门到达方格5。
 * 一共获得 8 + (-2) + 3 = 9个单位食物，得到食物展多: 或者参与者在游戏开始时处于方格2，直接主动宣布退出游戏，
 * 也可以获得 9 个单位食物。
 *
 * 样例2输入:
 * 3
 * 0 -1 3
 * 1 0 1
 * 2 0 2
 * 输出:
 * 5
 * 解释:
 * 参与者从方格0出发，通过传送门到达方格2，一共可以获得 3+2=5 个单位食物，此时得到食物最多
 */
public class GetFood02 {


    /**
     * 题解：
     * 树形dp。
     * 我们定义dp[i]表示以节点i结尾，可以获取的最大食物的数量。
     * 对于dp[i]，我们的选择有走到父节点和不走到父节点，我们取最大的即可。也就是
     *      dp[i] = max(当前节点的食物，  当前节点的食物 +  dp[parent_id])
     * 我们需要把所有的dp[i]都枚举一次，最终复杂度为O(n)
     */
    static int n;
    static int[][] nodes;
    static int[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        nodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            int pid = scanner.nextInt();
            int val = scanner.nextInt();
            nodes[id][0] = pid;
            nodes[id][1] = val;
        }

        dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }

    //dp[i] = max(当前节点的食物，  当前节点的食物 +  dp[parent_id])
    public static int dfs(int i){
        if (dp[i] != Integer.MIN_VALUE) return dp[i];
        if (nodes[i][0] == -1) return nodes[i][1];
        dp[i] = Math.max(nodes[i][1], nodes[i][1] + dfs(nodes[i][0]));
        return dp[i];
    }



    /*public static void main(String[] args) {
        int[][] nums = {
                {0,-1,3},
                {1,0,1},
                {2,0,2}
        };
        System.out.println(getFood(nums));
    }*/

    /**
     * 不完全对
     * @param nums
     * @return
     */
    public static int getFood(int[][] nums){
        int sum = 0;
        HashMap<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i][0],nums[i][2]);
            int parentId = nums[i][1];
            if (parentId == -1) continue;
            TreeNode pNode;
            if (!map.containsKey(parentId)){
                pNode = new TreeNode(parentId,nums[parentId][2]);
            }else {
                pNode = map.get(parentId);
                if (pNode.left == null) pNode.left = node;
                else if (pNode.right == null) pNode.right = node;
            }
            map.put(parentId, pNode);
        }

        int start = nums[0][0];
        sum = sum + nums[0][2];
        while (map.containsKey(start)){
            TreeNode node = map.get(start);
            int food1 = 0;
            int food2 = 0;
            if (node.left != null){
                food1 = node.left.value;
            }
            if (node.right != null){
                food2 = node.right.value;
            }
            //若food1 == food2, 则可能会出现错误解
            sum = sum + Math.max(food1, food2);
            start = food1 >= food2 ? node.left.value : node.right.value;
        }
        return sum;
    }
}

//传送门为父节点，可以传送的位置为 left，right
class TreeNode{
    int value;
    int food;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int value,int food){
        this.value = value;
        this.food = food;
    }
    public TreeNode(int value,int food,TreeNode left,TreeNode right){
        this.value = value;
        this.food = food;
        this.left = left;
        this.right = right;
    }
}
