package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2120. 执行所有后缀指令
 * 现有一个 n x n 大小的网格，左上角单元格坐标 (0, 0) ，
 * 右下角单元格坐标 (n - 1, n - 1) 。
 * 给你整数 n 和一个整数数组 startPos ，
 * 其中 startPos = [startrow, startcol]
 * 表示机器人最开始在坐标为 (startrow, startcol) 的单元格上。
 * <p>
 * 另给你一个长度为 m 、下标从 0 开始的字符串 s ，
 * 其中 s[i] 是对机器人的第 i 条指令：'L'（向左移动），
 * 'R'（向右移动），'U'（向上移动）和 'D'（向下移动）。
 * <p>
 * 机器人可以从 s 中的任一第 i 条指令开始执行。
 * 它将会逐条执行指令直到 s 的末尾，
 * 但在满足下述条件之一时，机器人将会停止：
 * <p>
 * 下一条指令将会导致机器人移动到网格外。
 * 没有指令可以执行。
 * 返回一个长度为 m 的数组 answer ，
 * 其中 answer[i] 是机器人从第 i 条指令 开始 ，可以执行的 指令数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 3, startPos = [0,1], s = "RRDDLU"
 * 输出：[1,5,4,3,1,0]
 * 解释：机器人从 startPos 出发，并从第 i 条指令开始执行：
 * - 0: "RRDDLU" 在移动到网格外之前，只能执行一条 "R" 指令。
 * - 1:  "RDDLU" 可以执行全部五条指令，机器人仍在网格内，最终到达 (0, 0) 。
 * - 2:   "DDLU" 可以执行全部四条指令，机器人仍在网格内，最终到达 (0, 0) 。
 * - 3:    "DLU" 可以执行全部三条指令，机器人仍在网格内，最终到达 (0, 0) 。
 * - 4:     "LU" 在移动到网格外之前，只能执行一条 "L" 指令。
 * - 5:      "U" 如果向上移动，将会移动到网格外。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, startPos = [1,1], s = "LURD"
 * 输出：[4,1,0,0]
 * 解释：
 * - 0: "LURD"
 * - 1:  "URD"
 * - 2:   "RD"
 * - 3:    "D"
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：n = 1, startPos = [0,0], s = "LRUD"
 * 输出：[0,0,0,0]
 * 解释：无论机器人从哪条指令开始执行，都会移动到网格外。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * 1 <= n, m <= 500
 * startPos.length == 2
 * 0 <= startrow, startcol < n
 * s 由 'L'、'R'、'U' 和 'D' 组成
 */
public class ExecutionOfAllSuffixInstructionsStayingInAGrid {

    // 模拟就行了
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] ans = new int[m];
        for(int i=0;i<m;i++) {
            int j = i;
            int x = startPos[0];
            int y = startPos[1];
            while(x>=0&&x<n&&y>=0&&y<n&&j<m) {
                char c = s.charAt(j);
                switch(c) {
                    case('U') : x--;break;
                    case('D') : x++;break;
                    case('L') : y--;break;
                    case('R') : y++;break;
                }
                j++;
            }
            ans[i] = j-i-(x>=0&&x<n&&y>=0&&y<n&&j==m?0:1);
        }
        return ans;
    }

}