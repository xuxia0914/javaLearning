package cn.xux.algorithm.leetcode.general.hard;

/**
 * 135. 分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class Candy {

    //n
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] lr = new int[n];
        lr[0] = 1;
        for(int i=1;i<n;i++) {
            lr[i] = ratings[i]>ratings[i-1]?lr[i-1]+1:1;
        }
        int[] rl = new int[n];
        rl[n-1] = 1;
        for(int i=n-2;i>=0;i--) {
            rl[i] = ratings[i]>ratings[i+1]?rl[i+1]+1:1;
        }
        int ans = 0;
        for(int i=0;i<n;i++) {
            ans += Math.max(lr[i], rl[i]);
        }
        return ans;
    }

    //n2
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] tmp = new int[n+2];
        tmp[0] = Integer.MAX_VALUE;
        System.arraycopy(ratings, 0, tmp, 1, n);
        tmp[n+1] = Integer.MAX_VALUE;
        ratings = tmp;
        n += 2;
        int[] nums = new int[n];
        int cnt = 0;
        int idx = 1;
        while(cnt<n-2) {
            if(nums[idx]==0) {
                if((ratings[idx]>ratings[idx-1]&&nums[idx-1]==0)
                        ||(ratings[idx]>ratings[idx+1]&&nums[idx+1]==0)) {
                    idx = idx==n-2?1:idx+1;
                    continue;
                }else {
                    boolean biggerThanLeft = ratings[idx]>ratings[idx-1];
                    boolean biggerThanRight = ratings[idx]>ratings[idx+1];
                    if(biggerThanLeft&&biggerThanRight) {
                        nums[idx] = Math.max(nums[idx-1], nums[idx+1])+1;
                    }else if(biggerThanLeft) {
                        nums[idx] = nums[idx-1]+1;
                    }else if(biggerThanRight) {
                        nums[idx] = nums[idx+1]+1;
                    }else {
                        nums[idx] = 1;
                    }
                    cnt++;
                }
            }
            idx = idx==n-2?1:idx+1;
        }
        int ans = 0;
        for(int i=1;i<n-1;i++) {
            ans += nums[i];
        }
        return ans;
    }

}
