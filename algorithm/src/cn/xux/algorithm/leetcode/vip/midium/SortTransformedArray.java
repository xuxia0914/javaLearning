package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 360. 有序转化数组
 * 给你一个已经 排好序 的整数数组 nums 和整数 a、b、c。对于数组中的每一个数 x，
 * 计算函数值 f(x) = ax2 + bx + c，请将函数值产生的数组返回。
 * 要注意，返回的这个数组必须按照 升序排列，并且我们所期望的解法时间复杂度为 O(n)。
 *
 * 示例 1：
 * 输入: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * 输出: [3,9,15,33]
 *
 * 示例 2：
 * 输入: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * 输出: [-23,-5,1,7]
 */
public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(nums==null||nums.length==0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] res = new int[n];
        if(a>=0) {
            int left = 0;
            int right = n-1;
            for(int i=n-1;i>=0;i--) {
                int fl = a*nums[left]*nums[left]+b*nums[left]+c;
                int fr = a*nums[right]*nums[right]+b*nums[right]+c;
                if(fl>=fr) {
                    res[i] = fl;
                    left++;
                }else {
                    res[i] = fr;
                    right--;
                }
            }
        }else {
            int left = 0;
            int right = n-1;
            for(int i=0;i<n;i++) {
                int fl = a*nums[left]*nums[left]+b*nums[left]+c;
                int fr = a*nums[right]*nums[right]+b*nums[right]+c;
                if(fl<=fr) {
                    res[i] = fl;
                    left++;
                }else {
                    res[i] = fr;
                    right--;
                }
            }
        }
        return res;
    }

}
