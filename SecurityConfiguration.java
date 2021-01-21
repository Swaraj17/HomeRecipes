package com.abnamro.dkw.recipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.abnamro.dkw.recipes.dao.SecurityConfigDao;

/** This class is called as the application is started and calls a DAO method.
 */
@Configuration
public class SecurityConfiguration implements ApplicationRunner{
	
	
	@Autowired
	private SecurityConfigDao securityConfigDao;
	
	/**This method calls the getConnection menthod of securityConfigDao class 
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		securityConfigDao.getConnection();
	}

}
