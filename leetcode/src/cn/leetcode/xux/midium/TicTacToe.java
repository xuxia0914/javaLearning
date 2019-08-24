package cn.leetcode.xux.midium;

/**
 * Design Tic-Tac-Toe设计井字棋游戏
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * You may assume the following rules:
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * TicTacToe toe = new TicTacToe(3);
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | | // Player 1 makes a move at (0, 0).
 * | | | |
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | | // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | | // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| | // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| | // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| | // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| | // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 *
 * Hint:
 *
 * Could you trade extra space such that move() operation can be done in O(1)?
 * You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 */
public class TicTacToe {

    char[][] board;
    int n;

    public TicTacToe(int n) {
        this.board = new char[n][n];
        this.n = n;
    }

    public int move(int i, int j, int player) {
        char c;
        if(player==1) {
            this.board[i][j] = 'X';
            c = 'X';
        }else {
            this.board[i][j] = 'O';
            c = 'O';
        }
        for(int k=0;k<this.n;k++) {
            if(this.board[i][k]!=c) {
                break;
            }
            if(k==this.n-1) {
                return player;
            }
        }
        for(int k=0;k<this.n;k++) {
            if(this.board[k][j]!=c) {
                break;
            }
            if(k==this.n-1) {
                return player;
            }
        }
        if(i==j) {
            for(int k=0;k<this.n;k++) {
                if(this.board[k][k]!=c) {
                    break;
                }
                if(k==this.n-1) {
                    return player;
                }
            }
        }
        if(i==this.n-j) {
            for(int k=0;k<this.n;k++) {
                if(this.board[k][this.n-k]!=c) {
                    break;
                }
                if(k==this.n-1) {
                    return player;
                }
            }
        }
        return 0;
    }

}
