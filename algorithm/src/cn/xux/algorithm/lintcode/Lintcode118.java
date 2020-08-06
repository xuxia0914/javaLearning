package cn.xux.algorithm.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 不同的子序列
 * 中文English
 * 给定字符串 S 和 T, 计算 S 的所有子序列中有多少个 T.
 * 子序列字符串是原始字符串删除一些(或零个)字符之后得到的字符串,
 * 并且要求剩下的字符的相对位置不能改变. (比如 "ACE" 是 ABCDE 的一个子序列, 而 "AEC" 不是)
 *
 * 样例
 * 样例 1:
 * 输入: S = "rabbbit", T = "rabbit"
 * 输出: 3
 * 解释: 你可以删除 S 中的任意一个 'b', 所以一共有 3 种方式得到 T.
 *
 * 样例 2:
 * 输入: S = "abcd", T = ""
 * 输出: 1
 * 解释: 只有删除 S 中的所有字符这一种方式得到 T
 *
 * 挑战
 * 挑战时间复杂度 O(n^2), 空间复杂度 O(n) 的算法.
 * 如果你不知道如何优化空间, O(n^2) 的空间复杂度也是可以通过的.
 */
public class Lintcode118 {

    /**
     * @param s: A string
     * @param t: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String s, String t) {
        // write your code here
        int lenT = t.length();
        List<Integer>[] cnts = new List[128];
        for(int i=0;i<lenT;i++) {
            char c = t.charAt(i);
            if(cnts[c]==null) {
                cnts[c] = new ArrayList<>();
            }
            cnts[c].add(i);
        }
        int[] dp = new int[lenT+1];
        dp[0] = 1;
        for(char c : s.toCharArray()) {
            if(cnts[c]!=null) {
                List<Integer> list = cnts[c];
                for(int i=list.size()-1;i>=0;i--) {
                    dp[list.get(i)+1] += dp[list.get(i)];
                }
            }
        }
        return dp[lenT];
    }

}
