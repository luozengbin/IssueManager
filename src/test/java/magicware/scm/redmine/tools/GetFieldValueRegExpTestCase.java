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

import static org.junit.Assert.*;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import magicware.scm.redmine.tools.Constants;

import org.junit.Test;

public class GetFieldValueRegExpTestCase {

    @Test
    public void testAllGroup() throws Exception {

        String testString = "xxxxx ${6}->{\"default\":\"4\", \"低\":\"3\", \"中\":\"4\", \"高\":\"5\"} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);

        int count = 0;
        while (m.find()) {
            MatchResult mr = m.toMatchResult();

            assertTrue("${6}->{\"default\":\"4\", \"低\":\"3\", \"中\":\"4\", \"高\":\"5\"}"
                    .equals(mr.group()));

            assertTrue(mr.groupCount() == 3);
            assertTrue("6".equals(mr.group(1)));
            assertTrue("{\"default\":\"4\", \"低\":\"3\", \"中\":\"4\", \"高\":\"5\"}"
                    .equals(mr.group(3)));

            count++;
        }

        assertTrue(count == 1);
    }

    @Test
    public void testGroup1() throws Exception {

        String testString = "xxxxx ${6} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            MatchResult mr = m.toMatchResult();
            assertTrue("${6}".equals(mr.group()));
            assertTrue(mr.groupCount() == 3);
            assertTrue("6".equals(mr.group(1)));
            count++;
        }
        assertTrue(count == 1);
    }

    @Test
    public void testGroup1IsNumberic() throws Exception {

        String testString = "xxxxx ${x} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            fail(Constants.ISSUE_FIELD_VALUE_EXP + " is not correct!!!");
        }
        assertTrue(count == 0);
    }

    @Test
    public void testGroup1Contain2Numberic() throws Exception {

        String testString = "xxxxx ${12} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            MatchResult mr = m.toMatchResult();
            assertTrue("12".equals(mr.group(1)));
            count++;
        }
        assertTrue(count == 1);
    }

    @Test
    public void testGroup1NotContain3Numberic() throws Exception {

        String testString = "xxxxx ${123} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            fail(Constants.ISSUE_FIELD_VALUE_EXP + " is not correct!!!");
        }
        assertTrue(count == 0);
    }

    @Test
    public void testGroup3() throws Exception {

        String testString = "xxxx ${6}->{\"default\":\"4\"} yyyy }xxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            "{\"default\":\"4\"}".equals(m.group(3));
            count++;
        }
        assertTrue(count == 1);
    }

    @Test
    public void testUnMatched() throws Exception {

        String testString = "xxxxx &{6} xxxxxx";

        Matcher m = Pattern.compile(Constants.ISSUE_FIELD_VALUE_EXP).matcher(
                testString);
        int count = 0;
        while (m.find()) {
            fail(Constants.ISSUE_FIELD_VALUE_EXP + " is not correct!!!");
        }
        assertTrue(count == 0);
    }

}
