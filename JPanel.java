package koalaChess;


import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import koalaChess.Pieces;


public class Chess extends JPanel{
	public URL file1 = getClass().getResource("a.wav");
	public AudioClip sound1 = java.applet.Applet.newAudioClip(file1);
	
	
	//用于random随机函数的数据结构
	private Pieces kaola[]=new Pieces[16];
	private int arr[]= {11,12,13,14,21,22,23,24,31,32,33,34,41,42,43,44};
	
	
	//用于画棋盘
	private Pieces wall[][]=new Pieces[4][4];
	
	
	//用于鼠标事件
	private int state=1;//判断鼠标事件进入的阶段，1为选定阶段，2为移动阶段
	private int x=0;//鼠标每次点击时候在wall中的横坐标
	private int y=0;//鼠标每次点击时候在wall中的纵坐标
	private int faction=0;//回合轮转的判定
	private Pieces a;//用于交换值的容器
	private boolean first=true;//用于第一次点击时分组的判定器
	
	//图片
	private static Image background;
	private static Image Rmouse;
	private static Image Bmouse;
	private static Image Rcat;
	private static Image Bcat;
	private static Image Rwolf;
	private static Image Bwolf;
	private static Image Rleopard;
	private static Image Bleopard;
	private static Image Rtiger;
	private static Image Btiger;
	private static Image Rlion;
	private static Image Blion;
	private static Image Relephent;
	private static Image Belephent;
	private static Image Rkoala;
	private static Image Bkoala;
	private static Image box;
	private static Image base;
	
