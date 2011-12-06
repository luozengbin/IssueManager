package magicware.scm.redmine.tools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import magicware.scm.redmine.tools.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	protected static Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	public static String readFileAsString(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			log.info("load file:" + path);
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(path)), Constants.CHARSET_ENCODE));
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				} else {
					sb.append(line);
				}
			}
			return sb.toString();
		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
				reader.close();
			} catch (Exception ignore) {
			}
		}
	}

}
