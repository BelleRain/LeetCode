package CodeTop;

/**
 * @author mxy
 * @create 2023-06-21 10:45
 */

/**
 * 450. 删除二叉搜索树中的节点     链接：https://leetcode.cn/problems/delete-node-in-a-bst
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 * 示例 1:
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 *
 * 示例 3:
 * 输入: root = [], key = 0
 * 输出: []
 *  
 * 提示:
 * 节点数的范围 [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -10^5 <= key <= 10^5
 *
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 */
public class Code450 {


    /**
     * 题解链接：https://leetcode.cn/problems/delete-node-in-a-bst/solution/miao-dong-jiu-wan-shi-liao-by-terry2020-tc0o/
     */

    /**
     * 方法一： 可能会增加树的高度
     * 算法思想：
     *   根据二叉搜索树的性质：
     *      1、如果目标节点大于当前节点值，则去右子树中删除；
     *      2、如果目标节点小于当前节点值，则去左子树中删除；
     *      3、如果目标节点就是当前节点，分为以下三种情况：
     *            — 其无左子：其右子顶替其位置，删除了该节点；
     *            — 其无右子：其左子顶替其位置，删除了该节点；
     *            — 其左右子节点都有：其左子树转移到其右子树的最左节点的左子树上，然后右子树顶替其位置，由此删除了该节点。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) return null;
        if (key > root.val) {
            //去右子树删除
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            //去左子树删除
            root.left = deleteNode(root.left, key);
        } else {
            //当前节点就是要删除的节点
            //情况1，欲删除的节点无左子
            if (root.left == null) return root.right;
            //情况2，欲删除的节点无右子
            if (root.right == null) return root.left;
            //情况3，欲删除的节点左右子都有
            TreeNode node = root.right;
            while (node.left != null) {
                //寻找欲删除节点右子树的最左节点
                node = node.left;
            }
            //将欲删除节点的左子树成为其右子树的最左节点的左子树
            node.left = root.left;
            //欲删除节点的右子树顶替其位置，节点被删除
            root = root.right;
        }
        return root;
    }


    /**
     * 方法二： 不会增加树的高度
     *     — 如果目标节点没有子节点，直接移除该目标节点
     *     — 如果目标节点只有一个节点，用其子节点作为替换
     *     — 如果目标节点有两个子节点，用其中序后继节点或者前驱节点来替换，再删除该目标节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            //当前节点的值小于key，去左子树上寻找待删除的点
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            //当前节点的值大于key，去右子树上寻找待删除的点
            root.right = deleteNode(root.right, key);
        } else {
            //当前节点就是待删除的节点
            //情况1，如果当前节点是叶子节点，直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                //情况2，如果当前节点的右子树存在，用下一节点（右子树的最左节点）替换当前节点，并在右子树上删除下一个节点
                //用下一节点（右子树的最左节点）替换当前节点
                root.val = getNextValue(root);
                //去右子树上将下一节点删除
                root.right = deleteNode(root.right, root.val);
            } else {
                //情况3，当前节点的右子树不存在，但是左子树存在时，用上一个（左子树的最右节点）替换当前节点，并在左子树上删除上一个节点
                root.val = getPrevValue(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    //寻找当前节点的后继节点的值
    private int getNextValue(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    //寻找当前节点的前驱节点的值
    private int getPrevValue(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}














































