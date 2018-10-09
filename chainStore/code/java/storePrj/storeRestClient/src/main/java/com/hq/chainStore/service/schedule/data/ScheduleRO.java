package com.hq.chainStore.service.schedule.data;

public interface ScheduleRO {

	public int getVer();

	public long getId() ;

	public long getStoreId();

	public String getTitle() ;

	public String getContent() ;

	public long getBeauticianId() ;

	public String getBeauticianName() ;
	
	public long getNoticeTime() ;

	public String getLeaguerId() ;

	public int getStatu() ;

	public long getCreatedTime() ;

	public long getLastUpdateTime();

}
