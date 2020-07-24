package cn.leetcode.xux.lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 635. 拼字游戏
 * 中文English
 * 给定一个2D矩阵包括 a-z 和字典 dict，找到矩阵上最大的单词集合，这些单词不能在相同的位置重叠。返回最大集合的 大小。
 *
 * 样例
 * 样例 1:
 *
 * 输入:
 * ["abc","def","ghi"]
 * {"abc","defi","gh"}
 * 输出:
 * 3
 *
 * 解释:
 * 我们可以得到最大的集合 `["abc", "defi", "gh"]`
 * 样例 2:
 *
 * 输入:
 * ["aaaa","aaaa","aaaa","aaaa"]
 * {"a"}
 * 输出:
 * 16
 *
 * 解释:
 * 我们可以得到最大的集合 `["a", "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"]`
 * 注意事项
 * 字典中的单词不重复
 * 可以重复使用字典中的单词
 */
public class Lintcode635 {

    /*
     * @param board: a list of lists of character
     * @param words: a list of string
     * @return: an integer
     */
    public int boggleGame(char[][] board, String[] words) {
        // write your code here
        dfs(board, words, new boolean[board.length][board[0].length], 0, 0, 0);
        return ans;
    }

    int ans = 0;

    public void dfs(char[][] board, String[] words, boolean[][] visited, int i, int j, int cnt) {
        ans = Math.max(ans, cnt);
        int m = board.length;
        int n = board[0].length;
        if(i<0||i>=m||j<0||j>=n) {
            return;
        }
        int ni = 0;
        int nj = 0;
        if(j<n-1) {
            ni = i;
            nj = j+1;
        }else {
            ni = i+1;
        }
        if(visited[i][j]) {
            dfs(board, words, visited, ni, nj, cnt);
            return;
        }
        for(String word : words) {
            if(word.charAt(0)==board[i][j]) {
                for(boolean[][] nei : bfs(board, visited, i, j, 0, word)) {
                    dfs(board, words, nei, ni, nj, cnt+1);
                }
            }
        }
        dfs(board, words, visited, ni, nj, cnt);
    }

    public List<boolean[][]> bfs(char[][] board, boolean[][] visited,
                                 int i, int j, int idx, String target) {
        int m = board.length;
        int n = board[0].length;
        List<boolean[][]> ans = new ArrayList<>();
        Queue<boolean[][]> queue1 = new LinkedList<>();
        queue1.offer(visited);
        Queue<int[]> queue2 = new LinkedList<>();
        queue2.offer(new int[]{i, j, idx});
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue1.isEmpty()) {
            boolean[][] cv = queue1.poll();
            int[] ci = queue2.poll();
            int x = ci[0];
            int y = ci[1];
            int k = ci[2];
            for(int[] step : steps) {
                int nx = x+step[0];
                int ny = y+step[1];
                if(nx>=0&&nx<m&&ny>=0&&ny<n&&!cv[nx][ny]
                        &&board[nx][ny]==target.charAt(k)) {
                    boolean[][] nv = new boolean[m][n];
                    for(int a=0;a<m;a++) {
                        for(int b=-0;b<n;b++) {
                            nv[a][b] = cv[a][b];
                        }
                    }
                    nv[nx][ny] = true;
                    if(k+1==target.length()) {
                        ans.add(nv);
                    }else {
                        queue1.offer(nv);
                        queue2.offer(new int[]{nx, ny, k+1});
                    }
                }
            }
        }
        return ans;
    }

}
