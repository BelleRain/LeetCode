package 春招实习笔试.美团.笔试0318;

/**
 * @author mxy
 * @create 2023-03-23 14:01
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 链接：https://mp.weixin.qq.com/s/WLUqD-ov9M_bXXaipfec6g
 * 第三题：构建回文串
 * 题目描述
 * 现在小美获得了一个字符串。小美想要使得这个字符串是回文串。
 * 小美找到了你。你可以将字符串中 至多两个位置 改为任意小写英文字符’a’-‘z’
 * 你的任务是帮助小美在当前制约下，获得字典序最小的回文字符串。
 * 【 数据保证能在题目限制下形成回文字符串。】
 * 注：回文字符串：即一个字符串从前向后和从后向前是完全一致的字符串。
 * 例如字符串abcba, aaaa, acca都是回文字符串。字符串abcd, acea都不是回文字符串。
 * 输入描述
 * 一行，一个字符串。字符串中仅由小写英文字符构成。
 * 保证字符串不会是空字符串。
 * 字符串长度介于 [1, 100000] 之间。
 * 输出描述
 * 一行，一个在题目条件限制下所可以获得的字典序最小的回文字符串。
 * 示例1
 * 输入
 * acca
 * 输出
 * aaaa
 */
public class BuildPalindrome03 {

    /**
     * 思路与代码:
     * 【此代码未进行大量数据的测试，仅供参考，有疑问欢迎讨论】
     * 这个题首先保证题目是可解的，因此最多只需要修改2次即可得到回文串。
     * 那么我们需要考虑以下几种情况：
     * 1.本身就是回文串，那么我们要做的就是找到第一个不是a的字符，把对应位置都改为a，这样可以使得字典序最小。
     * 2.本身不是回文串，我们需要找到不对称的点，如果找到的不对称的点均不是a，那就都改成a就好了；
     *   如果其中一个是a，那么要需要此处只需要消耗一次机会，把非a的改为a即可，接下来在执行一次该操作。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = s.length();
        int cnt = 2;
        char[] str = s.toCharArray();

        //注意：【 数据保证能在题目限制下形成回文字符串。】 即最多修改两次
        for (int i = 0; i < n / 2; i++) {
            //[i,...,n-1-i]
            //如果对称位置上的字符不相同，则进行修改；若相同，则不需要修改；
            //注意，最多修改两次
            if (str[i] != str[n - 1 -i]){
                //如果两位置上的都不为 'a'
                if (str[i] != 'a' && str[n - 1 - i] != 'a'){
                    // 在 cnt == 2 的条件下，一次修改两个字符
                    if (cnt == 2){
                        str[i] = str[n - 1 -i] = 'a';
                        cnt = cnt - 2;
                        break;
                    }
                    //否则将 两位置 统一改成 其中一个较小的字符，修改一次之后退出循环
                    else if (cnt == 1){
                        char c = str[i] < str[n - 1 -i] ? str[i] : str[n - 1 -i];
                        str[i] = str[n - 1 -i] = c;
                        cnt = cnt - 1;
                        break;
                    }
                }
                //如果两位置上只有一个 a，则将一次将其中的一个改为 a
                else {
                    if (cnt > 0){
                        if (str[i] == 'a' || str[n - 1 -i] == 'a'){
                            str[i] = str[n - 1 - i] = 'a';
                            cnt = cnt - 1;
                        }
                    }
                }
            }
        }

        //当前字符串已经是回文串
        //经过0次修改
        if (cnt == 2){
            for (int i = 0; i < n / 2; i++) {
                if (str[i] != 'a'){
                    str[i] = str[n - 1 - i] = 'a';
                    cnt = cnt - 2;
                }
            }
        }

        //只有一次修改机会
        //在长度为偶数的情况下，修改一次后则不是回文串了
        if (cnt == 1){
            //只有字符串的长度是奇数的时候有必要把中间点改为 a
            if (n % 2 != 0){
                str[n/2] = 'a';
            }
        }
        //字符数组转成字符串
        String res = String.valueOf(str);
        System.out.println(res);
    }



}
































