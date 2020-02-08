import java.util.*;
 
public class LanQiao
{
	static int k1[]=new int[3],k2[]=new int[3],target,count=0;
	public static void main(String args[])
	{
		Scanner cn=new Scanner(System.in);
		for(int i=0;i<3;i++)
			k1[i]=cn.nextInt(); //瓶子容量
		for(int i=0;i<3;i++)
			k2[i]=cn.nextInt();  //初始瓶子已有的
		target=cn.nextInt();
		tt(k2[0],k2[1],k2[2]);
	}
	public static void tt(int q,int w,int e)
	{
		System.out.println(q+" "+w+" "+e);
		if(q==target||w==target||e==target)
			return ;
		
		if(q!=0&&w==0)   //大瓶子倒中瓶
		{
			if(q>=k1[1]-w)
			{
				q-=(k1[1]-w);w=k1[1];
				tt(q,w,e);
			}
			else {w+=q;q=0;tt(q,w,e);}
		}
		
		else if(w!=0&&e!=k1[2])   //中瓶子倒小
		{
			if(w>=k1[2]-e)
			{
				w-=(k1[2]-e);e=k1[2];
				tt(q,w,e);
			}
			else {e+=w;w=0;tt(q,w,e);}
		}
		else  if(q!=k1[0]&&e==k1[2])   //小瓶倒大瓶子
		{
			if(e>=k1[0]-q)
			{
				e-=(k1[0]-q);q=k1[0];
				tt(q,w,e);
			}
			else {q+=e;e=0;tt(q,w,e);}
		}
		
	}
}