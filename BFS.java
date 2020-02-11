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
		if(time > ans) {  //���ʱ�����֮ǰ�Ĵ� ����Ҫ
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
				 //�����ƶ�״̬�������������������
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
	
	//ģ�� ���
	public  void in(Step s) {
		link.addFirst(s);
	}
	//ģ�� ����
	public Object out() {
		return link.removeLast();
	}
	//ģ�� ���нṹ�Ƿ�Ϊ��
	public boolean isEmpty(){
		return link.isEmpty();
	}
	//ģ�� ���нṹ�õ���һ��Ԫ��
	public Step getFrist() {
		return link.getFirst();
	}
	//ģ�� ���нṹȥ����һ��Ԫ��
	public Step deleteFrist() {
		return link.removeFirst() ;
	}
	
}
class Step
{
    int x; //��¼��ǰλ��
    int times; //��¼ʱ��
	public Step(int x, int times) {
		this.x = x;
		this.times = times;
	}
	public Step() {

	}
};