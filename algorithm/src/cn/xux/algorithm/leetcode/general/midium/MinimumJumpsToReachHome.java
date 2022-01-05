package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1654. 到家的最少跳跃次数
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 * 跳蚤跳跃的规则如下：
 * 它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。
 * 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，
 * 同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。
 * 如果没有恰好到达 x 的可行方案，请你返回 -1 。
 * <p>
 * 示例 1：
 * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * 输出：3
 * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 * <p>
 * 示例 2：
 * 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * 输出：2
 * 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
 * <p>
 * 提示：
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * forbidden 中所有位置互不相同。
 * 位置 x 不在 forbidden 中。
 */
public class MinimumJumpsToReachHome {

    // 假设当前位置为 curr，如果不考虑 forbidden 和其他所有限制条件，当 curr>x 时，要到达 x，后续操作必有至少一次向后跳，
    // 且由于向前向后跳的执行顺序不影响结果，可将后续的向后跳放在当前位置的下一跳执行，即当 curr>x 时，下一跳必向后跳
    // 同理，当 curr<x 时，下一跳必向前跳。
    // 然后把 forbidden 考虑进来，当 curr>x 时，向后跳的位置可能在 forbidden 中，
    // 此时需要向前跳直到 curr>max(forbidden)+b ，再向后跳，保证下一跳必不在 forbidden 中，
    // 综上考虑，跳跃路经的所有位置必然不大于 max(forbidden)+b+a
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) {
            return 0;
        }
        // invalid[i] 表示位置i是否在forbidden中或者已被访问过
        boolean[] invalid = new boolean[6001];
        invalid[0] = true;
        for (int f : forbidden) {
            invalid[f] = true;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int step = curr[1];
            if (pos + a == x || pos - b == x) {
                return step + 1;
            }
            if (pos > b && !invalid[pos - b] && pos - b + a == x) {
                return step + 2;
            }
            // 判断下一跳是否可以向前跳
            if (pos + a < 6001 && !invalid[pos + a]) {
                invalid[pos + a] = true;
                queue.offer(new int[]{pos + a, step + 1});
            }
            // 由于不能连续向后跳，所以把 向后跳一次+向前跳一次 合并考虑，保证入队的位置的前一跳必然是向前的
            // 判断此种操作是否可行
            if (pos > b && pos - b + a < 6001 && !invalid[pos - b] && !invalid[pos - b + a]) {
                invalid[pos - b + a] = true;
                queue.offer(new int[]{pos - b + a, step + 2});
            }
        }
        return -1;
    }

}
