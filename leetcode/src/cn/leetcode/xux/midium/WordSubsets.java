package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 916. 单词子集
 * 我们给出两个单词数组 A 和 B。每个单词都是一串小写字母。
 * 现在，如果 b 中的每个字母都出现在 a 中，包括重复出现的字母，那么称单词 b 是单词 a 的子集。
 * 例如，“wrr” 是 “warrior” 的子集，但不是 “world” 的子集。
 * 如果对 B 中的每一个单词 b，b 都是 a 的子集，那么我们称 A 中的单词 a 是通用的。
 * 你可以按任意顺序以列表形式返回 A 中所有的通用单词。
 *
 * 示例 1：
 * 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * 输出：["facebook","google","leetcode"]
 *
 * 示例 2：
 * 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * 输出：["apple","google","leetcode"]
 *
 * 示例 3：
 * 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * 输出：["facebook","google"]
 *
 * 示例 4：
 * 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * 输出：["google","leetcode"]
 *
 * 示例 5：
 * 输入：A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * 输出：["facebook","leetcode"]
 *
 * 提示：
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] 和 B[i] 只由小写字母组成。
 * A[i] 中所有的单词都是独一无二的，也就是说不存在 i != j 使得 A[i] == A[j]。
 */
public class WordSubsets {

    public static void main(String[] args) {
        System.out.println(new WordSubsets().wordSubsets(
                new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"e","o"}
        ));
    }

    public List<String> wordSubsets(String[] A, String[] B) {
        int[] cnts = new int[26];
        for(String b : B) {
            int[] tmp = new int[26];
            for(char c : b.toCharArray()) {
                tmp[c-'a']++;
            }
            for(int i=0;i<26;i++) {
                cnts[i] = Math.max(cnts[i], tmp[i]);
            }
        }
        List<String> result = new LinkedList<>();
        for(String a : A) {
            int[] tmp = new int[26];
            for(char c : a.toCharArray()) {
                tmp[c-'a']++;
            }
            boolean flag = true;
            for(int i=0;i<26;i++) {
                if(cnts[i]>tmp[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result.add(a);
            }
        }
        return result;
    }

}
