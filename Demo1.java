import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		long a=in.nextLong();
		long b=in.nextLong();
		long c=Math.max(Math.abs(a),Math.abs(b))-1;
		long  sum=0;
		//第一步
		sum+=4*c*c+4*c;  //等差数列求和
		
		//第二步 求点所在的正方形经过的长度
		if(a<=b)
			sum+=a-(-c-1)+b-(-c-1);
		else if(a>b)
			sum+=(c+1)*8-(a-(-c-1)+b-(-c-1));
		
		System.out.println(sum);
	}

}
