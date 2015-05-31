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
                .getClassLoader().getResource("data/config.json").getFile());

        config = ConfigFacade.getConfig();
    }

    //@Test
    public void testReadJSONTemplate() throws InvalidFormatException,
            IOException {
        for (SyncItem syncItem : config.getSyncItems()) {
            String issueTemplate = FileUtils.readFileAsString(syncItem
                    .getJsonTemplate());
            log.debug(issueTemplate);
        }
    }

    //@Test
    public void testExecuteSimple() throws InvalidFormatException, IOException {
        for (SyncItem syncItem : config.getSyncItems()) {
            app.execute(syncItem);
        }
    }

}
