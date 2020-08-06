package cn.xux.algorithm.leetcode.general.easy;

/**
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 * Example 2:
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 * Example 3:
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 * Example 4:
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.
 */
public class BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits1(int n) {
        n = n>>2^n;
        return (n&(n-1))==0;
    }

    boolean hasAlternatingBits2(int n) {
        return ((n + (n >> 1) + 1) & (n + (n >> 1))) == 0;
    }


}
