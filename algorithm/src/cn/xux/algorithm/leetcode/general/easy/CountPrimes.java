package cn.xux.algorithm.leetcode.general.easy;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *
 * 提示：
 * 0 <= n <= 5 * 106
 */
public class CountPrimes {

    public int countPrimes(int n) {
        if(n<2) {
            return 0;
        }
        int result = 0;
        boolean[] flags = new boolean[n];
        for(int i=2;i<n;i++) {
            if(!flags[i]) {
                result++;
                for(int j=2;i*j<n;j++) {
                    flags[i*j] = true;
                }
            }
        }
        return result;
    }

}
