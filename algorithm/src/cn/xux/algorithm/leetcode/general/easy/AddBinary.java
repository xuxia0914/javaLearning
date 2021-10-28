package cn.xux.algorithm.leetcode.general.easy;

import java.util.LinkedList;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int n1 = a.length();
        int n2 = b.length();
        int i1 = n1-1;
        int i2 = n2-1;
        int carry = 0;
        while(i1>=0||i2>=0||carry>0) {
            int curr = (i1>=0?a.charAt(i1)-'0':0)
                    + (i2>=0?b.charAt(i2)-'0':0) + carry;
            ans.append(curr&1);
            carry = curr>>1;
            i1--;
            i2--;
        }
        return ans.reverse().toString();
    }

    public String addBinary1(String a, String b) {
        LinkedList<Integer> list = new LinkedList<>();
        int lenA = a.length();
        int lenB = b.length();
        int indexA = lenA-1;
        int indexB = lenB-1;
        int carry = 0;
        int curr;
        while(indexA>=0||indexB>=0) {
            curr = 0;
            if(indexA>=0) {
                curr += a.charAt(indexA)-'0';
                indexA--;
            }
            if(indexB>=0) {
                curr += b.charAt(indexB)-'0';
                indexB--;
            }
            curr += carry;
            carry = curr/2;
            curr = curr%2;
            list.add(0, curr);
        }
        StringBuilder res = new StringBuilder();
        if(carry>0) {
            res.append(carry);
        }
        for(int i : list) {
            res.append(i);
        }
        return res.toString();
    }

}
