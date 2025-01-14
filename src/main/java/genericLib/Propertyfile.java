package genericLib;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.Properties;
//
//public class Propertyfile {
//
//	public String getData(String key) throws FileNotFoundException {
//		
//		Properties p= new Properties();
//	    FileInputStream fis=new FileInputStream(AutoConstant.propertyfilePath);
//	    return p.getProperty(key);  
//	}
//}


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertyfile {

    private Properties properties;

    
    public Propertyfile() {
        try {
            FileInputStream fis = new FileInputStream(AutoConstant.propertyfilePath);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    
    public String getData(String key) {
        return properties.getProperty(key);
    }
}
