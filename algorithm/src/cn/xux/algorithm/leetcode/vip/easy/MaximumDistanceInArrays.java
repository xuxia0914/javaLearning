package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 624 数组列表中的最大距离
 * 给 m 个数组, 每一个数组均为升序.
 * 现在你可以从两个不同的数组中挑选两个整数(每一个数组选一个)并且计算差值.
 * 我们将两个整数 a 和 b 之间的差定义为它们的绝对差 |a - b|.
 * 你的任务是去找到最大的差值.
 *
 * 样例1
 * 输入：[[1,2,3], [4,5], [1,2,3]]
 * 输出：4
 * 解释：
 * 获得最大差值的一种方式是在第一个数组或第三个数组中取 1, 在第二个数组中取 5.
 *
 * 样例2
 * 输入：[[1,2,3,4,5,6,7,8,9],[0,10]]
 * 输出：9
 *
 * 注意事项
 * 每一个给出的数组长度至少为 1. 至少有两个不为空的数组
 * m 个数组中所有整数的个数和在 [2, 10000]范围内.
 * m 个数组中所有的整数均将在[-10000, 10000]范围内.
 */
public class MaximumDistanceInArrays {

    public int maxDiff(int[][] arrs) {
        // write your code here
        int preMin = arrs[0][0];
        int preMax = arrs[0][arrs[0].length-1];
        int ans = 0;
        for(int i=1;i<arrs.length;i++) {
            int[] arr = arrs[i];
            int min = arr[0];
            int max = arr[arr.length-1];
            ans = Math.max(ans, max-preMin);
            ans = Math.max(ans, preMax-min);
            preMax = Math.max(preMax, max);
            preMin = Math.min(preMin, min);
        }
        return ans;
    }

}
