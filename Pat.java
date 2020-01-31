package koalaChess;

import java.awt.Image;
/*

*/

public class Pieces {
	private String name;		//名字,显示该棋子的名字
	private int  intensity;		//强度，分为0，2，4，6，8，10，12，14档，依次为鼠，猫，狼，豹（熊），虎，狮，象，考拉（兔）
	private int LocationX;		//x轴坐标，在wall二维数组中的x坐标，也是第二个值
	private int LocationY;		//y轴坐标，在wall二维数组中的y坐标，也是第一个值
	private boolean state;		//状态  false背面  true 正面，用于描述棋子的正反两面
	private int faction;		//派别	1红，2蓝，用于描述棋子的组别，红蓝方
	private boolean die;		//死了？true 死了，false没死
	private Image image;		//图片，加载对应图片
	
	//构造函数
	//参数为（名字（string），强度（int），派别（int），图片（Image））
	public Pieces(String name,int inten,int fac,Image image) {
		this.name=name;
		this.intensity =inten;
		//this.LocationX =x;
		//this.LocationY =y;
		this.state=false;
		this.faction=fac;
		this.die=false;
		this.image=image;
	}
	
	//构造函数
	//参数为Pieces
	public Pieces(Pieces It) {
		this.name=It.name;
		this.intensity =It.intensity;
		this.LocationX =It.LocationX;
		this.LocationY =It.LocationY;
		this.state=It.state;
		this.faction=It.faction;
		this.die=It.die;
		this.image=It.image;
	}
	
	//复制函数
	//参数是Pieces
	public void copy(Pieces It) {
		this.name=It.name;
		this.intensity =It.intensity;
		this.LocationX =It.LocationX;
		this.LocationY =It.LocationY;
		this.state=It.state;
		this.faction=It.faction;
		this.die=It.die;
		this.image=It.image;
	}
	//基础转移的函数：
	//
	//改变棋子的正反面
	public void setState() {
		this.state=true;
	}
	//判断棋子的正反面状态
	//返回这个值
	public boolean getState() {
		return this.state;
	}
	//返回x坐标
	public int getX() {
		return this.LocationX;
	}
	//设置他的x坐标
	public void setX(int X) {
		this.LocationX=X;
	}
	//返回y坐标
	public int getY() {
		return this.LocationY;
	}
	//设置他的y坐标
	public void setY(int Y) {
		this.LocationY=Y;
	}
	//向左移动
	//对横坐标x进行修改，x=x-1
	public void Left() {
		LocationX--;
	}
	//向右移动
	//对横坐标x进行修改，x=x+1
	public void Right() {
		LocationX++;
	}
	//向上移动
	//对纵坐标y进行修改，y=y-1
	public void Up() {
		LocationY--;
	}
	//向下移动
	//对纵坐标y进行修改，y=y+1
	public void Down() {
		LocationY++;
	}
	//判断棋子的死亡状态
	//返回这个状态
	public boolean getDie() {
		return this.die;
	}
	//
	//
	//吃法：
	//吃子：
	
	public void Eat(Pieces It) {
		/*
		第一步判断It和this的强度值
		
			如果相等  两个子的描述死亡die都变成true，使他们形成互换
			并把他们的强度都修改成-1
			
			如果不相等  把this的x，y都变成It的x，y，使this的位置变成It的位置
			并把It的die变成true，形成this吃掉It
		*/
		if(this.intensity==It.intensity) {
			this.die=true;
			It.intensity=-1;
			this.intensity=-1;
			//It.faction=0;
			//this.faction=0;
			It.die=true;
		}else {
			this.setX(It.getX());
			this.setY(It.getY());
			It.intensity=-1;
			//It.faction=0;
			It.die=true;
		}
	}
	
	//判断能否吃子
	/*
	第一步判断It的die
		如果die为true，则返回true
		如果die为false，则不做任何行动
	第二步判断It的正反状态
		如果为false（背面），则返回false
		如果为true（正面），则不做任何行动
	第三步判断It和this的组别（红蓝方）
		如果相等，二者为一方，返回false
		如果不相等，进入第四步
	第四步判断强度
		如果为It=14（考拉），无法被吃，则返回false
		如果为this=0，It=12，属于this为鼠，It为象的鼠吃象情况，返回true
		如果为this=12，It=0，属于this为象，It为鼠的象不能吃鼠情况，返回false
		特殊情况排除后，进行this的强度和It的强度比对
		如果this>=It，属于正常吃子情况，返回true
	第五步意料之外情况
		均返回false
	
	*/
	
	public boolean CanEat(Pieces It) {
		if(It.getDie()) {
			return true;
		}
		if(!It.state) {
			return false;
		}
		if(this.faction==It.faction) {
			return false;
		}
		else if(It.intensity==14) {
			return false;
		}else if(this.intensity==0&&It.intensity==12) {
			return true;
		}else if(this.intensity==12&&It.intensity==0) {
			return false;
		}else if(this.intensity>=It.intensity) {
			return true;
		}
		return false;
	}
	
	
	//返回image的值，用于paint函数的显示
	public Image getImage() {
		return image;
	}
	//返回名字，用于选定时的提示
	public String getName() {
		return name;
	}
	//返回组别，用于点击事件的组别获取
	public int getFaction() {
		return faction;
	}
	//设置image，用于paint函数的显示
	public void setImage(Image It) {
		this.image=It;
	}
	//设置die的值，用于类外修改
	public void setDie() {
		this.die=true;
	}
	//返回强度，用于类外判断
	public int getInt() {
		return this.intensity;
	}
	//设置强度，用于类外修改
	public void setInt(int a) {
		this.intensity=a;
	}
	

}
