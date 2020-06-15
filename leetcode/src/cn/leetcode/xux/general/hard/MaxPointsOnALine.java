package cn.leetcode.xux.general.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 *
 * 示例 1:
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 *
 * 示例 2:
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsOnALine {

    public static void main(String[] args) {
        System.out.println(new MaxPointsOnALine().maxPoints(
                new int[][]{{1,1},{1,1},{1,1},{1,1},{2,3},{1,4}}
//                new int[][]{{0,0},{1,65536},{65536,0}}
        ));
    }

    public int maxPoints(int[][] points) {
        if(points==null||points.length==0) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        for(int[] p : points) {
            String key = p[0]+"#"+p[1];
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        int[][] ps = new int[map.size()][3];
        int idx = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] tmp = entry.getKey().split("#");
            ps[idx][0] = Integer.parseInt(tmp[0]);
            ps[idx][1] = Integer.parseInt(tmp[1]);
            ps[idx][2] = entry.getValue();
            idx++;
        }
        Set<String> set = new HashSet<>();
        int ans = 2;
        int n = ps.length;
        if(n==1) {
            ans = points.length;
        }
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(!set.contains(i+"#"+j)) {
                    int curr = ps[i][2] + ps[j][2];
                    for(int k=j+1;k<n;k++) {
                        int[] p1 = ps[i];
                        int[] p2 = ps[j];
                        int[] p3 = ps[k];
                        if((long)(p2[1]-p1[1])*(p3[0]-p1[0])==(long)(p2[0]-p1[0])*(p3[1]-p1[1])) {
                            curr += ps[k][2];
                            set.add(i+"#"+k);
                            set.add(j+"#"+k);
                        }
                    }
                    ans = Math.max(ans, curr);
                }
            }
        }
        return ans;
    }

}
