package Top100;

/**
 * @author mxy
 * @create 2022-11-18 9:24
 */

/**
 * 33. 搜索旋转排序数组   链接：https://leetcode.cn/problems/search-in-rotated-sorted-array
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 */
public class Top33 {

    public static void main(String[] args) {
        Top33 top33 = new Top33();
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(top33.search(nums, 0));
    }

    /*
    注意：时间复杂度为 O(log n) 一般即为二分查找
     */

    /**
     * 题解链接：https://leetcode.cn/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
     *  评论区解答：将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
     *      此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
     * 复杂度分析:
     *     时间复杂度： O(logn)，其中 n 为 nums 数组的大小。整个算法时间复杂度即为二分查找的时间复杂度 O(logn)。
     *     空间复杂度： O(1) 。我们只需要常数级别的空间存放变量。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int l = 0,r = n - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]){ //如果[l,mid-1]有序
                //且 target 满足 [nums[l],nums[mid]],则将搜索范围缩小至[l,mid - 1]
                //否则，在[mid + 1,r]中寻找
                if (nums[0] <= target && target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            }else { //如果[mid,r]有序
                //且 target 满足 [nums[mid+1],nums[r]],则将搜索范围缩小至[mid+1,r]
                //否则，在[l,mid-1]中寻找
                if (nums[mid] < target && target <= nums[n - 1]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }
}
