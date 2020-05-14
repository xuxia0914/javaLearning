package cn.leetcode.xux.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1130. 叶值的最小代价生成树
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 * 示例：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：
 * 有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 * 提示：
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31。
 */
public class MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] maxLeafVal = new int[n][n];
        long[][] minSum = new long[n][n];
        for(int i=0;i<n;i++) {
            maxLeafVal[i][i] = arr[i];
        }
        for(int len=2;len<=n;len++) {
            for(int start=0;start+len-1<n;start++) {
                minSum[start][start+len-1] = Long.MAX_VALUE;
                for(int k=start;k<start+len-1;k++) {
                    int leftMax = maxLeafVal[start][k];
                    int rightMax = maxLeafVal[k+1][start+len-1];
                    long currSum = minSum[start][k]+minSum[k+1][start+len-1]+leftMax*rightMax;
                    if(currSum<minSum[start][start+len-1]) {
                        minSum[start][start+len-1] = currSum;
                        maxLeafVal[start][start+len-1] =
                                Math.max(maxLeafVal[start][k], maxLeafVal[k+1][start+len-1]);
                    }
                }
            }
        }
        return (int)minSum[0][n-1];
    }

    public int mctFromLeafValues1(int[] arr) {
        long[] result = maxLeafVal(arr, 0, arr.length-1);
        return (int)result[1];
    }

    Map<String, long[]> map = new HashMap<>();

    public long[] maxLeafVal(int[] arr, int start, int end) {
        if(start==end) {
            return new long[]{arr[start], 0};
        }
        String key = start+"#"+end;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        long[] result = new long[2];
        result[1] = Long.MAX_VALUE;
        for(int i=start;i<end;i++) {
            long[] leftResult = maxLeafVal(arr, start, i);
            long[] rightResult = maxLeafVal(arr, i+1, end);
            long currMax = Math.max(leftResult[0], rightResult[0]);
            long currSum = leftResult[1]+rightResult[1]+leftResult[0]*rightResult[0];
            if(currSum<result[1]) {
                result[1] = currSum;
                result[0] = currMax;
            }
        }
        map.put(key, result);
        return result;
    }

}
