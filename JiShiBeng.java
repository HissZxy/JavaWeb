//主函数
Jishiben.java
public class Jishiben{
	public static  void main(String args[]){
		Mytext text=new Mytext(“&随手一记”,100,90,600,600”);
//创建窗口、设置窗口名称和大小
	}
}
Mytext.java
//Mytext类
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
JTextArea area;//创建文本区
JMenuBar menubar;//创建菜单条
JButton button;//创建按钮
JFileChooser fileDialog;//创建对话框
BufferedReader in;//创建缓冲输入流
FileReader fileReader;//创建文件字符输入流
BufferedWriter out;//缓冲输出流
FileWriter fileWriter;//创建文件字符输出流
JMenu menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8,menu9;//创建菜单、子菜单
JmenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,
item14,item15,item16,item17,item18,item19,item20,item21,item22,item23,item24,
item25,item26,item27,item28,item29;//创建菜单项
//添加组件方法
public  Mytext(String s,int x,int y,int h,int w){
	init(s);
	setLocation(x,y);
	setSize(h,w);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使用结束后释放该窗口，退出程序
	}
	void init(String s){
	setTitle(s);
	menubar=new JMenuBar();
button=new JButton();
	area=new JTextArea();
	menu1=new JMenu("文件(File)");
	menu2=new JMenu("工具(Tool)");
	menu3=new JMenu("颜色(Color)");
	menu4=new JMenu("背景(Background)");
	menu5=new JMenu("字体(Font)");
	menu6=new JMenu("字号");
	menu7=new JMenu("字形");
	menu8=new JMenu("恢复原字体");
	menu9=new JMenu("关于");
	item1=new JMenuItem("复制(Copy)");
item2=new JMenuItem("剪切(Cut)");
	item3=new JMenuItem("粘贴(Paste)");
	item4=new JMenuItem("新建(Newset)");
	item5=new JMenuItem("保存(Paste)");
	item6=new JMenuItem("灰色(Gray)");
	item7=new JMenuItem("粉色(Pink)");
	item8=new JMenuItem("白色(White)");
	item9=new JMenuItem("红色(Red)");
	item10=new JMenuItem("黑色(Black)");
	item11=new JMenuItem("绿色(Green)");
	item12=new JMenuItem("黄色(Yellow)");
	item13=new JMenuItem("粉色(Pink)");
	item14=new JMenuItem("白色(White)");
	item15=new JMenuItem("红色(Red)");
	item16=new JMenuItem("黑色(Black)");
	item17=new JMenuItem("绿色(Green)");
	item18=new JMenuItem("黄色(Yellow)");
	item19=new JMenuItem("30");
	item20=new JMenuItem("40");
	item21=new JMenuItem("50");
	item22=new JMenuItem("加粗");
	item23=new JMenuItem("楷体");
	item24=new JMenuItem("华文新魏");
	item25=new JMenuItem("行书");
	item26=new JMenuItem("格式化");
	item27=new JMenuItem("相关提示");
	item28=new JMenuItem("退出(格式化)");
	item29=new JMenuItem("打开(Open)");
	area.setLineWrap(true);//自动换行
	button.add(item28);
	add(button,BorderLayout.SOUTH);//添加按钮，在南面
add(new JScrollPane(area),BorderLayout.CENTER);
//创建滚动条，将文本区在中间位置,BordedLayout布局
	area.setFont(new Font("宋体",0,20));
	//默认字体为宋体，0是不加粗字体，1是加粗的,字号为20
	fileDialog=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("文本文件","html","java");
	//文件对话框可见的类型
	fileDialog.setFileFilter(filter);
	//设置对话框默认打开或显示的文件类型
	item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
	//设置复制的功能的快捷键为Ctrl+C
item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
//设置剪切的功能的快捷键为Ctrl+Q
item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
//设置粘贴的功能的快捷键为Ctrl+V
item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
//设置新建的功能的快捷键为Ctrl+N
item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
//设置保存的功能的快捷键为Ctrl+P
item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
//设置打开的功能的快捷键为Ctrl+O
item28.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
//设置的功能的快捷键为Ctrl+T
item1.setActionCommand("copy");
//设置出发item1事件后封装到事件中的一个为"copy"字符串
item2.setActionCommand("cut");
//设置出发item2事件后封装到事件中的一个为"cut"字符串
item3.setActionCommand("paste");
//设置出发item3事件后封装到事件中的一个为"paste"字符串
item6.setActionCommand("gray");
//设置出发item6事件后封装到事件中的一个为"gray"字符串
item7.setActionCommand("pink");
//设置出发item7事件后封装到事件中的一个为"pink"字符串
item8.setActionCommand("white");
//设置出发item8事件后封装到事件中的一个为"white"字符串
item9.setActionCommand("red");
//设置出发item9事件后封装到事件中的一个为"red"字符串
item10.setActionCommand("black");
//设置出发item10事件后封装到事件中的一个为"black"字符串
item11.setActionCommand("green");
//设置出发item11事件后封装到事件中的一个为"green"字符串
item12.setActionCommand("yellow");
//设置出发item12事件后封装到事件中的一个为"yellow"字符串
			menu2.add(item1);//添加菜单项
			menu2.addSeparator();//设置分格条,下面的都一样
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
			setJMenuBar(menubar);//设置菜单条
HandleListener handleListener=new  HandleListener();//创建复制、剪切、粘贴监视器对象
colorListener=new BackgroundColorListener();//创建灰、粉、白、红、黑、绿、黄监视器对象
handleListener.setInputText(area);
colorListener.setInputText(area);
item1.addActionListener(handleListener);//item1将handleListener注册为监视器
item2.addActionListener(handleListener);//item2将handleListener注册为监视器
item3.addActionListener(handleListener);//item3将handleListener注册为监视器
item4.addActionListener(this);//动作监视器this是指当前对象,不能被子类继承
item5.addActionListener(this);
item29.addActionListener(this);
item6.addActionListener(colorListener);//item6将colorListener注册为监视器
item7.addActionListener(colorListener);//item7将colorListener注册为监视器
item8.addActionListener(colorListener);//item8将colorListener注册为监视器
item9.addActionListener(colorListener);//item9将colorListener注册为监视器
item10.addActionListener(colorListener);//item10将colorListener注册为监视器
item11.addActionListener(colorListener);//item11将colorListener注册为监视器
item12.addActionListener(colorListener);//item12将colorListener注册为监视器
item13.addActionListener(this);//动作监视器this是指当前对象
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
//下面是实现菜单里菜单项新建和保存的功能的方法
public void actionPerformed(ActionEvent e){
if(e.getSource()==item4){//监听"新建”事件
int state=fileDialog.showOpenDialog(this);// 显示文件选取的对话框
if(state==JFileChooser.APPROVE_OPTION){// 如果使用者按下确认键
{area.setText(null);//将文本清空
	area.setBackground(Color.white);
area.setForeground(Color.black);}
try{ 
File dir=fileDialog.getCurrentDirectory();//获取当前对话框
String name=fileDialog.getSelectedFile().getName();
File file=new File(dir,name);//获取目录和文件名
fileReader=new FileReader(file);
in=new BufferedReader(fileReader);//创建文件的输出流
String s=null;
while((s=in.readLine())!=null){//从文件中读取一行文本
area.append(s+"\n");}
in.close();
fileReader.close();//关闭输入流
 }
catch(IOException exp){}//捕获文件读写发生的异常
}
else if(e.getSource()==item5){//监听保存事件
    int state=fileDialog.showSaveDialog(this);
if(state==JFileChooser.APPROVE_OPTION){
         try{
           File dir=fileDialog.getCurrentDirectory();//获取当前对话框
            String name=fileDialog.getSelectedFile().getName();
            File file=new File(dir,name);
            fileWriter=new FileWriter(file);//写入字符文件
            out=new BufferedWriter(fileWriter);//将文本写入字符输出流
            out.write(area.getText());//获取文本区文字写入目的地
out.flush();//强制把缓冲区里的内容写到目的地
            out.close();
            fileWriter.close();//关闭
            }
           catch(IOException exp){}//捕获文件读写发生的异常
	   }
}
else if(e.getSource()==item29){//监听"打开"事件
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
                fileReader.close();//关闭流
            }
         catch(IOException exp){}//捕获文件读写发生的异常}	
else if(e.getSource()==item13){//监听"文字粉色"事件
	area.setForeground(Color.pink);
}
else if(e.getSource()==item14){//监听"文字白色"事件
	area.setForeground(Color.white);
}
else if(e.getSource()==item15){//监听"文字红色"事件
	area.setForeground(Color.red);
}
else if(e.getSource()==item16){//监听"文字黑色"事件
	area.setForeground(Color.black);
}
else if(e.getSource()==item17){//监听"文字绿色"事件
	area.setForeground(Color.green);
}
else if(e.getSource()==item18){//监听"文字黄色"事件
	area.setForeground(Color.yellow);
}
else if(e.getSource()==item19){//监听"Size30"事件
    area.setFont(new Font("宋体",0,30));
}
else if(e.getSource()==item20){//监听"Size40"事件
	area.setFont(new Font("宋体",0,40));
}
else if(e.getSource()==item21){//监听"Size50"事件
	area.setFont(new Font("宋体",0,50));
}
else if(e.getSource()==item22){//监听"文字加粗"事件
	area.setFont(new Font("宋体",1,20));
}
else if(e.getSource()==item23){//监听"楷体"事件
	area.setFont(new Font("楷体",0,20));
}
else if(e.getSource()==item24){//监听"华文新魏"事件
	area.setFont(new Font("华文新魏",0,20));
}
else if(e.getSource()==item24){//监听"行书"事件
	area.setFont(new Font("行书",0,20));
}
else if(e.getSource()==item26){//监听"恢复原设置"事件
{
	area.setFont(new Font("宋体",0,20));
	area.setBackground(Color.white);
	area.setForeground(Color.black);
			}
		}
else if(e.getSource()==item28){监听"退出"事件
	area.setText(null);
}
else if(e.getSource()==item27){监听"相关提示"事件
{ 
	area.setBackground(Color.pink);//默认背景颜色为白色
	area.setForeground(Color.black);//默认字体颜色为黑色
area.setFont(new Font("新文华谓",1,20));
area.append("该程序存在很多不足之处,建议使用前建议查询该网站了解相关信息\n");
area.append("该网址是:https://...\n");
int n=JOptionPane.showConfirmDialog(this,"您是否要复制该网址","温馨提示",JOptionPane.YES_NO_OPTION);//显示确认对话框
if(n==JOptionPane.YES_OPTION){//用户单击对话框上的按钮“是”
area.append("【注意】1、复制网址后,使用快捷键Ctrl+T，也可以继续编写文件。\n");
area.append("谢谢您的使用！");
}
if(n==JOptionPane.NO_OPTION)//用户单击对话框上的按钮“是”
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
//下面是实现背景颜色设置功能的方法
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
//HandleListener类
import java.awt.event.*;
import javax.swing.*;
//下面是实现工具里菜单项”复制”、剪切和粘贴的功能的方法
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