	//静态加载图片文件
	static {
		try {
			background =ImageIO.read(Chess.class.getResource("background.jpg"));
			box=ImageIO.read(Chess.class.getResource("box.png"));
			Rmouse=ImageIO.read(Chess.class.getResource("Rmouse.png"));
			Bmouse=ImageIO.read(Chess.class.getResource("Bmouse.png"));
			Rcat=ImageIO.read(Chess.class.getResource("Rcat.png"));
			Bcat=ImageIO.read(Chess.class.getResource("Bcat.png"));
			Rwolf=ImageIO.read(Chess.class.getResource("Rwolf.png"));
			Bwolf=ImageIO.read(Chess.class.getResource("Bwolf.png"));
			Rleopard=ImageIO.read(Chess.class.getResource("Rleopard.png"));
			Bleopard=ImageIO.read(Chess.class.getResource("Bleopard.png"));
			Rtiger=ImageIO.read(Chess.class.getResource("Rtiger.png"));
			Btiger=ImageIO.read(Chess.class.getResource("Btiger.png"));
			Rlion=ImageIO.read(Chess.class.getResource("Rlion.png"));
			Blion=ImageIO.read(Chess.class.getResource("Blion.png"));
			Relephent=ImageIO.read(Chess.class.getResource("Relephent.png"));
			Belephent=ImageIO.read(Chess.class.getResource("Belephent.png"));
			Rkoala=ImageIO.read(Chess.class.getResource("Rkoala.png"));
			Bkoala=ImageIO.read(Chess.class.getResource("Bkoala.png"));
			base=ImageIO.read(Chess.class.getResource("base.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//构造函数，对棋子和容器进行初始化
	public Chess() {
		//下脚标=inten+fac-1
		super();
		kaola[0]=new Pieces("鼠",0,1,Rmouse);
		kaola[1]=new Pieces("鼠",0,2,Bmouse);
		kaola[2]=new Pieces("猫",2,1,Rcat);
		kaola[3]=new Pieces("猫",2,2,Bcat);
		kaola[4]=new Pieces("狼",4,1,Rwolf);
		kaola[5]=new Pieces("狼",4,2,Bwolf);
		kaola[6]=new Pieces("豹",6,1,Rleopard);
		kaola[7]=new Pieces("豹",6,2,Bleopard);
		kaola[8]=new Pieces("虎",8,1,Rtiger);
		kaola[9]=new Pieces("虎",8,2,Btiger);
		kaola[10]=new Pieces("狮",10,1,Rlion);
		kaola[11]=new Pieces("狮",10,2,Blion);
		kaola[12]=new Pieces("象",12,1,Relephent);
		kaola[13]=new Pieces("象",12,2,Belephent);
		kaola[14]=new Pieces("考拉",14,1,Rkoala);
		kaola[15]=new Pieces("考拉",14,2,Bkoala);
		a=new Pieces(kaola[0]);
		/*
		this.random();
		for(int i=0;i<16;i++) {
			wall[kaola[i].getY()][kaola[i].getX()]=kaola[i];
		}
		*/
	}
	
	//随机函数，对棋子进行随机排列
	public void random() {
		//c是随机数对象，为了调用nextInt（int n）函数，该函数返回一个0到n-1的随机数
		//0到a-1中随机取出一个数，赋值给kaola的x，y坐标
		//y=arr[b]/10
		//x=arr[b]-y*10
		//a表示当前取随机数的范围，他是逐步递减的
		//b用来存储产生的随机数
		//d是对换时的中转容器
		//思想：0到15的随机产生的数使用后，与15对换，保证下一次在0到14之间取数
		Random c=new Random();			
		int a=16;
		int b=0;
		int d=0;
		for(int i=0;i<16;i++) {
			b=c.nextInt(a);
			//赋值
			d=arr[b]/10;
			kaola[i].setY(d-1);
			kaola[i].setX(arr[b]-d*10-1);
			//对换
			d=arr[a-1];
			arr[a-1]=arr[b];
			arr[b]=d;
			//a自减
			a--;
		}
	}
	
	
	//判断是否能够上下左右移动
	/*
	第一步判断状态是否是14（是否是考拉）
		如果是，则返回false
		如果不是，则不做任何动作
	第二步判断是否为边界
		如果是，则返回false
		如果不是，则进入第三步
	第三步判断要移动的位置是否为背面棋子
		如果是，则返回false
		如果不是，则进入第四步
	第四步判断要移动的地方的棋子能否被This吃
		如果不能，则返回false
		如果能，进入第五步
	第五步其他情况
		排除不能移动情况，余下的都可以移动
	*/
	
	
	//左
	public boolean CanLeft(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("对方是考拉，不能移动");
			return false;
			

		}
		if(This.getX()==0) {
			System.out.println("左边界，不能移动");
			return false;
		}else if(!wall[This.getY()][This.getX()-1].getState()) {
			System.out.println("左侧是？，不能移动");
			return false;
		}else if(!This.CanEat(wall[This.getY()][This.getX()-1])) {
			System.out.println("左侧对方比你大，不能移动");
			return false;
		}
		System.out.println("可以移动");
		return true;
	}
	
	//右
	public boolean CanRight(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("对方是考拉，不能移动");
			return false;
		}
		if(This.getX()==3) {
			System.out.println("右边界，不能移动");
			return false;
		}else if(!wall[This.getY()][This.getX()+1].getState()) {
			System.out.println("右侧是？，不能移动");
			return false;
		}else if(!This.CanEat(wall[This.getY()][This.getX()+1])) {
			System.out.println("右侧对方比你大，不能移动");
			return false;
		}
		System.out.println("可以移动");
		return true;
	}
	//上
	public boolean CanUp(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("对方是考拉，不能移动");
			return false;
		}
		if(This.getY()==0) {
			System.out.println("上边界，不能移动");
			return false;
		}else if(!wall[This.getY()-1][This.getX()].getState()) {
			System.out.println("上侧是？，不能移动");
			return false;
		}else if(!This.CanEat(wall[This.getY()-1][This.getX()])) {
			System.out.println("上侧对方比你大，不能移动");
			return false;
		}
		System.out.println("可以移动");
		return true;
	}
	//下
	public boolean CanDown(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("对方是考拉，不能移动");
			return false;
		}
		if(This.getY()==3) {
			System.out.println("下边界，不能移动");
			return false;
		}else if(!wall[This.getY()+1][This.getX()].getState()) {
			System.out.println("下侧是？，不能移动");
			return false;
		}else if(!This.CanEat(wall[This.getY()+1][This.getX()])) {
			System.out.println("下侧对方比你大，不能移动");
			return false;
		}
		System.out.println("可以移动");
		return true;
	}
	
	//移动：
	/*
	前提是可以移动
	要移动的位置有两种情况，一种是有棋子，另一种无棋子
	当有棋子的时候，进行吃子
	当没有棋子的时候，直接移动
	随后进行对wall进行清理（clear（））
	然后重画更新（repaint（））
	*/
	
