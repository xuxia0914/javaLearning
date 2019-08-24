package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 261. 以图判树
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        Set<Integer> set = new HashSet<>();
        for(int[] edge : edges) {
            if(edge[0]==edge[1]) {
                return false;
            }
            set.add(edge[0]);
            set.add(edge[1]);
        }
        if(set.size()-1==edges.length) {
            return true;
        }else {
            return false;
        }
    }

}
