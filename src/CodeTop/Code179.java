package CodeTop;

/**
 * @author mxy
 * @create 2023-05-24 15:12
 */

import java.util.Arrays;

/**
 * 179. 最大数    链接：https://leetcode.cn/problems/largest-number
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 */
public class Code179 {

    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }

    /**
     * 方法一：内置函数
     * 同 Offer45 （把数组排成最小的数）
     * @param nums
     * @return
     */
    public static String largestNumber1(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str,(x,y) -> (y + x).compareTo(x + y));
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }

        String res = builder.toString();
        int k = -1;
        for (int i = 0; i < res.length() - 1; i++) {
            if (res.charAt(i) == '0') k = i;
            else break;
        }
        res = res.substring(k + 1);
        return res;
    }


    /**
     * 快速排序：
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] str = new String[nums.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        quickSort(str,0,str.length - 1);
        //System.out.println(Arrays.toString(str));
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }
        String res = builder.toString();
        int k = -1;
        for (int i = 0; i < res.length() - 1; i++) {
            if (res.charAt(i) == '0') k = i;
            else break;
        }
        res = res.substring(k + 1);
        return res;
    }

    private static void quickSort(String[] str, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = str[l];
        while (i < j){
            while ((str[j] + str[l]).compareTo(str[l] + str[j]) <= 0 && i < j) j--;
            while ((str[i] + str[l]).compareTo(str[l] + str[i]) >= 0 && i < j) i++;
            String temp = str[j];
            str[j] = str[i];
            str[i] = temp;
        }
        str[l] = str[i];
        str[i] = tmp;
        quickSort(str, l, i - 1);
        quickSort(str, i + 1, r);
    }
}



































