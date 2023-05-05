package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 10:26
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 缺失的区间
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 */
public class Pro163 {

    public static void main(String[] args) {
        Pro163 pro163 = new Pro163();
        int[] nums = {0, 1, 3, 50, 75};
        System.out.println(pro163.findMissingRanges(nums, 0, 99));
    }

    /**
     * 1、双指针i，j，nums的元素在 [lower, upper],但 lower 和 upper 不一定为 nums[0] / nums[length - 1]
     * 2、移动双指针，判断 nums[i]-nums[j] = 1？，是： i++，j++；否：添加缺失字符串
     * 3、添加缺失字符串：nums[i] - nums[j] = 2 ? 是：缺失一个；否，缺失一组
     * 4、数组最后一个元素特殊处理：与upper比较
     * 5、注意考虑数组为空的情况
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> resList = new ArrayList<>();
        if (nums.length == 0){
            if (lower == upper) resList.add(lower +"");
            else resList.add(lower + "->" + upper);
            return resList;
        }
        if (nums[0] - lower == 1) resList.add(Integer.toString(lower));
        else if (nums[0] - lower > 1) resList.add(lower + "->" + (nums[0] - 1));
        for (int i = 0,j = 1; j < nums.length; i++,j++) {
            if (nums[j] - nums[i] == 2){
                resList.add((nums[j] - 1) + "");
            }else if (nums[j] - nums[i] > 2){
                resList.add((nums[i] + 1) + "->" + (nums[j] - 1));
            }
        }
        if (upper - nums[nums.length - 1] == 1) resList.add(upper + "");
        else if (upper - nums[nums.length - 1] > 1) resList.add((nums[nums.length - 1] + 1) + "->" + upper);
        return resList;
    }

    //预处理
    /*public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int N = nums.length;
        List<String> res = new ArrayList<>();
        int[] arrs = new int[N + 2];
        arrs[0] = lower - 1;
        arrs[N + 1] = upper + 1;
        System.arraycopy(nums, 0, arrs, 1, N);
        for (int i = 0; i < N + 2; i++) {
            if (i + 1 < N + 2 && arrs[i + 1] == arrs[i] + 1) continue;
            if (i + 1 < N + 2 && arrs[i + 1] == arrs[i] + 2) {
                res.add(String.valueOf(arrs[i] + 1));
            }
            if (i + 1 < N + 2 && arrs[i + 1] > arrs[i] + 2) {
                res.add(String.valueOf(arrs[i] + 1) + "->" + String.valueOf(arrs[i + 1] - 1));
            }
        }
        return res;
    }*/
}
