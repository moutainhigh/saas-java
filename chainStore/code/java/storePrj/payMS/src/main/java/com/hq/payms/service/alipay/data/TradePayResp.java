package com.hq.payms.service.alipay.data;


public class TradePayResp {
	/*
	 ACQ.SYSTEM_ERROR	接口返回错误	请立即调用查询订单API，查询当前订单的状态，并根据订单状态决定下一步的操作，如果多次调用依然报此错误码，请联系支付宝客服
	ACQ.INVALID_PARAMETER	参数无效	检查请求参数，修改后重新发起请求
	ACQ.ACCESS_FORBIDDEN	无权限使用接口	未签约条码支付或者合同已到期
	ACQ.EXIST_FORBIDDEN_WORD	订单信息中包含违禁词	修改订单信息后，重新发起请求
	ACQ.PARTNER_ERROR	应用APP_ID填写错误	联系支付宝小二（联系支付宝文档右边的客服头像或到支持中心咨询），确认APP_ID的状态
	ACQ.TOTAL_FEE_EXCEED	订单总金额超过限额	修改订单金额再发起请求
	ACQ.PAYMENT_AUTH_CODE_INVALID	支付授权码无效	用户刷新条码后，重新扫码发起请求
	ACQ.CONTEXT_INCONSISTENT	交易信息被篡改	更换商家订单号后，重新发起请求
	ACQ.TRADE_HAS_SUCCESS	交易已被支付	确认该笔交易信息是否为当前买家的，如果是则认为交易付款成功，如果不是则更换商家订单号后，重新发起请求
	ACQ.TRADE_HAS_CLOSE	交易已经关闭	更换商家订单号后，重新发起请求
	ACQ.BUYER_BALANCE_NOT_ENOUGH	买家余额不足	买家绑定新的银行卡或者支付宝余额有钱后再发起支付
	ACQ.BUYER_BANKCARD_BALANCE_NOT_ENOUGH	用户银行卡余额不足	建议买家更换支付宝进行支付或者更换其它付款方式
	ACQ.ERROR_BALANCE_PAYMENT_DISABLE	余额支付功能关闭	用户打开余额支付开关后，再重新进行支付
	ACQ.BUYER_SELLER_EQUAL	买卖家不能相同	更换买家重新付款
	ACQ.TRADE_BUYER_NOT_MATCH	交易买家不匹配	更换商家订单号后，重新发起请求
	ACQ.BUYER_ENABLE_STATUS_FORBID	买家状态非法	用户联系支付宝小二（联系支付宝文档右边的客服头像或到支持中心咨询），确认买家状态为什么非法
	ACQ.PULL_MOBILE_CASHIER_FAIL	唤起移动收银台失败	用户刷新条码后，重新扫码发起请求
	ACQ.MOBILE_PAYMENT_SWITCH_OFF	用户的无线支付开关关闭	用户在PC上打开无线支付开关后，再重新发起支付
	ACQ.PAYMENT_FAIL	支付失败	用户刷新条码后，重新发起请求，如果重试一次后仍未成功，更换其它方式付款
	ACQ.BUYER_PAYMENT_AMOUNT_DAY_LIMIT_ERROR	买家付款日限额超限	更换买家进行支付
	ACQ.BEYOND_PAY_RESTRICTION	商户收款额度超限	联系支付宝小二提高限额（联系电话：0571-88158090）
	ACQ.BEYOND_PER_RECEIPT_RESTRICTION	商户收款金额超过月限额	联系支付宝小二提高限额（联系电话：0571-88158090）
	ACQ.BUYER_PAYMENT_AMOUNT_MONTH_LIMIT_ERROR	买家付款月额度超限	让买家更换账号后，重新付款或者更换其它付款方式
	ACQ.SELLER_BEEN_BLOCKED	商家账号被冻结	联系支付宝小二，解冻账号（联系电话：95188）
	ACQ.ERROR_BUYER_CERTIFY_LEVEL_LIMIT	买家未通过人行认证	让用户联系支付宝小二并更换其它付款方式（联系电话：95188）
	ACQ.PAYMENT_REQUEST_HAS_RISK	支付有风险	更换其它付款方式
	ACQ.NO_PAYMENT_INSTRUMENTS_AVAILABLE	没用可用的支付工具	更换其它付款方式
	ACQ.USER_FACE_PAYMENT_SWITCH_OFF	用户当面付付款开关关闭	让用户在手机上打开当面付付款开关
	ACQ.INVALID_STORE_ID	商户门店编号无效	检查传入的门店编号是否有效
	ACQ.SUB_MERCHANT_CREATE_FAIL	二级商户创建失败	检查上送的二级商户信息是否有效
	ACQ.SUB_MERCHANT_TYPE_INVALID	二级商户类型非法	检查上传的二级商户类型是否有效
	ACQ.AGREEMENT_NOT_EXIST	用户协议不存在	确认代扣业务传入的协议号对应的协议是否已解约
	ACQ.AGREEMENT_INVALID	用户协议失效	代扣业务传入的协议号对应的用户协议已经失效，需要用户重新签约
	ACQ.AGREEMENT_STATUS_NOT_NORMAL	用户协议状态非NORMAL	代扣业务用户协议状态非正常状态，需要用户解约后重新签约
	ACQ.MERCHANT_AGREEMENT_NOT_EXIST	商户协议不存在	确认商户与支付宝是否已签约
	ACQ.MERCHANT_AGREEMENT_INVALID	商户协议已失效	商户与支付宝合同已失效，需要重新签约
	ACQ.MERCHANT_STATUS_NOT_NORMAL	商户协议状态非正常状态	商户与支付宝的合同非正常状态，需要重新签商户合同
	ACQ.CARD_USER_NOT_MATCH	脱机记录用户信息不匹配	请检查传入的进展出站记录是否正确
	ACQ.CARD_TYPE_ERROR	卡类型错误	检查传入的卡类型
	ACQ.CERT_EXPIRED	凭证过期	凭证已经过期
	ACQ.AMOUNT_OR_CURRENCY_ERROR	订单金额或币种信息错误	检查订单传入的金额信息是否有误，或者是不是当前币种未签约
	ACQ.CURRENCY_NOT_SUPPORT	订单币种不支持	请检查是否签约对应的币种
	 
	 
	*/
}
