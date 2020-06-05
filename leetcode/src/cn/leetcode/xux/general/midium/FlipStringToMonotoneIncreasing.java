package cn.leetcode.xux.general.midium;

/**
 * 926. 将字符串翻转到单调递增
 * 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，
 * 那么该字符串是单调递增的。
 * 我们给出一个由字符 '0' 和 '1' 组成的字符串 S，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。
 * 返回使 S 单调递增的最小翻转次数。
 *
 * 示例 1：
 * 输入："00110"
 * 输出：1
 * 解释：我们翻转最后一位得到 00111.
 *
 * 示例 2：
 * 输入："010110"
 * 输出：2
 * 解释：我们翻转得到 011111，或者是 000111。
 *
 * 示例 3：
 * 输入："00011000"
 * 输出：2
 * 解释：我们翻转得到 00000000。
 *
 * 提示：
 * 1 <= S.length <= 20000
 * S 中只包含字符 '0' 和 '1'
 */
public class FlipStringToMonotoneIncreasing {

    public int minFlipsMonoIncr(String S) {
        if(S==null||S.length()<2) {
            return 0;
        }
        int len = S.length();
        //dp[i][0]表示到索引i为止，第i个字符为0时需要修改的次数
        //dp[i][1]表示到索引i为止，第i个字符为1时需要修改的次数
        int[][] dp = new int[len][2];
        if(S.charAt(0)=='0') {
            dp[0][1] = 1;
        }else {
            dp[0][0] = 1;
        }
        for(int i=1;i<len;i++) {
            if(S.charAt(i)=='0') {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1])+1;
            }else {
                dp[i][0] = dp[i-1][0]+1;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            }
        }
        return Math.min(dp[len-1][0], dp[len-1][1]);
    }

    public int minFlipsMonoIncr1(String S) {
        if(S==null||S.length()<2) {
            return 0;
        }
        int len = S.length();
        int pre0 = 0;
        int pre1 = 0;
        if(S.charAt(0)=='0') {
            pre1 = 1;
        }else {
            pre0 = 1;
        }
        for(int i=1;i<len;i++) {
            if(S.charAt(i)=='0') {
                pre1 = Math.min(pre0, pre1)+1;
            }else {
                pre1 = Math.min(pre0, pre1);
                pre0++;
            }
        }
        return Math.min(pre0, pre1);
    }

}
