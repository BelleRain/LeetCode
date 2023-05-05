package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 10:22
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间     链接：https://leetcode.cn/problems/merge-intervals
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
 *
 */
public class Code56 {

    public static void main(String[] args) {
        int[][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        int[][] merge = merge(intervals);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }


    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0) return new int[0][0];
        Arrays.sort(intervals,(x,y) -> (x[0] - y[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (res.get(res.size() - 1)[1] < start){
                res.add(intervals[i]);
            }else {
                res.get(res.size() - 1)[1] = Math.max(end,res.get(res.size() - 1)[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
































