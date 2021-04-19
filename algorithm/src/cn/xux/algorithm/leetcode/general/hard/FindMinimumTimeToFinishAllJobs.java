package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 1723. 完成所有工作的最短时间
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，
 * 且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
 * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 * 示例 1：
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 *
 * 示例 2：
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 *
 * 提示：
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 */
public class FindMinimumTimeToFinishAllJobs {

    public static void main(String[] args) {
        System.out.println(new FindMinimumTimeToFinishAllJobs().minimumTimeRequired(
                new int[]{3,2,3}, 2
        ));
    }

    int ans;

    public int minimumTimeRequired(int[] jobs, int k) {
        ans = Integer.MAX_VALUE;
        dfs(jobs, 0, new int[k], k);
        return ans;
    }

    /**
     * dfs+剪枝
     * @param jobs  jobs[i]表示第i项工作需要花费的时间
     * @param idx   当前需要分配的job
     * @param curr  已经分配好的每个工人的工作量(已经按升序排列好)
     * @param cnt   有cnt个工人没有分配到工作，即curr中值为0的元素个数
     */
    private void dfs(int[] jobs, int idx, int[] curr, int cnt) {
        int n = jobs.length;
        int k = curr.length;
        if(n-idx<cnt||curr[k-1]>=ans) {
            return;
        }
        if(idx==n) {
            ans = curr[k-1];
            return;
        }
        for(int i=0;i<k;i++) {
            if(i==0||curr[i]!=curr[i-1]) {
                int[] next = curr.clone();
                next[i] += jobs[idx];
                Arrays.sort(next);
                dfs(jobs, idx+1, next, cnt-(curr[i]==0?1:0));
                if(curr[i]==0) {
                    break;
                }
            }
        }
    }

}
