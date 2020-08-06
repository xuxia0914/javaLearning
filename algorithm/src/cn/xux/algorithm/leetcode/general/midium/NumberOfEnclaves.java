package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1020. 飞地的数量
 * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
 * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
 * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 * 示例 1：
 * 输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：
 * 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 *
 * 示例 2：
 * 输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：
 * 所有 1 都在边界上或可以到达边界。
 *
 * 提示：
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * 所有行的大小都相同
 */
public class NumberOfEnclaves {

    public int numEnclaves(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        if(m<2||n<2) {
            return 0;
        }
        for(int i=0;i<n;i++) {
            dfs(A, 0, i);
            dfs(A, m-1, i);
        }
        for(int i=1;i<m-1;i++) {
            dfs(A, i, 0);
            dfs(A, i, n-1);
        }
        int result = 0;
        for(int[] arr : A) {
            for(int a : arr) {
                result += a;
            }
        }
        return result;
    }

    public void dfs(int[][] A, int i, int j) {
        if(i<0||i>=A.length||j<0||j>=A[0].length||A[i][j]==0) {
            return ;
        }
        A[i][j] = 0;
        dfs(A, i-1, j);
        dfs(A, i+1, j);
        dfs(A, i, j-1);
        dfs(A, i, j+1);
    }

}
