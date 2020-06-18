package cn.leetcode.xux.general.hard;

import java.util.*;

/**
 * 354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class RussianDollEnvelopes {

    //寻找最长递增子序列
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes==null||envelopes.length==0) {
            return 0;
        }
        //envelopes[i][0]升序，envelopes[i][1]降序排列
        Arrays.sort(envelopes, (o1,o2)->o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0]);
        int n = envelopes.length;
        int[] nums = new int[n];
        for(int i=0;i<n;i++) {
            nums[i] = envelopes[i][1];
        }
        int tail = 0;
        for(int i=1;i<n;i++) {
            if(nums[i]>nums[tail]) {
                nums[++tail] = nums[i];
            }else {
                int idx = Arrays.binarySearch(nums, 0, tail, nums[i]);
                if(idx<0) {
                    idx = -idx-1;
                    nums[idx] = nums[i];
                }
            }
        }
        return tail+1;
    }

    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

    //TLE
    public int maxEnvelopes1(int[][] envelopes) {
        if(envelopes==null||envelopes.length==0) {
            return 0;
        }
        for(int[] envelope : envelopes) {
            if(!map.containsKey(envelope[0])) {
                map.put(envelope[0], new TreeSet<>());
            }
            map.get(envelope[0]).add(envelope[1]);
        }
        return dfs(new int[]{1, 1});
    }

    Map<String, Integer> mem = new HashMap<>();

    private int dfs(int[] curr) {
        String key = curr[0]+"#"+curr[1];
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        Map.Entry<Integer, TreeSet<Integer>> entry = map.ceilingEntry(curr[0]);
        if(entry==null) {
            return 0;
        }
        int ans = 0;
        TreeSet<Integer> set = entry.getValue();
        Integer h = set.ceiling(curr[1]);
        if(h!=null) {
            ans = 1+dfs(new int[]{entry.getKey()+1, h+1});
        }
        ans = Math.max(ans, dfs(new int[]{entry.getKey()+1, curr[1]}));
        mem.put(key, ans);
        return ans;
    }

}
