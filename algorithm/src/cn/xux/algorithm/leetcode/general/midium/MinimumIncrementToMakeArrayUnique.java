package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * 示例 1:
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 *
 * 示例 2:
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 *
 * 提示：
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 */
public class MinimumIncrementToMakeArrayUnique {

    public int minIncrementForUnique(int[] A) {
        if(A==null||A.length<2) {
            return 0;
        }
        int[] cnts = new int[80000];
        for(int num : A) {
            cnts[num]++;
        }
        int result = 0;
        for(int i=0;i<79999;i++) {
            if(cnts[i]>1) {
                result += cnts[i]-1;
                cnts[i+1] += cnts[i]-1;
            }
        }
        return result;
    }

    public int minIncrementForUnique1(int[] A) {
        if(A==null||A.length<2) {
            return 0;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : A) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        int result = 0;
        while(map.size()>0) {
            int key = map.firstKey();
            int value = map.get(key);
            map.remove(key);
            if(value>1) {
                result += --value;
                key++;
                map.put(key, map.getOrDefault(key, 0)+value);
            }
        }
        return result;
    }

}
