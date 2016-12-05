package com.caveofprogramming.spring.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class PrintBeans {

	public List<Object> singletons;

	public PrintBeans(ApplicationContext ctx) {
		printBeans(getInstantiatedSigletons(ctx));
	}

	public  List<Object> getInstantiatedSigletons(ApplicationContext ctx) {

		singletons = new ArrayList<Object>();

		String[] all = ctx.getBeanDefinitionNames();

		ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
		for (String name : all) {
			Object s = clbf.getSingleton(name).getClass().getName();
			if (s != null)
				singletons.add(s);
		}

		return singletons;
	}

	public void printBeans(List<Object> list) {
		int id = 0;

		for (Object each : singletons) {
			id++;
			System.out.println(id + ": " + each);
		} 

	}
}