public class mouselisener extends MouseAdapter{

	static float x1,x2;//不断记录鼠标所在的横坐标
	Graphics g;
	Frame frame;
	Fish myfish;
	ArrayList<Fish> list;
	String getstr;
	static int flag=1;//用来切换x1,x2的记录顺序

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