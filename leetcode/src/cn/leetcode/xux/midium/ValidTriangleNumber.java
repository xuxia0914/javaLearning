package cn.leetcode.xux.midium;

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
        System.out.println(new ValidTriangleNumber().triangleNumber(new int[]{24,3,82,22,35,84,19}));
    }

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
            for(int j=i+1;j<len-1;j++) {
                int target = Arrays.binarySearch(nums, j+1, len-1, nums[i]+nums[j]);
                if(target<0) {
                    target = -target+1;
                }
                result += len-1-target;
            }
        }
        return result;
    }

}
