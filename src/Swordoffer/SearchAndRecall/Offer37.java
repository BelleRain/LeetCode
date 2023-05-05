package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-31 16:31
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 */
public class Offer37 {

    /**
     * 题解链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/solution/mian-shi-ti-37-xu-lie-hua-er-cha-shu-ceng-xu-bian-/
     * 题解一：层序遍历BFS
     *  m:列表区间 [0,n]中的null节点个数，则根节点、左子节点、右子节点的列表索引的递推公式：
     * 索引：node.val != null : node:n , node.left:2(n-m)+1 , node.right:2(n-m)+2
     *      node.val == null : node:n ,无
     * @param root
     * @return
     */
    /*
    序列化复杂度分析：
       时间复杂度 O(N) ： N 为二叉树的节点数，层序遍历需要访问所有节点，最差情况下需要访问 N + 1 个 null ，总体复杂度为 O(2N + 1) = O(N) 。
       空间复杂度 O(N) ： 最差情况下，队列 queue 同时存储 （N+1）/2 个节点（或 N+1 个 null ），使用 O(N) ；列表 res 使用 O(N) 。
     */
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<TreeNode>(){{add(root);}};
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node!=null){
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }


    /*反序列化复杂度分析：
        时间复杂度 O(N) ： N 为二叉树的节点数，按层构建二叉树需要遍历整个 vals ，其长度最大为 2N+1 。
        空间复杂度 O(N) ： 最差情况下，队列 queue 同时存储 (N+1)/2  个节点，因此使用 O(N) 额外空间。*/

    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (!vals[i].equals("null")){
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if (!vals[i].equals("null")){
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }


    /**
     * 题解链接：https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/solution/xu-lie-hua-er-cha-shu-by-leetcode-soluti-4duq/
     */
    /**
     * 方法一：深度优先搜索，前序遍历
     * 复杂度分析
     *      时间复杂度：在序列化和反序列化函数中，我们只访问每个节点一次，因此时间复杂度为 O(n)，其中 n 是节点数，即树的大小。
     *      空间复杂度：在序列化和反序列化函数中，我们递归会使用栈空间，故渐进空间复杂度为 O(n)。
     * @param root
     * @return
     */
   /* public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
    }*/


    /**
     * 方法二：括号表示编码 + 递归下降解码
     * LL(1)型文法
     * 复杂度分析
     *      时间复杂度：序列化时做了一次遍历，渐进时间复杂度为 O(n)。反序列化时，在解析字符串的时候 ptr 指针对字符串做了一次顺序遍历，字符串长度为 O(n)，故这里的渐进时间复杂度为 O(n)。
     *      空间复杂度：考虑递归使用的栈空间的大小，这里栈空间的使用和递归深度有关，递归深度又和二叉树的深度有关，在最差情况下，二叉树退化成一条链，故这里的渐进空间复杂度为 O(n)。
     * @param
     * @return
     */
    /*public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String left = "(" + serialize(root.left) + ")";
        String right = "(" + serialize(root.right) + ")";
        return left + root.val + right;
    }

    public TreeNode deserialize(String data) {
        int[] ptr = {0};
        return parse(data, ptr);
    }

    public TreeNode parse(String data, int[] ptr) {
        if (data.charAt(ptr[0]) == 'X') {
            ++ptr[0];
            return null;
        }
        TreeNode cur = new TreeNode(0);
        cur.left = parseSubtree(data, ptr);
        cur.val = parseInt(data, ptr);
        cur.right = parseSubtree(data, ptr);
        return cur;
    }

    public TreeNode parseSubtree(String data, int[] ptr) {
        ++ptr[0]; // 跳过左括号
        TreeNode subtree = parse(data, ptr);
        ++ptr[0]; // 跳过右括号
        return subtree;
    }

    public int parseInt(String data, int[] ptr) {
        int x = 0, sgn = 1;
        if (!Character.isDigit(data.charAt(ptr[0]))) {
            sgn = -1;
            ++ptr[0];
        }
        while (Character.isDigit(data.charAt(ptr[0]))) {
            x = x * 10 + data.charAt(ptr[0]++) - '0';
        }
        return x * sgn;
    }*/



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
