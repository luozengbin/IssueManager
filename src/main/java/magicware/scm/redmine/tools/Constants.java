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

public interface Constants {
	
	String NORMAL_PROTOCOL = "http";
	String SSL_PROTOCOL = "https";
	int HTTP_OK = 200;
	String CHARSET_ENCODE = "UTF-8";
	DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	
	String CONFIG_FILE = "bts.redmine.config";
	
	//JSONテンプレートから各項目値を表す式を抽出する正規表現式
	String ISSUE_FIELD_VALUE_EXP = "\\$\\{([0-9][0-9]?)\\}(->(\\{[^}]+\\}))?";
	
	//チケット検索結果から結果件数を抽出する正規表現式
	String ISSUE_COUNT_VALUE_EXP = "\"total_count\"[:]([ 0-9]+),";
	
	//チケット登録レスポンスからチケットIDを抽出する正規表現式
	String ISSUE_ID_VALUE_EXP = "\"id\"[:]([ 0-9]+),";
}


