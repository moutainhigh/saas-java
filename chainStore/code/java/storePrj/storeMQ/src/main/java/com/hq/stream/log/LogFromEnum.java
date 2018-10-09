package com.hq.stream.log;


/**  
 * ClassName: LogFormEnum <br/>  
 * Function: TODO 应用的日志枚举类. <br/>  
 *  
 * @author kevin 
 * @version   
 * @since JDK 1.6  
 */
public enum LogFromEnum {
	UNKNOW("未知"),
	STOREMS("B端应用MS"),
	CUSTOMERMS("C端应用MS"),
	ORDERMS("订单应用MS"),
	THIRDPARTYMS("第三方应用MS"),
	STOREFILEMS("文件应用MS"),
	STOREWEB("WEB应用MS"),
	CHAINMS("连锁店应用MS"),
	CHIANCUSERMS("连锁店客户应用MS"),
	STOREAPP("B端APP客户端"),
	CUSTOMERAPP("C端APP客户端"),
	STOREMANAGERMS("智美通后台管理MS"),
	;
	
	private String remark;
	
	private LogFromEnum(String remark){
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	
	public static LogFromEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
