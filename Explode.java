package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

public class Explode
{
	double x, y;// ��ը������

	static Image[] imgs = new Image[16];// ʹ��static����ͼ�񱻷�������

	static// ��̬��ʼ����
	{
		for (int i = 0; i < 16; i++)
		{
			imgs[i] = GameUtil.getImage("image/explode/e" + (i + 1) + ".gif");
			imgs[i].getWidth(null);
		}
	}

	int count;// ��ǰ��ʾ��ͼƬ���

	public void Draw(Graphics g)
	{
		if (count <= 15)//�ֲ�16��ͼƬ
		{
			System.out.println("�ֲ�16��ͼƬ");
			g.drawImage(imgs[count], (int) x, (int) y, null);
			count++;
		}
	}

	//������
	public Explode(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
