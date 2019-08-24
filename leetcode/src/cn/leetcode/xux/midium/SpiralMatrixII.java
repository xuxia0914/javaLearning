package cn.leetcode.xux.midium;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        if(n<1) {
            return new int[0][0];
        }
        int[][] res = new int[n][n];
        int curr = 1;
        boolean cloumn = true;
        boolean cloumnAsc = true;
        int cloumnStart = 0;
        int cloumnEnd = n-1;
        boolean rowAsc = true;
        int rowStart = 0;
        int rowEnd = n-1;
        while(curr<=n*n) {
            if(cloumn) {
                if(cloumnAsc) {
                    for(int i=cloumnStart;i<=cloumnEnd;i++) {
                        res[rowStart][i] = curr;
                        curr++;
                    }
                    rowStart++;
                }else {
                    for(int i=cloumnEnd;i>=cloumnStart;i--) {
                        res[rowEnd][i] = curr;
                        curr++;
                    }
                    rowEnd--;
                }
                cloumnAsc = !cloumnAsc;
            }else {
                if(rowAsc) {
                    for(int i=rowStart;i<=rowEnd;i++) {
                        res[i][cloumnEnd] = curr;
                        curr++;
                    }
                    cloumnEnd--;
                }else {
                    for(int i=rowEnd;i>=rowStart;i--) {
                        res[i][cloumnStart] = curr;
                        curr++;
                    }
                    cloumnStart++;
                }
                rowAsc = !rowAsc;
            }
            cloumn = !cloumn;
        }
        return res;
    }

}
