package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 266. 回文排列
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 *
 * 示例 1：
 * 输入:
 * "code"
 * 输出: false
 *
 * 示例 2：
 * 输入:
 * "aab"
 * 输出: true
 *
 * 示例 3：
 * 输入:
 * "carerac"
 * 输出: true
 */
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {
        if(s==null) {
            return false;
        }
        int len = s.length();
        if(len<2) {
            return true;
        }
        int[] array = new int[26];
        for(int i=0;i<len;i++) {
            array[s.charAt(i)-'a']++;
        }
        boolean flag = false;
        for(int i : array) {
            if(i%2==1) {
                if(!flag) {
                    flag = true;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePermutation pp = new PalindromePermutation();
        System.out.println(pp.canPermutePalindrome("code"));
        System.out.println(pp.canPermutePalindrome("aab"));
        System.out.println(pp.canPermutePalindrome("carerac"));
    }

}
