package CodeTop;

/**
 * @author mxy
 * @create 2023-05-27 14:26
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度    链接：https://leetcode.cn/problems/daily-temperatures
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *  
 * 提示：
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 */
public class Code739 {

    public static void main(String[] args) {
        int[] nums = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(nums)));
    }

    /**
     * 暴力解法：超时
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures1(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }


    /**
     * 题解链接：（视频讲解）https://leetcode.cn/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
     *   单调栈
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures2(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        int length = temperatures.length;
        int[] res = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            //当前温度小于等于栈顶温度，则入栈
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int pre = stack.pop();
                res[pre] = i - pre;
            }
            stack.push(i);
        }
        return res;
    }


    /**
     * 题解链接：https://leetcode.cn/problems/daily-temperatures/solution/jie-ti-si-lu-by-pulsaryu/
     * 相对于暴力解答而言，减少重复遍历的次数
     * 先从右边计算，则计算过的位置就不需要重复计算。
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] res = new int[length];
        //从右向左遍历
        for (int i = length - 2; i >= 0; i--) {
            //j += result[i] 是利用已经有的结果进行跳跃
            for (int j = i + 1; j < length; j+=res[j]) {
                if (temperatures[j] > temperatures[i]){
                    res[i] = j - i;
                    break;
                }else if (res[j] == 0){ //遇到0表示后面不会有更大的值，则当前值也应该为0
                    res[j] = 0;
                    break;
                }
            }
        }
        return res;
    }
}





































