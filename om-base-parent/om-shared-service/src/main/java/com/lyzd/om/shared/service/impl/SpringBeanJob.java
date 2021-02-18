package com.lyzd.om.shared.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lyzd.om.shared.service.JobService;


public class SpringBeanJob extends QuartzJobBean {
	
	public static final String KEY = "applicationContextSchedulerContextKey";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
					.get(KEY);
					//.get(JobConfig.KEY);
			JobService jobService = applicationContext.getBean(JobService.class);
			jobService.doJob(context.getJobDetail().getJobDataMap());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
