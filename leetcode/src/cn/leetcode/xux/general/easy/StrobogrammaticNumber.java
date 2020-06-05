package cn.leetcode.xux.general.easy;

/**
 * 246 Strobogrammatic Number 中心对称数
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 * Input:  "69"
 * Output: true
 *
 * Example 2:
 * Input:  "88"
 * Output: true
 *
 * Example 3:
 * Input:  "962"
 * Output: false
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        int left = 0;
        int right = num.length();
        while(left<right) {
            if(!(num.charAt(left)=='0'&&num.charAt(right)=='0')
                    &&!(num.charAt(left)=='1'&&num.charAt(right)=='1')
                    &&!(num.charAt(left)=='8'&&num.charAt(right)=='8')
                    &&!(num.charAt(left)=='9'&&num.charAt(right)=='6')
                    &&!(num.charAt(left)=='6'&&num.charAt(right)=='9')) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
