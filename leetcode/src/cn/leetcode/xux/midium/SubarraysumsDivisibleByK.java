package cn.leetcode.xux.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 974. 和可被 K 整除的子数组
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 * 示例：
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * 提示：
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraysumsDivisibleByK {

    public static void main(String[] args) {
        System.out.println(new SubarraysumsDivisibleByK().subarraysDivByK(
                new int[]{4,5,0,-2,-3,1}, 5
        ));
    }

    public int subarraysDivByK(int[] A, int K) {
        if(A==null||A.length==0||K<2) {
            return 0;
        }
        int sum = 0;
        int[] sumCnts = new int[K];
        sumCnts[0] = 1;
        int ans = 0;
        for(int a : A) {
            sum += a;
            int key = (sum%K+K)%K;
            ans += sumCnts[key];
            sumCnts[key]++;
        }
        return ans;
    }

}
