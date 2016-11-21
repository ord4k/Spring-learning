package com.test.test;

public class PersonFactory {
	public Person createPerson(int id, String name) {
		System.out.println("Using PersonFactory to create person");
		return new Person(id, name);
	}

}
