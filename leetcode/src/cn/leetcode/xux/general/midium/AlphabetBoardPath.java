package cn.leetcode.xux.general.midium;

/**
 * 5140. 字母板上的路径
 *
 * 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 *
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].
 *
 * 我们可以按下面的指令规则行动：
 *
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 *
 * 示例 1：
 * 输入：target = "leet"
 * 输出："DDR!UURRR!!DDD!"
 * 示例 2：
 * 输入：target = "code"
 * 输出："RR!DDRR!UUL!R!"
 *
 * 提示：
 *
 * 1 <= target.length <= 100
 * target 仅含有小写英文字母。
 */
public class AlphabetBoardPath {

    public String alphabetBoardPath(String target) {
        if(target==null||target.length()==0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int r = 0;
        int c = 0;
        int curr;
        int currR;
        int currC;
        for(int i=0;i<target.length();i++) {
            curr = target.charAt(i)-'a';
            currR = curr/5;
            currC = curr%5;
            if(currR==r&&currC==c) {
                sb.append('!');
                continue;
            }
            if(currR==5) {
                if(currC>c) {
                    for(int j=0;j<currC-c;j++) {
                        sb.append('R');
                    }
                }else if(currC<c) {
                    for(int j=0;j<c-currC;j++) {
                        sb.append('L');
                    }
                }
                if(currR>r) {
                    for(int j=0;j<currR-r;j++) {
                        sb.append('D');
                    }
                }else if(currR<r) {
                    for(int j=0;j<r-currR;j++) {
                        sb.append('U');
                    }
                }
            }else {
                if(currR>r) {
                    for(int j=0;j<currR-r;j++) {
                        sb.append('D');
                    }
                }else if(currR<r) {
                    for(int j=0;j<r-currR;j++) {
                        sb.append('U');
                    }
                }
                if(currC>c) {
                    for(int j=0;j<currC-c;j++) {
                        sb.append('R');
                    }
                }else if(currC<c) {
                    for(int j=0;j<c-currC;j++) {
                        sb.append('L');
                    }
                }
            }
            sb.append('!');
            r = currR;
            c = currC;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new AlphabetBoardPath().alphabetBoardPath("leet"));
    }

}
