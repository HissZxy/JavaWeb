//������
Jishiben.java
public class Jishiben{
	public static  void main(String args[]){
		Mytext text=new Mytext(��&����һ�ǡ�,100,90,600,600��);
//�������ڡ����ô������ƺʹ�С
	}
}
Mytext.java
//Mytext��
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import  javax.swing.JFrame.*;
import java.io.*;
import javax.swing.filechooser.*;
public class Mytext extends JFrame implements ActionListener{
JTextArea area;//�����ı���
JMenuBar menubar;//�����˵���
JButton button;//������ť
JFileChooser fileDialog;//�����Ի���
BufferedReader in;//��������������
FileReader fileReader;//�����ļ��ַ�������
BufferedWriter out;//���������
FileWriter fileWriter;//�����ļ��ַ������
JMenu menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8,menu9;//�����˵����Ӳ˵�
JmenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,
item14,item15,item16,item17,item18,item19,item20,item21,item22,item23,item24,
item25,item26,item27,item28,item29;//�����˵���
//����������
public  Mytext(String s,int x,int y,int h,int w){
	init(s);
	setLocation(x,y);
	setSize(h,w);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ�ý������ͷŸô��ڣ��˳�����
	}
	void init(String s){
	setTitle(s);
	menubar=new JMenuBar();
button=new JButton();
	area=new JTextArea();
	menu1=new JMenu("�ļ�(File)");
	menu2=new JMenu("����(Tool)");
	menu3=new JMenu("��ɫ(Color)");
	menu4=new JMenu("����(Background)");
	menu5=new JMenu("����(Font)");
	menu6=new JMenu("�ֺ�");
	menu7=new JMenu("����");
	menu8=new JMenu("�ָ�ԭ����");
	menu9=new JMenu("����");
	item1=new JMenuItem("����(Copy)");
item2=new JMenuItem("����(Cut)");
	item3=new JMenuItem("ճ��(Paste)");
	item4=new JMenuItem("�½�(Newset)");
	item5=new JMenuItem("����(Paste)");
	item6=new JMenuItem("��ɫ(Gray)");
	item7=new JMenuItem("��ɫ(Pink)");
	item8=new JMenuItem("��ɫ(White)");
	item9=new JMenuItem("��ɫ(Red)");
	item10=new JMenuItem("��ɫ(Black)");
	item11=new JMenuItem("��ɫ(Green)");
	item12=new JMenuItem("��ɫ(Yellow)");
	item13=new JMenuItem("��ɫ(Pink)");
	item14=new JMenuItem("��ɫ(White)");
	item15=new JMenuItem("��ɫ(Red)");
	item16=new JMenuItem("��ɫ(Black)");
	item17=new JMenuItem("��ɫ(Green)");
	item18=new JMenuItem("��ɫ(Yellow)");
	item19=new JMenuItem("30");
	item20=new JMenuItem("40");
	item21=new JMenuItem("50");
	item22=new JMenuItem("�Ӵ�");
	item23=new JMenuItem("����");
	item24=new JMenuItem("������κ");
	item25=new JMenuItem("����");
	item26=new JMenuItem("��ʽ��");
	item27=new JMenuItem("�����ʾ");
	item28=new JMenuItem("�˳�(��ʽ��)");
	item29=new JMenuItem("��(Open)");
	area.setLineWrap(true);//�Զ�����
	button.add(item28);
	add(button,BorderLayout.SOUTH);//��Ӱ�ť��������
add(new JScrollPane(area),BorderLayout.CENTER);
//���������������ı������м�λ��,BordedLayout����
	area.setFont(new Font("����",0,20));
	//Ĭ������Ϊ���壬0�ǲ��Ӵ����壬1�ǼӴֵ�,�ֺ�Ϊ20
	fileDialog=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("�ı��ļ�","html","java");
	//�ļ��Ի���ɼ�������
	fileDialog.setFileFilter(filter);
	//���öԻ���Ĭ�ϴ򿪻���ʾ���ļ�����
	item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
	//���ø��ƵĹ��ܵĿ�ݼ�ΪCtrl+C
item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
//���ü��еĹ��ܵĿ�ݼ�ΪCtrl+Q
item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
//����ճ���Ĺ��ܵĿ�ݼ�ΪCtrl+V
item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
//�����½��Ĺ��ܵĿ�ݼ�ΪCtrl+N
item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
//���ñ���Ĺ��ܵĿ�ݼ�ΪCtrl+P
item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
//���ô򿪵Ĺ��ܵĿ�ݼ�ΪCtrl+O
item28.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
//���õĹ��ܵĿ�ݼ�ΪCtrl+T
item1.setActionCommand("copy");
//���ó���item1�¼����װ���¼��е�һ��Ϊ"copy"�ַ���
item2.setActionCommand("cut");
//���ó���item2�¼����װ���¼��е�һ��Ϊ"cut"�ַ���
item3.setActionCommand("paste");
//���ó���item3�¼����װ���¼��е�һ��Ϊ"paste"�ַ���
item6.setActionCommand("gray");
//���ó���item6�¼����װ���¼��е�һ��Ϊ"gray"�ַ���
item7.setActionCommand("pink");
//���ó���item7�¼����װ���¼��е�һ��Ϊ"pink"�ַ���
item8.setActionCommand("white");
//���ó���item8�¼����װ���¼��е�һ��Ϊ"white"�ַ���
item9.setActionCommand("red");
//���ó���item9�¼����װ���¼��е�һ��Ϊ"red"�ַ���
item10.setActionCommand("black");
//���ó���item10�¼����װ���¼��е�һ��Ϊ"black"�ַ���
item11.setActionCommand("green");
//���ó���item11�¼����װ���¼��е�һ��Ϊ"green"�ַ���
item12.setActionCommand("yellow");
//���ó���item12�¼����װ���¼��е�һ��Ϊ"yellow"�ַ���
			menu2.add(item1);//��Ӳ˵���
			menu2.addSeparator();//���÷ָ���,����Ķ�һ��
			menu2.add(item2);
			menu2.addSeparator();
			menu2.add(item3);
			menu1.add(item4);
			menu1.addSeparator();
			menu1.add(item5);
			menu1.addSeparator();
			menu1.add(item29);
			menu3.add(item13);
			menu3.addSeparator();
			menu3.add(item14);
			menu3.addSeparator();
			menu3.add(item15);
			menu3.addSeparator();
			menu3.add(item16);
			menu3.addSeparator();
			menu3.add(item17);
		    menu3.addSeparator();
			menu3.add(item18);
			menu4.add(item6);
			menu4.addSeparator();
			menu4.add(item7);
			menu4.addSeparator();
			menu4.add(item8);
			menu4.addSeparator();
			menu4.add(item9);
			menu4.addSeparator();
			menu4.add(item10);
			menu4.addSeparator();
			menu4.add(item11);
			menu4.addSeparator();
			menu4.add(item12);
			menu6.add(item19);
			menu6.addSeparator();
			menu6.add(item20);
			menu6.addSeparator();
			menu6.add(item21);
			menu7.add(item22);
			menu7.addSeparator();
			menu7.add(item23);
			menu7.addSeparator();
			menu7.add(item24);
			menu7.addSeparator();
			menu7.add(item25);
			menu8.add(item26);
			menu9.add(item27);
			menu5.add(menu3);
			menu5.add(menu6);
			menu5.add(menu7);
		    menu5.add(menu8);
		    menubar.add(menu1);
			menubar.add(menu2);
			menubar.add(menu4);
			menubar.add(menu5);
			menubar.add(menu9);
			setJMenuBar(menubar);//���ò˵���
HandleListener handleListener=new  HandleListener();//�������ơ����С�ճ������������
colorListener=new BackgroundColorListener();//�����ҡ��ۡ��ס��졢�ڡ��̡��Ƽ���������
handleListener.setInputText(area);
colorListener.setInputText(area);
item1.addActionListener(handleListener);//item1��handleListenerע��Ϊ������
item2.addActionListener(handleListener);//item2��handleListenerע��Ϊ������
item3.addActionListener(handleListener);//item3��handleListenerע��Ϊ������
item4.addActionListener(this);//����������this��ָ��ǰ����,���ܱ�����̳�
item5.addActionListener(this);
item29.addActionListener(this);
item6.addActionListener(colorListener);//item6��colorListenerע��Ϊ������
item7.addActionListener(colorListener);//item7��colorListenerע��Ϊ������
item8.addActionListener(colorListener);//item8��colorListenerע��Ϊ������
item9.addActionListener(colorListener);//item9��colorListenerע��Ϊ������
item10.addActionListener(colorListener);//item10��colorListenerע��Ϊ������
item11.addActionListener(colorListener);//item11��colorListenerע��Ϊ������
item12.addActionListener(colorListener);//item12��colorListenerע��Ϊ������
item13.addActionListener(this);//����������this��ָ��ǰ����
item14.addActionListener(this);
item15.addActionListener(this);
item16.addActionListener(this);
item17.addActionListener(this);
item18.addActionListener(this);
item19.addActionListener(this);
item20.addActionListener(this);
item21.addActionListener(this);
item22.addActionListener(this);
item23.addActionListener(this);
item24.addActionListener(this);
item25.addActionListener(this);
item26.addActionListener(this);
item27.addActionListener(this);
item28.addActionListener(this);
}
//������ʵ�ֲ˵���˵����½��ͱ���Ĺ��ܵķ���
public void actionPerformed(ActionEvent e){
if(e.getSource()==item4){//����"�½����¼�
int state=fileDialog.showOpenDialog(this);// ��ʾ�ļ�ѡȡ�ĶԻ���
if(state==JFileChooser.APPROVE_OPTION){// ���ʹ���߰���ȷ�ϼ�
{area.setText(null);//���ı����
	area.setBackground(Color.white);
area.setForeground(Color.black);}
try{ 
File dir=fileDialog.getCurrentDirectory();//��ȡ��ǰ�Ի���
String name=fileDialog.getSelectedFile().getName();
File file=new File(dir,name);//��ȡĿ¼���ļ���
fileReader=new FileReader(file);
in=new BufferedReader(fileReader);//�����ļ��������
String s=null;
while((s=in.readLine())!=null){//���ļ��ж�ȡһ���ı�
area.append(s+"\n");}
in.close();
fileReader.close();//�ر�������
 }
catch(IOException exp){}//�����ļ���д�������쳣
}
else if(e.getSource()==item5){//���������¼�
    int state=fileDialog.showSaveDialog(this);
if(state==JFileChooser.APPROVE_OPTION){
         try{
           File dir=fileDialog.getCurrentDirectory();//��ȡ��ǰ�Ի���
            String name=fileDialog.getSelectedFile().getName();
            File file=new File(dir,name);
            fileWriter=new FileWriter(file);//д���ַ��ļ�
            out=new BufferedWriter(fileWriter);//���ı�д���ַ������
            out.write(area.getText());//��ȡ�ı�������д��Ŀ�ĵ�
out.flush();//ǿ�ưѻ������������д��Ŀ�ĵ�
            out.close();
            fileWriter.close();//�ر�
            }
           catch(IOException exp){}//�����ļ���д�������쳣
	   }
}
else if(e.getSource()==item29){//����"��"�¼�
        int state=fileDialog.showOpenDialog(this);
        if(state==JFileChooser.APPROVE_OPTION){
             area.setText(null);
        try{
            File dir=fileDialog.getCurrentDirectory();
            String name=fileDialog.getSelectedFile().getName();
            File file=new File(dir,name);
            fileReader=new FileReader(file);
            in=new BufferedReader(fileReader);
   String s=null;
		    while((s=in.readLine())!=null){
				area.append(s+"\n");}
                in.close();
                fileReader.close();//�ر���
            }
         catch(IOException exp){}//�����ļ���д�������쳣}	
else if(e.getSource()==item13){//����"���ַ�ɫ"�¼�
	area.setForeground(Color.pink);
}
else if(e.getSource()==item14){//����"���ְ�ɫ"�¼�
	area.setForeground(Color.white);
}
else if(e.getSource()==item15){//����"���ֺ�ɫ"�¼�
	area.setForeground(Color.red);
}
else if(e.getSource()==item16){//����"���ֺ�ɫ"�¼�
	area.setForeground(Color.black);
}
else if(e.getSource()==item17){//����"������ɫ"�¼�
	area.setForeground(Color.green);
}
else if(e.getSource()==item18){//����"���ֻ�ɫ"�¼�
	area.setForeground(Color.yellow);
}
else if(e.getSource()==item19){//����"Size30"�¼�
    area.setFont(new Font("����",0,30));
}
else if(e.getSource()==item20){//����"Size40"�¼�
	area.setFont(new Font("����",0,40));
}
else if(e.getSource()==item21){//����"Size50"�¼�
	area.setFont(new Font("����",0,50));
}
else if(e.getSource()==item22){//����"���ּӴ�"�¼�
	area.setFont(new Font("����",1,20));
}
else if(e.getSource()==item23){//����"����"�¼�
	area.setFont(new Font("����",0,20));
}
else if(e.getSource()==item24){//����"������κ"�¼�
	area.setFont(new Font("������κ",0,20));
}
else if(e.getSource()==item24){//����"����"�¼�
	area.setFont(new Font("����",0,20));
}
else if(e.getSource()==item26){//����"�ָ�ԭ����"�¼�
{
	area.setFont(new Font("����",0,20));
	area.setBackground(Color.white);
	area.setForeground(Color.black);
			}
		}
else if(e.getSource()==item28){����"�˳�"�¼�
	area.setText(null);
}
else if(e.getSource()==item27){����"�����ʾ"�¼�
{ 
	area.setBackground(Color.pink);//Ĭ�ϱ�����ɫΪ��ɫ
	area.setForeground(Color.black);//Ĭ��������ɫΪ��ɫ
area.setFont(new Font("���Ļ�ν",1,20));
area.append("�ó�����ںܶ಻��֮��,����ʹ��ǰ�����ѯ����վ�˽������Ϣ\n");
area.append("����ַ��:https://...\n");
int n=JOptionPane.showConfirmDialog(this,"���Ƿ�Ҫ���Ƹ���ַ","��ܰ��ʾ",JOptionPane.YES_NO_OPTION);//��ʾȷ�϶Ի���
if(n==JOptionPane.YES_OPTION){//�û������Ի����ϵİ�ť���ǡ�
area.append("��ע�⡿1��������ַ��,ʹ�ÿ�ݼ�Ctrl+T��Ҳ���Լ�����д�ļ���\n");
area.append("лл����ʹ�ã�");
}
if(n==JOptionPane.NO_OPTION)//�û������Ի����ϵİ�ť���ǡ�
	area.setText(null);
		}
	}
  }
}
BackgroundColorListener.java
//BackgroundListener
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
//������ʵ�ֱ�����ɫ���ù��ܵķ���
public class BackgroundColorListener  implements ActionListener{
JTextArea area;
public void setInputText(JTextArea text){
area=text;
}
public void actionPerformed(ActionEvent e){
String str=e.getActionCommand();
if(str.equals("gray"))
area.setBackground(Color.gray);
else if(str.equals("pink"))
area.setBackground(Color.pink);
else if(str.equals("white"))
area.setBackground(Color.white);
else if(str.equals("black"))
area.setBackground(Color.black);
else if(str.equals("green"))
   area.setBackground(Color.green);
else if(str.equals("yellow"))
 area.setBackground(Color.yellow);
else if(str.equals("red"))
area.setBackground(Color.red);}
}
HandleListener.java
//HandleListener��
import java.awt.event.*;
import javax.swing.*;
//������ʵ�ֹ�����˵�����ơ������к�ճ���Ĺ��ܵķ���
public class HandleListener  implements ActionListener{
JTextArea area;
public void setInputText(JTextArea text){
area=text;
}
public void actionPerformed(ActionEvent e){
String str=e.getActionCommand();
if(str.equals("copy"))
	area.copy();
else if(str.equals("cut"))
	area.cut();
else if(str.equals("paste"))
	area.paste();
}
}