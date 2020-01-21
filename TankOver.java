import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankClient extends Frame {
public static final int GAME_WIDTH = 800;
public static final int GAME_HIGHT = 600;
private static TankClient tc;
public static boolean p=true;

Wall w1 = new Wall(100, 200, 20, 150, this), w2 = new Wall(300, 100, 300, 20, this);
Tank myTank = new Tank(50, 50, true, Tank.Direction.STOP, this);
/**
* 怎么打多发炮弹，使用容器装炮弹，每当按下Ctr这个键的时候往容器中添加炮弹
* 画出每一个炮弹；
*/

List<Missile> missiles = new ArrayList<Missile>(); // 遍历比较快
// //LinkedList<>;//链表删除快，查找慢
// 画出爆炸
List<Explode> explodes = new ArrayList<Explode>();
private Image offScreenImage = null;
// 坏坦克创建
// 关数
int c = 1;
List<Tank> tanks = new ArrayList<Tank>();
// 重写
Blood b = new Blood();
/**
* //消除闪烁，使用双缓冲
//线程重画更加均匀，更能控制重化的速度。按键重画不能解决子弹自动飞行的问题；
*/
public void update(Graphics g) {
if (offScreenImage == null) {
offScreenImage = this.createImage(GAME_WIDTH, GAME_HIGHT);
}
// 拿到图片上的画笔
Graphics gOffScreen = offScreenImage.getGraphics();
Color c = gOffScreen.getColor();
gOffScreen.setColor(Color.blue);
gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HIGHT);
gOffScreen.setColor(c);
paint(gOffScreen);// 画在背后图片上
g.drawImage(offScreenImage, 0, 0, null);// 画在屏幕上

}
/**
* // 画出还有多少炮弹，画一个字符串到屏幕
*/
public void paint(Graphics g) {

g.drawString("missiles count:" + missiles.size(), 30, 50);
g.drawString("Explode count:" + explodes.size(), 30, 70);
g.drawString("Tanks count:" + tanks.size(), 30, 90);
g.drawString("tanks life:" + myTank.getLife(), 30, 110);
g.drawString("关卡数： -" + c + "-", 30, 130);
g.drawString("p为暂停 ", 30, 150);
for (int i = 0; i < missiles.size(); i++) {
Missile m = missiles.get(i);
m.draw(g);
m.hitTank(tanks);
m.hitTank(myTank);
m.hitWall(w1);
m.hitWall(w2);

}
for (int i = 0; i < tanks.size(); i++) {
Tank t = tanks.get(i);
t.collideWithWall(w1);
t.collideWithWall(w2);
t.collidesWithTanks(tanks);
t.draw(g);
myTank.collideWithWall(w1);
myTank.collideWithWall(w2);
}
for (int i = 0; i < explodes.size(); i++) {
Explode e = explodes.get(i);
e.draw(g);

}

myTank.draw(g);
w1.draw(g);
w2.draw(g);
myTank.eat(b);
if(myTank.getLife()<=40){
b.draw(g);
}
/**
* 敌人死完后再加入，C为关卡数，不同的关卡，坦克数量，以及出现的地方不同
*/
if (tanks.size() == 0) {
c++;
if (c > 10) {
for (int i = 0; i < 15; i++) {
tanks.add(new Tank((i) * 50, 50, false, Tank.Direction.D, tc));
}
} else if(c>5){
for (int j = 0; j < 10; j++) {
tanks.add(new Tank( 300,j*40+50, false, Tank.Direction.D, tc));
}
}else {
for (int i = 0; i < 10; i++) {
tanks.add(new Tank((i) * 50, 50, false, Tank.Direction.D, tc));
}
}
}
}
/**
* 添加坦克，以及设置窗体
*/
public void lauchFrame() {
for (int i = 0; i < 10; i++) {
tanks.add(new Tank((i + 1) * 50 + 100, 50, false, Tank.Direction.D, tc));
}
setTitle("TankWar");
setBounds(230, 100, 800, 600);
// 匿名内部类，短，不涉及将来的扩展
addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e) {
System.exit(0);
}
});

setResizable(false);
setBackground(Color.GREEN);
setVisible(true);
addKeyListener(new KeyMoniter());
new Thread(new PaintThread()).start();
}

public static void main(String[] args) {
tc = new TankClient();
tc.lauchFrame();

}

