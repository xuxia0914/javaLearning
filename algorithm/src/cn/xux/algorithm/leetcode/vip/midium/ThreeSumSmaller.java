package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;

/**
 * 259 较小的三数之和
 * 给定一个长度为 n 的整数数组和一个目标值 target，
 * 寻找能够使条件 nums[i] + nums[j] + nums[k] < target
 * 成立的三元组 i, j, k 个数（0 <= i < j < k < n）。
 *
 * 示例：
 * 输入: nums = [-2,0,1,3], target = 2
 * 输出: 2
 * 解释: 因为一共有两个三元组满足累加和小于 2:
 *      [-2,0,1]
 *      [-2,0,3]
 * 进阶：是否能在 O(n2) 的时间复杂度内解决？
 */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        if(nums==null||nums.length<3) {
            return 0;
        }
        Arrays.sort(nums);
        int n= nums.length;
        int res = 0;
        int left;
        int right;
        for(int i=0;i<n-2;i++) {
            left = i+1;
            right = n-1;
            while(left<right) {
                if(nums[i]+nums[left]+nums[right]<target) {
                    res += right-left;
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumSmaller tss = new ThreeSumSmaller();
        System.out.println(tss.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
    }

}
