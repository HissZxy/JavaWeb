/**
 * @(#)HitPigHead.java
 *
 *
 * @author
 * @version 1.00 2012/6/4
 */
 
import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;
 
abstract class SuperSprite
{
	int X,Y,width,height;
	boolean visible,active;
 
	abstract public void paintSprite(Graphics g);
	abstract public void updateState();
 
	public int getX()
	{
		return X;
	}
 
    public int getY()
	{
		return Y;
	}
 
    public void setLocation(int X,int Y)
   {
   	   this.X=X;
   	   this.Y=Y;
 
    }
 
    public int getWidth()
    {
    	 return width;
    }
 
    public int getHeight()
    {
    	return height;
    }
 
    public void setSize(int width,int height)
    {
    	this.width=width;
    	this.height=height;
    }
 
    public boolean canVisible()
    {
    	return visible;
    }
 
    public void setVisible(boolean v)
    {
    	visible=v;
    }
 
    public boolean canMove()
    {
    	return active;
    }
 
    public void setMove(boolean m)
    {
    	active=m;
    }
}
 
class PigSprite extends SuperSprite    //猪头Sprite
{
	int seed;             //产生随机数
	Image SpriteImage,Frame;      //Sprite本身的图像
	Applet Game;          //在绘制图像时会用到
	Random R;
	boolean showPig;      //绘制猪头图像
 
 
public PigSprite(Image SpriteImage,Image Frame,Applet Game)
{
	R=new Random();
 
	this.SpriteImage=SpriteImage;
	this.Frame =Frame;
	this.Game=Game;
	showPig=false;
 
	setVisible(true);    //可见
	setMove(true);       //可移动
 
}
 
public void updateState()
{
	if(active==true)
	{
		//转换猪头图像出现与消失的状态
		if(R.nextInt(seed)%100<1)
		{
			if(showPig==false)
				showPig=true;
		}
		else if(R.nextInt(seed)%100 > 95)
		{
			if(showPig==true)
				showPig=false;
		}
	}
 
}
 
 
public void paintSprite(Graphics g)    //绘制猪头Sprite
{
	if(visible == true)
	{
 
		g.drawImage(Frame,X,Y,Game);    //在最后一个参数中输入Applet
 
		 if(showPig == true)
		 	g.drawImage(SpriteImage,X+12,Y+18,Game);
      }
}
 
 
public void setSeed(int seed)
{
	this.seed=seed;
}
 
//测试是否击中猪头
public boolean hit(int X,int Y,int P_Width,int P_Height,int H_Width,int H_Height)
{
	if((this.X+P_Width>=X) && (this.Y+(P_Height/2)>=Y)
		&& (X+(H_Width/2)>=this.X) && (Y+(H_Height/2)>=this.Y) && showPig)
		{
			showPig=false;
			return true;
		}
		else
			return false;
}
}
 
class HammerSprite extends SuperSprite   //铁锤Sprite
{
 
	Image hammer1,hammer2,currentImage;      //铁锤图像
	Applet Game;          //在绘制图像时会用到
 
 
 
public HammerSprite(Image hammer1,Image hammer2,Applet Game)
{
 
 
	this.hammer1=hammer1;
	this.hammer2=hammer2;
	this.Game=Game;
 
	currentImage=hammer1;
 
	setLocation(0,0);
	setVisible(false);    //不可见
	setMove(false);       //不可移动
 
}
 
public void updateState()
{
	//转换铁锤图像
	if(currentImage==hammer1)
		currentImage=hammer2;
		else
			currentImage=hammer1;
}
 
 
public void paintSprite(Graphics g)    //绘制铁锤Sprite
{
	if(visible == true)
		g.drawImage(currentImage,X,Y,Game);    //在最后一个参数中输入Applet
 
 
}
}
 
 
 
