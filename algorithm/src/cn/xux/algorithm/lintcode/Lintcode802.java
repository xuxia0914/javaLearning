package cn.xux.algorithm.lintcode;

/**
 * 802. 数独
 * 中文English
 * 编写一个程序，通过填充空单元来解决数独难题。
 * 空单元由数字0表示。
 * 你可以认为只有一个唯一的解决方案。
 *
 * 样例
 * 样例 1：
 *
 * 给定的数独谜题:
 * [
 * [0,0,9,7,4,8,0,0,0],
 * [7,0,0,0,0,0,0,0,0],
 * [0,2,0,1,0,9,0,0,0],
 * [0,0,7,0,0,0,2,4,0],
 * [0,6,4,0,1,0,5,9,0],
 * [0,9,8,0,0,0,3,0,0],
 * [0,0,0,8,0,3,0,2,0],
 * [0,0,0,0,0,0,0,0,6],
 * [0,0,0,2,7,5,9,0,0]
 * ]
 *
 *
 * 返回结果：
 * [
 * [5,1,9,7,4,8,6,3,2],
 * [7,8,3,6,5,2,4,1,9],
 * [4,2,6,1,3,9,8,7,5],
 * [3,5,7,9,8,6,2,4,1],
 * [2,6,4,3,1,7,5,9,8],
 * [1,9,8,5,2,4,3,6,7],
 * [9,7,5,8,6,3,1,2,4],
 * [8,3,2,4,9,1,7,5,6],
 * [6,4,1,2,7,5,9,8,3]
 * ]
 */
public class Lintcode802 {

    public static void main(String[] args) {
        new Lintcode802().dfs(new int[][]{
                {0,0,9,7,4,8,0,0,0},
                {7,0,0,0,0,0,0,0,0},
                {0,2,0,1,0,9,0,0,0},
                {0,0,7,0,0,0,2,4,0},
                {0,6,4,0,1,0,5,9,0},
                {0,9,8,0,0,0,3,0,0},
                {0,0,0,8,0,3,0,2,0},
                {0,0,0,0,0,0,0,0,6},
                {0,0,0,2,7,5,9,0,0}});
    }

    /**
     * @param board: the sudoku puzzle
     * @return: nothing
     */
    public void solveSudoku(int[][] board) {
        // write your code here
        dfs(board);
    }

    public boolean dfs(int[][] board) {
        int i = -1;
        int j = -1;
        for(int a=0;a<81;a++) {
            if(board[a/9][a%9]==0) {
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
            flags[board[x][j]] = true;
            flags[board[i][x]] = true;
            flags[board[i/3*3+x/3][j/3*3+x%3]] = true;
        }
        for(int next=1;next<10;next++) {
            if(!flags[next]) {
                board[i][j] = next;
                if(dfs(board)) {
                    return true;
                }
                board[i][j] = 0;
            }
        }
        return false;
    }

}
