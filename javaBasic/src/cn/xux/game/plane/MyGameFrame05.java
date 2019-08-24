package cn.xux.game.plane;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 飞机游戏的主窗口
 */
public class MyGameFrame05 extends Frame {

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    Plane plane = new Plane(planeImg, 250, 250);
//    Shell shell = new Shell();
    Shell[] shells = new Shell[50];

    Explode bao ;

    Date startTime = new Date();
    Date endTime ;
    int period ;    //游戏持续时间

    @Override
    public void paint(Graphics g) { //自动被调用，g相当于一只画笔
        Color c = g.getColor();
        g.drawImage(bg, 0, 0, null);
        plane.drawMySelf(g);
//        shell.draw(g);
        for(Shell shell : shells) {
            shell.draw(g);
            if(shell.getRect().intersects(plane.getRect())) {
//                System.out.println("相撞了！！！");
                plane.live = false;
                if(bao==null) {
                    bao = new Explode(plane.x, plane.y);
                    endTime = new Date();
                    period = (int)((endTime.getTime()-startTime.getTime())/1000);
                }
                bao.draw(g);
            }
            if(!plane.live) {
                g.setColor(Color.RED);
                Font f = new Font("宋体", Font.BOLD, 30);
                g.setFont(f);
                g.drawString("游戏时间："+period+"m秒", Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2);
            }
        }

        g.setColor(c);

    }



    //帮助我们反复重画窗口！
    class PaintTread extends Thread {

        @Override
        public void run() {
            while (true) {
                //System.out.println("窗口画一次！");
                repaint();  //重画

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //定义键盘监听的内部类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            System.out.println("按下：" + e.getKeyCode());
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("抬起：" + e.getKeyCode());
            plane.minusDirection(e);
        }
    }

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        this.setTitle("打飞机游戏");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(300, 300);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintTread().start();   //启动重画窗口的线程
        addKeyListener(new KeyMonitor());  //给窗口增加键盘的监听

        //初始化炮弹
        for(int i=0;i<shells.length;i++) {
            shells[i] = new Shell();
        }
    }

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        MyGameFrame05 f = new MyGameFrame05();
        f.launchFrame();
    }

}
