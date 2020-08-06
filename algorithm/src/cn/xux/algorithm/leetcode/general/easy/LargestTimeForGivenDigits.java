package cn.xux.algorithm.leetcode.general.easy;

/**
 * 949. 给定数字能组成的最大时间
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 * 最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
 * 以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
 *
 * 示例 1：
 * 输入：[1,2,3,4]
 * 输出："23:41"
 *
 * 示例 2：
 * 输入：[5,5,5,5]
 * 输出：""
 *
 * 提示：
 * A.length == 4
 * 0 <= A[i] <= 9
 */
public class LargestTimeForGivenDigits {

    public String largestTimeFromDigits(int[] A) {
        int resultHour = -1;
        int resultMin = -1;
        String result = "";
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                if(j!=i) {
                    for(int k=0;k<4;k++) {
                        if(k!=i&&k!=j) {
                            int l = 6-i-j-k;
                            int hour = 10*A[i]+A[j];
                            int min = 10*A[k]+A[l];
                            if(hour<25&&min<60&&(resultHour==-1||hour>resultHour||(hour==resultHour&&min>resultMin))) {
                                resultHour = hour;
                                resultMin = min;
                                result = ""+A[i]+A[j]+":"+A[k]+A[l];
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
