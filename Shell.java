package cn.sxt.game;

import java.awt.Color;
import java.awt.Graphics;

//�ӵ�
public class Shell extends GameObject
{
	double degree;// �Ƕ�
	int xflag = 1;
	int yflag = 1;

	public Shell()
	{
		// ����
		x = 100;
		y = 100;

		// ����
		width = 10;
		height = 10;

		// �ٶ�
		speed = Constant.SHELL_SPEED;

		// �Ƕ�����
		degree = Math.random() * Math.PI * 2;// ����һ��0~2��֮��������
	}

	// ���ڵ�
	public void draw(Graphics g)
	{
		Color c = g.getColor();// ����֮ǰ����ɫ
		g.setColor(Color.yellow);

		g.fillOval((int) x, (int) y, width, height);// ����ڵ�

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

		g.setColor(c);// ����֮�����ɫ��ԭ
	}
}
