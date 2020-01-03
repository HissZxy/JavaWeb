import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//�����࣬�̳�JFrame
public class SnakeGame extends JFrame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SnakeGame snakeFrame = new SnakeGame();
        MyJpanel myJpanel = new MyJpanel();
        snakeFrame.addKeyListener(myJpanel);
        snakeFrame.add(myJpanel);
        JMenuBar jb = new JMenuBar();       //�����˵�����������Ӳ˵���
        JMenu game = new JMenu("��Ϸ");
        JMenu help = new JMenu("����");
        JMenuItem newGameItem = new JMenuItem("����Ϸ");
        JMenuItem stopItem = new JMenuItem("��ͣ");
        JMenuItem goonItem =  new JMenuItem("����");
        JMenuItem exitItem = new JMenuItem("�˳�");
        JMenuItem about = new JMenuItem("����");
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

        //���˵�����¼�����
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
