package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 * Flip all the lights.
 * Flip lights with even numbers.
 * Flip lights with odd numbers.
 * Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 * Example 1:
 * Input: n = 1, m = 1.
 * Output: 2
 * Explanation: Status can be: [on], [off]
 * Example 2:
 * Input: n = 2, m = 1.
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off]
 * Example 3:
 * Input: n = 3, m = 1.
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 * Note: n and m both fit in range [0, 1000].
 */
public class BulbSwitcherII {

    /**
     * Runtime: 34 ms, faster than 5.08% of Java online submissions for Bulb Switcher II.
     * Memory Usage: 36 MB, less than 5.38% of Java online submissions for Bulb Switcher II.
     * @param n
     * @param m
     * @return
     */
    public int flipLights(int n, int m) {
        if(n==0||m==0) {
            return 1;
        }
        if(n>6) {
            n = n%6 + 6;
        }
        int status = (int)Math.pow(2, n) - 1;
        Set<Integer> set = new HashSet<>();
        set.add(status);
        Set<Integer> tmp = new HashSet<>();
        for(int i=0;i<m;i++) {
            tmp.clear();
            for(Integer j : set) {
                tmp.add(flip1(j, n));
                tmp.add(flip2(j, n));
                tmp.add(flip3(j, n));
                tmp.add(flip4(j, n));
            }
            set = new HashSet<>(tmp);
        }
        return set.size();
    }

    public int flip1(int status, int n) {
        for(int i=0;i<n;i++) {
            status = status^(1<<i);
        }
        return status;
    }

    public int flip2(int status, int n) {
        for(int i=1;i<n;i+=2) {
            status = status^(1<<i);
        }
        return status;
    }

    public int flip3(int status, int n) {
        for(int i=0;i<n;i+=2) {
            status = status^(1<<i);
        }
        return status;
    }

    public int flip4(int status, int n) {
        for(int i=0;i<n;i+=3) {
            status = status^(1<<i);
        }
        return status;
    }

    public int flipLights2(int n, int m) {
        if(n==0||m==0) {
            return 1;
        }
        if(n>6) {
            n = n%6 + 6;
        }
        int status = (int)Math.pow(2, n) - 1;
        Set<Integer> set = new HashSet<>();
        set.add(status);
        Set<Integer> tmp = new HashSet<>();
        for(int i=0;i<m;i++) {
            tmp.clear();
            for(Integer j : set) {
                tmp.add(flip1(j, n));
                tmp.add(flip2(j, n));
                tmp.add(flip3(j, n));
                tmp.add(flip4(j, n));
            }
            set = new HashSet<>(tmp);
        }
        return set.size();
    }

    public static void main(String[] args) {
        BulbSwitcherII bs2 = new BulbSwitcherII();
//        System.out.println(bs2.flipLights(1, 1)); //2
        System.out.println(bs2.flipLights(2, 1));   //3
        System.out.println(bs2.flipLights(200, 100));   //3
    }

}
