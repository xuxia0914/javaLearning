package cn.leetcode.xux.midium;

/**
 * 259 3Sum Smaller 三数之和较小值
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 *
 * Follow up:
 * Could you solve it in O(n2) runtime?
 */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        if(nums==null||nums.length<3) {
            return 0;
        }
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
