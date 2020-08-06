package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 * What is the most profit we can make?
 * Example 1:
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 * Notes:
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 */
public class MostProfitAssigningWork {

    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        if(difficulty==null||difficulty.length==0||worker==null||worker.length==0) {
            return 0;
        }
        int[] dp = new int[100001];  //dp[i] means the max profit when difficulty is i;
        for(int i=0;i<difficulty.length;i++) {
            dp[difficulty[i]] = Math.max(dp[difficulty[i]], profit[i]);
        }
        for(int i=1;i<100001;i++) {
            dp[i] = Math.max(dp[i], dp[i-1]);
        }
        int res = 0;
        for(int w : worker) {
            res += dp[w];
        }
        return res;
    }

    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        if(difficulty==null||difficulty.length==0||worker==null||worker.length==0) {
            return 0;
        }
        sortByDifficulty(difficulty, profit, 0, difficulty.length-1);
        for(int i=1;i<profit.length;i++) {
            profit[i] = Math.max(profit[i], profit[i-1]);
        }
        Arrays.sort(worker);
        int res = 0;
        int i=0,j=0;
        while(j<worker.length) {
            while(i<difficulty.length&&difficulty[i]<=worker[j]) {
                i++;
            }
            if(i>0) {
                res += profit[i-1];
            }
            j++;
        }

        return res;
    }

    public void sortByDifficulty(int[] difficulty, int[] profit, int low, int high) {
        if(difficulty==null||difficulty.length==0
                ||profit==null||profit.length!=difficulty.length
                ||low>=high) {
            return;
        }
        int key1 = difficulty[low];
        int key2 = profit[low];
        int i = low;
        int j = high;
        while(i<j) {
            while(difficulty[j]>=key1&&i<j) {
                j--;
            }
            while(difficulty[i]<=key1&&i<j) {
                i++;
            }
            if(i<j) {
                difficulty[i] = difficulty[i]^difficulty[j];
                difficulty[j] = difficulty[i]^difficulty[j];
                difficulty[i] = difficulty[i]^difficulty[j];
                profit[i] = profit[i]^profit[j];
                profit[j] = profit[i]^profit[j];
                profit[i] = profit[i]^profit[j];
            }
        }
        difficulty[low] = difficulty[i];
        difficulty[i] = key1;
        profit[low] = profit[i];
        profit[i] = key2;

        sortByDifficulty(difficulty, profit, low, i-1);
        sortByDifficulty(difficulty, profit, i+1, high);

    }



    /**TLE*/
    public int maxProfitAssignment3(int[] difficulty, int[] profit, int[] worker) {
        if(difficulty==null||difficulty.length==0||worker==null||worker.length==0) {
            return 0;
        }
        int len1 = difficulty.length;
        int len2 = worker.length;
        for(int i=0;i<len1;i++) {
            for(int j=0;j<len1;j++) {
                if(difficulty[j]<=difficulty[i]) {
                    profit[i] = Math.max(profit[i], profit[j]);
                }
            }
        }
        int res = 0;
        for(int i=0;i<len2;i++) {
            int currDifficulty = -1;
            int profitIndex = -1;
            for(int j=0;j<len1;j++) {
                if(difficulty[j]>currDifficulty&&difficulty[j]<=worker[i]) {
                    profitIndex = j;
                    currDifficulty = difficulty[j];
                }
            }
            if(profitIndex!=-1) {
                res += profit[profitIndex];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MostProfitAssigningWork mostProfitAssigningWork = new MostProfitAssigningWork();
        /*System.out.println(mostProfitAssigningWork.maxProfitAssignment2(new int[]{2,4,6,8,10},
                new int[]{10,20,30,40,50},
                new int[]{4,5,6,7}));*/
        /*System.out.println(mostProfitAssigningWork.maxProfitAssignment2(new int[]{13,37,58},
                new int[]{4,90,96},
                new int[]{34,73,45}));*/
        System.out.println(mostProfitAssigningWork.maxProfitAssignment2(new int[]{68,35,52,47,86},
                new int[]{67,17,1,81,3},
                new int[]{92,10,85,84,82}));
    }

}