/**
* // 内部类，只为这个TankWar服务，方便的访问包装类的方法，不方便公开，
* @author yan
*
*/
private class PaintThread implements Runnable {
public void run() {
while (true) {
//游戏暂停有关，线程不能关，关了以后的话，后面的方法都不会运行
if(p==true){
repaint();}
// 内部调用父类的paint方法；
try {
Thread.sleep(50);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}
}

/**
* // 键盘事件，内部类
* @author yan
*
*/
private class KeyMoniter extends KeyAdapter {
public void keyReleased(KeyEvent e) {
myTank.keyReleased(e);
}

public void keyPressed(KeyEvent e) {
myTank.keyPressed(e);
}
}

}
*********************************************************
//血块类

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
//血块
public class Blood {
int step=0;
int x,y,w,h;
Random ran=new Random();
private boolean live=true;
public boolean isLive() {
return live;
}
public void setLive(boolean live) {
this.live = live;
}
TankClient tc;
/**
* 初始化血块
*/
public Blood(){
w=h=20;
x=300;
y=300;

}
/**
* 用来设定下一次血块随机出现的地点，避免线程带来的闪烁，（不归线程来控制）
*/
public void setBlood(){
x=ran.nextInt(500)+50;
y=ran.nextInt(500)+50;
live=true;
}
public void draw(Graphics g){
if(!live){
return ;
}

Color c=g.getColor();
g.setColor(Color.MAGENTA);
g.fillRect(x, y, w, h);
g.setColor(c);

}
/**
* 血块的碰撞检测
* @return
*/
public Rectangle getRect(){
return new Rectangle(x,y,w,h);
}

}
*******************************************

//爆炸类

import java.awt.Color;
import java.awt.Graphics;

public class Explode {
int x,y;
private boolean live=true;
private TankClient tc;
public Explode(int x,int y,TankClient tc){
this.x=x;
this.y=y;
this.tc=tc;

}
int[] diameter={4,6,7,10,12,15,18,21,25,26,30,32,35,40,45,49,30,14,6};
int step;
public void draw(Graphics g){
if(step==diameter.length){
live=false;
tc.explodes.remove(this);//爆炸完成后移除此对象
step=0;
return;
}
if(!live)return;
Color c = g.getColor();
g.setColor(Color.green);
g.fillOval(x, y, diameter[step], diameter[step]);
step++;
g.setColor(c);
}

}


*********************************************************
//炮弹类
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import Frame.Tank.Direction;
/**
* // 炮弹的生死状态，默认为生，出街，打倒敌人为死
* @author yan
*
*/
public class Missile {
private static final int xspeed = 8;
private static final int yspeed = 8;
public static final int WIDTH = 10;
public static final int HEIGHT = 10;
private List<Tank> tanks;
private boolean Live = true;
private boolean good=true;

public boolean isLive() {
return Live;
}

int x, y;
Tank.Direction dir;
private TankClient tc;

public Missile(int x, int y, Direction dir) {

this.x = x;
this.y = y;
this.dir = dir;
}

public Missile(int x, int y, Direction dir,boolean good,TankClient tc) {
this(x, y, dir);
this.good=good;
this.tc = tc;
}

public void draw(Graphics g) {
if (!Live) {
tc.missiles.remove(this);
}

Color c = g.getColor();
g.setColor(Color.black);
g.fillOval(x, y, WIDTH, HEIGHT);
g.setColor(c);
move();

}

private void move() {
switch (dir) {
case L:
x -= xspeed;
break;
case LU:
x -= xspeed;
y -= yspeed;
break;
case U:
y -= yspeed;
break;
case RU:
x += xspeed;
y -= yspeed;
break;

case R:
x += xspeed;
break;
case RD:
x += xspeed;
y += yspeed;
break;
case LD:
x -= xspeed;
y += yspeed;
break;
case D:
y += yspeed;
break;

}
if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HIGHT) {
Live = false;
tc.missiles.remove(this);
}

}

/**
* // 得到外围小方块,(碰撞检测辅助类Rectangle)
* @return
*/
public Rectangle getRect() {
return new Rectangle(x, y, WIDTH, HEIGHT);
}
/**
* // 添加爆炸对象到列表，射击类
* @param t
* @return
*/
public boolean hitTank(Tank t) {
if (this.Live&&this.getRect().intersects(t.getRect()) && t.isLive() == true&&this.good!=t.isGood()) {// 检测是否碰撞,碰撞之后，还得判断坦克是生还是死，否则子弹在那个地方还是会消失
if(t.isGood()){
t.setLife(t.getLife()-20);
if(t.getLife()<=0)
t.setLive(false);
}else{
t.setLive(false);
}

Explode e = new Explode(x, y, tc);
tc.explodes.add(e);
this.Live = false;
return true;
}
return false;
}
/**
* // 对一个数组的坦克进行射击类添加
* @param tanks
* @return
*/
public boolean hitTank(List<Tank> tanks) {
for (int i = 0; i < tanks.size(); i++) {
if (hitTank(tanks.get(i))) {
return true;
}
}
return false;

}
/**
* 坦克碰撞到墙
* @param w
* @return
*/
public boolean hitWall(Wall w){
if(this.Live&&this.getRect().intersects(w.getRect())){
this.Live=false;
return true;
}

return false;

}

}

