package one.microstream.microstreamtraining.business.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import one.microstream.X;
import one.microstream.chars.StringTable;
import one.microstream.chars.XChars;
import one.microstream.collections.BulkList;
import one.microstream.exceptions.IORuntimeException;
import one.microstream.microstreamtraining.entities.DataRoot;
import one.microstream.persistence.types.PersistenceRefactoringMappingProvider;
import one.microstream.reference.Reference;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageFoundation;
import one.microstream.storage.types.EmbeddedStorageManager;
import one.microstream.storage.types.Storage;
import one.microstream.storage.types.StorageConfiguration.Builder;
import one.microstream.typing.KeyValue;

public class StorageUtils {
	
	public static final Reference<DataRoot> ROOT = X.Reference(new DataRoot());
	public static final EmbeddedStorageManager STORAGE;
	public static final EmbeddedStorageFoundation<?> FOUNDATION;

	static {
		final Builder<?> configurationBuilder = Storage.ConfigurationBuilder()
				.setStorageFileProvider(Storage.FileProvider(new File("C:/MicroStreamTraining")))
				.setBackupSetup(Storage.BackupSetup("C:/MicroStreamTraining/Backup"))
				.setChannelCountProvider(Storage.ChannelCountProvider(4));

		FOUNDATION = EmbeddedStorage.Foundation(configurationBuilder.createConfiguration());
		
		// refactoring.csv einlesen und anwenden
		FOUNDATION.setRefactoringMappingProvider(RefactoringMappingFromClasspath());
		
		STORAGE = FOUNDATION.createEmbeddedStorageManager(ROOT).start();

	}
	
	public static PersistenceRefactoringMappingProvider RefactoringMappingFromClasspath() {
		final String fileContent;
		
		try (InputStream in = StorageUtils.class.getResourceAsStream("refactorings.csv")) {
			fileContent = XChars.readStringFromInputStream(in, StandardCharsets.UTF_8);
		} catch (final IOException ioe) {
			throw new IORuntimeException(ioe);
		}

		final StringTable stringTable = StringTable.Static.parse(fileContent);
		final BulkList<KeyValue<String, String>> entries = BulkList.New(stringTable.rows().size());

		stringTable.mapTo((k, v) -> entries.add(X.KeyValue(k, v)), row -> XChars.trimEmptyToNull(row[0]),
				row -> XChars.trimEmptyToNull(row[1]));

		return PersistenceRefactoringMappingProvider.New(entries);
	}
}
