package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-09 14:30
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 至多包含两个不同字符的最长子串
 * 给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度。
 *  
 * 示例 1：
 * 输入：s = "eceba"
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 *
 * 示例 2：
 * 输入：s = "ccaabbb"
 * 输出：5
 * 解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
 *  
 *
 * 提示：
 * 1 <= s.length <= 105
 * s 由英文字母组成
 */
public class Pro159 {

    public static void main(String[] args) {
        Pro159 pro159 = new Pro159();
        System.out.println(pro159.lengthOfLongestSubstringTwoDistinct("aaccbbb"));
    }


    /**
     * 滑动窗口题解总结：
     * https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/solution/hua-dong-chuang-kou-zhen-di-jian-dan-yi-73bii/
     */

    /**
     * 题解链接：https://blog.csdn.net/itcodexy/article/details/112856256
     * 方法 1：滑动窗口 选取 left - right + 1 子串进行验证
     *      字符数量没有超过2，则记录此时的最大长度，并且 right++
     *      如果字符数量超过2，则left++
     *      使用map存放出现过的字符及次数
     * 复杂度分析
     * 时间复杂度：O(N) 其中 N 是输入串的字符数目。
     * 空间复杂度：O(1)，这是因为额外的空间只有 hashmap ，且它包含不超过 3 个元素。
     * @param s
     * @return
     */
    /*public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        //key：字符，value：字符数量
        Map<Character, Integer> map = new HashMap<>();
        //left：左指针，right：右指针，count：不同的字符数量，maxLength：最大子串的长度
        int left = 0 , right = 0 , count = 0 , maxLength = 0;
        while (right < len){
            //在map中获取该字符出现的次数（不存在则0），次数+1
            int rightNumber = map.getOrDefault(chars[right], 0) + 1;
            map.put(chars[right],rightNumber); //更新map
            if (rightNumber == 1) count++; //该字符第一次出现
            if (count <= 2) maxLength = Math.max(maxLength, right - left + 1);
            right++; //右指针
            //如果 不同的字符数量>2,就一直移动左指针 left++ ，直至 count == 2为止
            //left所指向的字符一定是出现过的，因为是由right遍历累加进去的
            while (count > 2){
                //左侧移除一个字符，在map中获取该字符出现的次数 -1
                int leftNumber = map.get(chars[left]) - 1;
                map.put(chars[left], leftNumber);
                if (leftNumber == 0) count--;
                left++;
            }
        }
        return maxLength;
    }*/

    /**
     * 方法二：利用数组进行优化
     *      改进1：考虑ASCII码表中的128个字符，可以使用数组代替map，存放每个字符出现的次数
     *      改进2：滑动窗口，只会扩大或者平移（我们需要取的就是最大窗口长度）
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int length = s.length();
        int[] map = new int[128];
        //count 为不同字符的数量
        int right = 0 , left = 0 , count = 0;
        while (right < length){
            //每次都会移动right，当count > 2时，每次都会移动left，left和right同时移动，窗口变化过程中扩大或平移
            if (map[s.charAt(right++)]++ == 0) count++; //右侧新字符进入窗口
            if (count > 2){ //如果新字符进入窗口后 不同字符数量大于2，则左侧窗口也再向右滑动一个
                if (--map[s.charAt(left++)] == 0) count--;
            }
        }
        //最终的结果即为最大窗口
        return right - left;
    }

}















