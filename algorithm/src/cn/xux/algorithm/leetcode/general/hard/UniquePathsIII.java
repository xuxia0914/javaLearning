package cn.xux.algorithm.leetcode.general.hard;

/**
 * 980. 不同路径 III
 * 在二维网格 grid 上，有 4 种类型的方格：
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。
 *
 * 示例 1：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出：2
 * 解释：我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * 示例 2：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * 输出：4
 * 解释：我们有以下四条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * 示例 3：
 * 输入：[[0,1],[2,0]]
 * 输出：0
 * 解释：
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。
 *
 * 提示：
 * 1 <= grid.length * grid[0].length <= 20
 */
public class UniquePathsIII {

    int res = 0;

    int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int uniquePathsIII(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int si = 0;
        int sj = 0;
        int emptyCnt = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    si = i;
                    sj = j;
                    emptyCnt++;
                }
                if(grid[i][j]==0){
                    emptyCnt++;
                }
            }
        }
        dfs(grid, si, sj, emptyCnt, 0);
        return res;
    }

    public void dfs(int[][] grid, int i, int j, int emptyCnt, int currCnt) {
        int m = grid.length;
        int n = grid[0].length;
        if(i<0||i>=m||j<0||j>=n||grid[i][j]==-1) {
            return;
        }
        if(grid[i][j]==2) {
            if(currCnt==emptyCnt) {
                res++;
            }
            return;
        }
        grid[i][j] = -1;
        for(int[] d : dir) {
            dfs(grid, i+d[0], j+d[1], emptyCnt, currCnt+1);
        }
        grid[i][j] = 0;
    }

    public static void main(String[] args) {
        /*System.out.println(new UniquePathsIII().uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2,-1}
        }));*/

        System.out.println(new UniquePathsIII().uniquePathsIII(new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        }));
    }

}
