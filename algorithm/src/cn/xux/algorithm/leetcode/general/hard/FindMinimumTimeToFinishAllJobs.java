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
                new int[]{1,2,4,7,8}, 2
        ));
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        int sum = 0;
        for(int job : jobs) {
            sum += job;
        }
        int left = sum/k;
        int right = sum;
        while(left<right) {
            int mid = (left+right)/2;
            if(check(jobs, k, mid)) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    private boolean check(int[] jobs,int k, int max) {
        int n = jobs.length;
        Set<Workers> set = new HashSet<>();
        set.add(new Workers(new int[k]));
        int idx = 0;
        while(set.size()>0&&idx<n) {
            Set<Workers> newSet = new HashSet<>();
            for(Workers w : set) {
                for(int i=0;i<k;i++) {
                    if(w.assign[i]+jobs[idx]<=max) {
                        int[] assign = w.assign.clone();
                        assign[i] += jobs[idx];
                        Arrays.sort(assign);
                        newSet.add(new Workers(assign));
                    }else {
                        break;
                    }
                }
            }
            idx++;
            set = newSet;
        }
        return set.size()>0;
    }

    class Workers {
        int[] assign;
        Workers(int[] assign) {
            this.assign = assign;
        }
        @Override
        public boolean equals(Object o) {
            Workers ow = (Workers)o;
            int[] arr = ow.assign;
            for(int i=0;i<arr.length;i++) {
                if(this.assign[i]!=arr[i]) {
                    return false;
                }
            }
            return true;
        }
        @Override
        public int hashCode() {
            return this.assign[0];
        }
    }

}
