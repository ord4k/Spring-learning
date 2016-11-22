package com.caveofprograming.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainRunner {

	public static void main(String[] args) {
		/*
		 * Based on tutorial https://www.youtube.com/watch?v=JfgP566BHW0 starting at min. 60
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/caveofprograming/test/beans/beans.xml");
		
		String[] beans = ac.getBeanDefinitionNames();
		System.out.println("Load Beans");
		for (String bean : beans) {
			Object beanInstance = ac.getBean(bean);
			System.out.println("Bean: " + bean + " Class: " + beanInstance.getClass().getName());
		}
		

	}

}
