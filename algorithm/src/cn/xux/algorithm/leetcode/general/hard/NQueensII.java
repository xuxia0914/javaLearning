package cn.xux.algorithm.leetcode.general.hard;

/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
 * 当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。
 * 当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class NQueensII {

    public int totalNQueens(int n) {
        ans = 0;
        dfs(new char[n][n], 0, n);
        return ans;
    }

    int ans = 0;

    public void dfs(char[][] board, int i, int n) {
        if(i==n-1) {
            for(int j=0;j<n;j++) {
                ans += board[i][j]!='.'?1:0;
            }
            return;
        }
        for(int j=0;j<n;j++) {
            if(board[i][j]!='.') {
                char[][] nextBoard = new char[n][n];
                for(int k=0;k<n;k++) {
                    nextBoard[k] = board[k].clone();
                }
                nextBoard[i][j] = 'Q';
                for(int k=0;k<n;k++) {
                    if(k!=j) {
                        nextBoard[i][k] = '.';
                    }
                }
                for(int k=i+1;k<n;k++) {
                    nextBoard[k][j] = '.';
                }
                for(int k=1;i+k<=n-1&&j-k>=0;k++) {
                    nextBoard[i+k][j-k] = '.';
                }
                for(int k=1;i+k<=n-1&&j+k<=n-1;k++) {
                    nextBoard[i+k][j+k] = '.';
                }
                dfs(nextBoard, i+1, n);
            }
        }
    }

}
