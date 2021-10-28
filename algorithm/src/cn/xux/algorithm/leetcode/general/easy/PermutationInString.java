package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * 如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if(s1==null||s2==null||s1.length()>s2.length()) {
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int[] array1 = new int[26];
        int[] array2 = new int[26];
        for(int i=0;i<len1;i++) {
            array1[s1.charAt(i)-'a']++;
            array2[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(array1, array2)) {
            return true;
        }
        for(int i=0;i<=len2-len1;i++) {
            array2[s2.charAt(i)-'a']--;
            array2[s2.charAt(i+len1)-'a']++;
            if(Arrays.equals(array1, array2)) {
                return true;
            }
        }
        return false;
    }

}
