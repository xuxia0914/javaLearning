package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
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
