package cn.leetcode.xux.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1748. 最长公共子序列III
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 给出1-n的两个排列P1和P2，求它们的最长公共子序列。请将复杂度控制在O(nlogn)。
 *
 * 样例
 * 样例 1:
 *
 * 输入：[3,2,1,4,5],[1,2,3,4,5]
 * 输出：3
 * 解释：最长公共子序列为[1,4,5]。
 * 样例 2:
 *
 * 输入：[6,9,4,2,8,1,3,5,7],[8,1,2,4,5,3,7,9,6]
 * 输出：4
 * 解释：最长公共子序列为[8,1,3,7]。
 */
public class Lintcode1748 {

    public static void main(String[] args) {
        Lintcode1748 a = new Lintcode1748();
        System.out.println(a.longestCommonSubsequenceIII(new int[]{3,2,1,4,5}, new int[]{1,2,3,4,5}));
        System.out.println(a.longestCommonSubsequenceIII(new int[]{6,9,4,2,8,1,3,5,7}, new int[]{8,1,2,4,5,3,7,9,6}));
    }

    /**
     * @param A:
     * @param B:
     * @return: nothing
     */
    public int longestCommonSubsequenceIII(int[] A, int[] B) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            map.put(A[i], i);
        }
        //把B[i]替换为B[i]在A数组中的索引
        for(int i=0;i<n;i++) {
            B[i] = map.get(B[i]);
        }
        //求B[i]的最长递增子序列
        int tail = 0;
        for(int i=1;i<n;i++) {
            int index = Arrays.binarySearch(B, 0, tail+1, B[i]);
            index = -index-1;
            B[index] = B[i];
            if(index==tail+1) {
                tail++;
            }
        }
        return tail+1;
    }

}
