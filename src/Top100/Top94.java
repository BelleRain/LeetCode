package Top100;


/**
 * @author mxy
 * @create 2022-11-23 20:44
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. 二叉树的中序遍历   链接：https://leetcode.cn/problems/binary-tree-inorder-traversal
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *  
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Top94 {

    public static void main(String[] args) {
        Top94 top94 = new Top94();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        System.out.println(top94.inorderTraversal1(node1));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/solution/dong-hua-yan-shi-94-er-cha-shu-de-zhong-xu-bian-li/
     */

    /**
     * 方法一： 递归实现中序遍历
     * 复杂度分析  时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     * @param root
     * @return
     */
    /*List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return res;
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }*/

    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = inorderTraversal1(root.left);
        result.add(root.val);
        result.addAll(inorderTraversal1(root.right));
        return result;
    }

    /**
     * 方法二： 迭代
     * 1、递归 是 不断向左 遍历，迭代利用栈模拟该过程
     * 2、不断向左遍历 将 左子节点 加入 栈中，
     * 3、当 到达叶子节点后 ，栈顶出栈 加入res 中，
     * 时间复杂度：O(n)
     * 空间复杂度：O(h)，h 是树的高度
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (stack.size() != 0 || root != null){
            if (root != null){ //不断往左子树方向走，每走一次就将当前节点保存到栈中，这是模拟递归的调用
                stack.addLast(root);
                root = root.left;
            }else { //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
                TreeNode tmp = stack.removeLast();
                res.add(tmp.val);
                root = tmp.right;
            }
        }
        return res;
    }

    /**
     * 方法三：莫里斯遍历 ： 关键点 ： 定位 当前 节点 root 的 前驱节点
     * 用递归和迭代的方式都使用了辅助的空间，而莫里斯遍历的优点是没有使用任何辅助空间。
     * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构。
     * 复杂度分析：
     *      时间复杂度:找到每个前驱节点的复杂度是 O(n)，因为 n 个节点的二叉树有 n-1 条边，
     *      每条边只可能使用 2 次(一次定位到节点，一次找到前驱节点)，故时间复杂度为 O(n)
     *      空间复杂度：O(1)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        while (root != null){
            //如果左节点不为空，就将当前节点连带右子树全部挂到
            //左节点的最右子树下面
            if (root.left != null){
                pre = root.left;
                while (pre.right != null){
                    pre = pre.right;
                } //pre 为 root 的前驱节点
                pre.right = root;
                //将root指向 root的left
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
                //左子树为空，则打印这个节点，并向右边遍历
            }else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

