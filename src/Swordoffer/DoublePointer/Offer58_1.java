package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-28 10:08
 */

/**
 * 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class Offer58_1 {

    public static void main(String[] args) {

        Offer58_1 offer = new Offer58_1();
        String res = offer.reverseWords("a good   example");
        System.out.println(res);

        //String s = "   hello   world!   ";
        //s = s.trim();
        //System.out.println(s);
        //String[] str = s.split(" ");
        //System.out.println(Arrays.toString(str)); //[hello, , , world!, ,  ] 空白处为 ""
        ////for (String s1 : str) {
        ////    s1.trim();
        ////}
        //System.out.println(str[0]);
        //System.out.println(str[0].trim());
        //System.out.println(Arrays.toString(str));
        ////System.out.println(Arrays.toString(str[0].trim().toCharArray()));
        ////System.out.println("hello");
    }

    /**
     * 解法一：字符串分割 + 倒序拼接
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 82.96% 的用户
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == " ") return s;
        s = s.trim();
        String[] str = s.split(" ");
        //System.out.println(str.length);
        StringBuilder res = new StringBuilder();
        for (int i = str.length-1; i >= 0; i--) {
            //str[i] = str[i].trim(); //去除每个单词的首位空格，这一步可以去掉
            if (!str[i].equals("")){
                res.append(str[i]);
                res.append(" ");
            }
        }
        return res.toString().trim();
    }

    /**
     * 解法二：双指针
     */
    //public String reverseWords(String s) {
    //    s = s.trim(); // 删除首尾空格
    //    int j = s.length() - 1, i = j;
    //    StringBuilder res = new StringBuilder();
    //    while(i >= 0) {
    //        while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
    //        res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
    //        while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
    //        j = i; // j 指向下个单词的尾字符
    //    }
    //    return res.toString().trim(); // 转化为字符串并返回
    //}

    /**
     * 解法三：
     *    使用 split 将字符串按空格分割成字符串数组；
     *    使用 reverse 将字符串数组进行反转；
     *    使用 join 方法将字符串数组拼成一个字符串。
     */
    //public String reverseWords(String s) {
    //    // 除去开头和末尾的空白字符
    //    s = s.trim();
    //    // 正则匹配连续的空白字符作为分隔符分割
    //    List<String> wordList = Arrays.asList(s.split("\\s+"));
    //    Collections.reverse(wordList);
    //    return String.join(" ", wordList);
    //}

}









































