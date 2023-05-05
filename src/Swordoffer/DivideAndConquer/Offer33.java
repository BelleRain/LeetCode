package Swordoffer.DivideAndConquer;

/**
 * @author mxy
 * @create 2022-10-17 19:10
 */

/**
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 */
public class Offer33 {

    /**
     * 题解链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     */

    /**
     * 方法一：递归分治
     * @param postorder
     * @return
     * 复杂度分析：
     * 时间复杂度 O(N^2)：每次调用 recur(i,j) 减去一个根节点，因此递归占用 O(N) ；最差情况下（即当树退化为链表），每轮递归都需遍历树所有节点，占用 O(N)。
     * 空间复杂度 O(N)：最差情况下（即当树退化为链表），递归深度将达到 N 。
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    /**
     * 判断子树是否为二叉搜索树
     * @param postorder 后序遍历 = 左子树 + 右子树 + 根节点
     * @param i  左边起始索引
     * @param j  右边末尾索引（根节点索引）
     * @return
     */
    boolean recur(int[] postorder,int i,int j){
        //子树节点数量 <= 1,无需判别正确性，直接返回true
        if (i >= j) return true;
        int p = i;
        //划分左子树：左子树的值 < 根节点
        //左子树区间： [i,m-1]
        while (postorder[p] < postorder[j]) p++;
        //第一个大于根节点的值，右子树遍历的第一个节点
        int m = p;
        //划分右子树：右子树的值 > 根节点
        //右子树区间：[m,j-1]
        while (postorder[p] > postorder[j]) p++;
        //若p==j，则证明左右子树均正确，根节点正确，此树正确
        return p == j && recur(postorder, i, m-1) && recur(postorder, m, j-1);
    }

    /**
     * 方法二：辅助单调栈  参考解析：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
     * 后序遍历的逆序 = 根节点 + 右子树 + 左子树
     * 挨着的两个数如果arr[i]<arr[i+1]，那么arr[i+1]一定是arr[i]的右子节点
     * 如果arr[i]>arr[i+1]，那么arr[i+1]一定是arr[0]……arr[i]中某个节点的左子节点，并且这个值是大于arr[i+1]中最小的。
     * @param postorder
     * @return
     */
    //public boolean verifyPostorder(int[] postorder) {
    //    Stack<Integer> stack = new Stack<>();
    //    int root = Integer.MAX_VALUE;
    //    //注意for循环是倒序遍历的
    //    for (int i = postorder.length -1 ; i >= 0; i--) {
    //        //只要遇到了某一个左子节点，才会执行while的代码，才会更新parent的值，
    //        //否则parent就是一个非常大的值，也就是说如果没有遇到左子节点，那么右子节点可以非常大
    //        if (postorder[i] > root) return false;
    //        //如果当前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙
    //        //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
    //        while (!stack.isEmpty() && stack.peek() > postorder[i])
    //            root = stack.pop();
    //        //入栈，栈顶元素 < 当前节点，（逆序）数组升序
    //        stack.add(postorder[i]);
    //    }
    //    return true;
    //}

}






























