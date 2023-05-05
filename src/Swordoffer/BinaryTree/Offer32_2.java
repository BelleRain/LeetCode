package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-21 9:22
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  从上到下打印二叉树 II
 *  从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class Offer32_2 {


    /**
     * 解法一：
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 84.13% 的用户
     * @param root
     * @return
     */
    //每到一层便构建一个list数组,记录当前层节点的个数，之后依次出队列入list
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> reslist = new ArrayList<>();
        if (root == null){
            return reslist;
        }else{
            queue.add(root);
            while (!queue.isEmpty()){
                List<Integer> list = new ArrayList<>();
                //记录当前层节点的个数
                int length = queue.size();
                //当前层节点依次出队列
                for (int i = 0; i < length; i++) {
                    TreeNode node = queue.poll();
                    //加入当前层数组中
                    list.add(node.val);
                    //将当前层节点的左右节点依次加入queue
                    if (node.left != null){
                        queue.add(node.left);
                    }
                    if (node.right != null){
                        queue.add(node.right);
                    }
                }
                //加入结果 list 中
                reslist.add(list);
            }
        }
        return reslist;
    }

    /**
     * 解法二：思路同解法一，注意细节：
     *      for(int i = queue.size(); i > 0; i--) 而不是 for(int i = 0; i < queue.size(); i--) :
     *     这样写的原因为 queue.size() 在下方if语句加入元素后，队列的长度会发生变化,判断条件也会变化，
     *     循环次数也不再是当前层的长度。
     *     而for语句中，初始化 i 的语句只会在 开始执行一次，所以，要将 queue.size() 放在前面。
     *     保险写法 ：
     *      int length = queue.size();
     *      for (int i = 0; i < length; i++)
     */

    //public List<List<Integer>> levelOrder(TreeNode root) {
    //    Queue<TreeNode> queue = new LinkedList<>();
    //    List<List<Integer>> res = new ArrayList<>();
    //    if(root != null) queue.add(root);
    //    while(!queue.isEmpty()) {
    //        List<Integer> tmp = new ArrayList<>();
    //        for(int i = queue.size(); i > 0; i--) {
    //            TreeNode node = queue.poll();
    //            tmp.add(node.val);
    //            if(node.left != null) queue.add(node.left);
    //            if(node.right != null) queue.add(node.right);
    //        }
    //        res.add(tmp);
    //    }
    //    return res;
    //}



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}






































