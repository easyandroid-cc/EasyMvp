package com.easymvp.simple.simple;

public class AppInfo {
	private String appUpdateUrl;
	private String latestVersion;
	private int latestVersionCode;// 等待后台添加
	private String latestVersionName;// 等待后台添加
	private String updateContent;
	private int isRequire = 0;// 是否强制升级

	public int getLatestVersionCode() {
		return latestVersionCode;
	}

	public void setLatestVersionCode(int latestVersionCode) {
		this.latestVersionCode = latestVersionCode;
	}

	public String getLatestVersionName() {
		return latestVersionName;
	}

	public void setLatestVersionName(String latestVersionName) {
		this.latestVersionName = latestVersionName;
	}

	public String getAppUpdateUrl() {
		return appUpdateUrl;
	}

	public void setAppUpdateUrl(String appUpdateUrl) {
		this.appUpdateUrl = appUpdateUrl;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public int getIsRequire() {
		return isRequire;
	}

	public void setIsRequire(int isRequire) {
		this.isRequire = isRequire;
	}

	public boolean isCompelUpgrade() {
		if (isRequire == 1) {
			return true;
		} else {
			return false;
		}
	}

}
