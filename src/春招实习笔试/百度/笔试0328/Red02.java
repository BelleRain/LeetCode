package 春招实习笔试.百度.笔试0328;

import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-03-28 19:56
 */

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247485057&idx=1&sn=bb188e81514cd76b6e3abc4b6a30644a&chksm=fc52b725cb253e33056b6e2814e0903f6d9c45e76aa2d737921fcbc1f5062b52d8e5d33bd691
 * 第二题：RED字符串
 * 给定一个整数x，请你构造一个仅由'r'、'e'、d'三种字符组成的字符串，其中回文子串的数量恰好为x。字符串的长度不得超过10^5。
 * 输入描述
 * 一个正整数x。
 * 1<=x<=10^9
 * 输出描述
 * 输出一个仅由'r','e','d' 三种字符组成的字符串。若有多解输出任意即可。
 * 示例1
 * 输入
 * 3
 * 输出
 * red
 */
public class Red02 {

    /**
     * 思路：d 包含1个回文子串，dd包含3个回文字串，ddd包含6个，dddd包含10个，依次累加求。
     * 比如： x = 14，则 14 - 10 - 3 - 1 = 0；依次构建 10个r，3个e，1个d
     * 因为 10 > 3 > 1,所以不会有其他多余的回文子串
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }

}
