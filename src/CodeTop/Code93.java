package CodeTop;

/**
 * @author mxy
 * @create 2023-04-21 15:34
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 93. 复原 IP 地址     链接：https://leetcode.cn/problems/restore-ip-addresses
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
 * 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *  
 * 提示：
 * 1 <= s.length <= 20
 * s 仅由数字组成
 *
 */
public class Code93 {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("101023"));
        System.out.println(restoreIpAddresses1("101023"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/restore-ip-addresses/solution/hui-su-suan-fa-hua-tu-fen-xi-jian-zhi-tiao-jian-by/
     * 回溯： 横向遍历，纵向递归
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len > 12 || len < 4){
            return res;
        }
        Deque<String> path = new ArrayDeque<>(4);
        dfs(s,len,0,4,path,res);
        return res;
    }

    /**
     *
     * @param s     原字符串
     * @param len   原字符串的长度
     * @param begin 截取 ip 段的起始长度
     * @param residue  层数
     * @param path  记录根结点到叶子节点的路径
     * @param res   结果集
     */
    private static void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        if (begin == len){
            if (residue == 0){
                res.add(String.join(".",path));
            }
            return;
        }
        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) break;
            if (residue * 3 < len - i){ //对于当前分支，剩余的字符串划分residue段之后还有剩余，证明截取长度短
                continue;
            }
            if (judgeIpSegment(s, begin, i)){
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);
                dfs(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    private static boolean judgeIpSegment(String s,int left,int right){
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0'){
            return false;
        }
        int res = 0;
        while (left <= right){
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }



    public static List<String> restoreIpAddresses1(String s) {

        ArrayList<String> strings = new ArrayList<>();

        getIp(strings, new StringBuilder(), 1, s, 0);
        return strings;
    }

    public static void getIp(ArrayList<String> strings, StringBuilder stringBuilder, int i, String s, int indexOfString) {
        //indexOfString指向的是这一次的待添加位置
        if (i == 5 && indexOfString == s.length()) {
            StringBuilder stringBuilder1 = stringBuilder;
            String s1 = new String(stringBuilder1);
            strings.add(s1);
    }
        if (i <= 4) {
            //每次截取的长度为j
            for (int j = 1; j < 4; j++) {
                //没超过边界，则进入循环
                if (indexOfString + j <= s.length()) {
                    String string = s.substring(indexOfString, indexOfString + j);
                    int number = Integer.parseInt(string);
                    //截取的字符串符合要求，则进数组，进循环
                    if (number >= 0 && number <= 255 && (j == 1 || !(string.startsWith("0")))) {
                        stringBuilder.append(string);
                        if (i < 4) {
                            stringBuilder.append('.');
                        }
                        getIp(strings, stringBuilder, i + 1, s, indexOfString + j);
                        //因为前面有i-1个·，所以添加是位置要往后挪i-1位
                        //删除时候末尾位置稍微超出也可以，不会报错
                        stringBuilder.delete(indexOfString + i - 1, indexOfString + j + 1 + i - 1);
                    }
                }
            }
        }
    }
}

























