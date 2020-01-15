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
 * 加载出窗口
 * 
 * @author My_2
 * 
 */
public class gameFrame extends Frame {
	/**
	 * 加载窗口
	 */
	public void lunch() {
		setSize(Constant.Frame_wide, Constant.Frame_high);// 画布大小，windows类下面方法，继承得到的
		setLocation(0, 0);// 画布左上角位置
		setVisible(true);// 画布可见

		// addWindowListener方法作用是调出来的窗口能×掉
		// 调用addWindowListener方法，WindowAdapter属于方法addWindowListener的内部类
		// new WindowAdapter()为创建对象，这个对象要重写方法
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			

		});
		new Painthread().start();// 启动线程
	}

	// window是Frame的父类，paint是Frame的一个方法


	class Painthread extends Thread {//加载窗口時候會啟動線程，一啟動就會調用畫窗口，循環調用paint()
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
