package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given a sorted array of integers nums and integer values a, band c.
 * Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 * Example 1:
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * Output: [3,9,15,33]
 * Example 2:
 * Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * Output: [-23,-5,1,7]
 */
public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(nums==null||nums.length==0) {
            return new int[]{};
        }
        int len = nums.length;
        int[] res = new int[len];
        if(a==0) {
            if(b>=0) {
                for(int i=0;i<len;i++) {
                    res[i] = b*nums[i] + c;
                }
            }else {
                for(int i=0;i<len;i++) {
                    res[i] = b*nums[len-1-i] + c;
                }
            }
            return res;
        }
        double mid = -1.0*b/2*a;
        int left = 0;
        int right = len-1;
        int cnt = len;
        if(a>0) {
            while(cnt>0) {
                if(Math.abs(mid-nums[left])>=Math.abs(mid-nums[right])) {
                    res[cnt-1] = a*a*nums[left] + b*nums[left] + c;
                    left++;
                }else {
                    res[cnt-1] = a*a*nums[right] + b*nums[right] + c;
                    right--;
                }
                cnt--;
            }
        }else {
            while(cnt>0) {
                if(Math.abs(mid-nums[left])>=Math.abs(mid-nums[right])) {
                    res[len-cnt] = a*a*nums[left] + b*nums[left] + c;
                    left++;
                }else {
                    res[len-cnt] = a*a*nums[right] + b*nums[right] + c;
                    right--;
                }
                cnt--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(1.0/2);
        System.out.println(1-0.5);
        System.out.println(-2.0*1/2);
    }

}
