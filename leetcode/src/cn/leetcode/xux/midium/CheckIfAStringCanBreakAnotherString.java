package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 1433. 检查一个字符串是否可以打破另一个字符串
 * 给你两个字符串 s1 和 s2 ，它们长度相等，请你检查是否存在一个 s1  的排列可以打破 s2 的一个排列，
 * 或者是否存在一个 s2 的排列可以打破 s1 的一个排列。
 * 字符串 x 可以打破字符串 y （两者长度都为 n ）需满足对于所有 i（在 0 到 n - 1 之间）都有
 * x[i] >= y[i]（字典序意义下的顺序）。
 *
 * 示例 1：
 * 输入：s1 = "abc", s2 = "xya"
 * 输出：true
 * 解释："ayx" 是 s2="xya" 的一个排列，"abc" 是字符串 s1="abc" 的一个排列，且 "ayx" 可以打破 "abc" 。
 *
 * 示例 2：
 * 输入：s1 = "abe", s2 = "acd"
 * 输出：false
 * 解释：s1="abe" 的所有排列包括："abe"，"aeb"，"bae"，"bea"，"eab" 和 "eba" ，s2="acd" 的所有排列包括："acd"，"adc"，"cad"，"cda"，"dac" 和 "dca"。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2 的排列能打破 s1 的排列。
 *
 * 示例 3：
 * 输入：s1 = "leetcodee", s2 = "interview"
 * 输出：true
 *
 * 提示：
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 10^5
 * 所有字符串都只包含小写英文字母。
 */
public class CheckIfAStringCanBreakAnotherString {

    public boolean checkIfCanBreak(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = chars1.length;
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        boolean flag = false;
        boolean asc = false;
        for(int i=0;i<n;i++) {
            if(flag) {
                if((asc&&chars1[i]>chars2[i])
                        ||(!asc&&chars1[i]<chars2[i])) {
                    return false;
                }
            }else if(chars1[i]!=chars2[i]) {
                flag = true;
                if(chars1[i]<chars2[i]) {
                    asc = true;
                }
            }
        }
        return true;
    }

}
