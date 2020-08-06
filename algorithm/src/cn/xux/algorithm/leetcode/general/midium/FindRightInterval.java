package cn.xux.algorithm.leetcode.general.midium;

/**
 * 436. 寻找右区间
 * 给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，它的起始点大于或等于区间 i 的终点，这可以称为 j 在 i 的“右侧”。
 * 对于任何区间，你需要存储的满足条件的区间 j 的最小索引，这意味着区间 j 有最小的起始点可以使其成为“右侧”区间。
 * 如果区间 j 不存在，则将区间 i 存储为 -1。最后，你需要输出一个值为存储的区间值的数组。
 *
 * 注意:
 * 你可以假设区间的终点总是大于它的起始点。
 * 你可以假定这些区间都不具有相同的起始点。
 *
 *  示例 1:
 * 输入: [ [1,2] ]
 * 输出: [-1]
 * 解释:集合中只有一个区间，所以输出-1。
 *
 *  示例 2:
 * 输入: [ [3,4], [2,3], [1,2] ]
 * 输出: [-1, 0, 1]
 * 解释:对于[3,4]，没有满足条件的“右侧”区间。
 * 对于[2,3]，区间[3,4]具有最小的“右”起点;
 * 对于[1,2]，区间[2,3]具有最小的“右”起点。
 *
 *  示例 3:
 * 输入: [ [1,4], [2,3], [3,4] ]
 * 输出: [-1, 2, -1]
 * 解释:对于区间[1,4]和[3,4]，没有满足条件的“右侧”区间。
 * 对于[2,3]，区间[3,4]有最小的“右”起点。
 */
public class FindRightInterval {

    public static void main(String[] args) {
        new FindRightInterval().findRightInterval(new int[][]{{3,4}, {2,3}, {1,2}});
    }

    public int[] findRightInterval(int[][] intervals) {
        if(intervals==null||intervals.length==0) {
            return new int[0];
        }
        int len = intervals.length;
        int[] idxs = new int[len];
        for(int i=0;i<len;i++) {
            idxs[i] = i;
        }
        int[] result = new int[len];
        qSort(intervals, idxs, 0, len-1);
        for(int i=0;i<len;i++) {
            int left = i+1;
            int right = len-1;
            while(left<right) {
                int mid = (left+right)/2;
                if(intervals[mid][0]>=intervals[i][1]) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }
            if(left>len-1||(left==len-1&&intervals[left][0]<intervals[i][1])) {
                result[idxs[i]] = -1;
            }else {
                result[idxs[i]] = idxs[left];
            }
        }
        return result;
    }

    public void qSort(int[][] intervals, int[] idxs, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int[] key = intervals[start];
        while(left<right) {
            while(left<right&&(intervals[right][0]>key[0]||(intervals[right][0]==key[0]&&intervals[right][1]>=key[1]))) {
                right--;
            }
            while(left<right&&(intervals[left][0]<key[0]||(intervals[left][0]==key[0]&&intervals[left][1]<=key[1]))) {
                left++;
            }
            if(left<right) {
                int[] tmp1 = intervals[left];
                intervals[left] = intervals[right];
                intervals[right] = tmp1;
                int tmp2 = idxs[left];
                idxs[left] = idxs[right];
                idxs[right] = tmp2;
            }
        }
        intervals[start] = intervals[right];
        intervals[right] = key;
        int tmp = idxs[start];
        idxs[start] = idxs[right];
        idxs[right] = tmp;
        qSort(intervals, idxs, start, right-1);
        qSort(intervals, idxs, right+1, end);
    }

}
