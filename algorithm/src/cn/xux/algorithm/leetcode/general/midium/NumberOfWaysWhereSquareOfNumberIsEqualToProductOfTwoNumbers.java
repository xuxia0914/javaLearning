package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1577. 数的平方等于两数乘积的方法数
 * 给你两个整数数组 nums1 和 nums2 ，
 * 请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：
 * 类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k]
 * 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
 * 类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k]
 * 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
 *
 * 示例 1：
 * 输入：nums1 = [7,4], nums2 = [5,2,8,9]
 * 输出：1
 * 解释：类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2] (4^2 = 2 * 8)
 *
 * 示例 2：
 * 输入：nums1 = [1,1], nums2 = [1,1,1]
 * 输出：9
 * 解释：所有三元组都符合题目要求，因为 1^2 = 1 * 1
 * 类型 1：(0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[j] * nums2[k]
 * 类型 2：(0,0,1), (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k]
 *
 * 示例 3：
 * 输入：nums1 = [7,7,8,3], nums2 = [1,2,9,7]
 * 输出：2
 * 解释：有两个符合题目要求的三元组
 * 类型 1：(3,0,2), nums1[3]^2 = nums2[0] * nums2[2]
 * 类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1]
 *
 * 示例 4：
 * 输入：nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
 * 输出：0
 * 解释：不存在符合题目要求的三元组
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 1 <= nums1[i], nums2[i] <= 10^5
 */
public class NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {

    public static void main(String[] args) {
        NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers no =
                new NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers();
        System.out.println(no.numTriplets(new int[]{43024,99908}, new int[]{1864}));
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return sum(nums1,nums2)+sum(nums2, nums1);
    }

    public int sum(int[] nums1, int[] nums2) {
        int ans = 0;
        for(int num1 : nums1) {
            long target = (long)num1*num1;
            int left = 0;
            int right = nums2.length-1;
            while(left<right) {
                long mul = (long)nums2[left]*nums2[right];
                if(mul<target) {
                    left++;
                }else if(mul>target) {
                    right--;
                }else {
                    if(nums2[left]==nums2[right]) {
                        ans += (right-left+1)*(right-left)/2;
                        break;
                    }else {
                        int nl = 1;
                        while(nums2[left+1]==nums2[left]) {
                            nl++;
                            left++;
                        }
                        int nr = 1;
                        while(nums2[right-1]==nums2[right]) {
                            nr++;
                            right--;
                        }
                        ans += nl*nr;
                        left++;
                        right--;
                    }
                }
            }
        }
        return ans;
    }

}
