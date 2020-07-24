package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 804. 不同岛屿的数量II
 * 中文English
 * 给定一个0和1的非空的二维数组网格，一个岛是一个1(表示陆地)的组，
 * 4个方向(水平或垂直)连接。你可以假设网格的所有四条边都被水包围。
 * 计算不同岛屿的数量。当一个岛被认为与另一个岛相同时，
 * 它们有相同的形状，或在旋转后的形状相同(90,180，或270度)或翻转(左/右方向或向上/向下方向)。
 *
 * 样例
 * Example 1:
 *
 * Input: [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
 * Output: 1
 * Explanation:
 * The island is look like this:
 * 11000
 * 10000
 * 00001
 * 00011
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
 * Example 2:
 *
 * Input: [[1,1,1,0,0],[1,0,0,0,1],[0,1,0,0,1],[0,1,1,1,0]]
 * Output: 2
 * Explanation:
 * The island is look like this:
 * 11100
 * 10001
 * 01001
 * 01110
 *
 * Here are the two distinct islands:
 * 111
 * 1
 * and
 * 1
 * 1
 *
 * Notice that:
 * 111
 * 1
 * and
 * 1
 * 111
 * are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
 * 注意事项
 * 每个维度在给定网格的长度不超过50。
 */
public class Lintcode804 {

    /**
     * @param grid: the 2D grid
     * @return: the number of distinct islands
     */
    public int numDistinctIslands2(int[][] grid) {
        // write your code here
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    List<int[]> curr = new ArrayList<>();
                    dfs(grid, curr, i, j);
                    if(addToSet(curr)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, List<int[]> curr, int i, int j) {
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
            return;
        }
        curr.add(new int[]{i, j});
        grid[i][j] = 0;
        dfs(grid, curr, i-1, j);
        dfs(grid, curr, i+1, j);
        dfs(grid, curr, i, j-1);
        dfs(grid, curr, i, j+1);
    }

    Set<String> set = new HashSet<>();

    private boolean addToSet(List<int[]> list) {
        int offsetX = list.get(0)[0];
        int offsetY = list.get(0)[1];
        for(int[] pos : list) {
            offsetX = Math.min(offsetX, pos[0]);
            offsetY = Math.min(offsetY, pos[1]);
        }
        for(int[] pos : list) {
            pos[0] -= offsetX;
            pos[1] -= offsetY;
        }
        Collections.sort(list, (o1,o2)->o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1]);
        if(!set.add(arrToString(list))) {
            return false;
        }
        List<int[]>[] lists = new List[7];
        for(int i=0;i<7;i++) {
            lists[i] = new ArrayList<>();
        }
        for(int i=0;i<list.size();i++) {
            int x = list.get(i)[0];
            int y = list.get(i)[1];
            lists[0].add(new int[]{x, -y});
            lists[1].add(new int[]{-x, y});
            lists[2].add(new int[]{-x, -y});
            lists[3].add(new int[]{y, x});
            lists[4].add(new int[]{y, -x});
            lists[5].add(new int[]{-y, x});
            lists[6].add(new int[]{-y, -x});
        }
        for(int i=0;i<7;i++) {
            offsetX = lists[i].get(0)[0];
            offsetY = lists[i].get(0)[1];
            for(int[] pos : lists[i]) {
                offsetX = Math.min(offsetX, pos[0]);
                offsetY = Math.min(offsetY, pos[1]);
            }
            for(int[] pos : lists[i]) {
                pos[0] -= offsetX;
                pos[1] -= offsetY;
            }
            Collections.sort(lists[i], (o1,o2)->o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1]);
            set.add(arrToString(lists[i]));
        }
        return true;
    }

    private String arrToString(List<int[]> list) {
        StringBuilder sb = new StringBuilder();
        for(int[] a : list) {
            sb.append(a[0]*50+a[1]);
            sb.append("#");
        }
        return sb.toString();
    }

}
