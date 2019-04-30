package one.microstream.microstreamtraining.business.storage;

import java.io.File;

import one.microstream.X;
import one.microstream.microstreamtraining.entities.DataRoot;
import one.microstream.reference.Reference;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

public class StorageUtils {
	public static final Reference<DataRoot> ROOT = X.Reference(new DataRoot());
	public static final EmbeddedStorageManager STORAGE = EmbeddedStorage.start(ROOT, new File("C:/MicroStreamTraining"));
}
