```public class Framefish extends JFrame {
	
	//��ǰ�����Ļ���
	Graphics g;
    //����������Ҫ�ƶ���С?
    ArrayList<Fish> list = new ArrayList<Fish>();
	
	//�������ֲ��ܽ�show
	public void showui(){
		
		//���ô���
		this.setTitle("�����С��");
		this.setSize(1200,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		FlowLayout flow = new FlowLayout();
		this.setLayout(flow);
	   
		//���ð�ť
		Dimension demension = new Dimension(80,80);
	    JButton button1 = new JButton("��ʼ");
	    JButton button2 = new JButton("��ͣ");
	    JButton button3 = new JButton("����");
	    JButton button4 = new JButton("�ָ�");
	    button1.setPreferredSize(demension);
	    button2.setPreferredSize(demension);
	    button3.setPreferredSize(demension);
	    button4.setPreferredSize(demension);
	    this.add(button1);
	    this.add(button2);
	    this.add(button3);
	    this.add(button4);
	    //��ť��Ӽ�������ʵ�ֿ�ʼ��ͣ�ָ�����
	    buttonlisenenr bl = new buttonlisenenr(this,this.list,this.paopaolist);
	    button1.addActionListener(bl);
	    button2.addActionListener(bl);
	    button3.addActionListener(bl);
	    button4.addActionListener(bl);
	    
	    //���ô���ɼ�
		this.setVisible(true);
		g=this.getGraphics();
	
	}

������е�ÿһ���㶼���ε��ٶȣ���С������ͬʱ��Щ����Ҫ�ж��Ƿ񱻳Ե���Ե������㣬����Ҫ��������Ļ��(����������)������Ļ���ζ�(���仯λ���˶�)����Щ���Խ��ж��壬�������ࣺ
