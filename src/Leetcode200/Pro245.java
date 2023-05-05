package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 20:35
 */

import sun.rmi.runtime.NewThreadAction;

/**
 * 最短单词距离 III
 * 给定一个字符串数组 wordsDict 和两个字符串 word1 和 word2 ，返回列表中这两个单词之间的最短距离。
 * 注意：word1 和 word2 是有可能相同的，并且它们将分别表示为列表中 两个独立的单词 。
 *
 * 示例 1：
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出：1
 *
 * 示例 2：
 * 输入：wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * 输出：3
 *  
 * 提示：
 * 1 <= wordsDict.length <= 10^5
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 都在 wordsDict 中
 *
 */
public class Pro245 {

    public static void main(String[] args) {
        Pro245 pro245 = new Pro245();
        String[] w = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(pro245.shortestWordDistance(w, "coding", "makes"));
    }

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int index = 0, index1 = -1, index2 = -1, pre = -1, res = Integer.MAX_VALUE;
        if (word1.equals(word2)){
            for (String word : wordsDict) {
                if (word.equals(word1)) {
                    if (pre >= 0) res = Math.min(res, Math.abs(index - pre));
                    pre = index;
                }
                index++;
            }
        }else{
            for (String word : wordsDict) {
                boolean isChanged = false;
                if (word.equals(word1)) {
                    index1 = index;
                    isChanged = true;
                }else if (word.equals(word2)){
                    index2 = index;
                    isChanged = true;
                }
                if (isChanged && index1 >= 0 && index2 >= 0) res = Math.min(res, Math.abs(index1 - index2));
                index++;
            }
        }
        return res;
    }
}
