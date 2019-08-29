package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. 数组中两个数的最大异或值
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 *
 * 你能在O(n)的时间解决这个问题吗？
 *
 * 示例:
 * 输入: [3, 10, 5, 25, 2, 8]
 * 输出: 28
 * 解释: 最大的结果是 5 ^ 25 = 28.
 */
public class MaximumXorOfTwoNumbersInAnArray {

    // a^b = c ->a^c=b,b^c=a
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            //mask从0x01 ->0x03等一直变化，取后i位
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            //tmp表示当前的最大
            int tmp = max | (1 << i);
            for(int prefix : set){
                //证明存在一个数使得tmp^x = prefix
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumXorOfTwoNumbersInAnArray mx = new MaximumXorOfTwoNumbersInAnArray();
        System.out.println(mx.findMaximumXOR(new int[]{3, 10, 5}));
    }

}