package Swordoffer.String;

/**
 * @author mxy
 * @create 2022-09-17 20:37
 */

/**
 * 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class Offer05 {

    public static void main(String[] args) {
        Offer05 offer05 = new Offer05();
        System.out.println(offer05.replaceSpace("We are happy."));
    }

    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        //创建字符数组
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //System.out.println(charArray[i]);
            if (charArray[i] == ' '){
               res.append("%20");
            }else {
                res.append(charArray[i]);
            }

        }
        return res.toString();
        //return s.replaceAll(" ","%20"); //尽量不要直接使用库函数
    }


    /**
     * 该方法注意：String newStr = new String(array, 0, size);
     */
    //public String replaceSpace(String s) {
    //    int length = s.length();
    //    char[] array = new char[length * 3];
    //    int size = 0;
    //    for (int i = 0; i < length; i++) {
    //        char c = s.charAt(i);
    //        if (c == ' ') {
    //            array[size++] = '%';
    //            array[size++] = '2';
    //            array[size++] = '0';
    //        } else {
    //            array[size++] = c;
    //        }
    //    }
    //    String newStr = new String(array, 0, size);
    //    return newStr;
    //}
}
