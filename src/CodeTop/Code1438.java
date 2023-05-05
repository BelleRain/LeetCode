package CodeTop;

/**
 * @author mxy
 * @create 2023-03-19 13:11
 */

import org.omg.CORBA.MARSHAL;

import java.util.*;

/**
 * 1438. 绝对差不超过限制的最长连续子数组   链接：https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回 最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 * 示例 1：
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 *
 * 示例 2：
 * 输入：nums = [10,1,2,4,7,2 ], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 *
 * 示例 3：
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *  
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 */
public class Code1438 {

    public static void main(String[] args) {
        int[] nums = {10,2,1,4,7,2};
        System.out.println(longestSubarray1(nums, 5));
        System.out.println(longestSubarray2(nums, 5));
        //test();
    }

    /**
     * 题解链接：https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solution/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
     */


    /**
     * 最长连续子数组的长度
     * 方法一：滑动窗口 + 有序集合
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubarray1(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int left = 0,right = 0;
        int ret = 0;
        while (right < n){
             map.put(nums[right], map.getOrDefault(nums[right], 0)+1);
            while (map.lastKey() - map.firstKey() > limit){
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * 方法二：滑动窗口 + 单调队列
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<>();
        Deque<Integer> queMin = new LinkedList<>();
        int n = nums.length;
        int left = 0,right = 0;
        int ret = 0;
        while (right < n){
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]){
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]){
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit){
                if (nums[left] == queMin.peekFirst()){
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()){
                    queMax.pollFirst();
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }


    public static void test() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] nums = {10, 1, 2, 4, 7, 2};
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        //for (Map.Entry entry : map.entrySet()){
        //    entry.getKey();
        //    entry.getValue();
        //}
        map.forEach((key, value) -> {
            System.out.println(key + "=" + value);
        });
        System.out.println("map.firstKey(): " + map.firstKey());
        System.out.println("map.lastKey(): " + map.lastKey());

        System.out.println("map.firstEntry(): " + map.firstEntry());
        System.out.println("map.lastEntry(): " + map.lastEntry());

        System.out.println("map.ceilingKey(4):" + map.ceilingKey(4)); // <= 的最大值 map.ceilingKey(4):4
        System.out.println("map.floorKey(4):" + map.floorKey(4));     // >= 的最小值 map.floorKey(4):4

        System.out.println("map.ceilingKey(3):" + map.ceilingKey(3)); // >= 的最小值 map.ceilingKey(3):4
        System.out.println("map.floorKey(3):" + map.floorKey(3));     // <= 的最大值 map.floorKey(3):2

        System.out.println("map.higherKey(4):" + map.higherKey(4));  //map.higherKey(4):7
        System.out.println("map.lowerKey(4):" + map.lowerKey(4));    //map.lowerKey(4):2

        System.out.println("map.headMap(4):" + map.headMap(4)); //map.headMap(4):{1=1, 2=5} 小于
        System.out.println("map.tailMap(4):" + map.tailMap(4)); //map.tailMap(4):{4=3, 7=4, 10=0} 大于等于

        System.out.println("map.descendingMap():" + map.descendingMap());  //倒序遍历：{10=0, 7=4, 4=3, 2=5, 1=1}
    }
}




































