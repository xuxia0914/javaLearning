package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a class RecentCounter to count recent requests.
 * It has only one method: ping(int t), where t represents some time in milliseconds.
 * Return the number of pings that have been made from 3000 milliseconds ago until now.
 * Any ping with time in [t - 3000, t] will count, including the current ping.
 * It is guaranteed that every call to ping uses a strictly larger value of t than before.
 * Example 1:
 * Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
 * Output: [null,1,2,3,3]
 * Note:
 * Each test case will have at most 10000 calls to ping.
 * Each test case will call ping with strictly increasing values of t.
 * Each call to ping will have 1 <= t <= 10^9.
 */
public class NumberOfRecentCalls {

    List<Integer> records = new ArrayList<Integer>();

    public NumberOfRecentCalls() {

    }

    public int ping(int t) {
        while(records.size()>0) {
            if(t-records.get(0)>3000) {
                records.remove(0);
            }else {
                break;
            }
        }
        records.add(t);
        return records.size();
    }

    public static void main(String[] args) {
        NumberOfRecentCalls norc = new NumberOfRecentCalls();
        int[] array = {642,1849,4921,5936,5957};
        for(int i : array) {
            System.out.println(norc.ping(i));
        }
    }

}
