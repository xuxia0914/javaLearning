package cn.leetcode.xux.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        if(n<1) {
            return false;
        }
        Set<Integer> nums = new HashSet<>();
        int tmp = n;
        while(true) {
            tmp = helper(tmp);
            if(tmp==1) {
                return true;
            }else if(!nums.add(tmp)){
                return false;
            }
        }
    }

    public int helper(int n) {
        int res=0, i;
        while(n>0) {
            i = n%10;
            n /= 10;
            res += i*i;
        }
        return res;
    }

}
