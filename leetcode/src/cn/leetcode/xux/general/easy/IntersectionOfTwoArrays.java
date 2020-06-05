package cn.leetcode.xux.general.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1==null||nums1.length==0||nums2==null||nums2.length==0) {
            return new int[0];
        }
        if(nums1.length>nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums1.length;i++) {
            set.add(nums1[i]);
        }
        List<Integer> list = new ArrayList<>();
        for(int curr : nums2) {
            if(set.contains(curr)) {
                list.add(curr);
                set.remove(curr);
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
