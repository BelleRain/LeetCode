package Swordoffer.Sort;

/**
 * @author mxy
 * @create 2022-10-01 14:58
 */

/**
 * 把数组排成最小的数
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
 * 提示:
 *   0 < nums.length <= 100
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class Offer45 {
    public static void main(String[] args) {
        Offer45 offer = new Offer45();
        int[] nums = new int[]{2,100,10};
        String res = offer.minNumber(nums);
        System.out.println(res);
    }

    /**
     * 原文链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/
     */

    /**
     * 解题思路：
     *  1、此题求拼接起来的最小数字，本质上是一个排序问题。设数组 nums 中任意两数字的字符串为 x 和 y ，则规定 排序判断规则 为：
     *   （1） 若拼接字符串 x + y > y + x ，则 x “大于” y ；（x 位于 y的右边 -> yx）
     *   （2）反之，若 x + y < y + x ，则 x “小于” y ；（x 位于 y的左边 ->xy）
     *   （3）x “小于” y 代表：排序完成后，数组中 x 应在 y 左边；“大于” 则反之。
     *    根据以上规则，套用任何排序方法对 nums 执行排序即可。
     * 2、算法流程：
     *    1、初始化： 字符串列表 strs ，保存各数字的字符串格式；
     *    2、列表排序： 应用以上 “排序判断规则” ，对 strs 执行排序；
     *    3、返回值： 拼接 strs 中的所有字符串，并返回。
     * 3、复杂度分析：
     *     时间复杂度 O(NlogN) ： N 为最终返回值的字符数量（ strs 列表的长度 ≤N ）；使用快排或内置函数的平均时间复杂度为 O(NlogN) ，最差为 O(N^2)。
     *     空间复杂度 O(N) ： 字符串列表 strs 占用线性大小的额外空间。
     */

    /**
     * 方法一：快速排序
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    public void quickSort(String[] strs,int l,int r){
        if (l >= r) return;
        int i = l,j = r;
        String tmp = strs[i];
        while (i < j){
            while ((strs[j] + strs[l]).compareTo((strs[l] + strs[j])) >= 0 && i < j) j--;
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l,i-1 );
        quickSort(strs, i+1, r);
    }

    /**
     * 方法二：内置函数
     * @param nums
     * @return
     */
    //public String minNumber(int[] nums) {
    //    String[] strs = new String[nums.length];
    //    for (int i = 0; i < nums.length; i++) {
    //        strs[i] = String.valueOf(nums[i]);
    //    }
    //    Arrays.sort(strs,(x,y) -> (x+y).compareTo(y+x));
    //    StringBuilder res = new StringBuilder();
    //    for (String s : strs) {
    //        res.append(s);
    //    }
    //    return res.toString();
    //}

    /*
    字符串 xy < yx , yz < zy ，需证明 xz < zx 一定成立。
    设十进制数 x, y, z 分别有 a, b, c 位，则有：
    （左边是字符串拼接，右边是十进制数计算，两者等价）
    xy = x * 10^b + y
    yx = y * 10^a + x

    则 xy < yx 可转化为：
    x * 10^b + y < y * 10^a + x
    x (10^b - 1) < y (10^a - 1)
    x / (10^a - 1) < y / (10^b - 1)     ①

    同理， 可将 yz < zy 转化为：
    y / (10^b - 1) < z / (10^c - 1)     ②

    将 ① ② 合并，整理得：
    x / (10^a - 1) < y / (10^b - 1) < z / (10^c - 1)
    x / (10^a - 1) < z / (10^c - 1)
    x (10^c - 1) < z (10^a - 1)
    x * 10^c + z < z * 10^a + x
    ∴  可推出 xz < zx ，传递性证毕
     */
}



































