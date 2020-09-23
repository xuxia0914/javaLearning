package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 1119 (简单291) 删去字符串中的元音
 * 给你一个字符串 S，请你删去其中的所有元音字母（ 'a'，'e'，'i'，'o'，'u'），并返回这个新字符串。
 *
 * 示例
 * 输入：“leetcodeisacommunityforcoders”
 * 输出：“ltcdscmmntyfrcdrs”
 *
 * 输入：“aeiou”
 * 输出：""
 *
 * 提示
 * S 仅由小写英文字母组成。
 * 1 <= S.length <= 1000
 */
public class RemoveVowelsFromAString {

    public String removeVowels(String S) {
        StringBuilder ans = new StringBuilder();
        for(char c : S.toCharArray()) {
            if(!(c=='a'||c=='o'||c=='e'||c=='i'||c=='u')) {
                ans.append(c);
            }
        }
        return ans.toString();
    }

}
