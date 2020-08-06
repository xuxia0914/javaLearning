package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 375. 猜数字大小 II
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 *
 * 示例:
 * n = 10, 我选择了8.
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * 游戏结束。8 就是我选的数字。
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class GuessNumberHigherOrLowerII {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int i=1;i<n;i++) {
            for(int j=1;j+i<n+1;j++) {
                int curr = Integer.MAX_VALUE;
                for(int k=j;k<=j+i;k++) {
                    if(k==j) {
                        curr = Math.min(curr, k+dp[k+1][j+i]);
                    }else if(k==j+i) {
                        curr = Math.min(curr, k+dp[j][k-1]);
                    }else {
                        curr = Math.min(curr, k+dp[j][k-1]+dp[k+1][j+i]);
                    }
                }
                dp[j][j+i] = curr;
            }
        }
        return dp[1][n];
    }

    /**
     * 递归
     * 执行用时 :687 ms, 在所有 Java 提交中击败了7.10%的用户
     * 内存消耗 :105.9 MB, 在所有 Java 提交中击败了6.35%的用户
     * @param n
     * @return
     */
    public int getMoneyAmount1(int n) {
        return count(1, n);
    }

    Map<String, Integer> map = new HashMap<>();
    public int count(int start, int end) {
        if(start>=end) {
            return 0;
        }
        String s = ""+start+","+end;
        if(map.containsKey(s)) {
            return map.get(s);
        }
        int res = Integer.MAX_VALUE;
        for(int i=start;i<=end;i++) {
            int curr = i+Math.max(count(start, i-1), count(i+1, end));
            res = Math.min(res, curr);
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLowerII gn = new GuessNumberHigherOrLowerII();
//        System.out.println(gn.getMoneyAmount(5));  //6
        System.out.println(gn.getMoneyAmount(6));  //8
    }

}
