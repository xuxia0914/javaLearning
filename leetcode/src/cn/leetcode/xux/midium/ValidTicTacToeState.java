package cn.leetcode.xux.midium;

/**
 * A Tic-Tac-Toe board is given as a string array board.
 * Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 * The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
 * Here are the rules of Tic-Tac-Toe:
 * Players take turns placing characters into empty squares (" ").
 * The first player always places "X" characters, while the second player always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Example 1:
 * Input: board = ["O  ", "   ", "   "]
 * Output: false
 * Explanation: The first player always plays "X".
 * Example 2:
 * Input: board = ["XOX", " X ", "   "]
 * Output: false
 * Explanation: Players take turns making moves.
 * Example 3:
 * Input: board = ["XXX", "   ", "OOO"]
 * Output: false
 * Example 4:
 * Input: board = ["XOX", "O O", "XOX"]
 * Output: true
 * Note:
 * board is a length-3 array of strings, where each string board[i] has length 3.
 * Each board[i][j] is a character in the set {" ", "X", "O"}.
 */
public class ValidTicTacToeState {

    public boolean validTicTacToe(String[] board) {
        int numX = 0;
        int numO = 0;
        for(String s : board) {
            for(int i=0;i<3;i++) {
                if(s.charAt(i)=='X') {
                    numX++;
                }
                if(s.charAt(i)=='O') {
                    numO++;
                }
            }
        }
        if(numX!=numO&&numX!=numO+1) {
            return false;
        }
        boolean endX = false;
        boolean endO = false;
        for(String s : board) {
            if(s.equals("XXX")) {
                endX = true;
            }
            if(s.equals("OOO")) {
                endO = true;
            }
        }
        for(int i=0;i<3;i++) {
            if(board[0].charAt(i)=='X'&&board[1].charAt(i)=='X'&&board[2].charAt(i)=='X') {
                endX = true;
            }
            if(board[0].charAt(i)=='0'&&board[1].charAt(i)=='O'&&board[2].charAt(i)=='O') {
                endO = true;
            }
        }
        if(board[0].charAt(0)=='X'&&board[1].charAt(1)=='X'&&board[2].charAt(2)=='X') {
            endX = true;
        }
        if(board[0].charAt(0)=='O'&&board[1].charAt(1)=='O'&&board[2].charAt(2)=='O') {
            endO = true;
        }
        if(board[0].charAt(2)=='X'&&board[1].charAt(1)=='X'&&board[2].charAt(0)=='X') {
            endX = true;
        }
        if(board[0].charAt(2)=='O'&&board[1].charAt(1)=='O'&&board[2].charAt(0)=='O') {
            endO = true;
        }
        if(endX&&endO) {
            return false;
        }
        if(endX&&numX!=numO+1) {
            return false;
        }
        if(endO&&numX!=numO) {
            return false;
        }
        return true;
    }

}
