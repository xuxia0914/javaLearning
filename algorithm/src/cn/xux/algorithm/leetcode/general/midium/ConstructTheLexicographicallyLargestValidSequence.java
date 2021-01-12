package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1718. 构建字典序最大的可行序列
 * 给你一个整数 n ，请你找到满足下面条件的一个序列：
 * 整数 1 在序列中只出现一次。
 * 2 到 n 之间每个整数都恰好出现两次。
 * 对于每个 2 到 n 之间的整数 i ，两个 i 之间出现的距离恰好为 i 。
 * 序列里面两个数 a[i] 和 a[j] 之间的 距离 ，我们定义为它们下标绝对值之差 |j - i| 。
 * 请你返回满足上述条件中 字典序最大 的序列。题目保证在给定限制条件下，一定存在解。
 * 一个序列 a 被认为比序列 b （两者长度相同）字典序更大的条件是：
 * a 和 b 中第一个不一样的数字处，a 序列的数字比 b 序列的数字大。
 * 比方说，[0,1,9,0] 比 [0,1,5,6] 字典序更大，
 * 因为第一个不同的位置是第三个数字，且 9 比 5 大。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[3,1,2,3,2]
 * 解释：[2,3,2,1,3] 也是一个可行的序列，但是 [3,1,2,3,2] 是字典序最大的序列。
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：[5,3,1,4,3,5,2,4,2]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class ConstructTheLexicographicallyLargestValidSequence {

    int[] ans;

    public int[] constructDistancedSequence(int n) {
        ans = new int[2*n-1];
        dfs(0, new boolean[n+1], n);
        return ans;
    }

    // 回溯 贪婪
    public boolean dfs(int idx, boolean[] visited, int n) {
        if(idx==ans.length) {
            return true;
        }
        if(ans[idx]!=0) {
            return dfs(idx+1, visited, n);
        }
        // 贪婪 从大到小遍历 第一个(即i最大)符合条件的结果即为最终结果
        for(int i=n;i>=1;i--) {
            if(!visited[i]) {
                if(i>1&&idx+i<ans.length&&ans[idx+i]==0) {
                    ans[idx] = i;
                    ans[idx+i] = i;
                    visited[i] = true;
                    boolean res = dfs(idx+1, visited, n);
                    if(res) {
                        return true;
                    }else {
                        ans[idx] = 0;
                        ans[idx+i] = 0;
                        visited[i] = false;
                    }
                }
                if(i==1) {
                    ans[idx] = i;
                    visited[i] = true;
                    boolean res = dfs(idx+1, visited, n);
                    if(res) {
                        return true;
                    }else {
                        ans[idx] = 0;
                        visited[i] = false;
                    }
                }
            }
        }
        return false;
    }

}
