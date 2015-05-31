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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import magicware.scm.redmine.tools.config.ConfigFacade;

public class Constants {

	public static String NORMAL_PROTOCOL = "http";
	public static String SSL_PROTOCOL = "https";
	public static int HTTP_OK = 200;
	public static String CHARSET_ENCODE = "UTF-8";
	
	public static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
        SimpleDateFormat s = new SimpleDateFormat(ConfigFacade.getConfig().getDateFormat());
        s.setLenient(false);
        return s;
    }
};


	public static String CONFIG_FILE = "bts.redmine.config";

	// JSONテンプレートから各項目値を表す式を抽出する正規表現式
	public static String ISSUE_FIELD_VALUE_EXP = "\\$\\{([0-9][0-9]?)\\}(->(\\{[^}]+\\}))?";

	// チケット検索結果から結果件数を抽出する正規表現式
	public static String ISSUE_COUNT_VALUE_EXP = "\"total_count\"[:]([ 0-9]+),";

	// チケット登録レスポンスからチケットIDを抽出する正規表現式
	public static String ISSUE_ID_VALUE_EXP = "\"id\"[:]([ 0-9]+),";
}
