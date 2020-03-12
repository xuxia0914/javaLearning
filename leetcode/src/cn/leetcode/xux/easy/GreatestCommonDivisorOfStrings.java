package cn.leetcode.xux.easy;

/**
 * 1071. 字符串的最大公因子
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 * 示例 1：
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 *
 * 示例 2：
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 *
 * 示例 3：
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *
 * 提示：
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 */
public class GreatestCommonDivisorOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        if(str1==null||str1.length()==0||str2==null||str2.length()==0) {
            return "";
        }
        if(!(str1+str2).equals(str2+str1)) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    public int gcd(int m, int n) {
        if(m<n) {
            return gcd(n, m);
        }
        int tmp;
        while(m%n!=0) {
            tmp = m;
            m = n;
            n = tmp%n;
        }
        return n;
    }

    public static void main(String[] args) {
        GreatestCommonDivisorOfStrings gc = new GreatestCommonDivisorOfStrings();
        System.out.println(gc.gcdOfStrings("ABCABC", "ABC"));
        System.out.println(gc.gcdOfStrings("ABABAB", "AB"));
    }

}
