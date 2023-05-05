package 马士兵大厂面试;

/**
 * @author mxy
 * @create 2023-02-28 9:34
 */

/**
 * 1028. 从先序遍历还原二叉树   https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal/
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 * 示例 1：
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * 示例 2：
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 示例 3：
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 *
 * 提示：
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 *
 */
public class Pro1028 {

    public static void main(String[] args) {
        String str = "1-2--3--4-5--6--7";
        Pro1028 pro1028 = new Pro1028();
        pro1028.recoverFromPreorder(str);
    }

    public static int MAXN = 2001;
    public static int[] queue = new int[MAXN];
    public static int l,r; //[l...r)

    public TreeNode recoverFromPreorder(String traversal) {
        l = 0;
        r = 0;
        int number = 0;
        int level = 0;
        boolean pickLevel = true;
        for (int i = 0; i < traversal.length(); i++) {
            if (traversal.charAt(i) != '-'){
                if (pickLevel){
                    queue[r++] = level;
                    level = 0;
                    pickLevel = false;
                }
                number = number * 10 + traversal.charAt(i) - '0';
            }else {
                if (!pickLevel){
                    queue[r++] = number;
                    number = 0;
                    pickLevel = true;
                }
                level++;
            }
        }

        queue[r++] = number;
        for (int i = l; i < r; i++) {
            System.out.print(queue[i] + " ");
        }

        return f();
    }

    /**
     * 当前，消费的时间是：
     * 层 ： queue[l]
     * 节点值 ：queue[l+1]
     * queue ： 全局变量
     * l......r(终止位置)
     * queue[l] queue[l+1]
     * level value
     * @return
     */
    public static TreeNode f() {
        int level = queue[l++];
        TreeNode head = new TreeNode(queue[l++]);
        if (l < r && queue[l] > level){
            head.left = f();
        }
        if (l < r && queue[l] > level){
            head.right = f();
        }
        return head;
    }

    static class TreeNode{
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
}
