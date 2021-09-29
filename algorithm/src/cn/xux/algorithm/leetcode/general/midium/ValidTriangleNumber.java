package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 *
 * 注意:
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 */
public class ValidTriangleNumber {

    public static void main(String[] args) {
        try {
            try {
                int a = 1 / 0;
            } catch (Exception e) {
                System.out.println("11111111111111");
            }
            System.out.println("222222222222");
        } catch (Exception e) {
            System.out.println("3333333333333");
        }
        System.out.println("44444444444");
    }

    // n^2
    public int triangleNumber(int[] nums) {
        if(nums==null||nums.length<3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int result = 0;
        for(int i=0;i<len-2;i++) {
            if(nums[i]==0) {
                continue;
            }
            int c = i+1;
            for(int j=i+1;j<len-1;j++) {
                while(c<len&&nums[c]<nums[i]+nums[j]) {
                    c++;
                }
                result += c-j-1;
            }

        }
        return result;
    }

    // 时间 O(n^2logn)
    public int triangleNumber1(int[] nums) {
        if(nums==null||nums.length<3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int result = 0;
        for(int i=0;i<len-2;i++) {
            if(nums[i]==0) {
                continue;
            }
            for(int j=i+1;j<len-1;j++) {
                int left = j;
                int right = len-1;
                while(left<right) {
                    int mid = (left+right+1)/2;
                    if(nums[mid]<nums[i]+nums[j]) {
                        left = mid;
                    }else {
                        right = mid-1;
                    }
                }
                result += left-j;
            }
        }
        return result;
    }

}
