package Tools;

import com.alibaba.appengine.api.fetchurl.FetchUrlService;
import com.alibaba.appengine.api.fetchurl.FetchUrlServiceFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class Downloadtool {
	
	public static InputStream downloadImage(String urlString)
			throws Exception {
		//构造代理 本地测试时需要注释掉代理部分
//		Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress("10.242.175.127", 3128));
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
//		URLConnection con = url.openConnection(proxy);
		URLConnection con = url.openConnection();
		// 输入流
		String headerKey = "Proxy-Authorization"; 
		String auth = "1099691601436921_default_-2"+":"+"r3b8kc7z9k";
		
		String headerValue = "Basic " + new sun.misc.BASE64Encoder
				().encode(auth.getBytes()); 
//		con.setRequestProperty(headerKey, headerValue); 
		
		InputStream is = con.getInputStream();
		
		return is;
	}
	
	public static InputStream getImage(String urlString) {
		FetchUrlService fetchUrlService = FetchUrlServiceFactory.getFetchUrlService();
		String body = fetchUrlService.get(urlString);
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(body.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return is;
	}
	
}
