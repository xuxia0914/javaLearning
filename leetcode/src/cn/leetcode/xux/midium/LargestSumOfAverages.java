package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 813. 最大平均值和的分组
 * 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。
 * 计算我们所能得到的最大分数是多少。
 * 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
 *
 * 示例:
 * 输入:
 * A = [9,1,2,3,9]
 * K = 3
 * 输出: 20
 * 解释:
 * A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * 我们也可以把 A 分成[9, 1], [2], [3, 9].
 * 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 *
 * 说明:
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * 答案误差在 10^-6 内被视为是正确的。
 */
public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] A, int K) {
        if(A==null||A.length<K||K<1) {
            throw new IllegalStateException("illegal input");
        }
        int len = A.length;
        int[] sums = new int[len+1];
        for(int i=0;i<len;i++) {
            sums[i+1] = sums[i]+A[i];
        }
        double[] dp = new double[len];
        for(int i=0;i<len;i++) {
            dp[i] = (double)(sums[len]-sums[i])/(len-i);
        }
        for(int k=2;k<=K;k++) {
            for(int i=0;i<len;i++) {
                for(int j=i+1;j<len-k+2;j++) {
                    dp[i] = Math.max(dp[i], (double)(sums[j]-sums[i])/(j-i)+dp[j]);
                }
            }
        }
        return dp[0];
    }

}
