package one.microstream.microstreamtraining.business.storage.backup;

import java.io.File;

import one.microstream.microstreamtraining.business.storage.StorageUtils;
import one.microstream.persistence.internal.PersistenceTypeDictionaryFileHandler;
import one.microstream.persistence.types.PersistenceTypeDictionary;
import one.microstream.persistence.types.PersistenceTypeDictionaryAssembler;
import one.microstream.storage.types.Storage;
import one.microstream.storage.types.StorageConnection;
import one.microstream.storage.types.StorageFileProvider;
import one.microstream.storage.types.StorageFileWriter;
import one.microstream.storage.types.StorageIoHandler;

public class BackupHelper {

	public void createBackup(final File targetDirectory) {
		this.backupData(targetDirectory);
		this.backupMetadata(targetDirectory);
	}

	protected void backupData(final File targetDirectory) {
		final StorageConnection storageConnection = StorageUtils.STORAGE.createConnection();
		final StorageFileProvider backupFileProvider = Storage.FileProvider(targetDirectory);
		storageConnection.exportChannels(
				new StorageIoHandler.Implementation(backupFileProvider, new StorageFileWriter.Implementation()), false);
	}

	protected void backupMetadata(final File targetDirectory) {
		final PersistenceTypeDictionaryAssembler tDictAssembler = StorageUtils.FOUNDATION.getConnectionFoundation()
				.getTypeDictionaryAssembler();
		final PersistenceTypeDictionary tDictionary = StorageUtils.STORAGE.createConnection().persistenceManager()
				.typeDictionary();
		final String tDictString = tDictAssembler.assemble(tDictionary);

		final File fileTDc = new File(targetDirectory, "PersistenceTypeDictionary.ptd");
		PersistenceTypeDictionaryFileHandler.writeTypeDictionary(fileTDc, tDictString);
	}
}
