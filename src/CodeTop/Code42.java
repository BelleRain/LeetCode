package CodeTop;

/**
 * @author mxy
 * @create 2023-04-18 11:13
 */

import java.util.Stack;

/**
 * 42. 接雨水     链接：https://leetcode.cn/problems/trapping-rain-water
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *  *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 *
 */
public class Code42 {

    public static void main(String[] args) {
        //int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height = {4,2,0,3,2,5};
        System.out.println(trap1(height));
        System.out.println(trap2(height));
        System.out.println(trap3(height));
        System.out.println(trap4(height));
        System.out.println(trap5(height));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
     */

    /**
     * 按行求 : 超时
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        int sum = 0;
        int max = getMax(height);
        for (int i = 1; i <= max; i++) {
            boolean isStart = false; //标记是否开始更新 temp
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i){
                    temp_sum++;
                }
                if (height[j] >= i){
                    sum = sum + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }

    private static int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max){
                max = height[i];
            }
        }
        return max;
    }


    /**
     * 按列求
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left){
                    max_left = height[j];
                }
            }
            //找出右边最高
            int max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right){
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一端大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]){
                sum = sum + min - height[i];
            }
        }
        return sum;
    }

    /**
     * 动态规划
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        //max_left [i] 代表第 i 列左边最高的墙的高度，不包括 第 i 列
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        //max_right[i] 代表第 i 列右边最高的墙的高度，不包括 第 i 列
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]){
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }


    /**
     * 双指针
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 60.58% 的用户
     * @param height
     * @return
     */
    public static int trap4(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; //加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]){
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]){
                    sum = sum + (min - height[left]);
                }
                left++;
            }else { //从右到左更
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]){
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /**
     * 栈
     * 0,1,0,2,1,0,1,3,2,1,2,1
     * @param height
     * @return
     */
    public static int trap5(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length){
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]){
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()){ //栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }


    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int maxL = 0;
        int maxR = 0;
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int index = 0;
        while(left<right){
            maxL = Math.max(maxL, height[left]);
            maxR = Math.max(maxR, height[right]);
            if(maxR > maxL){
                result += maxL - height[left++];
            } else{
                result += maxR - height[right--];
            }
        }
        return result;
    }
}

































