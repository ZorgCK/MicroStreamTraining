package one.microstream.microstreamtraining.business.storage;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import one.microstream.microstreamtraining.business.storage.backup.StorageBackupJob;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	private Scheduler BackupScheduler;

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		try {
			initializeAndStartBackupScheduler();
		} catch (final SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		try {
			this.BackupScheduler.shutdown();
		} catch (final SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StorageUtils.STORAGE.shutdown();
	}

	/**
	 * Erstellt ein Backup alle 6 Stunden
	 * 
	 * @throws SchedulerException
	 */
	public void initializeAndStartBackupScheduler() throws SchedulerException {
		this.BackupScheduler = StdSchedulerFactory.getDefaultScheduler();

		final JobDetail job = newJob(StorageBackupJob.class)
				.withIdentity("BackupSchedulerJob", "BackupSchedulerJobGroup").build();

		// Test Trigger
		final Trigger trigger = newTrigger().withIdentity("BackupSchedulerJobTrigger", "BackupSchedulerJobGroup")
				.startNow().withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *"))
				.forJob("BackupSchedulerJob", "BackupSchedulerJobGroup").build();

		this.BackupScheduler.scheduleJob(job, trigger);
		this.BackupScheduler.start();

		logger.info("Logger: BackupScheduler gestartet");
	}
}
