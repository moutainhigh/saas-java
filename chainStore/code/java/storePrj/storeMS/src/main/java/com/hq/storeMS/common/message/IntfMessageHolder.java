package com.hq.storeMS.common.message;

import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MsgQueryForm;

public interface IntfMessageHolder {
	public MessageResp getMessageItem(MsgQueryForm queryForm);
}
