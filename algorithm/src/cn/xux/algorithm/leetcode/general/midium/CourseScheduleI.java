package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 207. 课程表
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 判断有向图是否有环
 */
public class CourseScheduleI {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses<2) {
            return true;
        }
        int[] inDegrees = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
            if(!map.containsKey(prerequisite[1])) {
                map.put(prerequisite[1], new ArrayList<>());
            }
            map.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int finishedCnt=0;
        for(int i=0;i<numCourses;i++) {
            if(inDegrees[i]==0) {
                queue.offer(i);
                if(++finishedCnt==numCourses) {
                    return true;
                }
            }
        }
        int curr;
        while(!queue.isEmpty()) {
            curr = queue.poll();
            if(map.containsKey(curr)) {
                for(int course : map.get(curr)) {
                    if(--inDegrees[course]==0) {
                        queue.offer(course);
                        if(++finishedCnt==numCourses) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CourseScheduleI cs = new CourseScheduleI();
        System.out.println(cs.canFinish(2, new int[][]{{0,1}}));
        System.out.println(cs.canFinish(2, new int[][]{{0,1}, {1,0}}));
    }

}