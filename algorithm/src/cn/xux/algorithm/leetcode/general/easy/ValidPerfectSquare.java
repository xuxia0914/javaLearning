package cn.xux.algorithm.leetcode.general.easy;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * Example 1:
 * Input: 16
 * Output: true
 * Example 2:
 * Input: 14
 * Output: false
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        String[] tmp = new String[2];
        if(num<0) {
            return false;
        }
        if(num<2) {
            return true;
        }
        int i = 1;
        while(num>0) {
            num -= i;
            i += 2;
        }
        return num==0;
    }

}
