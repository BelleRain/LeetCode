package Top100;

/**
 * @author mxy
 * @create 2022-11-22 9:25
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间  链接：https://leetcode.cn/problems/merge-intervals
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class Top56 {

    public static void main(String[] args) {
        Top56 top56 = new Top56();
        int[][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        int[][] nums = top56.merge(intervals);
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    /**
     * 题解一： 题解链接： https://leetcode.cn/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
     * 解题思路：
     * 1、按照左区间排序，由小到大
     * 2、申请 List<int[]> mergeList 列表，当列表为空时，直接加入；
     *    当列表不为空时，比较列表中最后一个元素的右区间 与 待加入列表元素的 左区间，
     *    若 右区间 < 左区间，则证明不包含，直接加入；否则，重置区间范围 ，右区间为 两个元素右区间的最大值
     * 复杂度分析
     * 时间复杂度：O(nlogn)，其中 n 为区间的数量。除去排序的开销，我们只需要一次线性扫描，所以主要的时间开销是排序的 O(nlogn)。
     * 空间复杂度：O(logn)，其中 n 为区间的数量。这里计算的是存储答案之外，使用的额外空间。O(logn) 即为排序所需要的空间复杂度。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0) return res.toArray(new int[0][]);
        Arrays.sort(intervals,(a,b) -> (a[0] - b[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (res.get(res.size() - 1)[1] < left) res.add(intervals[i]);
            else {
                res.get(res.size() - 1)[1] = Math.max(right, res.get(res.size() - 1)[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
