package CodeTop;

/**
 * @author mxy
 * @create 2023-04-14 16:03
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列    链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 限制：
 * 1 <= s 的长度 <= 8
 */


public class Offer38 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abc")));
        System.out.println(Arrays.toString(permutation("acc")));
    }

    public static String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        if (s.length() == 0) return res.toArray(new String[s.length()]);
        char[] chars = s.toCharArray();
        boolean[] visited = new boolean[chars.length];
        Arrays.sort(chars);
        dfs(res,chars,visited,builder,0);
        return res.toArray(new String[s.length()]);
    }

    private static void dfs(List<String> res, char[] chars, boolean[] visited, StringBuilder builder, int level) {
        if (level == chars.length){
            res.add(builder.toString());
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;
            builder.append(chars[i]);
            visited[i] = true;
            dfs(res, chars, visited, builder, level + 1);
            visited[i] = false;
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}

























