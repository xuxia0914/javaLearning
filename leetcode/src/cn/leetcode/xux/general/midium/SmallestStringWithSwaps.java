package cn.leetcode.xux.general.midium;

import java.util.*;

/**
 * 1202. 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，
 * 其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 *
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 */
public class SmallestStringWithSwaps {

    public static void main(String[] args) {
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        pairs.add(list1);
        pairs.add(list2);
        System.out.println(new SmallestStringWithSwaps().smallestStringWithSwaps("dcab", pairs));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        DSU2 dsu = new DSU2(len);
        for(List<Integer> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<len;i++) {
            int key = dsu.find(i);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }
        StringBuilder sb = new StringBuilder(s);
        for(List<Integer> idx_list : map.values()) {
            sort(sb, idx_list);
        }
        return sb.toString();
    }

    private void sort(StringBuilder sb, List<Integer> idx_list) {
        Collections.sort(idx_list);
        char[] tmp = new char[idx_list.size()];
        for(int i=0;i<tmp.length;i++) {
            tmp[i] = sb.charAt(idx_list.get(i));
        }
        Arrays.sort(tmp);
        for(int i=0;i<idx_list.size();i++) {
            sb.setCharAt(idx_list.get(i), tmp[i]);
        }
    }

}

class DSU2 {

    int[] parent;

    public DSU2(int n) {
        parent = new int[n];
        for(int i=1;i<n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        return parent[x]!=x?parent[x]=find(parent[x]):x;
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }

}