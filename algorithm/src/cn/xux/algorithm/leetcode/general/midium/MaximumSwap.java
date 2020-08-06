package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 * 给定数字的范围是 [0, 108]
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        if(num<10) {
            return num;
        }
        char[] arr = String.valueOf(num).toCharArray();
        int[] lastIdxs = new int[10];
        Arrays.fill(lastIdxs, -1);
        for(int i=0;i<arr.length;i++) {
            lastIdxs[arr[i]-'0'] = i;
        }
        for(int i=9;i>=0;i--) {
            if(lastIdxs[i]==-1) {
                continue;
            }
            for(int j=0;j<lastIdxs[i];j++) {
                if(i>arr[j]-'0') {
                    char tmp = arr[lastIdxs[i]];
                    arr[lastIdxs[i]] = arr[j];
                    arr[j] = tmp;
                    return Integer.parseInt(new String(arr));
                }
            }
        }
        return num;
    }

}
