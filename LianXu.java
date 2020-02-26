package consecutive.number;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 本题需要考虑的细节问题很多
 * @author Jacob
 *
 */
public class LianXu {
	//定义成员变量来存储结果
	static ArrayList<Integer> list = new ArrayList<Integer>();
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		//先对该数组进行排序
		Arrays.sort(num);
		judge(num);
		String str = "";
		if (list.isEmpty())
			System.out.println("mistake");
		else{
			for (int a : list) {
				str += a + " ";
			}
			//去除最后一个空格
			str = str.substring(0, str.length() - 1);
			System.out.println(str);
		}
			
	}
 
	private static void judge(int[] num) {
		int N = num.length;
		//如果只有一个元素，还要判读是否是1和100000000
		if (N == 1) {
			if(num[0]==1)
				list.add(num[0] + 1);
			else if(num[0]==1000000000)
				list.add(num[0]-1);
			else{
				list.add(num[0]-1);
				list.add(num[0]+1);
			}
			return;
		}
		int flag = 0;
		int a=0;
		for (int i = 1; i < N; i++) {
			if (num[i] != num[i - 1] + 1) {
				if(num[i]==num[i-1]+2){
					flag++;
					a=i;					
				}else{
					//如果间隔超过了2
					flag=2;
					break;
				}
			}
		}
		//如果该序列是连续的，还要判断最小是不是1以及最大是不是100000000
		if (flag == 0) {
			if(num[0]==1){
				list.add(num[num.length-1]+1);
			}else if(num[num.length-1]==1000000000)
				list.add(num[0]-1);
			else{
				list.add(num[0] - 1);
				list.add(num[N - 1] + 1);
			}
		//如果序列中只空了一个数，那么返回这个数
		}else if(flag==1){
			list.add(num[a-1]+1);
		}else
			return;
		
 
	}
}