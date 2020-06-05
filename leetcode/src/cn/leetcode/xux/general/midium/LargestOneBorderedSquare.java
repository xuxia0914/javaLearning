package cn.leetcode.xux.general.midium;

/**
 * 5141. 最大的以 1 为边界的正方形  显示英文描述
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * 示例 1：
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 * 提示：
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 */
public class LargestOneBorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int startI;
        int startJ;
        int res = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                startI = i;
                startJ = j;
                int tmp = res;
                while(startI+tmp<m&&startJ+tmp<n) {
                    if(helper(grid, new int[]{startI, startJ}, new int[]{startI+tmp, startJ+tmp})) {
                        res = tmp+1;
                    }
                    tmp++;
                }
            }
        }
        return res*res;
    }

    public boolean helper(int[][] grid, int[] start, int[] end) {
        for(int i=start[0];i<=end[0];i++) {
            if(grid[i][start[1]]==0||grid[i][end[1]]==0) {
                return false;
            }
        }
        for(int i=start[1]+1;i<=end[1]-1;i++) {
            if(grid[start[0]][i]==0||grid[end[0]][i]==0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new LargestOneBorderedSquare().largest1BorderedSquare(new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        }));
    }

}
