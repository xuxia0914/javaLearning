package cn.leetcode.xux.easy;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0) {
            return digits;
        }
        int carry = 1;
        for(int i=digits.length-1;i>=0;i--) {
            int sum = digits[i]+carry;
            digits[i] = sum%10;
            carry = sum/10;
            if(carry==0) {
                break;
            }
        }
        if(carry==1) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            int i=1;
            while(i<res.length) {
                res[i] = digits[i-1];
                i++;
            }
            return res;
        }
        return digits;
    }

    public static void main(String[] args) {
        new PlusOne().plusOne(new int[]{9});
    }

}
