package cn.leetcode.xux.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1755. 和的路径
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 有一个n×m的矩阵，和一个目标值k。
 * 你需要从[1,1]开始走到到[n,m]，只能向下或者向右走，经过的路径上的和记为f。现在你需要计算有多少条路径使得f=k。
 *
 * 样例
 * 样例 1:
 *
 * 输入: rec = [[2, 1, 5], [3, 10, 1], [1, 3, 0]], k = 9
 * 输出: 2
 * 解释:
 * (1,1)→(1,2)→(1,3)→(2,3)→(3,3)
 * (1,1)→(2,1)→(3,1)→(3,2)→(3,3)
 * 样例 2:
 *
 * 输入: rec = [[1, 1, 1], [1, 1, 1], [1, 1, 1]], k = 5
 * 输出: 6
 * 解释:
 * (1, 1)→(2, 1)→(3, 1)→(3, 2)→(3, 3)
 * (1, 1)→(2, 1)→(2, 2)→(3, 2)→(3, 3)
 * (1, 1)→(2, 1)→(2, 2)→(2, 3)→(3, 3)
 * (1, 1)→(1, 2)→(2, 2)→(3, 2)→(3, 3)
 * (1, 1)→(1, 2)→(2, 2)→(2, 3)→(3, 3)
 * (1, 1)→(1, 2)→(1, 3)→(2, 3)→(3, 3)
 * 注意事项
 * 1 \leq n, m \leq 201≤n,m≤20
 * 1 \leq rec[i][j] \leq 10000000001≤rec[i][j]≤1000000000
 * k \leq 10000000000000k≤10000000000000
 */
public class Lintcode1755 {

    /**
     * @param rec: the map
     * @param k: the goal
     * @return: the sum of paths
     */
    public long sumPath(int[][] rec, long k) {
        int n = rec.length, m = rec[0].length;
        int n1 = n / 2, m1 = m;
        HashMap<Long, Long>[] map1 = (HashMap<Long, Long>[]) new HashMap[m];
        HashMap<Long, Long>[] map2 = (HashMap<Long, Long>[]) new HashMap[m];
        for (int i = 0; i < n / 2; i++) {
            HashMap<Long, Long>[] nmap1 = (HashMap<Long, Long>[]) new HashMap[m];
            for (int j = 0; j < m; j++) {
                nmap1[j] = new HashMap<>();
            }
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    nmap1[0].put((long) rec[i][j], 1L);
                    continue;
                }
                if (i > 0) {
                    for (long key : map1[j].keySet()) {
                        if (key + rec[i][j] > k) continue;
                        long nkey = key + rec[i][j];
                        long v = map1[j].get(key);
                        nmap1[j].put(nkey, nmap1[j].getOrDefault(nkey, 0L) + v);
                    }
                }
                if (j > 0) {
                    for (long key : nmap1[j - 1].keySet()) {
                        if (key + rec[i][j] > k) continue;
                        long nkey = key + rec[i][j];
                        long v = nmap1[j - 1].get(key);
                        nmap1[j].put(nkey, nmap1[j].getOrDefault(nkey, 0L) + v);
                    }
                }
            }
            map1 = nmap1;
        }

        for (int i = n - 1; i >= n / 2; i--) {
            HashMap<Long, Long>[] nmap2 = (HashMap<Long, Long>[]) new HashMap[m];
            for (int j = 0; j < m; j++) {
                nmap2[j] = new HashMap<>();
            }
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    nmap2[j].put((long) rec[i][j], 1L);
                    continue;
                }
                if (i < n - 1) {
                    for (long key : map2[j].keySet()) {
                        if (key + rec[i][j] > k) continue;
                        long nkey = key + rec[i][j];
                        long v = map2[j].get(key);
                        nmap2[j].put(nkey, nmap2[j].getOrDefault(nkey, 0L) + v);
                    }
                }
                if (j < m - 1) {
                    for (long key : nmap2[j + 1].keySet()) {
                        if (key + rec[i][j] > k) continue;
                        long nkey = key + rec[i][j];
                        long v = nmap2[j + 1].get(key);
                        nmap2[j].put(nkey, nmap2[j].getOrDefault(nkey, 0L) + v);
                    }
                }
            }
            map2 = nmap2;
        }
        long ret = 0;
        for (int j = 0; j < m; j++) {
            for (long key : map1[j].keySet()) {
                ret += map2[j].getOrDefault(k - key, 0L) * map1[j].get(key);
            }
        }
        return ret;
    }

    /**
     * @param rec: the map
     * @param k: the goal
     * @return: the sum of paths
     */
    //MLE
    public long sumPath1(int[][] rec, long k) {
        // Write your code here.
        int m = rec.length;
        int n = rec[0].length;
        Map<Long, Long>[] dp = new Map[n];
        long sum = 0;
        for(int j=0;j<n;j++) {
            dp[j] = new HashMap<>();
            sum += rec[0][j];
            dp[j].put(sum, 1L);
        }
        for(int i=1;i<rec.length;i++) {
            Map<Long, Long>[] newDp = new Map[n];
            for(int j=0;j<n;j++) {
                newDp[j] = new HashMap<>();
                for(Map.Entry<Long, Long> entry : dp[j].entrySet()) {
                    long key = entry.getKey();
                    long value =  entry.getValue();
                    long newKey = key+rec[i][j];
                    if(newKey<=k) {
                        newDp[j].put(newKey,newDp[j].getOrDefault(newKey, 0L)+value);
                    }
                }
                if(j>0) {
                    for(Map.Entry<Long, Long> entry : newDp[j-1].entrySet()) {
                        long key = entry.getKey();
                        long value =  entry.getValue();
                        long newKey = key+rec[i][j];
                        if(newKey<=k) {
                            newDp[j].put(newKey,newDp[j].getOrDefault(newKey, 0L)+value);
                        }
                    }
                }
            }
            dp = newDp;
        }
        return dp[n-1].get(k);
    }

    /**
     * @param rec: the map
     * @param k: the goal
     * @return: the sum of paths
     */
    //TLE
    public long sumPath2(int[][] rec, long k) {
        // Write your code here.
        return dfs(rec, k, rec.length-1, rec[0].length-1);
    }

    Map<String, Long> mem = new HashMap<>();

    //表示从(0,0)到(i,j)的路径和等于k的路径个数
    private long dfs(int[][] rec, long k, int i, int j) {
        if(i==0&&j==0) {
            return k==rec[i][j]?1:0;
        }
        if(k<=rec[i][j]) {
            return 0;
        }
        String key = i+"#"+j+"#"+k;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        long ans = 0;
        if(i>0) {
            ans += dfs(rec, k-rec[i][j], i-1, j);
        }
        if(j>0) {
            ans += dfs(rec, k-rec[i][j], i, j-1);
        }
        mem.put(key, ans);
        return ans;
    }

}
