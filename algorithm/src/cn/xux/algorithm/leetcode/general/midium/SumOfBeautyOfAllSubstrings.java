package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 * 示例 1：
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，
 * 每一个字符串的美丽值都为 1 。
 *
 * 示例 2：
 * 输入：s = "aabcbaa"
 * 输出：17
 *
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
public class SumOfBeautyOfAllSubstrings {

    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        int[] cnts = new int[26];
        for(int l=0;l<n-2;l++) {
            Arrays.fill(cnts, 0);
            for(int r=l;r<n;r++) {
                int curr = s.charAt(r)-'a';
                cnts[curr]++;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int cnt : cnts) {
                    if(cnt!=0) {
                        min = Math.min(min, cnt);
                        max = Math.max(max, cnt);
                    }
                }
                ans += max-min;
            }
        }
        return ans;
    }

}
