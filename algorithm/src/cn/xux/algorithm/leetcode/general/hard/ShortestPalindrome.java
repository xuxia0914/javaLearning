package cn.xux.algorithm.leetcode.general.hard;

/**
 * 214. 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
 * 找到并返回可以用这种方式转换的最短回文串。
 *
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 *
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 */
public class ShortestPalindrome {

    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("aba"));
        System.out.println(new ShortestPalindrome().shortestPalindrome("aacecaaa"));
    }

    //Manacher
    public String shortestPalindrome(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        s = init(new StringBuilder(s).reverse().toString());
        int len = s.length();
        //dp[i]表示以第i个字符为中心的最大回文串的半径
        int[] dp = new int[len];
        int maxCenter = 0;
        int maxRight = 0;
        for(int i=1;i<len;i++) {
            if(i<maxRight) {
                dp[i] = Math.min(dp[2*maxCenter-i], maxRight-i);
            }
            while(i-dp[i]-1>=0&&i+dp[i]+1<len&&s.charAt(i-dp[i]-1)==s.charAt(i+dp[i]+1)) {
                dp[i]++;
            }
            if(i+dp[i]>maxRight) {
                maxRight = i+dp[i];
                maxCenter = i;
            }
            if(maxRight==len-1) {
                break;
            }
        }
        StringBuilder left = new StringBuilder();
        for(int i=0;i<2*maxCenter-maxRight;i++) {
            if(s.charAt(i)!='#') {
                left.append(s.charAt(i));
            }
        }
        StringBuilder center = new StringBuilder();
        for(int i=2*maxCenter-maxRight;i<len;i++) {
            if(s.charAt(i)!='#') {
                center.append(s.charAt(i));
            }
        }
        StringBuilder right = new StringBuilder(left).reverse();
        left.append(center).append(right);
        return left.reverse().toString();
    }

    private String init(String s) {
        StringBuilder sb = new StringBuilder("#");
        for(char c : s.toCharArray()) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }

    //TLE
    public String shortestPalindrome1(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        int end = s.length();
        for(;end>0;end--) {
            String curr = s.substring(0, end);
            if(new StringBuilder(curr).reverse().toString().equals(curr)) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder(s.substring(end));
        sb.reverse();
        sb.append(s);
        return sb.toString();
    }

}
