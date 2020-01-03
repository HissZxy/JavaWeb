import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//定义类，继承JFrame
public class SnakeGame extends JFrame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SnakeGame snakeFrame = new SnakeGame();
        MyJpanel myJpanel = new MyJpanel();
        snakeFrame.addKeyListener(myJpanel);
        snakeFrame.add(myJpanel);
        JMenuBar jb = new JMenuBar();       //创建菜单栏，以下添加菜单项
        JMenu game = new JMenu("游戏");
        JMenu help = new JMenu("帮助");
        JMenuItem newGameItem = new JMenuItem("新游戏");
        JMenuItem stopItem = new JMenuItem("暂停");
        JMenuItem goonItem =  new JMenuItem("继续");
        JMenuItem exitItem = new JMenuItem("退出");
        JMenuItem about = new JMenuItem("关于");
        game.add(newGameItem);
        game.add(stopItem);
        game.add(goonItem);
        game.add(exitItem);
        help.add(about);
        jb.add(game);
        jb.add(help);
        snakeFrame.setJMenuBar(jb);
        snakeFrame.setSize(448, 492);
        snakeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        snakeFrame.setVisible(true);
        snakeFrame.setResizable(false);

        //各菜单添加事件侦听
        newGameItem.addActionListener(e->{
            myJpanel.newGame();
            myJpanel.createApple();
            myJpanel.direction = 2;
            myJpanel.score = 0;
        });
        exitItem.addActionListener(e->{
            System.exit(0);
        });
        stopItem.addActionListener(e->{
            myJpanel.time.stop();
        });
        goonItem.addActionListener(e->{
            myJpanel.time.start();
        });
        about.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "made by phil_chow");
        });
    }

}
