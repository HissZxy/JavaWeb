public class thirtyninesteps {
	public static int sum;
	public static void dfs(int step,int count) {
		if(step>39) {
			return;
		}
		if(step==39) {
			if(count%2==0) 
				sum++;
			return;
		}
		
		for(int i=1;i<=2;i++) {
			dfs(step+i,count+1);
		}
		
	}
     public static void main(String args[]) {
    	 dfs(0,0);
    	 System.out.println(sum);
     }
}

