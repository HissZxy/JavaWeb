import java.math.BigDecimal;
import java.util.Scanner;


public class ������һ�� {


		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			int b = sc.nextInt();
			Normalize(a,b);
		}

		private static void Normalize(int a, int b) {
			// ��дNormalize��ʽ������
			double left = a/Math.sqrt(a*a+b*b);//�Ӻ����
			double right = b/Math.sqrt(a*a+b*b);//�Ӻ��ұ�
			//��������������������봦������
			System.out.println(new BigDecimal(left).setScale(1, BigDecimal.ROUND_HALF_UP)+"+"+new BigDecimal(right).setScale(1, BigDecimal.ROUND_HALF_UP)+"i");
		}
		

}
