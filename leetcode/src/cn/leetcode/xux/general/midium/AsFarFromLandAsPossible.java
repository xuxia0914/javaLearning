package cn.leetcode.xux.general.midium;

import java.util.Arrays;

/**
 * 5053. 地图分析
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 * 示例 1：
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 *
 * 示例 2：
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 *
 * 提示：
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 */
public class AsFarFromLandAsPossible {

    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean hasOne = false;
        boolean hasZero = false;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    hasZero = true;
                }else {
                    hasOne = true;
                }
                if(hasZero&&hasOne) {
                    break;
                }
            }
        }
        if(!hasOne||!hasZero) {
            return -1;
        }

        int[][] dp1 = new int[m][n];
        for(int[] tmp : dp1) {
            Arrays.fill(tmp, Integer.MAX_VALUE);
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    if(i>0) {
                        if(grid[i-1][j]==1) {
                            dp1[i][j] = 1;
                        }else if(dp1[i-1][j]!=Integer.MAX_VALUE) {
                            dp1[i][j] = Math.min(dp1[i][j], dp1[i-1][j]+1);
                        }
                    }
                    if(j>0) {
                        if(grid[i][j-1]==1) {
                            dp1[i][j] = 1;
                        }else if(dp1[i][j-1]!=Integer.MAX_VALUE) {
                            dp1[i][j] = Math.min(dp1[i][j], dp1[i][j-1]+1);
                        }
                    }
                }else {
                    dp1[i][j] = 0;
                }
            }
        }

        int[][] dp2 = new int[m][n];
        for(int[] tmp : dp2) {
            Arrays.fill(tmp, Integer.MAX_VALUE);
        }
        for(int i=0;i<m;i++) {
            for(int j=n-1;j>=0;j--) {
                if(i>0) {
                    if(grid[i-1][j]==1) {
                        dp2[i][j] = 1;
                    }else if(dp2[i-1][j]!=Integer.MAX_VALUE) {
                        dp2[i][j] = Math.min(dp2[i][j], dp2[i-1][j]+1);
                    }
                }
                if(j<n-1) {
                    if(grid[i][j+1]==1) {
                        dp2[i][j] = 1;
                    }else if(dp2[i][j+1]!=Integer.MAX_VALUE) {
                        dp2[i][j] = Math.min(dp2[i][j], dp2[i][j+1]+1);
                    }
                }
            }
        }

        int[][] dp3 = new int[m][n];
        for(int[] tmp : dp3) {
            Arrays.fill(tmp, Integer.MAX_VALUE);
        }
        for(int i=m-1;i>=0;i--) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    if(i<m-1) {
                        if(grid[i+1][j]==1) {
                            dp3[i][j] = 1;
                        }else if(dp3[i+1][j]!=Integer.MAX_VALUE) {
                            dp3[i][j] = Math.min(dp3[i][j], dp3[i+1][j]+1);
                        }
                    }
                    if(j>0) {
                        if(grid[i][j-1]==1) {
                            dp3[i][j] = 1;
                        }else if(dp3[i][j-1]!=Integer.MAX_VALUE) {
                            dp3[i][j] = Math.min(dp3[i][j], dp3[i][j-1]+1);
                        }
                    }
                }
            }
        }

        int[][] dp4 = new int[m][n];
        for(int[] tmp : dp4) {
            Arrays.fill(tmp, Integer.MAX_VALUE);
        }
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(i<m-1) {
                    if(grid[i+1][j]==1) {
                        dp4[i][j] = 1;
                    }else if(dp4[i+1][j]!=Integer.MAX_VALUE) {
                        dp4[i][j] = Math.min(dp4[i][j], dp4[i+1][j]+1);
                    }
                }
                if(j<n-1) {
                    if(grid[i][j+1]==1) {
                        dp4[i][j] = 1;
                    }else if(dp4[i][j+1]!=Integer.MAX_VALUE) {
                        dp4[i][j] = Math.min(dp4[i][j], dp4[i][j+1]+1);
                    }
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, Math.min(Math.min(dp1[i][j], dp2[i][j]), Math.min(dp3[i][j], dp4[i][j])));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        new AsFarFromLandAsPossible().maxDistance(new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,0}
        });
    }

}
