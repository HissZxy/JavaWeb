/**
 * ���ܣ�̹����Ϸ��2.01
 * 1�������ҵ�̹��
 * 2�����ҵ�̹�˶�����
 * 3�����ҵ�̹�˰��¿ո�(space)�������ӵ�
 * 4�����ҵ�̹�˿��������ӵ�(�������5���ӵ�)
 * 5���򵽵���̹�ˣ�����̹�˾���ʧ
 * 6������̹�˱����б�ը��Ч��
 * 7���õ��˵�̹�˿�����������ƶ�
 * 8������̹����ָ����MyPanel������ƶ�������Խ��
 * 9���õ��˵�̹�˷����ӵ�
 * 10�����ҵ�̹�˱������ӵ����У��ҵ�̹�˱�ը
 * 11����ֹ����̹���ص��˶�
 * 12�����Էֹ�--��һ����ʼ��Panel�����ǿյ���Ҫ����ʾ�ؿ�
 * 13����Ϸ������ͣ������--��ͣʱ���ӵ���̹���ٶ���Ϊ0��̹�˷��򲻱�
 * 14�����Լ�¼��ҵĳɼ�
 * 15��java��β��������ļ�
 */
package com.haiding.tank_7;
import java.awt.*; 
import java.awt.event.ActionEvent;//��¼�
import java.awt.event.ActionListener;//�����
import java.awt.event.KeyEvent;//�����¼�
import java.awt.event.KeyListener;//���̼���
import java.io.File;//����java.io���µ�file��
import javax.imageio.ImageIO;//javaͼ����
import javax.swing.*;
import java.util.*;//���ӵİ�
import javax.sound.sampled.*;//ʵ����Ƶ����
public class MyTank07 extends JFrame implements ActionListener{   //����mytank07������࣬
	//�������
	MyPanel mp=null;    //����java UI���������� 31��45
	//����һ����ʼ���
	MyStartPanel msp=null;
	//�����˵�
	JMenuBar jmb=null;
	//��ʼ��Ϸ
	JMenu jm1=null;
	JMenuItem jmi1=null;
	//�˳���Ϸ
	JMenuItem jmi2=null;
	//�����˳�
	JMenuItem jmi3=null;
	//���Ͼ�
	JMenuItem jmi4=null;
	
	public static void main(String[] args) {
		MyTank07 mt=new MyTank07(); //������Ķ���mt
	}
	//���캯��
	public MyTank07(){   //java���๹�������丸����JFrame
		//�����˵����˵�ѡ��
		jmb=new JMenuBar();
		jm1=new JMenu("��Ϸ(G)");
		//���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("��ʼ����Ϸ(N)");
		jmi1.setMnemonic('N');
		//��jmi1��Ӧ
		jmi1.addActionListener(this);//this��ָ����ö��������������this��java GUI�е����ã�
		jmi1.setActionCommand("newgame");//���þ�����������һ���ַ������ж��㾿��ѡ�������һ�����,���ǲ�����
		
		jmi2=new JMenuItem("�˳���Ϸ(E)");
		jmi2.setMnemonic('E');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		jmi3=new JMenuItem("�����˳�(S)");
		jmi3.setMnemonic('S');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("save");
		
		jmi4=new JMenuItem("������Ϸ(C)");
		jmi4.setMnemonic('C');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("congame");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);  //  add()���ã��ڲ�ͬ����໥��̬���ã�����.add()  //����add()�𵽼�������
		jm1.add(jmi4);
		jmb.add(jm1);
		this.setJMenuBar(jmb);
		
		//�������
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		this.add(msp);
		
