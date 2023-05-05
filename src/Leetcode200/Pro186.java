package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 15:04
 */

import com.sun.javafx.runtime.async.AsyncOperationListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 反转字符串中的单词 II
 * 给你一个字符数组 s ，反转其中 单词 的顺序。
 * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
 * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
 *
 * 示例 1：
 * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 *
 * 示例 2：
 * 输入：s = ["a"]
 * 输出：["a"]
 *  
 *
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 可以是一个英文字母（大写或小写）、数字、或是空格 ' ' 。
 * s 中至少存在一个单词
 * s 不含前导或尾随空格
 * 题目数据保证：s 中的每个单词都由单个空格分隔
 */
public class Pro186 {

    public static void main(String[] args) {
        Pro186 pro186 = new Pro186();
        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        //char[] s = {'a'};
        pro186.reverseWords(s);
    }

    //效率低
    //public void reverseWords(char[] s) {
    //    StringBuilder builder = new StringBuilder();
    //    if (s.length >= 2){
    //        for (int i = 0; i < s.length; i++) {
    //            builder.append(s[i]);
    //        }
    //        String s1 = builder.toString().trim();
    //        //System.out.println(s1);
    //        List<String> list = Arrays.asList(s1.split("\\s+"));
    //        //System.out.println(list.toString());
    //        Collections.reverse(list);
    //        //System.out.println(list.toString());
    //        String s2 = String.join(" ", list);
    //        char[] s0 = s2.toCharArray();
    //        //System.out.println(s0.length);
    //        for (int i = 0; i < s.length; i++) {
    //            s[i] = s0[i];
    //        }
    //        //System.out.println(Arrays.toString(s));
    //    }
    //}

    /**
     * 先翻转整个字符数组，然后再逐个翻转每个单词，调式即可得解题思路
     * @param s
     */
    public void reverseWords(char[] s) {
        int length = s.length;
        reverseRange(s, 0, length - 1);
        int start = 0;
        for (int i = 0; i < length; i++) {
            if (s[i] == ' ') {
                int end = i - 1;
                reverseRange(s, start, end);
                start = i + 1;
            }
        }
        //到达最后一个单词时，start指向最后一个单词的起始位置，i = length 时，已跳出循环，没有进行最后一个单词的翻转
        //故最后要单独进行
        reverseRange(s, start, length - 1);
    }

    public void reverseRange(char[] s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

}
