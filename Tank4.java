/**
 * 功能：坦克游戏的2.01
 * 1、画出我的坦克
 * 2、让我的坦克动起来
 * 3、让我的坦克按下空格(space)键发射子弹
 * 4、让我的坦克可以连发子弹(最多连发5颗子弹)
 * 5、打到敌人坦克，敌人坦克就消失
 * 6、加入坦克被击中爆炸的效果
 * 7、让敌人的坦克可以自由随机移动
 * 8、控制坦克在指定的MyPanel面板中移动，不可越界
 * 9、让敌人的坦克发射子弹
 * 10、当我的坦克被敌人子弹击中，我的坦克爆炸
 * 11、防止敌人坦克重叠运动
 * 12、可以分关--做一个开始的Panel，它是空的主要是提示关卡
 * 13、游戏可以暂停、继续--暂停时将子弹、坦克速度设为0，坦克方向不变
 * 14、可以记录玩家的成绩
 * 15、java如何操作声音文件
 */
package com.haiding.tank_7;
import java.awt.*; 
import java.awt.event.ActionEvent;//活动事件
import java.awt.event.ActionListener;//活动监听
import java.awt.event.KeyEvent;//键盘事件
import java.awt.event.KeyListener;//键盘监听
import java.io.File;//导入java.io包下的file类
import javax.imageio.ImageIO;//java图像处理
import javax.swing.*;
import java.util.*;//复杂的包
import javax.sound.sampled.*;//实现音频播放
public class MyTank07 extends JFrame implements ActionListener{   //定义mytank07这个子类，
	//定义组件
	MyPanel mp=null;    //都是java UI编程里的内容 31～45
	//定义一个开始面板
	MyStartPanel msp=null;
	//做出菜单
	JMenuBar jmb=null;
	//开始游戏
	JMenu jm1=null;
	JMenuItem jmi1=null;
	//退出游戏
	JMenuItem jmi2=null;
	//存盘退出
	JMenuItem jmi3=null;
	//接上局
	JMenuItem jmi4=null;
	
	public static void main(String[] args) {
		MyTank07 mt=new MyTank07(); //定义类的对象mt
	}
	//构造函数
	public MyTank07(){   //java子类构造器；其父类是JFrame
		//创建菜单及菜单选项
		jmb=new JMenuBar();
		jm1=new JMenu("游戏(G)");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("开始新游戏(N)");
		jmi1.setMnemonic('N');
		//对jmi1相应
		jmi1.addActionListener(this);//this是指向调用对象本身的引用名；this在java GUI中的作用？
		jmi1.setActionCommand("newgame");//作用就是另外设置一个字符串来判断你究竟选择的是哪一个组件,还是不懂啊
		
		jmi2=new JMenuItem("退出游戏(E)");
		jmi2.setMnemonic('E');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		jmi3=new JMenuItem("存盘退出(S)");
		jmi3.setMnemonic('S');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("save");
		
		jmi4=new JMenuItem("继续游戏(C)");
		jmi4.setMnemonic('C');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("congame");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);  //  add()作用：在不同类间相互静态调用，类名.add()  //或则add()起到加入作用
		jm1.add(jmi4);
		jmb.add(jm1);
		this.setJMenuBar(jmb);
		
		//构建组件
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		this.add(msp);
		
		//设置JFrame窗体
		this.setTitle("坦克大战");//JFrame标题
		this.setSize(600, 500);//JFrame窗体大小
		this.setLocationRelativeTo(null);//在屏幕中心显示
		this.setVisible(true);//显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出并关闭JFrame
	}
 
	public void actionPerformed(ActionEvent e) {
		//对用户不同的点击，做出不同的处理
		if(e.getActionCommand().equals("newgame")){
			//创建战场面板
			mp=new MyPanel("newgame");
			//启动mp线程
			Thread ta=new Thread(mp);
			ta.start();
			//先删除旧的开始面板
			this.remove(msp);
			//加入组件
			this.add(mp);
			//监听
			this.addKeyListener(mp);  //键盘监听
			//显示，刷新JFrame
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//用户点击了退出菜单
			//保存用户游戏记录数据
			Recorder.keepRecording();
			System.exit(0);
		}else if(e.getActionCommand().equals("save")){
			//用户点击保存菜单
			//保存击毁敌人的数量和敌人的坐标
			Recorder.setEts(mp.ets);
			Recorder.keepRecAndEnemyTank();
			System.exit(0);
		}else if(e.getActionCommand().equals("congame")){
			//继续上次游戏
			//创建战场面板
			mp=new MyPanel("congame");
			
			//启动mp线程
			Thread ta=new Thread(mp);
			ta.start();
			//先删除旧的开始面板
			this.remove(msp);
			//加入组件
			this.add(mp);
			//监听
			this.addKeyListener(mp);
			//显示，刷新JFrame
			this.setVisible(true);
		}
	}
}
//开始面板--起提示作用
class MyStartPanel extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		//提示信息
		if(times%2==0){
			g.setColor(Color.yellow);
			//开关信息的字体
			Font myFont=new Font("华文新魏", Font.BOLD, 30);
			g.setFont(myFont);
			g.drawString("Stage: 1", 150, 150);
		}	
	}
 
	public void run() {
		while(true){
			//休眠
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			times++;
			//重画
			this.repaint();
		}
	}
}
 
