package cn.leetcode.xux.easy;

import java.util.Arrays;

public class SortArrayByParityII {

    public static int[] sortArrayByParityII(int[] A) {
        if(A==null||A.length<2) {
            return A;
        }
        int oddIndex=1, evenIndex=0;
        int tmp;
        while(oddIndex<A.length&&evenIndex<A.length) {
            while(oddIndex<A.length&&A[oddIndex]%2==1) {
                oddIndex+=2;
            }
            while(evenIndex<A.length&&A[evenIndex]%2==0) {
                evenIndex+=2;
            }
            if(oddIndex<A.length&&evenIndex<A.length) {
                tmp = A[oddIndex];
                A[oddIndex] = A[evenIndex];
                A[evenIndex] = tmp;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = new int[]{4,2,5,7};
        System.out.println(Arrays.toString(sortArrayByParityII(A)));
    }

}
