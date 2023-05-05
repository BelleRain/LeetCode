package 春招实习笔试.美团.笔试0318;

/**
 * @author mxy
 * @create 2023-03-21 11:21
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://mp.weixin.qq.com/s/WLUqD-ov9M_bXXaipfec6g
 * 第二题：最长的彩带
 * 题目描述
 * 小美现在有一串彩带，假定每一厘米的彩带上都是一种色彩。
 * 因为任务的需要，小美希望从彩带上截取一段，使得彩带中的颜色数量不超过K种。
 * 显然，这样的截取方法可能非常多。于是小美决定尽量长地截取一段。
 * 你的任务是帮助小美截取尽量长的一段，使得这段彩带上不同的色彩数量不超过K种。
 * 输入描述
 * 第一行两个整数N,K，以空格分开，分别表示彩带有N厘米长，你截取的一段连续的彩带不能超过K种颜色。
 * 接下来一行N个整数，每个整数表示一种色彩，相同的整数表示相同的色彩。
 * 1≤N,K≤5000，彩带上的颜色数字介于[1, 2000]之间。
 * 输出描述
 * 一行，一个整数，表示选取的彩带的最大长度。
 * 示例1
 * 输入
 * 8 3
 * 1 2 3 2 1 4 5 1
 * 输出
 * 5
 */
public class Ribbon02 {

    /**
     * 经典的滑动窗口的思路。当窗口内的颜色的数量超过K的时候，滑动左指针。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] color = new int[N];
        while (scanner.hasNextInt()){
            for (int i = 0; i < N; i++) {
                color[i] = scanner.nextInt();
            }
        }
        System.out.println(longestRibbon(color,K));
    }

    private static int longestRibbon(int[] color, int k) {
        int res = 0;
        int l = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < color.length; r++) {
            map.put(color[r], map.getOrDefault(color[r], 0) + 1);
            while (l < r && map.size() > k){
                map.put(color[l], map.get(color[l]) - 1);
                if (map.get(color[l]) == 0){
                    map.remove(color[l]);
                }
                l++;
            }
            //System.out.println(r - l + 1);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    /*
    N, K = map(int, input().split(" "))
colors = [int(c) for c in input().split(" ")]

res = 0
cnts = {}

l = 0
for r in range(N):
    if colors[r] in cnts: cnts[colors[r]] += 1
    else: cnts[colors[r]] = 1
    while l < r and len(cnts) > K:
        cnts[colors[l]] -= 1
        if cnts[colors[l]] == 0: del cnts[colors[l]]
        l += 1
    res = max(res, r - l + 1)
print(res)
     */

}
