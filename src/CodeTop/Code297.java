package CodeTop;

/**
 * @author mxy
 * @create 2023-05-25 8:26
 */


import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化    链接：https://leetcode.cn/problems/serialize-and-deserialize-binary-tree
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 提示：
 * 树中结点数在范围 [0, 10^4] 内
 * -1000 <= Node.val <= 1000
 *
 */
public class Code297 {

    public static void main(String[] args) {

    }

    /**
     * 题解链接：https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/solution/shou-hui-tu-jie-gei-chu-dfshe-bfsliang-chong-jie-f/
     * 评论区Java版
     */

    /**
     * 方法一： dfs遍历
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize_dfs(TreeNode root) {
        if (root == null){
            return "X,";
        }
        String left = serialize_dfs(root.left);
        String right = serialize_dfs(root.right);
        return root.val + "," + left + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize_dfs(String data) {
        String[] nodes = data.split(",");
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(nodes));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("X")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }


    /**
     * 方法二： bfs遍历
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("X,");
            } else {
                sb.append(node.val + ",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(",")));
        TreeNode root = new TreeNode(Integer.parseInt(nodes.poll()));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            //queue 和 nodes 为层层对应的
            TreeNode node = queue.poll(); // queue 用于存放节点，node 为当前节点
            String left = nodes.poll();  //从 nodes 取出当前元素的左节点和右结点
            String right = nodes.poll();
            if (!left.equals("X")){
                node.left = new TreeNode(Integer.parseInt(left));
                queue.add(node.left);
            }
            if (!right.equals("X")){
                node.right = new TreeNode(Integer.parseInt(right));
                queue.add(node.right);
            }
        }
        return root;
    }

    //Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode(int x) {
             val = x;
         }
     }

}
















































