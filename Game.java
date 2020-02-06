package ������Ϸ;
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



/*----------------��Ϸ���棬������Ϸ------------------*/
public class Game {
    private final int FRAME_X =500;
    private final int FRAME_Y =450;
    private final int FRAME_WIDTH =500;
    private final int FRAME_HIGH =500;
    private final int TABLE_WIDTH = 100;         //���ӿ��
    private final int TABLE_HIGH =20 ;          //���Ӹ߶�
    private final int BALL_SIZE =20;            //��Ĵ�С
    Random rand = new Random();
    private int TABLE_X=rand.nextInt(200);     //���ӵ�����
    private int TABLE_Y=400;
    private int BALL_X=10;                      //С��ĺ�����
    private int BALL_Y=10;                       //С���������
    private int SPEED = 10;                     //С������ٶ�
    Frame f = new Frame("������Ϸ");
    private Mycanvas draw = new Mycanvas();
    private boolean is_X = true;            //Ϊtrue��ʱ�������+
    private boolean is_Y = true;            //Ϊtrue��ʱ��������+
    private int score =0;                   //��Ϸ�ĵ÷�
    private Panel p = new Panel();
    private JTextPane tex = new JTextPane();   //���а���ʾ
    //������̼�����
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
    //�����¼�������

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

                 System.out.println("������");
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
        Font f = new Font("���а������",Font.ITALIC,25);
        tex.setFont(f);
        String first ="����"+"\t"+"\t"+"����"+"\r";
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
        //��800����500
        f.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH+350, FRAME_HIGH);
        //�رմ���
        f.addWindowListener(new WindowAdapter() {                                    //��Ӵ��ڼ�����
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
        f.addKeyListener(keyProcessor);                                             //��Ӽ��̼�����
        draw.addKeyListener(keyProcessor);
        //������500����500
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
            Font font = new Font("����",10,20);
            g.setFont(font);
            g.drawString("������"+score, 380, 20);     
        }
    }
}