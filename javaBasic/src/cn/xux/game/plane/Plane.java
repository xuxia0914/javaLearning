package cn.xux.game.plane;
 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane  extends GameObject {

    int speed = 3;

    boolean live = true;

    boolean left, up, right, down;

    @Override
    public void drawMySelf(Graphics g) {

        if(live) {
            super.drawMySelf(g);
            //this.x++;//飞机水平飞，我们也可以调整x、y算法，按照我们指定的路径飞行
            if(left) {
                x -= speed;
            }
            if(right) {
                x += speed;
            }
            if(up) {
                y -= speed;
            }
            if(down) {
                y += speed;
            }
        }else {



        }

    }
     
    public Plane(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    //按下某个键，增加相应方向
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A :
                left = true;
                break;
            case KeyEvent.VK_W :
                up = true;
                break;
            case KeyEvent.VK_D :
                right = true;
                break;
            case KeyEvent.VK_S :
                down = true;
                break;
        }
    }

    //抬起某个键，取消相应方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A :
                left = false;
                break;
            case KeyEvent.VK_W :
                up = false;
                break;
            case KeyEvent.VK_D :
                right = false;
                break;
            case KeyEvent.VK_S :
                down = false;
                break;
        }
    }

}