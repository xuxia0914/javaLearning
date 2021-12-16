package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * <p>
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 */
public class ReversePairs {

    int result_int;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        result_int = 0;
        mergeSort(nums, 0, nums.length - 1);
        return result_int;
    }

    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int mid = (left + right) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        int index1 = left;
        int index2 = mid + 1;
        int index = 0;
        int[] tmp = new int[end - start + 1];
        while (index < end - start + 1) {
            if (index2 == end + 1) {
                tmp[index++] = nums[index1++];
            } else if (index1 == mid + 1) {
                tmp[index++] = nums[index2++];
            } else if (nums[index1] > nums[index2]) {
                result_int += end - index2 + 1;
                tmp[index++] = nums[index1++];
            } else {
                tmp[index++] = nums[index2++];
            }
        }
        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i - start];
        }
    }

    // 树状数组
    public int reversePairs1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int n = set.size();
        int[] tmp = new int[n];
        int idx = 0;
        for (int num : set) {
            tmp[idx++] = num;
        }
        Arrays.sort(tmp);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(tmp[i], i);
        }
        TreeArray ta = new TreeArray(n + 2);
        int ans = 0;
        for (int num : nums) {
            ans += ta.query(n + 1) - ta.query(map.get(num) + 1);
            ta.add(map.get(num) + 1, 1);
        }
        return ans;
    }

    class TreeArray {

        int[] tree;

        int n;

        public TreeArray(int n) {
            this.n = n;
            tree = new int[n];
        }

        public int lowBit(int x) {
            return x & (-x);
        }

        public void add(int x, int v) {
            while (x < n) {
                tree[x] += v;
                x += lowBit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x > 0) {
                ans += tree[x];
                x -= lowBit(x);
            }
            return ans;
        }

    }

}