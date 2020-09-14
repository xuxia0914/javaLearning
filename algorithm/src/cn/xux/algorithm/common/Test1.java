package cn.xux.algorithm.common;

import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().getAns(new int[]{8,9,7,3,0,5,11}));
    }

    /**
     * 秋叶收藏集
     * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
     * 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
     * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
     * 每部分树叶数量可以不相等，但均需大于等于 1。
     * 每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
     * 请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
     *
     * 示例 1：
     * 输入：leaves = "rrryyyrryyyrr"
     * 输出：2
     * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
     *
     * 示例 2：
     * 输入：leaves = "ryr"
     * 输出：0
     * 解释：已符合要求，不需要额外操作
     *
     * 提示：
     * 3 <= leaves.length <= 10^5
     * leaves 中只包含字符 'r' 和字符 'y'
     */
    public int minimumOperations(String leaves) {
        // dp[0]表示使树叶排列处于全红的状态 需要修改的次数
        // dp[1]表示树叶排列处于「红、黄」的状态 需要修改的次数
        // dp[2]表示树叶排列处于「红、黄、红」的状态 需要修改的次数
        int[] dp = new int[3];
        // 截止到第一个树叶
        dp[0] = (leaves.charAt(0)=='r'?0:1);

        // 截止到第二个树叶
        dp[1] = dp[0]+(leaves.charAt(1)=='y'?0:1);
        dp[0] += (leaves.charAt(1)=='r'?0:1);

        //截止到第三个树叶
        dp[2] = dp[1]+(leaves.charAt(2)=='r'?0:1);
        dp[1] = Math.min(dp[1], dp[0])+(leaves.charAt(2)=='y'?0:1);
        dp[0] += (leaves.charAt(2)=='r'?0:1);

        for(int i=3;i<leaves.length();i++) {
            dp[2] = Math.min(dp[2], dp[1])+(leaves.charAt(i)=='r'?0:1);
            dp[1] = Math.min(dp[1], dp[0])+(leaves.charAt(i)=='y'?0:1);
            dp[0] += (leaves.charAt(i)=='r'?0:1);
        }
        return dp[2];
    }

}