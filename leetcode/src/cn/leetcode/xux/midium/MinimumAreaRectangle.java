package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 939. 最小面积矩形
 * 给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，其中矩形的边平行于 x 轴和 y 轴。
 * 如果没有任何矩形，就返回 0。
 *
 * 示例 1：
 * 输入：[[1,1],[1,3],[3,1],[3,3],[2,2]]
 * 输出：4
 *
 * 示例 2：
 * 输入：[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * 输出：2
 *
 * 提示：
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * 所有的点都是不同的。
 */
public class MinimumAreaRectangle {

    public int minAreaRect(int[][] points) {
        if(points==null||points.length<4) {
            return 0;
        }
        int len = points.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<len;i++) {
            set.add(points[i][0]*40001+points[i][1]);
        }
        int result = Integer.MAX_VALUE;
        for(int i=0;i<len-1;i++) {
            for(int j=i+1;j<len;j++) {
                int[] point1 = points[i];
                int[] point2 = points[j];
                if(point1[0]!=point2[0]&&point1[1]!=point2[1]
                        &&set.contains(point1[0]*40001+point2[1])
                        &&set.contains(point2[0]*40001+point1[1])) {
                    result = Math.min(result, Math.abs((point1[0]-point2[0])*(point1[1]-point2[1])));
                }
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }

}
