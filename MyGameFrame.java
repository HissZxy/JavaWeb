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

	// 双缓冲 解决闪烁问题
	private Image offScreenImage = null;

	public void update(Graphics g)
	{
		if (offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// 这是游戏窗口的宽度和高度
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
	int period;// 持续时间

	@Override
	public void paint(Graphics g)// 自动被调用，g相当于一支画笔
	{
		super.paint(g);

		g.drawImage(bg, 0, 0, null);// 画背景

		plane.drawItSelf(g);// 画飞机
		// 画炮弹
		for (int i = 0; i < Constant.SHELL_NUM; i++)
		{
			shell[i].draw(g);

			// 检测是否飞机和炮弹相接触
			boolean touch = shell[i].getRect().intersects(plane.getRect());
			if (touch)
			{
				Constant.LIFE--;

				plane.live = false;
				explode.x = plane.x;
				explode.y = plane.y;

				System.out.println("碰到了！");
				explode.count %= 16;
				explode.Draw(g);

				endTime = new Date();
				period = (int) (endTime.getTime() - startTime.getTime()) / 1000;// 计算时间差

				Font f = new Font("宋体", Font.BOLD, 20);
				g.setFont(f);
				g.setColor(Color.WHITE);
				g.drawString("(" + period + "秒)剩余生命值：" + Constant.LIFE, (int) plane.x, (int) plane.y);
			}
		}
	}

	// 键盘监听
	class KeyMonitor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)// 按下键
		{
			// TODO Auto-generated method stub
			super.keyPressed(e);// 38按向上 40按向下 37按向左 39按向右
			plane.KeyDown(e);
		}

		@Override
		public void keyReleased(KeyEvent e)// 抬起键
		{
			// TODO Auto-generated method stub
			super.keyReleased(e);
			plane.KeyUp(e);
		}
	}

	// 多线程的使用 反复重画窗口，当经常使用一个类时，可以把调用它放入类作为被调用类的内部类，这样方便调用
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

	// 初始化窗口函数
	public void launchFrame()
	{
		this.setTitle("魔鬼鱼");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// 窗口大小 这里有何神奇的bug 垮外界屏幕的时候窗口无法缩小
		this.setLocation(500, 500);// 窗口位置

		// 匿名内部类 安卓编程到处都是 用来在关闭窗口之后结束程序
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);// 0表示正常结束 负数表示异常结束
			}
		});

		new PaintThread().start();// 启动重画窗口的线程

		addKeyListener(new KeyMonitor());// 添加键盘监听
	}

	public static void main(String[] args)// 使用alt / 组合件可以快速补全
	{
		MyGameFrame fr = new MyGameFrame();
		fr.launchFrame();
	}
}
