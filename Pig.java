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
 
class PigSprite extends SuperSprite    //��ͷSprite
{
	int seed;             //���������
	Image SpriteImage,Frame;      //Sprite�����ͼ��
	Applet Game;          //�ڻ���ͼ��ʱ���õ�
	Random R;
	boolean showPig;      //������ͷͼ��
 
 
public PigSprite(Image SpriteImage,Image Frame,Applet Game)
{
	R=new Random();
 
	this.SpriteImage=SpriteImage;
	this.Frame =Frame;
	this.Game=Game;
	showPig=false;
 
	setVisible(true);    //�ɼ�
	setMove(true);       //���ƶ�
 
}
 
public void updateState()
{
	if(active==true)
	{
		//ת����ͷͼ���������ʧ��״̬
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
 
 
public void paintSprite(Graphics g)    //������ͷSprite
{
	if(visible == true)
	{
 
		g.drawImage(Frame,X,Y,Game);    //�����һ������������Applet
 
		 if(showPig == true)
		 	g.drawImage(SpriteImage,X+12,Y+18,Game);
      }
}
 
 
public void setSeed(int seed)
{
	this.seed=seed;
}
 
//�����Ƿ������ͷ
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
 
class HammerSprite extends SuperSprite   //����Sprite
{
 
	Image hammer1,hammer2,currentImage;      //����ͼ��
	Applet Game;          //�ڻ���ͼ��ʱ���õ�
 
 
 
public HammerSprite(Image hammer1,Image hammer2,Applet Game)
{
 
 
	this.hammer1=hammer1;
	this.hammer2=hammer2;
	this.Game=Game;
 
	currentImage=hammer1;
 
	setLocation(0,0);
	setVisible(false);    //���ɼ�
	setMove(false);       //�����ƶ�
 
}
 
public void updateState()
{
	//ת������ͼ��
	if(currentImage==hammer1)
		currentImage=hammer2;
		else
			currentImage=hammer1;
}
 
 
public void paintSprite(Graphics g)    //��������Sprite
{
	if(visible == true)
		g.drawImage(currentImage,X,Y,Game);    //�����һ������������Applet
 
 
}
}
 
 
 
public class HitPigHead extends Applet      //������Ϸ����
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
 		addMouseListener(this);        //ע��ʱ�䴦����
 		addMouseMotionListener(this);
 
 		AppletWidth = getSize().width;
 		AppletHeight = getSize().height;
 
 		countX=3;         //X��3����ͷtexture
 		countY=3;         //Y��3����ͷtexture
 		score=0;          //��¼����
 
 		//ʹ��MediaTracker׷��ͼ��
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
 		}           //û�н����쳣����
 
 		FrameWidth=frame.getWidth(this);     //��ͷtexture�Ŀ��
 		FrameHeight=frame.getHeight(this);     //��ͷtexture�ĸ߶�
 
 
 		pigSprite =new PigSprite[9];      //ʹ��9����ͷͼ��3*3��
 		for (int i=0;i<9;i++)
 		{
 			//������ͷSprite
  			pigSprite[i]=new PigSprite(pig,frame,this);
 			//�趨λ��
 			pigSprite[i].setLocation(i%countX*FrameWidth,i/countY*FrameHeight);
 			pigSprite[i].setSeed(i+100);     //�趨�������Χ
 
 		}
 
 		//��������Sprite
 		hammerSprite=new HammerSprite(hammer1,hammer2,this);
 		HammerWidth=hammer1.getWidth(this);    //����ͼ����
 		HammerHeight=hammer1.getHeight(this);   //����ͼ��߶�
 
 
 		//�����ӻ���
 		OffScreen=createImage(AppletWidth,AppletHeight);
 		drawOffScreen=OffScreen.getGraphics();
 
 		resize(FrameWidth*countX,FrameHeight*countY);
 
 	}
 
 	public void start()             //start()����
 	{
 		newThread=new Thread(this);    //�������������߳�
 		newThread.start();
 	}
 
 	public void stop()            //stop()����
 	{
 		newThread=null;         //���߳���Ϊnull
 	}
 
 	public void paint(Graphics g)
 	{
 		//ֻ����˲��������ͼ��
 		drawOffScreen.clearRect(0,0,AppletWidth,AppletHeight);
 
 		//������ͷSprite
 		for (int i=0;i<9;i++)
 			pigSprite[i].paintSprite(drawOffScreen);
 
 			//��������Sprite
 
 			hammerSprite.paintSprite(drawOffScreen);
 			//���ӻ���������������
 			g.drawImage(OffScreen,0,0,this);
 
 	}
 
 	public void update(Graphics g)     //update()����
 	{
 		paint(g);
 	}
 
 	public void run()
 	{
 		while(newThread!=null)
 		{
 			repaint();        //�ػ�ͼ��
 			try
 			{
 				Thread.sleep(33);    //��ͣ33����
 			}
 			catch(InterruptedException E){}
 
 			for(int i=0;i<9;i++)            //ת����ͷSprite
 			pigSprite[i].updateState();
 
 		}
 	}
 
 	//==========ʵ��MouseListener����======================
 	public void mouseExited(MouseEvent e)            //����뿪Component
 	{
 		hammerSprite.setVisible(false);             //����������Sprite
 		hammerSprite.setLocation(0,0);              //��λ��ԭ��
 
 	}
 
 	public void mouseClicked(MouseEvent e){	}      //��갴�������º�ſ�
 
 	public void mouseEntered(MouseEvent e)     //������Component
 	{
 		hammerSprite.setVisible(true);           //��������Sprite
 		hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
 
 	}
 
 	public void mousePressed(MouseEvent e)      //��갴��������
 	{
 		hammerSprite.updateState();         //ת������Sprite
 
 
 		int X=hammerSprite.getX();          //ȡ������Sprite��X����
 		int Y=hammerSprite.getY();           //ȡ������Sprite��Y����
 
 
 
 	//�����Ƿ������ͷsprite
 	for (int i=0;i<9;i++)
 	{
 		//������������ӷ�������������ʾ��״̬��
 		if(pigSprite[i].hit(X,Y,FrameWidth,FrameHeight,HammerWidth,HammerHeight)==true)
 		{
 			score=score+10;
 			showStatus("Ŀǰ�÷֣�"+score);
 		}
 		}
 	}
 
 
public void mouseReleased(MouseEvent e)          //��갴���ſ�
{
	hammerSprite.updateState();                 //ת������Sprite
}
 
 
//=================ʵ��MouseMotionListener����================
public void mouseMoved(MouseEvent e)         //����ƶ�ʱ
{
	//���������������Sprite����
	hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
 
}
 
public void mouseDragged(MouseEvent e)         //�����ҷʱ
{
	//���������������Sprite����
	hammerSprite.setLocation(e.getX()-(HammerWidth/2),e.getY()-(HammerHeight/2));
}
}