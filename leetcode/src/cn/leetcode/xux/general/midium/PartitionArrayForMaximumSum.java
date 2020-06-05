package cn.leetcode.xux.general.midium;

/**
 * 1043. 分隔数组以得到最大和
 * 给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。
 * 分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。
 * 返回给定数组完成分隔后的最大和。
 *
 * 示例：
 * 输入：A = [1,15,7,9,2,5,10], K = 3
 * 输出：84
 * 解释：A 变为 [15,15,15,9,10,10,10]
 *
 * 提示：
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class PartitionArrayForMaximumSum {

    public static void main(String[] args) {
        System.out.println(new PartitionArrayForMaximumSum().maxSumAfterPartitioning(
                new int[]{1,15,7,9,2,5,10},
                3
        ));
    }

    public int maxSumAfterPartitioning(int[] A, int K) {
        if(A==null||A.length==0) {
            return 0;
        }
        int len = A.length;
        int[] dp = new int[len+1];
        for(int i=1;i<=len;i++) {
            int max = A[i-1];
            dp[i] = dp[i-1]+A[i-1];
            int j = i-1;
            while(j>0&&i-j+1<=K) {
                max = Math.max(max, A[j-1]);
                dp[i] = Math.max(dp[i], dp[j-1]+(i-j+1)*max);
                j--;
            }
        }
        return dp[len];
    }

}
