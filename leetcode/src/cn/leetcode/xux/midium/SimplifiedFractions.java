package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1447. 最简分数
 * 给你一个整数n，请你返回所有0到1之间（不包括0和1）满足分母小于等于n的最简分数。
 * 分数可以以 任意 顺序返回。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 *
 * 示例 3：
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 *
 * 示例 4：
 * 输入：n = 1
 * 输出：[]
 *
 * 提示：
 * 1 <= n <= 100
 */
public class SimplifiedFractions {

    public List<String> simplifiedFractions(int n) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (o1,o2)->o1[0]*o2[1]!=o1[1]*o2[0]?o1[0]*o2[1]-o1[1]*o2[0]:o1[0]-o2[0]);
        for(int i=2;i<=n;i++) {
            queue.offer(new int[]{1, i});
        }
        List<String> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            int[] curr = queue.peek();
            ans.add(curr[0]+"/"+curr[1]);
            while(!queue.isEmpty()&&queue.peek()[0]*curr[1]==queue.peek()[1]*curr[0]) {
                curr = queue.poll();
                if(curr[0]+1<curr[1]) {
                    queue.offer(new int[]{curr[0]+1, curr[1]});
                }
            }
        }
        return ans;
    }

    public List<String> simplifiedFractions1(int n) {
        Set<String> ans = new HashSet<>();
        for(int i=1;i<n;i++) {
            for(int j=i+1;j<=n;j++) {
                int g = gcd(j, i);
                ans.add((i/g)+"/"+(j/g));
            }
        }
        return new ArrayList<>(ans);
    }

    private int gcd(int m, int n){
        while(m%n!=0) {
            int tmp = m%n;
            m = n;
            n = tmp;
        }
        return n;
    }

}
