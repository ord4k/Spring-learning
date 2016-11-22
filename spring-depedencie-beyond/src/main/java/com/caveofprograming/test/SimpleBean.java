package com.caveofprograming.test;

import java.util.logging.Logger;

public class SimpleBean {
	Logger logger = Logger.getLogger("SimpleBean");
	
	/**
	 * Call to this method is triggered by the existance of the @PostConstruct annotation
	 * Plus the configuration of either <context:annotation-config> or the JavaCOnfig equation
	 * 
	 * This method will be called first (before the call to the initMethod)
	 * 
	 * see javax.annotation.PostConstruct
	 */

	@PostConstruct
	public void aPostConstructorMethod() {
		logger.info("aPostConstructMethod() called");
	}
	
	/**
	 * *This is another initialization method designed to be called by the presentce of the attribute
	 * be <bean> tag in the Spring XML confugration file.
	 * 
	 * THis method should be called after the post construct method above
	 * */
	public void anInitMethod() {
		logger.info("anIniMethod() called");
	}





}

