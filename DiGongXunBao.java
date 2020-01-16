package lqb2014;
 
import java.util.Scanner;
 
public class Task9_3 {
	static int n;
	static int m;
	static int[][] num;
	static int ans[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		num=new int[n][m];//迷宫
		ans=new int[n+m-1];//从入口到出口走过的格子
		for(int i=0;i<n;++i)
			for(int j=0;j<m;++j)
				num[i][j]=sc.nextByte();
		dfs(0,0,0);
	}
	static void dfs(int row,int col,int k) {
		if(row==n-1&&col==m-1) {//到达出口
			for(int i=0;i<ans.length-1;++i)
				System.out.print(ans[i]);
			System.out.print(num[row][col]+"\n");
		}
		if(row<n&&col<m) {/*防止数组越界，如果没有这句判断，row最后会等于n，虽然跳过if(row<n)，
			但会进入if(col<m)，此时ans[k]=num[row][col]中的row就越界了，col同理*/
			if(row<n) {
				ans[k]=num[row][col];
				dfs(row+1,col,k+1);//传参数的好处是下一层执行完毕返回后不会对上一层的参数造成影响
			}
			if(col<m) {
				ans[k]=num[row][col];
				dfs(row,col+1,k+1);
			}
		}
		
	}
}