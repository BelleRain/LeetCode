package Top100;

/**
 * @author mxy
 * @create 2022-11-17 11:58
 */

import java.util.*;

/**
 * 22. 括号生成    链接：https://leetcode.cn/problems/generate-parentheses
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 */
public class Top22 {

    public static void main(String[] args) {
        Top22 top22 = new Top22();
        System.out.println(top22.generateParenthesis(2));
        System.out.println(top22.generateParenthesis4(2));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     */

    /**
     * 方法一：深度优先遍历 （做减法）
     * @param n
     * @return
     */
    // 做减法
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * 1、当前左右括号都有大于0个时，才可以产生分支
     * 2、产生左分支时，只看当前是否还有左括号可以使用
     * 3、产生右分支时，还要受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于等于左边剩余数量的时候，才可以产生分支
     * 4、在左边和右边剩余括号为0时结算
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * （严格）回溯写法
     * @param n
     * @return
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        if (n == 0) return res;
        dfs2(res,path,n,n);
        return res;
    }

    private static void dfs2(List<String> res, StringBuilder path, int left, int right) {
        if (left == 0 && right == 0){
            res.add(path.toString() );
            return;
        }
        if (left > right){ //剪枝
            return;
        }
        if (left > 0){
            path.append("(");
            dfs2(res, path, left - 1, right);
            path.deleteCharAt(path.length() - 1);
        }
        if (right > 0){
            path.append(")");
            dfs2(res, path, left, right - 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 方法一：深度优先遍历 （做加法）
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs3("",0,0,n,res);
        return res;
    }

    /**
     * @param curStr   当前递归得到的结果
     * @param left     左括号已经用了几个
     * @param right    右括号已经用了几个
     * @param n        左括号、右括号一共得用几个
     * @param res      结果集
     */
    private void dfs3(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n){
            res.add(curStr);
            return;
        }
        if (left < right) return; //剪枝
        if (left < n) dfs3(curStr + "(", left + 1, right, n, res);
        if (right < n) dfs3(curStr + ")", left, right + 1, n, res);
    }

    /**
     * 方法二：广度优先遍历
     */
    public class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis4(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }


    /*class Node {
        *//**
         * 当前得到的字符串
         *//*
        private String res;
        *//**
         * 剩余左括号数量
         *//*
        private int left;
        *//**
         * 剩余右括号数量
         *//*
    private int right;

    public Node(String str, int left, int right) {
        this.res = str;
        this.left = left;
        this.right = right;
    }
}*/

    // 注意：这是深度优先遍历

    public List<String> generateParenthesis5(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 查看了 Stack 源码，官方推荐使用 Deque 对象，
        // 注意：只使用栈相关的接口，即只使用 `addLast()` 和 `removeLast()`
        Deque<Node> stack = new ArrayDeque<>();
        stack.addLast(new Node("", n, n));

        while (!stack.isEmpty()) {

            Node curNode = stack.removeLast();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                stack.addLast(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                stack.addLast(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }


    /**
     * 上面方法一未回溯，此种方法回溯
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 6.57% 的用户
     */
    List<String> ans = new ArrayList<String>();
    public List<String> generateParenthesis6(int n) {
        backtrack(new StringBuilder(), n, n);
        return ans;
    }

    public void backtrack(StringBuilder cur, int left, int right) {
        if(left == 0 && right == 0){
            ans.add(cur.toString());
            return;
        }
        if(left < right){
            if(left > 0){
                cur.append('(');
                backtrack(cur, left-1, right);
                cur.deleteCharAt(cur.length()-1);
            }
            cur.append(')');
            backtrack(cur, left, right-1);
        }else if(left == right){
            cur.append('(');
            backtrack(cur, left-1, right);
        }
        cur.deleteCharAt(cur.length()-1);
    }
}
