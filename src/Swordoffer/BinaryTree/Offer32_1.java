package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-21 8:19
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。   
 * 例如: 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *返回：
 *[3,9,20,15,7]
 */
public class Offer32_1 {


    /**
     * 解法一：
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 97.96% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 62.77% 的用户
     * @param root
     * @return
     */
    //树的广度优先遍历
    //利用队列实现
    public int[] levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return new int[0];
        }else {
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        //int length = list.size();
        //int[] res = new int[length];
        //int i = 0;
        //for (Integer item : list) {
        //    res[i++] = item;
        //}

        //写法二：
        int[] res = new int[list.size()];;
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        /**
         * 这里说明一下int[] arr = result.stream().mapToInt(Integer::valueOf).toArray();
         * 是jdk8的新特性，可以将Integer的list转换成为int数组。
         * 想要转换成int[]类型，就得先转成IntStream。
         * 这里就通过mapToInt()把Stream调用Integer::valueOf来转成IntStream
         * 而IntStream中默认toArray()转成int[]。
         */
        //一句话把arrayList<Integer>转成int数组
        int[] res1 = list.stream().mapToInt(Integer::valueOf).toArray();

        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}



































