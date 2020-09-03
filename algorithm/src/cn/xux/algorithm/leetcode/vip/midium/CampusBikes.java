package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1057. 校园自行车分配（超详细的解法！！！）
 * 在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。
 * 所有工人和自行车的位置都用网格上的 2D 坐标表示。
 * 我们需要为每位工人分配一辆自行车。在所有可用的自行车和工人中，
 * 我们选取彼此之间曼哈顿距离最短的工人自行车对 (worker, bike) ，
 * 并将其中的自行车分配給工人。如果有多个 (worker, bike) 对之间的曼哈顿距离相同，
 * 那么我们选择工人索引最小的那对。类似地，如果有多种不同的分配方法，则选择自行车索引最小的一对。
 * 不断重复这一过程，直到所有工人都分配到自行车为止。
 * 给定两点 p1 和 p2 之间的曼哈顿距离为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。
 * 返回长度为 n 的向量 ans，其中 a[i] 是第 i 位工人分配到的自行车的索引（从 0 开始）。
 *
 * 示例 1：
 * 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * 输出：[1,0]
 * 解释：工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。所以输出是 [1,0]。
 *
 * 示例 2：
 *
 * 输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * 输出：[0,2,1]
 * 解释：
 * 工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，
 * 因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。因此输出为 [0,2,1]。
 *
 * 提示：
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * 所有工人和自行车的位置都不相同。
 * 1 <= workers.length <= bikes.length <= 1000
 */
public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // write your code here
        int m = workers.length;
        int n = bikes.length;
        int[][] pairs = new int[m*n][2];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int idx = n*i+j;
                pairs[idx][0] = i;
                pairs[idx][1] = j;
            }
        }
        Arrays.sort(pairs, (o1,o2)->{
                int len1 = Math.abs(workers[o1[0]][0]-bikes[o1[1]][0])
                        + Math.abs(workers[o1[0]][1]-bikes[o1[1]][1]);
                int len2 = Math.abs(workers[o2[0]][0]-bikes[o2[1]][0])
                        + Math.abs(workers[o2[0]][1]-bikes[o2[1]][1]);
                if(len1!=len2) {
                    return len1-len2;
                }
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
        });
        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        boolean[] bikeVisited = new boolean[bikes.length];
        for(int i=0;i<pairs.length;i++) {
            int[] curr = pairs[i];
            if(ans[curr[0]]==-1&&!bikeVisited[curr[1]]) {
                ans[curr[0]] = curr[1];
                bikeVisited[curr[1]] = true;
            }
        }
        return ans;
    }

    // O(mn) TLE why?
    public int[] assignBikes1(int[][] workers, int[][] bikes) {
        // write your code here
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (o1,o2)->{
                    int len1 = Math.abs(workers[o1[0]][0]-bikes[o1[1]][0])
                            + Math.abs(workers[o1[0]][1]-bikes[o1[1]][1]);
                    int len2 = Math.abs(workers[o2[0]][0]-bikes[o2[1]][0])
                            + Math.abs(workers[o2[0]][1]-bikes[o2[1]][1]);
                    if(len1!=len2) {
                        return len1-len2;
                    }
                    return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
                });
        for(int w=0;w<workers.length;w++) {
            for(int b=0;b<bikes.length;b++) {
                queue.offer(new int[]{w,b});
            }
        }
        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        boolean[] bikeVisited = new boolean[bikes.length];
        while(queue.size()>0) {
            int[] curr = queue.poll();
            if(ans[curr[0]]==-1&&!bikeVisited[curr[1]]) {
                ans[curr[0]] = curr[1];
                bikeVisited[curr[1]] = true;
            }
        }
        return ans;
    }

}
