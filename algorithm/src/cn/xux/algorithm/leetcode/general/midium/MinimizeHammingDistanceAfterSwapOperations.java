package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1722. 执行交换操作后的最小汉明距离
 * 给你两个整数数组 source 和 target ，长度都是 n 。
 * 还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] = [ai, bi]
 * 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。
 * 注意，你可以按 任意 顺序 多次 交换一对特定下标指向的元素。
 * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。
 * 形式上，其值等于满足 source[i] != target[i] （下标从 0 开始）的下标i（0 <= i <= n-1）的数量。
 * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
 *
 * 示例 1：
 * 输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * 输出：1
 * 解释：source 可以按下述方式转换：
 * - 交换下标 0 和 1 指向的元素：source = [2,1,3,4]
 * - 交换下标 2 和 3 指向的元素：source = [2,1,4,3]
 * source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
 *
 * 示例 2：
 * 输入：source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * 输出：2
 * 解释：不能对 source 执行交换操作。
 * source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。
 *
 * 示例 3：
 * 输入：source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
 * 输出：0
 *
 * 提示：
 * n == source.length == target.length
 * 1 <= n <= 105
 * 1 <= source[i], target[i] <= 105
 * 0 <= allowedSwaps.length <= 105
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 */
public class MinimizeHammingDistanceAfterSwapOperations {

    //[1,2,3,4]
    //[2,1,4,5]
    //[[0,1],[2,3]]
    public static void main(String[] args) {
        System.out.println(new MinimizeHammingDistanceAfterSwapOperations()
                .minimumHammingDistance(new int[]{1,2,3,4}, new int[]{2,1,4,5}, new int[][]{{0,1},{2,3}}));
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);
        for(int[] as : allowedSwaps) {
            dsu.union(as[0], as[1]);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            int root = dsu.find(i);
            if(!map.containsKey(root)) {
                map.put(root, new ArrayList<>());
            }
            map.get(root).add(i);
        }
        int ans = 0;
        for(List<Integer> list : map.values()) {
            Map<Integer, Integer> cnts = new HashMap<>();
            for(int i : list) {
                cnts.put(source[i], cnts.getOrDefault(source[i], 0)+1);
            }
            for(int i : list) {
                if(cnts.getOrDefault(target[i], 0)==0) {
                    ans++;
                }else {
                    cnts.put(target[i], cnts.get(target[i])-1);
                }
            }
        }
        return ans;
    }

    class DSU {

        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for(int i=1;i<n;i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(parent[x]!=x) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public void union(int x, int y) {
            parent[find(y)] = find(x);
        }

    }

}
