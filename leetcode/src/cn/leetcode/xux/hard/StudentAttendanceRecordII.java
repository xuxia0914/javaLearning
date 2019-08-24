package cn.leetcode.xux.hard;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
 * A student attendance record is a string that only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 * Note: The value of n won't exceed 100,000.
 */
public class StudentAttendanceRecordII {

    static int M = 1000000007;

    /**n>28之后就不对了，不知道为什么*/
    public static int checkRecord(int n) {
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
            A0L0 = (tmp[0]+tmp[1]+tmp[2]);
            A0L1 = tmp[0];
            A0L2 = tmp[1];
            A1L0 = (tmp[0]+tmp[1]+tmp[2]+tmp[3]+tmp[4]+tmp[5]);
            A1L1 = tmp[3];
            A1L2 = tmp[4];
            dp[i] = new int[]{A0L0,A0L1,A0L2,A1L0,A1L1,A1L2};
        }
        return (dp[n-1][0]+dp[n-1][1]+dp[n-1][2]+dp[n-1][3]+dp[n-1][4]+dp[n-1][5])%M;
    }

    public static int checkRecord1(int n) {
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
        System.out.println(checkRecord(2));
        System.out.println(checkRecord(100));   //985598218
    }

}
