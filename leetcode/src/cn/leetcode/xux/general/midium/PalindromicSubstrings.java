package cn.leetcode.xux.general.midium;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 * 输入的字符串长度不会超过1000。
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        if(s==null||s=="") {
            return 0;
        }
        int len = s.length();
        int result = 0;
        for(int i=0;i<len;i++) {
            for(int j=0;i-j>=0&&i+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        for(int i=0;i>=0&&i+1<len;i++) {
            for(int j=0;i-j>=0&&i+1+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+1+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    //Manacher算法
    public int countSubstrings1(String s) {
        if(s==null||s=="") {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for(char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        String newStr = sb.toString();
        int len = newStr.length();
        int[] dp = new int[len];
        dp[0] = 0;
        int maxMid = 0;
        int maxRight = 0;
        int result = s.length();
        for(int i=1;i<len;i++) {
            if(i<maxRight) {
                dp[i] = Math.min(dp[2*maxMid-i], maxRight-i);
            }
            int j=dp[i]+1;
            while(i+j<len&&i-j>=0) {
                if(newStr.charAt(i+j)==newStr.charAt(i-j)) {
                    dp[i]++;
                    j++;
                }else {
                    break;
                }
            }
            if(i+dp[i]>maxRight) {
                maxMid = i;
                maxRight = i+dp[i];
            }
            result += dp[i]/2;
        }
        return result;
    }

    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings1("aaa"));
        System.out.println(ps.countSubstrings1("abc"));
    }

}
