import java.math.BigDecimal;
import java.util.Scanner;


public class 复数归一化 {


		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			int b = sc.nextInt();
			Normalize(a,b);
		}

		private static void Normalize(int a, int b) {
			// 编写Normalize公式计算结果
			double left = a/Math.sqrt(a*a+b*b);//加号左边
			double right = b/Math.sqrt(a*a+b*b);//加号右边
			//对左右两个结果四舍五入处理后输出
			System.out.println(new BigDecimal(left).setScale(1, BigDecimal.ROUND_HALF_UP)+"+"+new BigDecimal(right).setScale(1, BigDecimal.ROUND_HALF_UP)+"i");
		}
		

}
