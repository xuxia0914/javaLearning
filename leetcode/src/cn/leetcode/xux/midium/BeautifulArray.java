package cn.leetcode.xux.midium;

/**
 * 932. 漂亮数组
 * 对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：
 * 对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。
 * 那么数组 A 是漂亮数组。
 * 给定 N，返回任意漂亮数组 A（保证存在一个）。
 *
 * 示例 1：
 * 输入：4
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：5
 * 输出：[3,1,2,5,4]
 *
 * 提示：
 * 1 <= N <= 1000
 */
public class BeautifulArray {

    public static void main(String[] args) {
        new BeautifulArray().beautifulArray(4);
    }

    public int[] beautifulArray(int N) {
        int[] dp = new int[]{1};
        int n = 1;
        while(n<N) {
            int[] newDp = new int[2*n];
            for(int i=0;i<n;i++) {
                newDp[i] = dp[i]*2-1;
                newDp[i+n] = dp[i]*2;
            }
            n *= 2;
            dp = newDp;
        }
        int[] result = new int[N];
        int idx = 0;
        for(int i=0;i<n&&idx<N;i++) {
            if(dp[i]<=N) {
                result[idx++] = dp[i];
            }
        }
        return result;
    }

}
