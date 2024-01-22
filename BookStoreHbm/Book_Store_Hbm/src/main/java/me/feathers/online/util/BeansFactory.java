package me.feathers.online.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeansFactory {

	//1.创建一个Properties对象
	private static Properties prop = new Properties();
	
	//2.通过静态代码块来初始化prop
	static{
		//2-1.创建一个字节输入流
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("beans.properties");
		
		//2-2.加载
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("对不起!beans.properties文件加载失败!");
		}
	}
	
	//3.提供一个方法.来返回根据key获取value
	public static Object getBean(String key){
		if(prop==null)
			return null;
		try {
			return Class.forName(prop.getProperty(key)).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
