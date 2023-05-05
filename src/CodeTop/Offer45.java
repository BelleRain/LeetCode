package CodeTop;

/**
 * @author mxy
 * @create 2023-04-09 16:16
 */

import java.util.Arrays;

/**
 * 面试题45. 把数组排成最小的数   链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 *
 * 提示:
 * 0 < nums.length <= 100
 *
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 */
public class Offer45 {

    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(minNumber1(nums));
        System.out.println(minNumber2(nums));
    }


    /**
     * 排序规则：
     * 设
     * x + y > y + x 作 x > y  取 yx
     * x + y < y + x 作 x < y  取 xy
     * x “小于” y 代表：排序完成后，数组中 x 应在 y 左边；“大于” 则反之。
     */


    /**
     * 方法一：快速排序
     * @param nums
     * @return
     */
    public static String minNumber1(int[] nums) {
        if (nums.length == 0) return "";
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        quicksort(0,str.length - 1,str);
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }
        return builder.toString();
    }

    private static void quicksort(int l, int r, String[] str) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = str[i];
        while (i < j){
            while ((str[j] + str[l]).compareTo(str[l] + str[j]) >= 0 && i < j) j--;
            while ((str[i] + str[l]).compareTo(str[l] + str[i]) <= 0 && i < j) i++;
            tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
        str[i] = str[l];
        str[l] = tmp;
        quicksort(l, i - 1, str);
        quicksort(i + 1, r, str);
    }


    /**
     * 方法二：内置函数
     * @param nums
     * @return
     */
    public static String minNumber2(int[] nums) {
        if (nums.length == 0) return "";
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str,(x,y) -> (x+y).compareTo(y+x));
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }
        return builder.toString();
    }

}
