package cn.leetcode.xux.midium;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(word.charAt(0)==board[i][j]&&helper(board, new boolean[m][n], word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean helper(char[][] board, boolean[][] visited, String word, int idx, int i, int j) {
        if(idx==word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if(i<0||i>=m||j<0||j>=n||board[i][j]!=word.charAt(idx)||visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean res = helper(board, visited, word, idx+1, i-1, j)
                ||helper(board, visited, word, idx+1, i+1, j)
                ||helper(board, visited, word, idx+1, i, j-1)
                ||helper(board, visited, word, idx+1, i, j+1);
        visited[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        /*System.out.println(new WordSearch().exist(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}},"oath"));*/
        System.out.println(new WordSearch().exist(new char[][]{{'a','a'}},"aaa"));
    }

}
