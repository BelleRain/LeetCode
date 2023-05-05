package CodeTop;

/**
 * @author mxy
 * @create 2023-04-18 10:11
 */

/**
 * 11. 盛最多水的容器     链接：https://leetcode.cn/problems/container-with-most-water
 * 给定一个长度为  n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 提示：
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 *
 */
public class Code11 {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea1(height));
        System.out.println(maxArea2(height));
    }


    public static int maxArea1(int[] height) {
        int left = 0, right = height.length - 1, res = 0;
        while (left < right){
            //向内移动短板
            if (height[left] < height[right]){
                res = Math.max(res, height[left] * (right - left));
                left++;
            }else {
                res = Math.max(res, height[right] * (right - left));
                right--;
            }
        }
        return res;
    }


    public static int maxArea2(int[] height) {
        int size = height.length;
        int last = size - 1;
        int first = 0;
        int res = 0;
        while (first < last){
           int minH = Math.min(height[first], height[last]);
           res = Math.max(res, minH * (last - first));
           while (first < last && height[first] <= minH){
               first++;
           }
           while (first < last && height[last] <= minH){
               last--;
           }
        }
        return res;
    }
}


































