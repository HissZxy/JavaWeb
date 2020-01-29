// 出现的位置和本身的大小
int x, y, size, speedx, speedy;
// 在次画布上画下,再把次画布画在界面上
Graphics g;
Framefish frame;

// 在鱼队列的生成中对每一个生成的小鱼初始化
public Fish(int x, int y, int speedx, int speedy, int size, Graphics g, Framefish frame) {
	this.x = x;
	this.y = y;
	this.speedx = speedx;
	this.speedy = speedy;
	this.size = size;
	this.g = g;
	this.frame = frame;
}

// 还活着就画下来,否则马上把这条鱼从队列中移除
public  void draw(Graphics g,ArrayList<Fish> list) {

		}

// 从队列中第一个鱼开始判断，大的鱼吃掉小的鱼
public void eat(ArrayList<Fish> list) {
	
}

// 每条鱼都有横纵速度，只要再界面内就继续移动,但不一定画出
public void move(ArrayList<Fish> list) {

}