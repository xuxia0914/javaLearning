package cn.leetcode.xux.general.midium;

/**
 * 5433. n 的第 k 个因子
 * 给你两个正整数 n 和 k 。
 * 如果正整数 i 满足 n % i == 0 ，那么我们就说正整数 i 是整数 n 的因子。
 * 考虑整数 n 的所有因子，将它们 升序排列 。请你返回第 k 个因子。
 * 如果 n 的因子数少于 k ，请你返回 -1 。
 *
 * 示例 1：
 * 输入：n = 12, k = 3
 * 输出：3
 * 解释：因子列表包括 [1, 2, 3, 4, 6, 12]，第 3 个因子是 3 。
 *
 * 示例 2：
 * 输入：n = 7, k = 2
 * 输出：7
 * 解释：因子列表包括 [1, 7] ，第 2 个因子是 7 。
 *
 * 示例 3：
 * 输入：n = 4, k = 4
 * 输出：-1
 * 解释：因子列表包括 [1, 2, 4] ，只有 3 个因子，所以我们应该返回 -1 。
 *
 * 示例 4：
 * 输入：n = 1, k = 1
 * 输出：1
 * 解释：因子列表包括 [1] ，第 1 个因子为 1 。
 *
 * 示例 5：
 * 输入：n = 1000, k = 3
 * 输出：4
 * 解释：因子列表包括 [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000] 。
 *
 * 提示：
 * 1 <= k <= n <= 1000
 */
public class TheKthFactorOfN {

    public static void main(String[] args) {
        System.out.println(new TheKthFactorOfN().kthFactor(1,1));
    }

    public int kthFactor(int n, int k) {
        int mid = (int)Math.sqrt(n);
        int cntMid = 0;
        int preEnd = mid;
        if(mid*mid==n) {
            cntMid++;
            preEnd--;
        }
        int cntPre = 0;
        int[] mem = new int[mid+1];
        for(int i=1;i<=preEnd;i++) {
            if(n%i==0) {
                cntPre++;
                if(cntPre==k) {
                    return i;
                }
                mem[cntPre] = i;
            }
        }
        if(k>cntPre*2+cntMid) {
            return -1;
        }else if(k==cntPre+1&&cntMid==1) {
            return mid;
        }else {
            return n/mem[2*cntPre-k+cntMid+1];
        }
    }

}
