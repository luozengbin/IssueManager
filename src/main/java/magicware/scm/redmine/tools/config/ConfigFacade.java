/*
 * Copyright 2011 Zouhin Ro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
