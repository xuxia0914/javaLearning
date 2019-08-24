package cn.leetcode.xux.easy;

/**
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 * Input: 3
 * Output: False
 */
public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        if(c<0) {
            return false;
        }
        if(c<3) {
            return true;
        }
        int x = (int)Math.sqrt(c);
        int y = (int)Math.sqrt(c-x*x);
        while(x>=y) {
            if(x*x+y*y==c) {
                return true;
            }else {
                x--;
                y = (int)Math.sqrt(c-x*x);
            }
        }
        return false;
    }

}
