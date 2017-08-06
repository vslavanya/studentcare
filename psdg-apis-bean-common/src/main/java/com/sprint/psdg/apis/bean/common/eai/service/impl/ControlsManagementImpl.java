package com.sprint.psdg.apis.bean.common.eai.service.impl;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sprint.integration.eai.services.controlsmanagementservice.v1.controlsmanagementservice_wsdl.ControlsManagementService;
import com.sprint.integration.eai.services.controlsmanagementservice.v1.controlsmanagementservice_wsdl.ControlsManagementServicePortType;
import com.sprint.integration.eai.services.controlsmanagementservice.v1.controlsmanagementservice_wsdl.Faultmessage;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.AuthenticationType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.ControlListRoleTypeCodeType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.InfoType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.QueryAccountControlSettingsResponseType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.QueryAccountControlSettingsType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.SubscriberInfoType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.SubscriberListResponseType;
import com.sprint.integration.interfaces.queryaccountcontrolsettings.v1.queryaccountcontrolsettings.SubscriberListType;
import com.sprint.psdg.apis.bean.common.eai.service.ControlsManagement;

@Service
public class ControlsManagementImpl extends BaseEaiService implements ControlsManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControlsManagementImpl.class);

    private static final String NAMESPACE_URI_CONTROLS_MANAGEMENT = "http://integration.sprint.com/eai/services/ControlsManagementService/v1/ControlsManagementService.wsdl";
    private static final String CONTROLS_MANAGEMENT_SERVICE = "ControlsManagementService";
    private static final QName CONTROLS_MANAGEMENT_SERVICE_NAME = new QName(NAMESPACE_URI_CONTROLS_MANAGEMENT, CONTROLS_MANAGEMENT_SERVICE);
    private static final String LOGIN_ID = "SprintZone";

    private ControlsManagementServicePortType port;

	@Override
	protected String getWsdlServiceName() {
		return CONTROLS_MANAGEMENT_SERVICE;
	}

	@Override
	protected void initializePort() {
		port = new ControlsManagementService(getWsdlLocation(), CONTROLS_MANAGEMENT_SERVICE_NAME).getControlsManagementServicePortType();		
	}


	@Override
	public SubscriberListResponseType queryAccountInfo(final String subscriberId) {
		try {
			final QueryAccountControlSettingsType request = new QueryAccountControlSettingsType();
			final InfoType info = new InfoType();
			final SubscriberListType subscriberList = new SubscriberListType();
			final SubscriberInfoType subscriberInfo = new SubscriberInfoType();
			subscriberInfo.setSubscriberId(subscriberId);
			subscriberList.getSubscriberInfo().add(subscriberInfo);
			info.setSubscriberList(subscriberList);
			request.setInfo(info);
			final AuthenticationType authentication = new AuthenticationType();
			authentication.setLoginId(LOGIN_ID);
			authentication.setRole(ControlListRoleTypeCodeType.INTERNAL_APPLICATION);
			request.setAuthentication(authentication);
			final QueryAccountControlSettingsResponseType response = port.queryAccountControlSettings(request, getTrackingHeader());
			return response.getSubcriberList();
		} catch (Faultmessage e) {
			LOGGER.error("Faultmessage caught: " + e.getMessage(), e);
		}
		return new SubscriberListResponseType();
	}

}
