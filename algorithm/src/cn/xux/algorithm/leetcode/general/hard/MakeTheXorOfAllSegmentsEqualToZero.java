package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1787. 使所有区间的异或结果为零
 * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。
 * 区间 [left, right]（left <= right）的
 * 异或结果 是对下标位于 left 和 right（包括 left 和 right ）
 * 之间所有元素进行 XOR 运算的结果：
 * nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 *
 * 示例 1：
 * 输入：nums = [1,2,0,3,0], k = 1
 * 输出：3
 * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
 *
 * 示例 2：
 * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
 * 输出：3
 * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
 *
 * 示例 3：
 * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
 * 输出：3
 * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
 *
 * 提示：
 * 1 <= k <= nums.length <= 2000
 * ​​​​​​0 <= nums[i] < 2^10
 */
public class MakeTheXorOfAllSegmentsEqualToZero {

    public static void main(String[] args) {
        MakeTheXorOfAllSegmentsEqualToZero mt = new MakeTheXorOfAllSegmentsEqualToZero();
        System.out.println(mt.minChanges(new int[]{1,2,0,3,0}, 1)); //3
        System.out.println(mt.minChanges(new int[]{3,4,5,2,1,7,3,4,7}, 3)); //3
        System.out.println(mt.minChanges(new int[]{1,2,4,1,2,5,1,2,6}, 3)); //3
        System.out.println(mt.minChanges(new int[]{23,27,14,0,14,3,7,10,14,23,5,5}, 1));    //11
    }

