class Student{
public String name;
int age;
String xuewei;
public Student(String name,int age,String xuewei){
this.name=name;
this.age=age;
this.xuewei=xuewei;
} 
 public void show(){
System.out.println("ѧ��name="+name+"ѧ��age="+age+"ѧλ="+xuewei);
}
}
 class benke extends Student{
super.name();
super.age();
String zhuanye;


public benke(String name,int age,String zhuanye){
super.name();
super.age();
this.zhuanye=zhuanye;
}
public void show(){
System.out.println("ѧ��name="+name+"ѧ��age="+age+"ѧ��zhuanye"+zhuanye);
}
}
 class yanjiu extends Student{
super.name();
super.age();
String fangxiang;
public yanjiu(String name,int age,String fangxiang){
this.fangxiang=fangxiang;
}
public void show(){
System.out.println("ѧ��name="+name+"ѧ��age="+age+"ѧ��fangxiang"+fangxaing);
}
}
public class StuTest{
public static void main(String[]args){
Student a=new Student();
a.setname("zxy");
a.setage(18);
a.println();
benke b=new benke;
b.setname("zxy");
b.setage(18);
b.setzhuanye("ruangong");
b.println();
yanjiu c=new yanjiu;
c.setname("zxy");
c.setage(18);
c.fangxiang("jisuanji");
c.println();
}
}
