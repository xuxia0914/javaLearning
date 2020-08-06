package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 874. 最大的假期天数
 * 中文English
 * LintCode想让它最好的员工之一选择在N个城市间旅行来收集算法问题。
 * 但是只工作不玩耍，聪明的孩子也会变傻，你可以在某些特定的城市并且一个星期里去度假。
 * 你的工作是安排旅行，尽可能多的假期，但是有一些规则和限制你需要遵守。
 * 规则和限制:
 * 您只能在1个城市中旅行，由0到N-1的索引表示。一开始，你周一在城市0。
 * 这些城市都是通过航班连接起来的。这些航班被表示为N*N矩阵(非必要对称)，
 * 称为代表航空公司从城市i到j城市状态的flights矩阵。如果没有从城市i到城市j的航班，
 * flights[i][j] = 0;否则,flights[i][j]= 1。还有，flights[i][i] = 0。
 * 你总共有K周(每周有7天)旅行。你只能每天最多乘坐一次航班，而且只能在每周一早上乘坐航班。
 * 由于飞行时间太短，我们不考虑飞行时间的影响。
 * 对于每个城市，你只能在不同的星期里限制休假日，给定一个命名为days的N*K矩阵表示这种关系。
 * 对于days[i][j]的值，它表示你可以在j+1周的城市i里休假的最长天数，
 * 你得到的是flights矩阵和days矩阵，你需要输出你在K周期间可以获得的最长假期。
 *
 * 样例
 * 样例 1:
 *
 * 输入：flights = [[0,1,1],[1,0,1],[1,1,0]]，days = [[1,3,1],[6,0,3],[3,3,3]]
 * 输出：12
 * 解释：
 * Ans = 6 + 3 + 3 = 12.
 *
 * 最好的策略之一是:
 * 	第一周:周一从城市0飞往城市1，休假6天，工作1天。
 * 	(虽然你从城市0开始，但从周一开始，我们也可以飞到其他城市去。)
 * 	第二周:周一从城市1飞到城市2，休假3天，工作4天。
 * 	第三周:呆在城市2，休假3天，工作4天。
 * 样例 2:
 *
 * 输入： flights = [[0,0,0],[0,0,0],[0,0,0]]，days = [[1,1,1],[7,7,7],[7,7,7]]
 * 输出：3
 * 解释：
 * Ans = 1 + 1 + 1 = 3.
 *
 * 	因为没有航班可以让你飞到另一个城市，所以你必须在城市里呆3个星期。
 * 	每个星期，你只有一天休假的时间和六天的工作。
 * 	所以假期的最大天数是3
 * 样例 3:
 *
 * 输入：flights = [[0,1,1],[1,0,1],[1,1,0]]，days = [[7,0,0],[0,7,0],[0,0,7]]
 * 输出：21
 * 解释：
 * Ans = 7 + 7 + 7 = 21.
 *
 * 	最好的策略之一是:
 * 	第一周:呆在城市0，玩7天。
 * 	第二周:周一从城市0飞到城市1，然后休假7天。
 * 	第三周:周一从城市1飞到城市2，然后休假7天。
 * 注意事项
 * N和K是正整数，它们在[1, 100]的范围内。
 * 在flights矩阵中，所有的值都是在[0, 1]范围内的整数。
 * 在days矩阵中，所有的值都是范围内的整数[0, 7]。
 * 你可以呆在一个超过假期天数的城市，但是你应该多工作几天，这不会算作休假日。
 * 如果你从A市飞到B市，并在那天休假，那么假期的扣除将计入B城市的假期天数。
 * 我们不考虑飞行时间对计算假期的影响。
 */
public class Lintcode874 {

    /**
     * @param flights: the airline status from the city i to the city j
     * @param days: days[i][j] represents the maximum days you could take vacation in the city i in the week j
     * @return: the maximum vacation days you could take during K weeks
     */
    public int maxVacationDays(int[][] flights, int[][] days) {
        // Write your code here
        int n = flights.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int K = days[0].length;
        for(int k=0;k<K;k++) {
            int[] newDp = new int[n];
            Arrays.fill(newDp, -1);
            for(int i=0;i<n;i++) {
                if(dp[i]!=-1) {
                    for(int j=0;j<n;j++) {
                        if(j==i||flights[i][j]==1) {
                            newDp[j] = Math.max(newDp[j], dp[i]+days[j][k]);
                        }
                    }
                }
            }
            dp = newDp;
        }
        int ans = 0;
        for(int num : dp) {
            ans = Math.max(ans, num);
        }
        return ans;
    }

}
