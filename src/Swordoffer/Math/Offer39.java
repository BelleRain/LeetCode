package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-10-22 8:11
 */

/**
 * 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 */
public class Offer39 {

    public static void main(String[] args) {
        Offer39 offer = new Offer39();
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(offer.majorityElement(nums));
    }


    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solution/mian-shi-ti-39-shu-zu-zhong-chu-xian-ci-shu-chao-3/
     * 本题常见的三种解法：
     * 哈希表统计法： 遍历数组 nums ，用 HashMap 统计各数字的数量，即可找出 众数 。此方法时间和空间复杂度均为 O(N) 。
     * 数组排序法： 将数组 nums 排序，数组中点的元素 一定为众数。
     * 摩尔投票法： 核心理念为 票数正负抵消 。此方法时间和空间复杂度分别为 O(N) 和 O(1) ，为本题的最佳解法。
     */

    /**
     * 方法一：哈希表统计法
     * @param nums
     * @return
     */
    //public int majorityElement(int[] nums) {
    //    Map<Integer, Integer> map = new HashMap<>();
    //    for (int i = 0; i < nums.length; i++) {
    //        if (!map.containsKey(nums[i])){
    //            map.put(nums[i],1);
    //        }else {
    //            map.put(nums[i],map.get(nums[i])+1);
    //        }
    //        if (map.get(nums[i]) > nums.length/2){
    //            return nums[i];
    //        }
    //    }
    //    return 0;
    //}

    /**
     * 方法二：数组排序法
     * @param nums
     * @return
     */
    //public int majorityElement(int[] nums) {
    //    Arrays.sort(nums);
    //    return nums[nums.length/2];
    //}

    /**
     * 方法三：摩尔投票法（最佳）
     * 将数组中出现次数超过一半的次数简称为 “众数”。
     * 数学中众数的定义为 “数组中出现次数最多的数字”
     * 推论一： 若记 众数 的票数为 +1 ，非众数 的票数为 -1 ，则一定有所有数字的 票数和 > 0 。
     * 推论二： 若数组的前 a 个数字的 票数和 = 0 ，则 数组剩余 (n-a) 个数字的 票数和一定仍 >0 ，即后 (n-a) 个数字的 众数仍为 x 。
     * @param nums
     * @return
     * 复杂度分析：
     * 时间复杂度 O(N) ： N 为数组 nums 长度。
     * 空间复杂度 O(1) ： votes 变量使用常数大小的额外空间
     */
    public int majorityElement(int[] nums) {
        //众数：x ；票数统计：vote
        int x = 0,votes = 0;
        for (int num : nums) {
            //votes == 0，n1为数组首个元素，根据推论，当 vote = 0时，剩余数组的众数一定不变
            //若 n1 = x ,则 说明 已抵消的数（已遍历过的数）中,有一半是众数
            //若 n1 ！= x，则 说明 已抵消的数中，众数x的数量最少为 0 个，最多为一半
            if (votes == 0) x = num;
            // num == x ? 1 : -1;
            // vote = vote + num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    /*
    拓展： 由于题目说明 给定的数组总是存在多数元素 ，因此本题不用考虑 数组不存在众数 的情况。若考虑，需要加入一个 “验证环节” ，遍历数组 nums 统计 x 的数量。
            若 x 的数量超过数组长度一半，则返回 x ；
            否则，返回未找到众数；
          时间和空间复杂度不变，仍为 O(N) 和 O(1) 。
        public int majorityElement(int[] nums) {
            int x = 0, votes = 0, count = 0;
            for(int num : nums){
                if(votes == 0) x = num;
                votes += num == x ? 1 : -1;
            }
            // 验证 x 是否为众数
            for(int num : nums)
                if(num == x) count++;
            return count > nums.length / 2 ? x : 0; // 当无众数时返回 0
        }
     */

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solution/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-pvh8/
     */

    /**
     * 方法四：随机化
     * @param
     * @return
     */
    //private int randRange(Random rand, int min, int max) {
    //    return rand.nextInt(max - min) + min;
    //}
    //
    //private int countOccurences(int[] nums, int num) {
    //    int count = 0;
    //    for (int i = 0; i < nums.length; i++) {
    //        if (nums[i] == num) {
    //            count++;
    //        }
    //    }
    //    return count;
    //}
    //
    //public int majorityElement(int[] nums) {
    //    Random rand = new Random();
    //
    //    int majorityCount = nums.length / 2;
    //
    //    while (true) {
    //        int candidate = nums[randRange(rand, 0, nums.length)];
    //        if (countOccurences(nums, candidate) > majorityCount) {
    //            return candidate;
    //        }
    //    }
    //}

    /**
     * 方法四：分治
     * @param nums
     * @param num
     * @param lo
     * @param hi
     * @return
     */
    //private int countInRange(int[] nums, int num, int lo, int hi) {
    //    int count = 0;
    //    for (int i = lo; i <= hi; i++) {
    //        if (nums[i] == num) {
    //            count++;
    //        }
    //    }
    //    return count;
    //}
    //
    //private int majorityElementRec(int[] nums, int lo, int hi) {
    //    // base case; the only element in an array of size 1 is the majority
    //    // element.
    //    if (lo == hi) {
    //        return nums[lo];
    //    }
    //
    //    // recurse on left and right halves of this slice.
    //    //int mid = (hi + lo) / 2;
    //    int mid = (hi - lo) / 2 + lo;
    //    int left = majorityElementRec(nums, lo, mid);
    //    int right = majorityElementRec(nums, mid + 1, hi);
    //
    //    // if the two halves agree on the majority element, return it.
    //    if (left == right) {
    //        return left;
    //    }
    //
    //    // otherwise, count each element and return the "winner".
    //    int leftCount = countInRange(nums, left, lo, hi);
    //    int rightCount = countInRange(nums, right, lo, hi);
    //
    //    return leftCount > rightCount ? left : right;
    //}
    //
    //public int majorityElement(int[] nums) {
    //    return majorityElementRec(nums, 0, nums.length - 1);
    //}

}






























