package cn.xux.algorithm.leetcode.general.hard;

/**
 * 552. 学生出勤记录 II
 * 给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。
 * 学生出勤记录是只包含以下三个字符的字符串：
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。
 *
 * 示例 1:
 * 输入: n = 2
 * 输出: 8
 * 解释：
 * 有8个长度为2的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数超过一次。
 * 注意：n 的值不会超过100000。
 */
public class StudentAttendanceRecordII {

    int M = 1000000007;

    public int checkRecord(int n) {
        if(n<1) {
            return 0;
        }
        int[][] dp = new int[n][6];
        int[] first = new int[]{1, 1, 0, 1, 0, 0};
        dp[0] = first;
        int[] tmp;
        for(int i=1;i<n;i++) {
            int A0L0,A0L1,A0L2,A1L0,A1L1,A1L2;
            tmp = dp[i-1];
            A0L0 = ((tmp[0]+tmp[1])%M+tmp[2])%M;
            A0L1 = tmp[0];
            A0L2 = tmp[1];
            A1L0 = (((((tmp[0]+tmp[1])%M+tmp[2])%M+tmp[3])%M+tmp[4])%M+tmp[5])%M;
            A1L1 = tmp[3];
            A1L2 = tmp[4];
            dp[i] = new int[]{A0L0,A0L1,A0L2,A1L0,A1L1,A1L2};
        }
        return (((((dp[n-1][0]+dp[n-1][1])%M+dp[n-1][2])%M+dp[n-1][3])%M+dp[n-1][4])%M+dp[n-1][5])%M;
    }

    public int checkRecord1(int n) {
        if(n == 1) return 3;
        // has 'A' , 0: not end with 'L' , 1: end with 'L'
        long[][] A = new long[n+1][2];
        // without 'A' ，0: not end with 'L' , 1: end with 'L'
        long[][] B = new long[n+1][2];
        // 返回 A[n][0] + A[n][1] + B[n][0] + B[n][1]
        A[1][0] = 1;A[1][1] = 0;B[1][0] = 1;B[1][1] = 1;
        A[2][0] = 3;A[2][1] = 1;B[2][0] = 2;B[2][1] = 2;
        for(int i=3;i<=n;i++) {
            long tmpA0 = B[i-1][0] + B[i-1][1] + A[i-1][0] + A[i-1][1];
            long tmpA1 = 2*A[i-2][0] +A[i-2][1]+ B[i-2][0]+B[i-2][1];
            long tmpB0 = B[i-1][0] + B[i-1][1];
            long tmpB1 = 2*B[i-2][0] + B[i-2][1];
            A[i][0] = tmpA0%M;
            A[i][1] = tmpA1%M;
            B[i][0] = tmpB0%M;
            B[i][1] = tmpB1%M;
        }
        return (int) ((A[n][0] + A[n][1] + B[n][0] + B[n][1])%M);
    }

    public static void main(String[] args) {
        StudentAttendanceRecordII sa = new StudentAttendanceRecordII();
        System.out.println(sa.checkRecord(2));  //6
        System.out.println(sa.checkRecord(100));   //985598218
    }

}
