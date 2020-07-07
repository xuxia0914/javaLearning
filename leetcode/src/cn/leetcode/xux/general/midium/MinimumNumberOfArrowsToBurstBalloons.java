package cn.leetcode.xux.general.midium;

import java.util.Arrays;

/**
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，
 * 提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，
 * 所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。
 * 开始坐标总是小于结束坐标。平面内最多存在104个气球。
 *
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。
 * 在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。
 * 可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * Example:
 * 输入:[[10,16], [2,8], [1,6], [7,12]]
 * 输出:2
 *
 * 解释:对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(new int[][]{{9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}}));
    }

    public int findMinArrowShots(int[][] points) {
        if(points==null||points.length==0) {
            return 0;
        }
        Arrays.sort(points, (o1, o2)->o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1]);
        int result = 0;
        int i = 0;
        while(i<points.length) {
            int[] curr = points[i];
            int j = i+1;
            while(j<points.length&&curr[1]>=points[j][0]) {
                curr[0] = Math.max(curr[0], points[j][0]);
                curr[1] = Math.min(curr[1], points[j][1]);
                j++;
            }
            result++;
            i = j;
        }
        return result;
    }

}
