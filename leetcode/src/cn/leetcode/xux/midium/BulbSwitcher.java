package cn.leetcode.xux.midium;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round,
 * you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb.
 * Find how many bulbs are on after n rounds.
 * Example:
 * Input: 3
 * Output: 1
 * Explanation:
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 * So you should return 1, because there is only one bulb is on.
 */
public class BulbSwitcher {
    /**
     * TLE
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
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

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Bulb Switcher.
     * Memory Usage: 32.8 MB, less than 5.18% of Java online submissions for Bulb Switcher.
     * @param n
     * @return
     */
    public int bulbSwitch2(int n) {
        int res = 1;
        while(res*res<=n) {
            res++;
        }
        return res-1;
    }

}
