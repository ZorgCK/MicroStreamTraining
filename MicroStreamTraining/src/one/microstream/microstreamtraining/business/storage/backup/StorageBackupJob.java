package one.microstream.microstreamtraining.business.storage.backup;

import java.io.File;
import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xdev.Application;

import one.microstream.microstreamtraining.business.storage.StorageUtils;

public class StorageBackupJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(StorageBackupJob.class);

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		Application.getExecutorService().execute(new Runnable() {

			@Override
			public void run() {
				final String path = StorageUtils.incrementalBackup.getAbsolutePath();
				final BackupHelper helper = new BackupHelper();

				final LocalDateTime now = LocalDateTime.now();
				final File file = new File(path + File.separator + "Backup_" + now.getDayOfMonth() + now.getMonthValue()
						+ now.getYear() + now.getHour() + now.getMinute());
				file.mkdir();
				helper.createBackup(file);

				logger.info("Backup successfull created at " + now);
			}
		});
	}

}
