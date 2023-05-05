package CodeTop;

/**
 * @author mxy
 * @create 2023-04-28 16:35
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 394. 字符串解码     链接：https://leetcode.cn/problems/decode-string
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *  
 * 提示：
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300] 
 *
 */
public class Code394 {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
     */


    /**
     * 解法一：辅助栈法
     * 算法流程：
     * 1、构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
     *      当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
     *      当 c 为字母时，在 res 尾部添加 c；
     *      当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 0：
     *          记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
     *          记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
     *          进入到新 [ 后，res 和 multi 重新记录。
     *      当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res，其中:
     *          last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
     *          cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2。
     * 2、返回字符串 res。
     * 复杂度分析：
     *   时间复杂度 O(N)，一次遍历 s；
     *   空间复杂度 O(N)，辅助栈在极端情况下需要线性空间，例如 2[2[2[a]]]。
     * @param s
     * @return
     */
    public static String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '['){
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if (c == ']'){
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            //此种写法不合适，题中未说明 倍数 只在 0 - 9 之间，例如 "100[leetcode]" 则报错
            //else if (c >= '0' && c <= '9') multi = c - '0';
            else res.append(c);
        }
        return res.toString();
    }


    /**
     * 解法二：递归法
     * 1、总体思路与辅助栈法一致，不同点在于将 [ 和 ] 分别作为递归的开启与终止条件：
     *      当 s[i] == ']' 时，返回当前括号内记录的 res 字符串与 ] 的索引 i （更新上层递归指针位置）；
     *      当 s[i] == '[' 时，开启新一层递归，记录此 [...] 内字符串 tmp 和递归后的最新索引 i，并执行 res + multi * tmp 拼接字符串。
     * 2、遍历完毕后返回 res。
     * 复杂度分析：
     *      时间复杂度 O(N)，递归会更新索引，因此实际上还是一次遍历 s；
     *      空间复杂度 O(N)，极端情况下递归深度将会达到线性级别。
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        return dfs(s,0)[0];
    }

    private static String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()){
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            else if (s.charAt(i) == '['){
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (multi > 0){
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if (s.charAt(i) == ']'){
                return new String[]{String.valueOf(i),res.toString()};
            }
            else {
                res.append(String.valueOf(s.charAt(i)));
            }
            i++;
        }
        return new String[]{res.toString()};
    }

}


































