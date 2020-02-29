package project01_2;
import java.util.Scanner;
public class Main {
public static void main(String[] args) {
	int i,j;
	Scanner out = new Scanner(System.in);
	int n =out.nextInt();
	for(i=0;i<(n-1)*2;i++) {
		System.out.print(' ');
	}//分步打出第一行
	System.out.println('*');		
	for(i=1;i<2*n-2;i++){
		if(i<n) {
			for(j=0;j<2*n-2*i-2;j++) {
			System.out.print(' ');
		}//打出第一个*之前的空格
		System.out.print('*');
		for(j=0;j<4*i-1;j++) {
			System.out.print(' ');
					}//打出第二个*之前的空格
		System.out.println('*');
		}//在此之前的代码清晰简单也比较明确，在这之后的代码写的时候自己有点晕，一直在推导n,i和空格之间的关系，好在最后也是搞明白了
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