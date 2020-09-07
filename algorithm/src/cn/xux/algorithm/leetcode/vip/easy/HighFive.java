package cn.xux.algorithm.leetcode.vip.easy;

import java.util.*;

/**
 * 1086. 前五科的均分（C++）
 * 给你一个不同学生的分数列表，请按 学生的 id 顺序 返回每个学生 最高的五科 成绩的 平均分。
 * 对于每条 items[i] 记录， items[i][0] 为学生的 id，items[i][1] 为学生的分数。
 * 平均分请采用整数除法计算。
 *
 * 示例：
 * 输入：[[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * 输出：[[1,87],[2,88]]
 * 解释：
 * id = 1 的学生平均分为 87。
 * id = 2 的学生平均分为 88.6。但由于整数除法的缘故，平均分会被转换为 88。
 *
 * 提示：
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * 学生的 ID 在 1 到 1000 之间
 * 学生的分数在 1 到 100 之间
 * 每个学生至少有五个分数
 */
public class HighFive {

    public int[][] highFive(int[][] items) {
        if(items==null||items.length==0) {
            return null;
        }
        Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        for(int[] item : items) {
            if(!map.containsKey(item[0])) {
                map.put(item[0], new PriorityQueue<>());
            }
            PriorityQueue<Integer> queue = map.get(item[0]);
            queue.offer(item[1]);
            if(queue.size()>5) {
                queue.poll();
            }
        }
        int[][] ans = new int[map.size()][2];
        int idx = 0;
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            PriorityQueue<Integer> val = entry.getValue();
            int sum = 0;
            while(val.size()>0) {
                sum += val.poll();
            }
            ans[idx][0] = key;
            ans[idx][1] = sum/5;
            idx++;
        }
        return ans;
    }

}
