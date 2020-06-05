package cn.leetcode.xux.general.hard;

/**
 *Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by the character '.'.
 *
 * You may assume that there will be only one unique solution.
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
