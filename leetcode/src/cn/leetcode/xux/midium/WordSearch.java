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
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(helper(board, new boolean[m][n], word, 0, i, j)) {
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
