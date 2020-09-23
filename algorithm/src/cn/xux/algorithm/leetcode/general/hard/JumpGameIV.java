package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 1345. 跳跃游戏 IV
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标 i 跳到下标：
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 *
 * 示例 1：
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 *
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 *
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 *
 * 示例 4：
 * 输入：arr = [6,1,9]
 * 输出：2
 *
 * 示例 5：
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 *
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 */
public class JumpGameIV {

    public int minJumps(int[] arr) {
        if(arr.length==1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int curr = queue.poll();
                // 通过枚举同值进入队列的索引取负之后再进入队列
                // 避免同一个同值的索引列表(设size=n)被执行n次
                // 不然时间复杂度仍为O(n^2)
                if(curr>=0) {
                    for(int next : map.get(arr[curr])) {
                        if(next==arr.length-1) {
                            return step+1;
                        }else if(visited.add(next)) {
                            queue.offer(-next);
                        }
                    }
                }else {
                    curr = -curr;
                }
                if(curr>0&&visited.add(curr-1)) {
                    queue.offer(curr-1);
                }
                if(curr<arr.length-1) {
                    if(curr+1==arr.length-1) {
                        return step+1;
                    }else if(visited.add(curr+1)) {
                        queue.offer(curr+1);
                    }
                }
            }
            step++;
        }
        return -1;
    }

}
