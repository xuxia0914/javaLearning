package cn.leetcode.xux.midium;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *     1.数字 1-9 在每一行只能出现一次。
 *     2.数字 1-9 在每一列只能出现一次。
 *     3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *     解题思路：注意题目中说的是只要当前已经填充的数字是合法的就可以，不一定要这个数独是有解的。
 *     依次判断已给定的矩阵每一行、每一列、以及每个小矩阵上是否是有重复数字即可；
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++) {
            boolean[] flags = new boolean[9];
            for(int j=0;j<9;j++) {
                if(board[i][j]!='.') {
                    if(flags[board[i][j]-'1']) {
                        return false;
                    }
                    flags[board[i][j]-'1'] = true;
                }
            }
        }

        for(int i=0;i<9;i++) {
            boolean[] flags = new boolean[9];
            for(int j=0;j<9;j++) {
                if(board[j][i]!='.') {
                    if(flags[board[j][i]-'1']) {
                        return false;
                    }
                    flags[board[j][i]-'1'] = true;
                }
            }
        }

        for(int i=0;i<9;i++) {
            boolean[] flags = new boolean[9];
            for(int j=0;j<9;j++) {
                if(board[i/3*3+j/3][i%3*3+j%3]!='.') {
                    if(flags[board[i/3*3+j/3][i%3*3+j%3]-'1']) {
                        return false;
                    }
                    flags[board[i/3*3+j/3][i%3*3+j%3]-'1'] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        System.out.println(vs.isValidSudoku(new char[][]{
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        }));
    }

}
