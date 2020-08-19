package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 582. 终止进程
 * 这个问题中. 每个进程都有一个唯一的 PID(进程id) 和 PPID(父进程id)。
 * 每个进程只有一个父进程，但可能有一个或多个子进程，这就像一个树形结构。
 * 并且只有一个进程的PPID是0，这意味着这个进程没有父进程。所有的pid都是不同的正整数。
 * 我们使用两个整数列表来表示进程列表，其中第一个列表包含每个进程的PID，第二个列表包含对应的PPID。
 * 现在给定这两个列表，以及一个你要终止(kill)的进程的ID，返回将在最后被终止的进程的PID列表。
 * （当一个进程被终止时，它的所有子进程将被终止。返回的列表没有顺序要求）
 *
 * 样例 1:
 * 输入: PID = [1, 3, 10, 5], PPID = [3, 0, 5, 3], killID = 5
 * 输出: [5, 10]
 * 解释: 终止进程5同样会终止进程10.
 *      3
 *    /   \
 *   1     5
 *        /
 *       10
 *
 * 样例 2:
 * 输入: PID = [1, 2, 3], PPID = [0, 1, 1], killID = 2
 * 输出: [2]
 * 注意事项
 * 给定的kill id一定是PID列表中的某个id
 * 给定的PID列表中至少含有一个进程
 */
public class KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i=0;i<pid.size();i++) {
            if(!map.containsKey(ppid.get(i))) {
                map.put(ppid.get(i), new HashSet<>());
            }
            map.get(ppid.get(i)).add(pid.get(i));
        }
        Set<Integer> killed = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        killed.add(kill);
        queue.offer(kill);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(map.containsKey(curr)) {
                for(int child : map.get(curr)) {
                    if(killed.add(child)) {
                        queue.offer(child);
                    }
                }
            }
        }
        return new ArrayList(killed);
    }

    public static void main(String[] args) {
        KillProcess kp = new KillProcess();
        List<Integer> pid = new ArrayList<>();
        pid.add(1);
        pid.add(3);
        pid.add(10);
        pid.add(5);
        List<Integer> ppid = new ArrayList<>();
        ppid.add(3);
        ppid.add(0);
        ppid.add(5);
        ppid.add(3);
        System.out.println(kp.killProcess(pid, ppid, 5));
    }

}
