package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 9:06
 */

/**
 * 4. 寻找两个正序数组的中位数      链接：https://leetcode.cn/problems/median-of-two-sorted-arrays
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 */
public class Code04 {

    public static void main(String[] args) {
        Code04 code04 = new Code04();
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(code04.findMedianSortedArrays1(nums1, nums2));
        System.out.println(code04.findMedianSortedArrays2(nums1, nums2));
        System.out.println(code04.findMedianSortedArrays3(nums1, nums2));
        System.out.println(code04.findMedianSortedArrays4(nums1, nums2));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     */

    /**
     * 解法一：O(m + n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] temp = merge(nums1, nums2);
        //奇数个
        if ((temp.length & 1) == 1) return (double) temp[temp.length / 2];
        else return (double) (temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2;
    }

    //合并过程
    public int[] merge(int[] nums1,int[] nums2){
        if (nums1.length == 0 && nums2.length == 0) return new int[0];
        if (nums1.length == 0) return nums2;
        if (nums2.length == 0) return nums1;
        int[] temp = new int[nums1.length + nums2.length];
        int i = 0 , j = 0, t = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] <= nums2[j]) temp[t++] = nums1[i++];
            else temp[t++] = nums2[j++];
        }
        while (i < nums1.length) temp[t++] = nums1[i++];
        while (j < nums2.length) temp[t++] = nums2[j++];
        return temp;
    }

    /**
     * 解法二：O(m + n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1,right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len/2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])){
                right = nums1[aStart++];
            }else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0){
            return (left + right)/2.0;
        }else {
            return right;
        }
    }


    /**
     * 解法三：O(log(m + n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (m + n + 1)/2;
        int right = (m + n + 2)/2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的k
        return (getKth(nums1, 0,n - 1,nums2,0,m - 1,left) + getKth(nums1,0,n - 1,nums2,0,m - 1,right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让len1的长度小于len2，这样就能保证如果数组空了，一定是len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1,start1 ,end1 ,k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        int i = start1 + Math.min(len1, k/2) - 1;
        int j = start2 + Math.min(len2, k/2) - 1;
        if (nums1[i] > nums2[j]){
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }else {
            return getKth(nums1, i + 1, end1, nums2,start2 ,end2 , k - (i - start1 + 1));
        }
    }


    /**
     * 解法四：O(log(m + n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n){
            return findMedianSortedArrays4(nums2, nums1); //保证 m <= n
        }
        int iMin = 0,iMax = m;
        while (iMin <= iMax){
            int i = (iMin + iMax)/2;
            int j = (m + n + 1)/2 - i;
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]){
                //i需要增大
                iMin = i + 1;
            }else if (i != 0 && j != n && nums1[i - 1] > nums2[j]){
                //i需要减小
                iMax = i - 1;
            }
            else { //达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0){
                    maxLeft = nums2[j - 1];
                }else if (j == 0){
                    maxLeft = nums1[i - 1];
                }else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n)%2 == 1){
                    return maxLeft;  //奇数的话不用考虑右半部分
                }
                int minRight = 0;
                if (i == m){
                    minRight = nums2[j];
                }else if (j == n){
                    minRight = nums1[i];
                }else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight)/2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }
}





















