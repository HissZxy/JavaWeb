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
	
	static void slove(int row,int col,int maxVal,int amt) {//分别为所在行，列，当前已拿宝物最大值，已拿宝物数
		if(row==n-1&&col==m-1) {
			if(amt==k||(amt==k-1&&num[row][col]>maxVal)) {
				count++;
				if(count>1000000007)
					count=count%1000000007;
			}
		}
		if(row<n&&col<m) //数组容量大一点也行，因为最后的row会比n大1，col同理
		{
			if(row<n) {//向下走
				if(maxVal<num[row][col]) 
					slove(row+1,col,num[row][col],amt+1);//拿
				slove(row+1,col,maxVal,amt);//不拿
			}
			if(col<m) {//向右走
				if(maxVal<num[row][col]) 
					slove(row,col+1,num[row][col],amt+1);//拿
				slove(row,col+1,maxVal,amt);//不拿
			}
		}
	}
	
}