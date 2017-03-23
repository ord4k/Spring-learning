package com.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("com/test/test/beans/beans.xml");

		Person person = (Person) context.getBean("person");

		person.setTaxId(666);

		System.out.println(person);
		Address address2 = (Address) context.getBean("address");
		System.out.println(address2);

		System.out.println(person.number());

		((ClassPathXmlApplicationContext) context).close();

	}

}
