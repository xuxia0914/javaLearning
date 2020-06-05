package cn.leetcode.xux.general.midium;

/**
 * 题目大意：
 * 模拟实现游戏“糖果粉碎传奇”
 * 给定二维数组board，每行、列中3个或以上连续相同的数字都可以被消去（变为0表示空位），消去后位于上方的数字会填充空位。
 * 重复直到没有更多的数字可以被消去。
 * 解题思路：
 * 模拟题
 * 循环，把拟被消去的数字替换为其相反数，遍历每一列，自底向上遍历每一行，将负数消去。
 * 当某一次消去未找到任何数字时，循环终止。
 */
public class CandyCrush {

    public void crush(int[][] board) {
        if(board==null||(board.length<3&&board[0].length<3)) {
            return;
        }
        int m = board.length;
        int n = board[0].length;

        System.out.println("\r\n");

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println("");
        }

        boolean isDone = true;

        for(int i=0;i<m;i++) {
            int pre = 0;
            int cnt = 0;
            for(int j=0;j<n;j++) {
                if(board[i][j]==0) {
                  continue;
                }
                if(Math.abs(board[i][j])==Math.abs(pre)) {
                    cnt++;
                }else {
                    if(cnt>=3) {
                        isDone = false;
                        while(cnt>0) {
                            board[i][j-cnt] = -Math.abs(board[i][j-cnt]);
                            cnt--;
                        }
                    }
                    cnt = 1;
                    pre = board[i][j];
                }
            }
            if(cnt>=3) {
                isDone = false;
                while(cnt>0) {
                    board[i][n-cnt] = -Math.abs(board[i][n-cnt]);
                    cnt--;
                }
            }
        }
        for(int i=0;i<n;i++) {
            int pre = 0;
            int cnt = 0;
            for(int j=0;j<m;j++) {
                if(board[j][i]==0) {
                    continue;
                }
                if(Math.abs(board[j][i])==Math.abs(pre)) {
                    cnt++;
                }else {
                    if(cnt>=3) {
                        isDone = false;
                        while(cnt>0) {
                            board[j-cnt][i] = -Math.abs(board[j-cnt][i]);
                            cnt--;
                        }
                    }
                    cnt = 1;
                    pre = board[j][i];
                }
            }
            if(cnt>=3) {
                isDone = false;
                while(cnt>0) {
                    board[m-cnt][i] = -Math.abs(board[m-cnt][i]);
                    cnt--;
                }
            }
        }
        for(int j=n-1;j>=0;j--) {
            int index = m-1;
            for(int i=m-1;i>=0;i--) {
                if(board[i][j]>0) {
                    board[index--][j] = board[i][j];
                }
            }
            while(index>=0) {
                board[index--][j] = 0;
            }
        }
        if(isDone) {
            return;
        }
        crush(board);
    }


    public static void main(String[] args) {
        CandyCrush cc = new CandyCrush();

        cc.crush(new int[][]{
                {5,  1,  512,3,  3  },
                {610,4,  1,  613,614},
                {710,1,  2,  713,714},
                {810,1,  2,  1,  1  },
                {1,  1,  2,  2,  2  },
                {4,  1,  4,  4, 1014}
        });
    }

}
