package Swordoffer.SearchAlgorithms;

/**
 * @author mxy
 * @create 2022-09-17 22:29
 */

/**
 * 数组中重复的数字
 * 找出数组中重复的数字。   在一个长度为 n 的数组 nums 里的  所有数字都在 0～n-1 的范围内 。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 输入： [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Offer03 {

    public static void main(String[] args) {
        //int[] nums = {2, 3, 1, 0, 2, 5, 3};
        //ArrayIndexOutOfBoundsException 数组越界异常
        //因为 n 为数组长度，所有的数据都在 0~n-1之间，故不会出现数组越界的问题
        //int[] nums = {3,5,3};
        int[] nums = {2,1,2};
        Offer03 offer03 = new Offer03();
        System.out.println(offer03.findRepeatNumber(nums));
    }

    /**
     * 解法一：不考虑
     * 执行用时： 2306 ms , 在所有 Java 提交中击败了 5.09% 的用户
     * 内存消耗： 48.8 MB , 在所有 Java 提交中击败了 75.84% 的用户
     * @param nums
     * @return
     */
    //public int findRepeatNumber(int[] nums) {
    //    for (int i = 0; i < nums.length; i++) {
    //        for (int j = i+1; j < nums.length; j++) {
    //            if (nums[i] == nums[j]){
    //                return nums[i];
    //            }
    //        }
    //    }
    //    return -1;
    //}

    /**
     * 解法二：利用HashSet判重
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 33.06% 的用户
     * 内存消耗： 50 MB , 在所有 Java 提交中击败了 39.41% 的用户
     * @param nums
     * @return
     */
    //public int findRepeatNumber(int[] nums) {
    //    HashSet<Integer> set = new HashSet<>();
    //    for (int num : nums) {
    //        if (set.contains(num))
    //            return num;
    //        set.add(num);
    //    }
    //    return -1;
    //}

    /**
     * 解法三：原地交换
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 49 MB , 在所有 Java 提交中击败了 61.90% 的用户
     * 解题思路：
     * 1、长度为n的数组 nums 里的所有数字都在 0~n-1的范围内。
     * 表明：数组元素的 索引和值 一对多 的关系。
     * 2、因此，可遍历数组并通过交换操作，使元素的 索引与值 一一对应，
     * 因而，就能通过索引映射对应的值，起到与字典等价的作用
     *
     * 流程：1、遍历数组nums，设索引初始值 i=0
     *         若nums[i] = i,说明此数字已在对应位置，无需交换，因此跳过
     *         若nums[nums[i]] = nums[i]，说明索引 nums[i]处的值和索引i处的值都为nums[i],
     *         即找到一组重复值，返回此值 nums[i];
     *         否则，交换索引为i和nums[i]的元素值，将此数字交换到索引位置
     *       2、若遍历完毕尚未返回，则返回-1.
     */
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i<nums.length){
            if (i == nums[i]){
                i++;
                continue;
            }
            //index=i，index2=nums[i] index1!=index2 , --> nums[index1] == nums[index2] ---> repeat
            if (nums[nums[i]] == nums[i])
                return nums[i];
            //原地交换 令 nums[tmp] = tmp
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }





}







































