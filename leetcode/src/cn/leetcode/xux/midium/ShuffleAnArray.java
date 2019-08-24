package cn.leetcode.xux.midium;

import java.util.Random;

/**
 * 384. 打乱数组
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 */
public class ShuffleAnArray {

    public int[] nums;
    Random random = new Random();

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return this.nums;
    }

    public int[] shuffle() {
        if(this.nums==null||this.nums.length<2) {
            return nums;
        }
        int[] a = this.nums.clone();
        for(int i=1;i<a.length;i++) {
            int x = random.nextInt(i+1);
            swap(a, i, x);
        }
        return a;
    }

    public void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
