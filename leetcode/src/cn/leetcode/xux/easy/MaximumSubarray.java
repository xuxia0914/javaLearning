package cn.leetcode.xux.easy;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaximumSubarray {

    public static int solution(int[] array) {
        int current = array[0];
        int max = array[0];
        for(int i=1;i<array.length;i++) {
            current = Math.max(current+array[i], array[i]);
            max = Math.max(max, current);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-2,1,-3,4,-1,2,1,-5,4,7}));
    }

}
