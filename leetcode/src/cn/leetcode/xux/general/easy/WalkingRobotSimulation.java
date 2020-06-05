package cn.leetcode.xux.general.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * 返回从原点到机器人的最大欧式距离的平方。
 *
 * 示例 1：
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 *
 * 示例 2：
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *
 * 提示：
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 */
public class WalkingRobotSimulation {

    public static void main(String[] args) {
        new WalkingRobotSimulation().robotSim(new int[]{4,-1,4,-2,4}, new int[][]{{2,4}});
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        if(commands==null||commands.length==0) {
            return 0;
        }
        int[][] steps = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int stepIdx = 0;
        int[] currPos = new int[]{0,0};
        Set<String> obs = new HashSet<>();
        for(int[] obstacle : obstacles) {
            obs.add(obstacle[0]+"#"+obstacle[1]);
        }
        int result = 0;
        for(int command : commands) {
            if(command==-2) {
                stepIdx = (stepIdx+3)%4;
            }else if(command==-1) {
                stepIdx = (stepIdx+1)%4;
            }else {
                int cnt = command;
                while(cnt-->0) {
                    int nextX = currPos[0]+steps[stepIdx][0];
                    int nextY = currPos[1]+steps[stepIdx][1];
                    if(obs.contains(nextX+"#"+nextY)) {
                        break;
                    }else {
                        currPos[0] = nextX;
                        currPos[1] = nextY;
                        result = Math.max(currPos[0]*currPos[0]+currPos[1]*currPos[1], result);
                    }
                }
            }
        }
        return result;
    }

}
