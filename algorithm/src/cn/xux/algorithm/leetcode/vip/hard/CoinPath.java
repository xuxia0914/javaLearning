package cn.xux.algorithm.leetcode.vip.hard;

import java.util.*;

/**
 * 656. 金币路径
 * 给定一个数组 A（下标从 1 开始）包含 N 个整数：A1，A2，……，AN 和一个整数 B。
 * 你可以从数组 A 中的任何一个位置（下标为 i）跳到下标 i+1，i+2，……，i+B 的任意一个可以跳到的位置上。
 * 如果你在下标为 i 的位置上，你需要支付 Ai 个金币。
 * 如果 Ai 是 -1，意味着下标为 i 的位置是不可以跳到的。
 * 现在，你希望花费最少的金币从数组 A 的 1 位置跳到 N 位置，
 * 你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。
 * 如果有多种花费最少的方案，输出字典顺序最小的路径。
 * 如果无法到达 N 位置，请返回一个空数组。
 *
 * 样例 1:
 * 输入：[1,2,3,4,5]，B=2
 * 输出：[1,3,5]
 * 解释：9是最小的花费
 *
 * 样例 2 :
 * 输入: [1,2,4,-1,2], 1
 * 输出: []
 *
 * 注释 :
 * 路径 Pa1，Pa2，……，Pan 是字典序小于 Pb1，Pb2，……，Pbm 的，
 * 	当且仅当第一个 Pai 和 Pbi 不同的 i 满足 Pai < Pbi，
 * 	如果不存在这样的 i 那么满足 n < m。
 * A1 >= 0。
 * A2, ..., AN （如果存在） 的范围是 [-1, 100]。
 * A 数组的长度范围 [1, 1000].
 * B 的范围 [1, 100].
 */
public class CoinPath {

    public static void main(String[] args) {
        System.out.println(new CoinPath().cheapestJump(new int[]{1,2,3,4,5}, 2));
    }

    /**
     * @param A: a list of integer
     * @param B: an integer
     * @return: return a list of integer
     */
    public List<Integer> cheapestJump(int[] A, int B) {
        // write your code here
        int N = A.length; //省得老写A.length好麻烦
        List<Integer> ans = new LinkedList<>();
        int[] dp = new int[N];
        //dp state: the min cost from the end;
        Arrays.fill(dp, -1);
        dp[N - 1] = A[N - 1]; // 初始化
        Map<Integer, Integer> leftRightMap = new HashMap<>();
        // 这个map从左边的点指向他右边的点，
        //由于这里是倒着找的， parentChild有点confusing，
        //所以用leftRight而不是parentChild,
        for (int i = N - 2; i >= 0; i--) { //从右向左
            if (A[i] == -1) continue;
            for (int b = 1; b <= B && b + i < N; b++) { //从左向右
                if (A[b + i] == -1 || dp[i + b] == -1) continue;
                if (dp[i] == -1 || dp[b + i] + A[i] < dp[i]) {
                    dp[i] = dp[b + i] + A[i];
                    // 现在记一下从i点去哪个点总cost小，
                    leftRightMap.put(i, i + b);
                }
            }
        }

        if (dp[0] == -1) return ans;
        Integer cur = 0;
        while (cur != null) {
            ans.add(cur + 1);
            cur = leftRightMap.get(cur);

        }
        return ans;
    }

    public List<Integer> cheapestJump1(int[] A, int B) {
        // write your code here
        Node ans = helper(A, B, 0);
        return ans==null?new LinkedList<>():ans.path;
    }

    //返回从idx开始到n-1的最小消耗的路径和消耗，如果多个则选路径字典序小的
    private Node helper(int[] A, int B, int idx) {
        if(A[idx]==-1) {
            return null;
        }
        int n = A.length;
        Node ans = null;
        if(idx==n-1) {
            ans = new Node();
            ans.path = new LinkedList<>();
            ans.path.add(idx+1);
            ans.sum = A[idx];
        }
        for(int b=1;b<=B&&idx+b<n;b++) {
            if(A[idx+b]!=-1) {
                Node post = helper(A, B, idx+b);
                if(post!=null) {
                    if(ans==null||post.sum+A[idx]<ans.sum) {
                        if(ans==null) {
                            ans = new Node();
                        }
                        ans.path = new LinkedList<>(post.path);
                        ans.path.add(0, idx+1);
                        ans.sum = post.sum+A[idx];
                    }
                }
            }
        }
        return ans;
    }

    class Node {
        List<Integer> path;
        int sum;
    }

}