		//����JFrame����
		this.setTitle("̹�˴�ս");//JFrame����
		this.setSize(600, 500);//JFrame�����С
		this.setLocationRelativeTo(null);//����Ļ������ʾ
		this.setVisible(true);//��ʾ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳����ر�JFrame
	}
 
	public void actionPerformed(ActionEvent e) {
		//���û���ͬ�ĵ����������ͬ�Ĵ���
		if(e.getActionCommand().equals("newgame")){
			//����ս�����
			mp=new MyPanel("newgame");
			//����mp�߳�
			Thread ta=new Thread(mp);
			ta.start();
			//��ɾ���ɵĿ�ʼ���
			this.remove(msp);
			//�������
			this.add(mp);
			//����
			this.addKeyListener(mp);  //���̼���
			//��ʾ��ˢ��JFrame
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//�û�������˳��˵�
			//�����û���Ϸ��¼����
			Recorder.keepRecording();
			System.exit(0);
		}else if(e.getActionCommand().equals("save")){
			//�û��������˵�
			//������ٵ��˵������͵��˵�����
			Recorder.setEts(mp.ets);
			Recorder.keepRecAndEnemyTank();
			System.exit(0);
		}else if(e.getActionCommand().equals("congame")){
			//�����ϴ���Ϸ
			//����ս�����
			mp=new MyPanel("congame");
			
			//����mp�߳�
			Thread ta=new Thread(mp);
			ta.start();
			//��ɾ���ɵĿ�ʼ���
			this.remove(msp);
			//�������
			this.add(mp);
			//����
			this.addKeyListener(mp);
			//��ʾ��ˢ��JFrame
			this.setVisible(true);
		}
	}
}
//��ʼ���--����ʾ����
class MyStartPanel extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		//��ʾ��Ϣ
		if(times%2==0){
			g.setColor(Color.yellow);
			//������Ϣ������
			Font myFont=new Font("������κ", Font.BOLD, 30);
			g.setFont(myFont);
			g.drawString("Stage: 1", 150, 150);
		}	
	}
 
	public void run() {
		while(true){
			//����
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			times++;
			//�ػ�
			this.repaint();
		}
	}
}
 
//�ҵ����Panel
class MyPanel extends JPanel implements KeyListener,Runnable{//MyPanel�̳�Jpanel��壬����KeyListener���̽ӿں�Runnable�߳̽ӿ�
	//����һ���ҵ�̹��
	Hero hero=null;
	//������������
	AePlayWave apw=null;
 
	//������˵�̹����
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Node> nodes=new Vector<Node>();
	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int enSize=3;//��ʼ����̹������
	
	//���屬ըͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
		
