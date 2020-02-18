package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

public class Explode
{
	double x, y;// 爆炸的坐标

	static Image[] imgs = new Image[16];// 使用static避免图像被反复加载

	static// 静态初始化块
	{
		for (int i = 0; i < 16; i++)
		{
			imgs[i] = GameUtil.getImage("image/explode/e" + (i + 1) + ".gif");
			imgs[i].getWidth(null);
		}
	}

	int count;// 当前显示的图片序号

	public void Draw(Graphics g)
	{
		if (count <= 15)//轮播16张图片
		{
			System.out.println("轮播16张图片");
			g.drawImage(imgs[count], (int) x, (int) y, null);
			count++;
		}
	}

	//构造器
	public Explode(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
