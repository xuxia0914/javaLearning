package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1901. 找出顶峰元素 II
 * 一个 2D 网格中的 顶峰元素 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * <p>
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，
 * 其中任意两个相邻格子的值都 不相同 。
 * 找出 任意一个 顶峰元素 mat[i][j] 并 返回其位置 [i,j] 。
 * <p>
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * <p>
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: mat = [[1,4],[3,2]]
 * 输出: [0,1]
 * 解释: 3和4都是顶峰元素，所以[1,0]和[0,1]都是可接受的答案。
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * 输出: [1,1]
 * 解释: 30和32都是顶峰元素，所以[1,1]和[2,2]都是可接受的答案。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 105
 * 任意两个相邻元素均不相等.
 */
public class FindAPeakElementII {

    public int[] findPeakGrid(int[][] mat) {
        // 二分法查询，先确定目标在哪一行，
        // 如果 某一行是第一行 或者 这一行的最大值大于等于上一行的最大值，那么从当前行往下查询必有答案
        // 否则从当前行的上一行往上查询必有答案。
        int m = mat.length;
        int n = mat[0].length;
        int up = 0;
        int bot = m - 1;
        while (up < bot) {
            int mid = (up + bot + 1) >> 1;
            int currMax = mat[mid][0];
            for (int num : mat[mid]) {
                currMax = Math.max(currMax, num);
            }
            if (mid > 0) {
                int upMax = mat[mid - 1][0];
                for (int num : mat[mid - 1]) {
                    upMax = Math.max(upMax, num);
                }
                if (upMax <= currMax) {
                    up = mid;
                } else {
                    bot = mid - 1;
                }
            } else {
                up = mid;
            }
        }
        // 找出这一行的最大值的位置
        int max = mat[up][0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (mat[up][i] > max) {
                max = mat[up][i];
                idx = i;
            }
        }
        return new int[]{up, idx};
    }

}
