package com.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class Person {

	private int id;
	private String name;
	private int taxId;
	private Address address;

	@Autowired
	private Number number;

	public Number getNumber() {
		return number;
	}

	public void setNumber(Number number) {
		this.number = number;
	}

	public Person() {

	}

	public int number() {
		return number.getRandomNumber();

	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Person getInstance(int id, String name) {
		System.out.println("Creating Person using factory method");
		return new Person(id, name);
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void onCreate() {
		System.out.println("Person created:" + this);
	}

	public void onDestroy() {
		System.out.println("Person destroyed");
	}

	public void speak() {
		System.out.println("Hello! My name is MIchal");
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", taxId=" + taxId + ", address=" + address + "]";
	}
}
