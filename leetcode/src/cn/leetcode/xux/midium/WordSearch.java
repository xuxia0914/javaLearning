package cn.leetcode.xux.midium;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(exist(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean exist(char[][] board, String word, int idx, int i, int j) {
        if(idx==word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if(i<0||i>=m||j<0||j>=n||board[i][j]!=word.charAt(idx)) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '#';
        boolean res = exist(board, word, idx+1, i-1, j)
                ||exist(board, word, idx+1, i+1, j)
                ||exist(board, word, idx+1, i, j-1)
                ||exist(board, word, idx+1, i, j+1);
        board[i][j] = tmp;
        return res;
    }

}
