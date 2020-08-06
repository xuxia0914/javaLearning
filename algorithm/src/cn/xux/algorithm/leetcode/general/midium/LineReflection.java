package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.
 * Example 1:
 * Given points = [[1,1],[-1,1]], return true.
 * Example 2:
 * Given points = [[1,1],[-1,-1]], return false.
 * Follow up:
 * Could you do better than O(n2)?
 * Hint:
 * Find the smallest and largest x-value for all points.
 * If there is a line then it should be at y = (minX + maxX) / 2.
 * For each point, make sure that it has a reflected point in the opposite side.
 */
public class LineReflection {

    public static boolean solution(int[][] points) {
        if(points==null||points.length<2||points[0].length!=2) {
            return false;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int maxX = 0, minX = 0;
        for(int[] point : points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            Set<Integer> set = map.getOrDefault(point[1], new HashSet<Integer>());
            set.add(point[0]);
            map.put(point[1], set);
        }
        int a = maxX+minX;  //对称轴为 x = a/2
        for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            Set<Integer> set = entry.getValue();
            for(Integer i : set) {
                if(!set.contains(a-i)) {
                    return false;
                }
            }
        }
        return true;
    }

}
