package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 723. 粉碎糖果（模拟）
 * 这个问题是实现一个简单的消除算法。
 * 给定一个二维整数数组 board 代表糖果所在的方格，
 * 不同的正整数 board[i][j] 代表不同种类的糖果，
 * 如果 board[i][j] = 0 代表 (i, j) 这个位置是空的。
 * 给定的方格是玩家移动后的游戏状态，现在需要你根据以下规则粉碎糖果，
 * 使得整个方格处于稳定状态并最终输出。
 * 如果有三个及以上水平或者垂直相连的同种糖果，同一时间将它们粉碎，即将这些位置变成空的。
 * 在同时粉碎掉这些糖果之后，如果有一个空的位置上方还有糖果，
 * 那么上方的糖果就会下落直到碰到下方的糖果或者底部，
 * 这些糖果都是同时下落，也不会有新的糖果从顶部出现并落下来。
 * 通过前两步的操作，可能又会出现可以粉碎的糖果，请继续重复前面的操作。
 * 当不存在可以粉碎的糖果，也就是状态稳定之后，请输出最终的状态。
 * 你需要模拟上述规则并使整个方格达到稳定状态，并输出。
 *
 * 样例 :
 * 输入:
 * board =
 * [[110,5,112,113,114],
 * [210,211,5,213,214],
 * [310,311,3,313,314],
 * [410,411,412,5,414],
 * [5,1,512,3,3],
 * [610,4,1,613,614],
 * [710,1,2,713,714],
 * [810,1,2,1,1],
 * [1,1,2,2,2],
 * [4,1,4,4,1014]]
 * 输出:
 * [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],
 * [110,0,0,0,114],[210,0,0,0,214],
 * [310,0,0,113,314],[410,0,0,213,414],
 * [610,211,112,313,614],[710,311,412,613,714],
 * [810,411,512,713,1014]]
 *
 * 注释 :
 * board 数组的行数区间是 [3, 50]。
 * board[i] 数组的长度区间（即 board 数组的列数区
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
