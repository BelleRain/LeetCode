package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-12 9:48
 */

/**
 * 二叉搜索树的最近公共祖先     链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class Offer68_1 {

    /**
     * 题解：原文链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-i-er-cha-sou-suo-shu-de-zui-jin-g-7/
     * 解题思路：
     * 1、祖先的定义： 若节点 p 在节点 root 的左（右）子树中，或 p = root，则称 root是 p 的祖先。
     * 2、最近公共祖先的定义： 设节点 root 为节点 p,q 的某公共祖先，若其左子节点 root.left 和右子节点 root.right 都不是 p,q 的公共祖先，则称 root 是 “最近的公共祖先” 。
     *   根据以上定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
     *    1、p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
     *    2、p = root，且 q 在 root 的左或右子树中；
     *    3、q = root，且 p 在 root 的左或右子树中；
     * 3、本题给定了两个重要条件：① 树为 二叉搜索树 ，② 树的所有节点的值都是 唯一 的。根据以上条件，可方便地判断 p,q 与 root 的子树关系，即：
     *    1、若 root.val < p.val ，则 p 在 root 右子树 中；
     *    2、若 root.val > p.val ，则 p 在 root 左子树 中；
     *    3、若 root.val = p.val ，则 p 和 root 指向 同一节点 。
     */
    /**
     * 方法一：迭代
     * 1、循环搜索： 当节点 root 为空时跳出；
     *    1、当 p, q 都在 root 的 右子树 中，则遍历至 root.right ；
     *    2、否则，当 p, q 都在 root 的 左子树 中，则遍历至 root.left ；
     *    3、否则，说明找到了 最近公共祖先 ，跳出。
     * 2、返回值： 最近公共祖先 root 。
     * 复杂度分析：
     *   时间复杂度 O(N) ： 其中 N 为二叉树节点数；每循环一轮排除一层，二叉搜索树的层数最小为 logN （满二叉树），最大为 N （退化为链表）。
     *   空间复杂度 O(1) ： 使用常数大小的额外空间。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            if (root.val < p.val && root.val < q.val) //p,q都在右子树
                root = root.right; //遍历至右子节点
            else if(root.val > p.val && root.val > q.val) //p,q都在左子树
                root = root.left; //遍历至左子节点
            else break;  //p,q在左右子树中或 p，q其中一个作为根结点，直接返回当前根节点
        }
        return root;
    }

    //优化，若可保证 p.val < q.val,则在循环中可减少判断条件
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    if (p.val > q.val){ //保证 p.val < q.val
    //        TreeNode tmp = p;
    //        p = q;
    //        q = tmp;
    //    }
    //    while (root != null){
    //        if (root.val < p.val) //p,q都在右子树
    //            root = root.right;
    //        else if (root.val > q.val) //p,q都在左子树
    //            root = root.left;
    //        else break;
    //    }
    //    return root;
    //}

    /**
     * 方法二：递归
     * 1、递推工作：
     *      1、当 p, q 都在 root 的 右子树 中，则开启递归 root.right 并返回；
     *      2、否则，当 p, q 都在 root 的 左子树 中，则开启递归 root.left 并返回；
     * 2、返回值： 最近公共祖先 root 。
     * 复杂度分析：
     * 时间复杂度 O(N)： 其中 N 为二叉树节点数；每循环一轮排除一层，二叉搜索树的层数最小为 logN （满二叉树），最大为 N （退化为链表）。
     * 空间复杂度 O(N)： 最差情况下，即树退化为链表时，递归深度达到树的层数 N 。
     * @param root
     * @param p
     * @param q
     * @return
     */
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    if (root.val < p.val && root.val < q.val)
    //        return lowestCommonAncestor(root.right, p, q);
    //    if (root.val > p.val && root.val > q.val)
    //        return lowestCommonAncestor(root.left, p, q);
    //    return root;
    //}

    /**
     * 题解链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-0wpw1/
     */
    /**
     * 方法三：两次遍历
     * 思路与算法:
     * 注意到题目中给出的是一棵「二叉搜索树」，因此我们可以快速地找出树中的某个节点以及从根节点到该节点的路径，例如我们需要找到节点 p：
     * 我们从根节点开始遍历；
     *      如果当前节点就是 p，那么成功地找到了节点；
     *      如果当前节点的值大于 p 的值，说明 p 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *      如果当前节点的值小于 p 的值，说明 p 应该在当前节点的右子树，因此将当前节点移动到它的右子节点。
     *   对于节点 q 同理。在寻找节点的过程中，我们可以顺便记录经过的节点，这样就得到了从根节点到被寻找节点的路径。
     * 当我们分别得到了从根节点到 p 和 q 的路径之后，我们就可以很方便地找到它们的最近公共祖先了。
     * 显然，p 和 q 的最近公共祖先就是从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点。
     * 因此，如果我们设从根节点到 p 的路径为数组 path_p，从根节点到 q 的路径为数组 path_q，那么只要找出最大的编号 i，其满足
     *                  path_p[i]=path_q[i]
     *  那么对应的节点就是「分岔点」，即 p 和 q 的最近公共祖先就是 path_p[i]（或 path_q[i]）。
     * 复杂度分析:
     * 时间复杂度：O(n)，其中 n 是给定的二叉搜索树中的节点个数。上述代码需要的时间与节点 p 和 q 在树中的深度线性相关，而在最坏的情况下，树呈现链式结构，p 和 q 一个是树的唯一叶子结点，一个是该叶子结点的父节点，此时时间复杂度为 Θ(n)。
     * 空间复杂度：O(n)，我们需要存储根节点到 p 和 q 的路径。和上面的分析方法相同，在最坏的情况下，路径的长度为 Θ(n)，因此需要 Θ(n) 的空间。
     * @param root
     * @param p
     * @param q
     * @return
     */
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    List<TreeNode> path_p = getPath(root, p);
    //    List<TreeNode> path_q = getPath(root, q);
    //    TreeNode ancestor = null;
    //    for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
    //        if (path_p.get(i) == path_q.get(i)) {
    //            ancestor = path_p.get(i);
    //        } else {
    //            break;
    //        }
    //    }
    //    return ancestor;
    //}
    //
    //public List<TreeNode> getPath(TreeNode root, TreeNode target) {
    //    List<TreeNode> path = new ArrayList<TreeNode>();
    //    TreeNode node = root;
    //    while (node != target) {
    //        path.add(node);
    //        if (target.val < node.val) {
    //            node = node.left;
    //        } else {
    //            node = node.right;
    //        }
    //    }
    //    path.add(node);
    //    return path;
    //}

    /**
     *方法四：一次遍历
     * 思路与算法:
     * 在方法三中，我们对从根节点开始，通过遍历找出到达节点 p 和 q 的路径，一共需要两次遍历。我们也可以考虑将这两个节点放在一起遍历。
     * 整体的遍历过程与方法一中的类似：
     * 我们从根节点开始遍历；
     *      如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     *      如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
     *      如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，p 和 q 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
     * 可以发现，如果我们将这两个节点放在一起遍历，我们就省去了存储路径需要的空间。
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 n 是给定的二叉搜索树中的节点个数。分析思路与方法一相同。
     * 空间复杂度：O(1)。
     * @param root
     * @param p
     * @param q
     * @return
     */
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    TreeNode ancestor = root;
    //    while (true) {
    //        if (p.val < ancestor.val && q.val < ancestor.val) {
    //            ancestor = ancestor.left;
    //        } else if (p.val > ancestor.val && q.val > ancestor.val) {
    //            ancestor = ancestor.right;
    //        } else {
    //            break;
    //        }
    //    }
    //    return ancestor;
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































