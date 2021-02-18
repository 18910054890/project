package com.lyzd.om.shared.service;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import com.lyzd.om.shared.model.common.JobModel;


public interface JobService {

	void saveJob(JobModel jobModel);

	void doJob(JobDataMap jobDataMap);

	void deleteJob(Long id) throws SchedulerException;
}
