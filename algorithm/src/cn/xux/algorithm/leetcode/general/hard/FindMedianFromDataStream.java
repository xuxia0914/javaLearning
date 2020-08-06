package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出数据流的中位数
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 */
public class FindMedianFromDataStream {

    public List<Integer> list = new ArrayList<Integer>();

    public void add(Integer i) {
        if(this.list.size()==0) {
            this.list.add(i);
        }else {
            int left=0, right=this.list.size()-1, mid;
            while(left<right) {
                mid = (left+right)/2;
                if(list.get(mid)<i) {
                    left = mid+1;
                }else {
                    right = mid;
                }
            }
            if(right==this.list.size()-1&&list.get(right)<i) {
                list.add(i);
            }else {
                list.add(right, i);
            }
        }
    }

    public float findMedian() {
        if(this.list.size()%2==0) {
            return (float)(list.get(list.size()/2)+list.get(list.size()/2-1))/2;
        }else {
            return (float)list.get(list.size()/2);
        }
    }

    public static void main(String[] args) {
        FindMedianFromDataStream fmfds = new FindMedianFromDataStream();
        fmfds.add(1);
        fmfds.add(2);
        fmfds.add(3);
        fmfds.add(4);
        fmfds.add(5);
        System.out.println(fmfds.findMedian());
        fmfds.add(3);
        System.out.println(fmfds.findMedian());
    }

}
