package one.microstream.microstreamtraining.business.storage;

import java.io.File;

import one.microstream.X;
import one.microstream.microstreamtraining.entities.DataRoot;
import one.microstream.reference.Reference;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageFoundation;
import one.microstream.storage.types.EmbeddedStorageManager;
import one.microstream.storage.types.Storage;
import one.microstream.storage.types.StorageConfiguration.Builder;

public class StorageUtils {
	
	public static final Reference<DataRoot> ROOT = X.Reference(new DataRoot());
	public static final EmbeddedStorageManager STORAGE;
	public static final EmbeddedStorageFoundation<?> FOUNDATION;
	public final static File incrementalBackup = new File("C:/MicroStreamTraining/IncrementalBackup");
	static {
		final Builder<?> configurationBuilder = Storage.ConfigurationBuilder()
				.setStorageFileProvider(Storage.FileProvider(new File("C:/MicroStreamTraining")))
				.setBackupSetup(Storage.BackupSetup("C:/MicroStreamTraining/Backup"))
				.setChannelCountProvider(Storage.ChannelCountProvider(4));

		FOUNDATION = EmbeddedStorage.Foundation(configurationBuilder.createConfiguration());
		STORAGE = FOUNDATION.createEmbeddedStorageManager(ROOT).start();
	}
}