public class HitPigHead extends Applet      //定义游戏主类
              implements Runnable,MouseListener,MouseMotionListener
 {
 	int AppletWidth,AppletHeight,FrameWidth,FrameHeight,countX,countY,HammerWidth,HammerHeight,score;
 	Image frame,pig,hammer1,hammer2,OffScreen;
 	Thread newThread;
 	Graphics drawOffScreen;
 	MediaTracker MT;
 
 	PigSprite pigSprite [];
 	HammerSprite hammerSprite;
 
 	public void init()
 	{
 		addMouseListener(this);        //注册时间处理方法
 		addMouseMotionListener(this);
 
 		AppletWidth = getSize().width;
 		AppletHeight = getSize().height;
 
 		countX=3;         //X轴3个猪头texture
 		countY=3;         //Y轴3个猪头texture
 		score=0;          //记录分数
 
 		//使用MediaTracker追踪图像
 		MT = new MediaTracker(this);
 		pig = getImage(getDocumentBase(),"Images/pig1.gif");
 		frame = getImage(getDocumentBase(),"Images/frame1.gif");
 		hammer1 = getImage(getDocumentBase(),"Images/hammer1.gif");
 		hammer2= getImage(getDocumentBase(),"Images/hammer2.gif");
 		MT.addImage(pig,0);
 		MT.addImage(frame,0);
 		MT.addImage(hammer1,0);
 		MT.addImage(hammer2,0);
 
 		try
 		{
 			MT.waitForAll();
 		}
 		catch(InterruptedException E)
 			{
 		}           //没有进行异常处理
 
 		FrameWidth=frame.getWidth(this);     //猪头texture的宽度
 		FrameHeight=frame.getHeight(this);     //猪头texture的高度
 
 
 		pigSprite =new PigSprite[9];      //使用9个猪头图像（3*3）
 		for (int i=0;i<9;i++)
 		{
 			//建立猪头Sprite
  			pigSprite[i]=new PigSprite(pig,frame,this);
 			//设定位置
 			pigSprite[i].setLocation(i%countX*FrameWidth,i/countY*FrameHeight);
 			pigSprite[i].setSeed(i+100);     //设定随机数范围
 
 		}
 
 		//建立铁锤Sprite
 		hammerSprite=new HammerSprite(hammer1,hammer2,this);
 		HammerWidth=hammer1.getWidth(this);    //铁锤图像宽度
 		HammerHeight=hammer1.getHeight(this);   //铁锤图像高度
 
 
 		//建立子画面
 		OffScreen=createImage(AppletWidth,AppletHeight);
 		drawOffScreen=OffScreen.getGraphics();
 
 		resize(FrameWidth*countX,FrameHeight*countY);
 
 	}
 
 	public void start()             //start()方法
 	{
 		newThread=new Thread(this);    //建立与启动新线程
 		newThread.start();
 	}
 
 	public void stop()            //stop()方法
 	{
 		newThread=null;         //将线程设为null
 	}
 
 	public void paint(Graphics g)
 	{
 		//只清除此部分区域的图像
 		drawOffScreen.clearRect(0,0,AppletWidth,AppletHeight);
 
 		//绘制猪头Sprite
 		for (int i=0;i<9;i++)
 			pigSprite[i].paintSprite(drawOffScreen);
 
 			//绘制铁锤Sprite
 
 			hammerSprite.paintSprite(drawOffScreen);
 			//将子画面贴到主画面上
 			g.drawImage(OffScreen,0,0,this);
 
 	}
 
 	public void update(Graphics g)     //update()方法
 	{
 		paint(g);
 	}
 
 	public void run()
 	{
 		while(newThread!=null)
 		{
 			repaint();        //重绘图像
 			try
 			{
 				Thread.sleep(33);    //暂停33毫秒
 			}
 			catch(InterruptedException E){}
 
 			for(int i=0;i<9;i++)            //转换猪头Sprite
 			pigSprite[i].updateState();
 
 		}
 	}
 
 	//==========实现MouseListener界面======================
 	public void mouseExited(MouseEvent e)            //鼠标离开Component
 	{
 		hammerSprite.setVisible(false);             //不绘制铁锤Sprite
 		hammerSprite.setLocation(0,0);              //归位于原点
 
 	}
 
 	public void mouseClicked(MouseEvent e){	}      //鼠标按键被按下后放开
 
 	public void mouseEntered(MouseEvent e)     //鼠标进入Component
 	{
 		hammerSprite.setVisible(true);           //绘制铁锤Sprite
 		hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
 
 	}
 
 	public void mousePressed(MouseEvent e)      //鼠标按键被按下
 	{
 		hammerSprite.updateState();         //转换铁锤Sprite
 
 
 		int X=hammerSprite.getX();          //取得铁锤Sprite的X坐标
 		int Y=hammerSprite.getY();           //取得铁锤Sprite的Y坐标
 
 
 
 	//测试是否击中猪头sprite
 	for (int i=0;i<9;i++)
 	{
 		//如果击中则增加分数并将分数显示在状态列
 		if(pigSprite[i].hit(X,Y,FrameWidth,FrameHeight,HammerWidth,HammerHeight)==true)
 		{
 			score=score+10;
 			showStatus("目前得分："+score);
 		}
 		}
 	}
 
 
public void mouseReleased(MouseEvent e)          //鼠标按键放开
{
	hammerSprite.updateState();                 //转换铁锤Sprite
}
 
 
//=================实现MouseMotionListener界面================
public void mouseMoved(MouseEvent e)         //鼠标移动时
{
	//将鼠标光标置于铁锤Sprite中央
	hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
 
}
 
public void mouseDragged(MouseEvent e)         //鼠标拖曳时
{
	//将鼠标光标置于铁锤Sprite中央
	hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
}
}