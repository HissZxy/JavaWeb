package 输入输出;
import java.io.*;
import class suiji{
static int max,min,sum=0;
static int[]a=new int[5000];
public static void main(String args[]){
File f=new File("a.txt");
if(f==null){
System.out.println("错误文件");
System.exit(0);
}
genRandom(f);
calculate(f);
}
static void genRandom(File f){
try{
FileOutputStream t=new FileOutputStream(f);
DataOutputStream d=new DataOutputStream(t);
for(int i=0;i<5000;i++){
d.writeInt((int)(Math.random()*10000));
}
d.close();
}catch(FileNotFoundException e){
e.printStackTrace();
}
catch(Exception e){
e.printStackTrace();
}
}
static void calculate(File f){
try{
FileInputStream fis=new FileInputStream(f);
DataInputStream dis=new DataInputStream(fis);
int i;
for(i=0;i<5000;i++){
a[i]=dis.readInt();
}
dis.close();
max=a[0];
min=a[0];
for(i=0;i<5000;i++){
if(max<a[i])max=a[i];
if(min>a[i])min=a[i];
sum+=a[i];
}
}
catch(FileNotFoundException e){
e.printStackTrace();
}catch(Exception e){
e.printStackTrace();
}
int average=sum/5000;
System.out.println("max="+max+"\tmin="+min);
System.out.println("sum"+sum+"\taverage="+average);
}
}
