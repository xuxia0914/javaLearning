package cn.leetcode.xux.easy;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if(s==null) {
            return false;
        }
        int len = s.length();
        if(len<2) {
            return true;
        }
        int left = 0;
        int right = len-1;
        s = s.toLowerCase();
        while(left<right) {
            while(left<right&&(s.charAt(left)>'z'||s.charAt(left)<'a')) {
                left++;
            }
            while(left<right&&(s.charAt(right)>'z'||s.charAt(right)<'a')) {
                right--;
            }
            if(s.charAt(right)!=s.charAt(left)) {
                return false;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("OP"));
    }

}
