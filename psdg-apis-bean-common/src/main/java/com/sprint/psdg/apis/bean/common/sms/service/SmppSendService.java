package com.sprint.psdg.apis.bean.common.sms.service;

public interface SmppSendService {
	boolean sendSms(final String shortCode, final String mdn, final String smsMessage);
}
