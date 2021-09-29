package cn.xux.algorithm.leetcode.general.midium;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length<2) {
            return intervals;
        }
        int len = intervals.length;
        sort(intervals, 0, len-1);
        int i = 0;
        int cnt = len;
        while(i<len-1) {
            if(intervals[i]!=null) {
                int j = i+1;
                while(j<len) {
                    if(intervals[i][1]>=intervals[j][0]) {
                        intervals[i][1] = Math.max(intervals[i][1], intervals[j][1]);
                        intervals[j] = null;
                        j++;
                        cnt--;
                    }else {
                        break;
                    }
                }
            }
            i++;
        }

        int[][] res = new int[cnt][2];
        int k = 0;
        for(int[] interval : intervals) {
            if(interval!=null) {
                res[k++] = interval;
            }
        }
        return res;
    }

    public void sort(int[][] intervals, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int[] key = intervals[start];
        while(left<right) {
            while(left<right&&intervals[right][0]>=key[0]) {
                right--;
            }
            while(left<right&&intervals[left][0]<=key[0]) {
                left++;
            }
            if(left<right) {
                int[] tmp = intervals[left];
                intervals[left] = intervals[right];
                intervals[right] = tmp;
            }
        }
        intervals[start] = intervals[right];
        intervals[right] = key;
        sort(intervals, start, right-1);
        sort(intervals, right+1, end);
    }

    public static void main(String[] args) {
        /*new MergeIntervals().merge(new int[][]{
            {8, 10},
            {1, 3},
            {15, 18},
            {2, 6}
        });*/
        new MergeIntervals().merge(new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        });
    }

}
