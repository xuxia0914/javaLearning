package cn.xux.algorithm.leetcode.vip.midium;

import java.util.*;

/**
 * 444. 序列重建（拓扑排序）
 * 验证原始的序列 org 是否可以从序列集 seqs 中唯一地重建。
 * 序列 org 是 1 到 n 整数的排列，其中 1 ≤ n ≤ 104。
 * 重建是指在序列集 seqs 中构建最短的公共超序列。（即使得所有 seqs 中的序列都是该最短序列的子序列）。
 * 确定是否只可以从 seqs 重建唯一的序列，且该序列就是 org 。
 *
 * 示例 1：
 * 输入：org: [1,2,3], seqs: [[1,2],[1,3]]
 * 输出：false
 * 解释：[1,2,3] 不是可以被重建的唯一的序列，因为 [1,3,2] 也是一个合法的序列。
 *
 * 示例 2：
 * 输入：org: [1,2,3], seqs: [[1,2]]
 * 输出：false
 * 解释：可以重建的序列只有 [1,2]。
 *
 * 示例 3：
 * 输入：org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 * 输出：true
 * 解释：序列 [1,2], [1,3] 和 [2,3] 可以被唯一地重建为原始的序列 [1,2,3]。
 *
 * 示例 4：
 * 输入：org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * 输出：true
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        int n = org.length;
        //判断seqs合法性
        for(int[] seq : seqs) {
            for (int num : seq) {
                if (num < 1 || num > n) {
                    return false;
                }
            }
        }
        if(n==1) {
            for(int[] seq : seqs) {
                if(seq.length>0) {
                    return true;
                }
            }
            return false;
        }
        // 初始化拓扑图
        // int[i]表示元素i的入度
        int[] in = new int[n+1];
        // edges[i]表示元素i指向的下一个元素的集合
        Set<Integer>[] edges = new Set[n+1];
        for(int[] seq : seqs) {
            for(int i=0;i<seq.length-1;i++) {
                if(edges[seq[i]]==null) {
                    edges[seq[i]] = new HashSet<>();
                }
                // 这里要避免重复计算入度
                if(!edges[seq[i]].contains(seq[i+1])) {
                    edges[seq[i]].add(seq[i+1]);
                    in[seq[i+1]]++;
                }
            }
        }
        int idx = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            if(in[i]==0) {
                queue.offer(i);
            }
        }
        // 获得下一个(唯一且等于org[idx])入度为0的元素，
        while(queue.size()==1&&queue.peek()==org[idx++]) {
            int curr = queue.poll();
            if(edges[curr]!=null) {
                for(int next : edges[curr]) {
                    if(--in[next]==0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return idx==n;
    }

}
