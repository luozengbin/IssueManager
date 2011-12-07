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

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import magicware.scm.redmine.tools.Constants;
import magicware.scm.redmine.tools.RedmineClient;
import magicware.scm.redmine.tools.config.Config;
import magicware.scm.redmine.tools.config.ConfigFacade;
import magicware.scm.redmine.tools.model.Issue;
import magicware.scm.redmine.tools.util.FileUtils;
import net.arnx.jsonic.JSON;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedmineClientTestCase {

    protected static Logger log = LoggerFactory
            .getLogger(RedmineClientTestCase.class);

    private static RedmineClient redmineClient = null;

    private static Config config = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty(Constants.CONFIG_FILE, RedmineClientTestCase.class
                .getClassLoader().getResource("data/config.json").getFile());

        config = ConfigFacade.getConfig();
        redmineClient = new RedmineClient(config.getRedmineHost(),
                config.getRedminePort(), config.getRedmineContext());
    }

    @Before
    public void setUpBefore() throws Exception {
        redmineClient.fillBasicAuth(config.getRedmineAuthUser(),
                config.getRedmineAuthPwd());
    }

    @Test
    public void testDoBasicAuth() throws ClientProtocolException, IOException {
        int status = redmineClient.request("/");
        assertTrue(status >= 200 && status < 300);
    }

    @Test
    public void testQueryIssue() throws ClientProtocolException, IOException {
        assertTrue((redmineClient.queryIssue("8", "cf_16", "xxx")) == 0);
        assertTrue((redmineClient.queryIssue("8", "cf_16", "115")) == 1);
    }

    // @Test
    public void testDeleteIssue() throws ClientProtocolException, IOException {
        assertTrue(redmineClient.deleteIssue("722"));
    }

    // @Test
    public void testCreateNewIssue() throws IOException {

        String testJsonFile = RedmineClientTestCase.class.getResource(
                "issue_001.json").getFile();

        String newIssue = FileUtils.readFileAsString(testJsonFile);

        log.debug(newIssue);

        Map<String, Issue> issueMap = JSON.decode(newIssue);
        log.debug(JSON.encode(issueMap, true));

        String newIssueId = redmineClient.createNewIssue(newIssue);
        assertTrue(newIssueId != null);
        log.info("newIssueId -> " + newIssueId);

        // if(newIssueId != null){
        // assertTrue(redmineClient.deleteIssue(newIssueId));
        // }
    }

}
