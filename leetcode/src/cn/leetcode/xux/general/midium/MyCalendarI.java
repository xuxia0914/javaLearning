package cn.leetcode.xux.general.midium;

import java.util.Arrays;

/**
 * 729. 我的日程安排表 I
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * 示例 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * 解释:
 * 第一个日程安排可以添加到日历中.  第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了。
 * 第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 。
 *
 * 说明:
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 100次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 */
public class MyCalendarI {



}

class MyCalendar {

    int[][] books;

    public MyCalendar() {
        books = new int[0][2];
    }

    public boolean book(int start, int end) {
        int[] curr = new int[]{start, end-1};
        int len = books.length;
        int idx = Arrays.binarySearch(
                books,
                curr,
                (o1, o2)->o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1]
        );
        if(idx>=0) {
            return false;
        }
        idx = -idx-1;
        if((idx==0||books[idx-1][1]<curr[0])&&(idx==len||books[idx][0]>curr[1])) {
            int[][] newBooks = new int[len+1][];
            for(int i=0;i<=len;i++) {
                if(i<idx) {
                    newBooks[i] = books[i];
                }else if(i==idx) {
                    newBooks[i] = curr;
                }else {
                    newBooks[i] = books[i-1];
                }
            }
            books = newBooks;
            return true;
        }else {
            return false;
        }
        /*for(int[] book : books) {
            if(!(end-1<book[0]||start>book[1])) {
                return false;
            }
        }
        books.add(new int[]{start, end-1});
        return true;*/
    }

}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */