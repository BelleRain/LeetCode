package Swordoffer.DivideAndConquer;

/**
 * @author mxy
 * @create 2022-10-13 8:06
 */

import java.util.HashMap;

/**
 * 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 */
public class Offer07 {

    /**
     * 方法一： 递归 原文链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/
     * 解题思路：
     *      前序遍历性质： 节点按照 [ 根节点 | 左子树 | 右子树 ] 排序。
     *      中序遍历性质： 节点按照 [ 左子树 | 根节点 | 右子树 ] 排序。
     * 以题目示例为例：
     *      前序遍历划分 [ 3 | 9 | 20 15 7 ]
     *      中序遍历划分 [ 9 | 3 | 15 20 7 ]
     *  根据以上性质，可得出以下推论：
     *      1、前序遍历的首元素 为 树的根节点 node 的值。
     *      2、在中序遍历中搜索根节点 node 的索引 ，可将 中序遍历 划分为 [ 左子树 | 根节点 | 右子树 ] 。
     *      3、根据中序遍历中的左（右）子树的节点数量，可将 前序遍历 划分为 [ 根节点 | 左子树 | 右子树 ]
     *  通过以上三步，可确定 三个节点 ：1.树的根节点、2.左子树根节点、3.右子树根节点。
     *  根据「分治算法」思想，对于树的左、右子树，仍可复用以上方法划分子树的左右子树。
     * 分治算法解析：
     *  1、递推参数： 根节点在前序遍历的索引 root 、子树在中序遍历的左边界 left 、子树在中序遍历的右边界 right ；
     *  2、终止条件： 当 left > right ，代表已经越过叶节点，此时返回 null ；
     *  3、递推工作：
     *          1、建立根节点 node ： 节点值为 preorder[root] ；
     *          2、划分左右子树： 查找根节点在中序遍历 inorder 中的索引 i ；
     *          为了提升效率，本文使用哈希表 dic 存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1) ；
     *          3、构建左右子树： 开启左右子树递归；
     *              根节点索引	        中序遍历左边界	     中序遍历右边界
     *    左子树	    root + 1	            left	           i - 1
     *    右子树	 i - left + root + 1	    i + 1	            right
     *    TIPS： i - left + root + 1 含义为 根节点索引 + 左子树长度 + 1
     *  4、返回值： 回溯返回 node ，作为上一层递归中根节点的左 / 右子节点；
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为树的节点数量。初始化 HashMap 需遍历 inorder ，占用 O(N) 。递归共建立 N 个节点，每层递归中的节点建立、搜索操作占用 O(1) ，因此使用 O(N) 时间。
     * 空间复杂度 O(N) ： HashMap 使用 O(N) 额外空间；最差情况下（输入二叉树为链表时），递归深度达到 N ，占用 O(N) 的栈帧空间；因此总共使用 O(N) 空间。
     * 注意：本文方法只适用于 “无重复节点值” 的二叉树。
     * @param preorder
     * @param inorder
     * @return
     */
    int[] preorder;
    HashMap<Integer,Integer> dic = new HashMap<>();  //存储 中序遍历 的值与索引的映射
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            dic.put(inorder[i],i);
        }
        return recur(0, 0, inorder.length - 1);
    }

    /**
     *
     * @param root 根结点在前序遍历的索引root
     * @param left 子树在中序遍历的左边界left
     * @param right 子树在中序遍历的右边界right
     * @return
     */
    //前序遍历 = 根节点 + 左子树 + 右子树
    //中序遍历 = 左子树 + 根节点 + 右子树
    public TreeNode recur(int root,int left,int right){
        //相等就是自己，大于便是越界  当 left > right ，代表已经越过叶节点，此时返回 null ；
        if (left > right) return null;    //递归终止
        TreeNode node = new TreeNode(preorder[root]); //建立根节点
        int i = dic.get(preorder[root]); //划分根结点、左子树、右子树
        // i ：根结点在中序遍历的索引
        //左子树的根节点索引 = 前序遍历root+1，左子树 中序遍历的左边界 = left ，左子树 中序遍历的右边界 = i-1
        node.left = recur(root+1, left, i-1);  //开启左子树递归
        //右子树的根结点索引 = 前序遍历 root + 左子树的长度 + 1 ，右子树 中序遍历的左边界 = i + 1，右子树 中序遍历的右边界 = right
        //左子树的长度 = 中序遍历中 （i-1) - left + 1 = i - left
        node.right = recur(root + i - left + 1, i+1, right); //开启右子树递归
        return node;
    }

    /**
     * 方法二：迭代 ，详解见原文链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-by-leetcode-s/
     * @param preorder
     * @param inorder
     * @return
     */
    //public TreeNode buildTree(int[] preorder, int[] inorder) {
    //    if (preorder == null || preorder.length == 0) {
    //        return null;
    //    }
    //    TreeNode root = new TreeNode(preorder[0]);
    //    Deque<TreeNode> stack = new LinkedList<TreeNode>();
    //    stack.push(root);
    //    int inorderIndex = 0;
    //    for (int i = 1; i < preorder.length; i++) {
    //        int preorderVal = preorder[i];
    //        TreeNode node = stack.peek();
    //        if (node.val != inorder[inorderIndex]) {
    //            node.left = new TreeNode(preorderVal);
    //            stack.push(node.left);
    //        } else {
    //            while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
    //                node = stack.pop();
    //                inorderIndex++;
    //            }
    //            node.right = new TreeNode(preorderVal);
    //            stack.push(node.right);
    //        }
    //    }
    //    return root;
    //}



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
