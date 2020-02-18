package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

public class MyGameFrame extends Frame
{

	// ˫���� �����˸����
	private Image offScreenImage = null;

	public void update(Graphics g)
	{
		if (offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// ������Ϸ���ڵĿ�Ⱥ͸߶�
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	Image bg = GameUtil.getImage("image/bg.jpg");
	Image planeImg = GameUtil.getImage("image/plane.png");
	Plane plane = new Plane(planeImg, 250, 250, Constant.PLANE_SPEED);
	Shell shell[] = new Shell[Constant.SHELL_NUM];
	{
		for (int i = 0; i < Constant.SHELL_NUM; i++)
		{
			shell[i] = new Shell();
		}
	}

	Explode explode = new Explode(0, 0);
	Date startTime = new Date();
	Date endTime;
	int period;// ����ʱ��

	@Override
	public void paint(Graphics g)// �Զ������ã�g�൱��һ֧����
	{
		super.paint(g);

		g.drawImage(bg, 0, 0, null);// ������

		plane.drawItSelf(g);// ���ɻ�
		// ���ڵ�
		for (int i = 0; i < Constant.SHELL_NUM; i++)
		{
			shell[i].draw(g);

			// ����Ƿ�ɻ����ڵ���Ӵ�
			boolean touch = shell[i].getRect().intersects(plane.getRect());
			if (touch)
			{
				Constant.LIFE--;

				plane.live = false;
				explode.x = plane.x;
				explode.y = plane.y;

				System.out.println("�����ˣ�");
				explode.count %= 16;
				explode.Draw(g);

				endTime = new Date();
				period = (int) (endTime.getTime() - startTime.getTime()) / 1000;// ����ʱ���

				Font f = new Font("����", Font.BOLD, 20);
				g.setFont(f);
				g.setColor(Color.WHITE);
				g.drawString("(" + period + "��)ʣ������ֵ��" + Constant.LIFE, (int) plane.x, (int) plane.y);
			}
		}
	}

	// ���̼���
	class KeyMonitor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)// ���¼�
		{
			// TODO Auto-generated method stub
			super.keyPressed(e);// 38������ 40������ 37������ 39������
			plane.KeyDown(e);
		}

		@Override
		public void keyReleased(KeyEvent e)// ̧���
		{
			// TODO Auto-generated method stub
			super.keyReleased(e);
			plane.KeyUp(e);
		}
	}

	// ���̵߳�ʹ�� �����ػ����ڣ�������ʹ��һ����ʱ�����԰ѵ�������������Ϊ����������ڲ��࣬�����������
	class PaintThread extends Thread
	{
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			super.run();
			while (true)
			{
				repaint();
				try
				{
					Thread.sleep(Constant.SLEEP);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// ��ʼ�����ں���
	public void launchFrame()
	{
		this.setTitle("ħ����");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// ���ڴ�С �����к������bug �������Ļ��ʱ�򴰿��޷���С
		this.setLocation(500, 500);// ����λ��

		// �����ڲ��� ��׿��̵������� �����ڹرմ���֮���������
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);// 0��ʾ�������� ������ʾ�쳣����
			}
		});

		new PaintThread().start();// �����ػ����ڵ��߳�

		addKeyListener(new KeyMonitor());// ��Ӽ��̼���
	}

	public static void main(String[] args)// ʹ��alt / ��ϼ����Կ��ٲ�ȫ
	{
		MyGameFrame fr = new MyGameFrame();
		fr.launchFrame();
	}
}
