package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	public static String getValue(String key) throws IOException
	{
		ClassLoader loader = PropertiesReader.class.getClassLoader();
		InputStream input = loader.getResourceAsStream("/config.properties");
		//创建一个Properties容器 
        Properties prop = new Properties(); 
        //从流中加载properties文件信息 
        prop.load(input); 
        //循环输出配置信息 
        return (String) prop.get(key);
	}
}