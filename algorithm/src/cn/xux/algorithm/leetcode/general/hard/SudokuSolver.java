package cn.xux.algorithm.leetcode.general.hard;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * 一个数独。
 * 答案被标成红色。
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class SudokuSolver {

    boolean isDone = false;

    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    public boolean dfs(char[][] board) {
        int i = -1;
        int j = -1;
        for(int a=0;a<81;a++) {
            if(board[a/9][a%9]=='.') {
                i = a/9;
                j = a%9;
                break;
            }
        }
        if(i==-1) {
            return true;
        }
        boolean[] flags =  new boolean[10];
        for(int x=0;x<9;x++) {
            flags[board[x][j]-'0'] = true;
            flags[board[i][x]-'0'] = true;
            flags[board[i/3*3+x/3][j/3*3+x%3]-'0'] = true;
        }
        for(int next=1;next<10;next++) {
            if(!flags[next]) {
                board[i][j] = (char)(next+'0');
                if(dfs(board)) {
                    return true;
                }
                board[i][j] = '.';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] sodu = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        SudokuSolver ss = new SudokuSolver();
        ss.solveSudoku(sodu);
        System.out.println("");
    }

}
