package cn.leetcode.xux.midium;

/**
 * 792. 匹配子序列的单词数
 * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
 *
 * 示例:
 * 输入:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * 输出: 3
 * 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
 *
 * 注意:
 * 所有在words和 S 里的单词都只由小写字母组成。
 * S 的长度在 [1, 50000]。
 * words 的长度在 [1, 5000]。
 * words[i]的长度在[1, 50]。
 */
public class NumberOfMatchingSubsequences {

    //TODO
    public int numMatchingSubseq(String S, String[] words) {
        if(S==null||S.length()==0||words==null||words.length==0) {
            return 0;
        }
        int n = words.length;
        int len = S.length();
        int[] idxs = new int[n];
        for(int i=0;i<len;i++) {
            for(int j=0;j<n;j++) {
                if(idxs[j]<words[j].length()&&S.charAt(i)==words[j].charAt(idxs[j])) {
                    idxs[j]++;
                }
            }
        }
        int result = 0;
        for(int i=0;i<n;i++) {
            if(idxs[i]==words[i].length()) {
                result++;
            }
        }
        return result;
    }

}
