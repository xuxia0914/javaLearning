package cn.leetcode.xux.midium;

/**
 * 457. 环形数组循环
 * 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。
 * 相反，如果是负数 (-k)，则向后移动 k 个索引。
 * 因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
 * 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 > 1。
 * 此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。
 *
 * 示例 1：
 * 输入：[2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 *
 * 示例 2：
 * 输入：[-1,2]
 * 输出：false
 * 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 *
 * 示例 3:
 * 输入：[-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2 的运动是向前的运动，
 * 而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
 *
 * 提示：
 * -1000 ≤ nums[i] ≤ 1000
 * nums[i] ≠ 0
 * 0 ≤ nums.length ≤ 5000
 *
 * 进阶：
 * 你能写出时间时间复杂度为 O(n) 和额外空间复杂度为 O(1) 的算法吗？
 */
public class CircularArrayLoop {

    public static void main(String[] args) {
        System.out.println(new CircularArrayLoop().circularArrayLoop(new int[]{3,1,2}));
    }

    public boolean circularArrayLoop(int[] nums) {
        if(nums==null||nums.length<2) {
            return false;
        }
        int len = nums.length;
        int mod = (1000/len+1)*len;
        for(int i=0;i<len;i++) {
            boolean sign = nums[i]>0;
            int num = (i+nums[i]+mod)%len;
            if(!sign&&num==0) {
                num = len;
            }
            nums[i] = num*(sign?1:-1);
        }
        if(helper(nums)) {
            return true;
        }else {
            for(int i=0;i<len;i++) {
                if(nums[i]==0) {
                    nums[i] = -len;
                }else if(nums[i]==-len) {
                    nums[i] = 0;
                }else {
                    nums[i] = -nums[i];
                }
            }
            return helper(nums);
        }
    }

    public boolean helper(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++) {
            if(nums[i]<0||nums[i]==i||nums[nums[i]]<0||nums[nums[i]]==nums[i]) {
                continue;
            }
            boolean result = true;
            int slow = nums[i];
            int fast = nums[nums[i]];
            while(slow!=fast) {
                if(nums[fast]<0||nums[fast]==fast||nums[nums[fast]]<0||nums[nums[fast]]==nums[fast]) {
                    result = false;
                    break;
                }else {
                    slow = nums[slow];
                    fast = nums[nums[fast]];
                }
            }
            if(result) {
                return true;
            }
        }
        return false;
    }

}
