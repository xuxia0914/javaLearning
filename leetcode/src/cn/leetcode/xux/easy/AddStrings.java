package cn.leetcode.xux.easy;

/**
 * 字符串相加
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int index1 = num1.length()-1;
        int index2 = num2.length()-1;
        int curr;
        int carry=0;
        while(index1>=0||index2>=0) {
            curr = 0;
            if(index1>=0) {
                curr += num1.charAt(index1)-'0';
                index1--;
            }
            if(index2>=0) {
                curr += num2.charAt(index2)-'0';
                index2--;
            }
            curr += carry;
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
