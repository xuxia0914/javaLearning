package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1906. 查询差绝对值的最小值
 * 一个数组 a 的 差绝对值的最小值 定义为：0 <= i < j < a.length 且 a[i] != a[j] 的 |a[i] - a[j]| 的 最小值。
 * 如果 a 中所有元素都 相同 ，那么差绝对值的最小值为 -1 。
 * <p>
 * 比方说，数组 [5,2,3,7,2] 差绝对值的最小值是 |2 - 3| = 1 。
 * 注意答案不为 0 ，因为 a[i] 和 a[j] 必须不相等。
 * 给你一个整数数组 nums 和查询数组 queries ，其中 queries[i] = [li, ri] 。
 * 对于每个查询 i ，计算 子数组 nums[li...ri] 中 差绝对值的最小值 ，
 * 子数组 nums[li...ri] 包含 nums 数组（下标从 0 开始）中下标在 li 和 ri 之间的所有元素（包含 li 和 ri 在内）。
 * <p>
 * 请你返回 ans 数组，其中 ans[i] 是第 i 个查询的答案。
 * <p>
 * 子数组 是一个数组中连续的一段元素。
 * <p>
 * |x| 的值定义为：
 * <p>
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
 * 输出：[2,1,4,1]
 * 解释：查询结果如下：
 * - queries[0] = [0,1]：子数组是 [1,3] ，差绝对值的最小值为 |1-3| = 2 。
 * - queries[1] = [1,2]：子数组是 [3,4] ，差绝对值的最小值为 |3-4| = 1 。
 * - queries[2] = [2,3]：子数组是 [4,8] ，差绝对值的最小值为 |4-8| = 4 。
 * - queries[3] = [0,3]：子数组是 [1,3,4,8] ，差的绝对值的最小值为 |3-4| = 1 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
 * 输出：[-1,1,1,3]
 * 解释：查询结果如下：
 * - queries[0] = [2,3]：子数组是 [2,2] ，差绝对值的最小值为 -1 ，因为所有元素相等。
 * - queries[1] = [0,2]：子数组是 [4,5,2] ，差绝对值的最小值为 |4-5| = 1 。
 * - queries[2] = [0,5]：子数组是 [4,5,2,2,7,10] ，差绝对值的最小值为 |4-5| = 1 。
 * - queries[3] = [3,5]：子数组是 [2,7,10] ，差绝对值的最小值为 |7-10| = 3 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 100
 * 1 <= queries.length <= 2 * 104
 * 0 <= li < ri < nums.length
 */
public class MinimumAbsoluteDifferenceQueries {

    public int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] cnt = new int[n + 1][2];
        cnt[0] = new int[101];
        for (int i = 0; i < n; i++) {
            cnt[i + 1] = cnt[i].clone();
            cnt[i + 1][nums[i]]++;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int[] arr = new int[101];
            int pre = 0;
            int curr = 100;
            for (int j = 1; j < 101; j++) {
                arr[j] = cnt[q[1] + 1][j] - cnt[q[0]][j];
                if (arr[j] > 0) {
                    if (pre > 0) {
                        curr = Math.min(curr, j - pre);
                    }
                    pre = j;
                }
            }
            ans[i] = curr == 100 ? -1 : curr;
        }
        return ans;
    }

}
