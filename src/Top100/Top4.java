package Top100;

/**
 * @author mxy
 * @create 2022-11-14 14:57
 */

import java.util.Arrays;

/**
 * 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class Top4 {

    /**
     * 该题具有多种题解，此处只写一个较优解，
     * 该题链接：https://leetcode.cn/problems/median-of-two-sorted-arrays/?favorite=2cktkvj
     * @param args
     */

    public static void main(String[] args) {
        Top4 top4 = new Top4();
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        //System.out.println(Arrays.toString(top4.merge(nums1, nums2)));
        System.out.println(top4.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }


    /**
     * 1、合并有序数组 并 求出中位数 ，合并部分类似 归并排序的合并过程
     * 时间复杂度为 O(m+n),虽调试通过，但复杂度不合题意
     * @param nums1
     * @param nums2
     * @return
     */
    /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] temp = merge(nums1, nums2);
        if ((temp.length & 1) == 1) return (double) temp[temp.length / 2]; //奇数个
        else return (double) (temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2;
    }

    //合并过程
    public int[] merge(int[] nums1,int[] nums2){
        if (nums1.length == 0 && nums2.length == 0) return new int[0];
        if (nums1.length == 0) return nums2;
        if (nums2.length == 0) return nums1;
        int[] temp = new int[nums1.length + nums2.length];
        int i = 0 , j = 0, t = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] <= nums2[j]) temp[t++] = nums1[i++];
            else temp[t++] = nums2[j++];
        }
        while (i < nums1.length) temp[t++] = nums1[i++];
        while (j < nums2.length) temp[t++] = nums2[j++];
        return temp;
    }*/
}
