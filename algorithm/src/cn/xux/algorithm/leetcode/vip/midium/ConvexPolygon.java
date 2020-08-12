package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 469. 凸多边形
 * 给定一个按顺序连接的多边形的顶点，判断该多边形是否为凸多边形。（凸多边形的定义）
 * 注：
 * 顶点个数至少为 3 个且不超过 10,000。
 * 坐标范围为 -10,000 到 10,000。
 * 你可以假定给定的点形成的多边形均为简单多边形（简单多边形的定义）。换句话说，保
 * 每个顶点处恰好是两条边的汇合点，并且这些边 互不相交 。
 *
 * 示例 1：
 * [[0,0],[0,1],[1,1],[1,0]]
 * 输出： True
 *
 * 示例 2：
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 * 输出： False
 *
 * 思路:
 * 叉乘判断
 * 设A(x1,y1),B(x2,y2),C(x3,y3)则三角形两边的矢量分别是：
 * AB=(x2-x1,y2-y1), AC=(x3-x1,y3-y1)
 * 则AB和AC的叉积为：(2*2的行列式) 值为：(x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)
 * 利用右手法则进行判断：
 * 如果AB*AC>0,则三角形ABC是逆时针的
 * 如果AB*AC<0,则三角形ABC是顺时针的
 * 因为不知道顶点是顺时针输入，还是逆时针输入，所以要记录符号，后面点叉乘如果一样就是凸多边形。
 */
public class ConvexPolygon {

    public boolean isConvex(int[][] points) {
        int n = points.length;
        int pre = 0;
        int curr = 0;
        for(int i=0;i<n;i++) {
            int dx1 = points[(i+1)%n][0] - points[i][0];
            int dx2 = points[(i+2)%n][0] - points[(i+1)%n][0];
            int dy1 = points[(i+1)%n][1] - points[i][1];
            int dy2 = points[(i+2)%n][1] - points[(i+1)%n][1];
            curr = dx1*dy2 - dx2*dy1;
            if(curr!=0) {
                if(curr*pre<0) {
                    return false;
                }else {
                    pre = curr;
                }
            }
        }
        return true;
    }

}
