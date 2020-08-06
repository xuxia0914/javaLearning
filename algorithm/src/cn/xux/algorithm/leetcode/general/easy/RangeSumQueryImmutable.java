package cn.xux.algorithm.leetcode.general.easy;

public class RangeSumQueryImmutable {

    int[] sums;

    public RangeSumQueryImmutable(int[] nums) {
        if(nums==null||nums.length<2) {
            this.sums = nums;
            return;
        }
        this.sums = new int[nums.length];
        this.sums[0] = nums[0];
        for(int i=1;i<nums.length;i++) {
            this.sums[i] = this.sums[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if(i<0||i>j||this.sums==null||this.sums.length==0) {
            return 0;
        }
        return i==0?sums[j]:sums[j]-sums[i-1];
    }

}
