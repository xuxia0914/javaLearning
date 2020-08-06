package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 870. 优势洗牌
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 *
 * 示例 1：
 * 输入：A = [2,7,11,15], B = [1,10,4,11]
 * 输出：[2,11,7,15]
 *
 * 示例 2：
 * 输入：A = [12,24,8,32], B = [13,25,32,11]
 * 输出：[24,32,8,12]
 *
 * 提示：
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {

    //[718967141,189971378,341560426,23521218,339517772]
    //[967482459,978798455,744530040,3454610,940238504]
    public static void main(String[] args) {
        new AdvantageShuffle().advantageCount(
                new int[]{718967141,189971378,341560426,23521218,339517772},
                new int[]{967482459,978798455,744530040,3454610,940238504});
    }

    public int[] advantageCount(int[] A, int[] B) {
        if(A==null||A.length==0) {
            return new int[]{};
        }
        int n = A.length;
        boolean[] visited = new boolean[n];
        int[][] newB = new int[n][2];
        for(int i=0;i<n;i++) {
            newB[i][1] = i;
            newB[i][0] = B[i];
        }
        Arrays.sort(A);
        Arrays.sort(newB, Comparator.comparingInt(o -> o[0]));
        int[] result = new int[n];
        int idx1 = 0;
        int idx2 = 0;
        while(idx1<n) {
            if(A[idx1]>newB[idx2][0]) {
                result[newB[idx2][1]] = A[idx1];
                visited[idx1] = true;
                idx1++;
                idx2++;
            }else {
                idx1++;
            }
        }
        idx1 = 0;
        for(int i=idx2;i<n;i++) {
            while(visited[idx1]) {
                idx1++;
            }
            result[newB[i][1]] = A[idx1++];
        }
        return result;
    }

}
