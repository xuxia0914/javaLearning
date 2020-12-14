package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1621. 大小为 K 的不重叠线段的数目
 * 给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x = i 处，
 * 请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。
 * 线段的两个端点必须都是 整数坐标 。这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
 * 请你返回 k 个不重叠线段的方案数。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：5
 * 解释：
 * 如图所示，两个线段分别用红色和蓝色标出。
 * 上图展示了 5 种不同的方案 {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。
 *
 * 示例 2：
 * 输入：n = 3, k = 1
 * 输出：3
 * 解释：总共有 3 种不同的方案 {(0,1)}, {(0,2)}, {(1,2)} 。
 *
 * 示例 3：
 * 输入：n = 30, k = 7
 * 输出：796297179
 * 解释：画 7 条线段的总方案数为 3796297200 种。将这个数对 109 + 7 取余得到 796297179 。
 *
 * 示例 4：
 * 输入：n = 5, k = 3
 * 输出：7
 *
 * 示例 5：
 * 输入：n = 3, k = 2
 * 输出：1
 *
 * 提示：
 * 2 <= n <= 1000
 * 1 <= k <= n-1
 */
public class NumberOfSetsOfKNonOverlappingLineSegments {

    public static void main(String[] args) {
        NumberOfSetsOfKNonOverlappingLineSegments ns
                = new NumberOfSetsOfKNonOverlappingLineSegments();
        System.out.println(ns.numberOfSets(4, 2));
        System.out.println(ns.numberOfSets(3, 1));
        System.out.println(ns.numberOfSets(30, 7));
        System.out.println(ns.numberOfSets(5, 3));
        System.out.println(ns.numberOfSets(3, 2));
    }

    int mod = 1000000007;

    public int numberOfSets(int n, int k) {
        // dp1[i][j]: i+1个点(即线段总长为i)，j个子线段，最后一个端点必须被使用
        long[][] dp1 = new long[n][k+1];
        // dp2[i][j]: i+1个点(即线段总长为i)，j个子线段，最后一个端点必须不使用
        long[][] dp2 = new long[n][k+1];
        dp1[0][0] = 1;
        for(int i=1;i<n;i++) {
            dp1[i][0] = 1;
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<=k;j++) {
                dp2[i][j] = (dp1[i-1][j]+dp2[i-1][j])%mod;
                dp1[i][j] = (dp1[i-1][j-1]+dp2[i-1][j-1]+dp1[i-1][j])%mod;
            }
        }
        return (int)((dp1[n-1][k]+dp2[n-1][k])%mod);
    }

}
