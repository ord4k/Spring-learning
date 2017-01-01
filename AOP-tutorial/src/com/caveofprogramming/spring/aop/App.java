package com.caveofprogramming.spring.aop;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.caveofprogramming.spring.camera.accessories.Lens;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/caveofprogramming/spring/aop/beans.xml");
	
		Object obj = context.getBean("camera");
		System.out.println(obj.getClass());
		System.out.println(obj instanceof PhotoSnapper);
		
		Camera camera = (Camera) context.getBean("camera");
		
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println("Caught exception: " +e.getMessage());	
		}
	
	
	
		context.close();
	}
}
