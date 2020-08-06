package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 *
 * 示例：
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 * 提示：
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 */
public class BinarySubarraysWithSum {

    public int numSubarraysWithSum(int[] A, int S) {
        if(A==null||A.length==0||S>A.length||S<0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for(int num : A) {
            sum += num;
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        int result = 0;
        if(S==0) {
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int val = entry.getValue();
                result += val*(val-1)/2;
            }
        }else {
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if(entry.getKey()>=S) {
                    int val1 = entry.getValue();
                    int val2 = map.getOrDefault(entry.getKey()-S, 0);
                    result += val1*val2;
                }
            }
        }
        return result;
    }

}