    // dp + 贪心
    public int minChanges(int[] nums, int k) {
        //分析，要使所有长度为 k 的区间最终的异或结果为 0 ，可以列出
        /*
        第一行    a0、 a1、   a2    ... ak-1
        第二行    ak、 ak+1、 ak+2  ... a2k-1
        第三行    a2k、a2k+1、a2k+2 ... a3k-1
        第 i 行   ...
        最后一行  a(len-len%k)、a(len-len%k+1) ... a(len-1)

            可以按顺序分离出前 len / k 行长度为 k 的区间，以及长度为 len % k 的最后一个区间
            又有每个长度为 k 的区间异或结果为 0 ，有
                a0 = ak = a2k = ... = a(len - len % k)
                a1 = a(k + 1) = a(2k + 1) = ... = a(len - len % k + 1)

            依此类推，可以竖着看每一列，则最终每一列的元素都应该相同；且每一个区间为 k 的异或结果为 0

        由此可以诞生出两种情况：
            1) 每一列最终都更改为其本身列里已存在的数
            2) 存在某一列，最终更改为的数 x ，不在那列之中
        对于情况2，如果每一列都更改为自身那列已有的数，总代价应该是 每一列的元素数减去那一列的众数 的和，然而有一列更改的是未出现过的数，那一列的代价是 那一列的元素数，所以前后更改操作增加的量就是 那一列的众数，要使得这个增加量最小，说明这个众数得最小，所以 “要修改的那一列就是众数最小的那一列”

        对于情况1，就是一个动态规划：定义 dp[i][j] 为前 i 列的异或结果为 j 时最少的操作次数，则容易得到状态转移方程：
            dp[i][v ^ j] = dp[i - 1][j] + count(i) - freq[i][v]
        表示当前 i - 1 列的异或结果为 j 时，如果第 i 列所有元素修改成 v ( v 为出现过的元素)，那么前 i 列的异或结果为 v ^ j 。
        */

        int len = nums.length;
        int[] count = new int[k];//每一列的元素个数
        Map<Integer,Integer>[] freq = new Map[k];//每一列中出现的元素次数

        //初始化 count 和 freq
        for (int i = 0; i < k; ++i) {
            freq[i] = new HashMap<>();
        }
        for (int i = 0; i < len; ++i) {
            ++count[i % k];
            freq[i % k].put(nums[i], freq[i % k].getOrDefault(nums[i], 0) + 1);
        }

        //讨论情况1 进行动态规划
        int[][] dp = new int[k + 1][1024];
        for (int[] d : dp) {
            Arrays.fill(d, (int) 1e9);
        }
        dp[0][0] = 0;// i 从1开始，如果 i 为 0 ，此时异或和为 0 才是合法状态
        int res = 0, max = Integer.MIN_VALUE, minmax = Integer.MAX_VALUE;//贪心结果、每列众数、最小的众数
        for (int i = 1; i <= k; ++i) {
            max = Integer.MIN_VALUE;
            for (int j = 0; j < 1024; ++j) {
                max = Math.max(max, freq[i - 1].getOrDefault(j, 0));
                for (Map.Entry<Integer,Integer> entry : freq[i - 1].entrySet()) {
                    int key = entry.getKey();//值
                    int value = entry.getValue();//次数
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ key] + count[i - 1] - value);
                }
            }
            res += count[i - 1] - max;
            minmax = Math.min(max, minmax);
        }

        /*//讨论情况2 进行贪心 这个情况其实可以合在上面的循环中
        int res = 0, max = Integer.MIN_VALUE, minmax = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            max = Integer.MIN_VALUE;//每一列出现次数最少和最多的元素出现的次数
            for (int j = 0; j < 1024; ++j) {
                max = Math.max(max, freq[i][j]);
            }
            res += count[i] - max;
            minmax = Math.min(max, minmax);//所有列的众数中最小的众数
        }*/
        res += minmax;
        return Math.min(res, dp[k][0]);
    }

    // 优化后的dp
    public int minChanges1(int[] nums, int k) {
        //另一种就是不使用贪心，直接用dp，但是其中会有一些优化
        /*
            首先依然是定义 dp[i][j] 表示前 i 列异或结果为 j 时的最小操作次数，状态转移方程依然是：
                dp[i][j] = dp[i - 1][j ^ v] + count[i] - freq[i][v]
            分析可得，要遍历 k、j、v，总的时间复杂度是 2000 * 1024 * 1024，肯定超时。

            对于状态的转移，其实还有一个更加粗略的式子：
                dp[i][j] = dp[i - 1][v] + count[i]
            这个式子表示，对于任意的 dp[i - 1][v] ,总能修改第 i 列所有元素为某一个数字，使状态转移为 dp[i][j] 。
            对于 j 可能的取值，一部分是第 i 列中已存在的数，一部分是除此之外的数，我们实际上只用算已存在的数，除此之外的其实都是修改第 i 列的所有元素，这些只用算一次，不用都算一遍。
            那么可以预处理算出 当第 i 列都要修改时，异或结果为某个值 j 时的最小操作次数 dp[i][j] 为多少。
            怎么算呢？注意到 dp[i - 1][v] ，我们可以算出在前 i - 1 列中，对于任意的取值 v ，找到一个最小的操作次数 dp[i - 1][v] ，那么这个值加上 count[i] ，就是前 i 列的最小操作次数，这也是前 i 列所有最小操作次数的上限。算出这个，就可以在遍历 0 ~ 1023 中不是已存在的数时，直接使用此值即可；是已存在的，那就还得进一步用转移方程算出结果。
        */
        int len = nums.length;
        int[] count = new int[k];//每一列的元素个数
        Map<Integer,Integer>[] freq = new Map[k];//每一列中出现的元素次数

        //初始化 count 和 freq
        for (int i = 0; i < k; ++i) {
            freq[i] = new HashMap<>();
        }
        for (int i = 0; i < len; ++i) {
            ++count[i % k];
            freq[i % k].put(nums[i], freq[i % k].getOrDefault(nums[i], 0) + 1);
        }

        //进行动态规划
        int[][] dp = new int[k + 1][1024];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        dp[0][0] = 0;// i 从1开始，如果 i 为 0 ，此时异或和为 0 才是合法状态
        for (int i = 1; i <= k; ++i) {
            int minOperator = -1;
            for (int j = 0; j < 1024; ++j) {
                if (dp[i - 1][j] == -1) continue;
                if (minOperator == -1 || minOperator > dp[i - 1][j]) minOperator = dp[i - 1][j];
            }
            minOperator += count[i - 1];

            for (int j = 0; j < 1024; ++j) {
                if (dp[i][j] == -1) dp[i][j] = minOperator;//先全部都按照 第 i 列都更换的情况来预设值，也就是这个操作次数的上限，如果是出现过的次数，下面再算一遍
                if (dp[i - 1][j] == -1) continue;
                for (Map.Entry<Integer,Integer> entry : freq[i - 1].entrySet()) {
                    int key = entry.getKey();//值
                    int value = entry.getValue();//次数
                    int cost = dp[i - 1][j] + count[i - 1] - value;
                    if (dp[i][j ^ key] == -1 || dp[i][j ^ key] > cost) dp[i][j ^ key] = cost;
                }
            }
        }

        return dp[k][0];
    }

    // 纯dp TLE
    public int minChanges2(int[] nums, int k) {
        int maxXor = 1024;
        // dp[i]表示遍历到某组元素时异或结果为i的最小替换次数(迭代)
        int[] dp = new int[maxXor];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int n = nums.length;
        for(int i=0;i<k;i++) {
            // 当前组的元素个数
            int total = 0;
            // 每个元素出现的次数
            int[] freq = new int[maxXor];
            // 同一元素出现的最多次数most
            int most = 0;
            for(int j=i;j<n;j+=k) {
                freq[nums[j]]++;
                most = Math.max(most, freq[nums[j]]);
                total++;
            }
            // 该组的元素改为一个统一值f时，
            // 与原dp的下标j(如果dp[j]!=MAX_VALUE)异或后为newDp的下标x
            // newDp[x] = min(newDp[x],dp[j]+total-freq)
            int[] newDp = new int[maxXor];
            Arrays.fill(newDp, Integer.MAX_VALUE);
            for(int j=0;j<maxXor;j++) {
                if(dp[j]!=Integer.MAX_VALUE) {
                    for(int f=0;f<maxXor;f++) {
                        int x = f^j;
                        newDp[x] = Math.min(newDp[x], dp[j]+total-freq[f]);
                    }
                }
            }
            dp = newDp;
        }
        return dp[0];
    }

}
