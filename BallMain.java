import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/*��������*/
public class BallMain extends JFrame{
	//����Ŀ��
	public static final int SCREEN_WIDTH = 1360;
	public static final int SCREEN_HEIGHT = 760;
	
	//ȫ��
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)d.getWidth();
    int height = (int)d.getHeight();
	
	public BallMain(){
		this.setTitle("V1.0");
		//����λ��
		this.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		//���С�򵽴���
		BallJPanel bj = new BallJPanel();
		this.add(bj);
		
		//��Ӽ��̵ļ����¼�
		this.addKeyListener(bj);
		
		/*frame.addKeyListener(tj);
		tj.addKeyListener(tj);
		*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	public static void main(String[] args) {
		BallMain b = new BallMain();
		//�������
				try {
					FileInputStream f =new FileInputStream("music/yyyy.wav");
					AudioStream as = new AudioStream(f);
					AudioPlayer.player.start(as);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
			
					e.printStackTrace();
				}
	}
}