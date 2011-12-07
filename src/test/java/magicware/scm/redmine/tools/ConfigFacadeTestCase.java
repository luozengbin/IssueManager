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

import magicware.scm.redmine.tools.Constants;
import magicware.scm.redmine.tools.config.Config;
import magicware.scm.redmine.tools.config.ConfigFacade;
import magicware.scm.redmine.tools.config.SyncItem;
import net.arnx.jsonic.JSON;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigFacadeTestCase {

    protected static Logger log = LoggerFactory
            .getLogger(ConfigFacadeTestCase.class);

    @Test
    public void testGetSampleJson() {

        Config config = new Config();
        config.setRedmineHost("192.168.0.10");
        config.setRedminePort(80);
        config.setRedmineContext("/redmine");
        config.setRedmineAuthUser("redmine");
        config.setRedmineAuthPwd(Base64.encodeBase64String("dummy".getBytes()));

        SyncItem[] syncItems = new SyncItem[1];
        syncItems[0] = new SyncItem();
        syncItems[0].setFilePath("D:\\QA表一覧.xls");
        syncItems[0].setSheetName("問い合せ一覧");
        syncItems[0].setJsonTemplate("D:/issue_template_001.json");
        syncItems[0].setProjectId("8");
        syncItems[0].setKeyFiledId("cf_16");
        syncItems[0].setKeyColumnIdx(1);

        config.setSyncItems(syncItems);
        log.info("sample config json string");
        log.info(JSON.encode(config, true));
    }

    @Test
    public void test() {
        String testConfigFile = ConfigFacadeTestCase.class.getClassLoader()
                .getResource("data/config.json").getFile();

        System.setProperty(Constants.CONFIG_FILE, testConfigFile);
        log.info("Config File: " + testConfigFile);
        log.info(JSON.encode(ConfigFacade.getConfig(), true));
    }
}
