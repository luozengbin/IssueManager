package magicware.scm.redmine.tools;

import java.io.IOException;


import magicware.scm.redmine.tools.config.Config;
import magicware.scm.redmine.tools.config.ConfigFacade;
import magicware.scm.redmine.tools.config.SyncItem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class AppEntry {

	public static void main(String[] args) throws InvalidFormatException,
			IOException {

		System.getProperty(Constants.CONFIG_FILE, args[0]);

		Config config = ConfigFacade.getConfig();

		IssueSyncApp app = new IssueSyncApp();

		for (SyncItem syncItem : config.getSyncItems()) {
			app.execute(syncItem);
		}
	}

}
