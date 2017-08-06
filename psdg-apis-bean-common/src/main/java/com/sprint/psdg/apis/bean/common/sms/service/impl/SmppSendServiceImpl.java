package com.sprint.psdg.apis.bean.common.sms.service.impl;

import org.springframework.stereotype.Service;

import com.sprint.psdg.apis.bean.common.sms.service.SmppSendService;

@Service
public class SmppSendServiceImpl implements SmppSendService {
	@Override
	public boolean sendSms(final String shortCode, final String mdn, final String smsMessage) {
		//TODO: SMS segmentation
		return true;
	}
}
