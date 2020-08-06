package cn.xux.algorithm.lintcode;

/**
 * 795. 4种独特的路径
 * 中文English
 * 一个机器人位于一个m*n的网格的左上角。
 * 机器人可以在任何时间点移动任何方向，但是每个网格只能达到一次。机器人正试图到达网格的右下角。
 * 有多少种可能的独特路径?
 *
 * 样例
 * 样例 1:
 *
 * 输入:
 * 2 3
 * 输出:
 * 4
 * 样例 2:
 *
 * 输入:
 * 3 3
 * 输出:
 * 12
 */
public class Lintcode795 {

    /**
     * @param m: the row
     * @param n: the column
     * @return: the possible unique paths
     */
    public int uniquePaths(int m, int n) {
        // Write your code here
        dfs(0, 0, new boolean[m][n]);
        return ans;
    }

    int ans = 0;

    private void dfs(int i, int j, boolean[][] visited) {
        if(i<0||i>=visited.length||j<0||j>=visited[0].length||visited[i][j]) {
            return;
        }
        if(i==visited.length-1&&j==visited[0].length-1) {
            ans++;
            return;
        }
        visited[i][j] = true;
        dfs(i-1, j, visited);
        dfs(i+1, j, visited);
        dfs(i, j-1, visited);
        dfs(i, j+1, visited);
        visited[i][j] = false;
    }

}
