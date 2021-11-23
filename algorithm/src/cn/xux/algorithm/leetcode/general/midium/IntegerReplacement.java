package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 397. 整数替换
 * 给定一个正整数 n ，你可以做如下操作：
 * <p>
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2^31 - 1
 */
public class IntegerReplacement {

    public static void main(String[] args) {
        System.out.println(new IntegerReplacement().integerReplacement(2147483647));
    }

    // 贪心
    public int integerReplacement(int n) {
        int ans = 0;
        long curr = n;
        while (curr != 1) {
            if ((curr & 1) == 0) {
                curr >>= 1;
                ans++;
            } else {
                if (curr == 3 || (curr & 3) == 1) {
                    curr = (curr - 1) >> 1;
                } else {
                    curr = (curr + 1) >> 1;
                }
                ans += 2;
            }
        }
        return ans;
    }

    public int integerReplacement1(int n) {
        Set<Long> set = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        set.add((long) n);
        queue.offer((long) n);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                long curr = queue.poll();
                if (curr == 1) {
                    return level;
                }
                if ((curr & 1) == 0) {
                    if (set.add(curr >> 1)) {
                        queue.offer(curr >> 1);
                    }
                } else {
                    if (set.add(curr + 1)) {
                        queue.offer(curr + 1);
                    }
                    if (set.add(curr - 1)) {
                        queue.offer(curr - 1);
                    }
                }
            }
            level++;
        }
        return -1;
    }

}
