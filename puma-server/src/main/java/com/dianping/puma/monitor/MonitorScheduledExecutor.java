package com.dianping.puma.monitor;

import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.dianping.puma.core.util.ScheduledExecutorUtils;

@Component("monitorScheduledExecutor")
public class MonitorScheduledExecutor implements InitializingBean{
	 
	public static MonitorScheduledExecutor instance;
	
	private ScheduledExecutorService executorService = null;

	private static final int MAX_THREAD_COUNT = 5;

	private static final String THREAD_FACTORY_NAME = "monitor";

	public MonitorScheduledExecutor() {
		setExecutorService(ScheduledExecutorUtils.createScheduledExecutorService(MAX_THREAD_COUNT, THREAD_FACTORY_NAME));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		instance = this;
	}

	public void setExecutorService(ScheduledExecutorService executorService) {
		this.executorService = executorService;
	}

	public ScheduledExecutorService getExecutorService() {
		return executorService;
	}
	
	public boolean isScheduledValid(){
		if (getExecutorService() != null
				&& !getExecutorService().isShutdown()
				&& !getExecutorService().isTerminated()) {
			return true;
		}
		return false;
	}
}