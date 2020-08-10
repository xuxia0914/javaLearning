package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 353. 贪吃蛇（deque+set）
 * 请你设计一个 贪吃蛇游戏，该游戏将会在一个 屏幕尺寸 = 宽度 x 高度 的屏幕上运行。
 * 起初时，蛇在左上角的 (0, 0) 位置，身体长度为 1 个单位。
 * 你将会被给出一个 (行, 列) 形式的食物位置序列。当蛇吃到食物时，身子的长度会增加 1 个单位，得分也会 +1。
 * 食物不会同时出现，会按列表的顺序逐一显示在屏幕上。比方讲，第一个食物被蛇吃掉后，第二个食物才会出现。
 * 当一个食物在屏幕上出现时，它被保证不能出现在被蛇身体占据的格子里。
 * 对于每个 move() 操作，你需要返回当前得分或 -1（表示蛇与自己身体或墙相撞，意味游戏结束）。
 *
 * 示例：
 * 给定 width = 3, height = 2, 食物序列为 food = [[1,2],[0,1]]。
 * Snake snake = new Snake(width, height, food);
 * 初始时，蛇的位置在 (0,0) 且第一个食物在 (1,2)。
 * |S| | |
 * | | |F|
 * snake.move("R"); -> 函数返回 0
 * | |S| |
 * | | |F|
 * snake.move("D"); -> 函数返回 0
 * | | | |
 * | |S|F|
 * snake.move("R"); -> 函数返回 1
 * (蛇吃掉了第一个食物，同时第二个食物出现在位置 (0,1))
 * | |F| |
 * | |S|S|
 * snake.move("U"); -> 函数返回 1
 * | |F|S|
 * | | |S|
 * snake.move("L"); -> 函数返回 2 (蛇吃掉了第二个食物)
 * | |S|S|
 * | | |S|
 * snake.move("U"); -> 函数返回 -1 (蛇与边界相撞，游戏结束)
 */
public class DesignSnakeGame {
    LinkedList<Integer> que;
    HashSet<Integer> body;
    int m;
    int n;
    int [][] food;
    int index;
    int score;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame(int width, int height, int[][] food) {
        que = new LinkedList<>();
        que.offerFirst(0);
        body = new HashSet<>();
        body.add(0);

        m = height;
        n = width;
        this.food = food;
        index = 0;
        score = 0;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if(score < 0){
            return -1;
        }

        LinkedList<Integer> localQue = que;
        HashSet<Integer> localBody = body;

        int head = que.peekFirst();
        int x = head / n;
        int y = head % n;
        switch(direction){
            case "U":
                x--;
                break;
            case "D":
                x++;
                break;
            case "L":
                y--;
                break;
            case "R":
                y++;
                break;
        }

        int newHead = x * n + y;

        // Remove tail position first since new head could be at old tail position
        body.remove(que.peekLast());
        if(x < 0 || x >= m || y < 0 || y >= n || body.contains(newHead)){
            score = -1;
            return score;
        }

        que.offerFirst(newHead);
        body.add(newHead);

        // If this is food, there is no need to remove tail.
        if(index < food.length && food[index][0] == x && food[index][1] == y){
            index++;
            score++;
            body.add(que.peekLast());
            return score;
        }

        // If this is not food, then we nned to remove tail.
        que.pollLast();
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
