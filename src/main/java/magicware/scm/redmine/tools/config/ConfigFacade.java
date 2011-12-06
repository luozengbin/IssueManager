package magicware.scm.redmine.tools.config;

import magicware.scm.redmine.tools.Constants;
import magicware.scm.redmine.tools.util.FileUtils;
import net.arnx.jsonic.JSON;

public class ConfigFacade {

	private static final ConfigFacade instance = new ConfigFacade(
			System.getProperty(Constants.CONFIG_FILE));

	protected Config config = null;

	private ConfigFacade(String configFile) {
		try {
			config = JSON.decode(FileUtils.readFileAsString(configFile),
					Config.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Config getConfig() {
		return instance.config;
	}
}
