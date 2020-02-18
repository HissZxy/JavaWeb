package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


//包含游戏中物体的常用属性，物体继承于这个类即可
public class GameObject
{
	Image img;	//图像路径名称
	double x,y;	//位置坐标
	int speed;	//速度
	int width,height;	//宽高
	
	//画出自己
	public void drawItSelf(Graphics g)
	{
		g.drawImage(img, (int)x, (int)y, null);
	}
	
	//矩形用于做碰撞检测 返回物体所在矩形
	public Rectangle getRect()
	{
		return new Rectangle((int)x,(int)y,width,height);
	}

	//构造器
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
	
	//重载的构造器 方便调用
	public GameObject(Image img, double x, double y)
	{
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	//无参构造器
	public GameObject()
	{
		super();
	}
}
