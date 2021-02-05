package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 1733. 需要教语言的最少人数
 * 在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。
 * 两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
 * 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
 * 总共有 n 种语言，编号从 1 到 n 。
 * languages[i] 是第 i 位用户掌握的语言集合。
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] 表示 u​​​​​​​​​​​i​​​​​ 和 vi 为好友关系。
 * 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。
 * 请返回你 最少 需要教会多少名用户。
 * 请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，
 * 且 y 和 z 是好友， x 和 z 不一定是好友。
 *
 * 示例 1：
 * 输入：n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * 输出：1
 * 解释：你可以选择教用户 1 第二门语言，也可以选择教用户 2 第一门语言。
 *
 * 示例 2：
 * 输入：n = 3, languages = [[2],[1,3],[1,2],[3]],
 * friendships = [[1,4],[1,2],[3,4],[2,3]]
 * 输出：2
 * 解释：教用户 1 和用户 3 第三门语言，需要教 2 名用户。
 *
 * 提示：
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 * 1 <= languages[i][j] <= n
 * 1 <= u​​​​​​i < v​​​​​​i <= languages.length
 * 1 <= friendships.length <= 500
 * 所有的好友关系 (u​​​​​i, v​​​​​​i) 都是唯一的。
 * languages[i] 中包含的值互不相同。
 */
public class MinimumNumberOfPeopleToTeach {

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        //人数
        int m = languages.length;
        //表示是否遍历过
        boolean[] p = new boolean[m+1];
        //统计每种语言下，可以说该门语言但不能直接和好友交流的人
        Set<Integer>[] set = new Set[n+1];
        for(int i=0;i<n+1;i++)set[i] = new HashSet<>();
        for(int[] friend:friendships){
            int u = friend[0];
            int v = friend[1];
            //使用set判断u和v是否可以通过某种语言进行直接交流，是就跳过
            Set<Integer> tmp = new HashSet<>();
            boolean flag = false;
            for(int k:languages[u-1]){
                tmp.add(k);
            }
            for(int k:languages[v-1]){
                if(tmp.contains(k)){
                    flag = true;
                    break;
                }
            }
            //是，就跳过
            if(flag)continue;
            //对于u和v，如果没被遍历过，则加入对应语言set中
            if(!p[u]){
                p[u] = true;
                for(int k:languages[u-1]){
                    set[k].add(u);
                }
            }
            if(!p[v]){
                p[v] = true;
                for(int k:languages[v-1]){
                    set[k].add(v);
                }
            }
        }
        int ans = m;
        //num，表示不能直接交流的人的总人数
        int num = 0;
        for(int i=1;i<m+1;i++){
            if(p[i])num++;
        }
        //判断每种语言下，需要学习的人数，取最小即答案
        for(int i=1;i<n+1;i++){
            ans = Math.min(ans,num - set[i].size());
        }
        return ans;
    }

}
