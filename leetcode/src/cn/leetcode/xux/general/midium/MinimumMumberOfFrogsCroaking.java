package cn.leetcode.xux.general.midium;

/**
 * 1419. 数青蛙
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。
 * 由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 注意：要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。
 * 如果没有输出全部五个字母，那么它就不会发出声音。
 * 如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 *
 * 示例 1：
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙 “呱呱” 两次
 *
 * 示例 2：
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "crcoakroak"
 * 第二只青蛙 "crcoakroak"
 *
 * 示例 3：
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 *
 * 示例 4：
 * 输入：croakOfFrogs = "croakcroa"
 * 输出：-1
 *
 * 提示：
 * 1 <= croakOfFrogs.length <= 10^5
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 */
public class MinimumMumberOfFrogsCroaking {

    public int minNumberOfFrogs(String croakOfFrogs) {
        int len = croakOfFrogs.length();
        if(len%5!=0) {
            return -1;
        }
        int[] needs = new int[4];
        int ans = 0;
        int curr = 0;
        for(char c : croakOfFrogs.toCharArray()) {
            switch(c) {
                case 'c' : {
                    needs[0]++;
                    curr++;
                    ans = Math.max(ans, curr);
                    break;
                }
                case 'r' : {
                    if(needs[0]<1) {
                        return -1;
                    }
                    needs[0]--;
                    needs[1]++;
                    break;
                }
                case 'o' : {
                    if(needs[1]<1) {
                        return -1;
                    }
                    needs[1]--;
                    needs[2]++;
                    break;
                }
                case 'a' : {
                    if(needs[2]<1) {
                        return -1;
                    }
                    needs[2]--;
                    needs[3]++;
                    break;
                }
                case 'k' : {
                    if(needs[3]<1) {
                        return -1;
                    }
                    needs[3]--;
                    curr--;
                    break;
                }
                default: return -1;
            }
        }
        return needs[0]==0&&needs[1]==0&&needs[2]==0&&needs[3]==0?ans:-1;
    }

}
