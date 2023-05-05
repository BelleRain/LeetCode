package Swordoffer.Sort;

/**
 * @author mxy
 * @create 2022-10-11 8:41
 */

import java.util.*;

/**
 * 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 */
public class Offer41 {

    public static void main(String[] args) {
        Offer41 offer = new Offer41();
        offer.addNum(5);
        offer.addNum(2);
        offer.addNum(4);
        offer.addNum(3);
        offer.addNum(1);
        offer.addNum(6);
        System.out.println(offer.findMedian());
    }


    /**
     * 方法一：   原文链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
     * 解题思路：
     *    给定一长度为 N 的无序数组，其中位数的计算方法：首先对数组执行排序（使用 O(NlogN) 时间），然后返回中间元素即可（使用 O(1) 时间）。
     *   针对本题，根据以上思路，可以将数据流保存在一个列表中，并在添加元素时 保持数组有序 。此方法的时间复杂度为 O(N) ，
     *   其中包括： 查找元素插入位置 O(logN) （二分查找）、向数组某位置插入元素 O(N) （插入位置之后的元素都需要向后移动一位）。
     *
     *   借助 堆 可进一步优化时间复杂度。
     * 建立一个 小顶堆 A 和 大顶堆 B ，各保存列表的一半元素，且规定：
     *     A 保存 较大 的一半，长度为 N/2 （ N 为偶数） 或 (N+1)/2 （ N 为奇数）；
     *     B 保存 较小 的一半，长度为 N/2 （ N 为偶数） 或 (N-1)/2 （ N 为奇数）;
     * 随后，中位数可仅根据 A, B 的堆顶元素计算得到。
     * 算法流程：
     * 1、设元素总数为 N = m + n，其中 m 和 n 分别为 A和 B 中的元素个数。
     * 2、 addNum(num) 函数：
     *    1、 当 m = n（即 N 为 偶数）：需向 A 添加一个元素。实现方法：将新元素 num 插入至 B ，再将 B 堆顶元素插入至 A ；
     *    2、 当 m！= n（即 N 为 奇数）：需向 B 添加一个元素。实现方法：将新元素 num 插入至 A ，再将 A 堆顶元素插入至 B ；
     *    假设插入数字 num 遇到情况 1. 由于 num 可能属于 “较小的一半” （即属于 B ），因此不能将 nums 直接插入至 A 。
     *     而应先将 num 插入至 B ，再将 B 堆顶元素插入至 A 。这样就可以始终保持 A 保存较大一半、 B 保存较小一半。
     * 3、findMedian() 函数：
     *   1、 当 m = n（ N 为 偶数）：则中位数为 (( A 的堆顶元素 + B 的堆顶元素 )/2)。
     *   2、 当 m！= n （ N 为 奇数）：则中位数为 A 的堆顶元素。
     * 复杂度分析：
     * 时间复杂度：
     *     查找中位数 O(1) ： 获取堆顶元素使用 O(1) 时间；
     *     添加数字 O(logN) ： 堆的插入和弹出操作使用 O(logN) 时间。
     * 空间复杂度 O(N) ： 其中 N 为数据流中的元素数量，小顶堆 A 和大顶堆 B 最多同时保存 N 个元素。
     */
    /** initialize your data structure here. */
    Queue<Integer> A,B;
    public Offer41() {
        //中位数：奇数个时：较大一半的最小值或较小一半的最大值
        //       偶数个时，较大一半的最小值 和 较小一半的最大值 之和 /2
        //由A加入B，保证是较小的数，由B加入A，保证是较大的数
        //A和B的数量始终差1 或 0
        A = new PriorityQueue<>(); //小顶堆，保存较大的一半
        B = new PriorityQueue<>((x,y) -> (y-x)); //大顶堆，保存较小的一半
    }

    public void addNum(int num) {
        if (A.size() != B.size()){
            A.add(num);
            B.add(A.poll());
        }else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek())/2.0;
    }

    /**
     * 方法二：有序集合 + 双指针 原文链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/shu-ju-liu-zhong-de-zhong-wei-shu-by-lee-um4f/
     *思路和算法:
     *  我们也可以使用有序集合维护这些数。我们把有序集合看作自动排序的数组，使用双指针指向有序集合中的中位数元素即可。
     *  当累计添加的数的数量为奇数时，双指针指向同一个元素。当累计添加的数的数量为偶数时，双指针分别指向构成中位数的两个数。
     *  当我们尝试添加一个数 num 到数据结构中，我们需要分情况讨论：
     *    1、初始有序集合为空时，我们直接让左右指针指向 num 所在的位置。
     *    2、有序集合为中元素为奇数时，left 和 right 同时指向中位数。如果 num 大于等于中位数，那么只要让 left 左移，否则让 right 右移即可。
     *    3、有序集合为中元素为偶数时，left 和 right 分别指向构成中位数的两个数。
     *        1、当 num 成为新的唯一的中位数，那么我们让 left 右移，right 左移，这样它们即可指向 num 所在的位置；
     *        2、当 num 大于等于 right，那么我们让 left 右移即可；
     *        3、当 num 小于 right 指向的值，那么我们让 right 左移，注意到如果 num 恰等于 left 指向的值，
     *           那么 num 将被插入到 left 右侧，使得 left 和 right 间距增大，所以我们还需要额外让 left 指向移动后的 right。
     */
    //TreeMap<Integer, Integer> nums;
    //int n;
    //int[] left;
    //int[] right;
    //
    //public Offer41() {
    //    nums = new TreeMap<Integer, Integer>();
    //    n = 0;
    //    left = new int[2];
    //    right = new int[2];
    //}
    //
    //public void addNum(int num) {
    //    nums.put(num, nums.getOrDefault(num, 0) + 1);
    //    if (n == 0) {
    //        left[0] = right[0] = num;
    //        left[1] = right[1] = 1;
    //    } else if ((n & 1) != 0) {
    //        if (num < left[0]) {
    //            decrease(left);
    //        } else {
    //            increase(right);
    //        }
    //    } else {
    //        if (num > left[0] && num < right[0]) {
    //            increase(left);
    //            decrease(right);
    //        } else if (num >= right[0]) {
    //            increase(left);
    //        } else {
    //            decrease(right);
    //            System.arraycopy(right, 0, left, 0, 2);
    //        }
    //    }
    //    n++;
    //}
    //
    //public double findMedian() {
    //    return (left[0] + right[0]) / 2.0;
    //}
    //
    //private void increase(int[] iterator) {
    //    iterator[1]++;
    //    if (iterator[1] > nums.get(iterator[0])) {
    //        iterator[0] = nums.ceilingKey(iterator[0] + 1);
    //        iterator[1] = 1;
    //    }
    //}
    //
    //private void decrease(int[] iterator) {
    //    iterator[1]--;
    //    if (iterator[1] == 0) {
    //        iterator[0] = nums.floorKey(iterator[0] - 1);
    //        iterator[1] = nums.get(iterator[0]);
    //    }
    //}

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

































