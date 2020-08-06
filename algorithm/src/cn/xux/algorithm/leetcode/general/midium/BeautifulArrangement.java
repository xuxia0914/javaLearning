package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. 优美的排列
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，
 * 使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
 * 我们就称这个数组为一个优美的排列。条件：
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 *
 * 示例1:
 * 输入: 2
 * 输出: 2
 * 解释:
 * 第 1 个优美的排列是 [1, 2]:
 *   第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * 第 2 个优美的排列是 [2, 1]:
 *   第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 *
 * 说明:
 * N 是一个正整数，并且不会超过15。
 */
public class BeautifulArrangement {

    public int countArrangement(int N) {
        List<Integer>[] valid = new List[N+1];
        for(int pos=1;pos<=N;pos++) {
            valid[pos] = new ArrayList<>();
            for(int num=1;num<=N;num++) {
                if(num%pos==0||pos%num==0) {
                    valid[pos].add(num);
                }
            }
        }
        dfs(1, valid, new boolean[N+1]);
        return ans;
    }

    int ans = 0;

    public void dfs(int pos, List<Integer>[] valid, boolean[] visited) {
        if(pos==visited.length) {
            ans++;
            return;
        }
        for(int num : valid[pos]) {
            if(!visited[num]) {
                visited[num] = true;
                dfs(pos+1, valid, visited);
                visited[num] = false;
            }
        }
    }

}
