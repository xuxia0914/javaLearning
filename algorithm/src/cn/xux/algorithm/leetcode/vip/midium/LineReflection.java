package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 356. 直线镜像
 * 在一个二维平面空间中，给你 n 个点的坐标。
 * 问，是否能找出一条平行于 y 轴的直线，让这些点关于这条直线成镜像排布？
 *
 * 示例 1：
 * 输入: [[1,1],[-1,1]]
 * 输出: true
 *
 * 示例 2：
 * 输入: [[1,1],[-1,-1]]
 * 输出: false
 *
 * 拓展：你能找到比 O(n^2) 更优的解法吗?
 */
public class LineReflection {

    public boolean isReflected(int[][] points) {
        if(points==null||points.length%2==1) {
            return false;
        }
        int min = points[0][0];
        int max = points[0][0];
        for(int[] p : points) {
            min = Math.min(min, p[0]);
            max = Math.max(max, p[0]);
        }
        int mid2 = max+min;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] p : points) {
            if(!map.containsKey(p[1])) {
                map.put(p[1], new HashMap<>());
            }
            Map<Integer, Integer> curr = map.get(p[1]);
            curr.put(p[0],curr.getOrDefault(p[0], 0)+1);
        }
        for(Map<Integer, Integer> curr : map.values()) {
            for(Map.Entry<Integer, Integer> entry : curr.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                if(curr.getOrDefault(mid2-key, 0)!=val) {
                    return false;
                }
            }
        }
        return true;
    }

}
