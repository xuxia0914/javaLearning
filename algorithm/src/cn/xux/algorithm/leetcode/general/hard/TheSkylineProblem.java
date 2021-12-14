package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. 天际线问题
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
 * 给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * <p>
 * 每个建筑物的几何信息由数组 buildings 表示，
 * 其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * <p>
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 天际线 应该表示为由 “关键点” 组成的列表，
 * 格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
 * 关键点是水平线段的左端点。
 * 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，
 * 仅用于标记天际线的终点。
 * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * 注意：输出天际线中不得有连续的相同高度的水平线。
 * 例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...]
 * 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：
 * [...[2 3], [4 5], [12 7], ...]
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * *图A中所有建筑物的尺寸记录为：[
 * * [2 9 10],
 * * [3 7 15],
 * * [5 12 12],
 * * [15 20 10],
 * * [19 24 8] ] 。
 * * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。
 * * 关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。
 * * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * * 例如，图B中的天际线应该表示为：[
 * * [2 10],
 * * [3 15],
 * * [7 12],
 * * [12 0],
 * * [15 10],
 * * [20 8],
 * * [24, 0] ]。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：buildings = [[0,2,3],[2,5,3]]
 * 输出：[[0,3],[5,0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings 按 lefti 非递减排序
 */
public class TheSkylineProblem {

    public static void main(String[] args) {
        System.out.println(new TheSkylineProblem().getSkyline(new int[][]{{0, 2, 3}, {2, 5, 3}}));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return ans;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        for (int[] building : buildings) {
            queue.offer(new int[]{building[0], -building[2]});
            queue.offer(new int[]{building[1], building[2]});
        }
        PriorityQueue<Integer> heightQueue = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        int currHeight = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[1] < 0) {
                heightQueue.offer(-curr[1]);
            } else {
                heightQueue.remove(curr[1]);
            }
            int height = heightQueue.isEmpty() ? 0 : heightQueue.peek();
            if (height != currHeight) {
                List<Integer> list = new ArrayList<>();
                list.add(curr[0]);
                list.add(height);
                ans.add(list);
                currHeight = height;
            }
        }
        return ans;
    }

}
