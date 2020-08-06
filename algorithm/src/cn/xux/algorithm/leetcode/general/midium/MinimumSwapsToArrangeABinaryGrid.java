package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1536. 排布二进制网格的最少交换次数
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。
 * 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。
 * 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。
 * 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。
 *
 * 示例 1：
 * 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 * 输出：3
 *
 * 示例 2：
 * 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * 输出：-1
 * 解释：所有行都是一样的，交换相邻行无法使网格符合要求。
 *
 * 示例 3：
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 * 输出：0
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] 要么是 0 要么是 1 。
 */
public class MinimumSwapsToArrangeABinaryGrid {

    public static void main(String[] args) {
        System.out.println(new MinimumSwapsToArrangeABinaryGrid()
                .minSwaps(new int[][]{{0,0,1},{1,1,0},{1,0,0}}));
    }

    public int minSwaps(int[][] grid) {
        if(grid==null||grid.length<2) {
            return 0;
        }
        int n = grid.length;
        int ans = 0;
        boolean[] visited = new boolean[n];
        for(int i=1;i<n;i++) {
            int step = 0;
            boolean flag = false;
            for(int j=0;j<n;j++) {
                if(!visited[j]) {
                    if(isNeed(grid[j], i)) {
                        visited[j] = true;
                        flag = true;
                        ans += step;
                        break;
                    }
                    step++;
                }
            }
            if(!flag) {
                return -1;
            }
        }
        return ans;
    }

    private boolean isNeed(int[] arr, int start) {
        for(int i=start;i<arr.length;i++) {
            if(arr[i]==1) {
                return false;
            }
        }
        return true;
    }

}
