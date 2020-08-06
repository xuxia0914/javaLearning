package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1477. 找两个和为目标值且不重叠的子数组
 * 给你一个整数数组 arr 和一个整数值 target 。
 * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。
 * 可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 *
 * 示例 1：
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 *
 * 示例 2：
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），
 * 但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
 *
 * 示例 3：
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 *
 * 示例 4：
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 *
 * 示例 5：
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 */
public class FindTwoNonOverlappingSubArraysEachWithTargetSum {

    public int minSumOfLengths(int[] arr, int target) {
        int left = 0;
        int currSum = 0;
        //到arr[key]为止 符合条件的子数组的最小长度
        int n = arr.length;
        int[] minLen = new int[n];
        int ans = -1;
        for(int right=0;right<arr.length;right++) {
            currSum += arr[right];
            while (currSum > target) {
                currSum -= arr[left++];
            }
            if (currSum == target) {
                if(left>0&&minLen[left-1]!=0) {
                    if(ans==-1) {
                        ans = minLen[left-1]+right-left+1;
                    }else {
                        ans = Math.min(ans, minLen[left-1]+right-left+1);
                    }
                }
                minLen[right] = right>0&&minLen[right-1]!=0?
                        Math.min(minLen[right-1], right-left+1):right-left+1;
            }else if(right>0) {
                minLen[right] = minLen[right-1];
            }
        }
        return ans;
    }

}
