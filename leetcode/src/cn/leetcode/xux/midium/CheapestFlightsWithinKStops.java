package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。
 * 如果没有这样的路线，则输出 -1。
 *
 * 示例 1:
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 *
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 *
 * 示例 2:
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 *
 * 提示：
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1.
 * 航班数量范围是 [0, n * (n - 1) / 2].
 * 每个航班的格式 (src, dst, price).
 * 每个航班的价格范围是 [1, 10000].
 * k 范围是 [0, n - 1].
 * 航班没有重复，且不存在环路
 */
public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(src==dst) {
            return 0;
        }
        if(flights==null||flights.length==0) {
            return -1;
        }
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] arr : flights) {
            if(!map.containsKey(arr[0])) {
                map.put(arr[0], new LinkedList<>());
            }
            map.get(arr[0]).add(new int[]{arr[1], arr[2]});
        }
        int[] expends = new int[n];
        Arrays.fill(expends, -1);
        expends[src] = 0;
        int result = -1;
        while(K-->=0) {
            int[] newExpends = new int[n];
            Arrays.fill(newExpends, -1);
            for(int i=0;i<n;i++) {
                if(expends[i]>-1&&map.containsKey(i)) {
                    for(int[] arr : map.get(i)) {
                        if(newExpends[arr[0]]==-1||newExpends[arr[0]]>expends[i]+arr[1]) {
                            newExpends[arr[0]] = expends[i]+arr[1];
                            if(arr[0]==dst) {
                                result = result==-1?newExpends[dst]:Math.min(result, newExpends[dst]);
                            }
                        }
                    }
                }
            }
            expends = newExpends;
        }
        return result;
    }

}