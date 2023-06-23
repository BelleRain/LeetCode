package CodeTop;

/**
 * @author mxy
 * @create 2023-06-15 10:11
 */

/**
 * 剑指 Offer 51. 数组中的逆序对     链接：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 */
public class Offer51 {

    public static void main(String[] args) {

    }

    /**
     * 归并排序
     * @param nums
     * @return
     */
    int[] nums,tmp;
    public int reversePairs1(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort1(0,nums.length - 1);
    }

    private int mergeSort1(int l, int r) {
       //终止条件
        if (l >= r) return 0;
        //递归划分
        int m = (l + r) / 2;
        int res = mergeSort1(l, m) + mergeSort1(m + 1, r);
        //合并阶段
        int i = l, j = m + 1;
        // 1. 暂存数组 nums 闭区间 [i,r] 内的元素至辅助数组 tmp；
        for (int k = l; k <= r; k++) {
            tmp[k] = nums[k];
        }
        //循环合并：设置双指针 i，j 分别指向 左/右 子数组的首元素；
        for (int k = l; k <= r; k++) {
            //当 i = m + 1 时： 代表左子数组已合并完，因此添加右子数组当前元素 tmp[j], 并执行 j = j + 1
            if (i == m + 1)
                nums[k] = tmp[j++];
            //否则，当 j = r + 1 时：代表右子数组已合并完，因此添加左子数组当前元素 tmp[i]，并执行 i = i + 1；
            //否则，当 tmp[i] <= tmp[j] 时：添加左子数组当前元素 tmp[i] ，并执行 i = i + 1；
            //为简化代码，[当 j = r + 1时] 与 [当 tmp[i] <= tmp[j] 时] 两判断项可合并
            else if (j == r + 1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            //否则（即 tmp[i] > tmp[j]）时： 添加右子数组当前元素 tmp[j]，并执行 j = j + 1；此时构成 m - i + 1 个【逆序对】，统计结果添加至 res；
            else{
                nums[k] = tmp[j++];
                //因为按从小到大排序，左半部分为较小的部分
                //若出现 tmp[i] > tmp[j]，则意味着[i,m] 大于 [j], 所以合并时逆序对为 m - i + 1
                //多次累加下来即为 res += m - i + 1;
                res += m - i + 1; //统计逆序对
            }
        }
        return res;
    }


    //利用归并排序模板
    int count;
    public int reversePairs(int[] nums){
        this.count = 0;
        merge(nums,0,nums.length - 1);
        return count;
    }

    public void merge(int[] nums, int left, int right){
        int mid = left + ((right - left) >> 1);
        if (left < right){
            merge(nums, left, mid);
            merge(nums, mid + 1, right);
            mergeSort(nums, left, mid ,right);
        }
    }

    private void mergeSort(int[] nums, int left, int mid, int right) {
        int[] temparr = new int[right - left + 1];
        int index = 0;
        int temp1 = left, temp2 = mid + 1;
        while (temp1 <= mid && temp2 <= right){
            if (nums[temp1] <= nums[temp2]){
                temparr[index++] = nums[temp1++];
            }else {
                //统计逆序对的个数
                count += mid - temp1 + 1;
                temparr[index++] = nums[temp2++];
            }
        }
        //把左边剩余的数移入数组
        while (temp1 <= mid){
            temparr[index++] = nums[temp1++];
        }
        //把右边剩余的数移入数组
        while (temp2 <= right){
            temparr[index++] = nums[temp2++];
        }
        //把新数组中的数覆盖nums数组
        for (int k = 0; k < temparr.length; k++) {
            nums[k + left] = temparr[k];
        }
    }

}







































