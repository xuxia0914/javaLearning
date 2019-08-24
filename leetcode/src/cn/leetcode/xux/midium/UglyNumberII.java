package cn.leetcode.xux.midium;

/**
 * Ugly Number II
 * Write a function to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number.
 * Hint:
 * The naive approach is to call isUgly() for every number until you reach the n-th one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 * An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1L_1L​1​​, L2L_2L​2​​, and L3L_3L​3​​.
 * Assume you have UkUkU​k​​, the kthk^{th}k​th​​ ugly number. Then Uk+1U{k+1}U​k+1​​ must be Min(L1∗2,L2∗3,L3∗5)Min(L_1 2, L_2 3, L_3 * 5)Min(L​1​​∗2,L​2​​∗3,L​3​​∗5).
 * 分析
 * 根据提示中的信息，我们知道丑陋序列可以拆分成3个子序列：
 * 1x2, 2x2, 3x2, 4x2, 5x2, …
 * 1x3, 2x3, 3x3, 4x3, 5x3, …
 * 1x5, 2x5, 3x5, 4x5, 5x5, …
 * 每次从三个列表中取出当前最小的那个加入序列，直到第n个为止。
 */
public class UglyNumberII {

    public static int nthUglyNumber(int n) {
        if(n<0) {
            return 0;
        }
        if(n<2) {
            return n;
        }
        int[] res = new int[n];
        res[0] = 1;
        int n2=0, n3=0, n5=0;
        for(int i=1;i<n;i++) {
            res[i] = Math.min(Math.min(res[n2]*2, res[n3]*3), res[n5]*5);
            if(res[i]==res[n2]*2) {
                n2++;
            }
            if(res[i]==res[n3]*3) {
                n3++;
            }
            if(res[i]==res[n5]*5) {
                n5++;
            }
        }
        return res[n-1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(11));
    }

}
