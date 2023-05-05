package Swordoffer.SearchAlgorithms;

/**
 * @author mxy
 * @create 2022-09-20 19:01
 */

import java.util.*;

/**
 * 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 示例 1:  输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:  输入：s = ""
 * 输出：' '
 */
public class Offer50 {

    public static void main(String[] args) {
        Offer50 offer50 = new Offer50();
        char res = offer50.firstUniqChar("leetcode");
        //char res = offer50.firstUniqChar("");
        System.out.println(res);

    }

    /**
     * 解法一： 不推荐
     * 执行用时： 29 ms , 在所有 Java 提交中击败了 17.47% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 52.85% 的用户
     * @param s
     * @return
     */
    //利用hashmap存放字符和对应的次数
    //注意：直接存入字符，则map不会按照 原子符串 的顺序存放，故第一个 value为1 的key也不是原字符串中 第一个只出现一次的字符
    //public char firstUniqChar(String s) {
    //    HashMap<Character, Integer> map = new HashMap<>();
    //    for (int i = 0; i < s.length(); i++) {
    //        char key = s.charAt(i);
    //        //如果没有放入
    //        if (!map.containsKey(key)){
    //            int count = 0;
    //            map.put(key,count++);
    //        }
    //        //之前已放入
    //        map.put(key,map.get(key) + 1);
    //    }
    //    //遍历原字符串
    //    for (int i = 0; i < s.length(); i++) {
    //       if (map.get(s.charAt(i)) == 1){
    //           return s.charAt(i);
    //       }
    //    }
    //    return ' ';
    //}

    /**
     * 解法二：利用字符串相关函数
     * @param s
     * @return
     */
    //public char firstUniqChar(String s) {
    //    if(s=="")
    //        return ' ';
    //    int res = -1;
    //    for (char ch = 'a'; ch <= 'z'; ch++) {
    //        int index = s.indexOf(ch);
    //        if (index != -1 && index == s.lastIndexOf(ch)) {
    //            res = (res == -1 || res > index) ? index : res;
    //        }
    //    }
    //    if(res!=-1)
    //        return s.charAt(res);
    //    else
    //        return ' ';
    //}

    /**
     * 解法三：哈希表 方法一的简略写法 ，用boolean来标志 比 integer 更简洁，合理
     *
     * 复杂度分析： 时间复杂度 O(N) ： N为字符串 s 的长度；需遍历 s 两轮，使用 O(N)；HashMap 查找操作的复杂度为 O(1)；
     * 空间复杂度 O(1)： 由于题目指出 s 只包含小写字母，因此最多有 26 个不同字符，HashMap 存储需占用 O(26) = O(1)的额外空间。
     */
    //public char firstUniqChar(String s) {
    //    HashMap<Character, Boolean> dic = new HashMap<>();
    //    char[] sc = s.toCharArray();
    //    for(char c : sc)
    //        dic.put(c, !dic.containsKey(c));
    //    for(char c : sc)
    //        if(dic.get(c)) return c;
    //    return ' ';
    //}

    /**
     * 解法四：有序哈希表  LinkedHashMap
     *  在哈希表的基础上，有序哈希表中的键值对是 按照插入顺序排序 的。基于此，可通过遍历有序哈希表，实现搜索首个 “数量为 1的字符”。
     *  哈希表是 去重 的，即哈希表中键值对数量  ≤ 字符串 s 的长度。
     *  因此，相比于方法三，方法四减少了第二轮遍历的循环次数。当字符串很长（重复字符很多）时，方法四则效率更高。
     *  Java 使用 LinkedHashMap 实现有序哈希表
     */
    public char firstUniqChar(String s) {
        Map<Character, Boolean> dic = new LinkedHashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            dic.put(c, !dic.containsKey(c));
        for(Map.Entry<Character, Boolean> d : dic.entrySet()){
            if(d.getValue()) return d.getKey();
            //System.out.println(d);
        }
        return ' ';
    }

    /**
     * 解法五：使用哈希表存储索引
     * 思路与算法 ：我们可以对方法一进行修改，使得第二次遍历的对象从字符串变为哈希映射。
     * 具体地，对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出现一次）或者 −1（如果该字符出现多次）。
     * 当我们第一次遍历字符串时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个键值对加入哈希映射中，
     * 否则我们将 c 在哈希映射中对应的值修改为 -1。
     * 在第一次遍历结束后，我们只需要再遍历一次哈希映射中的所有值，找出其中不为 -1 的 最小值，
     * 即为第一个不重复字符的索引，然后返回该索引对应的字符。如果哈希映射中的所有值均为 -1，我们就返回空格。
     */
    //public char firstUniqChar(String s) {
    //    Map<Character, Integer> position = new HashMap<Character, Integer>();
    //    int n = s.length();
    //    for (int i = 0; i < n; ++i) {
    //        char ch = s.charAt(i);
    //        if (position.containsKey(ch)) {
    //            position.put(ch, -1);
    //        } else {
    //            position.put(ch, i);
    //        }
    //    }
    //    int first = n;
    //    for (Map.Entry<Character, Integer> entry : position.entrySet()) {
    //        int pos = entry.getValue();
    //        if (pos != -1 && pos < first) {
    //            first = pos;
    //        }
    //    }
    //    return first == n ? ' ' : s.charAt(first);
    //}

    /**
     * 解法六：利用队列
     * 思路与算法：  我们也可以借助队列找到第一个不重复的字符。队列具有「先进先出」的性质，因此很适合用来找出第一个满足某个条件的元素。
     * 具体地，我们使用与方法五相同的哈希映射，并且使用一个额外的队列，按照顺序存储每一个字符以及它们第一次出现的位置。
     * 当我们对字符串进行遍历时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个二元组放入队尾，
     * 否则我们就需要检查队列中的元素是否都满足「只出现一次」的要求，即我们不断地根据哈希映射中存储的值（是否为 −1）选择弹出队首的元素，
     * 直到队首元素「真的」只出现了一次或者队列为空。  在遍历完成后，如果队列为空，说明没有不重复的字符，返回空格，否则队首的元素即为第一个不重复的字符以及其索引的二元组。
     * 小贴士 ：在维护队列时，我们使用了「延迟删除」这一技巧。也就是说，即使队列中有一些字符出现了超过一次，但它只要不位于队首，
     * 那么就不会对答案造成影响，我们也就可以不用去删除它。只有当它前面的所有字符被移出队列，它成为队首时，我们才需要将它移除。
     */
    //public char firstUniqChar(String s) {
    //    Map<Character, Integer> position = new HashMap<Character, Integer>();
    //    Queue<Pair> queue = new LinkedList<Pair>();
    //    int n = s.length();
    //    for (int i = 0; i < n; ++i) {
    //        char ch = s.charAt(i);
    //        if (!position.containsKey(ch)) {
    //            position.put(ch, i);
    //            queue.offer(new Pair(ch, i));
    //        } else {
    //            position.put(ch, -1);
    //            while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
    //                queue.poll();
    //            }
    //        }
    //    }
    //    return queue.isEmpty() ? ' ' : queue.poll().ch;
    //}
    //
    //class Pair {
    //    char ch;
    //    int pos;
    //
    //    Pair(char ch, int pos) {
    //        this.ch = ch;
    //        this.pos = pos;
    //    }
    //}

}





































