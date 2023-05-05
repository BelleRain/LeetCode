package Swordoffer.DivideAndConquer;

/**
 * @author mxy
 * @create 2022-11-02 15:49
 */

/**
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *  
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class Offer51 {

    public static void main(String[] args) {
        Offer51 offer = new Offer51();
        int[] nums = {7,5,6,4};
        System.out.println(offer.reversePairs(nums));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/jian-zhi-offer-51-shu-zu-zhong-de-ni-xu-pvn2h/
     * 题解一：归并排序
     * 复杂度分析：
     *    时间复杂度 O(NlogN) ： 其中 N 为数组长度；归并排序使用 O(NlogN) 时间；
     *    空间复杂度 O(N) ： 辅助数组 tmp 占用 O(N) 大小的额外空间；
     * @param nums
     * @return
     */
    int[] nums,tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }

    private int mergeSort(int l,int r){
        //终止条件
        if (l >= r) return 0;
        //递归划分
        int m = (l+r)/2;
        int res = mergeSort(l, m) + mergeSort(m+1, r);
        //合并阶段
        int i = l,j = m+1;
        //1.暂存数组nums闭区间[i,r]内的元素至辅助数组tmp；
        for (int k = l; k <= r; k++) {
            tmp[k] = nums[k];
        }
        //循环合并：设置双指针 i , j 分别指向左 / 右子数组的首元素；
        for (int k = l; k <= r; k++) {
            //当 i = m + 1 时： 代表左子数组已合并完，因此添加右子数组当前元素 tmp[j] ，并执行 j=j+1 ；
            if (i == m+1)
                nums[k] = tmp[j++];
            //否则，当 j = r + 1 时： 代表右子数组已合并完，因此添加左子数组当前元素 tmp[i] ，并执行 i = i + 1；
            //否则，当 tmp[i]≤tmp[j] 时： 添加左子数组当前元素 tmp[i] ，并执行 i = i + 1；
            //为简化代码，「当j=r+1 时」 与 「当tmp[i]≤tmp[j] 时」 两判断项可合并。
            else if (j == r+1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            //否则（即 tmp[i] > tmp[j]）时： 添加右子数组当前元素 tmp[j] ，并执行 j = j + 1；此时构成 m - i + 1 个「逆序对」，统计添加至 res ；
            else {
                nums[k] = tmp[j++];
                //因为按从小到大排序，左半部分为较小的部分，
                //若出现tmp[i] > tmp[j]，则意味着[i,m]大于[j],所以一次合并时逆序对为m-i+1
                //多次累加下来即为 res += m-i+1;
                res += m-i+1; //统计逆序对
            }
        }
        return res;
    }

    //利用归并排序模板
    /*int count;
    public int reversePairs(int[] nums) {
        this.count = 0;
        merge(nums, 0, nums.length - 1);
        return count;
    }
    public void merge(int[] nums, int left, int right) {
        int mid = left + ((right - left) >> 1);
        if (left < right) {
            merge(nums, left, mid);
            merge(nums, mid + 1, right);
            mergeSort(nums, left, mid, right);
        }
    }
    public void mergeSort(int[] nums, int left, int mid, int right) {
        int[] temparr = new int[right - left + 1];
        int index = 0;
        int temp1 = left, temp2 = mid + 1;

        while (temp1 <= mid && temp2 <= right) {
            if (nums[temp1] <= nums[temp2]) {
                temparr[index++] = nums[temp1++];
            } else {
                //统计逆序对的个数
                count += mid - temp1 + 1;
                temparr[index++] = nums[temp2++];
            }
        }
        //把左边剩余数移入数组
        while (temp1 <= mid) {
            temparr[index++] = nums[temp1++];
        }
        //把右边剩余的数移入数组
        while (temp2 <= right) {
            temparr[index++] = nums[temp2++];
        }
        //把新数组中的数覆盖nums数组
        for (int k = 0; k < temparr.length; k++) {
            nums[k+left] = temparr[k];
        }
    }*/

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
     * 题解二：离散化树状数组
     * 复杂度分析
     *      时间复杂度：离散化的过程中使用了时间代价为 O(nlogn) 的排序，单次二分的时间代价为 O(logn)，一共有 n 次，
     *          总时间代价为 O(nlogn)；循环执行 n 次，每次进行O(logn) 的修改和O(logn) 的查找，总时间代价为O(nlogn)。
     *          故渐进时间复杂度为 O(nlogn)。
     *      空间复杂度：树状数组需要使用长度为 n 的数组作为辅助空间，故渐进空间复杂度为 O(n)。
     * @param nums
     * @return
     */
    /*public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        // 离散化
        Arrays.sort(tmp);
        for (int i = 0; i < n; ++i) {
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        }
        // 树状数组统计逆序对
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            ans += bit.query(nums[i] - 1);
            bit.update(nums[i]);
        }
        return ans;
    }

    public class BIT {
        private int[] tree;
        private int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public int query(int x) {
            int ret = 0;
            while (x != 0) {
                ret += tree[x];
                x -= lowbit(x);
            }
            return ret;
        }

        public void update(int x) {
            while (x <= n) {
                ++tree[x];
                x += lowbit(x);
            }
        }
    }*/


    //超时
    /*int count = 0;
    public int reversePairs(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }*/

}
