package cn.leetcode.xux.midium;

/**
 * 整数拆分
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
 * Return the maximum product you can get.
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * Note: you may assume that n is not less than 2.
 * Hint:
 * There is a simple O(n) solution to this problem.
 * You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 */
public class IntegerBreak {

    public static int solution(int n) {
        if(n==2) {
            return 1;
        }
        if(n==3) {
            return 2;
        }
        if(n==4) {
            return 4;
        }
        int result = 1;
        while(n>4) {
            result = 3*result;
            n -= 3;
        }
        return result*n;
    }

    public static void main(String[] args) {
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
        System.out.println(solution(10));
    }

}
