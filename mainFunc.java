package myPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

public class mainFunc extends gameFrame{
	Date date_start;
	Date date_end;
	
	Plane plane=new Plane("Image/mu.png",80,80);
	ArrayList braclletList=new ArrayList();
	int braclletNum;//子弹数
	double speed;
	public void paint(Graphics g){		
		plane.draw(g);
		frintinfor(g,"宋体","等级：",20,Color.blue,Constant.Frame_wide-160,80);
		frintinfor(g,"宋体","0-10秒：辣鸡",15,Color.blue,Constant.Frame_wide-160,100);
		frintinfor(g,"宋体","10-20秒：菜鸡",15,Color.blue,Constant.Frame_wide-160,115);
		frintinfor(g,"宋体","20-30秒：菜逼",15,Color.blue,Constant.Frame_wide-160,130);
		frintinfor(g,"宋体","30秒以上：优秀",15,Color.blue,Constant.Frame_wide-160,145);
		for(int i=0;i<braclletNum;i++)
		{
			Bracllet bracllet1=(Bracllet)braclletList.get(i);
			bracllet1.draw(g);
			boolean peng=plane.getRectangle().intersects(bracllet1.getRectangle());
			if(peng&&plane.live){
				plane.live=false;
				date_end=new Date();
			}
			if(plane.live==false)
			{
				int time=((int)(date_end.getTime()-date_start.getTime()))/1000;
				switch (time/10) {
				case 0:
					frintinfor(g,"宋体","等级：辣鸡！",50,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2-100);
					frintinfor(g,"宋体","    坚持了"+time+"秒",30,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2+60-100);
					break;
				case 1:
					frintinfor(g,"宋体","等级：菜鸡",50,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2-100);
					frintinfor(g,"宋体","    坚持了"+time,30,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2+60-100);
					break;
				case 2:
					frintinfor(g,"宋体","等级：菜逼",50,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2-100);
					frintinfor(g,"宋体","    坚持了"+time,30,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2+60-100);
					break;	
					
					
				default:
					frintinfor(g,"宋体","等级：优秀",50,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2-100);
					frintinfor(g,"宋体","    坚持了"+time,30,Color.red,Constant.Frame_high/2-100,Constant.Frame_wide/2+60-100);
					break;
				}
				
			}
		}
	}
		
	public void frintinfor(Graphics g,String s1,String s2,int fontsize,Color cc,int gameover_locationx,int gameover_locationy){
		Font font=new Font(s1,Font.BOLD,fontsize);
		Color c=g.getColor();
		g.setColor(cc);
		g.setFont(font);
		g.drawString(s2, gameover_locationx, gameover_locationy);
		g.setColor(c);
		
	}
	@Override
	public void lunch() {
		
		super.lunch();
		date_start=new Date();
		addKeyListener(new KeyMonitor());
		for(int i=0;i<braclletNum;i++)
		{
			Bracllet bracllet1=new Bracllet(speed);
			braclletList.add(bracllet1);
		}
		
	}

public mainFunc(int braclletNum,double speed){
	this.braclletNum=braclletNum;
	this.speed=speed;
}

	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.press_dicraytion(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.release_dicraytion(e);

		}				
		
	}
	public static void main(String[] args) {
		new mainFunc(25,10).lunch();//点数，点速度
	}
}
