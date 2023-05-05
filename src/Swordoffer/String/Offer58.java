package Swordoffer.String;

/**
 * @author mxy
 * @create 2022-09-17 21:39
 */

/**
 * 左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 */
public class Offer58 {

    public static void main(String[] args) {
        Offer58 offer58 = new Offer58();
        offer58.reverseLeftWords("abcdefg",2);
    }

    /**
     * 解法一： 分离 再 拼接
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 59.58% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 72.37% 的用户
     * @param s
     * @param n
     * @return
     */
    //public String reverseLeftWords(String s, int n) {
    //    //前n个字符串
    //    String s1 = s.substring(0, n);
    //    //后n个字符串
    //    String s2 = s.substring(n);
    //    StringBuilder builder = new StringBuilder();
    //    builder.append(s2);
    //    builder.append(s1);
    //    System.out.println(builder.toString());
    //    return builder.toString();
    //}

    /**
     * 解法二：字符串可以直接拼接
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 38.11% 的用户
     * @param s
     * @param n
     * @return
     */
    //public String reverseLeftWords(String s, int n) {
    //    String a=null;
    //    a=s.substring(n) + s.substring(0,n);
    //    return a;
    //}


    /**
     * 以下这两种方法运行速度慢，但注意其中的 取余 方法,取余用来简化代码
     *
     *         StringBuilder res = new StringBuilder();
     *         for(int i = n; i < s.length(); i++)
     *             res.append(s.charAt(i));
     *         for(int i = 0; i < n; i++)
     *             res.append(s.charAt(i));
     *         return res.toString();
     *
     * 取余：i = n,...,s.length-1;  i % s.length = n,...,s.length-1
     *      i = s.length,s.length+1,....,s.length + n-1; i % s.length = 0,1,2,....,n-1
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        for(int i = n; i < n + s.length(); i++)
            res.append(s.charAt(i % s.length()));
        return res.toString();
    }

    //该方法空间使用量大，字符串为“不可变对象”，每轮都要新建对象，因此效率低下
    //public String reverseLeftWords(String s, int n) {
    //    String res = "";
    //    for(int i = n; i < n + s.length(); i++)
    //        res += s.charAt(i % s.length());
    //    return res;
    //}
}
