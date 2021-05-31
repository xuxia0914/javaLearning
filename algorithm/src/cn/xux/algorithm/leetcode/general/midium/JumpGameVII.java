package cn.xux.algorithm.leetcode.general.midium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1871. 跳跃游戏 VII
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。
 * 一开始，你在下标 0 处，且该位置的值一定为 '0' 。
 * 当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
 * i + minJump <= j <= min(i + maxJump, s.length - 1) 且 s[j] == '0'.
 * 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：s = "011010", minJump = 2, maxJump = 3
 * 输出：true
 * 解释：
 * 第一步，从下标 0 移动到下标 3 。
 * 第二步，从下标 3 移动到下标 5 。
 *
 * 示例 2：
 * 输入：s = "01101110", minJump = 2, maxJump = 3
 * 输出：false
 *
 * 提示：
 * 2 <= s.length <= 105
 * s[i] 要么是 '0' ，要么是 '1'
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 */
public class JumpGameVII {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] canReach = new boolean[n];
        int[] preSum = new int[n+1];
        canReach[0] = true;
        preSum[1]  =1;
        for(int i=1;i<n;i++) {
            if(s.charAt(i)=='0'&&preSum[Math.max(0,i-minJump+1)]-preSum[Math.max(0,i-maxJump)]>0) {
                canReach[i] = true;
            }
            preSum[i+1] = preSum[i]+(canReach[i]?1:0);
        }
        return canReach[n-1];
    }

        // TLE
    public boolean canReach1(String s, int minJump, int maxJump) {
        int n = s.length();
        if(s.charAt(n-1)=='1') {
            return false;
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(0);
        while(!deque.isEmpty()) {
            int tail = deque.peekLast();
            if(tail==n-1) {
                return true;
            }
            int curr = deque.pollFirst();
            for(int i=Math.max(minJump,tail-curr+1);i<=Math.min(maxJump,n-1-curr);i++) {
                if(s.charAt(curr+i)=='0') {
                    deque.offerLast(curr+i);
                }
            }
        }
        return false;
    }

}
