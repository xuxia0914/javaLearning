package cn.xux.algorithm.leetcode.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1507. 转变日期格式
 * 给你一个字符串 date ，它的格式为 Day Month Year ，其中：
 * Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。
 * Month 是集合 {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"} 中的一个元素。
 * Year 的范围在 ​[1900, 2100] 之间。
 * 请你将字符串转变为 YYYY-MM-DD 的格式，其中：
 * YYYY 表示 4 位的年份。
 * MM 表示 2 位的月份。
 * DD 表示 2 位的天数。
 *
 * 示例 1：
 * 输入：date = "20th Oct 2052"
 * 输出："2052-10-20"
 *
 * 示例 2：
 * 输入：date = "6th Jun 1933"
 * 输出："1933-06-06"
 *
 * 示例 3：
 * 输入：date = "26th May 1960"
 * 输出："1960-05-26"
 *
 * 提示：
 * 给定日期保证是合法的，所以不需要处理异常输入。
 */
public class ReformatDate {

    public String reformatDate(String date) {
        Map<String, String> month = new HashMap<>();
        month.put("Jan", "01");
        month.put("Feb", "02");
        month.put("Mar", "03");
        month.put("Apr", "04");
        month.put("May", "05");
        month.put("Jun", "06");
        month.put("Jul", "07");
        month.put("Aug", "08");
        month.put("Sep", "09");
        month.put("Oct", "10");
        month.put("Nov", "11");
        month.put("Dec", "12");
        month.put("Jan", "01");
        String[] strs = date.split(" ");
        StringBuilder ans = new StringBuilder();
        ans.append(strs[2]).append("-").append(month.get(strs[1])).append("-");
        String day = strs[0].substring(0, strs[0].length()-2);
        if(day.length()==1) {
            ans.append("0");
        }
        ans.append(day);
        return ans.toString();
    }

}
