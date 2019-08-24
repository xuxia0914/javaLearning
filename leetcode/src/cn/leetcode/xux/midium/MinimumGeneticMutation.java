package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 433. 最小基因变化
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 *
 * 注意:
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 *
 * 示例 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 返回值: 1
 *
 * 示例 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 返回值: 2
 *
 * 示例 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 返回值: 3
 */
public class MinimumGeneticMutation {

    public int minMutation(String start, String end, String[] bank) {
        if(bank==null||bank.length==0) {
            return -1;
        }
        int n = bank.length;
        boolean[] visited = new boolean[n];
        int cnt = 0;
        boolean flag = false;
        for(int i=0;i<n;i++) {
            if(start.equals(bank[i])) {
                visited[i] = true;
                cnt++;
            }
            if(end.equals(bank[i])) {
                flag = true;
            }
        }
        if(!flag) {
            return -1;
        }
        int res = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()) {
            int size = queue.size();
            res++;
            while(size-->0) {
                String curr = queue.poll();
                for(int i=0;i<n;i++) {
                    if(!visited[i]&&helper(curr, bank[i])) {
                        if(bank[i].equals(end)) {
                            return res;
                        }
                        visited[i] = true;
                        cnt++;
                        if(cnt==n) {
                            return -1;
                        }
                        queue.offer(bank[i]);
                    }
                }
            }
        }
        return -1;
    }

    public boolean helper(String src, String target) {
        int cnt = 0;
        for(int i=0;i<src.length();i++) {
            if(src.charAt(i)!=target.charAt(i)) {
                cnt++;
            }
            if(cnt>1) {
                return false;
            }
        }
        return cnt==1;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumGeneticMutation().minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

}
