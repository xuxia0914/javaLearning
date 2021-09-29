package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1986. 完成任务的最少工作时间段
 * 你被安排了 n 个任务。任务需要花费的时间用长度为 n 的整数数组 tasks 表示，
 * 第 i 个任务需要花费 tasks[i] 小时完成。一个 工作时间段 中，
 * 你可以 至多 连续工作 sessionTime 个小时，然后休息一会儿。
 *
 * 你需要按照如下条件完成给定任务：
 *
 * 如果你在某一个时间段开始一个任务，你需要在 同一个 时间段完成它。
 * 完成一个任务后，你可以 立马 开始一个新的任务。
 * 你可以按 任意顺序 完成任务。
 * 给你 tasks 和 sessionTime ，请你按照上述要求，
 * 返回完成所有任务所需要的 最少 数目的 工作时间段 。
 *
 * 测试数据保证 sessionTime 大于等于 tasks[i] 中的 最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：tasks = [1,2,3], sessionTime = 3
 * 输出：2
 * 解释：你可以在两个工作时间段内完成所有任务。
 * - 第一个工作时间段：完成第一和第二个任务，花费 1 + 2 = 3 小时。
 * - 第二个工作时间段：完成第三个任务，花费 3 小时。
 * 示例 2：
 *
 * 输入：tasks = [3,1,3,1,1], sessionTime = 8
 * 输出：2
 * 解释：你可以在两个工作时间段内完成所有任务。
 * - 第一个工作时间段：完成除了最后一个任务以外的所有任务，花费 3 + 1 + 3 + 1 = 8 小时。
 * - 第二个工作时间段，完成最后一个任务，花费 1 小时。
 * 示例 3：
 *
 * 输入：tasks = [1,2,3,4,5], sessionTime = 15
 * 输出：1
 * 解释：你可以在一个工作时间段以内完成所有任务。
 *
 *
 * 提示：
 *
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 */
public class MinimumNumberOfWorkSessionsToFinishTheTasks {

    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int m = 1<<n;
        int[] dp = new int[m];
        Arrays.fill(dp, 14);
        for(int i=1;i<m;i++) {
            // 判断状态i的任务集能否在一个时间段完成
            int time = 0;
            int tmp = i;
            int idx = n-1;
            while(tmp>0) {
                if((tmp&1)==1&&(time+=tasks[idx])>sessionTime) {
                    break;
                }
                tmp >>= 1;
                idx--;
            }
            if(time<=sessionTime) {
                dp[i] = 1;
                continue;
            }
            // 不能在一个时间段完成，那么需要遍历子状态并选最有子状态使dp[i]最小
            for(int j=(i&(i-1));j>i>>1;j=((j-1)&i)) {
                dp[i] = Math.min(dp[i], dp[j]+dp[j^i]);
            }
        }
        return dp[m-1];
    }

    int[] tasks;
    int n;
    int sessionTime;

    public int minSessions1(int[] tasks, int sessionTime) {
        this.tasks = tasks;
        this.n = tasks.length;
        this.sessionTime = sessionTime;
        int right = n;
        int sum = 0;
        for(int task : tasks) {
            sum += task;
        }
        int left = sum/sessionTime+(sum%sessionTime==0?0:1);
        while(left<right) {
            int mid = (left+right)/2;
            if(check(0, new int[mid])) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    // dfs+剪枝
    // 判断sess.length个时间段能否完成所有任务
    private boolean check(int idx, int[] sess) {
        if(idx==n) {
            return true;
        }
        for(int i=0;i<sess.length&&sess[i]+tasks[idx]<=sessionTime;i++) {
            if(i==0||sess[i]!=sess[i-1]) {
                int[] next = sess.clone();
                next[i] += tasks[idx];
                Arrays.sort(next);
                if(check(idx+1, next)) {
                    return true;
                }
            }
        }
        return false;
    }

}
