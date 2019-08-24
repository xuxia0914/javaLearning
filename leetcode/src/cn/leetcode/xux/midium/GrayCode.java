package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * Example 1:
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * Example 2:
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 *              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 *              Therefore, for n = 0 the gray code sequence is [0].
 */
public class GrayCode {

    /**
     * Runtime: 1 ms, faster than 47.61% of Java online submissions for Gray Code.
     * Memory Usage: 34.1 MB, less than 12.95% of Java online submissions for Gray Code.
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if(n<0) {
            return res;
        }
        res.add(0);
        if(n==0) {
            return res;
        }
        res = grayCode(n-1);
        int num = (int)Math.pow(2, n-1);
        int i = res.size();
        while(i-->0) {
            res.add(num+res.get(i));
        }
        return res;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Gray Code.
     * Memory Usage: 34.3 MB, less than 6.83% of Java online submissions for Gray Code.
     * @param n
     * @return
     */
    public List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();

        int num = (int)Math.pow(2, n);
        for(int i=0;i<num;i++) {
            res.add((i>>1)^i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode1(3));
    }

}
