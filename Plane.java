package myPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Plane {

	String imagepath;
	double x,y;
	Image Img;
	boolean up,down,right,left,live=true;
	public Plane(String imagepath, double x, double y) {
		super();
		this.imagepath = imagepath;
		this.x = x;
		this.y = y;
		Img=GameFunc.getImage(imagepath);
	}
	
	public void move(){
//		上：38，下：40
//		左：37    右：39		
		if(left)
			this.x-=20;
		if(up)
			this.y-=20;
		if(right)
			this.x+=20;
		if(down)
			this.y+=20;
		if(x<=8){
			x=8;
		}
		if(y<=30){
			y=30;
		}
		if(x>=Constant.Frame_wide-Img.getWidth(null)){
			x=Constant.Frame_wide-Img.getWidth(null);
		}
		if(y>=Constant.Frame_high-Img.getWidth(null)){
			y=Constant.Frame_high-Img.getWidth(null);
		}
		
	}
	
	public void press_dicraytion(KeyEvent e){
		switch (e.getKeyCode()) {
//		上：38，下：40
//		左：37    右：39
		
		case 37:
			left=true;
			break;
		case 38:
			up=true;
			break;
		case 39:
			right=true;
			break;
		case 40:
			down=true;
			break;
		default:
			break;
		}
		
	}
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	public void release_dicraytion(KeyEvent e){
		switch (e.getKeyCode()) {
//		上：38，下：40
//		左：37    右：39
		
		case 37:
			left=false;
			break;
		case 38:
			up=false;
			break;
		case 39:
			right=false;
			break;
		case 40:
			down=false;
			break;
		default:
			break;
		}
		
	}
	
	public void draw(Graphics g){
		if(live)
		{
			move();
		}
		
		Color c=g.getColor();
		g.setColor(Color.blue);
		g.fillOval((int)x, (int)y, 20, 20);
		g.setColor(c);
	}
	
}
