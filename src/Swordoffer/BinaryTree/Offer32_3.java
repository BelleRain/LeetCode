package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-21 10:21
 */

import java.util.*;

/**
 * 从上到下打印二叉树 III
 * 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *  3
 * / \
 *9  20
 *   /  \
 * 15   7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class Offer32_3 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;

        Offer32_3 offer = new Offer32_3();

        List<List<Integer>> res = offer.levelOrder(node1);
        System.out.println(res);

    }

    /**
     * 解法一：错误点：举例： 对于 [1,2,3,4,null,null,5]，输出 [[1], [3, 2], [5, 4]]
     *          原因：当遍历第二层时，加入队列中的顺序为 [3,2];
     *              当出队列时，第一次循环 i=0，node = 3，node.left = null，node.right = 5，
     *              又level=3，故 5先从队尾入队；同理，node = 2，node.left = 4，故4后入队。出队顺序为 [5,4]
     *        综上：队列入队的顺序不可变，但存入 每层数组list 的顺序随层数改变
     * @param root
     * @return
     */
    //使用双向队列
    //记录当前层的节点数
    //记录当前层的层数，层数为奇数时，队尾入，队头出；层数为偶数时，队头入，队头出
    //public List<List<Integer>> levelOrder(TreeNode root) {
    //
    //    Deque<TreeNode> deque = new ArrayDeque<>();
    //    List<List<Integer>> res = new ArrayList<>();
    //    int level = 1;  //记录当前层数
    //    if (root == null) {
    //        return res;
    //    }
    //    deque.addLast(root);
    //    while (!deque.isEmpty()) {
    //        List<Integer> list = new ArrayList<>();
    //        int length = deque.size();
    //        //每次大循环 level+1
    //        level++;
    //        for (int i = 0; i < length; i++) {
    //            //无论哪一层节点，都在头部出
    //           TreeNode node = deque.pollFirst();
    //           list.add(node.val);
    //           //如果下一层的层数为奇数，则队尾入
    //           if (level % 2 != 0){
    //               if (node.left != null){
    //                    deque.addLast(node.left);
    //               }
    //               if (node.right != null){
    //                   deque.addLast(node.right);
    //               }
    //           }else{ //如果下一层层数为偶数，则队头入
    //               if (node.left != null){
    //                   deque.addFirst(node.left);
    //               }
    //               if (node.right != null){
    //                   deque.addFirst(node.right);
    //               }
    //           }
    //        }
    //        res.add(list);
    //    }
    //    return res;
    //}

    /**
     * 解法一：层序遍历 + 双端队列    亮点：if (res.size()%2 == 0) tmp.addLast(node.val); res.size()%2 == 0说明在奇数层
     *      利用双端队列的两端皆可添加元素的特性，设打印列表（双端队列） tmp ，
     *      并规定： 奇数层 则添加至 tmp 尾部 ，
     *      偶数层 则添加至 tmp 头部 。
     * 算法流程：
     *      特例处理： 当树的根节点为空，则直接返回空列表 [] ；
     *      初始化： 打印结果空列表 res ，包含根节点的双端队列 deque ；
     *      BFS 循环： 当 deque 为空时跳出； 新建列表 tmp ，用于临时存储当前层打印结果；
     *      当前层打印循环： 循环次数为当前层节点数（即 deque 长度）；
     *      出队： 队首元素出队，记为 node；
     *      打印： 若为奇数层，将 node.val 添加至 tmp 尾部；否则，添加至 tmp 头部；
     *      添加子节点： 若 node 的左（右）子节点不为空，则加入 deque ；
     *      将当前层结果 tmp 转化为 list 并添加入 res ；
     *      返回值： 返回打印结果列表 res 即可；
     * @param root
     * @return
     *
     */
    //用结果数组的长度代表 层数
    //public List<List<Integer>> levelOrder(TreeNode root) {
    //    Deque<TreeNode> deque = new LinkedList<>();
    //    List<List<Integer>> res = new ArrayList<>();
    //    if (root == null){
    //        return res;
    //    }
    //    deque.addFirst(root);
    //    while (!deque.isEmpty()){
    //        int length = deque.size();
    //        LinkedList<Integer> tmp = new LinkedList<>();
    //        for (int i = 0; i < length; i++) {
    //            //层数从 0 开始，层数为 res数组的长度
    //            TreeNode node = deque.removeFirst();
    //            //层数为偶数，则队尾入
    //            if (res.size()%2 == 0) tmp.addLast(node.val);
    //            //层数为奇数，则队头入
    //            else tmp.addFirst(node.val);
    //            if (node.left != null) deque.add(node.left);
    //            if (node.right != null) deque.add(node.right);
    //        }
    //        res.add(tmp);
    //    }
    //    return res;
    //}

    /**
     * 方法二：层序遍历 + 双端队列（奇偶层逻辑分离）
     * 方法一代码简短、容易实现；但需要判断每个节点的所在层奇偶性，即冗余了 N 次判断。
     * 通过将奇偶层逻辑拆分，可以消除冗余的判断。 算法流程： 与方法一对比，仅 BFS 循环不同。
     * BFS 循环： 循环打印奇 / 偶数层，当 deque 为空时跳出；
     *  打印奇数层： 从左向右 打印，先左后右 加入下层节点；
     *  若 deque 为空，说明向下无偶数层，则跳出；
     *  打印偶数层： 从右向左 打印，先右后左 加入下层节点；
     */
    //public List<List<Integer>> levelOrder(TreeNode root) {
    //    Deque<TreeNode> deque = new LinkedList<>();
    //    List<List<Integer>> res = new ArrayList<>();
    //    if(root != null) deque.add(root);
    //    while(!deque.isEmpty()) {
    //        // 打印奇数层
    //        List<Integer> tmp = new ArrayList<>();
    //        for(int i = deque.size(); i > 0; i--) {
    //            // 从左向右打印
    //            TreeNode node = deque.removeFirst();
    //            tmp.add(node.val);
    //            // 先左后右加入下层节点
    //            if(node.left != null) deque.addLast(node.left);
    //            if(node.right != null) deque.addLast(node.right);
    //        }
    //        res.add(tmp);
    //        if(deque.isEmpty()) break; // 若为空则提前跳出
    //        // 打印偶数层
    //        tmp = new ArrayList<>();
    //        for(int i = deque.size(); i > 0; i--) {
    //            // 从右向左打印
    //            TreeNode node = deque.removeLast();
    //            tmp.add(node.val);
    //            // 先右后左加入下层节点
    //            if(node.right != null) deque.addFirst(node.right);
    //            if(node.left != null) deque.addFirst(node.left);
    //        }
    //        res.add(tmp);
    //    }
    //    return res;
    //}

    /**亮点： if(res.size() % 2 == 1) Collections.reverse(tmp);
     *方法三：层序遍历 + 倒序
     * 此方法的优点是只用列表即可，无需其他数据结构。
     * 偶数层倒序： 若 res 的长度为 奇数 ，说明当前是偶数层，则对 tmp 执行 倒序 操作。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            //若 res 的长度为 奇数 ，说明当前是偶数层，则对 tmp 执行 倒序 操作,再将 tmp 加入 res
            if(res.size() % 2 == 1) Collections.reverse(tmp);
            res.add(tmp);
        }
        return res;
    }





    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
































