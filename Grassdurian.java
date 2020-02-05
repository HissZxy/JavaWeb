    import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.GridLayout;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    
    import javax.swing.JFrame;
    import javax.swing.JMenu;
    import javax.swing.JMenuBar;
    import javax.swing.JMenuItem;
    
    public class Grassdurian extends JFrame{
    
    	public int count_x; //ˮƽ�������
    	public int count_y; //��ֱ�������
    	public int size; //�ߴ�
    	public int distance;//���
    	private Block[][] blocks; //����
    	private Block activitedBlock; //�����
    	private int scope; //����
    	
    	public Grassdurian(int count_x, int count_y, int size, int distance){
    		init(count_x, count_y, size, distance);
    	}
    	
    	public void init(int count_x, int count_y, int size, int distance){
    		this.count_x = count_x;
    		this.count_y = count_y;
    		this.size = size;
    		this.distance = distance;
    		this.scope = 0;
    		prepareGUI();
    	}
    	
    	// ����
    	public void run(){
    		//
    		this.scope = 0;
    		this.blocks = new Block[count_y][count_x];
    		for(int line=0; line < count_x; line++){
    			for(int row=0; row < count_y; row++){
    				this.blocks[line][row] = Block.random(row, line);
    				if(Math.random()*10 >= 5){
    					System.out.println("����Ϊ0");
    					this.blocks[line][row].setNumber(0);
    				}
    			}
    		}
    		this.setActivitedBlock(blocks[count_y/2][count_x/2]);
    		repaint();
    		
    	}
    	
    	// ׼��GUI
    	private void prepareGUI(){
    		
    		
    		this.setTitle("1024");
    		this.setSize((size+distance)*count_x, (size+distance)*count_y);
    		this.setLayout(new GridLayout(count_y, count_x));
    		
    		// �����¼�����
    		this.addWindowListener(new WindowAdapter() {
    			public void windowClosing(WindowEvent windowEvent){
    			    System.exit(0);
    			}        
    		}); 
    
    		// �����¼�����
    		this.addKeyListener(new KeyAdapter() {
    			public void keyPressed(KeyEvent e){
    				int keyCode = e.getKeyCode();
    				//�ո�
    				if(keyCode == KeyEvent.VK_SPACE){
    					run();
    				}else{
    					move(keyCode);
    					repaint();
    					setTitle("1024 �ܷ���"+scope);
    					
    				}
    			}
    		});
    		
    		// ����¼�����
    		this.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				super.mouseClicked(e);
    				setActivitedBlockByPostion(e.getX(), e.getY());
    			}
    		});
    		
    		this.setVisible(true);
    					
    	}
    	
    	
    	@Override
    	public void paint(Graphics g){
    		drawCanvas(g);
    	}
    	
    	// ͨ��λ�����û����
    	public void setActivitedBlockByPostion(int pos_x, int pos_y){
    		int x = pos_x/size;
    		int y = pos_y/size;
    		Block block = blocks[y][x];
    		setActivitedBlock(block);
    		repaint();
    	}
    	
    	// ���û����
    	public void setActivitedBlock(Block block){
    		this.activitedBlock = block;
    	}
    	
    	// ���ƻ���
    	public void drawCanvas(Graphics g)
    	{
    		// ����
    		g.setColor(new Color(255, 255, 255));
    		g.fillRect(0, 0, (size+distance)*count_x, (size+distance)*count_y);
    		
    		for(int line=0; line < count_y; line++){
    			for(int row=0; row < count_x; row++){
    				Block block = this.blocks[line][row];
    				int[] colors = Block.getColor(block.number);
    				String number = String.valueOf(block.number);
    				int numberLength = number.length();
    				int fontSize = size/numberLength;
    				int x = block.pos_x*(size+distance);
    				int y = block.pos_y*(size+distance);
    				int width = size-distance;
    				int height = size-distance;
    				
    				
    				// ���ƾ���
    				g.setColor(new Color(colors[0], colors[1], colors[2]));
    				g.fillRect(x, y, width, height);
    				
    				// ��������
    				g.setFont(new Font("����",Font.PLAIN, fontSize));
    				g.setColor(new Color(31, 31, 31));
    				g.drawString(number, x+(width/4), y+(height/numberLength));	
    			}	
    		}
    		
    		// ���ƻ����
    		int x = activitedBlock.pos_x*(size+distance);
    		int y = activitedBlock.pos_y*(size+distance);
    		int width = size-distance;
    		int height = size-distance;
    		Graphics2D graphics2d = (Graphics2D)g;
    		graphics2d.setColor(Color.RED);
    		graphics2d.setStroke(new BasicStroke(distance/2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    		graphics2d.drawRect(x, y, width, height);
    	}
    	
    	
    	// ��������ƶ�
    	public void move(int direction){
    		System.out.println("��������ƶ�");
    		
    		int row = activitedBlock.pos_x;//lie
    		int line = activitedBlock.pos_y;//hang
    		System.out.println("��ǰ����λ��"+activitedBlock.pos_y+":"+activitedBlock.pos_x);
    		
    		switch (direction) {
    			case KeyEvent.VK_W:
    				row = count_x;
    				for(int r=0; r < row; r++){
    					for(int l=0; l < line; l++){
    						Block top = blocks[l][r];
    						System.out.println(top.pos_y+":"+top.pos_x+"="+top.number);
    						Block botton = blocks[l+1][r];
    
    						if(top.number == botton.number){
    							this.scope += top.number;
    							top.merge();
    							
    							System.out.println("ƥ��"+top.pos_y+":"+ top.pos_x+"-"+botton.pos_y+":"+botton.pos_x);
    							System.out.println("�ϲ����ƶ�ʣ�µĿ�");
    //							
    							for(int iline=l+1; iline < count_y-1; iline++){
    								blocks[iline][r] = blocks[iline+1][r];
    								blocks[iline][r].move(KeyEvent.VK_W);
    								System.out.println("�ƶ�"+blocks[iline][r].pos_y+"��"+blocks[iline][r].pos_x+"��="+blocks[iline][r].number);
    							}
    							
    							System.out.println("����"+(count_y-1)+"��"+r+"��=");
    							blocks[count_y-1][r] = Block.random(r, count_y-1);
    							
    							break;
    						}
    					}		
    				}
    				break;
    			
    			case KeyEvent.VK_S:
    				
    				row = count_x;
    				for(int r=0; r < row; r++){
    					for(int l=count_y-1; l > line; l--){
    						Block botton = blocks[l][r];
    						System.out.println(botton.pos_y+":"+botton.pos_x+"="+botton.number);
    						Block top = blocks[l-1][r];
    
    						if(top.number == botton.number){
    							this.scope += botton.number;
    							botton.merge();
    							
    							System.out.println("ƥ��"+botton.pos_y+":"+ botton.pos_x+"-"+top.pos_y+":"+top.pos_x+"="+botton.number);
    							System.out.println("�ϲ����ƶ�ʣ�µĿ�");
    							
    //							
    							for(int iline=l-1; iline > 0; iline--){
    								blocks[iline][r] = blocks[iline-1][r];
    								blocks[iline][r].move(KeyEvent.VK_S);
    								System.out.println("��һ���ƶ���"+blocks[iline][r].pos_y+"��"+blocks[iline][r].pos_x+"��="+blocks[iline][r].number);
    							}
    							
    							System.out.println("����"+(0)+"��"+r+"��=");
    							blocks[0][r] = Block.random(r, 0);
    							break;
    						}
    							
    					}		
    				}
    				break;
    			
    			case KeyEvent.VK_A:
    				line = count_y;
    				for(int l=0; l < line; l++){
    					for(int r=0; r < row; r++){
    						Block left = blocks[l][r];
    						System.out.println(left.pos_y+":"+left.pos_x+"="+left.number);
    						Block right = blocks[l][r+1];
    
    						if(left.number == right.number){
    							this.scope += left.number;
    							left.merge();
    							
    			
    							System.out.println("ƥ��"+left.pos_y+":"+ left.pos_x+"-"+right.pos_y+":"+right.pos_x+"="+right.number);
    							System.out.println("�ϲ����ƶ�ʣ�µĿ�");
    							
    //							
    							for(int irow=r+1; irow < count_x-1; irow++){
    								blocks[l][irow] = blocks[l][irow+1];
    								blocks[l][irow].move(KeyEvent.VK_A);
    								System.out.println("��һ���ƶ���"+blocks[l][irow].pos_y+"��"+blocks[l][irow].pos_x+"��="+blocks[l][irow].number);
    							}
    							
    							System.out.println("����"+(l)+"��"+4+"��=");
    							blocks[l][4] = Block.random(4, l);
    							break;
    						}
    							
    					}		
    				}
    				break;
    			
    			case KeyEvent.VK_D:
    				line = count_y;
    				for(int l=0; l < line; l++){
    					for(int r=count_x-1; r > row; r--){
    						Block right = blocks[l][r];
    						System.out.println(right.pos_y+":"+right.pos_x+"="+right.number);
    						Block left = blocks[l][r-1];
    
    						if(left.number == right.number){
    							this.scope += right.number;
    							right.merge();
    							
    			
    							System.out.println("ƥ��"+left.pos_y+":"+ left.pos_x+"-"+right.pos_y+":"+right.pos_x+"="+right.number);
    							System.out.println("�ϲ����ƶ�ʣ�µĿ�");
    							
    							for(int irow=r-1; irow > 0; irow--){
    								blocks[l][irow] = blocks[l][irow-1];
    								blocks[l][irow].move(KeyEvent.VK_D);
    								System.out.println("��һ���ƶ���"+blocks[l][irow].pos_y+"��"+blocks[l][irow].pos_x+"��="+blocks[l][irow].number);
    							}
    							
    							System.out.println("����"+(l)+"��"+0+"��=");
    							blocks[l][0] = Block.random(0, l);
    							break;
    						}
    							
    					}		
    				}
    				break;
    
    			default:
    				break;
    		}
    		
    	}
    	
    	
    }





//
**����Test��**

    


   public class Test {

	public static void main(String[] args) {
		
		Grassdurian grassdurian = new Grassdurian(5, 5, 100, 10);
		grassdurian.run();

  
	}

}