package Functions;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;


	public class mngr {
	    public static ExtentReports extent;
	    static String filePath;
	    
	    public synchronized static ExtentReports getReporter() {
	    	
	    	try {
	    	
	    		
	    	switch(System.getProperty("user.name").toLowerCase()){

	    		case "Khonwani":

			        		FileUtils.deleteDirectory(new File(System.getProperty("user.dir")+"\\report\\jenkins-report\\target"));
			        		filePath = (System.getProperty("user.dir"))+ "\\report\\jenkins-report\\WebContent\\index.html";
			        		break;

			      default:
		   
	    				filePath = (System.getProperty("user.dir"))+"\\report\\index.html";
	    				break;
	    		}
	    	
	    	if (extent == null) {
	            extent = new ExtentReports(filePath, true);
	        }
	    	
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    
	        
	        return extent;
	    }
	}