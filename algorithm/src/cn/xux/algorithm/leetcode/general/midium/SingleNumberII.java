package cn.xux.algorithm.leetcode.general.midium;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        int one = 0;
        int two = 0;
        int three = 0;
        for(int num : nums) {
            //ones & num 提取两个数都为1的位，与twos作或操作保留出现2次的位
            two |= (one&num);
            //当 ones 和 num 同时为 1 or 0 时，ones = 0，因为同时为1已经加到twos里了，这里不做count
            one ^= num;
            //当ones和twos对应位都为1时，说明此位出现了3次
            three = one&two;
            //three为1的位，将one和two对应位归零
            one &= ~three;
            two &= ~three;
        }
        return one;
    }

}
