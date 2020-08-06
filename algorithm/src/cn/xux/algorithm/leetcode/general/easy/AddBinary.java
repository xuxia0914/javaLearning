package cn.xux.algorithm.leetcode.general.easy;

import java.util.LinkedList;

/**
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {

    public String addBinary(String a, String b) {
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
