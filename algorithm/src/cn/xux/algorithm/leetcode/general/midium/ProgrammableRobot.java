package cn.xux.algorithm.leetcode.general.midium;

/**
 * LCP 3. 机器人大冒险
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 *
 * 示例 2：
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 *
 * 示例 3：
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *
 * 限制：
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 */
public class ProgrammableRobot {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int lenX = 0;
        int lenY = 0;
        for(char c : command.toCharArray()) {
            if(c=='U') {
                lenY++;
            }else {
                lenX++;
            }
        }
        for(int[] obstacle : obstacles) {
            if(obstacle[0]>x||obstacle[1]>y) {
                continue;
            }
            int cycleNum = Math.min(obstacle[0]/lenX, obstacle[1]/lenY);
            int remainX = obstacle[0]-lenX*cycleNum;
            int remainY = obstacle[1]-lenY*cycleNum;
            int index = 0;
            while(remainX>=0&&remainY>=0) {
                if(remainX==0&&remainY==0) {
                    return false;
                }
                if(command.charAt(index++)=='U') {
                    remainY--;
                }else {
                    remainX--;
                }
            }
        }
        int cycleNum = Math.min(x/lenX, y/lenY);
        int remainX = x-lenX*cycleNum;
        int remainY = y-lenY*cycleNum;
        int index = 0;
        while(remainX>=0&&remainY>=0) {
            if (remainX == 0 && remainY == 0) {
                return true;
            }
            if (command.charAt(index++) == 'U') {
                remainY--;
            } else {
                remainX--;
            }
        }
        return false;
    }

}
