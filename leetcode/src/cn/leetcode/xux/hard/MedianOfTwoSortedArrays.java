package cn.leetcode.xux.hard;

/**
 * 求两个有序数组的中位数
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length,l2=nums2.length;
        if(l1>l2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        if(l1==0) {
            return (double)(nums2[(l2-1)/2]+nums2[l2/2])/2;
        }
        int left1=0,right1=l1;
        int mid1 = l1/2, mid2;
        while(mid1<=l1) {
            mid1 = (left1+right1)/2;
            mid2 = (l1+l2)/2 - mid1;

            double mid1Left = (mid1==0) ? Integer.MIN_VALUE : nums1[mid1-1];
            double mid2left = (mid2==0) ? Integer.MIN_VALUE : nums2[mid2-1];
            double mid1Right = (mid1==l1) ? Integer.MAX_VALUE : nums1[mid1];
            double mid2Right = (mid2==l2) ? Integer.MAX_VALUE : nums2[mid2];
            if(mid1Left>mid2Right) {
                right1 = mid1-1;
            }else if(mid2left>mid1Right) {
                left1 = mid1+1;
            }else {
                if((l1+l2)%2==0) {
                    return (Math.max(mid1Left, mid2left)+Math.min(mid1Right, mid2Right))/2;
                }else {
                    return Math.min(mid1Right, mid2Right);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays mt = new MedianOfTwoSortedArrays();
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7, 8}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 4, 8}, new int[]{2, 3, 5}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 4}, new int[]{2, 3}));
        System.out.println(mt.findMedianSortedArrays(new int[]{1, 2, 3, 7}, new int[]{4, 5, 6, }));
    }

}
