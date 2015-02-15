package org.mycomp;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerTrail {

	public static void main(String[] args) throws SchedulerException {
		JobDetail job = new JobDetail();
    	job.setName("dummyJobName");
    	job.setJobClass(MyJob.class);
 
    	//configure the scheduler time
    	SimpleTrigger trigger = new SimpleTrigger();
    	trigger.setName("trail");
    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    	trigger.setRepeatInterval(30000);
 
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
	}

}