	//右
	public void Right(Pieces This) {
		if(wall[This.getY()][This.getX()+1].getDie()) {
			This.Right();
		}else {
			This.Eat(wall[This.getY()][This.getX()+1]);
			
		}
		Clear();
		repaint();
	}
	//左
	public void Left(Pieces This) {
		if(wall[This.getY()][This.getX()-1].getDie()) {
			This.Left();
		}else {
			This.Eat(wall[This.getY()][This.getX()-1]);
			
		}
		Clear();
		repaint();
	}
	//上
	public void Up(Pieces This) {
		if(wall[This.getY()-1][This.getX()].getDie()) {
			This.Up();
		}else {
			This.Eat(wall[This.getY()-1][This.getX()]);
			
		}
		Clear();
		repaint();
	}
	//下
	public void Down(Pieces This) {
		if(wall[This.getY()+1][This.getX()].getDie()) {
			This.Down();
		}else {
			This.Eat(wall[This.getY()+1][This.getX()]);
			
		}
		Clear();
		repaint();
	}
	
	
	//清理，目的是重写 
	/*
	对wall进行遍历
	当发现wall里的某一个格子与他的x，y不匹配，让他与与其匹配的格子对换
	*/
	public void Clear() {
		//Pieces a=new Pieces(wall[0][0]);
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				/*
				if(wall[i][j].getDie()) {
					wall[i][j].setInt(-1);
				}
				else
				*/
				if(wall[i][j].getX()!=j||wall[i][j].getY()!=i) {
					a.copy(wall[wall[i][j].getY()][wall[i][j].getX()]);
					wall[wall[i][j].getY()][wall[i][j].getX()].copy(wall[i][j]);
					
					wall[i][j].copy(a);
					wall[i][j].setY(i);
					wall[i][j].setX(j);
				}
			}
		}
		//repaint();
	}
	
	
	//初始化
	public void Initialize() {
		kaola[0]=new Pieces("鼠",0,1,Rmouse);
		kaola[1]=new Pieces("鼠",0,2,Bmouse);
		kaola[2]=new Pieces("猫",2,1,Rcat);
		kaola[3]=new Pieces("猫",2,2,Bcat);
		kaola[4]=new Pieces("狼",4,1,Rwolf);
		kaola[5]=new Pieces("狼",4,2,Bwolf);
		kaola[6]=new Pieces("豹",6,1,Rleopard);
		kaola[7]=new Pieces("豹",6,2,Bleopard);
		kaola[8]=new Pieces("虎",8,1,Rtiger);
		kaola[9]=new Pieces("虎",8,2,Btiger);
		kaola[10]=new Pieces("狮",10,1,Rlion);
		kaola[11]=new Pieces("狮",10,2,Blion);
		kaola[12]=new Pieces("象",12,1,Relephent);
		kaola[13]=new Pieces("象",12,2,Belephent);
		kaola[14]=new Pieces("考拉",14,1,Rkoala);
		kaola[15]=new Pieces("考拉",14,2,Bkoala);
		a=new Pieces(kaola[0]);
		this.state=1;
		this.x=0;
		this.y=0;
		this.first=true;
		this.random();
		for(int i=0;i<16;i++) {
			wall[kaola[i].getY()][kaola[i].getX()]=kaola[i];
		}
		
	}
	
	//绘图
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintWall(g);
		paintFont(g);
	}
	
	//画棋盘
	/*
	遍历：
	如果死了，就画绿色的台子（base）
	否则，判断状态是否为背面
	如果背面，就画问号
	否则，画对应的图片，如果在鼠标选定2阶段，则放大
	*/
	public void paintWall(Graphics g) {
		int k=0;
		int l=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				k=80*i+20;
				l=80*j+100;
				if(wall[j][i].getDie()) {
					g.drawImage(base, k, l, 70, 70, null);
				}
				else 
					if(!wall[j][i].getState()) {
					g.drawImage(box, k, l, 70, 70, null);
				}
				else {
						/*if(state==2) {
							g.drawImage(wall[this.y][this.x].getImage(),k-10 , l-10, 90, 90, null);
							continue;
						}*/
						g.drawImage(wall[j][i].getImage(),k , l, 70, 70, null);
						
						//g.drawImage(wall[this.y][this.x].getImage(),x-10 , y-10, 90, 90, null);
					
				}
 
			}
		}
		if(state==2) {
			g.drawImage(wall[this.y][this.x].getImage(),80*x+20-10 , 80*y+100-10, 90, 90, null);
			Font f=getFont();
			Font font=new Font(f.getFontName(),Font.BOLD,25);
			g.setFont(font);
			g.setColor(Color.darkGray);
			if(CanRight(wall[this.y][this.x])) {
				g.drawString(">", (x+1)*80+20+35-10, y*80+100+35+5);
			}
			if(CanLeft(wall[this.y][this.x])) {
				g.drawString("<", (x-1)*80+20+35-10, y*80+100+35+5);
			}
			if(CanUp(wall[this.y][this.x])) {
				g.drawString("^", x*80+20+35-10, (y-1)*80+100+35+5);
			}
			if(CanDown(wall[this.y][this.x])) {
				g.drawString("v", x*80+20+35-10, (y+1)*80+100+35+5);
			}
		}
		
		
	}
	//画文字
	public void paintFont(Graphics g) {
		Font f=getFont();
		Font font=new Font(f.getFontName(),Font.BOLD,30);
		int x = 110;
		int y = 55;
		g.setFont(font);
		if(faction==1) {
			g.setColor(Color.red);
			g.drawString("红方回合", x, y);
		}
		if(faction==2) {
			g.setColor(Color.blue);
			g.drawString("蓝方回合", x, y);
		}
		
	}
	
	//鼠标事件
	/*鼠标点击事件
	 * 重写了鼠标的单击函数，mouseCliced（MouseEvent e）
	 * 点击有两个状态，一个是选定状态，二是移动状态
	 * 选定状态即为选择要移动的棋子，或是点开背面的棋子
	 * 移动状态即为选择要移动到的地方
	 * state=1即为一状态，2为二状态
	 * 在初始的时候state为1，当选定棋子后state=2
	 * 选定状态：
	 * 		对wall进行遍历，通过鼠标点击返回的横纵坐标确定点击的为哪一个棋子
	 * 		如果点击的棋子死掉，则直接返回
	 * 		没有死掉，则记录该棋子的横纵坐标，并保存到x，y中
	 * 		如果是背面朝上的棋子，判断是不是第一次点开，如果是，进行分组，随后的每次翻棋和移动都更改红蓝方的回合
	 * 		并且把棋子反转过来，用setState（）函数修改
	 * 		如果是正面朝上的棋子，判断能不能选定，非本方棋子不能被选定
	 * 		如果可以选定进入移动状态
	 * 移动状态：
	 * 		如果右键点击，回退到选定状态，即把state置1
	 * 		左键点击则判断该位置能否移动
	 * 		通过can***（）的函数去判断，如果可以移动，调用移动函数
	 * 		把state置一回到选定状态
	 * */
	public void Run() {
		Initialize();//初始化
		repaint();
		MouseAdapter i=new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i=e.getButton();
				Clear();
				repaint();
				if(state==1) {
					//选定
					for(int k=0;k<4;k++) {
						for(int j=0;j<4;j++) {
							if(20+j*80<e.getX()&&e.getX()<20+j*80+70) {
								if(100+k*80<e.getY()&&e.getY()<100+k*80+70) {
									if(wall[k][j].getDie()) {
										System.out.println("错误：点击了已经死去的棋子，请重新点击选定棋子");
										return;
									}
									x=j;
									y=k;
									//点开？
									if(!wall[y][x].getState()) {
										if(first) {
											System.out.println("第一次点开？，分伙完成");
											System.out.println(""+faction+">>>>>");
											faction=wall[y][x].getFaction();
											first=false;
										}
										
										wall[y][x].setState();
										if(faction==1) {
											faction=2;
										}else {
											faction=1;
										}
										
										repaint();
										return;
									}
									else {
										//state=2;
										//箭头特效
										if(wall[y][x].getFaction()!=faction) {
											//错误
											System.out.println("错误：点击了非本阵营的棋子，请重新点击选定棋子");
											return;
										}else {
											state=2;
										}
										System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"被选定");
										repaint();
										return;
									}
								}
								else {
									state=1;
								}
							}else {
								state=1;
							}
						}
					}
					
				}
				if(state==2){
					if(i==MouseEvent.BUTTON2) {
						state=1;
						System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"取消选定");
					}
					if(i==MouseEvent.BUTTON1) {
						//移动
						
						if(CanUp(wall[y][x])) {
							if(20+x*80<e.getX()&&e.getX()<20+x*80+70) {
								if(100+(y-1)*80<e.getY()&&e.getY()<100+(y-1)*80+70) {
									Up(wall[y][x]);
									System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"上移");
									state=1;
									if(faction==1) {
										faction=2;
									}else {
										faction=1;
									}
									System.out.println(""+faction+">>>>");
									Clear();
									repaint();
									judge();
									return;
								}
							}
							
						}
						if(CanDown(wall[y][x])) {
							if(20+x*80<e.getX()&&e.getX()<20+x*80+70) {
								if(100+(y+1)*80<e.getY()&&e.getY()<100+(y+1)*80+70) {
									Down(wall[y][x]);
									state=1;
									if(faction==1) {
										faction=2;
									}else {
										faction=1;
									}
									System.out.println(""+faction+">>>>");
									Clear();
									repaint();
									judge();
									return;
								}
							}
							
						}
						if(CanLeft(wall[y][x])) {
							if(20+(x-1)*80<e.getX()&&e.getX()<20+(x-1)*80+70) {
								if(100+y*80<e.getY()&&e.getY()<100+y*80+70) {
									Left(wall[y][x]);
									state=1;
									if(faction==1) {
										faction=2;
									}else {
										faction=1;
									}
									System.out.println(""+faction+">>>>");
									Clear();
									repaint();
									judge();
									return;
								}
							}
							
						}
						if(CanRight(wall[y][x])) {
							if(20+(x+1)*80<e.getX()&&e.getX()<20+(x+1)*80+70) {
								if(100+y*80<e.getY()&&e.getY()<100+y*80+70) {
									Right(wall[y][x]);
									state=1;
									if(faction==1) {
										faction=2;
									}else {
										faction=1;
									}
									System.out.println(""+faction+">>>>");
									Clear();
									repaint();
									judge();
									return;
								}
							}
							
						}
						state=1;
						repaint();
					}
				}
				
			}
			
		};
		Clear();
		this.requestFocus();
		this.addMouseListener(i);
		Clear();
		repaint();
		
		
		
	}
	//判断胜利
	public void judge() {
		int red=0;
		int blue=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(wall[i][j].getDie()) {
					if(wall[i][j].getFaction()==1) {
						red++;
					}
					if(wall[i][j].getFaction()==2) {
						blue++;
					}
				}
			}
		}
		if(red==7) {
			//蓝方胜利
			if(blue==7) {
				NoWin();
			}
			BlueWin();
			        
		}
		if(blue==7) {
			//红方胜利
			RedWin();
		}
		
		
	}
	public void BlueWin() {
		int res=JOptionPane.showConfirmDialog(null, "蓝方胜利！", "蓝方胜利", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                 //点击“是”后执行这个代码块
			 Initialize();
			 repaint();
         }else{
        	 System.exit(0);
             return;
         } 
	}
	public void RedWin() {
		int res=JOptionPane.showConfirmDialog(null, "红方胜利！", "红方胜利", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                 //点击“是”后执行这个代码块
			 System.exit(0);
         }else{
             System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
             System.exit(0);
             return;
         } 
	}
	public void NoWin() {
		int res=JOptionPane.showConfirmDialog(null, "和棋", "和棋", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                //点击“是”后执行这个代码块
			 System.exit(0);
        }else{
            System.out.println("选择否后执行的代码");  
            //点击“否”后执行这个代码块
            System.exit(0);
            
        } 
	}
	
	public static void main(String args[]) {
		
		JFrame window=new JFrame();
		Chess koala=new Chess();
		window.setSize(370,620);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setTitle("KoalaChess");
		window.add(koala);
		koala.sound1.loop();
		window.setVisible(true);
		koala.Run();
		window.setVisible(true);
	}
	
	
	
	
	

}
