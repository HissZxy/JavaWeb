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
//			hanoi(A, B, C, i);//ͨ��д��hanoi�ҳ�����
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
//			hanoi(a, b, c, n-1);//��a����n-1�̽���bŲ��c����
//			move(a, b);//��a����ʣ���һ����Ų��b��
//			hanoi(c, a, b, n-1);//��c����n-1���̽���aŲ��b��
//		}
//	}
 
//	private static void move(char a, char b) {
//		//System.out.println(a+"-->>"+b);
//		count ++;
//	}
}