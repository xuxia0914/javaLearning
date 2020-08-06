package cn.xux.algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1749. 金字塔
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 给一个金字型塔的数列，第一行一个0，第二行两个1……第六行六个5，
 * 现在金字塔数字打乱了，你只能移动0这个数字，
 * 只能向左上右上左下右下走，问在20步内能否回到原来状态。
 *
 * 样例
 * 样例 1:
 * 输入：[[1],[2,0],[2,1,2],[3,3,3,3],[4,4,4,4,4],[5,5,5,5,5,5]]
 * 输出：3
 * 解释:
 * 一开始，移动到(2,1)
 * 然后,移动到(1,0)
 * 最后,移动到(0,0)
 *
 * 样例 2:
 * 输入：[[1],[0,1],[2,2,2],[3,3,3,3],[4,4,4,4,4],[5,5,5,5,5,5]]
 * 输出：1
 * 解释: 直接移动到(0,0)
 *
 * 注意事项
 * 保证输入合法
 * 如果20步内不能回到原状态，就返回-1
 */
public class Lintcode1749 {

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
    }

    /**
     * @param a: the number admiral
     * @return: the number of steps to return to the original state
     */
    public int getSteps(int[][] a) {
        // Write your code here
        Set<Long> visited = new HashSet<>();
        //二位数组展开为一维数组，最后一个数字表示0在数组中的索引
        int[] startArray = expand(a);
        //把当前数组映射为long型的状态(前15个数字可以决定一个唯一状态)，便于记忆和比较
        long startLong = arrayToLong(startArray);
        long targetLong = 11222333344444L;
        if(targetLong==startLong) {
            return 0;
        }
        //存储当前数组状态
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<21;i++) {
            if(startArray[i]==0) {
                startArray[21] = i;
                break;
            }
        }
        queue.offer(startArray);
        //记录已经访问过的状态，避免重复计算
        visited.add(startLong);
        //步数
        int level = 1;
        //记忆化BFS
        while(level<=20&&!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] currArray = queue.poll();
                int currIdx = currArray[21];
                //遍历下一个可能的状态
                for(int nextIdx : mapping[currIdx]) {
                    int[] nextArray = currArray.clone();
                    nextArray[currIdx] = currArray[nextIdx];
                    nextArray[nextIdx] = currArray[currIdx];
                    nextArray[21] = nextIdx;
                    long nextLong = arrayToLong(nextArray);
                    if(nextLong==targetLong) {
                        return level;
                    }else if(visited.add(nextLong)) {
                        queue.offer(nextArray);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private long arrayToLong(int[] a) {
        long ans = 0;
        for(int i=0;i<=14;i++) {
            ans = ans*10+a[i];
        }
        return ans;
    }

    private int[] expand(int[][] a) {
        int[] ans = new int[22];
        int idx = 0;
        for(int[] b : a) {
            for(int c : b) {
                ans[idx++] = c;
            }
        }
        return ans;
    }

    //mapping[i]表示数组的索引i的数为0时，可以与其替换的数的所有索引
    int[][] mapping = new int[][]{
            {1,2},
            {0,3,4},
            {0,4,5},
            {1,6,7},
            {1,2,7,8},
            {2,8,9},
            {3,10,11},
            {3,4,11,12},
            {4,5,12,13},
            {5,13,14},
            {6,15,16},
            {6,7,16,17},
            {7,8,17,18},
            {8,9,18,19},
            {9,19,20},
            {10},
            {10,11},
            {11,12},
            {12,13},
            {13,14},
            {14}
    };

}
