```public class Framefish extends JFrame {
	
	//当前画布的画笔
	Graphics g;
    //画布里所有要移动的小?
    ArrayList<Fish> list = new ArrayList<Fish>();
	
	//函数名字不能叫show
	public void showui(){
		
		//设置窗体
		this.setTitle("大鱼吃小鱼");
		this.setSize(1200,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		FlowLayout flow = new FlowLayout();
		this.setLayout(flow);
	   
		//设置按钮
		Dimension demension = new Dimension(80,80);
	    JButton button1 = new JButton("开始");
	    JButton button2 = new JButton("暂停");
	    JButton button3 = new JButton("结束");
	    JButton button4 = new JButton("恢复");
	    button1.setPreferredSize(demension);
	    button2.setPreferredSize(demension);
	    button3.setPreferredSize(demension);
	    button4.setPreferredSize(demension);
	    this.add(button1);
	    this.add(button2);
	    this.add(button3);
	    this.add(button4);
	    //按钮添加监听器，实现开始暂停恢复结束
	    buttonlisenenr bl = new buttonlisenenr(this,this.list,this.paopaolist);
	    button1.addActionListener(bl);
	    button2.addActionListener(bl);
	    button3.addActionListener(bl);
	    button4.addActionListener(bl);
	    
	    //设置窗体可见
		this.setVisible(true);
		g=this.getGraphics();
	
	}

鱼队列中的每一条鱼都有游的速度，大小的区别，同时这些鱼需要判断是否被吃掉获吃掉其他鱼，并且要出现在屏幕上(即被画下来)，在屏幕上游动(即变化位置运动)对这些属性进行定义，创建鱼类：
