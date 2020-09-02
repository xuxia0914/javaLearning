package cn.xux.algorithm.leetcode.general.hard;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. 最小区间
 * 你有 k 个升序排列的整数列表。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例：
 * 输入：[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 提示：
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -105 <= 元素的值 <= 105
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 */
public class SmallestRangeCoveringElementsFromKLists {

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                Comparator.comparingInt(o -> nums.get(o[0]).get(o[1])));
        int max = -100000;
        for(int i=0;i<nums.size();i++) {
            queue.offer(new int[]{i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        int[] ans = new int[]{-100000, 100000};
        while(true) {
            int[] minIdx = queue.poll();
            if(ans[1]-ans[0]>max-nums.get(minIdx[0]).get(minIdx[1])) {
                ans[0] = nums.get(minIdx[0]).get(minIdx[1]);
                ans[1] = max;
            }
            if(minIdx[1]==nums.get(minIdx[0]).size()-1) {
                break;
            }
            minIdx[1]++;
            max = Math.max(max, nums.get(minIdx[0]).get(minIdx[1]));
            queue.offer(minIdx);
        }
        return ans;
    }

}