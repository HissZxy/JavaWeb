package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


//������Ϸ������ĳ������ԣ�����̳�������༴��
public class GameObject
{
	Image img;	//ͼ��·������
	double x,y;	//λ������
	int speed;	//�ٶ�
	int width,height;	//���
	
	//�����Լ�
	public void drawItSelf(Graphics g)
	{
		g.drawImage(img, (int)x, (int)y, null);
	}
	
	//������������ײ��� �����������ھ���
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,width,height);
	}

	//������
	public GameObject(Image img, double x, double y, int speed, int width, int height)
	{
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	//���صĹ����� �������
	public GameObject(Image img, double x, double y)
	{
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	//�޲ι�����
	public GameObject()
	{
		super();
	}
}
