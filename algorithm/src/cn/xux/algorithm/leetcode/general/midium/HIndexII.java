package cn.xux.algorithm.leetcode.general.midium;

/**
 * 275. H指数 II
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
 *
 * 示例:
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 *
 * 说明:
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 *
 * 进阶：
 * 这是 H指数 的延伸题目，本题中的 citations 数组是保证有序的。
 * 你可以优化你的算法到对数时间复杂度吗？
 */
public class HIndexII {

    public int hIndex(int[] citations) {
        if(citations==null||citations.length==0) {
            return 0;
        }
        int n = citations.length;
        for(int i=n-1;i>=0;i--) {
            if(citations[i]>=n-i&&(i==0||citations[i-1]<=n-i)) {
                return n-i;
            }
        }
        return 0;
    }

    /**
     * 时间复杂度O(logn)的方法
     * @param citations
     * @return
     */
    public int hIndex1(int[] citations) {
        if(citations==null||citations.length==0) {
            return 0;
        }
        int n = citations.length;
        int left = 0;
        int right = n-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(citations[mid]<n-mid) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        if(left==n-1&&citations[left]<1) {
            return 0;
        }
        return n-left;
    }

    public static void main(String[] args) {
        HIndexII hi = new HIndexII();
        System.out.println(hi.hIndex1(new int[]{0,0}));
        System.out.println(hi.hIndex1(new int[]{1,1,2,3,4,5,7}));
    }

}
