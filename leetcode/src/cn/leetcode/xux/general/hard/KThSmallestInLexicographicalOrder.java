package cn.leetcode.xux.general.hard;

/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * 注意：1 ≤ k ≤ n ≤ 10^9。
 *
 * 示例 :
 * 输入: n: 13   k: 2
 * 输出: 10
 *
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 */
public class KThSmallestInLexicographicalOrder {

    public static void main(String[] args) {
        System.out.println(new KThSmallestInLexicographicalOrder()
                .findKthNumber(2, 2));
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;    //k表示要跳过前面多少个节点
        while(k>0) {
            int count = count(n, curr, curr+1);
            if(count>k) {
                // 如果以curr为前缀的节点数大于要跳过的节点数，
                // 说明结果是以curr为前缀的,减去当前节点并向下遍历
                curr *= 10;
                k--;
            }else {
                // 如果以curr为前缀的节点数小于等于要跳过的节点数
                // 则跳过以curr为前缀的所有节点并向右遍历
                curr++;
                k -= count;
            }
        }
        return curr;
    }

    //以start为前缀且不大于n的节点的个数
    public int count(int n, long start, long end) {
        int ans = 0;
        while(start<=n) {
            ans += Math.min(end, n+1)-start;
            start *=10;
            end *= 10;
        }
        return ans;
    }

}
