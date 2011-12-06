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
