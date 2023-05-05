package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-31 18:57
 */

import java.util.*;

/**
 * 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * 限制：
 * 1 <= s 的长度 <= 8
 */
public class Offer38 {

    public static void main(String[] args) {
        Offer38 offer = new Offer38();
        String s = "abb";
        System.out.println(Arrays.toString(offer.permutation(s)));
        System.out.println(Arrays.toString(offer.permutation1(s)));
        System.out.println(Arrays.toString(offer.permutation2(s)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
     * 方法一：回溯法 （此题debug较容易理解）
     * 复杂度分析：
     * 时间复杂度 O(N!N) ： N 为字符串 s 的长度；时间复杂度和字符串排列的方案数成线性关系，方案数为 N×(N−1)×(N−2)…×2×1 ，即复杂度为 O(N!)；
     * 字符串拼接操作 join() 使用 O(N) ；因此总体时间复杂度为 O(N!N) 。
     * 空间复杂度 O(N^2)： 全排列的递归深度为 N ，系统累计使用栈空间大小为 O(N) ；递归中辅助 Set 累计存储的字符数量最多为 N + (N-1) + ... + 2 + 1 = (N+1)N/2 ，即占用 O(N^2)的额外空间。
     * @param s
     * @return
     */
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        //从第一层开始递归
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x){ //x:当前固定位
        //终止条件：当 x == c.length - 1时，表示所有位已经固定（最后一位只有一种情况），则将当前组合c转化为字符串并加入res，并返回；
        //以数组长度为3为例，当递归函数到达第三层，就返回，因为此时第二第三个位置已经发生了交换
        if (x == c.length - 1){
            res.add(String.valueOf(c)); //添加排列方案
            return;
        }
        //set 用于排除重复的字符
        HashSet<Character> set = new HashSet<>();
        // 当第二层只有两种情况，dfs(1）i = 1开始
        for (int i = x; i < c.length; i++) {
            //类似abb，有重复元素，则重复元素剪枝
            if (set.contains(c[i])) continue;  //重复，因此剪枝
            set.add(c[i]);
            //交换元素，这里很是巧妙，当在第二层dfs(1),x = 1,那么i = 1或者 2， 不是交换1和1，要就是交换1和2
            swap(i, x); //交换，将c[i]固定在第x位
            dfs(x+1);//开启固定第x+1位字符
            //返回时交换回来，这样保证到达第1层的时候，一直都是abc。这里捋顺一下，开始一直都是abc，那么第一位置总共就3个交换
            //分别是a与a交换，这个就相当于 x = 0, i = 0;
            //     a与b交换            x = 0, i = 1;
            //     a与c交换            x = 0, i = 2;
            //就相当于上图中开始的三条路径
            //第一个元素固定后，每个引出两条路径,
            //     b与b交换            x = 1, i = 1;
            //     b与c交换            x = 1, i = 2;
            //所以，结合上图（见原文链接），在每条路径上标注上i的值，就会非常容易好理解了
            swap(i, x); //恢复交换，保证第一层为 abc，整个大循环结束才会返回上一层
        }
    }
    void swap(int a,int b){
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-hhvs/
     */
    /**
     * 方法一：回溯
     * 复杂度分析
     *      时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。这些字符的全部排列有 O(n!) 个，每个排列平均需要 O(n) 的时间来生成。
     *      空间复杂度：O(n)。我们需要 O(n) 的栈空间进行回溯，注意返回值不计入空间复杂度。
     */
    List<String> rec;
    boolean[] vis;  //标记已经填过的字符

    public String[] permutation1(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    /**
     *
     * @param arr 待排数组
     * @param i 下一个待填入的空位是第i个空位（从0开始）
     * @param n 共有多少个空位
     * @param perm  当前排列
     */
    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) { //已经填完了n个空位
            rec.add(perm.toString());
            return;
        }
        //在填入第i个字符时，遍历所有的给定字符
        for (int j = 0; j < n; j++) {
            //去重操作：
            //该字符被访问过
            //该字符的与前一个字符相等，且前一个字符未被访问过，保证对于重复的字符，一定是从左至右依次填入空位
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            //如果该字符没有被标记过v[j] = false,则尝试填入，并进行标记
            //继续尝试下一个空位，即调用函数backtrack(i+1,perm)
            //回溯时，需要撤销该空位填的字符以及对该字符的标记，并继续向当前空位尝试填入其他没被标记过的字符。
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    /**
     * 方法二：下一个排列
     * 复杂度分析
     *      时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。我们需要 O(nlogn) 的时间得到第一个排列，
     *      nextPermutation 函数的时间复杂度为 O(n)，我们至多执行该函数 O(n!) 次，因此总时间复杂度为 O(n×n!+nlogn)=O(n×n!)。
     *      空间复杂度：O(1)。注意返回值不计入空间复杂度。
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        List<String> ret = new ArrayList<String>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    public boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1);
        return true;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }


    /*public static String[] permutation(String s) {
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
    }*/
}














