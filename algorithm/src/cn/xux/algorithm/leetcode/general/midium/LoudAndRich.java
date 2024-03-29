package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 851. 喧闹和富有
 * 在一组 N 个人（编号为 0, 1, 2, ..., N-1）中，
 * 每个人都有不同数目的钱，以及不同程度的安静（quietness）。
 * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * 如果能够肯定 person x 比 person y 更有钱的话，
 * 我们会说 richer[i] = [x, y] 。注意 richer 可能只是有效观察的一个子集。
 * 另外，如果 person x 的安静程度为 q ，我们会说 quiet[x] = q 。
 * 现在，返回答案 answer ，其中 answer[x] = y 的前提是，
 * 在所有拥有的钱不少于 person x 的人中，
 * person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 * <p>
 * 示例：
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]],
 * quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，
 * person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中(这可能包括 person 3，4，5，6 以及 7)，
 * 最安静(有较低安静值 quiet[x])的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 * <p>
 * 提示：
 * 1 <= quiet.length = N <= 500
 * 0 <= quiet[i] < N，所有 quiet[i] 都不相同。
 * 0 <= richer.length <= N * (N-1) / 2
 * 0 <= richer[i][j] < N
 * richer[i][0] != richer[i][1]
 * richer[i] 都是不同的。
 * 对 richer 的观察在逻辑上是一致的。
 */
public class LoudAndRich {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            ++inDeg[r[1]];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = i;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x]; // 更新 x 的邻居的答案
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;
    }

    int[] result;

    public int[] loudAndRich1(int[][] richer, int[] quiet) {
        if (quiet == null || quiet.length == 0) {
            return new int[0];
        }
        int len = quiet.length;
        result = new int[len];
        Arrays.fill(result, -1);
        //Map<Integer, List<Integer>> mem = new HashMap<>();
        List<Integer>[] mem = new List[len];
        for (int[] arr : richer) {
            if (mem[arr[1]] == null) {
                mem[arr[1]] = new ArrayList<>();
            }
            mem[arr[1]].add(arr[0]);
        }
        for (int i = 0; i < len; i++) {
            resultOfCurrPerson(mem, i, quiet);
        }
        return result;
    }

    public int resultOfCurrPerson(List<Integer>[] mem, int currPerson, int[] quiet) {
        if (result[currPerson] != -1) {
            return result[currPerson];
        }
        int currResult = currPerson;
        if (mem[currPerson] != null) {
            for (int richerPerson : mem[currPerson]) {
                int richerPersonResult = resultOfCurrPerson(mem, richerPerson, quiet);
                if (quiet[richerPersonResult] < quiet[currResult]) {
                    currResult = richerPersonResult;
                }
            }
        }
        result[currPerson] = currResult;
        return currResult;
    }

}
