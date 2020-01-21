package cn.leetcode.xux.midium;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 *
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if(nums==null||nums.length<2) {
            return;
        }
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(left<n&&nums[left]==0) {
            left++;
        }
        while(right>=0&&nums[right]==2) {
            right--;
        }
        int i=left;
        while(i<=right) {
            if(nums[i]==1) {
                i++;
            }else {
                if(nums[i]==0) {
                    swap(nums, left++, i);
                }else {
                    swap(nums, i, right--);
                }
                while(left<n&&nums[left]==0) {
                    left++;
                }
                while(right>=0&&nums[right]==2) {
                    right--;
                }
                i=left;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
//        int[] colors = new int[]{2, 0, 2, 0, 1, 1, 2, 0, 2, 1, 0};
        int[] colors = new int[]{0,0};
        new SortColors().sortColors(colors);
        for(int i : colors) {
            System.out.print(i+" ");
        }
    }

}
