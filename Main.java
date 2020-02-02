public class Main {
     public static int a[] = new int[7];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      for(int i1=0;i1<10;i1++) {
    	   for(int i2=0;i2<10;i2++) {
    		   for(int i3=0;i3<10;i3++) {
    			   for(int i4=0;i4<10;i4++) {
    				   for(int i5=0;i5<10;i5++) {
    					   for(int i6=0;i6<10;i6++) {
    						   for(int i7=0;i7<10;i7++) {
    				    		   a[0] = i1;
    				    		   a[1] = i2;
    				    		   a[2] = i3;
    				    		   a[3] = i4;
    				    		   if(7*i1%10!=i4) {
    				    			   continue;
    				    		   }
    				    		   a[4] = i5;
    				    		   a[5] = i6;
    				    		   a[6] = i7;
    				    		   
    				    		   if(i1 + i2*10+i1 + i3*100+i2*10+i1 + i4*1000+i3*100+i2*10+i1 
    				    				   +i5*10000+i4*1000+i3*100+i2*10+i1
    				    				   +i6*100000+i5*10000+i4*1000+i3*100+i2*10+i1
    				    				   +i7*1000000+i6*100000+i5*10000+i4*1000+i3*100+i2*10+i1
    				    				   ==i4*1000000+i4*100000+i4*10000+i4*1000+i4*100+i4*10+i4) {
    				    			   System.out.println("------------------");
    				    			   for(int j=6;j>-1;j--) {
    				    				   System.out.print(a[j]);
    				    			   }
    				    		   }
    				    	   }
    			    	   }
    		    	   }
    	    	   }
        	   }
       	   }
       }
		 
	}

}
