package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2121. 相同元素的间隔之和
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 arr 。
 * <p>
 * arr 中两个元素的 间隔 定义为它们下标之间的 绝对差 。
 * 更正式地，arr[i] 和 arr[j] 之间的间隔是 |i - j| 。
 * <p>
 * 返回一个长度为 n 的数组 intervals ，
 * 其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同）的 间隔之和 。
 * <p>
 * 注意：|x| 是 x 的绝对值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,1,3,1,2,3,3]
 * 输出：[4,2,7,2,4,4,5]
 * 解释：
 * - 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
 * - 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
 * - 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
 * - 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
 * - 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
 * - 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
 * - 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
 * 示例 2：
 * <p>
 * 输入：arr = [10,5,10,10]
 * 输出：[5,0,3,4]
 * 解释：
 * - 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
 * - 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
 * - 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
 * - 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == arr.length
 * 1 <= n <= 105
 * 1 <= arr[i] <= 105
 */
public class IntervalsBetweenIdenticalElements {

    public long[] getDistances(int[] arr) {
        int n = arr.length;
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        int[] cnt = new int[max + 1];
        long[] sum = new long[max + 1];
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] += (long) i * cnt[arr[i]] - sum[arr[i]];
            cnt[arr[i]]++;
            sum[arr[i]] += i;
        }
        cnt = new int[max + 1];
        sum = new long[max + 1];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] += sum[arr[i]] - (long) i * cnt[arr[i]];
            cnt[arr[i]]++;
            sum[arr[i]] += i;
        }
        return ans;
    }

}
