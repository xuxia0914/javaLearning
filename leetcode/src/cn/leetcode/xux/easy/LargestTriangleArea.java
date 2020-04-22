package cn.leetcode.xux.easy;

/**
 * 812. 最大三角形面积
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释: 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 *
 * 注意:
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 *  -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 */
public class LargestTriangleArea {

    //鞋带公式
    public double largestTriangleArea(int[][] points) {
        if(points==null||points.length<3) {
            return 0;
        }
        int n = points.length;
        double result = 0d;
        for(int i=0;i<n-2;i++) {
            for(int j=1;j<n-1;j++) {
                for(int k=2;k<n;k++) {
                    int[] point1 = points[i];
                    int[] point2 = points[j];
                    int[] point3 = points[k];
                    double curr = (double)Math.abs(point1[0]*point2[1]+point2[0]*point3[1]+point3[0]*point1[1]
                            -point1[1]*point2[0]-point2[1]*point3[0]-point3[1]*point1[0])/2;
                    result = Math.max(result, curr);
                }
            }
        }
        return result;
    }

}
