package cn.xux.algorithm.leetcode.general.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 989. 数组形式的整数加法
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 *
 * 示例 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 *
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 *
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class AddToArrayFormOfInteger {

    public static void main(String[] args) {
        System.out.println(new AddToArrayFormOfInteger()
                .addToArrayForm(new int[]{2,7,4}, 181));
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        int index = A.length-1;
        int carry = K;
        while(carry>0||index>=0) {
            int curr = carry+(index>=0?A[index--]:0);
            res.add(0, curr%10);
            carry = curr/10;
        }
        return res;
    }

    public List<Integer> addToArrayForm1(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        int i = 1;
        int index = A.length-1;
        int curr;
        int carry = 0;
        while(K/i>0||index>=0) {
            curr = 0;
            if(K/i>0) {
                curr += K/i%10;
                i *= 10;
            }
            if(index>=0) {
                curr += A[index];
                index--;
            }
            curr += carry;
            carry = curr/10;
            res.add(0, curr%10);
        }
        if(carry>0) {
            res.add(0, carry);
        }
        return res;
    }

}
