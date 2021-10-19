package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 2033. 获取单值网格的最小操作数
 * 给你一个大小为 m x n 的二维整数网格 grid 和一个整数 x 。
 * 每一次操作，你可以对 grid 中的任一元素 加 x 或 减 x 。
 *
 * 单值网格 是全部元素都相等的网格。
 *
 * 返回使网格化为单值网格所需的 最小 操作数。如果不能，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,4],[6,8]], x = 2
 * 输出：4
 * 解释：可以执行下述操作使所有元素都等于 4 ：
 * - 2 加 x 一次。
 * - 6 减 x 一次。
 * - 8 减 x 两次。
 * 共计 4 次操作。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,5],[2,3]], x = 1
 * 输出：5
 * 解释：可以使所有元素都等于 3 。
 * 示例 3：
 *
 *
 *
 * 输入：grid = [[1,2],[3,4]], x = 2
 * 输出：-1
 * 解释：无法使所有元素相等。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= x, grid[i][j] <= 104
 */
public class MinimumOperationsToMakeAUniValueGrid {

    public static void main(String[] args) {
        // [[931,128],[639,712]]
        // 73
        System.out.println(new MinimumOperationsToMakeAUniValueGrid()
                .minOperations(new int[][]{{931,128},{639,712}}, 73));
    }

    // 对于两个数[a, b]，目标位置肯定在ab之间，
    // 而且只要在ab之间，移动距离固定b-a，可以随便选。
    // 一组数，对于头尾来讲，只要目标位置在头尾之间，
    // 头和尾的移动距离就是最小且固定的，把头尾去掉，
    // 同样考虑剩下的元素，不断重复，
    // 最终目标位置就是在最中间两个元素之间，所有移动距离和最小。
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[m*n];
        int i = 0;
        for(int[] array : grid) {
            for(int num : array) {
                if(Math.abs(num-grid[0][0])%x!=0) {
                    return -1;
                }
                arr[i++] = num;
            }
        }
        Arrays.sort(arr);
        int tar = arr[arr.length/2];
        int ans = 0;
        for(int num : arr) {
            ans += Math.abs(num-tar)/x;
        }
        return ans;
    }

}
