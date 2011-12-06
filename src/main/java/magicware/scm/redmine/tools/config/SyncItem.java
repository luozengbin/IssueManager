package magicware.scm.redmine.tools.config;

public class SyncItem {

	private String filePath;

	private String sheetName;

	private String jsonTemplate;

	private int keyColumnIdx;
	
	private int keyRowBeginIdx;

	private String keyFiledId;

	private String projectId;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getJsonTemplate() {
		return jsonTemplate;
	}

	public void setJsonTemplate(String jsonTemplate) {
		this.jsonTemplate = jsonTemplate;
	}

	public int getKeyColumnIdx() {
		return keyColumnIdx;
	}

	public void setKeyColumnIdx(int keyColumnIdx) {
		this.keyColumnIdx = keyColumnIdx;
	}

	public int getKeyRowBeginIdx() {
		return keyRowBeginIdx;
	}

	public void setKeyRowBeginIdx(int keyRowBeginIdx) {
		this.keyRowBeginIdx = keyRowBeginIdx;
	}

	public String getKeyFiledId() {
		return keyFiledId;
	}

	public void setKeyFiledId(String keyFiledId) {
		this.keyFiledId = keyFiledId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
