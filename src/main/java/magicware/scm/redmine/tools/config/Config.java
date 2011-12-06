package magicware.scm.redmine.tools.config;

public class Config {

	private String redmineHost;

	private int redminePort;
	
	private String redmineContext;

	private String redmineAuthUser;

	private String redmineAuthPwd;

	private SyncItem[] syncItems;

	public String getRedmineHost() {
		return redmineHost;
	}

	public void setRedmineHost(String redmineHost) {
		this.redmineHost = redmineHost;
	}

	public int getRedminePort() {
		return redminePort;
	}

	public void setRedminePort(int redminePort) {
		this.redminePort = redminePort;
	}

	public String getRedmineContext() {
		return redmineContext;
	}

	public void setRedmineContext(String redmineContext) {
		this.redmineContext = redmineContext;
	}

	public String getRedmineAuthUser() {
		return redmineAuthUser;
	}

	public void setRedmineAuthUser(String redmineAuthUser) {
		this.redmineAuthUser = redmineAuthUser;
	}

	public String getRedmineAuthPwd() {
		return redmineAuthPwd;
	}

	public void setRedmineAuthPwd(String redmineAuthPwd) {
		this.redmineAuthPwd = redmineAuthPwd;
	}

	public SyncItem[] getSyncItems() {
		return syncItems;
	}

	public void setSyncItems(SyncItem[] syncItems) {
		this.syncItems = syncItems;
	}

}
