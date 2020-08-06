package cn.xux.algorithm.leetcode.general.midium;

/**
 * 474. 一和零
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 * 注意:
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 *
 * 示例 1:
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 *
 * 示例 2:
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 */
public class OnesAndZeroes {

    public int findMaxForm1(String[] strs, int m, int n) {
        if(strs.length==0)
            return 0;
        //[0:i-1]的字符串物品中，j个0，k个1最多能够构成字符串数量。字符串为物品，0,1数量为背包限制。
        //dp[i][j]=max(dp[i][j],dp[i-0数量][j-1数量]+1)
        int[][] dp=new int[m+1][n+1];
        for(String str: strs){
            int zeros=0, ones=0;
            //统计该字符串的0,1数量
            for(int i=0; i<str.length(); i++){
                char c=str.charAt(i);
                if( c=='0')
                    zeros++;
                else
                    ones++;
            }
            for(int j=m; j>=zeros; j--)
                for(int k=n; k>=ones; k--)
                    dp[j][k]=Math.max(dp[j][k],1+dp[j-zeros][k-ones]);
        }
        return dp[m][n];
    }

    int res = 0;

    /**
     * TLE
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] grid = new int[len][2];
        for(int i=0;i<len;i++) {
            String str = strs[i];
            int a = 0;
            int b = 0;
            for(int j=0;j<str.length();j++) {
                if(str.charAt(j)=='0') {
                    a++;
                }else {
                    b++;
                }
            }
            grid[i] = new int[]{a, b};
        }
        helper(grid, m, n, 0, 0);
        return res;
    }

    public void helper(int[][] grid, int m, int n, int curr, int cnt) {
        if(curr==grid.length) {
            res = Math.max(res, cnt);
            return;
        }
        helper(grid, m, n, curr+1, cnt);
        if(m>=grid[curr][0]&&n>=grid[curr][1]) {
            helper(grid, m-grid[curr][0], n-grid[curr][1], curr+1, cnt+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new OnesAndZeroes().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

}
