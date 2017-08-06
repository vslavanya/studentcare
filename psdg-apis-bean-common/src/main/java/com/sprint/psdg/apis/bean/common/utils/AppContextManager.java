/*
* Copyright (c) 2015 Sprint Corp. All rights reserved.
* Sprint Corp Confidential and Proprietary information. Do not redistribute.
*/
package com.sprint.psdg.apis.bean.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Lal V S
 *
 */
@Component
public class AppContextManager implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    

    public static ApplicationContext getAppContext(){
        return applicationContext;
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	} 
}

