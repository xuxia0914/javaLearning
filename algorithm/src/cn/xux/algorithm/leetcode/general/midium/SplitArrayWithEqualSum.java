package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 548. 将数组分割成和相等的子数组
 * 给定一个有 n 个整数的数组，你需要找到满足以下条件的三元组 (i, j, k) ：
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * 子数组 (0, i - 1)，(i + 1, j - 1)，(j + 1, k - 1)，(k + 1, n - 1) 的和应该相等。
 * 这里我们定义子数组 (L, R) 表示原数组从索引为L的元素开始至索引为R的元素。
 *
 * 示例:
 * 输入: [1,2,1,2,1,2,1]
 * 输出: True
 * 解释:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) =
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 *
 * 注意:
 * 1 <= n <= 2000。
 * 给定数组中的元素会在 [-1,000,000, 1,000,000] 范围内。
 */
public class SplitArrayWithEqualSum {

    public boolean splitArray(List<Integer> nums) {
        if(nums==null||nums.size()<7) {
            return false;
        }
        int n = nums.size();
        int[] preSum = new int[n];
        preSum[0] = nums.get(0);
        for(int i=1;i<n;i++) {
            preSum[i] = preSum[i-1]+nums.get(i);
        }
        for(int mid=3;mid<n-3;mid++) {
            Set<Integer> set = new HashSet<>();
            int left;
            int right;
            for(int leftMid=1;leftMid<mid-1;leftMid++) {
                left = preSum[leftMid-1];
                right = preSum[mid-1]-preSum[leftMid];
                if(left==right) {
                    set.add(left);
                }
            }
            if(set.size()>0) {
                for(int rightMid=mid+2;rightMid<n;rightMid++) {
                    left = preSum[rightMid-1]-preSum[mid];
                    right = preSum[n-1]-preSum[rightMid];
                    if(left==right&&set.contains(left)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
