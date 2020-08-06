package cn.xux.algorithm.leetcode.lcci.midium;

/**
 * 面试题 16.13. 平分正方形
 * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。
 * 假设正方形顶边和底边与 x 轴平行。
 * 每个正方形的数据square包含3个数值，
 * 正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，
 * 以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，
 * 请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，
 * 这2个点所连成的线段一定会穿过另外2个交点）。
 * 2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，
 * 要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
 * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
 *
 * 示例：
 * 输入：
 * square1 = {-1, -1, 2}
 * square2 = {0, -1, 2}
 * 输出： {-1,0,2,0}
 * 解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
 *
 * 提示：
 * square.length == 3
 * square[2] > 0
 */
public class BisectSquares {

    public double[] cutSquares(int[] square1, int[] square2) {
        //计算两个正方形的中心点
        double[] circle1 = {square1[0] + square1[2]/2.0,
                square1[1] + square1[2]/2.0};
        double[] circle2 = {square2[0] + square2[2]/2.0,
                square2[1] + square2[2]/2.0};
        double[] ans = new double[4];
        if(circle1[0] == circle2[0]) {
            //垂直的直线
            ans[0] = ans[2] = circle1[0];
            ans[1] = Math.min(square1[1], square2[1]);
            ans[3] = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            return ans;
        }
        //计算斜率
        double k = (circle2[1] - circle1[1]) / (circle2[0] - circle1[0]);
        if(Math.abs(k) < 1) {
            //和垂直的边相交
            double leftX = Math.min(square1[0], square2[0]);
            double rightX = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
            double leftY = k * (leftX - circle1[0]) + circle1[1];
            double rightY = k * (rightX - circle1[0]) + circle1[1];
            return new double[]{leftX, leftY, rightX, rightY};
        } else {
            //和水平的边相交
            double bottonY = Math.min(square1[1], square2[1]);
            double topY = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            double bottonX = (bottonY - circle1[1]) / k + circle1[0];
            double topX = (topY - circle1[1]) / k + circle1[0];
            return bottonX < topX ? new double[]{bottonX, bottonY, topX, topY}
                    : new double[]{topX, topY, bottonX, bottonY};
        }
    }

}
