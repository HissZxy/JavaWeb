import java.util.*;
 
public class Main 
{
 
    public static void main(String[] args) 
    {
    	Scanner cn=new Scanner(System.in);
    	int count=cn.nextInt();
    	cn.nextLine();
    	int [][]kk=new int[count][count];
    	
    	for(int i=0;i<count;i++)
    	{
    		String str=cn.nextLine();
    		String []ss=str.split("\\s+");   //�ָ�ո�
    		for(int j=0;j<count;j++)
    		{
    			kk[i][j]=Integer.valueOf(ss[j]);
    		} 		
    	}
    	
    	/*���������򵥵�����
    	 * for(int i=0;i<count;i++)
    		for(int j=0;j<count;j++)
    			kk[i][j]=cn.nextInt();*/
    	
    	String str2="";
    	for(int i=0;i<count;i++)//  ͨ����ȥ����  ��������������оƬ��һ��оƬ���ж�
    	{
    		int sum=0;
    		for(int j=0;j<count;j++)
    		{
    			if(kk[j][i]==1)sum++;
    		}
    		if(sum>count/2)str2=str2+String.valueOf(i+1)+" ";
    	}
    	System.out.println(str2);
    	
    	/*���������򵥵����
    	 * System.out.println(i+1+" ");*/
    	
    }
}