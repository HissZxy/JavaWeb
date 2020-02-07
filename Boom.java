import java.util.*;
 
public class Boom
{
	static int n,count=-1,k[][],x,y;
	 
	public static void main(String args[])
	{
		Scanner cn=new Scanner(System.in);
		
		n=cn.nextInt();
		k=new int[n][n];
		boolean t[][]=new boolean [n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				String r=cn.next();   //A记做3 +为1  -为0  B为4
				if(r.equals("A")){x=i;y=j;k[i][j]=3;t[i][j]=true;}
				else if(r.equals("B"))k[i][j]=4;
				else if(r.equals("+"))k[i][j]=1;
				else if(r.equals("-"))k[i][j]=0;
			}
		long t1=System.currentTimeMillis();
		dd(x,y,0,t);                //位置  步数
		long t2=System.currentTimeMillis();
		System.out.println(count);
		System.out.println("耗时："+(t2-t1));
	}
	public static void dd(int q,int w,int step,boolean t[][])
	{
		if(k[q][w]==4)
		{
			if(count==-1)count=step;
			else if(count>step)count=step;
			
			return;
		}
		if(step>count&&count!=-1)return;
		//上走     保证不在第一行   同时下一步的位置没有走过   同时避免++ 和--（即1+1=2和0+0=0）
		if(q!=0&&t[q-1][w]!=true&&k[q][w]+k[q-1][w]!=0&&k[q][w]+k[q-1][w]!=2) 
			{ t[q-1][w]=true;dd(q-1,w,step+1,t);t[q-1][w]=false;}       //回溯法的关键
		//下走
		if(q!=n-1&&t[q+1][w]!=true&&k[q][w]+k[q+1][w]!=0&&k[q][w]+k[q+1][w]!=2)
			{ t[q+1][w]=true;dd(q+1,w,step+1,t);t[q+1][w]=false;}
		//左走
		if(w!=0&&t[q][w-1]!=true&&k[q][w]+k[q][w-1]!=0&&k[q][w]+k[q][w-1]!=2)
			{ t[q][w-1]=true;dd(q,w-1,step+1,t);t[q][w-1]=false;}
		//右走
		if(w!=n-1&&t[q][w+1]!=true&&k[q][w]+k[q][w+1]!=0&&k[q][w]+k[q][w+1]!=2) 
			{ t[q][w+1]=true;dd(q,w+1,step+1,t);t[q][w+1]=false;}
		
		return;
			
	}
}