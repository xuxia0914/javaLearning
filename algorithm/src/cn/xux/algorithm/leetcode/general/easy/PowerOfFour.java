package cn.xux.algorithm.leetcode.general.easy;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * Example 1:
 * Input: 16
 * Output: true
 * Example 2:
 * Input: 5
 * Output: false
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {

    public boolean isPowerOfFour(int num) {
        if(num<1) {
            return false;
        }
        if((num&(num-1))!=0) {  //先判断是否是2的次方
            return false;
        }
        int sqr = (int)Math.sqrt(num);
        if(sqr*sqr!=num) {  //再判断是否是整数的平方
            return false;
        }
        return true;
    }

}
