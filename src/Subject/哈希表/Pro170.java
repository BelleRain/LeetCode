package Subject.哈希表;

/**
 * @author mxy
 * @create 2022-11-10 11:46
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和 III - 数据结构设计
 * 设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。
 *
 * 实现 TwoSum 类：
 *
 * TwoSum() 使用空数组初始化 TwoSum 对象
 * void add(int number) 向数据结构添加一个数 number
 * boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 true ；否则，返回 false 。
 *  *  
 *
 * 示例：
 * 输入：
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * 输出：
 * [null, null, null, null, true, false]
 *
 * 解释：
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4，返回 true
 * twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false
 *  
 *
 * 提示：
 * -10^5 <= number <= 10^5
 * -2^31 <= value <= 2^31 - 1
 * 最多调用 10^4 次 add 和 find
 */
public class Pro170 {

    public static void main(String[] args) {
        Pro170 pro170 = new Pro170();
        pro170.add(1);
        pro170.add(3);
        pro170.add(5);
        pro170.find(4);
        pro170.find(7);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/two-sum-iii-data-structure-design/solution/liang-shu-zhi-he-iii-shu-ju-jie-gou-she-ji-by-leet/
     */

    /**
     * 方法一：双指针法
     * 要求：列表有序
     * 我们会发现，add(number) 函数将被频繁调用，而 find(value) 将不被那么频繁调用。
     * 这样的使用模式下，意味着我们应该减少 add(number) 函数的时间消耗，因而我们是在 find(value) 对列表进行排序，而不是在 add(number)。
     * 在哪个函数进行排序，都是可行的，只是对应该使用模式下在 add(number) 下进行排序就不是最佳的方案了。
     * 并且，我们在 find(value) 中是按需排序，也就是当列表更新时，才进行排序。
     * 复杂度分析：
     *      时间复杂度：
     *          add(number)： O(1)
     *          find(value)：O(N⋅log(N))，在最坏的情况下，我们需要对列表进行排序和遍历整个列表，这需要 O(N⋅log(N)) 和 O(N) 的时间。因此总的时间复杂度为 O(N⋅log(N))。
     *      空间复杂度：O(N)，其中 NN 指的是列表中的元素个数。
     */
/*    List<Integer> nums;
    boolean is_sorted;
    public Pro170() {
        nums = new ArrayList<>();
        is_sorted = false;
    }

    public void add(int number) {
        nums.add(number);
        is_sorted = false;
    }

    public boolean find(int value) {
        if (!is_sorted){
            Collections.sort(nums);
            is_sorted = true;
        }
        int left = 0 , right = nums.size() - 1;
        while (left < right){
            if (nums.get(left) + nums.get(right) > value) right--;
            else if (nums.get(left) + nums.get(right) < value) left++;
            else return true;
        }
        return false;
    }*/

    /**
     * 方法二：哈希表
     * 首先初始化一个哈希表。
     * 在 add(number) 函数中：在哈希表中添加 number 到 number 频数之间的映射关系。
     * 在 find(value) 函数中：遍历哈希表，对于每个键值（number），我们检查哈希表中是否存在 value - number。如果存在，我们终止循环并返回结果。
     * 当 number = value - number 时，在哈希表中 number 对应的值应大于 2。（证明list中有两个相同的值）
     * 复杂度分析
     *      时间复杂度：
     *           在 add(number) 中： O(1)，只进行了哈希表的更新。
     *           在 find(value) 中： O(N)，其中 N 指的是哈希表中键值对的数量。在最坏的情况下，会遍历整个表。
     *      空间复杂度：O(N)，哈希表所使用的空间大小。
     */
    Map<Integer,Integer> nums;
    public Pro170() {
        nums = new HashMap<>();
    }

    public void add(int number) {
        nums.put(number, nums.getOrDefault(number,0) + 1);
    }

    public boolean find(int value) {
        for(Map.Entry<Integer,Integer> entry : nums.entrySet()){
            Integer complement = value - entry.getKey();
            if (complement != entry.getKey()){
                if (nums.containsKey(complement)) return true;
            }else {
                if (entry.getValue() >= 2) return true;
            }
        }
        return false;
    }
}
