/**
* �ڵ���
* ���ԣ����꣬�ٶȣ������˺�����С���ڵ��ĵ��ң���ɫ
* @author gc
*
*/
public class Bullet {
	private int x,y;
	private int speed = 8;
	private int dir;
	
	private int atk;
	private int diameter = 4;//diameter
	private boolean isFriendly;//�ڵ��ĵ�������
	private Color color;//bullet's color
	
	//�ӵ��Ƿ�ɼ�
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
		//һ���ӵ��ɳ���Ļ�������Ͳ�Ӧ���ٱ�ִ��
		//������ɼ�
		if (!visible)return;
		g.setColor(color);
		int w = diameter>>1;
		g.fillArc(x-w, y-w, diameter, diameter, 0, 360);
		//�ӵ��ķ��й켣
		logic();
		
		
	}
 
	//�ӵ��ķ��й켣
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
	//������������������ӵ��Ƿ�ɼ�������
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
 
}