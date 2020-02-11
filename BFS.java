import java.util.LinkedList;
import java.util.Scanner;
public class Main {
	static int k,ans=10000;
	static int count[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int  n = sc.nextInt();
		k = sc.nextInt();
		dfs(n,0);
		System.out.println(ans);
		sc.close();
	}
	//dfs
	private static void dfs(int n, int time) {
		if(n==k) {
			if(time < ans)
				ans = time;
			return ;
		}
		if(time > ans) {  //如果时间大于之前的答案 则不需要
			return ;
		}
		if(n > 2 * k) {
			return;
		}
		if(n>k) {	
			dfs(n-1,time+1);
		}
		dfs(n+1,time+1);
		dfs(n-1,time+1);
		dfs(n*2,time+1);
	}
	//bfs
	static boolean[] flag =new boolean[10000];
	
	private static void dfs2(int n, int t) {
		 Quene q = new  Quene();
		 q.in(new Step(n,0));
		 for(int i=0;i<flag.length;i++) {
			 flag[i] = true;
		 }
		 flag[n] = false;
		 while(!q.isEmpty())
		 {
			 Step s =q.getFrist();
			 if(s.x == k)
			 {
				 System.out.println(s.times);
			 }
			 else
			 {
				 //三种移动状态，符合条件加入队列中
		            if(s.x-1>=0&&!flag[s.x-1]) 
		            {
		                q.in(new Step(s.x-1,s.times+1));
		                flag[s.x-1]=false;
		            }
		            if(s.x+1>=0&&!flag[s.x+1])
		            {
		                q.in(new Step(s.x+1,s.times+1));
		                flag[s.x+1]=false;
		            }
		            if(s.x*2>=0&&!flag[s.x*2])
		            {
		                q.in(new Step(s.x*2,s.times+1));
		                flag[s.x*2]=false;
		            }
		            q.deleteFrist();
			 }
			 
		 }
	}
}
class Quene{
	
	LinkedList<Step> link = new LinkedList<Step>();
	
	//模拟 入队
	public  void in(Step s) {
		link.addFirst(s);
	}
	//模拟 出队
	public Object out() {
		return link.removeLast();
	}
	//模拟 队列结构是否为空
	public boolean isEmpty(){
		return link.isEmpty();
	}
	//模拟 队列结构得到第一个元素
	public Step getFrist() {
		return link.getFirst();
	}
	//模拟 队列结构去除第一个元素
	public Step deleteFrist() {
		return link.removeFirst() ;
	}
	
}
class Step
{
    int x; //记录当前位置
    int times; //记录时间
	public Step(int x, int times) {
		this.x = x;
		this.times = times;
	}
	public Step() {

	}
};