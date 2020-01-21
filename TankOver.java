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
* ��ô��෢�ڵ���ʹ������װ�ڵ���ÿ������Ctr�������ʱ��������������ڵ�
* ����ÿһ���ڵ���
*/

List<Missile> missiles = new ArrayList<Missile>(); // �����ȽϿ�
// //LinkedList<>;//����ɾ���죬������
// ������ը
List<Explode> explodes = new ArrayList<Explode>();
private Image offScreenImage = null;
// ��̹�˴���
// ����
int c = 1;
List<Tank> tanks = new ArrayList<Tank>();
// ��д
Blood b = new Blood();
/**
* //������˸��ʹ��˫����
//�߳��ػ����Ӿ��ȣ����ܿ����ػ����ٶȡ������ػ����ܽ���ӵ��Զ����е����⣻
*/
public void update(Graphics g) {
if (offScreenImage == null) {
offScreenImage = this.createImage(GAME_WIDTH, GAME_HIGHT);
}
// �õ�ͼƬ�ϵĻ���
Graphics gOffScreen = offScreenImage.getGraphics();
Color c = gOffScreen.getColor();
gOffScreen.setColor(Color.blue);
gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HIGHT);
gOffScreen.setColor(c);
paint(gOffScreen);// ���ڱ���ͼƬ��
g.drawImage(offScreenImage, 0, 0, null);// ������Ļ��

}
/**
* // �������ж����ڵ�����һ���ַ�������Ļ
*/
public void paint(Graphics g) {

g.drawString("missiles count:" + missiles.size(), 30, 50);
g.drawString("Explode count:" + explodes.size(), 30, 70);
g.drawString("Tanks count:" + tanks.size(), 30, 90);
g.drawString("tanks life:" + myTank.getLife(), 30, 110);
g.drawString("�ؿ����� -" + c + "-", 30, 130);
g.drawString("pΪ��ͣ ", 30, 150);
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
* ����������ټ��룬CΪ�ؿ�������ͬ�Ĺؿ���̹���������Լ����ֵĵط���ͬ
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
* ���̹�ˣ��Լ����ô���
*/
public void lauchFrame() {
for (int i = 0; i < 10; i++) {
tanks.add(new Tank((i + 1) * 50 + 100, 50, false, Tank.Direction.D, tc));
}
setTitle("TankWar");
setBounds(230, 100, 800, 600);
// �����ڲ��࣬�̣����漰��������չ
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
* // �ڲ��ֻ࣬Ϊ���TankWar���񣬷���ķ��ʰ�װ��ķ����������㹫����
* @author yan
*
*/
private class PaintThread implements Runnable {
public void run() {
while (true) {
//��Ϸ��ͣ�йأ��̲߳��ܹأ������Ժ�Ļ�������ķ�������������
if(p==true){
repaint();}
// �ڲ����ø����paint������
try {
Thread.sleep(50);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}
}

/**
* // �����¼����ڲ���
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
//Ѫ����

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
//Ѫ��
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
* ��ʼ��Ѫ��
*/
public Blood(){
w=h=20;
x=300;
y=300;

}
/**
* �����趨��һ��Ѫ��������ֵĵص㣬�����̴߳�������˸���������߳������ƣ�
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
* Ѫ�����ײ���
* @return
*/
public Rectangle getRect(){
return new Rectangle(x,y,w,h);
}

}
*******************************************

//��ը��

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
tc.explodes.remove(this);//��ը��ɺ��Ƴ��˶���
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
//�ڵ���
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import Frame.Tank.Direction;
/**
* // �ڵ�������״̬��Ĭ��Ϊ�������֣��򵹵���Ϊ��
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
* // �õ���ΧС����,(��ײ��⸨����Rectangle)
* @return
*/
public Rectangle getRect() {
return new Rectangle(x, y, WIDTH, HEIGHT);
}
/**
* // ��ӱ�ը�����б������
* @param t
* @return
*/
public boolean hitTank(Tank t) {
if (this.Live&&this.getRect().intersects(t.getRect()) && t.isLive() == true&&this.good!=t.isGood()) {// ����Ƿ���ײ,��ײ֮�󣬻����ж�̹�������������������ӵ����Ǹ��ط����ǻ���ʧ
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
* // ��һ�������̹�˽�����������
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
* ̹����ײ��ǽ
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
//̹����

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
private boolean good;//�õĻ���
public boolean isGood() {
return good;
}
private boolean Live=true;//���
private static Random random=new Random();
private int step=random.nextInt(12)+3;//���������i=10�����û�̹�˸ı䷽��
public boolean isLive() {
return Live;
}

public void setLive(boolean live) {
Live = live;
}
public enum Direction {
L, LU, U, RU, R, RD, LD, D, STOP
};// ö������

private Direction dir = Direction.STOP;
//����һ����Ͳ�ķ���
private Direction ptDir=Direction.D;
public Tank(int x, int y,boolean good) {

this.x = x;
this.y = y;
this.good=good;
}

public Tank(int x,int y, boolean good,Direction dir,TankClient tc){
this(x, y,good);//������һ�����췽��
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

//����̹�˵ķ���һ��ֱ�ߵ�����Ͳ��
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

// �ƶ�̹��
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
//̹�˳�������
if(x<=0)x=0;
if(y<=30)y=30;
if(x+Tank.HEIGHT>=TankClient.GAME_WIDTH)x=TankClient.GAME_WIDTH-Tank.WIDTH;
if(y+Tank.HEIGHT>=TankClient.GAME_HIGHT)y=TankClient.GAME_HIGHT-Tank.HEIGHT;

if(dir!=Direction.STOP){
ptDir=dir;
}
//����̹���Լ�����˶�
//���i=10�����û�̹�˸ı䷽��
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
// ��ü���������
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
case KeyEvent.VK_P://��Ϸ��ͣ
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
// ��ü���������
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
//�������һ����ͣ������ǰ�澹Ȼ�õ��ڲ�����ʹ���̣߳�����Ҫ��ġ�������
break;
}
locateDirection();

}

//����
public Missile fire(){
if(!Live){return null;}
Missile m=new Missile(x+Tank.WIDTH/2-Missile.WIDTH/2,y+Tank.HEIGHT/2-Missile.HEIGHT/2,ptDir,good,tc);//����̹�˵�λ�úͷ���
tc.missiles.add(m);

return m;


}
public Missile fire(Direction dir){
if(!Live){return null;}
Missile m=new Missile(x+Tank.WIDTH/2-Missile.WIDTH/2,y+Tank.HEIGHT/2-Missile.HEIGHT/2,dir,good,tc);//����̹�˵�λ�úͷ���
tc.missiles.add(m);
return m;

}
////�õ���ΧС����,(��ײ��⸨����Rectangle)
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
//ǽ����
import java.awt.Graphics;
import java.awt.Rectangle;
/**
* ǽ����
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
* (��ײ��⸨����Rectangle)
* @return
*/
public Rectangle getRect(){
return new Rectangle(x,y,w,h);

}
}