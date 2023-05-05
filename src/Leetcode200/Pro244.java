package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 17:16
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最短单词距离 II
 * 请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词，并返回列表中这两个单词之间的最短距离。
 *
 * 实现 WordDistanc 类:
 * WordDistance(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 worddict 中 word1 和 word2 之间的最短距离。
 *  
 *
 * 示例 1:
 * 输入:
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * 输出:
 * [null, 3, 1]
 *
 * 解释：
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // 返回 3
 * wordDistance.shortest("makes", "coding");    // 返回 1
 *  
 * 注意:
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在数组 wordsDict 中
 * word1 != word2
 * shortest 操作次数不大于 5000 
 */
public class Pro244 {

    /**
     * 与 243.最短单词距离 相比，此题需要自己构建数据结构，利用数据结构去解决问题
     * 哈希表 + 双指针
     */
    Map<String, List<Integer>> indicesMap;
    public Pro244(String[] wordsDict) {
        int index = 0; //字符串的下标
        indicesMap = new HashMap<String, List<Integer>>();
        for (String word : wordsDict) {
            indicesMap.putIfAbsent(word, new ArrayList<>());
            indicesMap.get(word).add(index++);
        }
    }

    public int shortest(String word1, String word2) {
        //list1 中的元素 和 list2 中的元素 均为递增排列 （原因：存储的是某word的下标，word出现的下标递增）
        List<Integer> list1 = indicesMap.get(word1);
        List<Integer> list2 = indicesMap.get(word2);
        int pos1 = 0 , pos2 = 0 , size1 = list1.size() , size2 = list2.size(), res = Integer.MAX_VALUE;
        while (pos1 < size1 && pos2 < size2){
            int index1 = list1.get(pos1);
            int index2 = list2.get(pos2);
            res = Math.min(Math.abs(index1 - index2),res);
            if (index1 < index2) pos1++;
            else pos2++;
        }
        return res;
    }
}
