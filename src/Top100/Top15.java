package Top100;

/**
 * @author mxy
 * @create 2022-11-16 10:00
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和  链接：https://leetcode.cn/problems/3sum
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */
public class Top15 {

    public static void main(String[] args) {
        Top15 top15 = new Top15();
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(top15.threeSum(nums));
    }

    /**
     * 题解一：  题解链接：https://leetcode.cn/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
     * 复杂度分析：
     * 时间复杂度 O(N^2)：其中固定指针k循环复杂度 O(N)，双指针 i，j 复杂度 O(N)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排序
        List<List<Integer>> res = new ArrayList<>();
        //i,j,k三个指针，k指向三个数中的最小值
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0 ) break; //num[j] >= nums[i] >= nums[k]，若nums[k] >0,则一定不存在和为0的解
            if (k > 0 && nums[k] == nums[k-1]) continue; //跳过所有重复的k，防止重复组合
            int i = k + 1, j = nums.length - 1; //i，j为[k+1,length-1]的左右指针
            while (i < j){  //固定k指针，移动另外两个指针，k指针在for大循环中移动
                int sum = nums[k] + nums[i] + nums[j];
                //跳过重复值的原因：重复的值会得到重复的三元组，答案中要求不可有重复的三元组
                if (sum < 0){ //如果和小于0，则向右移动 i 指针并跳过所有重复的nums[i]，增大和
                    while (i < j && nums[i] == nums[++i]);
                }
                else if (sum > 0){ //如果和大于0.则向左移动 j 指针并跳过重复的nums[j]，减小和
                    while (i < j && nums[j] == nums[--j]);
                }else{ //当和等于0，记录三元组，执行 i+=1，j-=1，并跳过所有重复的nums[i] 和 nums[j]，防止记录到重复组合
                    res.add(new ArrayList<>(Arrays.asList(nums[k],nums[i],nums[j])));
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }


    /**
     * 题解二： 题解链接：https://leetcode.cn/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
     * 思路与题解一相同，只是运行较快
     * 注意与题解一中while循环的区别：
     *      （1）while (i < j && nums[i] == nums[++i]);  //题解一的处理更加巧妙
     *      （2）while (L<R && nums[L] == nums[L+1]) L++;
     *            L++;
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 91.52% 的用户
     * 内存消耗： 45.4 MB , 在所有 Java 提交中击败了 72.13% 的用户
     * @param nums
     * @return
     */
    /*public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }*/
}
