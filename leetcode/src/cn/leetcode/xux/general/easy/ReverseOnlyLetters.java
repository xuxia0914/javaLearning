package cn.leetcode.xux.general.easy;

/**
 * 917. 仅仅反转字母
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 * 提示：
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S 中不包含 \ or "
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        if(S==null||S.length()==0) {
            return S;
        }
        char[] chars = S.toCharArray();
        int len = chars.length;
        int left = 0;
        int right = len-1;
        while(left<right) {
            while(left<right&&!(chars[left]>='a'&&chars[left]<='z')&&!(chars[left]>='A'&&chars[left]<='Z')) {
                left++;
            }
            while(left<right&&!(chars[right]>='a'&&chars[right]<='z')&&!(chars[right]>='A'&&chars[right]<='Z')) {
                right--;
            }
            if(left<right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
            }
            left++;
            right--;
        }
        return new String(chars);
    }

}
