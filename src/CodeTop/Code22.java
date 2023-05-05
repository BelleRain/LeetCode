package CodeTop;

/**
 * @author mxy
 * @create 2023-04-22 11:14
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成     链接：https://leetcode.cn/problems/generate-parentheses
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
 *
 */
public class Code22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis1(2));
        System.out.println(generateParenthesis2(2));
    }

    /**
     * 详解见 Top22
     */

    /**
     * 写法一：path 以 局部变量的形式写入 path + "(", path + ")"
     * @param n
     * @return
     */
    public static List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        String path = new String();
        if (n == 0) return res;
        dfs1(res,path,n,n);
        return res;
    }

    private static void dfs1(List<String> res, String path, int left, int right) {
        if (left == 0 && right == 0){
            res.add(path);
            return;
        }
        if (left > right){ //剪枝
            return;
        }
        if (left > 0){
            dfs1(res, path + "(", left - 1, right);
        }
        if (right > 0){
            dfs1(res, path + ")", left, right - 1);
        }
    }

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
}

































