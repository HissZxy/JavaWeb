package LanQiaoBei;
 
import java.math.BigInteger;
 
public class TestHanoi {
	
	public static int count = 0;
 
	public static void main(String[] args) {
//		char A, B, C;
//		A = 'A';
//		B = 'B';
//		C = 'C';
//		for(int i=1; i<10; i++) {
//			count = 0;
//			hanoi(A, B, C, i);//通过写出hanoi找出规律
//			System.out.println(count);
//		}
		int start = 1;
		int end = 64;
		BigInteger temp = BigInteger.valueOf(2);
		BigInteger result = BigInteger.ZERO;
		for(int i=start; i<=end; i++) {
			result = result.multiply(temp).add(BigInteger.ONE);
		}
		System.out.println(result);
	}
 
//	private static void hanoi(char a, char b, char c, int n) {
//		if(n == 1) {
//			return;
//		} else {
//			hanoi(a, b, c, n-1);//将a塔上n-1盘借助b挪到c塔上
//			move(a, b);//将a塔上剩余的一个盘挪到b上
//			hanoi(c, a, b, n-1);//把c塔上n-1个盘借助a挪到b上
//		}
//	}
 
//	private static void move(char a, char b) {
//		//System.out.println(a+"-->>"+b);
//		count ++;
//	}
}