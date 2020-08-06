package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 890. 查找和替换模式
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * 返回 words 中与给定模式匹配的单词列表。
 * 你可以按任何顺序返回答案。
 *
 * 示例：
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 *
 * 提示：
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        if(words==null||words.length==0
                ||pattern==null||pattern.length()==0) {
            return result;
        }
        for(String word : words) {
            if(isMatch(word, pattern)) {
                result.add(word);
            }
        }
        return result;
    }

    public boolean isMatch(String word, String pattern) {
        if(word==null||word.length()!=pattern.length()) {
            return false;
        }
        int[] mapping1 = new int[26];
        int[] mapping2 = new int[26];
        for(int i=0;i<word.length();i++) {
            char c1 = word.charAt(i);
            char c2 = pattern.charAt(i);
            if(mapping1[c1-'a']==0&&mapping2[c2-'a']==0) {
                mapping1[c1-'a'] = c2;
                mapping2[c2-'a'] = c1;
            }else if(mapping1[c1-'a']==0||mapping2[c2-'a']==0
                    ||mapping1[c1-'a']!=c2||mapping2[c2-'a']!=c1) {
                return false;
            }
        }
        return true;
    }

}
