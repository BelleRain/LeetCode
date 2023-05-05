package Top100;

/**
 * @author mxy
 * @create 2022-11-16 8:35
 */

/**
 * 11. 盛最多水的容器   链接：https://leetcode.cn/problems/container-with-most-water
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
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
 */
public class Top11 {

    public static void main(String[] args) {
        Top11 top11 = new Top11();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(top11.maxArea(height));
    }

    /**
     * 题解一：  题解链接：https://leetcode.cn/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
     *      s[i,j] = min(h[i],h[j]) * (j - i)
     *   在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 -1​ 变短：
     *       若向内 移动短板 ，水槽的短板 min(h[i], h[j]) 可能变大，因此下个水槽的面积 可能增大 。
     *       若向内 移动长板 ，水槽的短板 min(h[i], h[j])​ 不变或变小，因此下个水槽的面积 一定变小 。
     *  因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积。
     *
     * 1、复杂度分析：
     *      时间复杂度 O(N)​ ： 双指针遍历一次底边宽度 N​​ 。
     *      空间复杂度 O(1)​ ： 变量 i , j , res 使用常数额外空间。
     * 2、关键点：容器面积取决于短板
     * 3、移动长板，面积只会变得更小：
     *      1、当遇到更短的板子，面积变小
     *      2、当遇到更长的板子，面积仍然变小（因为容器面积取决于短板，长板变长没有用）
     *      3、当长板子长度不变，面积还是变小
     * 而移动短板有可能使得面积变大或变小。 因此，选择改变短板，才有可能使面积变大
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0 ,right = height.length - 1, res = 0;
        while (left < right){
             res = height[left] < height[right] ?
                    Math.max(res, (right - left) * height[left++]) :
                    Math.max(res, (right - left) * height[right--]);
        }
        return res;
    }


    /**
     * 题解二: 利用两个while循环，可以不必每次移动指针计算并比较取面积最大值，减少计算和比较的次数
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 51.3 MB , 在所有 Java 提交中击败了 68.66% 的用户
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int size = height.length;
        int last = size-1;
        int first = 0;
        int res = 0;
        while(first!=last){
            int minH = Math.min(height[first],height[last]);
            int a = (last-first) * minH;
            res = Math.max(res,a);
            //如果height[first] <= minH 的话，由于last - first逐渐减小，则 面积减小或不变，
            //当height[first] > minH 面积才有可能增大
            while(height[first]<=minH && first!=last){
                first++;
            }
            //height[last]<=minH同理
            while(height[last]<=minH && first!=last){
                last--;
            }
            //利用两个while循环，可以不必每次计算并比较取面积最大值，减少计算和比较的次数
        }
        return res;
    }
}
