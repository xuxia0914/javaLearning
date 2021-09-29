package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * 示例:
 * 输入:[[0,0],[1,0],[2,0]]
 * 输出:2
 *
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] point : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] ints : points) {
                int dis = (point[0] - ints[0]) * (point[0] - ints[0])
                        + (point[1] - ints[1]) * (point[1] - ints[1]);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (int value : map.values()) {
                ans += value * (value - 1);
            }
        }
        return ans;
    }

    public int numberOfBoomerangs1(int[][] points) {
        if(points==null||points.length<3) {
            return 0;
        }
        int n = points.length;
        int[] dists = new int[n];
        int res = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dists[j] = (points[i][0]-points[j][0])*(points[i][0]-points[j][0])+(points[i][1]-points[j][1])*(points[i][1]-points[j][1]);
            }
            Arrays.sort(dists);
            int cnt = 0;
            int pre = -1;
            for(int dist : dists) {
                if(dist==pre) {
                    cnt++;
                }else {
                    if(cnt>1) {
                        res += cnt*(cnt-1);
                    }
                    pre = dist;
                    cnt=1;
                }
            }
            if(cnt>1) {
                res += cnt*(cnt-1);
            }
        }
        return res;
    }

}
