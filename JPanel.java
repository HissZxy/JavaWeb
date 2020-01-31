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
	
	
	//����random������������ݽṹ
	private Pieces kaola[]=new Pieces[16];
	private int arr[]= {11,12,13,14,21,22,23,24,31,32,33,34,41,42,43,44};
	
	
	//���ڻ�����
	private Pieces wall[][]=new Pieces[4][4];
	
	
	//��������¼�
	private int state=1;//�ж�����¼�����Ľ׶Σ�1Ϊѡ���׶Σ�2Ϊ�ƶ��׶�
	private int x=0;//���ÿ�ε��ʱ����wall�еĺ�����
	private int y=0;//���ÿ�ε��ʱ����wall�е�������
	private int faction=0;//�غ���ת���ж�
	private Pieces a;//���ڽ���ֵ������
	private boolean first=true;//���ڵ�һ�ε��ʱ������ж���
	
	//ͼƬ
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
	
	//��̬����ͼƬ�ļ�
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
	
	//���캯���������Ӻ��������г�ʼ��
	public Chess() {
		//�½ű�=inten+fac-1
		super();
		kaola[0]=new Pieces("��",0,1,Rmouse);
		kaola[1]=new Pieces("��",0,2,Bmouse);
		kaola[2]=new Pieces("è",2,1,Rcat);
		kaola[3]=new Pieces("è",2,2,Bcat);
		kaola[4]=new Pieces("��",4,1,Rwolf);
		kaola[5]=new Pieces("��",4,2,Bwolf);
		kaola[6]=new Pieces("��",6,1,Rleopard);
		kaola[7]=new Pieces("��",6,2,Bleopard);
		kaola[8]=new Pieces("��",8,1,Rtiger);
		kaola[9]=new Pieces("��",8,2,Btiger);
		kaola[10]=new Pieces("ʨ",10,1,Rlion);
		kaola[11]=new Pieces("ʨ",10,2,Blion);
		kaola[12]=new Pieces("��",12,1,Relephent);
		kaola[13]=new Pieces("��",12,2,Belephent);
		kaola[14]=new Pieces("����",14,1,Rkoala);
		kaola[15]=new Pieces("����",14,2,Bkoala);
		a=new Pieces(kaola[0]);
		/*
		this.random();
		for(int i=0;i<16;i++) {
			wall[kaola[i].getY()][kaola[i].getX()]=kaola[i];
		}
		*/
	}
	
	//��������������ӽ����������
	public void random() {
		//c�����������Ϊ�˵���nextInt��int n���������ú�������һ��0��n-1�������
		//0��a-1�����ȡ��һ��������ֵ��kaola��x��y����
		//y=arr[b]/10
		//x=arr[b]-y*10
		//a��ʾ��ǰȡ������ķ�Χ�������𲽵ݼ���
		//b�����洢�����������
		//d�ǶԻ�ʱ����ת����
		//˼�룺0��15�������������ʹ�ú���15�Ի�����֤��һ����0��14֮��ȡ��
		Random c=new Random();			
		int a=16;
		int b=0;
		int d=0;
		for(int i=0;i<16;i++) {
			b=c.nextInt(a);
			//��ֵ
			d=arr[b]/10;
			kaola[i].setY(d-1);
			kaola[i].setX(arr[b]-d*10-1);
			//�Ի�
			d=arr[a-1];
			arr[a-1]=arr[b];
			arr[b]=d;
			//a�Լ�
			a--;
		}
	}
	
	
	//�ж��Ƿ��ܹ����������ƶ�
	/*
	��һ���ж�״̬�Ƿ���14���Ƿ��ǿ�����
		����ǣ��򷵻�false
		������ǣ������κζ���
	�ڶ����ж��Ƿ�Ϊ�߽�
		����ǣ��򷵻�false
		������ǣ�����������
	�������ж�Ҫ�ƶ���λ���Ƿ�Ϊ��������
		����ǣ��򷵻�false
		������ǣ��������Ĳ�
	���Ĳ��ж�Ҫ�ƶ��ĵط��������ܷ�This��
		������ܣ��򷵻�false
		����ܣ�������岽
	���岽�������
		�ų������ƶ���������µĶ������ƶ�
	*/
	
	
	//��
	public boolean CanLeft(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("�Է��ǿ����������ƶ�");
			return false;
			

		}
		if(This.getX()==0) {
			System.out.println("��߽磬�����ƶ�");
			return false;
		}else if(!wall[This.getY()][This.getX()-1].getState()) {
			System.out.println("����ǣ��������ƶ�");
			return false;
		}else if(!This.CanEat(wall[This.getY()][This.getX()-1])) {
			System.out.println("���Է�����󣬲����ƶ�");
			return false;
		}
		System.out.println("�����ƶ�");
		return true;
	}
	
	//��
	public boolean CanRight(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("�Է��ǿ����������ƶ�");
			return false;
		}
		if(This.getX()==3) {
			System.out.println("�ұ߽磬�����ƶ�");
			return false;
		}else if(!wall[This.getY()][This.getX()+1].getState()) {
			System.out.println("�Ҳ��ǣ��������ƶ�");
			return false;
		}else if(!This.CanEat(wall[This.getY()][This.getX()+1])) {
			System.out.println("�Ҳ�Է�����󣬲����ƶ�");
			return false;
		}
		System.out.println("�����ƶ�");
		return true;
	}
	//��
	public boolean CanUp(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("�Է��ǿ����������ƶ�");
			return false;
		}
		if(This.getY()==0) {
			System.out.println("�ϱ߽磬�����ƶ�");
			return false;
		}else if(!wall[This.getY()-1][This.getX()].getState()) {
			System.out.println("�ϲ��ǣ��������ƶ�");
			return false;
		}else if(!This.CanEat(wall[This.getY()-1][This.getX()])) {
			System.out.println("�ϲ�Է�����󣬲����ƶ�");
			return false;
		}
		System.out.println("�����ƶ�");
		return true;
	}
	//��
	public boolean CanDown(Pieces This) {
		if(This.getInt()==14) {
			System.out.println("�Է��ǿ����������ƶ�");
			return false;
		}
		if(This.getY()==3) {
			System.out.println("�±߽磬�����ƶ�");
			return false;
		}else if(!wall[This.getY()+1][This.getX()].getState()) {
			System.out.println("�²��ǣ��������ƶ�");
			return false;
		}else if(!This.CanEat(wall[This.getY()+1][This.getX()])) {
			System.out.println("�²�Է�����󣬲����ƶ�");
			return false;
		}
		System.out.println("�����ƶ�");
		return true;
	}
	
	//�ƶ���
	/*
	ǰ���ǿ����ƶ�
	Ҫ�ƶ���λ�������������һ���������ӣ���һ��������
	�������ӵ�ʱ�򣬽��г���
	��û�����ӵ�ʱ��ֱ���ƶ�
	�����ж�wall��������clear������
	Ȼ���ػ����£�repaint������
	*/
	
	//��
	public void Right(Pieces This) {
		if(wall[This.getY()][This.getX()+1].getDie()) {
			This.Right();
		}else {
			This.Eat(wall[This.getY()][This.getX()+1]);
			
		}
		Clear();
		repaint();
	}
	//��
	public void Left(Pieces This) {
		if(wall[This.getY()][This.getX()-1].getDie()) {
			This.Left();
		}else {
			This.Eat(wall[This.getY()][This.getX()-1]);
			
		}
		Clear();
		repaint();
	}
	//��
	public void Up(Pieces This) {
		if(wall[This.getY()-1][This.getX()].getDie()) {
			This.Up();
		}else {
			This.Eat(wall[This.getY()-1][This.getX()]);
			
		}
		Clear();
		repaint();
	}
	//��
	public void Down(Pieces This) {
		if(wall[This.getY()+1][This.getX()].getDie()) {
			This.Down();
		}else {
			This.Eat(wall[This.getY()+1][This.getX()]);
			
		}
		Clear();
		repaint();
	}
	
	
	//����Ŀ������д 
	/*
	��wall���б���
	������wall���ĳһ������������x��y��ƥ�䣬����������ƥ��ĸ��ӶԻ�
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
	
	
	//��ʼ��
	public void Initialize() {
		kaola[0]=new Pieces("��",0,1,Rmouse);
		kaola[1]=new Pieces("��",0,2,Bmouse);
		kaola[2]=new Pieces("è",2,1,Rcat);
		kaola[3]=new Pieces("è",2,2,Bcat);
		kaola[4]=new Pieces("��",4,1,Rwolf);
		kaola[5]=new Pieces("��",4,2,Bwolf);
		kaola[6]=new Pieces("��",6,1,Rleopard);
		kaola[7]=new Pieces("��",6,2,Bleopard);
		kaola[8]=new Pieces("��",8,1,Rtiger);
		kaola[9]=new Pieces("��",8,2,Btiger);
		kaola[10]=new Pieces("ʨ",10,1,Rlion);
		kaola[11]=new Pieces("ʨ",10,2,Blion);
		kaola[12]=new Pieces("��",12,1,Relephent);
		kaola[13]=new Pieces("��",12,2,Belephent);
		kaola[14]=new Pieces("����",14,1,Rkoala);
		kaola[15]=new Pieces("����",14,2,Bkoala);
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
	
	//��ͼ
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintWall(g);
		paintFont(g);
	}
	
	//������
	/*
	������
	������ˣ��ͻ���ɫ��̨�ӣ�base��
	�����ж�״̬�Ƿ�Ϊ����
	������棬�ͻ��ʺ�
	���򣬻���Ӧ��ͼƬ����������ѡ��2�׶Σ���Ŵ�
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
	//������
	public void paintFont(Graphics g) {
		Font f=getFont();
		Font font=new Font(f.getFontName(),Font.BOLD,30);
		int x = 110;
		int y = 55;
		g.setFont(font);
		if(faction==1) {
			g.setColor(Color.red);
			g.drawString("�췽�غ�", x, y);
		}
		if(faction==2) {
			g.setColor(Color.blue);
			g.drawString("�����غ�", x, y);
		}
		
	}
	
	//����¼�
	/*������¼�
	 * ��д�����ĵ���������mouseCliced��MouseEvent e��
	 * ���������״̬��һ����ѡ��״̬�������ƶ�״̬
	 * ѡ��״̬��Ϊѡ��Ҫ�ƶ������ӣ����ǵ㿪���������
	 * �ƶ�״̬��Ϊѡ��Ҫ�ƶ����ĵط�
	 * state=1��Ϊһ״̬��2Ϊ��״̬
	 * �ڳ�ʼ��ʱ��stateΪ1����ѡ�����Ӻ�state=2
	 * ѡ��״̬��
	 * 		��wall���б�����ͨ����������صĺ�������ȷ�������Ϊ��һ������
	 * 		��������������������ֱ�ӷ���
	 * 		û�����������¼�����ӵĺ������꣬�����浽x��y��
	 * 		����Ǳ��泯�ϵ����ӣ��ж��ǲ��ǵ�һ�ε㿪������ǣ����з��飬����ÿ�η�����ƶ������ĺ������Ļغ�
	 * 		���Ұ����ӷ�ת��������setState���������޸�
	 * 		��������泯�ϵ����ӣ��ж��ܲ���ѡ�����Ǳ������Ӳ��ܱ�ѡ��
	 * 		�������ѡ�������ƶ�״̬
	 * �ƶ�״̬��
	 * 		����Ҽ���������˵�ѡ��״̬������state��1
	 * 		���������жϸ�λ���ܷ��ƶ�
	 * 		ͨ��can***�����ĺ���ȥ�жϣ���������ƶ��������ƶ�����
	 * 		��state��һ�ص�ѡ��״̬
	 * */
	public void Run() {
		Initialize();//��ʼ��
		repaint();
		MouseAdapter i=new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i=e.getButton();
				Clear();
				repaint();
				if(state==1) {
					//ѡ��
					for(int k=0;k<4;k++) {
						for(int j=0;j<4;j++) {
							if(20+j*80<e.getX()&&e.getX()<20+j*80+70) {
								if(100+k*80<e.getY()&&e.getY()<100+k*80+70) {
									if(wall[k][j].getDie()) {
										System.out.println("���󣺵�����Ѿ���ȥ�����ӣ������µ��ѡ������");
										return;
									}
									x=j;
									y=k;
									//�㿪��
									if(!wall[y][x].getState()) {
										if(first) {
											System.out.println("��һ�ε㿪�����ֻ����");
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
										//��ͷ��Ч
										if(wall[y][x].getFaction()!=faction) {
											//����
											System.out.println("���󣺵���˷Ǳ���Ӫ�����ӣ������µ��ѡ������");
											return;
										}else {
											state=2;
										}
										System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"��ѡ��");
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
						System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"ȡ��ѡ��");
					}
					if(i==MouseEvent.BUTTON1) {
						//�ƶ�
						
						if(CanUp(wall[y][x])) {
							if(20+x*80<e.getX()&&e.getX()<20+x*80+70) {
								if(100+(y-1)*80<e.getY()&&e.getY()<100+(y-1)*80+70) {
									Up(wall[y][x]);
									System.out.println(wall[y][x].getName()+wall[y][x].getFaction()+"����");
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
	//�ж�ʤ��
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
			//����ʤ��
			if(blue==7) {
				NoWin();
			}
			BlueWin();
			        
		}
		if(blue==7) {
			//�췽ʤ��
			RedWin();
		}
		
		
	}
	public void BlueWin() {
		int res=JOptionPane.showConfirmDialog(null, "����ʤ����", "����ʤ��", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                 //������ǡ���ִ����������
			 Initialize();
			 repaint();
         }else{
        	 System.exit(0);
             return;
         } 
	}
	public void RedWin() {
		int res=JOptionPane.showConfirmDialog(null, "�췽ʤ����", "�췽ʤ��", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                 //������ǡ���ִ����������
			 System.exit(0);
         }else{
             System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
             System.exit(0);
             return;
         } 
	}
	public void NoWin() {
		int res=JOptionPane.showConfirmDialog(null, "����", "����", JOptionPane.YES_NO_OPTION);
		 if(res==JOptionPane.YES_OPTION){ 
                //������ǡ���ִ����������
			 System.exit(0);
        }else{
            System.out.println("ѡ����ִ�еĴ���");  
            //������񡱺�ִ����������
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