********************************************************
//坦克类

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Tank {
public static final int xspeed = 5;
public static final int yspeed = 5;
public static final int WIDTH=30;
public static final int HEIGHT=30;
private BloodBar bb=new BloodBar();
TankClient tc=null;
private int life=100;
public int getLife() {
return life;
}

public void setLife(int life) {
this.life = life;
}
int x, y;
int oldx,oldy;
private boolean bL = false, bU = false, bR = false, bD = false;
private boolean good;//好的坏的
public boolean isGood() {
return good;
}
private boolean Live=true;//活的
private static Random random=new Random();
private int step=random.nextInt(12)+3;//计数，如果i=10，则让坏坦克改变方向
public boolean isLive() {
return Live;
}

public void setLive(boolean live) {
Live = live;
}
public enum Direction {
L, LU, U, RU, R, RD, LD, D, STOP
};// 枚举类型

private Direction dir = Direction.STOP;
//定义一个炮筒的方向；
private Direction ptDir=Direction.D;
public Tank(int x, int y,boolean good) {

this.x = x;
this.y = y;
this.good=good;
}

public Tank(int x,int y, boolean good,Direction dir,TankClient tc){
this(x, y,good);//调用上一个构造方法
this.dir=dir;
this.tc=tc;
}

public void draw(Graphics g) {
if(good)bb.draw(g);
if(Live==false){
if(!good){
tc.tanks.remove(this);
}
return;
}
Color c = g.getColor();
if(good==true){
g.setColor(Color.cyan);
}else{
g.setColor(Color.yellow);
}
g.fillOval(x, y, WIDTH, HEIGHT);
g.setColor(c);

//根据坦克的方向画一条直线当作炮筒；
switch (ptDir) {
case L:

g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT/2);
break; 
case LU:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y);
break;
case U:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH/2, y);
break;
case RU:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y);
break;
case R:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y+Tank.HEIGHT/2);
break;
case RD:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y+Tank.HEIGHT);
break;
case LD:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT);	
break;
case D:
g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH/2, y+Tank.HEIGHT);
break;

}
move();
}

// 移动坦克
void move() {
oldx=x;
oldy=y;
switch (dir) {
case L:
x -= xspeed;
break;
case LU:
x -= xspeed;
y -= yspeed;
break;
case U:
y -= yspeed;
break;
case RU:
x += xspeed;
y -= yspeed;
break;

case R:
x += xspeed;
break;
case RD:
x += xspeed;
y += yspeed;
break;
case LD:
x -= xspeed;
y += yspeed;
break;
case D:
y += yspeed;
break;
case STOP:
break;

}
//坦克出界问题
if(x<=0)x=0;
if(y<=30)y=30;
if(x+Tank.HEIGHT>=TankClient.GAME_WIDTH)x=TankClient.GAME_WIDTH-Tank.WIDTH;
if(y+Tank.HEIGHT>=TankClient.GAME_HIGHT)y=TankClient.GAME_HIGHT-Tank.HEIGHT;

if(dir!=Direction.STOP){
ptDir=dir;
}
//给坏坦克自己随机运动
//如果i=10，则让坏坦克改变方向
if(!good){
step--;
if(step==0){
Direction[] dirs=Direction.values();
int r=random.nextInt(dirs.length);
dir=dirs[r];
step=random.nextInt(12)+3;
}
int r1=random.nextInt(40);
if(r1>35){
this.fire();
}
}
}

public void keyPressed(KeyEvent e) {
// 获得键的虚拟码
int key = e.getKeyCode();
// if(key==KeyEvent.VK_RIGHT){
// x+=5;
// }if(key==KeyEvent.VK_LEFT){
// x=x-5;
// }if(key==KeyEvent.VK_UP){
// y=y-5;
// }if(key==KeyEvent.VK_DOWN){
// y=y+5;
// }
switch (key) {

case KeyEvent.VK_LEFT:
bL = true;
break;
case KeyEvent.VK_UP:
bU = true;
break;
case KeyEvent.VK_RIGHT:
bR = true;
break;
case KeyEvent.VK_DOWN:
bD = true; 
break;
case KeyEvent.VK_P://游戏暂停
if(tc.p==true){
tc.p=false;
}else{
tc.p=true;
}
break;
case KeyEvent.VK_S:

break;
}
locateDirection();
}

