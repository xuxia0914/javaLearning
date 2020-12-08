package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1681. 最小不兼容性
 * 给你一个整数数组 nums​​​ 和一个整数 k 。
 * 你需要将这个数组划分到 k 个相同大小的子集中，
 * 使得同一个子集里面没有两个相同的元素。
 * 一个子集的 不兼容性 是该子集里面最大值和最小值的差。
 * 请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，
 * 如果无法分成分成 k 个子集，返回 -1 。
 * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
 *
 * 示例 1：
 * 输入：nums = [1,2,1,4], k = 2
 * 输出：4
 * 解释：最优的分配是 [1,2] 和 [1,4] 。
 * 不兼容性和为 (2-1) + (4-1) = 4 。
 * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
 *
 * 示例 2：
 * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
 * 输出：6
 * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
 * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
 *
 * 示例 3：
 * 输入：nums = [5,3,3,6,3,3], k = 3
 * 输出：-1
 * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
 *
 * 提示：
 * 1 <= k <= nums.length <= 16
 * nums.length 能被 k 整除。
 * 1 <= nums[i] <= nums.length
 */
public class MinimumIncompatibility {

    public static void main(String[] args) {
        //[4,10,4,7,9,10,4,6,9,10] 5
        //13
        System.out.println(new MinimumIncompatibility().minimumIncompatibility(new int[]{4,10,4,7,9,10,4,6,9,10}, 5));
    }

    // O(4^n) TLE
    public int minimumIncompatibility(int[] nums, int k) {
        // 特殊情况 nums.length==k
        if(nums.length==k) {
            return 0;
        }
        int n = nums.length;
        int[] cnts = new int[n+1];
        for(int num : nums) {
            if(++cnts[num]>k) {
                return -1;
            }
        }
        // 特殊情况 k==1;
        if(k==1) {
            int min = nums[0];
            int max = nums[0];
            for(int num : nums) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            return max-min;
        }

        // 每个子集的长度
        int subLen = n/k;
        // dp[i]: i的二进制表示中，1对应的nums的元素的所有符合子集的不兼容性的和的最小值
        int[] dp = new int[(int)Math.pow(2, n)];
        // 记录所有不含重复数字的长度为subLen的子集的数(即dp的索引)
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<dp.length;i++) {
            // 计算 i 的二进制表示中1的个数
            int cnt = 0;
            int tmp = i;

            while(tmp>0) {
                tmp &= (tmp-1);
                cnt++;
            }
            if(cnt==subLen) {
                // 如果 i 映射的nums的元素个数是subLen，计算当前子数组的最大值和最小值的差（前提是不包含相等的元素）
                int min = n;
                int max = 0;
                int idx = n-1;
                tmp = i;
                Set<Integer> set = new HashSet<>();
                boolean flag = true;
                while(tmp>0) {
                    if((tmp&1)==1) {
                        if(!set.add(nums[idx])) {
                            flag = false;
                            break;
                        }else {
                            min = Math.min(min, nums[idx]);
                            max = Math.max(max, nums[idx]);
                        }
                    }
                    tmp >>= 1;
                    idx--;
                }
                if(!flag) {
                    continue;
                }
                dp[i] = max-min;
                list.add(i);
            }else if(cnt>0&&cnt%subLen==0) {
                // 如果 i 映射的nums的元素个数是subLen的倍数，计算当前子集的最小不兼容性

                // 这个遍历可以优化为只遍历长度为subLen的子集(即list里元素)，减少重复运算，不然会TLE
                // for(int j=0;j<i;j++) {
                for(int j : list) {
                    // j和i^j是组成i的两个子集的判断条件
                    if(dp[j]>0&&dp[i^j]>0&&(j&(i^j))==0) {
                        dp[i] = dp[i]==0?(dp[j]+dp[i^j]):Math.min(dp[i], dp[j]+dp[i^j]);
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

}
