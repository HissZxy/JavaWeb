package 弹球游戏;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*-----------------登陆界面,输入名字--------------------*/
public class Langding {
    private final int FRAME_X =500;
    private final int FRAME_Y =450;
    private final int FRAME_WIDTH =500;
    private final int FRAME_HIGH =500;
    private JFrame f;
    private JButton ok;
    private JPanel p;
    private TextField name;
    private TextField pass;
    private Game g;
    private int score=0;
    /*-------------暂时还没用到这个*/
    //private String PASS_WORD ="";
    private String NAME_WORD ="";

    public void init() throws Exception {
        f = new JFrame("登陆窗口");
        p = new JPanel() {
           protected void paintComponent(Graphics g) {
            ImageIcon icon = new ImageIcon("lib/01.png");
            Image img = icon.getImage();
            Image img1 = img.getScaledInstance(FRAME_WIDTH+300, FRAME_HIGH,Image.SCALE_DEFAULT);
            icon.setImage(img1);
            g.drawImage(img, 0, 0, icon.getIconWidth(),
            icon.getIconHeight(), icon.getImageObserver());
           }
          };
        f.add(p);
        ok =  new JButton("登陆");
        name = new TextField(20);
        //pass = new TextField(20);
        p.add(name);
        //p.add(pass);
        ok.setPreferredSize(new Dimension(100, 100));
        p.add(ok);
        //点击按钮的事件
        Action go = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {    
            DataBaseLoding d = new DataBaseLoding();
                try {
                    //获得输入的名字
                    NAME_WORD = name.getText();
                    //将名字导入到数据库
                    d.initParam("mysql.ini");
                    d.insertName(NAME_WORD);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("玩家名字是"+name.getText());
                //游戏开始
                g = new Game();
                g.init();
            }
        };
        ok.addActionListener(go);
        f.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH+300, FRAME_HIGH);
        f.add(p);
        f.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Langding l = new Langding();
        l.init();
    }
}