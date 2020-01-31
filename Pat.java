package koalaChess;

import java.awt.Image;
/*

*/

public class Pieces {
	private String name;		//����,��ʾ�����ӵ�����
	private int  intensity;		//ǿ�ȣ���Ϊ0��2��4��6��8��10��12��14��������Ϊ��è���ǣ������ܣ�������ʨ���󣬿������ã�
	private int LocationX;		//x�����꣬��wall��ά�����е�x���꣬Ҳ�ǵڶ���ֵ
	private int LocationY;		//y�����꣬��wall��ά�����е�y���꣬Ҳ�ǵ�һ��ֵ
	private boolean state;		//״̬  false����  true ���棬�����������ӵ���������
	private int faction;		//�ɱ�	1�죬2���������������ӵ���𣬺�����
	private boolean die;		//���ˣ�true ���ˣ�falseû��
	private Image image;		//ͼƬ�����ض�ӦͼƬ
	
	//���캯��
	//����Ϊ�����֣�string����ǿ�ȣ�int�����ɱ�int����ͼƬ��Image����
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
	
	//���캯��
	//����ΪPieces
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
	
	//���ƺ���
	//������Pieces
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
	//����ת�Ƶĺ�����
	//
	//�ı����ӵ�������
	public void setState() {
		this.state=true;
	}
	//�ж����ӵ�������״̬
	//�������ֵ
	public boolean getState() {
		return this.state;
	}
	//����x����
	public int getX() {
		return this.LocationX;
	}
	//��������x����
	public void setX(int X) {
		this.LocationX=X;
	}
	//����y����
	public int getY() {
		return this.LocationY;
	}
	//��������y����
	public void setY(int Y) {
		this.LocationY=Y;
	}
	//�����ƶ�
	//�Ժ�����x�����޸ģ�x=x-1
	public void Left() {
		LocationX--;
	}
	//�����ƶ�
	//�Ժ�����x�����޸ģ�x=x+1
	public void Right() {
		LocationX++;
	}
	//�����ƶ�
	//��������y�����޸ģ�y=y-1
	public void Up() {
		LocationY--;
	}
	//�����ƶ�
	//��������y�����޸ģ�y=y+1
	public void Down() {
		LocationY++;
	}
	//�ж����ӵ�����״̬
	//�������״̬
	public boolean getDie() {
		return this.die;
	}
	//
	//
	//�Է���
	//���ӣ�
	
	public void Eat(Pieces It) {
		/*
		��һ���ж�It��this��ǿ��ֵ
		
			������  �����ӵ���������die�����true��ʹ�����γɻ���
			�������ǵ�ǿ�ȶ��޸ĳ�-1
			
			��������  ��this��x��y�����It��x��y��ʹthis��λ�ñ��It��λ��
			����It��die���true���γ�this�Ե�It
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
	
	//�ж��ܷ����
	/*
	��һ���ж�It��die
		���dieΪtrue���򷵻�true
		���dieΪfalse�������κ��ж�
	�ڶ����ж�It������״̬
		���Ϊfalse�����棩���򷵻�false
		���Ϊtrue�����棩�������κ��ж�
	�������ж�It��this����𣨺�������
		�����ȣ�����Ϊһ��������false
		�������ȣ�������Ĳ�
	���Ĳ��ж�ǿ��
		���ΪIt=14�����������޷����ԣ��򷵻�false
		���Ϊthis=0��It=12������thisΪ��ItΪ�����������������true
		���Ϊthis=12��It=0������thisΪ��ItΪ������ܳ������������false
		��������ų��󣬽���this��ǿ�Ⱥ�It��ǿ�ȱȶ�
		���this>=It�����������������������true
	���岽����֮�����
		������false
	
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
	
	
	//����image��ֵ������paint��������ʾ
	public Image getImage() {
		return image;
	}
	//�������֣�����ѡ��ʱ����ʾ
	public String getName() {
		return name;
	}
	//����������ڵ���¼�������ȡ
	public int getFaction() {
		return faction;
	}
	//����image������paint��������ʾ
	public void setImage(Image It) {
		this.image=It;
	}
	//����die��ֵ�����������޸�
	public void setDie() {
		this.die=true;
	}
	//����ǿ�ȣ����������ж�
	public int getInt() {
		return this.intensity;
	}
	//����ǿ�ȣ����������޸�
	public void setInt(int a) {
		this.intensity=a;
	}
	

}
