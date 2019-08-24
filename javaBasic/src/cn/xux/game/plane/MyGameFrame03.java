package cn.xux.game.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 */
public class MyGameFrame03 extends JFrame {

    Image plane = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    int planeX = 250, planeY = 250;

    @Override
    public void paint(Graphics g) { //自动被调用，g相当于一只画笔
        g.drawImage(bg, 0, 0, null);
        g.drawImage(plane, planeX, planeY, null);
        planeX++;
    }

    //帮助我们反复重画窗口！
    class PaintTread extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("窗口画一次！");
                repaint();  //重画

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        this.setTitle("打飞机游戏");
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocation(300, 300);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintTread().start();
    }

    public static void main(String[] args) {
        MyGameFrame03 f = new MyGameFrame03();
        f.launchFrame();
    }

}
