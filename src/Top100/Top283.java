package Top100;

/**
 * @author mxy
 * @create 2023-01-08 20:54
 */

import java.util.Arrays;

/**
 * 283. 移动零   链接：https://leetcode.cn/problems/move-zeroes
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *  
 * 进阶：你能尽量减少完成的操作次数吗？
 *
 */
public class Top283 {

    public static void main(String[] args) {
        Top283 top283 = new Top283();
        //int[] nums = {0,1,0,3,12};
        int[] nums = {1,3,0,3,12};
        top283.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
     */

    /**
     * 方法一：一次遍历
     * @param nums
     */
    /*public void moveZeroes(int[] nums) {
        if (nums.length == 1 || nums.length == 0) return;
        int j = 0; //i指向0元素，j指向非0元素
        //前面的数为0时，i和j同时加1，不会进行交换
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }*/


    /**
     * 方法二： 二次遍历
     *     第一次遍历的时候指针j用来记录当前有多少非0元素。
     *     即遍历的时候每遇到一个非0元素就将其往数组左边挪，
     *     第一次遍历完后，j指针的下标就指向了最后一个非0元素下标。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[j++] = nums[i];
        }
        //非0元素都统计完了，剩下的都是非0元素，所以第二次遍历把末尾元素都赋为0即可
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
