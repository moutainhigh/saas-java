package com.hq.chainStore.service.leaguerAffair.apiData;

public class DelArchives {
	private long archivesId;

	public static DelArchives newInstance() {
		return new DelArchives();
	}

	public long getArchivesId() {
		return archivesId;
	}

	public void setArchivesId(long archivesId) {
		this.archivesId = archivesId;
	}

}
