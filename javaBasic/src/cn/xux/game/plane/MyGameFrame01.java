package cn.xux.game.plane;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 */
public class MyGameFrame01 extends JFrame {

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
        MyGameFrame01 f = new MyGameFrame01();
        f.launchFrame();
    }

}
