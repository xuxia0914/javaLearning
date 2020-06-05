package cn.leetcode.xux.general.midium;

/**
 * 306. 累加数
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 示例 1:
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * 示例 2:
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        if(num==null||num.length()<3) {
            return false;
        }
        int len = num.length();
        String numStr1;
        String numStr2;
        for(int i=1;len-i>i;i++) {
            if(len-i<i) {
                return false;
            }
            numStr1 = num.substring(0, i);
            for(int j=i+1;len-j>=Math.max(j-i, i);j++) {
                if(len-j<Math.max(j-i, i)) {
                    return false;
                }
                numStr2 = num.substring(i, j);
                if(helper(numStr1, numStr2, num.substring(j))) {
                    return true;
                }
                if(numStr2.equals("0")) {
                    break;
                }
            }
            if(numStr1.equals("0")) {
                break;
            }
        }
        return false;
    }

    public boolean helper(String num1, String num2, String num) {
        if(num.length()<Math.max(num1.length(), num2.length())) {
            return false;
        }
        String sum = sum(num1, num2);
        if(num.equals(sum)) {
            return true;
        }else if(num.startsWith(sum)) {
            return helper(num2, sum, num.substring(sum.length()));
        }else {
            return false;
        }
    }

    public String sum(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int idx1 = num1.length()-1;
        int idx2 = num2.length()-1;
        int curr;
        int carry = 0;
        while(idx1>=0||idx2>=0) {
            curr = carry;
            if(idx1>=0) {
                curr += num1.charAt(idx1)-'0';
            }
            if(idx2>=0) {
                curr += num2.charAt(idx2)-'0';
            }
            carry = curr/10;
            res.append(curr%10);
            idx1--;
            idx2--;
        }
        if(carry>0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        AdditiveNumber an = new AdditiveNumber();
        System.out.println(an.isAdditiveNumber("112358"));
        System.out.println(an.isAdditiveNumber("199100199"));
        System.out.println(an.isAdditiveNumber("000"));
        System.out.println(an.isAdditiveNumber("221474836472147483649"));
    }

}
