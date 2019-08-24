package cn.leetcode.xux.midium;

/**
 * 137. 只出现一次的数字 II
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        int one = 0;
        int two = 0;
        int three = 0;
        for(int num : nums) {
            two |= (one&num);   //ones & num 提取两个数都为1的位，与twos作或操作保留出现2次的位
            one ^= num;  //当 ones 和 num 同时为 1 or 0 时，ones = 0，因为同时为1已经加到twos里了，这里不做count
            three = one&two;    //当ones和twos对应位都为1时，说明此位出现了3次
            //three为1的位，将one和two对应位归零
            one &= ~three;
            two &= ~three;
        }
        return one;
    }

}
