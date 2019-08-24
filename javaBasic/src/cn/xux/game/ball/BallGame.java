package cn.xux.game.ball;

import javax.swing.*;
import java.awt.*;

public class BallGame extends JFrame {

    Image ball = Toolkit.getDefaultToolkit().getImage("img/ball.png");
    Image desk = Toolkit.getDefaultToolkit().getImage("img/desk.jpg");

    double x = 100, y = 100;
    boolean right = true;

    //画窗口的方法
    public void paint(Graphics g) {
        System.out.println("窗口被画了一次！");
        g.drawImage(desk, 0, 0, null);
        g.drawImage(ball, (int)x, (int)y, null);

        if(right) {
            x = x+10;
        }else {
            x = x-10;
        }

        if(x>856-40-30) {
            right = false;
        }

        if(x<40) {
            right = true;
        }

    }

    //窗口加载
    void launchFrame() {
        setSize(856, 500);
        setLocation(50, 50);
        setVisible(true);

        //重画窗口,每秒25次
        while(true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BallGame game = new BallGame();
        game.launchFrame();
    }

}
