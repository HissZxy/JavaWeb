import java.util.*;
 
public class LanQiao
{
	static int k1[]=new int[3],k2[]=new int[3],target,count=0;
	public static void main(String args[])
	{
		Scanner cn=new Scanner(System.in);
		for(int i=0;i<3;i++)
			k1[i]=cn.nextInt(); //ƿ������
		for(int i=0;i<3;i++)
			k2[i]=cn.nextInt();  //��ʼƿ�����е�
		target=cn.nextInt();
		tt(k2[0],k2[1],k2[2]);
	}
	public static void tt(int q,int w,int e)
	{
		System.out.println(q+" "+w+" "+e);
		if(q==target||w==target||e==target)
			return ;
		
		if(q!=0&&w==0)   //��ƿ�ӵ���ƿ
		{
			if(q>=k1[1]-w)
			{
				q-=(k1[1]-w);w=k1[1];
				tt(q,w,e);
			}
			else {w+=q;q=0;tt(q,w,e);}
		}
		
		else if(w!=0&&e!=k1[2])   //��ƿ�ӵ�С
		{
			if(w>=k1[2]-e)
			{
				w-=(k1[2]-e);e=k1[2];
				tt(q,w,e);
			}
			else {e+=w;w=0;tt(q,w,e);}
		}
		else  if(q!=k1[0]&&e==k1[2])   //Сƿ����ƿ��
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