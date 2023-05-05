package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-22 15:44
 */

/**
 * 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 */
public class Offer26 {

    public static void main(String[] args) {
        //A = [3,4,5,1,2]
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        //B = [4,1]
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(1);
        node6.left = node7;

        Offer26 offer26 = new Offer26();
        boolean res = offer26.isSubStructure(node1, node6);
        System.out.println(res);

    }

    /**
     * 解法一：
     * 解题思路：若树B是树A的子结构，则子结构的根结点可能为树A的任意一个节点。因此，判断树B是否为树A的子结构
     *  需要以下两步工作：
     *      1.先序遍历树A中的每个节点nA；（对应函数 isStructure（A,B））
     *      2.判断树A中以 nA 为根节点的子树 是否包含树B。（对应函数recur（A,B））
     * 算法流程：
     *  recur（A,B）函数：
     *      1.终止条件：
     *          1.当节点B为空：说明树B已匹配完成（越过叶子节点），因此返回true；
     *          2、当节点A为空：说明已经越过树A叶子节点，即匹配失败，返回false；
     *          3、当节点A和B的值不同：说明匹配失败，返回false；
     *     2.返回值：
     *          1、判断A和B的左子节点是否相等，即recur（A.left，B.left）；
     *          2、判断A和B的右子节点是否相等，即recur（A.right，B.right）；
     * isStruture(A,B)函数：
     *     1.特例处理：当树A为空 或 树B为空时，直接返回false；
     *     2.返回值：若树B是树A的子结构，则必满足以下三种情况之一，因此用 或||连接
     *          1.以节点A为根的子树包含树B，对应 recur（A，B）；
     *          2.树B是树A左子树的子结构，对应isStructure（A.left,B）；
     *          3.树B是树A右子树的子结构，对应isStructure（A.right,B）
     *          以上 2. 3. 实质上是在对树 A 做 先序遍历 。
     * 复杂度分析： 时间复杂度 O(MN)： 其中 M,N 分别为树 A 和 树 B 的节点数量；先序遍历树 A 占用 O(M) ，每次调用 recur(A, B) 判断占用 O(N)。
     *            空间复杂度 O(M) ： 当树 A 和树 B 都退化为链表时，递归调用深度最大。
     *                  当 M≤N 时，遍历树 A 与递归判断的总递归深度为 M ；
     *                  当 M>N 时，最差情况为遍历至树 A 叶子节点，此时总递归深度为 M。
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A,TreeNode B){
        return (A!=null && B!=null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B ));
    }

    boolean recur(TreeNode A,TreeNode B){
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * 递归注意： 要明确结束的条件，
     *          尽量不要深究程序执行细节，
     *          按照逻辑书写递归程序
     */

    /**
     * 另一种易懂写法：
     */
    //public boolean isSubStructure(TreeNode A,TreeNode B){
    //    if (B == null || A == null){
    //        return false;
    //    }
    //    if (A.val == B.val && (recur(A.left, B.left) && recur(A.right, B.right))) {
    //        return true;
    //    }
    //    return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    //}
    //
    //boolean recur(TreeNode A,TreeNode B){
    //    if (B == null) return true;
    //    if (A == null) return false;
    //    if (A.val == B.val){
    //        return recur(A.left, B.left) && recur(A.right, B.right);
    //    }else {
    //        return false;
    //    }
    //}


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
















































