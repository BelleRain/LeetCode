package 春招实习笔试.百度.笔试0328;

import java.util.*;

/**
 * @author mxy
 * @create 2023-03-28 18:45
 */

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247485057&idx=1&sn=bb188e81514cd76b6e3abc4b6a30644a&chksm=fc52b725cb253e33056b6e2814e0903f6d9c45e76aa2d737921fcbc1f5062b52d8e5d33bd691&cur_album_id=2828382526552391682&scene=190#rd
 * 第一题：百度字符串
 *
 * 小红拿到了一个字符串,她想知道这个字符串能否通过重新排列组成"Baidu"字符串?
 *
 * 注:必须大小写完全相同。共有t组询问。
 * 输入描述
 * 第一行输入一个正整数t，代表询问次数。
 * 接下来的t行，每一行输入一个仅包含英文字母的字符串。
 * 所有字符串的长度之和保证不超过200000。
 * 输出描述
 * 成功则输出YES，否则输出NO
 * 示例1
 * 输入
 * 4
 * Baidu
 * baidu
 * Baidu
 * bbdu
 * 输出
 * YES
 * NO
 * YES
 * NO
 */
public class BaiduString01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String[] str = new String[N+1];
        for (int i = 0; i < N+1; i++) {
            str[i] = scanner.nextLine();
        }

        String string = "Baidu";
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <string.length(); i++) {
            set.add(string.charAt(i));
        }

        for (int i = 1; i < N+1; i++) {
            if (str[i].length() != 5){
                System.out.println("No");
                continue;
            }
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < str[i].length(); j++) {
                list.add(str[i].charAt(j));
            }
            if (list.containsAll(set)){
                System.out.println("Yes");
                continue;
            }else {
                System.out.println("No");
                continue;
            }
        }
    }
}
