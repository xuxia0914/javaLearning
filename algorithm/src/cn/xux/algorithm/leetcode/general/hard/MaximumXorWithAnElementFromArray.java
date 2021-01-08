package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。
 * 另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。
 * 换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。
 * 如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，
 * 其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 *
 * 示例 1：
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 *
 * 示例 2：
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 *
 * 提示：
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 */
public class MaximumXorWithAnElementFromArray {
    //输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
    //输出：[15,-1,5]
    public static void main(String[] args) {
        new MaximumXorWithAnElementFromArray().maximizeXor(
                new int[]{5,2,4,6,6,3},
                new int[][]{{12,4},{8,1},{6,3}});
    }

    // TLE TODO
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Arrays.sort(nums);
        for(int i=0;i<n;i++) {
            int[] tmp = nums.clone();
            int idx = Arrays.binarySearch(tmp, queries[i][1]);
            if(idx<0) {
                idx = -idx-2;
            }
            if(idx>=0) {
                ans[i] = quickQuery(tmp, 0, idx, queries[i][0])^queries[i][0];
            }
        }
        return ans;
    }

    private int quickQuery(int[] nums, int start, int end, int q) {
        if(start==end) {
            return nums[start];
        }
        int l = start;
        int r = end;
        int key = nums[l];
        while(l<r) {
            while(l<r&&(nums[r]^q)>=(key^q)) {
                r--;
            }
            while(l<r&&(nums[l]^q)<=(key^q)) {
                l++;
            }
            if(l<r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
        }
        nums[start] = nums[l];
        nums[l] = key;
        if(l==end) {
            return key;
        }else {
            return quickQuery(nums, r+1, end, q);
        }
    }

    // 暴力解法 TLE
    public int[] maximizeXor1(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for(int i=0;i<n;i++) {
            for(int num : nums) {
                if(num<=queries[i][1]) {
                    ans[i] = Math.max(ans[i], queries[i][0]^num);
                }
            }
        }
        return ans;
    }

}