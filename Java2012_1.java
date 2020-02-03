package LanQiaoBei;
 
import java.text.DecimalFormat;
 
public class Java2012_1 {
 
	public static void main(String[] args) {
		double x = 1;
		double y = 3;
		double x1 = x;
		String finalValue = "0.618034";
		double temp = 1/3;
		String result = "";
		DecimalFormat df = new DecimalFormat("0.000000");
		while(!finalValue.equals(result)) {
			x1 = x;
			x = y;
			y += x1; 
			temp = x/y;
			result = df.format(temp);
		}
		System.out.println((int)x + "/" + (int)y);
	}
 
}