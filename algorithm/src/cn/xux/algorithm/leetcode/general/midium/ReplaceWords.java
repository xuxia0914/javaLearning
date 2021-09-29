package cn.xux.algorithm.leetcode.general.midium;

import java.util.List;

/**
 * 648. 单词替换
 * 在英语中，我们有一个叫做 词根(root)的概念，
 * 它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 *
 * 示例 1:
 * 输入: dict(词典) = ["cat", "bat", "rat"]
 * sentence(句子) = "the cattle was rattled by the battery"
 * 输出: "the cat was rat by the bat"
 *
 * 注:
 * 输入只包含小写字母。
 * 1 <= 字典单词数 <=1000
 * 1 <=  句中词语数 <= 1000
 * 1 <= 词根长度 <= 100
 * 1 <= 句中词语长度 <= 1000
 */
public class ReplaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for(String d : dictionary) {
            Trie curr = root;
            for(char c : d.toCharArray()) {
                if(curr.children[c-'a']==null) {
                    curr.children[c-'a'] = new Trie();
                }
                curr = curr.children[c-'a'];
                if(curr.val!=null) {
                    break;
                }
            }
            curr.val = curr.val==null?d:curr.val;
        }
        String[] words = sentence.split(" ");
        for(int i=0;i<words.length;i++) {
            Trie curr = root;
            for(char c : words[i].toCharArray()) {
                if(curr.children[c-'a']==null) {
                    break;
                }
                curr = curr.children[c-'a'];
                if(curr.val!=null) {
                    words[i] = curr.val;
                    break;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(words[0]);
        for(int i=1;i<words.length;i++) {
            ans.append(' ').append(words[i]);
        }
        return ans.toString();
    }

    class Trie {
        Trie[] children = new Trie[26];
        String val = null;
    }

    public String replaceWords1(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            String currRoot = "";
            for(String root : dict) {
                if(word.startsWith(root)&&(currRoot.equals("")||(!currRoot.equals("")&&root.length()<currRoot.length()))) {
                    currRoot = root;
                }
            }
            if(!currRoot.equals("")) {
                words[i] = currRoot;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(words[0]);
        for(int i=1;i<words.length;i++) {
            sb.append(" ").append(words[i]);
        }
        return sb.toString();
    }

}
