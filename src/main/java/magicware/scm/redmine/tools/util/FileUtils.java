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
                    new FileInputStream(new File(path)),
                    Constants.CHARSET_ENCODE));
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
