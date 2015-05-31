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
package magicware.scm.redmine.tools.config;

public class Config {

	private String redmineHost;

	private int redminePort;

	private String redmineContext;

	private String redmineAuthUser;

	private String redmineAuthPwd;

	private SyncItem[] syncItems;
	
	private String redmineApiKey;
	
	private String dateFormat = "yyyy/MM/dd";

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

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

	public String getRedmineApiKey() {
		return redmineApiKey;
	}

	public void setRedmineApiKey(String redmineApiKey) {
		this.redmineApiKey = redmineApiKey;
	}


	
}
