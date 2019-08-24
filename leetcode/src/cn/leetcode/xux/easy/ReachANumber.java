package cn.leetcode.xux.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * Return the minimum number of steps required to reach the destination.
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 *
 * 利用BFS输出前N步可以到达的位置：
 * [1, -1]
 * [1, 3, -3, -1]
 * [0, 2, 4, 6, -6, -4, -2]
 * [0, 2, 4, 6, 8, 10, -10, -8, -6, -4, -2]
 * [1, 3, 5, 7, 9, 11, 13, 15, -15, -13, -11, -9, -7, -5, -3, -1]
 * [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, -21, -19, -17, -15, -13, -11, -9, -7, -5, -3, -1]
 * 观察上表可知：
 * 负数位置与正数位置对称
 * 当(N - 1) % 4 <= 1时，第N步（N从1开始）可以到达的位置为奇数，否则为偶数
 * 第N步可以到达的最大绝对值位置为：首项为1，公差为1的等差数列前N项和 S(n) = n * (n + 1) / 2
 * 令X为target，解方程S(n) = X得：n = (sqrt(1 + 8*x) - 1) / 2
 */
public class ReachANumber {

    public static int reachNumber(int target) {
        target = Math.abs(target);
        int i=1, maxPos=0;
        while(maxPos<target||maxPos%2!=target%2) {
            maxPos += i;
            i++;
        }
        return i-1;
    }

    public static void main(String[] args) {
        System.out.println(reachNumber(0)); //0
        System.out.println(reachNumber(1)); //1
        System.out.println(reachNumber(2)); //3
        System.out.println(reachNumber(3)); //2
    }

}
