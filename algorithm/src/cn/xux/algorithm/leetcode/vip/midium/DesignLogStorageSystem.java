package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 635. 设计日志存储系统（map）
 * 你将获得多条日志，每条日志都有唯一的 id 和 timestamp，
 * timestamp 是形如 Year:Month:Day:Hour:Minute:Second 的字符串，
 * 例如 2017:01:01:23:59:59，所有值域都是零填充的十进制数。
 * 设计一个日志存储系统实现如下功能：
 * void Put(int id, string timestamp)：给定日志的 id 和 timestamp，将这个日志存入你的存储系统中。
 * int[] Retrieve(String start, String end, String granularity)：返回在给定时间区间内的所有日志的 id。start 、 end 和 timestamp 的格式相同，granularity 表示考虑的时间级。
 * 比如，start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day" 代表区间 2017 年 1 月 1 日到 2017 年 1 月 2 日。
 *
 * 样例 1 ：
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
 * // 返回值 [1,2,3]，返回从 2016 年到 2017 年所有的日志。
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour");
 * // 返回值 [1,2], 返回从 2016:01:01:01 到 2017:01:01:23 区间内的日志，
 * 日志 3 不在区间内。
 *
 * 注释 ：
 * Put 和 Retrieve 的指令总数不超过 300。
 * 年份的区间是 [2000,2017]，小时的区间是 [00,23]。
 * Retrieve 的输出顺序不作要求。
 */
public class DesignLogStorageSystem {
}

class LogSystem {

    ArrayList< long[] > list;
    public LogSystem() {
        list = new ArrayList < long[] > ();
    }

    public void put(int id, String timestamp) {
        int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
        list.add(new long[] {convert(st), id});
    }
    public long convert(int[] st) {
        st[1] = st[1] - (st[1] == 0 ? 0 : 1);
        st[2] = st[2] - (st[2] == 0 ? 0 : 1);
        return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    }
    public List < Integer > retrieve(String s, String e, String gra) {
        ArrayList < Integer > res = new ArrayList();
        long start = granularity(s, gra, false);
        long end = granularity(e, gra, true);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] >= start && list.get(i)[0] < end)
                res.add((int) list.get(i)[1]);
        }
        return res;
    }

    public long granularity(String s, String gra, boolean end) {
        HashMap< String, Integer > h = new HashMap();
        h.put("Year", 0);
        h.put("Month", 1);
        h.put("Day", 2);
        h.put("Hour", 3);
        h.put("Minute", 4);
        h.put("Second", 5);
        String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
        String[] st = s.split(":");
        for (int i = 0; i <= h.get(gra); i++) {
            res[i] = st[i];
        }
        int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
        if (end)
            t[h.get(gra)]++;
        return convert(t);
    }


}