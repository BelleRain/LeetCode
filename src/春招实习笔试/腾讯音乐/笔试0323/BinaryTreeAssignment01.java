package 春招实习笔试.腾讯音乐.笔试0323;

/**
 * @author mxy
 * @create 2023-03-24 9:46
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 链接：https://mp.weixin.qq.com/s/gBy28P-8DNEb0DtdGn6gEw
 * 第一题：二叉树赋值
 * 小红拿到了一个二叉树，二叉树共有n个节点。小红希望你将所有节点赋值为1到n的正整数，且没有两个节点的值相等。
 * 需要满足:奇数层的权值和与偶数层的权值和之差的绝对值不超过1。
 * 如果有多种赋值方案，请返回任意—种方案。如果无解，请返回空树。数据范围: 1< n ≤10^5。
 * 给定的二叉树节点初始权值默认为-1。
 *
 * 示例1:
 * 输入
 * {-1,-1,-1}
 * 输出
 * {3,1,2}
 * 示例2
 * 输入
 * {-1,-1,#,-1,-1}
 * 输出
 * {}
 * 示例3
 * 输入
 * {-1,-1,-1,#,-1,-1}
 * 输出
 * {1,3,4,#,2,5}
 */
public class BinaryTreeAssignment01 {

    public static void main(String[] args) {
        BinaryTreeAssignment01 resultTree = new BinaryTreeAssignment01();
        BinaryTree tree = new BinaryTree();
        TreeNode node1 = new TreeNode(-1);
        TreeNode node2 = new TreeNode(-1);
        TreeNode node3 = new TreeNode(-1);
        TreeNode node4 = new TreeNode(-1);
        TreeNode node5 = new TreeNode(-1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;
        TreeNode fun = resultTree.fun(node1);
        tree.preOrder(fun);
    }

    /**
     * 思路和代码
     * 【此代码未进行大量数据的测试，仅供参考，有疑问欢迎讨论】
     * 贪心。如果奇数层的节点数和偶数层的节点数大于等于2，必然无解。
     * 分配策略为:
     * 较少数量的层[n,n-3....
     * 较多数量的层[n-1,n-2....
     */
    List<TreeNode> odd = new ArrayList<>();
    List<TreeNode> even = new ArrayList<>();
    public TreeNode fun(TreeNode root){
        //1 2 3 4 5 6 7
        cnts(root, 1);
        int n = odd.size() + even.size();
        if (Math.abs(odd.size() - even.size()) >= 2){
            return null;
        }
        if (odd.size() < even.size()){
            List<TreeNode> temp = new ArrayList<>();
            temp = odd;
            odd = even;
            even = temp;
        }
        int i = n - 3, j = n - 2;
        int sum1 = n;
        int sum2 = n - 1;
        even.get(0).val = n;
        odd.get(0).val = n - 1;
        int index1 = 1;
        int index2 = 1;
        for (int k = 0; k < n - 2; k++) {
            if (sum1 <= sum2){
                if (index1 >= even.size()) break;
                even.get(index1).val = j;
                index1++;
                sum1 = sum1 + j;
            }else {
                if (index2 >= odd.size()) break;
                sum2 = sum2 + j;
                odd.get(index2).val = j;
                index2++;
            }
            j--;
        }
        while (index1 < even.size()){
            even.get(index1).val = j;
            j--;
            index1++;
        }
        while (index2 < odd.size()){
            odd.get(index2).val = j;
            j--;
            index2++;
        }
        return root;
    }

    public void cnts(TreeNode node,int i){
        if (node == null) return;
        if (i % 2 == 0){
            even.add(node);
        }else {
            odd.add(node);
        }
        cnts(node.left,i + 1);
        cnts(node.right, i + 1);
    }

}

class BinaryTree{
    TreeNode root;
    //public void setRoot(TreeNode node){
    //    this.root = root;
    //}
    public void preOrder(TreeNode root){
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
}


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}