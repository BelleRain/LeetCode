package CodeTop;

/**
 * @author mxy
 * @create 2023-04-26 8:34
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. 反转字符串中的单词   链接：https://leetcode.cn/problems/reverse-words-in-a-string
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *  
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
 *
 */
public class Code151 {

    public static void main(String[] args) {
        String res = reverseWords1("  hello world  ");
        System.out.println(res);
        System.out.println(res.length());
        String res1 = reverseWords("  hello world  ");
        System.out.println(res1);
        System.out.println(res1.length());
    }


    /**
     * 最后一位是不间断空格：
     * ASCII编码为160的空格，其实是不间断空格(non-breaking space)，就是页面上的 :所产生的空格。
     * 不间断空格 non-breaking space的缩写正是nbsp;。这种空格的作用就是在页面换行时不被打断。
     *
     * 不间断空格无法被trim()所裁剪，也无法被正则表达式的\s所匹配，也无法被Stringutils.isBlank()所识别，无法像裁剪寻常空格那样移除这个不间断空格。
     * 我们可以利用不间断空格的 Unicode 编码来移除它，其编码为\u00A0，正常空格编码为\u0020。
     *
     * String str = "a\u00A0b\u00A0c";
     * str = str.replaceAll("\u00A0", "\u0020");
     * // 或者
     * str = str.replaceAll("\\u00A0+", "\u0020"); // 正则表达式写法
     *
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        //反转整个字符串
        //删除多余空格
        //反转每个单词
        char[] str=s.toCharArray();
        char[] reverse=new char[str.length+1];
        int i=str.length-1,r,l,p=0;
        while(i>=0){
            while(i>=0&&str[i]==' '){
                i--;
            }
            r=i;
            while(i>=0&& str[i]!=' '){
                i--;
            }
            l=i;
            if(r>l){
                for(int j=l+1;j<=r;j++){
                    reverse[p++]=str[j];
                }
                reverse[p++]=' ';
            }
        }
        return new String(reverse,0,p-1);
    }



    public static String reverseWords1(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配 连续的空白字符 作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}






































