public class mouselisener extends MouseAdapter{

	static float x1,x2;//���ϼ�¼������ڵĺ�����
	Graphics g;
	Frame frame;
	Fish myfish;
	ArrayList<Fish> list;
	String getstr;
	static int flag=1;//�����л�x1,x2�ļ�¼˳��

	public mouselisener(Graphics g, Frame mf, ArrayList<Fish> list) {
		this.g = g;
		this.frame = mf;
		this.list = list;
		this.myfish = list.get(0);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		//myfish.x = e.getX();
		//myfish.y = e.getY();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		if(flag==1){
			x1=e.getX();
			myfish.x=e.getX();
			myfish.y=e.getY();
		    flag=2;
		}
		else if(flag==2){
			x2=e.getX();
			myfish.x=e.getX();
			myfish.y=e.getY();
			flag=1;
			x1=x2;
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	public void actionPerformed(ActionEvent e) {}

}