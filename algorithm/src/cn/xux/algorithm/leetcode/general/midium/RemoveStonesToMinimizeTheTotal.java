package cn.xux.algorithm.leetcode.general.midium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1962. 移除石子使总数最小
 * 给你一个整数数组 piles ，数组 下标从 0 开始 ，
 * 其中 piles[i] 表示第 i 堆石子中的石子数量。
 * 另给你一个整数 k ，请你执行下述操作 恰好 k 次：
 * <p>
 * 选出任一石子堆 piles[i] ，并从中 移除 floor(piles[i] / 2) 颗石子。
 * 注意：你可以对 同一堆 石子多次执行此操作。
 * <p>
 * 返回执行 k 次操作后，剩下石子的 最小 总数。
 * <p>
 * floor(x) 为 小于 或 等于 x 的 最大 整数。（即，对 x 向下取整）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [5,4,9], k = 2
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [5,4,5] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [3,4,5] 。
 * 剩下石子的总数为 12 。
 * 示例 2：
 * <p>
 * 输入：piles = [4,3,6,7], k = 3
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [4,3,3,7] 。
 * - 对第 3 堆石子执行移除操作，石子分布情况变成 [4,3,3,4] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [2,3,3,4] 。
 * 剩下石子的总数为 12 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 105
 * 1 <= piles[i] <= 104
 * 1 <= k <= 105
 */
public class RemoveStonesToMinimizeTheTotal {

    public int minStoneSum(int[] piles, int k) {
        int ans = 0;
        int[] cnt = new int[10001];
        for (int p : piles) {
            ans += p;
            cnt[p]++;
        }
        int i = 10000;
        while (i > 1 && k > 0) {
            while (i > 1 && cnt[i] == 0) {
                i--;
            }
            ans -= i / 2;
            cnt[i]--;
            cnt[(i + 1) / 2]++;
            k--;
        }
        return ans;
    }

    public int minStoneSum1(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        int ans = 0;
        for (int p : piles) {
            queue.offer(p);
            ans += p;
        }
        while (k-- > 0 && queue.peek() > 1) {
            int curr = queue.poll();
            int d = curr >> 1;
            ans -= d;
            queue.offer(curr - d);
        }
        return ans;
    }

}
