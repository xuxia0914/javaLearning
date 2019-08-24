package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一个图是否是二分图
 * Given a graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent ubsets.
 * Note:
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 */
public class IsGraphBipartite {

    public static boolean solution(int[][] graph) {
        int length = graph.length;
        int[] colors = new int[length];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<length;i++) {
            if(colors[i]==0) {
                queue.offer(i);
                while(!queue.isEmpty()) {
                    int nums = queue.poll();
                    if(colors[nums]==0) {
                        colors[nums] = 1;
                        int[] neighbors = graph[nums];
                        for(int neighbor : neighbors) {
                            if(colors[neighbor]==0) {
                                queue.offer(neighbor);
                                colors[neighbor]=-1;
                            }else if(colors[neighbor]==-1) {
                                continue;
                            }else {
                                return false;
                            }
                        }
                    }else {
                        int[] neighbors = graph[nums];
                        for(int neighbor : neighbors) {
                            if(colors[neighbor]==0) {
                                queue.offer(neighbor);
                                colors[neighbor]=-1*colors[nums];
                            }else if(colors[neighbor]==-1*colors[nums]) {
                                continue;
                            }else {
                                return false;
                            }
                        }
                    }

                }
            }else {
                continue;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1,3}, {0,2}, {1,3}, {0,2}}));
        System.out.println(solution(new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}}));
    }

}
