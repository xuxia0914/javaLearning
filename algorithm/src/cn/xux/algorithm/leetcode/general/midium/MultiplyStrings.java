package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] arr = new int[len1+len2-1];
        for(int i=0;i<len1;i++) {
            for(int j=0;j<len2;j++) {
                arr[i+j] += (num1.charAt(len1-1-i)-'0')*(num2.charAt(len2-1-j)-'0');
            }
        }
        int carry = 0;
        for(int i=0;i<arr.length;i++) {
            int curr = arr[i]+carry;
            arr[i] = curr%10;
            carry = curr/10;
        }
        StringBuilder ans = new StringBuilder();
        if(carry!=0) {
            ans.append(carry);
        }
        for(int i=arr.length-1;i>=0;i--) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }

    public String multiply1(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) {
            return "0";
        }
        List<Integer> list = new LinkedList<>();
        int len1 = num1.length();
        int len2 = num2.length();
        int index = len1+len2-2;
        int carry = 0;
        int curr;
        while(index>=0) {
            curr = carry;
            int i = len1-1;
            while(i>=0) {
                if(index-i<=len2-1&&index-i>=0) {
                    curr += (num1.charAt(i)-'0')*(num2.charAt(index-i)-'0');
                }
                i--;
            }
            carry = curr/10;
            list.add(0, curr%10);
            index--;
        }
        StringBuilder sb = new StringBuilder();
        if(carry>0) {
            sb.append(carry);
        }
        for(int i : list) {
            sb.append(i);
        }
        return sb.toString();
    }

}
