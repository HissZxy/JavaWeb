package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject // Plane��̳� GameObject��
{
	public boolean up, down, left, right;
	boolean live = true;

	public void drawItSelf(Graphics g)
	{
		//if (live)
		//{
			if (up)
				y -= speed;
			if (down)
				y += speed;
			if (left)
				x -= speed;
			if (right)
				x += speed;

			g.drawImage(img, (int) x, (int) y, null);
		//} else
		//{
		//	System.out.println("�����˶�");
		//}

	}

	public void KeyDown(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case 38:
			up = true;
			System.out.println(e.getKeyCode() + "������");
			break;
		case 40:
			down = true;
			System.out.println(e.getKeyCode() + "������");
			break;
		case 37:
			left = true;
			System.out.println(e.getKeyCode() + "������");
			break;
		case 39:
			right = true;
			System.out.println(e.getKeyCode() + "������");
			break;
		default:
			System.out.println(e.getKeyCode() + "����Ч��");
		}
	}

	public void KeyUp(KeyEvent e)
	{
		up = down = left = right = false;
		System.out.println(e.getKeyCode() + "����̧��");
	}

	public Plane(Image img, double x, double y, int speed)
	{
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = img.getWidth(null);// ���ͼƬ���
		this.height = img.getHeight(null);// ���ͼƬ�߶�
	}
}
