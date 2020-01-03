import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Currency;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//主要任务在这，继承JPanel,使用KeyListener
public class MyJpanel extends JPanel implements KeyListener{

    int[][] map =  new int[22][22];     //定义地图坐标
    SnakeList snakeList;

    int appleX;             //贪食蛇的食物苹果 坐标
    int appleY;
    int direction = 2;      //当前蛇前进方向
    int score = 0;          //分数

    Timer time =  new Timer(500, new TimerListiner());      //建立时间点

    boolean isPause = false;

    public MyJpanel() {
        // TODO Auto-generated constructor stub

        draWall();
        newGame();
        createApple();
        time.start();
    }

    //初始化贪食蛇
    public void newGame(){
        snakeList = new SnakeList();
        snakeList.addFirstSnake(10, 8);
        snakeList.addFirstSnake(10, 9);
        snakeList.addFirstSnake(10, 10);
    }

    //在地图四周画上墙壁
    public void draWall(){
        for (int i = 0; i < 22; i++) {
            map[0][i] = 1;
            map[21][i] = 1;
            map[i][0] = 1;
            map[i][21] = 1;
        }
    }

    //生产贪食蛇食物
    public void createApple(){
        int x,y;
        do{
            x = (int) (Math.random()*20)+1;
            y = (int) (Math.random()*20)+1;
//          Snake current = snakeList.findSnake(x, y);
        }while(snakeList.findSnake(x, y)==1);       //不可以在贪食蛇身体上生产
        appleX = x;
        appleY = y;
    }

    //控制贪食蛇移动，主要思想是链表添加头节点，删除尾节点，如果吃了苹果则不删除尾节点
    public void moving(){

        Snake current = snakeList.first;

        switch (direction) {
        case 0:
            snakeList.addFirstSnake(current.x-1, current.y);
            break;
        case 1:
            snakeList.addFirstSnake(current.x+1, current.y);
            break;
        case 2:
            snakeList.addFirstSnake(current.x, current.y+1);
            break;
        case 3:
            snakeList.add(current.x, current.y-1);
            break;
        }
        isOver();
        if (eating(current)==0) {
            Snake now = null;
            while (current.next!=null) {
                now = current;
                current = current.next;
            }
            now.next = null;
            current = null;
        }
        repaint();

    }

    //判断贪食蛇是否吃掉了苹果
    public int eating(Snake s){
        if(s.x==appleX&&s.y==appleY){
            score += 10;
            createApple();
            return 1;
        }
        return 0;
    }

    //判断游戏是否结束。撞墙了或者撞到自己了
    public void isOver(){
        Snake cur = snakeList.first;
        if (cur.x==0||cur.x==21||cur.y==0||cur.y==21||isSelf()==1) {
            JOptionPane.showMessageDialog(null, "游戏结束！");
            newGame();
            createApple();
            direction = 2;
            score = 0;
        }
    }

    //判断是否撞到自己
    public int  isSelf() {
        Snake cur = snakeList.first;
        Snake first = cur;
        cur = cur.next;
        while(cur!=null){
            if (first.x==cur.x&&first.y==cur.y) {
                return 1;
            }else{
                cur = cur.next;
            }
        }
        return 0;

    }

    //画图
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        //画苹果
        g.fillOval(appleX*20, appleY*20, 20, 20);
        g.setColor(Color.gray);
        //画贪食蛇
        Snake current = snakeList.first;
        while (current!=null) {
            g.fillRoundRect(current.x*20, current.y*20, 20, 20, 12, 12);
            current = current.next;
        }
        g.drawString("得分："+score, 340, 40);
        g.setColor(Color.black);
        for (int i = 0; i <22; i++) {
            for (int j = 0; j < 22; j++) {
                if(map[i][j]==1){
                    g.fillRect(i*20, j*20, 20, 20);
                }
            }
        }
    };


    class TimerListiner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

//          repaint();
            moving();
            //随着得分增加，提高行进速度，即减少刷新时间
            if(score<50){
                time.setDelay(500);
            }else if (score>=50&&score<100) {
                time.setDelay(400);
            }else if (score>=100&&score<200) {
                time.setDelay(300);
            }else if (score>=200&&score<300) {
                time.setDelay(200);
            }else if (score>=300) {
                time.setDelay(100);
            }
            if (isPause) {
                time.stop();
            }
        }

    }

    //按键上下左右的方法

    public  void down() {
        // TODO Auto-generated method stub
        if (direction==0||direction==1) {
            direction = 2;
            moving();
        }
    }
    public  void up() {
        // TODO Auto-generated method stub
        if (direction==0||direction==1) {
            direction = 3;
            moving();
        }
    }
    public  void left() {
        // TODO Auto-generated method stub
        if (direction==2||direction==3) {
            direction = 0;
            moving();
        }
    }
    public  void right() {
        // TODO Auto-generated method stub
        if (direction==2||direction==3) {
            direction = 1;
            moving();
        }
    }

    //添加按键事件
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
            down();
            break;
        case KeyEvent.VK_LEFT:
            left();
            break;
        case KeyEvent.VK_RIGHT:
            right();
            break;
        case KeyEvent.VK_UP:
            up();
            break;
        case KeyEvent.VK_SPACE:
            time.start();
            isPause = !isPause;
        }
    }



    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

}
