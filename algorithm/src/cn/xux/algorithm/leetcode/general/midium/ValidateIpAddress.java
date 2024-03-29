package cn.xux.algorithm.leetcode.general.midium;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * 468. 验证IP地址
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
 * IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
 * 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
 * IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。
 * 比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。
 * 而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。
 * 所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
 * 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。
 * 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 * 同时，在 IPv6 地址中，多余的 0 也是不被允许的。
 * 比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 * 说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。
 *
 * 示例 1:
 * 输入: "172.16.254.1"
 * 输出: "IPv4"
 * 解释: 这是一个有效的 IPv4 地址, 所以返回 "IPv4"。
 *
 * 示例 2:
 * 输入: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出: "IPv6"
 * 解释: 这是一个有效的 IPv6 地址, 所以返回 "IPv6"。
 *
 * 示例 3:
 * 输入: "256.256.256.256"
 * 输出: "Neither"
 * 解释: 这个地址既不是 IPv4 也不是 IPv6 地址。
 */
public class ValidateIpAddress {

    public String validIPAddress(String IP) {
        try {
            return InetAddress.getByName(IP) instanceof Inet4Address?"IPv4":"IPv6";
        }catch(Exception e) {
            return "Neither";
        }
    }

    public String validIPAddress1(String IP) {
        if(IP.indexOf('.') != -1) {
            if(isIpv4(IP)) {
                return "IPv4";
            }
        }
        if(IP.indexOf(':') != -1) {
            if(isIpv6(IP)) {
                return "IPv6";
            }
        }
        return "Neither";
    }
    public boolean isIpv6(String ip) {
        if(ip.indexOf('.') != -1) return false;
        if(ip.endsWith(".") || ip.endsWith(":")) return false;
        String[] str = ip.split(":");
        if(str.length != 8) return false;
        for(int i = 0; i < str.length; i++) {
            if(str[i].equals("")) return false;
            char[] cur = str[i].toCharArray();
            if(cur.length > 4) return false;
            for(char c : cur) {
                if((Character.isUpperCase(c) && c > 'F') || (Character.isLowerCase(c) && c > 'f')) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isIpv4(String str) {
        if(str.indexOf(':') != -1) return false;
        if(str.endsWith(".") || str.endsWith(":")) return false;
        String[] strs = str.split("\\.");
        if(strs.length != 4) {
            return false;
        }
        for(int i = 0; i < strs.length; i++) {
            if(strs[i].equals("")) return false;
            int sum = 0;
            for(char c : strs[i].toCharArray()) {
                if(Character.isLetter(c)) return false;
                sum = sum * 10 + c - '0';
                if(sum >= 256) return false;
            }
            if(strs[i].length() != Integer.toString(sum).length()) {
                return false;
            }
        }
        return true;
    }

}
