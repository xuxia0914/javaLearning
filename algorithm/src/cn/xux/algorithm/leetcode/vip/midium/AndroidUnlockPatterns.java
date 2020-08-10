package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 351. 安卓系统手势解锁
 * 我们都知道安卓有个手势解锁的界面，是一个 3 x 3 的点所绘制出来的网格。
 * 给你两个整数，分别为 m 和 n，其中 1 ≤ m ≤ n ≤ 9，
 * 那么请你统计一下有多少种解锁手势，是至少需要经过 m 个点，但是最多经过不超过 n 个点的。
 * 先来了解下什么是一个有效的安卓解锁手势:
 * 每一个解锁手势必须至少经过 m 个点、最多经过 n 个点。
 * 解锁手势里不能设置经过重复的点。
 * 假如手势中有两个点是顺序经过的，那么这两个点的手势轨迹之间是绝对不能跨过任何未被经过的点。
 * 经过点的顺序不同则表示为不同的解锁手势。
 *
 * 解释:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * 无效手势：4 - 1 - 3 - 6 连接点 1 和点 3 时经过了未被连接过的 2 号点。
 * 无效手势：4 - 1 - 9 - 2 连接点 1 和点 9 时经过了未被连接过的 5 号点。
 * 有效手势：2 - 4 - 1 - 3 - 6 连接点 1 和点 3 是有效的，
 * 因为虽然它经过了点 2 ，但是点 2 在该手势中之前已经被连过了。
 * 有效手势：6 - 5 - 4 - 1 - 9 - 2 连接点 1 和点 9 是有效的，
 * 因为虽然它经过了按键 5 ，但是点 5 在该手势中之前已经被连过了。
 */
public class AndroidUnlockPatterns {

    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;
        boolean[] visited = new boolean[10];
        dfs(1, visited, skip, 1, m, n, 1);
        dfs(2, visited, skip, 1, m, n, 2);
        dfs(5, visited, skip, 1, m, n, 5);
        return ans;
    }

    int ans = 0;

    public void dfs(int currentNumber, boolean[] visited,
                     int[][] skip,int count, int m, int n, int start) {
        if (count>n) {
            return;
        }
        if(count>=m) {
            ans += start==5?1:4;
        }
        visited[currentNumber] = true;
        for (int i=1;i<=9;i++) {
            int crossThroughNumber = skip[currentNumber][i];
            if (!visited[i]&&(crossThroughNumber==0||visited[crossThroughNumber])) {
                dfs (i, visited, skip, count+1, m, n, start);
            }
        }
        visited[currentNumber] = false;
    }

}
