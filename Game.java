package 弹球游戏;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JTextPane;
import javax.swing.Timer;



/*----------------游戏界面，弹球游戏------------------*/
public class Game {
    private final int FRAME_X =500;
    private final int FRAME_Y =450;
    private final int FRAME_WIDTH =500;
    private final int FRAME_HIGH =500;
    private final int TABLE_WIDTH = 100;         //板子宽度
    private final int TABLE_HIGH =20 ;          //板子高度
    private final int BALL_SIZE =20;            //球的大小
    Random rand = new Random();
    private int TABLE_X=rand.nextInt(200);     //板子的坐标
    private int TABLE_Y=400;
    private int BALL_X=10;                      //小球的横坐标
    private int BALL_Y=10;                       //小球的纵坐标
    private int SPEED = 10;                     //小球横向速度
    Frame f = new Frame("弹球游戏");
    private Mycanvas draw = new Mycanvas();
    private boolean is_X = true;            //为true的时候横坐标+
    private boolean is_Y = true;            //为true的时候纵坐标+
    private int score =0;                   //游戏的得分
    private Panel p = new Panel();
    private JTextPane tex = new JTextPane();   //排行榜显示
    //定义键盘监听器
    KeyAdapter keyProcessor = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_LEFT&&TABLE_X>0) {
                TABLE_X -=10;
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT&&TABLE_X<370){
                TABLE_X +=10;
            }
            if(e.getKeyCode()==KeyEvent.VK_UP&&TABLE_Y>0) {
                TABLE_Y -=10;
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN&&TABLE_Y<430) {
                TABLE_Y +=10;
            }
        }
    };
    //定义事件监听器

    ActionListener task = new ActionListener (){
         public void actionPerformed(ActionEvent e) {

             if(BALL_X>FRAME_X||BALL_Y>FRAME_Y&&BALL_Y>TABLE_Y-15) {

                 try {
                    DataBaseLoding d = new DataBaseLoding();
                    d.initParam("mysql.ini");
                    d.insertScore(score);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                 System.out.println("结束了");
                 timer.stop();
             }
             if(is_X) {
                 BALL_X +=10;
             }else {
                 BALL_X -=10;
             }
             if(BALL_X>440){
                is_X=false; 
             }
             if(BALL_X<10) {
                 is_X=true;

             }

             if(BALL_Y<0) {
                 is_Y =true;
             }
             if(is_Y) {
                 BALL_Y+=10;

             }else {
                 BALL_Y-=10;
             }

             if(BALL_Y==TABLE_Y-10&&BALL_X>TABLE_X&&BALL_X<TABLE_X+100) {
                 is_Y=false;
             }
             score++;
            draw.repaint();
         }
    };
    Timer timer = new Timer(100,task);
    public void text() {
        Font f = new Font("排行榜的字体",Font.ITALIC,25);
        tex.setFont(f);
        String first ="名次"+"\t"+"\t"+"分数"+"\r";
        try {
            DataBaseLoding d = new DataBaseLoding();
            d.initParam("mysql.ini");
            first +=d.select();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        tex.setText(first);
        p.add(tex);
    }
    public void init() {
        f.add(draw);
        text();
        f.add(p, BorderLayout.EAST);
        //长800，高500
        f.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH+350, FRAME_HIGH);
        //关闭窗口
        f.addWindowListener(new WindowAdapter() {                                    //添加窗口监听器
            public void windowClosing(WindowEvent e) {
                try {
                    DataBaseLoding d = new DataBaseLoding();
                    d.initParam("mysql.ini");
                    d.insertScore(score);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        f.addKeyListener(keyProcessor);                                             //添加键盘监听器
        draw.addKeyListener(keyProcessor);
        //画布长500，高500
        draw.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HIGH));
        timer.start();
        f.setVisible(true);
    }
    class Mycanvas extends Canvas{
        public void paint(Graphics g) {
            g.setColor(new Color(0, 0, 0));
            g.drawLine(0, FRAME_Y, FRAME_X-20, FRAME_Y);
            g.drawLine(FRAME_X-25, 0, FRAME_X-25, FRAME_Y);
            g.drawLine(0, 0, 0, FRAME_Y);
            g.setColor(new Color(0,0,0));
            g.fillRect(TABLE_X,TABLE_Y , TABLE_WIDTH, TABLE_HIGH);
            g.setColor(new Color(255,175,175));
            g.fillRect(BALL_X, BALL_Y, BALL_SIZE, BALL_SIZE);
            Font font = new Font("分数",10,20);
            g.setFont(font);
            g.drawString("分数："+score, 380, 20);     
        }
    }
}