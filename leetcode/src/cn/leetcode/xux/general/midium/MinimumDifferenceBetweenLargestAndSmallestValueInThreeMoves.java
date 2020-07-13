package cn.leetcode.xux.general.midium;

/**
 * 1509. 三次操作后最大值与最小值的最小差
 * 给你一个数组 nums ，每次操作你可以选择 nums 中的任意一个元素并将它改成任意值。
 * 请你返回三次操作后， nums 中最大值与最小值的差的最小值。
 *
 * 示例 1：
 * 输入：nums = [5,3,2,4]
 * 输出：0
 * 解释：将数组 [5,3,2,4] 变成 [2,2,2,2].
 * 最大值与最小值的差为 2-2 = 0 。
 *
 * 示例 2：
 * 输入：nums = [1,5,0,10,14]
 * 输出：1
 * 解释：将数组 [1,5,0,10,14] 变成 [1,1,0,1,1] 。
 * 最大值与最小值的差为 1-0 = 1 。
 *
 * 示例 3：
 * 输入：nums = [6,6,0,1,1,4,6]
 * 输出：2
 *
 * 示例 4：
 * 输入：nums = [1,5,6,14,15]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference(int[] nums) {
        if(nums==null||nums.length<5) {
            return 0;
        }
        Integer[] max = new Integer[4];
        Integer[] min = new Integer[4];
        for(int num : nums) {
            if(max[3]==null||num>max[3]) {
                max[0] = max[1];
                max[1] = max[2];
                max[2] = max[3];
                max[3] = num;
            }else if(max[2]==null||num>max[2]) {
                max[0] = max[1];
                max[1] = max[2];
                max[2] = num;
            }else if(max[1]==null||num>max[1]) {
                max[0] = max[1];
                max[1] = num;
            }else if(max[0]==null||num>max[0]) {
                max[0] = num;
            }
            if(min[0]==null||num<min[0]) {
                min[3] = min[2];
                min[2] = min[1];
                min[1] = min[0];
                min[0] = num;
            }else if(min[1]==null||num<min[1]) {
                min[3] = min[2];
                min[2] = min[1];
                min[1] = num;
            }else if(min[2]==null||num<min[2]) {
                min[3] = min[2];
                min[2] = num;
            }else if(min[3]==null||num<min[3]) {
                min[3] = num;
            }
        }
        return Math.min(Math.min(max[0]-min[0], max[1]-min[1]),
                Math.min(max[3]-min[3], max[2]-min[2]));
    }

}
