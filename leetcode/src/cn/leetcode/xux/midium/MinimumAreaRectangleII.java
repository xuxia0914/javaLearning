package cn.leetcode.xux.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 963. 最小面积矩形 II
 * 给定在 xy 平面上的一组点，确定由这些点组成的任何矩形的最小面积，其中矩形的边不一定平行于 x 轴和 y 轴。
 * 如果没有任何矩形，就返回 0。
 *
 * 示例 1：
 * 输入：[[1,2],[2,1],[1,0],[0,1]]
 * 输出：2.00000
 * 解释：最小面积的矩形出现在 [1,2],[2,1],[1,0],[0,1] 处，面积为 2。
 *
 * 示例 2：
 * 输入：[[0,1],[2,1],[1,1],[1,0],[2,0]]
 * 输出：1.00000
 * 解释：最小面积的矩形出现在 [1,0],[1,1],[2,1],[2,0] 处，面积为 1。
 *
 * 示例 3：
 * 输入：[[0,3],[1,2],[3,1],[1,3],[2,1]]
 * 输出：0
 * 解释：没法从这些点中组成任何矩形。
 *
 * 示例 4：
 * 输入：[[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
 * 输出：2.00000
 * 解释：最小面积的矩形出现在 [2,1],[2,3],[3,3],[3,1] 处，面积为 2。
 *
 * 提示：
 * 1 <= points.length <= 50
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * 所有的点都是不同的。
 * 与真实值误差不超过 10^-5 的答案将视为正确结果。
 */
public class MinimumAreaRectangleII {

    public static void main(String[] args) {
//        System.out.println(new MinimumAreaRectangleII().minAreaFreeRect(new int[][]{{0,3},{1,2},{3,1},{1,3},{2,1}}));
        System.out.println(new MinimumAreaRectangleII().minAreaFreeRect(new int[][]{{1,2},{2,1},{1,0},{0,1}}));
    }

    public double minAreaFreeRect(int[][] points) {
        if(points==null||points.length==0) {
            return 0d;
        }
        int n = points.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            int[] point = points[i];
            map.put(point[0]+"#"+point[1], i);
        }
        double result = Double.MAX_VALUE;
        boolean flag = false;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) if(j!=i) {
                for(int k=0;k<n;k++) if(k!=i&&k!=j) {
                    if((points[j][1]-points[i][1])*(points[k][1]-points[i][1])
                            !=-(points[j][0]-points[i][0])*(points[k][0]-points[i][0])) {
                        continue;
                    }
                    int x = points[j][0]+points[k][0]-points[i][0];
                    int y = points[j][1]+points[k][1]-points[i][1];
                    String key = x+"#"+y;
                    if(map.containsKey(key)) {
                        int val = map.get(key);
                        if(val!=i&&val!=j&&val!=k) {
                            double curr = Math.sqrt(Math.pow(points[i][0]-points[j][0], 2)+Math.pow(points[i][1]-points[j][1], 2))
                                    * Math.sqrt(Math.pow(points[i][0]-points[k][0], 2)+Math.pow(points[i][1]-points[k][1], 2));
                            result = Math.min(result, curr);
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag?result:0d;
    }

}
