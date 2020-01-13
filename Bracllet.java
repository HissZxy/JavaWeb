package myPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bracllet {
	
	double x=Constant.Frame_wide/2,y=Constant.Frame_high/2,degree,speed;
	public Bracllet(double speed){
		this.speed=speed;
		degree=Math.random()*Math.PI*2;
		
	}
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, 10, 10);
	}

	
	public void draw(Graphics g){
		x+=this.speed*Math.cos(degree);
		y+=this.speed*Math.sin(degree);
		
		//横纵边栏目宽8,30
		if(x<8||x>(Constant.Frame_wide)-18)//30表示标题栏，看不见
		{
			System.out.println((Constant.Frame_wide/2)-10+"x="+x);
			this.degree=Math.PI-this.degree;
		}
		if(y<30||y>(Constant.Frame_high)-30)//30表示标题栏，看不见
		{
			this.degree=-this.degree;
			System.out.println("y="+y);
		}
		Color c=g.getColor();
		g.setColor(Color.magenta);
		g.fillOval((int)x, (int)y, 10, 10);
		g.setColor(c);		

	}

}