	//���캯��
	public MyPanel(String flag){
		hero=new Hero(200, 270);//�ҵ�̹�˳�ʼλ��
		//�ָ���Ϸ��¼
		Recorder.getRecoring();
		if(flag.equals("newgame")){
			//���ſ�ս����
			apw=new AePlayWave("/Users/a1/eclipse-workspace/MyTank07/Monody.wav");
			apw.start();
			//��ʼ�����˵�̹��
			for(int i=0;i<enSize;i++){
				//����һ�����˵�̹�˶���
				EnemyTank et=new EnemyTank((i+1)*50, 0);
				//��ʼ����̹����ɫ
				et.setColor(2);
				//��ʼ����̹�˵ķ���
				et.setDirect(1);
				
				//��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);
				
				//��������̹�˽���
				Thread t=new Thread(et);
				t.start();
				
				//������̹�����һ���ӵ�
				Shot s=new Shot(et.x+10, et.y+30, 1);
				//���ӵ����������
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//�������̹��
				ets.add(et);
			}
		}else if(flag.equals("congame")){
			nodes=new Recorder().getNodesAndEnNums();
			//��ʼ�����˵�̹��
			for(int i=0;i<nodes.size();i++){
				Node node=nodes.get(i);
				//����һ�����˵�̹�˶���
				EnemyTank et=new EnemyTank(node.x, node.y);
				//��ʼ����̹����ɫ
				et.setColor(2);
				//��ʼ����̹�˵ķ���
				et.setDirect(node.direct);
				
				//��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);
				
				//��������̹�˽���
				Thread t=new Thread(et);
				t.start();
				
				//������̹�����һ���ӵ�
				Shot s=new Shot(et.x+10, et.y+30, 1);
				//���ӵ����������
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//�������̹��
				ets.add(et);
			}
		}
		
		//��ʼ����ըͼƬ
		try {
			image1=ImageIO.read(new File("bomb1.gif"));
			image2=ImageIO.read(new File("bomb2.gif"));
			image3=ImageIO.read(new File("bomb3.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ʾ��Ϣ
	public void showInfo(Graphics g){
		//������ʾ��Ϣ̹��(��̹�˲�����ս����ֻ��������ʾ��ʾ��Ϣ)
		this.drawTank(80, 330, g, 1, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString(Recorder.getEnNum()+"", 110, 350);//��ʾ����̹������
		
		this.drawTank(140, 330, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString(Recorder.getMyLife()+"", 170, 350);//��ʾ�ҵ�̹������
	
		//������ҵ��ܳɼ�
		g.setColor(Color.black);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("�����ܳɼ�", 420, 30);
		
		this.drawTank(420, 60, g, 1, 0);
		g.setColor(Color.black);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	//��дpaint����
	public void paint(Graphics g){
		super.paint(g);//���ø���paint����
		
		//������ʾ��Ϣ
		this.showInfo(g);
		
		//����Panel��ɫ
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);//fillRect(0,0,X?,Y?)��X?/Y?Ϊ�����
		
		//�����Լ���̹��
		if(hero.isLive==true){
			this.drawTank(hero.getX(), hero.getY(), g, 0, this.hero.direct);
		}
		
		//��ss������ȡ��ÿһ���ӵ�������
		for(int i=0;i<this.hero.ss.size();i++){
			Shot myShot=hero.ss.get(i);
			//����һ���ӵ����ж��ӵ��Ƿ�Ϊ��
			if(myShot!=null&&myShot.isLive==true){
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			//�ж��ӵ��Ƿ�����
			if(myShot.isLive==false){
				//������ss��ɾ�����ӵ�
				hero.ss.remove(myShot);
			}
		}
		
		//����ը��
		for(int i=0;i<bombs.size();i++){
			//ȡ��ը��
			Bomb b=bombs.get(i);
			if(b.life>6){
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3){
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//��b������ֵ����
			b.lifeDown();
			//���ը������ֵΪ0���ͰѸ�ը����bombs������ȥ��
			if(b.life==0){
				bombs.remove(b);
			}
		}
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++){
			EnemyTank et=ets.get(i);
			if(et.isLive){
				this.drawTank(et.getX(), et.getY() , g, 1, et.getDirect());
				//�ٻ�������̹�˵��ӵ�
				for(int j=0;j<et.ss.size();j++){
					//ȡ���ӵ�
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive){
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else{
						//������˵�̹�������˾ʹ�����Vector��ȥ��
						et.ss.remove(enemyShot);
					}
				}
			}
		}
	}
	
	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	public void hitEnemyTank(){
		//����Vector������
		for(int i=0;i<hero.ss.size();i++){
			//��ȡ�ӵ�
			Shot myShot=hero.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(myShot.isLive){
				//ȡ��ÿһ������̹�����ӵ������ж�
				for(int j=0;j<ets.size();j++){
					//ȡ��̹��
					EnemyTank et=ets.get(j);
					//�жϵ���̹���Ƿ񻹻���
					if(et.isLive){
						if(this.hitTank(myShot, et)){
							apw=new AePlayWave("/Users/a1/eclipse-workspace/MyTank07/Monody.wav");
							apw.start();
							//����reduceEnNum()���ٵ���̹��ͳ����
							Recorder.reduceEnNum();
							//����addEnNumRec()�����������̹��ͳ����
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//�жϵ��˵��ӵ��Ƿ�����ҵ�̹��
	public void hitMe(){
		//ȡ��ÿһ�����˵�̹��
		for(int i=0;i<this.ets.size();i++){
			//ȡ�����˵�̹��
			EnemyTank et=ets.get(i);
			//ȡ��ÿһ�ŵ��˵��ӵ�
			for(int j=0;j<et.ss.size();j++){
				//ȡ���ӵ�
				Shot enemyShot=et.ss.get(j);
				
				if(hero.isLive){
					if(this.hitTank(enemyShot, hero)){
						
					}
				}
			}
		}
	}
 
	//дһ������ר���ж��ӵ��Ƿ���е���̹��
	public boolean hitTank(Shot s,Tank et){
		boolean b=false;
		//�жϸõ���̹�˵ķ���
		switch(et.direct){
		case 0://����̹�����ϻ�����
		case 1:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+29){
				//���з���Ϊ�ϻ��µĵ���̹��
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				b=true;
				//����һ��ը��
				Bomb bomb=new Bomb(et.x, et.y);
				//����Vector�����й���
				bombs.add(bomb);
			}
			break;
		case 2://����̹�����������
		case 3:
			if(s.x>et.x&&s.x<et.x+29&&s.y>et.y&&s.y<et.y+20){
				//���з���Ϊ����ҵĵ���̹��
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				b=true;
				//����һ��ը��
				Bomb bomb=new Bomb(et.x, et.y);
				//����Vector�����й���
				bombs.add(bomb);
			}
			break;
		}
		return b;
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int type,int direct){
		//�ж���ʲô���͵�̹��
		switch(type){
		case 0:
			g.setColor(Color.cyan);//�ҵ�̹����ɫ
			break;
		case 1:
			g.setColor(Color.white);//����̹����ɫ
			break;
		case 2:
			g.setColor(Color.red);
			break;
		}
		
		//�ж�̹�˵ķ���
		switch(direct){
		//�����ߵ�̹��
		case 0:
			//�����ҵ�̹��(��ʱ�ٷ�װ��һ������)
			//1��������ߵľ���
			g.fill3DRect(x, y, 5, 30, false);
			//2�������ұߵľ���
			g.fill3DRect(x+15, y, 5, 30, false);
			//3�������м����
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4�������м�Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5��������(��Ͳ)
			g.drawLine(x+10, y+15, x+10, y);
			break;
		//�����ߵ�̹��
		case 1:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+29);
			break;
		//�����ߵ�̹��
		case 2:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
		//�����ߵ�̹��
		case 3:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+29, y+10);
			break;
		}
	}
 
	public void keyPressed(KeyEvent e) {//���¼��¼�a����s����d����w����
		if(e.getKeyCode()==KeyEvent.VK_W||e.getKeyCode()==KeyEvent.VK_UP){
			//����
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_DOWN){
			//�����ҵ�̹�˵ķ���,����
			this.hero.setDirect(1);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_LEFT){
			//����
			this.hero.setDirect(2);
			this.hero.moveLeft();
		}else if(e.getKeyCode()==KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT){
			//����
			this.hero.setDirect(3);
			this.hero.moveRight();
		}
		
		//�ж�����Ƿ��¿ո��,���ɽ�����else if����Ȼ����ͬʱ��������Ϳո�(space)��
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			apw=new AePlayWave("e:\\tankgame\\tank_Shelling_sound.wav");
			apw.start();
			//�����ӵ�����
			if(this.hero.ss.size()<=4){
				//���¿ո�󿪻�
				this.hero.shotEnemy();
			}
		}
		
		//����repaint()���������ػ����
		this.repaint();
	}
 
	public void keyReleased(KeyEvent e) {//������¼�
		
	}
	
	public void keyTyped(KeyEvent e) {//�������ֵ
		
	}
 
	//��дrun����
	public void run() {
		while(true){
			try {
				Thread.sleep(100);//��Ϣ100������ػ�MyPanel���
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.hitEnemyTank(); 
			//�жϵ��˵��ӵ��Ƿ�����ҵ�̹��
			this.hitMe();
 
			//�ػ�MyPanel���
			this.repaint();
		}
	}
}