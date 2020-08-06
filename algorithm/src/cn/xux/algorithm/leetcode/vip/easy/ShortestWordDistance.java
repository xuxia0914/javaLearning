package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 243.最短单词距离
 *
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * 输入: word1 =“coding”, word2 =“practice”
 *
 * 输出: 3
 *
 * 输入: word1 ="makes", word2 ="coding"
 *
 * 输出: 1
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int res = words.length-1;
        for(int i=0;i<words.length;i++) {
            if(words[i].equals(word1)) {
                idx1 = i;
            }
            if(words[i].equals(word2)) {
                idx2 = i;
            }
            if(idx1!=-1&&idx2!=-1) {
                res = Math.min(res, Math.abs(idx1-idx2));
            }
        }
        return Math.abs(idx1-idx2);
    }

}
