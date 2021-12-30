package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1044. 最长重复子串
 * 给你一个字符串 s ，考虑其所有 重复子串 ：
 * 即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 *
 * 返回 任意一个 可能具有最长长度的重复子串。
 * 如果 s 不含重复子串，那么答案为 "" 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 *
 * 输入：s = "abcd"
 * 输出：""
 *
 *
 * 提示：
 *
 * 2 <= s.length <= 3 * 104
 * s 由小写英文字母组成
 */
public class LongestDuplicateSubstring {

    // 二分+字符串hash
    public String longestDupSubstring(String s) {
        int n = s.length();
        int left = 0;
        int right = n;
        String ans = "";
        while(left<right) {
            int mid = (left+right+1)>>1;
            int P = 131313;
            long[] p = new long[n+1];
            p[0] = 1;
            long[] h = new long[n+1];
            for(int i=1;i<=n;i++) {
                h[i] = h[i-1]*P+s.charAt(i-1);
                p[i] = p[i-1]*P;
            }
            Set<Long> set = new HashSet<>();
            boolean flag = false;
            for(int i=0;n-i>=mid;i++) {
                int j = i+mid;
                long hash = h[j]-h[i]*p[mid];
                if(!set.add(hash)) {
                    ans = s.substring(i, i+mid);
                    left = mid;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                right = mid-1;
            }
        }
        return ans;
    }

    // 二分+hash      MLE
    public String longestDupSubstring1(String s) {
        int n = s.length();
        int left = 0;
        int right = n;
        String ans = "";
        while(left<right) {
            int mid = (left+right+1)>>1;
            Set<String> set = new HashSet<>();
            boolean flag = false;
            for(int i=0;n-i>=mid;i++) {
                String curr = s.substring(i, i+mid);
                if(!set.add(curr)) {
                    ans = curr;
                    left = mid;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                right = mid-1;
            }
        }
        return ans;
    }

}
