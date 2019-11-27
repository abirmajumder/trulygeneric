package com.trulygeneric.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.datamodel.repository.ApplicationRepository;

@Component
public class Starter implements InitializingBean {

	@Autowired ApplicationRepository appRepo;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		appRepo.findAll().forEach( System.out::println);
		
	}
	
}
