package 春招实习笔试.腾讯音乐.笔试0323;

/**
 * @author mxy
 * @create 2023-03-24 9:19
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 链接：https://mp.weixin.qq.com/s/gBy28P-8DNEb0DtdGn6gEw
 * 第二题：小红的字符串切分
 * 小红定义一个字符串的权值为:字符串长度乘以字符串的字母种类数量。例如,"abacb"的价值为5*3=15。
 * 小红拿到了一个字符串，她准备将该字符串切分成k个子串(将这k个子串按顺序拼在一起即可得到原串）。
 * 小红希望切分后这k个子串的最大权值尽可能小。你能帮帮小红吗?你不需要给出一个方案，只需要返回最终这k个子串的最大权值即可。
 * 字符串仅包含小写字母，且长度不超过500000。k为不超过字符串长度的正整数。
 * 示例
 * 输入
 * "ababbbb",2
 * 输出
 * 6
 */
public class StringSegment02 {

    public static void main(String[] args) {
        StringSegment02 seg = new StringSegment02();
        System.out.println(seg.getMaxValue("ababbbb", 2));
    }

    //二分答案
    public int getMaxValue(String str,int k){
        int l = 0;
        int r = 26 * 500000; //最大权值 = 26种字母 * 最大长度(500000)
        while (l < r){
            int mid = (l + r)/2;
            if (check(str, k, mid)){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return r;
    }

    public boolean check(String str, int k,int x){
        int cnt = 1;
        int st = 0, ed = 0;
        Set<Character> set = new HashSet<>();
        while (ed < str.length()){
            if (set.contains(str.charAt(ed))){
                if (set.size() * (ed - st + 1) <= x){
                    set.add(str.charAt(ed));
                    ed = ed + 1;
                }else {
                    st = ed;
                    set.clear();
                    cnt = cnt + 1;
                }
            }else {
                if ((set.size() + 1) * (ed - st + 1) <= x){
                    set.add(str.charAt(ed));
                    ed = ed + 1;
                }else {
                    st = ed;
                    set.clear();
                    cnt = cnt + 1;
                }
            }
        }
        return cnt <= k;
    }
}
