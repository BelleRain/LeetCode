package Swordoffer.SearchAlgorithms;

/**
 * @author mxy
 * @create 2022-09-18 9:53
 */

/**
 * 在排序数组中查找数字 I   注意：排序数组，故无需另行排序
 * 统计一个数字在 排序数组 中出现的次数。
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 */
public class Offer53_1 {

    public static void main(String[] args) {
        Offer53_1 offer = new Offer53_1();
        int[] nums = {5, 7, 8, 8, 10};
        //int[] nums = {2,2};
        int count = offer.search(nums, 8);
        System.out.println(count);
    }

    /**
     * 解法一： 使用二分查找
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 78.04% 的用户
     * @param nums
     * @param target
     * @return
     */
    //public int search(int[] nums, int target) {
    //    int count = binarySearch(0, nums.length-1, numsNew, target);
    //    return count;
    //
    //    //System.out.println(Arrays.toString(numsNew));
    //}
    //
    //public int binarySearch(int left, int right, Integer[] nums, int target) {
    //    ArrayList<Integer> arrayList = new ArrayList<>();
    //    if (left > right || nums.length == 0) {
    //        return 0;
    //    }
    //    int mid = (left + right) / 2;
    //    int midVal = nums[mid];
    //    if (target < midVal) {
    //        return binarySearch(left, mid - 1, nums, target);
    //    } else if (target > midVal) {
    //        return binarySearch(mid + 1, right, nums, target);
    //    } else {
    //        /**
    //         * 思路分析
    //         *    1. 在找到mid 索引值，不要马上返回
    //         *    2. 向mid 索引值的左边扫描，将所有满足 target， 的元素的下标，加入到集合ArrayList
    //         *    3. 向mid 索引值的右边扫描，将所有满足 target， 的元素的下标，加入到集合ArrayList
    //         *    4. 将Arraylist返回
    //         */
    //        //向mid 索引值的左边扫描，将所有满足 target， 的元素的下标，加入到集合ArrayList
    //        int temp = mid - 1;
    //        while (true) {
    //            if (temp < 0 || nums[temp] != target){
    //                break;
    //            }
    //            arrayList.add(nums[temp]);
    //            temp--;
    //        }
    //        arrayList.add(nums[mid]);
    //        int tmp = mid + 1;
    //        while (true){
    //            if (tmp > nums.length-1 || nums[tmp] != target){
    //                break;
    //            }
    //            arrayList.add(nums[tmp]);
    //            tmp++;
    //        }
    //        return arrayList.size();
    //    }
    //}

    /**
     * 解法二：
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 解题思路： 核心：查询重复元素的左右边界
     * 初始化： 左边界 i = 0i=0 ，右边界 j = len(nums) - 1j=len(nums)−1 。
     * 循环二分： 当闭区间 [i, j][i,j] 无元素时跳出；
     * 计算中点 m = (i + j) / 2m=(i+j)/2 （向下取整）；
     * 若 nums[m] < targetnums[m]<target ，则 targettarget 在闭区间 [m + 1, j][m+1,j] 中，因此执行 i = m + 1i=m+1；
     * 若 nums[m] > targetnums[m]>target ，则 targettarget 在闭区间 [i, m - 1][i,m−1] 中，因此执行 j = m - 1j=m−1；
     * 若 nums[m] = targetnums[m]=target ，则右边界 rightright 在闭区间 [m+1, j][m+1,j] 中；左边界 leftleft 在闭区间 [i, m-1][i,m−1] 中。因此分为以下两种情况：
     * 若查找 右边界 rightright ，则执行 i = m + 1i=m+1 ；（跳出时 ii 指向右边界）
     * 若查找 左边界 leftleft ，则执行 j = m - 1j=m−1 ；（跳出时 jj 指向左边界）
     * 返回值： 应用两次二分，分别查找 rightright 和 leftleft ，最终返回 right - left - 1right−left−1 即可。
     *
     * @param nums
     * @param target
     * @return
     */
    //public int search(int[] nums, int target) {
    //    //搜索右边界right
    //    int i = 0, j = nums.length - 1;
    //    while (i <= j) {
    //        int m = (i+j)/2;
    //        //中间值小于等于目标值，搜索右边
    //        if (nums[m] <= target)
    //            i = m+1;
    //        else j = m - 1;
    //    }
    //    //找到重复元素的下一元素
    //    int right = i;
    //    //若数组中无target，则提前返回
    //    if (j>=0&&nums[j]!=target)
    //        return 0;
    //    //搜索左边界right
    //    i = 0; j = nums.length-1;
    //    while (i<=j){
    //        int m = (i+j)/2;
    //        if (nums[m] < target)
    //            i=m+1;
    //        else
    //            j = m -1;
    //    }
    //    int left =j;
    //    return right -left -1;
    //}


    /**
     * 解法三： 对解法二的优化，核心:统一将边界规定为查询右边界
     * 以上代码显得比较臃肿（两轮二分查找代码冗余），可将二分查找右边界 right 的代码 封装至函数 helper()
     * @param nums
     * @param target
     * @return
     */
    //public int search(int[] nums, int target) {
    //    return helper(nums, target) - helper(nums, target - 1);
    //}
    //int helper(int[] nums, int tar) {
    //    int i = 0, j = nums.length - 1;
    //    while(i <= j) {
    //        int m = (i + j) / 2;
    //        if(nums[m] <= tar) i = m + 1;
    //        else j = m - 1;
    //    }
    //    return i;
    //}


    /**
     * 解法四：
     * 寻找左右边界取差
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}













