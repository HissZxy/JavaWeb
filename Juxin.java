/**
 * 
 */
package ������ϰ;
 
import java.util.Scanner;
 
/**
 * @author Administrator
 *
 */
public class ��������� {
 
	/**
	 * @param args
	 * ��������
����ƽ�������������Σ����ǵı�ƽ����ֱ������ϵ��X���Y�ᡣ����ÿ�����Σ����Ǹ�������һ����Զ�������꣬����������������εĽ��������
�����ʽ
����������������У�ÿ������һ�����Ρ�
������ÿ���У��������ε�һ����Զ�������꣬ÿ��������궼����������ֵ������10^7��ʵ����ʾ��
�����ʽ
�������������һ��ʵ����Ϊ���������������С������λ��
��������
1 1 3 3
2 2 4 4
�������
1.00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		double[] x=new double[4];
		double[] y=new double[4];
		double[] z=new double[4];
		for (int i = 0; i < 4; i++) {
			x[i]=sc.nextDouble();
			y[i]=sc.nextDouble();
		}
		z[0]=Math.max(Math.min(x[0], x[1]), Math.min(x[2], x[3]));//X1
		z[1]=Math.min(Math.max(x[0], x[1]), Math.max(x[2], x[3]));//X2
		z[2]=Math.max(Math.min(y[0], y[1]), Math.min(y[2], y[3]));//y1
		z[3]=Math.min(Math.max(y[0], y[1]), Math.max(y[2], y[3]));//y1
		if (z[0]<z[1]&&z[2]<z[3]) {
			System.out.printf("%.2f\n",(z[1]-z[0])*(z[3]-z[2]));
		}else {
			System.out.println("0.00");
		}
	}
 
}