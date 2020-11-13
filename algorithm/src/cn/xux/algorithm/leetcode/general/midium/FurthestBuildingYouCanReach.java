package cn.xux.algorithm.leetcode.general.midium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1642. 可以到达的最远建筑
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
 * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 *
 * 示例 1：
 *
 * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * 输出：4
 * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
 * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
 * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
 * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
 * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
 * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
 *
 * 示例 2：
 * 输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * 输出：7
 *
 * 示例 3：
 * 输入：heights = [14,3,19,3], bricks = 17, ladders = 0
 * 输出：3
 *
 * 提示：
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 106
 * 0 <= bricks <= 109
 * 0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if(heights==null||heights.length==0) {
            return -1;
        }
        int ans = 0;
        int n = heights.length;
        // 用梯子跳过的楼层高度的小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while(ans<n-1) {
            int height = heights[ans+1]-heights[ans];
            // 当高度小于等于0，直接跳到下一个建筑物
            if(height<=0) {
                ans++;
                continue;
            }
            // 下面是高度大于0的情况
            // 如果还有梯子可用，优先选梯子，并把梯子跳过的高度入堆
            if(ladders>0) {
                queue.offer(height);
                ans++;
                ladders--;
                continue;
            }
            // 下面是高度大于0且没有梯子可用的情况
            // 如果堆为空(即前面也没有用过梯子)，或者已经有用过的梯子但是梯子跳过的最小高度不小于当前高度，
            // 那么当前高度只能砖块越过(砖块数要大于等于当前高度)
            if((queue.isEmpty()&&bricks>=height)
                    ||(!queue.isEmpty()&&queue.peek()>=height&&bricks>=height)) {
                ans++;
                bricks -= height;
                continue;
            }
            // 如果堆不为空(即前面有用过梯子)，而且梯子越过的最小高度小于当前高度，
            // 那么把之前用梯子越过的最小高度改为用砖块越过(如果砖块数不小于梯子跳过的最小高度)，当前高度用梯子跳过
            if(!queue.isEmpty()&&height>queue.peek()&&bricks>=queue.peek()) {
                bricks -= queue.poll();
                ans++;
                queue.offer(height);
                continue;
            }
            break;
        }
        return ans;
    }

}
