package cn.leetcode.xux.general.midium;

/**
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 * where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
 * Example:
 * Input: [1,2,1,2,1,2,1]
 * Output: True
 * Explanation:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 * Note:
 * 1 <= n <= 2000.
 * Elements in the given array will be in range [-1,000,000, 1,000,000].
 */
public class SplitArrayWithEqualSum {

    public static boolean splitArray(int[] nums) {
        if(nums==null||nums.length<7) {
            return false;
        }
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i=1; i<len;++i) {
            sums[i] = sums[i-1]+nums[i];
        }
        int left1, left2, right1, right2;
        for(int j=3;j<len-3;j++) {
            for(int i=1;i<j-1;i++) {
                left1 = sums[i-1];
                left2 = sums[j-1]-sums[i];
                if(left1==left2) {
                    for(int k=j+2;k<len-1;k++) {
                        right1 = sums[k-1]-sums[j];
                        right2 = sums[len-1]-sums[k];
                        if(right1==right2&&right1==left1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{1,2,1,2,1,2,2}));
    }

}
