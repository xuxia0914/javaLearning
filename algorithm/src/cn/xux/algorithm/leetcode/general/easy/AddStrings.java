package cn.xux.algorithm.leetcode.general.easy;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int index1 = num1.length()-1;
        int index2 = num2.length()-1;
        int curr;
        int carry=0;
        while(index1>=0||index2>=0) {
            curr = carry;
            if(index1>=0) {
                curr += num1.charAt(index1)-'0';
                index1--;
            }
            if(index2>=0) {
                curr += num2.charAt(index2)-'0';
                index2--;
            }
            carry = curr/10;
            res.append(curr%10);
        }
        if(carry>0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        AddStrings as = new AddStrings();
//        System.out.println(as.addStrings("1111111", "22222222"));
        System.out.println(as.addStrings("9", "1"));
        System.out.println(as.addStrings("99", "99"));
    }

}
