package cn.xux.algorithm.leetcode.vip.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 1065. 字符串的索引对
 * 给出 字符串 text 和 字符串列表 words, 返回所有的索引对 [i, j] 使得在索引对范围内的子字符串 text[i]…text[j]（包括 i 和 j）属于字符串列表 words。
 *
 * 示例 1:
 * 输入: text = "thestoryofleetcodeandme",
 * 		words = ["story","fleet","leetcode"]
 * 输出: [[3,7],[9,13],[10,17]]
 *
 * 示例 2:
 * 输入: text = "ababa", words = ["aba","ab"]
 * 输出: [[0,1],[0,2],[2,3],[2,4]]
 * 解释:  注意，返回的配对可以有交叉，比如，"aba" 既在 [0,2] 中也在 [2,4] 中
 *
 * 提示:
 * 所有字符串都只包含小写字母。
 * 保证 words 中的字符串无重复。
 * 1 <= text.length <= 100
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 50
 * 按序返回索引对 [i,j]（即，按照索引对的第一个索引进行排序，
 * 			当第一个索引对相同时按照第二个索引对排序）。
 */
public class IndexPairsOfAString {

    public static void main(String[] args) {
        IndexPairsOfAString ip = new IndexPairsOfAString();
        ip.indexPairs("thestoryofleetcodeandme", new String[]{"story","fleet","leetcode"});
        ip.indexPairs("ababa", new String[]{"aba","ab"});
    }

    public int[][] indexPairs(String text, String[] words) {
        if(text==null||text.length()==0) {
            return new int[0][2];
        }
        Trie root = new Trie();
        for(String word : words) {
            insert(word, root);
        }
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<text.length();i++) {
            Trie curr = root;
            for(int j=i;j<text.length();j++) {
                int num = text.charAt(j)-'a';
                if(curr.children[num]==null) {
                    break;
                }else {
                    curr = curr.children[num];
                    if(curr.isWord) {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

    private void insert(String word, Trie root) {
        Trie curr = root;
        for(char c : word.toCharArray()) {
            if(curr.children[c-'a']==null) {
                curr.children[c-'a'] = new Trie();
            }
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
    }

    class Trie {
        Trie[] children;
        boolean isWord;

        Trie() {
            children = new Trie[26];
            isWord = false;
        }
    }

}
