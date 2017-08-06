package com.sprint.psdg.apis.bean.common.eai.service;

import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.SubscriberListResponseType;

public interface ControlsManagement {
	SubscriberListResponseType queryAccountInfo(final String subscriberId);
}
