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


