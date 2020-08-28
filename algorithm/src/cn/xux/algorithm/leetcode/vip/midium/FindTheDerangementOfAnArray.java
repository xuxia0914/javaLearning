package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 634. 寻找数组的错位排列
 * 在组合数学中，如果一个排列中所有元素都不在原先的位置上，那么这个排列就被称为错位排列。
 * 给定一个从 1 到 n 升序排列的数组，你可以计算出总共有多少个不同的错位排列吗？
 * 由于答案可能非常大，你只需要将答案对 109+7 取余输出即可。
 *
 * 样例 1:
 * 输入: 3
 * 输出: 2
 * 解释: 原始的数组为 [1,2,3]。
 * 两个错位排列的数组为 [2,3,1] 和 [3,1,2]。
 *
 * 注释:
 * n 的范围是 [1, 106]。
 */
public class FindTheDerangementOfAnArray {

    public int findDerangement(int n) {
        if(n==1) {
            return 0;
        }
        int mod = 1000000007;
        long pre1 = 0;
        long pre2 = 1;
        for(int i=3;i<=n;i++) {
            long tmp = (pre2+pre1)*(i-1)%mod;
            pre1 = pre2;
            pre2 = tmp;
        }
        return (int)pre2;
    }

}
