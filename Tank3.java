/**
* 炮弹类
* 属性：坐标，速度，方向，伤害，大小，炮弹的敌我，颜色
* @author gc
*
*/
public class Bullet {
	private int x,y;
	private int speed = 8;
	private int dir;
	
	private int atk;
	private int diameter = 4;//diameter
	private boolean isFriendly;//炮弹的敌我属性
	private Color color;//bullet's color
	
	//子弹是否可见
	private boolean visible = true;
	
	public Bullet(int x, int y, int dir, int atk, boolean isFriendly, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.dir = dir;
		this.atk = atk;
		this.diameter = diameter;
		this.isFriendly = isFriendly;
		this.color = color;
	}
	
	public void draw(Graphics g) {
		//一旦子弹飞出屏幕，这代码就不应该再被执行
		//如果不可见
		if (!visible)return;
		g.setColor(color);
		int w = diameter>>1;
		g.fillArc(x-w, y-w, diameter, diameter, 0, 360);
		//子弹的飞行轨迹
		logic();
		
		
	}
 
	//子弹的飞行轨迹
	private void logic() {
		switch (dir) {
		case Tank.DIR_UP:
			y -= speed;
			if(y < 0)visible = false;
			break;
		case Tank.DIR_DOWN:
			y += speed;
			if(y > Constant.FRAME_HEIGHT)visible = false;
			break;
		case Tank.DIR_LEFT:
			x -= speed;
			if (x < 0)visible = false;
			break;
		case Tank.DIR_RIGHT:
			x += speed;
			if(x > Constant.FRAME_WIDTH)visible = false;
			break;
		}
		
	}
/////////////////////////////////////
	//增加在其他的类访问子弹是否可见的属性
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
 
}