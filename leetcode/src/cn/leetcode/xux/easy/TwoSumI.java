package cn.leetcode.xux.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 找出数组中两个数是它们之和等于给定的值
 */
public class TwoSumI {

    public static int[] solution1(int[] A, int sum) {
        for(int i=0;i<A.length-1;i++) {
            for(int j=i+1;j<A.length;j++) {
                if(A[i]+A[j]==sum) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] solution2(int[] A, int sum) {
        if(A==null||A.length<2) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<A.length;i++) {
            int tmp = sum-A[i];
            if(map.containsKey(tmp)) {
                return new int[]{map.get(tmp), i};
            }
            map.put(A[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution2(new int[]{2,7,11,15}, 9)));
    }

}
