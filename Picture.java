package project01_2;
import java.util.Scanner;
public class Main {
public static void main(String[] args) {
	int i,j;
	Scanner out = new Scanner(System.in);
	int n =out.nextInt();
	for(i=0;i<(n-1)*2;i++) {
		System.out.print(' ');
	}//�ֲ������һ��
	System.out.println('*');		
	for(i=1;i<2*n-2;i++){
		if(i<n) {
			for(j=0;j<2*n-2*i-2;j++) {
			System.out.print(' ');
		}//�����һ��*֮ǰ�Ŀո�
		System.out.print('*');
		for(j=0;j<4*i-1;j++) {
			System.out.print(' ');
					}//����ڶ���*֮ǰ�Ŀո�
		System.out.println('*');
		}//�ڴ�֮ǰ�Ĵ���������Ҳ�Ƚ���ȷ������֮��Ĵ���д��ʱ���Լ��е��Σ�һֱ���Ƶ�n,i�Ϳո�֮��Ĺ�ϵ���������Ҳ�Ǹ�������
		else {
			for(j=0;j<(i-n+1)*2;j++) {
				System.out.print(' ');
			}
			System.out.print('*');
			for(j=0;j<8*n-4*i-9;j++) {
				System.out.print(' ');
						}
			System.out.println('*');
		}
		
	}
	for(i=0;i<(n-1)*2;i++) {
		System.out.print(' ');
	}
	System.out.println('*');
}
}