package cn.leetcode.xux.midium;

/**
 * 540. 有序数组中的单一元素
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 */
public class SingleElementInASortedArray {

    public static void main(String[] args) {
        System.out.println(new SingleElementInASortedArray().singleNonDuplicate(new int[]{1,1,2,2,3}));
    }

    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(nums[mid]!=nums[mid-1]&&nums[mid]!=nums[mid+1]) {
                return nums[mid];
            }else {
                if(nums[mid]==nums[mid-1]) {
                    if((mid-1-left)%2==0) {
                        left = mid+1;
                    }else {
                        right = mid-2;
                    }
                }else {
                    if((mid-left)%2==0) {
                        left = mid+2;
                    }else {
                        right = mid-1;
                    }
                }
            }
        }
        return nums[left];
    }

}
