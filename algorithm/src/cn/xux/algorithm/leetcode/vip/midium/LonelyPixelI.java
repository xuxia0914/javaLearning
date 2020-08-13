package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 531. 孤独像素 I
 * 给定一幅黑白像素组成的图像, 计算黑色孤独像素的数量。
 * 图像由一个由‘B’和‘W’组成二维字符数组表示, ‘B’和‘W’分别代表黑色像素和白色像素。
 * 黑色孤独像素指的是在同一行和同一列不存在其他黑色像素的黑色像素。
 *
 * 示例:
 * 输入:
 * [['W', 'W', 'B'],
 *  ['W', 'B', 'W'],
 *  ['B', 'W', 'W']]
 * 输出: 3
 * 解析: 全部三个'B'都是黑色孤独像素。
 *
 * 注意:
 * 输入二维数组行和列的范围是 [1,500]。
 */
public class LonelyPixelI {

    public int findLonelyPixel(char[][] picture) {
        if(picture==null||picture.length==0||picture[0].length==0) {
            return 0;
        }
        int m = picture.length;
        int n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(picture[i][j]=='B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int ans = 0;
        for(int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(picture[i][j]=='B'&&rows[i]==1&&cols[j]==1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