//我的面板Panel
class MyPanel extends JPanel implements KeyListener,Runnable{//MyPanel继承Jpanel面板，创建KeyListener键盘接口和Runnable线程接口
	//定义一个我的坦克
	Hero hero=null;
	//定义声音播放
	AePlayWave apw=null;
 
	//定义敌人的坦克组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Node> nodes=new Vector<Node>();
	
	//定义炸弹集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int enSize=3;//初始敌人坦克数量
	
	//定义爆炸图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
		
	//构造函数
	public MyPanel(String flag){
		hero=new Hero(200, 270);//我的坦克初始位置
		//恢复游戏记录
		Recorder.getRecoring();
		if(flag.equals("newgame")){
			//播放开战声音
			apw=new AePlayWave("/Users/a1/eclipse-workspace/MyTank07/Monody.wav");
			apw.start();
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++){
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank((i+1)*50, 0);
				//初始敌人坦克颜色
				et.setColor(2);
				//初始敌人坦克的方向
				et.setDirect(1);
				
				//将MyPanel的敌人向量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人坦克进程
				Thread t=new Thread(et);
				t.start();
				
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10, et.y+30, 1);
				//把子弹加入给敌人
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//加入敌人坦克
				ets.add(et);
			}
		}else if(flag.equals("congame")){
			nodes=new Recorder().getNodesAndEnNums();
			//初始化敌人的坦克
			for(int i=0;i<nodes.size();i++){
				Node node=nodes.get(i);
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank(node.x, node.y);
				//初始敌人坦克颜色
				et.setColor(2);
				//初始敌人坦克的方向
				et.setDirect(node.direct);
				
				//将MyPanel的敌人向量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人坦克进程
				Thread t=new Thread(et);
				t.start();
				
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10, et.y+30, 1);
				//把子弹加入给敌人
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				
				//加入敌人坦克
				ets.add(et);
			}
		}
		
		//初始化爆炸图片
		try {
			image1=ImageIO.read(new File("bomb1.gif"));
			image2=ImageIO.read(new File("bomb2.gif"));
			image3=ImageIO.read(new File("bomb3.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//画出提示信息
	public void showInfo(Graphics g){
		//画出提示信息坦克(该坦克不参与战斗，只是用于显示提示信息)
		this.drawTank(80, 330, g, 1, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString(Recorder.getEnNum()+"", 110, 350);//显示敌人坦克数量
		
		this.drawTank(140, 330, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString(Recorder.getMyLife()+"", 170, 350);//显示我的坦克数量
	
		//画出玩家的总成绩
		g.setColor(Color.black);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString("您的总成绩", 420, 30);
		
		this.drawTank(420, 60, g, 1, 0);
		g.setColor(Color.black);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	//重写paint函数
	public void paint(Graphics g){
		super.paint(g);//调用父类paint方法
		
		//画出提示信息
		this.showInfo(g);
		
		//设置Panel底色
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);//fillRect(0,0,X?,Y?)中X?/Y?为活动区域
		
		//画出自己的坦克
		if(hero.isLive==true){
			this.drawTank(hero.getX(), hero.getY(), g, 0, this.hero.direct);
		}
		
		//从ss向量中取出每一颗子弹并画出
		for(int i=0;i<this.hero.ss.size();i++){
			Shot myShot=hero.ss.get(i);
			//画出一颗子弹，判断子弹是否为空
			if(myShot!=null&&myShot.isLive==true){
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			//判断子弹是否死亡
			if(myShot.isLive==false){
				//从向量ss中删除该子弹
				hero.ss.remove(myShot);
			}
		}
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++){
			//取出炸弹
			Bomb b=bombs.get(i);
			if(b.life>6){
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3){
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//让b的生命值减少
			b.lifeDown();
			//如果炸弹生命值为0，就把该炸弹从bombs向量中去掉
			if(b.life==0){
				bombs.remove(b);
			}
		}
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++){
			EnemyTank et=ets.get(i);
			if(et.isLive){
				this.drawTank(et.getX(), et.getY() , g, 1, et.getDirect());
				//再画出敌人坦克的子弹
				for(int j=0;j<et.ss.size();j++){
					//取出子弹
					Shot enemyShot=et.ss.get(j);
					if(enemyShot.isLive){
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else{
						//如果敌人的坦克死亡了就从向量Vector中去掉
						et.ss.remove(enemyShot);
					}
				}
			}
		}
	}
	
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank(){
		//遍历Vector集合类
		for(int i=0;i<hero.ss.size();i++){
			//先取子弹
			Shot myShot=hero.ss.get(i);
			//判断子弹是否有效
			if(myShot.isLive){
				//取出每一个敌人坦克与子弹进行判断
				for(int j=0;j<ets.size();j++){
					//取出坦克
					EnemyTank et=ets.get(j);
					//判断敌人坦克是否还活着
					if(et.isLive){
						if(this.hitTank(myShot, et)){
							apw=new AePlayWave("/Users/a1/eclipse-workspace/MyTank07/Monody.wav");
							apw.start();
							//调用reduceEnNum()减少敌人坦克统计数
							Recorder.reduceEnNum();
							//调用addEnNumRec()增加消灭敌人坦克统计数
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//判断敌人的子弹是否击中我的坦克
	public void hitMe(){
		//取出每一个敌人的坦克
		for(int i=0;i<this.ets.size();i++){
			//取出敌人的坦克
			EnemyTank et=ets.get(i);
			//取出每一颗敌人的子弹
			for(int j=0;j<et.ss.size();j++){
				//取出子弹
				Shot enemyShot=et.ss.get(j);
				
				if(hero.isLive){
					if(this.hitTank(enemyShot, hero)){
						
					}
				}
			}
		}
	}
 
	//写一个函数专门判断子弹是否击中敌人坦克
	public boolean hitTank(Shot s,Tank et){
		boolean b=false;
		//判断该敌人坦克的方向
		switch(et.direct){
		case 0://敌人坦克向上或向下
		case 1:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+29){
				//击中方向为上或下的敌人坦克
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				b=true;
				//创建一颗炸弹
				Bomb bomb=new Bomb(et.x, et.y);
				//放入Vector向量中管理
				bombs.add(bomb);
			}
			break;
		case 2://敌人坦克向左或向右
		case 3:
			if(s.x>et.x&&s.x<et.x+29&&s.y>et.y&&s.y<et.y+20){
				//击中方向为左或右的敌人坦克
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				b=true;
				//创建一颗炸弹
				Bomb bomb=new Bomb(et.x, et.y);
				//放入Vector向量中管理
				bombs.add(bomb);
			}
			break;
		}
		return b;
	}
	
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int type,int direct){
		//判断是什么类型的坦克
		switch(type){
		case 0:
			g.setColor(Color.cyan);//我的坦克颜色
			break;
		case 1:
			g.setColor(Color.white);//敌人坦克颜色
			break;
		case 2:
			g.setColor(Color.red);
			break;
		}
		
		//判断坦克的方向
		switch(direct){
		//向上走的坦克
		case 0:
			//画出我的坦克(到时再封装成一个函数)
			//1、画出左边的矩形
			g.fill3DRect(x, y, 5, 30, false);
			//2、画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//3、画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4、画出中间圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5、画出线(炮筒)
			g.drawLine(x+10, y+15, x+10, y);
			break;
		//向下走的坦克
		case 1:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+29);
			break;
		//向左走的坦克
		case 2:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
		//向右走的坦克
		case 3:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+29, y+10);
			break;
		}
	}
 
	public void keyPressed(KeyEvent e) {//按下键事件a向左s向下d向右w向上
		if(e.getKeyCode()==KeyEvent.VK_W||e.getKeyCode()==KeyEvent.VK_UP){
			//向上
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_DOWN){
			//设置我的坦克的方向,向下
			this.hero.setDirect(1);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_LEFT){
			//向左
			this.hero.setDirect(2);
			this.hero.moveLeft();
		}else if(e.getKeyCode()==KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT){
			//向右
			this.hero.setDirect(3);
			this.hero.moveRight();
		}
		
		//判断玩家是否按下空格键,不可接上面else if。不然不能同时按方向键和空格(space)键
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			apw=new AePlayWave("e:\\tankgame\\tank_Shelling_sound.wav");
			apw.start();
			//控制子弹连发
			if(this.hero.ss.size()<=4){
				//按下空格后开火
				this.hero.shotEnemy();
			}
		}
		
		//调用repaint()函数，来重绘界面
		this.repaint();
	}
 
	public void keyReleased(KeyEvent e) {//弹起键事件
		
	}
	
	public void keyTyped(KeyEvent e) {//按键输出值
		
	}
 
	//重写run函数
	public void run() {
		while(true){
			try {
				Thread.sleep(100);//休息100毫秒后重绘MyPanel面板
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.hitEnemyTank(); 
			//判断敌人的子弹是否击中我的坦克
			this.hitMe();
 
			//重绘MyPanel面板
			this.repaint();
		}
	}
}