void locateDirection() {
if(bL&&!bU&&!bR&&!bD) {dir=Direction.L;}
else if(bL&&bU&&!bR&&!bD) {dir=Direction.LU;}
else if(!bL&&bU&&!bR&&!bD){ dir=Direction.U;}
else if(!bL&&bU&&bR&&!bD) {dir=Direction.RU;}
else if (!bL&&!bU&&!bR&&bD){ dir=Direction.D;}
else if(!bL&&!bU&&bR&&!bD) {dir=Direction.R;}
else if(!bL&&!bU&&bR&&bD) {dir=Direction.RD;}
else if(bL&&!bU&&!bR&&bD) {dir=Direction.LD;}
else if(!bL&&!bU&&!bR&&!bD) dir=Direction.STOP;

}

private void stay(){
x=oldx;
y=oldy;
}


public void keyReleased(KeyEvent e) {
// 获得键的虚拟码
int key = e.getKeyCode();
switch (key) {
case KeyEvent.VK_CONTROL:
fire();
break;
case KeyEvent.VK_LEFT:
bL = false;
break;
case KeyEvent.VK_UP:
bU = false;
break;
case KeyEvent.VK_RIGHT:
bR = false;
break;
case KeyEvent.VK_DOWN:
bD = false;
break;
case KeyEvent.VK_A:
this.superFire();
break;
case KeyEvent.VK_F2:
if(!this.Live){
this.life=100;
this.Live=true;
}
break;
case KeyEvent.VK_P:
//本来想加一个暂停，但是前面竟然用的内部类来使用线程，所以要大改。。。。
break;
}
locateDirection();

}

//开火
public Missile fire(){
if(!Live){return null;}
Missile m=new Missile(x+Tank.WIDTH/2-Missile.WIDTH/2,y+Tank.HEIGHT/2-Missile.HEIGHT/2,ptDir,good,tc);//传递坦克的位置和方向
tc.missiles.add(m);

return m;


}
public Missile fire(Direction dir){
if(!Live){return null;}
Missile m=new Missile(x+Tank.WIDTH/2-Missile.WIDTH/2,y+Tank.HEIGHT/2-Missile.HEIGHT/2,dir,good,tc);//传递坦克的位置和方向
tc.missiles.add(m);
return m;

}
////得到外围小方块,(碰撞检测辅助类Rectangle)
public Rectangle getRect(){
return new Rectangle(x,y,WIDTH,HEIGHT);
}
public boolean collidesWithTanks(List<Tank>tanks){
for(int i=0;i<tanks.size();i++){
Tank t=tanks.get(i);
if(this!=t){
if(this.Live&&t.isLive()&&this.getRect().intersects(t.getRect())){
this.stay();
t.stay();
return true;
}
}
}
return false;
}
public boolean collideWithWall(Wall w){
if(this.Live&&this.getRect().intersects(w.getRect())){
this.stay();
}
return false;
}

private void superFire(){
Direction[] dirs=Direction.values();
for (int i=0;i<8;i++){
fire(dirs[i]);
}
}
private class BloodBar{
public void draw (Graphics g){
Color c=g.getColor();
g.setColor(Color.red);
g.drawRect(x, y-12, WIDTH, 10);
int w=WIDTH*life/100;
g.fillRect(x, y-12, w, 10);
g.setColor(c);

}
}
public boolean eat (Blood b){
if(this.Live&&b.isLive()&&this.getRect().intersects(b.getRect())){
this.life=100;
b.setLive(false);
b.setBlood();
return true;
}

return false;
}


}

**************************************************************
//墙壁类
import java.awt.Graphics;
import java.awt.Rectangle;
/**
* 墙壁类
* @author yan
*
*/
public class Wall {
int x,y,w,h;
TankClient tc;
public Wall(int x,int y,int w,int h,TankClient tc){
this.x=x;
this.y=y;
this.w=w;
this.h=h;
this.tc=tc;
}

public void draw(Graphics g){
g.fillRect(x, y, w, h);

}
/**
* (碰撞检测辅助类Rectangle)
* @return
*/
public Rectangle getRect(){
return new Rectangle(x,y,w,h);

}
}