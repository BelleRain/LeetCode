package Top100;

/**
 * @author mxy
 * @create 2022-12-09 15:04
 */

import sun.text.normalizer.Trie;

import java.util.HashSet;
import java.util.Set;

/**
 * 208. 实现 Trie (前缀树)      链接：https://leetcode.cn/problems/implement-trie-prefix-tree
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地 存储和检索 字符串数据集中的 键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *  
 *
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *  
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 *
 */
public class Top208 {

    /**
     * Trie Tree 字典树
     * 题解链接：https://leetcode.cn/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/
     *          https://leetcode.cn/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
     */
    class TrieNode{
        private boolean isEnd;
        TrieNode[] next;

        public TrieNode(){
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    private TrieNode root;
    public Top208() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()){
            if (node.next[c - 'a'] == null){
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()){
            node = node.next[c - 'a'];
            if (node == null){
                return false;
            }
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.next[c - 'a'];
            if (node == null){
                return false;
            }
        }
        return true;
    }



    //执行用时： 330 ms , 在所有 Java 提交中击败了 5.12% 的用户
    //内存消耗： 49.8 MB , 在所有 Java 提交中击败了 76.41% 的用户
    /*Set<String> set;
    public Top208() {
        set = new HashSet<>();
    }

    public void insert(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.contains(word);
    }

    public boolean startsWith(String prefix) {
        int length = prefix.length();
        for (String s : set) {
            if (s.length() >= length){
                String str = s.substring(0, length);
                if (str.equals(prefix)) return true;
            }
        }
        return false;
    }*/
}
