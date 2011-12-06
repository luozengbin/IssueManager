package magicware.scm.redmine.tools;

import java.io.IOException;


import magicware.scm.redmine.tools.Constants;
import magicware.scm.redmine.tools.IssueSyncApp;
import magicware.scm.redmine.tools.config.Config;
import magicware.scm.redmine.tools.config.ConfigFacade;
import magicware.scm.redmine.tools.config.SyncItem;
import magicware.scm.redmine.tools.util.FileUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IssueSyncAppTestCase {

	protected static Logger log = LoggerFactory
			.getLogger(IssueSyncAppTestCase.class);
	
	private static Config config = null;
	
	private IssueSyncApp app = new IssueSyncApp();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty(Constants.CONFIG_FILE, RedmineClientTestCase.class
				.getResource("config.json").getFile());

		config = ConfigFacade.getConfig();
	}

	@Test
	public void testReadJSONTemplate() throws InvalidFormatException, IOException {
		for (SyncItem syncItem : config.getSyncItems()) {
			String issueTemplate = FileUtils.readFileAsString(syncItem.getJsonTemplate());
			log.debug(issueTemplate);
		}
	}

	@Test
	public void testExecuteSimple() throws InvalidFormatException, IOException {
		for (SyncItem syncItem : config.getSyncItems()) {
			app.execute(syncItem);
		}
	}
	
}
