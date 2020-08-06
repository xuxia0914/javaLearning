package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，
 * 并且使皇后彼此之间不能相互攻击(即每两个皇后不能在同一横线，竖线或斜线上)。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 * 输入: 4
 * 输出: [
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
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。
 * 当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。
 * 当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        ans.clear();
        dfs(new char[n][n], 0, n);
        return ans;
    }

    List<List<String>> ans = new ArrayList<>();

    public void dfs(char[][] board, int i, int n) {
        if(i==n) {
            List<String> list = new ArrayList<>();
            for(int j=0;j<n;j++) {
                list.add(new String(board[j]));
            }
            ans.add(list);
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
