package myPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * ���س�����
 * 
 * @author My_2
 * 
 */
public class gameFrame extends Frame {
	/**
	 * ���ش���
	 */
	public void lunch() {
		setSize(Constant.Frame_wide, Constant.Frame_high);// ������С��windows�����淽�����̳еõ���
		setLocation(0, 0);// �������Ͻ�λ��
		setVisible(true);// �����ɼ�

		// addWindowListener���������ǵ������Ĵ����ܡ���
		// ����addWindowListener������WindowAdapter���ڷ���addWindowListener���ڲ���
		// new WindowAdapter()Ϊ���������������Ҫ��д����
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			

		});
		new Painthread().start();// �����߳�
	}

	// window��Frame�ĸ��࣬paint��Frame��һ������


	class Painthread extends Thread {//���ش��ڕr������Ӿ��̣�һ���Ӿ͕��{�î����ڣ�ѭ�h�{��paint()
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}


}
