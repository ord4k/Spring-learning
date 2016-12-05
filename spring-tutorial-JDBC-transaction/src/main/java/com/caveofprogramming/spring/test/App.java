package com.caveofprogramming.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class App {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/caveofprogramming/spring/test/beans/beans.xml");

		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		
		//to delete given ID
/*
			offersDao.delete(23);
*/

		try {
			
			/*			//update
			Offer updateOffer = new Offer(7, "Karen2","kave2@op.pl", "2");
			if(offersDao.update(updateOffer)) {
				System.out.println("Object updated");
			}
			else {
				System.out.println("Cannot update an object");
			}
 			*/
			
/*			//to Create a new ID in database, 
			Offer offer1 = new Offer("Dave", "dave@op.pl", "Coding Java");
			Offer offer2 = new Offer("Karen", "Karen@op.pl", "Software testing to order");
			
			
			if(offersDao.create(offer1)) {
				System.out.println(("Created offer object"));	
			}
			
			if(offersDao.create(offer2)) {
				System.out.println(("Created offer object2"));	
			}
*/
			//to get offer for the given ID
/*			Offer offer = offersDao.getOffer();
			System.out.println(offer);
*/

		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
		}
		
		try {
			List<Offer> offers = offersDao.getOffers();
			for(Offer each : offers) {
				System.out.println(each);
				}
			

		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
		}
		
	

		// to printout list of all beans being created
		//PrintBeans beans = new PrintBeans(context);
		
		//Batch update
		List<Offer> offersList = new ArrayList<Offer>();
		
		offersList.add(new Offer(22,"name","Dave@op.pl", "Cash"));
		offersList.add(new Offer(23, "Karen","Karen@op.pl", "Elegant web design"));
		
		int [] rvals = offersDao.create(offersList);
		
		for(int value : rvals) {
			System.out.println("Updated " + value + " rows");
		}
		

		((ClassPathXmlApplicationContext) context).close();
	}
	
}
