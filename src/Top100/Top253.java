package Top100;

/**
 * @author mxy
 * @create 2022-12-29 9:50
 */

import javax.swing.text.MutableAttributeSet;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II      链接：https://leetcode.cn/problems/meeting-rooms-ii
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 返回 所需会议室的最小数量 。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 *
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *  
 * 提示：
 * 1 <= intervals.length <= 10^4
 * 0 <= starti < endi <= 10^6
 *
 */
public class Top253 {

    public static void main(String[] args) {
        Top253 top253 = new Top253();
        int[][] intervals = {
                {0,30},
                {5,10},
                {15,20}
        };
        System.out.println(top253.minMeetingRooms(intervals));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/meeting-rooms-ii/solution/tu-jie-zhuan-hua-wei-shang-xia-che-wen-t-uy2q/
     *          https://leetcode.cn/problems/meeting-rooms-ii/solution/tong-ji-tong-shi-jin-xing-de-hui-yi-by-loick/
     * " 先下车，后上车 "
     * @param intervals
     * @return
     */
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0){
            return 0;
        }
        //1、构建最小堆，会议结束时间
        PriorityQueue<Integer> q = new PriorityQueue<>();
        //2、按会议开始时间从小到大排序 [0,30],[5,10],[15,20]
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        //3、遍历堆，比较会议开始时间和结束时间，若堆顶
        q.add(intervals[0][1]);
        //intervals[i][0] : 会议开始时间
        //intervals[i][1] : 会议结束时间
        for (int i = 1; i < intervals.length; i++) {
            //堆顶（最小）会议的结束时间 <= 当前遍历会议的开始时间，则可以使用同一间会议室，故弹出堆顶
            if (q.peek() <= intervals[i][0]){
                q.poll();
            }
            //加入当前遍历会议结束时间
            q.add(intervals[i][1]);
        }
        //4、最终堆的大小即为房间的数量
        return q.size();
    }

    /**
     * 题解链接：https://leetcode.cn/problems/meeting-rooms-ii/solution/tu-jie-zhuan-hua-wei-shang-xia-che-wen-t-uy2q/
     * 对应下标： 0：  0 ； 30 ：1 ；
     *           5:  2 ； 10 : 3；
     *           15： 4； 20 ：5
     * 上下车问题:
     *      上车：[0, 1], [5, 1], [15, 1]
     *      下车：[10, -1], [20, -1], [30, -1]
     *
     *      然后按照第一个数把上下车排好序
     *      人数  1    2     1     2     1      0
     *           0---- 5---- 10----15----20-----30
     *      变化  +1   +1    -1    +1    -1    -1
     * 注意：遇到相同的开始和结束时间，先下车 后上车
     * @param intervals
     * @return
     */
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[][] data = new int[2 * intervals.length][2];
        int res = 0,max = 0;
        //步长为2
        for (int i = 0; i < intervals.length * 2; i += 2) {
            data[i][0] = intervals[i/2][0];
            data[i][1] = 1;
            data[i + 1][0] = intervals[i/2][1];
            data[i + 1][1] = -1;
        }
        Arrays.sort(data,(x,y) -> x[0] != y[0] ? Integer.compare(x[0],y[0]) : Integer.compare(x[1],y[1]));
        for (int[] cur : data) {
            //System.out.println(Arrays.toString(cur));
            res += cur[1];
            //取车上人数的最大值
            max = Math.max(max, res);
        }
        return max;
    }


    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int idx1 = 0;
        int idx2 = 0;
        int cnt = 0;
        while (idx1 < n){
            //会议结束时间 <= 会议开始时间，则可以使用同一间会议室
            if (ends[idx2] <= starts[idx1]){
                cnt--;
                idx2++;
            }
            cnt++;
            idx1++;
        }
        return cnt;
    }
}


















