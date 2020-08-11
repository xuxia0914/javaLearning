package cn.xux.algorithm.leetcode.vip.easy;

import java.util.*;

/**
 * 359 (简单089) 日志速率限制器
 * 请你设计一个日志系统，
 * 可以流式接收日志以及它的时间戳。
 * 该日志会被打印出来，
 * 需要满足一个条件：
 * 当且仅当日志内容 在过去的 10 秒钟内没有被打印过。
 * 给你一条日志的内容和它的时间戳（粒度为秒级），
 * 如果这条日志在给定的时间戳应该被打印出来，则返回 true，否则请返回 false。
 * 要注意的是，
 * 可能会有多条日志在同一时间被系统接收。
 *
 * 示例
 * Logger logger = new Logger();
 * // 日志内容 “foo” 在时刻 1 到达系统
 * logger.shouldPrintMessage(1, “foo”); returns true;
 * // 日志内容 “bar” 在时刻 2 到达系统
 * logger.shouldPrintMessage(2,“bar”); returns true;
 * // 日志内容 “foo” 在时刻 3 到达系统
 * logger.shouldPrintMessage(3,“foo”); returns false;
 * // 日志内容 “bar” 在时刻 8 到达系统
 * logger.shouldPrintMessage(8,“bar”); returns false;
 * // 日志内容 “foo” 在时刻 10 到达系统
 * logger.shouldPrintMessage(10,“foo”); returns false;
 * // 日志内容 “foo” 在时刻 11 到达系统
 * logger.shouldPrintMessage(11,“foo”); returns true;
 */
public class LoggerRateLimiter {

}

class Logger {

    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)||timestamp-map.get(message)>=10) {
            map.put(message, timestamp);
            return true;
        }else {
            return false;
        }
    }

}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

