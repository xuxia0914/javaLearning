package cn.leetcode.xux.easy;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfAString {

    public String reverseVowels(String s) {
        if(s==null||s.length()<2) {
            return s;
        }

        String vowels = "aoeiuAOEIU";
        int len = s.length();
        int left = 0;
        int right = len-1;
        char tmp;
        StringBuilder sb = new StringBuilder(s);
        while(left<right) {
            while(left<right&&!vowels.contains(s.substring(left, left+1))) {
                left++;
            }
            while(left<right&&!vowels.contains(s.substring(right, right+1))) {
                right--;
            }
            if(left<right) {
                sb.setCharAt(left, s.charAt(right));
                sb.setCharAt(right, s.charAt(left));
                left++;
                right--;
            }
        }
        return sb.toString();
    }

}
