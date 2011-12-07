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
import static org.junit.Assert.fail;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import magicware.scm.redmine.tools.Constants;

import org.junit.Test;

public class GetIssueCountRegExpTestCase {

	@Test
	public void testGetIssueCountExp01() throws Exception {
		
		String testString = "\"total_count\": 100,";

		Matcher m = Pattern.compile(Constants.ISSUE_COUNT_VALUE_EXP).matcher(
				testString);
		int count = 0;
		while (m.find()) {
			MatchResult mr = m.toMatchResult();
			assertTrue(Integer.valueOf(mr.group(1).trim()) == 100);
			count++;
		}
		assertTrue(count == 1);
	}

	@Test
	public void testGetIssueCountExp02() throws Exception {
		
		String testString = "\"total_count\": 100 ,";

		Matcher m = Pattern.compile(Constants.ISSUE_COUNT_VALUE_EXP).matcher(
				testString);
		int count = 0;
		while (m.find()) {
			MatchResult mr = m.toMatchResult();
			assertTrue(Integer.valueOf(mr.group(1).trim()) == 100);
			count++;
		}
		assertTrue(count == 1);
	}
	
	@Test
	public void testGetIssueCountExp03() throws Exception {
		
		String testString = "\"total_count\":100,";

		Matcher m = Pattern.compile(Constants.ISSUE_COUNT_VALUE_EXP).matcher(
				testString);
		int count = 0;
		while (m.find()) {
			MatchResult mr = m.toMatchResult();
			assertTrue(Integer.valueOf(mr.group(1).trim()) == 100);
			count++;
		}
		assertTrue(count == 1);
	}
	
	@Test
	public void testGetIssueCountExp04() throws Exception {
		
		String testString = "total_count:100,";

		Matcher m = Pattern.compile(Constants.ISSUE_COUNT_VALUE_EXP).matcher(
				testString);
		int count = 0;
		while (m.find()) {
			fail(Constants.ISSUE_COUNT_VALUE_EXP + " is not correct!!!");
		}
		assertTrue(count == 0);
	}
	
}
