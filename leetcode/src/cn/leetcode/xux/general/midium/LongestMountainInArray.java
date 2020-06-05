package cn.leetcode.xux.general.midium;

/**
 * 845. 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 *
 * 示例 1：
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 *
 * 示例 2：
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class LongestMountainInArray {

    public int longestMountain(int[] A) {
        int result = 0;
        if(A==null||A.length<3) {
            return result;
        }
        int n = A.length;
        //0 还没开始，正在上升，正在下降
        int state = 0;
        int start = 0;
        for(int i=1;i<n;i++) {
            if(A[i]>A[i-1]) {
                if(state==0) {
                    start = i-1;
                    state = 1;
                }else if(state==2) {
                    result = Math.max(result, i - start);
                    start = i-1;
                    state = 1;
                }
            }else if(A[i]==A[i-1]) {
                if(state==2) {
                    result = Math.max(result, i - start);
                }
                state = 0;
            }else {
                if(state==1) {
                    state = 2;
                }
            }
        }
        if(state==2) {
            result = Math.max(result, n-start);
        }
        return result;
    }

}
