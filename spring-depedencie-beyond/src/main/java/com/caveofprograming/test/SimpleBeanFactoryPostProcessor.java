package com.caveofprograming.test;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SimpleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	Logger logger = Logger.getAnonymousLogger().getLogger("SimpleBeanFactoryPostProcessor");

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info("In postPRocessBeanFactory");
	}

}
