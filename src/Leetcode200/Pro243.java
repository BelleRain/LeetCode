package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 16:19
 */

/**
 * 最短单词距离
 * 给定一个字符串数组 wordDict 和两个已经存在于该数组中的不同的字符串 word1 和 word2 。返回列表中这两个单词之间的最短距离。
 *
 * 示例 1:
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * 输出: 3
 *
 * 示例 2:
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出: 1
 *  
 *
 * 提示:
 * 1 <= wordsDict.length <= 3 * 10^4
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] 由小写英文字母组成
 * word1 和 word2 在 wordsDict 中
 * word1 != word2
 */
public class Pro243 {

    /**
     * 1、申请两个指针 index1，index2，分别记录word1，和 word2 的位置 ，初始值均为 -1
     * 2、利用标志位 isChanged 记录 index1，index2本次是否更新，若更新并且 index1 >= 0 && index2 >= 0 则 求取 |index1 - index2|
     * 3、每次循环都要重置 isChanged 为 false ！！！
     * 4、利用res记录结果，每次更新取 这次的结果 与 上次结果的最小值
     * 5、返回res
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1, index2 = -1, index = 0,res = Integer.MAX_VALUE;
        for (String word : wordsDict) {
            boolean isChanged = false;
            if (word.equals(word1)){
                index1 = index;
                isChanged = true;
            }else if (word.equals(word2)){
                index2 = index;
                isChanged = true;
            }
            if (isChanged && index1 >= 0 && index2 >= 0) res = Math.min(res, Math.abs(index1 - index2));
            index++;
        }
        return res;
    }
}
