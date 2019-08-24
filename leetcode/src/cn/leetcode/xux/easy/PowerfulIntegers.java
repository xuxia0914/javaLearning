package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.
 * Return a list of all powerful integers that have value less than or equal to bound.
 * You may return the answer in any order.  In your answer, each value should occur at most once.
 *
 * Example 1:
 * Input: x = 2, y = 3, bound = 10
 * Output: [2,3,4,5,7,9,10]
 * Explanation:
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 *
 * Example 2:
 * Input: x = 3, y = 5, bound = 15
 * Output: [2,4,6,8,10,14]
 *
 * Note:
 * 1 <= x <= 100
 * 1 <= y <= 100
 * 0 <= bound <= 10^6
 */
public class PowerfulIntegers {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        if(bound<2) {
            return new ArrayList<>(res);
        }
        if(x==1&&y==1) {
            res.add(2);
            return new ArrayList<>(res);
        }

        if(x==1) {
            int b = 1;
            while(1+b<=bound) {
                res.add(1+b);
                b *= y;
            }
            return new ArrayList<>(res);
        }

        if(y==1) {
            int a = 1;
            while(a+1<=bound) {
                res.add(a+1);
                a *= x;
            }
            return new ArrayList<>(res);
        }


        int a = 1;
        while(a+1<=bound) {
            int b = 1;
            while(a+b<=bound) {
                res.add(a+b);
                b *= y;
            }
            a *= x;
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        System.out.println(new PowerfulIntegers().powerfulIntegers(2, 3, 10));
    }

}
