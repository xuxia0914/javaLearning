package cn.xux.game.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 */
public class MyGameFrame02 extends JFrame {

    public void paint(Graphics g) {
        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(Color.BLUE);
        g.drawLine(100, 100, 300, 300);
        g.drawRect(100, 100, 300, 300);
        g.drawOval(100, 100, 300, 300);
        g.fillRect(100, 100, 40, 40);
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 50));
        g.drawString("你好", 200, 200);

        g.setColor(c);
        g.setFont(f);
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
    }

    public static void main(String[] args) {
        MyGameFrame02 f = new MyGameFrame02();
        f.launchFrame();
    }

}
