package lqb2014;
import java.util.Scanner;
 
public class Task9 {
	static int n,m,k,count=0;
	static int num[][];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		num=new int[n][m];
		for(int i=0;i<n;++i) {
			for(int j=0;j<m;++j) {
				num[i][j]=sc.nextInt();
			}
		}
		slove(0,0,0,0);
		System.out.println(count);
 
	}
	
	static void slove(int row,int col,int maxVal,int amt) {//�ֱ�Ϊ�����У��У���ǰ���ñ������ֵ�����ñ�����
		if(row==n-1&&col==m-1) {
			if(amt==k||(amt==k-1&&num[row][col]>maxVal)) {
				count++;
				if(count>1000000007)
					count=count%1000000007;
			}
		}
		if(row<n&&col<m) //����������һ��Ҳ�У���Ϊ����row���n��1��colͬ��
		{
			if(row<n) {//������
				if(maxVal<num[row][col]) 
					slove(row+1,col,num[row][col],amt+1);//��
				slove(row+1,col,maxVal,amt);//����
			}
			if(col<m) {//������
				if(maxVal<num[row][col]) 
					slove(row,col+1,num[row][col],amt+1);//��
				slove(row,col+1,maxVal,amt);//����
			}
		}
	}
	
}