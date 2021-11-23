package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * 859. 亲密字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 *
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 *
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * 示例 2：
 *
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * 示例 3：
 *
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * 示例 4：
 *
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length, goal.length <= 2 * 104
 * s 和 goal 由小写英文字母组成
 */
public class BuddyStrings {
    public boolean buddyStrings(String s, String goal) {
        int n = s.length();
        boolean[] exits = new boolean[26];
        if(s.equals(goal)) {
            for(char c : s.toCharArray()) {
                if(exits[c-'a']) {
                    return true;
                }
                exits[c-'a'] = true;
            }
            return false;
        }else {
            // 是不是已经出现过不相同的字符
            boolean flag1 = false;
            // 第一个不相同的字符出现的位置
            int dif = -1;
            // 是不是已经交换过了
            boolean flag2 = false;
            for(int i=0;i<n;i++) {
                char c1 = s.charAt(i);
                char c2 = goal.charAt(i);
                if(c1!=c2) {
                    if(flag2) {
                        return false;
                    }else if(flag1) {
                        if(c1 != goal.charAt(dif)||c2!=s.charAt(dif)) {
                            return false;
                        }else {
                            flag2 = true;
                        }
                    }else {
                        flag1 = true;
                        dif = i;
                    }
                }
            }
            return flag2;
        }
    }

}
