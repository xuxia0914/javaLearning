package cn.leetcode.xux.midium;

/**
 * 1391. 检查网格中是否存在有效路径
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 * 1 表示连接左单元格和右单元格的街道。
 * 2 表示连接上单元格和下单元格的街道。
 * 3 表示连接左单元格和下单元格的街道。
 * 4 表示连接右单元格和下单元格的街道。
 * 5 表示连接左单元格和上单元格的街道。
 * 6 表示连接右单元格和上单元格的街道。
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
 * 注意：你 不能 变更街道。
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 *
 * 示例 1：
 * 输入：grid = [[2,4,3],[6,5,2]]
 * 输出：true
 * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 *
 * 示例 2：
 * 输入：grid = [[1,2,1],[1,2,1]]
 * 输出：false
 * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 *
 * 示例 3：
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 *
 * 示例 4：
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 *
 * 示例 5：
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 *
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 */
public class CheckIfThereIsAValidPathInAGrid {

    public boolean hasValidPath(int[][] grid) {
        return hasValidPath(grid, 0, 0);
    }

    public boolean hasValidPath(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if(i<0||i>=m||j<0||j>=n||grid[i][j]==0) {
            return false;
        }
        if(i==m-1&&j==n-1) {
            return true;
        }
        int curr = grid[i][j];
        grid[i][j] = 0;
        boolean result = false;
        switch(curr) {
            case 1 :
                if(j-1>=0&&(grid[i][j-1]==1||grid[i][j-1]==4||grid[i][j-1]==6)) {
                    result |= hasValidPath(grid, i, j-1);
                }
                if(j+1<n&&(grid[i][j+1]==1||grid[i][j+1]==3||grid[i][j+1]==5)) {
                    result |= hasValidPath(grid, i, j+1);
                }
                return result;
            case 2 :
                if(i-1>=0&&(grid[i-1][j]==2||grid[i-1][j]==3||grid[i-1][j]==4)) {
                    result |= hasValidPath(grid, i-1, j);
                }
                if(i+1<m&&(grid[i+1][j]==2||grid[i+1][j]==5||grid[i+1][j]==6)) {
                    result |= hasValidPath(grid, i+1, j);
                }
                return result;
            case 3 :
                if(j-1>=0&&(grid[i][j-1]==1||grid[i][j-1]==4||grid[i][j-1]==6)) {
                    result |= hasValidPath(grid, i, j-1);
                }
                if(i+1<m&&(grid[i+1][j]==2||grid[i+1][j]==5||grid[i+1][j]==6)) {
                    result |= hasValidPath(grid, i+1, j);
                }
                return result;
            case 4 :
                if(j+1<n&&(grid[i][j+1]==1||grid[i][j+1]==3||grid[i][j+1]==5)) {
                    result |= hasValidPath(grid, i, j+1);
                }
                if(i+1<m&&(grid[i+1][j]==2||grid[i+1][j]==5||grid[i+1][j]==6)) {
                    result |= hasValidPath(grid, i+1, j);
                }
                return result;
            case 5 :
                if(j-1>=0&&(grid[i][j-1]==1||grid[i][j-1]==4||grid[i][j-1]==6)) {
                    result |= hasValidPath(grid, i, j-1);
                }
                if(i-1>=0&&(grid[i-1][j]==2||grid[i-1][j]==3||grid[i-1][j]==4)) {
                    result |= hasValidPath(grid, i-1, j);
                }
                return result;
            case 6 :
                if(j+1<n&&(grid[i][j+1]==1||grid[i][j+1]==3||grid[i][j+1]==5)) {
                    result |= hasValidPath(grid, i, j+1);
                }
                if(i-1>=0&&(grid[i-1][j]==2||grid[i-1][j]==3||grid[i-1][j]==4)) {
                    result |= hasValidPath(grid, i-1, j);
                }
                return result;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        CheckIfThereIsAValidPathInAGrid cif = new CheckIfThereIsAValidPathInAGrid();
        System.out.println(cif.hasValidPath(new int[][]{{2},{2},{2},{2},{2},{2},{6}}));
    }

}
