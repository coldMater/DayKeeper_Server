import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Learner {

	public String learn(String path, String selectedDate) {
	    
	    StringBuffer cmd = new StringBuffer();
	    StringBuffer pout = new StringBuffer();
	    String result = null;
	    cmd.append("python C:\\Users\\Administrator\\Desktop\\smart2\\smart2\\src\\com\\python\\MachineLearner.py");
	    cmd.append(" ");
	    cmd.append(path);
	    cmd.append(" ");
	    cmd.append(selectedDate);
	    
	    System.out.println(path);
	    System.out.println(cmd.toString());
	    
	    try {
	       
	       System.out.println("java start");
	       Runtime r = Runtime.getRuntime();
	       Process p = r.exec(cmd.toString());
	       
	       int resultSign = -1;
	          
	       try{
	          resultSign = p.waitFor();
	       }catch(Exception e) {
	          System.out.println(e);
	       }
	       
	       if(resultSign == 0) {
	          
	          System.out.println("Python operation success");
	          BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
	          
	          String line = "";
	          while((line = bfr.readLine()) != null) {
	             pout.append(line);
	             pout.append("\n");
	          }
	          
	          bfr.close();
	          
	          result = pout.toString();
	          System.out.println(result);
	       } else {
	          System.out.println(resultSign);
	       }
	       
	       System.out.println("java end");
	    } catch (Exception e) {
	       System.out.println(e);
	    }
	    
	    return result;
	}
	
}
