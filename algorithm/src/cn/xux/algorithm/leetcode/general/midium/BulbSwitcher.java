package cn.xux.algorithm.leetcode.general.midium;

/**
 * 319. 灯泡开关
 * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。
 *
 * 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
 *
 * 找出并返回 n 轮后有多少个亮着的灯泡。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 3
 * 输出：1
 * 解释：
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= n <= 109
 */
public class BulbSwitcher {

    // 编号的约数的个数是奇数的灯泡最后是亮的，
    // 而约数个数是奇数意味着这个数是某个数的平方，
    // 即求最大平方根
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    //TLE
    public int bulbSwitch1(int n) {
        boolean[] status  = new boolean[n];
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j=j+i+1) {
                status[j] = !status[j];
            }
        }
        int cnt = 0;
        for(boolean b : status) {
            if(b) {
                cnt++;
            }
        }
        return cnt;
    }

}
