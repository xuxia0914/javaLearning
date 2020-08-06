package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1262. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 * 示例 1：
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 *
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {

    public int maxSumDivThree(int[] nums) {
        int result = 0;
        int left1_1 = -1;
        int left1_2 = -1;
        int left2_1 = -1;
        int left2_2 = -1;
        for(int num : nums) {
            result += num;
            if(num%3==1) {
                if(left1_1==-1||num<left1_1) {
                    left1_2 = left1_1;
                    left1_1 = num;
                }else if(left1_2==-1||num<left1_2) {
                    left1_2 = num;
                }
            }else if(num%3==2) {
                if(left2_1==-1||num<left2_1) {
                    left2_2 = left2_1;
                    left2_1 = num;
                }else if(left2_2==-1||num<left2_2) {
                    left2_2 = num;
                }
            }
        }
        if(result%3==0) {
            return result;
        }else if(result%3==1) {
            if(left1_1!=-1&&left2_1!=-1&&left2_2!=-1) {
                result = result-Math.min(left1_1, left2_1+left2_2);
            }else if(left1_1!=-1) {
                result -= left1_1;
            }else if(left2_1!=-1&&left2_2!=-1) {
                result -= left2_1+left2_2;
            }else {
                return 0;
            }
        }else {
            if(left2_1!=-1&&left1_1!=-1&&left1_2!=-1) {
                result = result-Math.min(left2_1, left1_1+left1_2);
            }else if(left2_1!=-1) {
                result -= left2_1;
            }else if(left1_1!=-1&&left1_2!=-1) {
                result -= left1_1+left1_2;
            }else {
                return 0;
            }
        }
        return result;
    }

}
