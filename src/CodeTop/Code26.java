package CodeTop;

/**
 * @author mxy
 * @create 2023-06-17 12:56
 */

/**
 * 26. 删除有序数组中的重复项    链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。
 * nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * 判题标准:
 *
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。
 * 不需要考虑数组中超出新长度后面的元素。
 *  
 *
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按 升序 排列
 *
 */
public class Code26 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }


    /**
     * 题解链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
     * 1、数组是有序的，那重复的元素一定会相邻
     * 2、要求删除重复的元素，实际上就是将不重复的元素移到数组的左侧
     * 3、考虑用 2 个指针，一个在前记作 p ，一个在后记作 q，算法流程：
     *      1、比较 p 和 q 位置的元素是否相等
     *      2、如果相等，q 后移 1 位
     *      3、如果不相等，将 q 位置的 元素 复制到 p + 1位置上， p 后移一位，q 后移1位
     *      重复上述过程，直到 q 等于 数组长度。
     *      返回 p + 1，即为新数组的长度
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length){
            if (nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }


    /**
     * 优化：
     *    考虑如下数组：[0,1,2,3,4,5]
     *    此时数组中没有重复元素，按照上面的方面，每次比较时 nums[p] 都不等于 nums[q],
     *    因此就会将 q 指向的元素原地复制一遍，这个操作其实是不必要的。
     *    因此可以添加一个判断，当 q - p > 1 时，才进行复制。
     * @param nums
     * @return
     */
    public static int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length){
            if (nums[p] != nums[q]){
                if (q - p > 1){
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }



}







































