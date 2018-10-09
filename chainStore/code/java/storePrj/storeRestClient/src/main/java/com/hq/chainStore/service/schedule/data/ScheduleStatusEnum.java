package com.hq.chainStore.service.schedule.data;


public enum ScheduleStatusEnum {
	
	NEW("新建"),//新建待办事项
	PENDING("未处理"),
	FINISH("已处理"),
	;
	
   private String mark;
   
   private ScheduleStatusEnum(String mark){
	   this.mark = mark;
   }
   
   public String getMark(){
	   return mark;
   }
   
   //通过下标获得枚举
   public static ScheduleStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
