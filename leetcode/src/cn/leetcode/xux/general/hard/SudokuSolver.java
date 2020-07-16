package cn.leetcode.xux.general.hard;

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
        int x = 0;
        int y = 0;
        for(int i=0;i<81;i++) { //找出第一个'.'的索引
            if(board[i/9][i%9]=='.') {
                x = i/9;
                y = i%9;
                break;
            }
            if(i==80) { //数独完成
                isDone = true;
                return;
            }
        }
        boolean[] flags = new boolean[9];   //当前位置可以填的数字
        for(int i=0;i<9;i++) {
            if(board[x][i]!='.') {
                flags[board[x][i]-'1'] = true;
            }
            if(board[i][y]!='.') {
                flags[board[i][y]-'1'] = true;
            }
            if(board[x/3*3+i/3][y/3*3+i%3]!='.') {
                flags[board[x/3*3+i/3][y/3*3+i%3]-'1'] = true;
            }
        }
        for(int i=0;i<9;i++) {  //遍历-递归
            if(!flags[i]) {
                board[x][y] = (char)(i+'1');
                solveSudoku(board);
                if(isDone) {
                    return;
                }
                board[x][y] = '.';
            }
        }
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
