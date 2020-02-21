package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

//子弹
public class Shell extends GameObject
{
	double degree;// 角度
	int xflag = 1;
	int yflag = 1;

	public Shell()
	{
		// 坐标
		x = 100;
		y = 100;

		// 长宽
		width = 10;
		height = 10;

		// 速度
		speed = Constant.SHELL_SPEED;

		// 角度生成
		degree = Math.random() * Math.PI * 2;// 产生一个0~2π之间的随机数
	}

	// 画炮弹
	public void draw(Graphics g)
	{
		Color c = g.getColor();// 保存之前的颜色
		g.setColor(Color.yellow);

		g.fillOval((int) x, (int) y, width, height);// 填充炮弹

		if (x <= 20 || x >= Constant.GAME_WIDTH - 20)
		{
			xflag *= -1;
		}
		if (y <= 20 || y >= Constant.GAME_HEIGHT - 20)
		{
			yflag *= -1;
		}

		x += speed * Math.cos(degree) * xflag;
		y += speed * Math.sin(degree) * yflag;

		g.setColor(c);// 用完之后把颜色还原
	}
}
