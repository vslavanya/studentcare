package com.sprint.psdg.apis.bean.common.eai.service.impl;

import java.util.List;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sprint.integration.interfaces.cdsprovisioningservice.v1.cdsprovisioningservice_wsdl.CdsProvisioningService;
import com.sprint.integration.interfaces.cdsprovisioningservice.v1.cdsprovisioningservice_wsdl.CdsProvisioningServicePortType;
import com.sprint.integration.interfaces.cdsprovisioningservice.v1.cdsprovisioningservice_wsdl.Faultmessage;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.BanInfoType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.CommunicationMethodInfoType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.CommunicationMethodListType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.ContactMethodTypeCodeType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.PrimarySecondaryTypeCodeType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.QuerySubscriberCdsProfileRequestType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.QuerySubscriberCdsProfileResponseType;
import com.sprint.integration.interfaces.querysubscribercdsprofile.v1.querysubscribercdsprofile.SearchCriteriaType;
import com.sprint.psdg.apis.bean.common.eai.service.CdsProvisioning;

@Service
public class CdsProvisioningImpl extends BaseEaiService implements CdsProvisioning {
    private static final Logger LOGGER = LoggerFactory.getLogger(CdsProvisioningImpl.class);
    private static final String NAMESPACE_URI = "http://integration.sprint.com/interfaces/CdsProvisioningService/v1/CdsProvisioningService.wsdl";
    private static final String WSDL_SERVICE = "CdsProvisioningService";
    private static final QName SERVICE_NAME = new QName(NAMESPACE_URI, WSDL_SERVICE);
    
    private CdsProvisioningServicePortType port;

	@Override
	protected String getWsdlServiceName() {
		return WSDL_SERVICE;
	}

	@Override
	protected void initializePort() {
		port = new CdsProvisioningService(getWsdlLocation(), SERVICE_NAME).getCdsProvisioningServicePortType();		
	}

	@Override
	public boolean isAccountHolder(String mdn) {
		final QuerySubscriberCdsProfileRequestType request = new QuerySubscriberCdsProfileRequestType();
		final SearchCriteriaType searchCriteria = new SearchCriteriaType();
		searchCriteria.setMdn(mdn);
		request.setInfo(searchCriteria);
		try {
			QuerySubscriberCdsProfileResponseType response = port.querySubscriberCdsProfile(request, getTrackingHeader());
			BanInfoType accountInfo = response.getAccountInfo();
			CommunicationMethodListType commMethodList = accountInfo.getCommMethodList();
			if (commMethodList != null) {
				List<CommunicationMethodInfoType> commMethodInfoList = commMethodList.getCommMethodInfo();
				for (CommunicationMethodInfoType commMethodInfo : commMethodInfoList) {
					if (PrimarySecondaryTypeCodeType.S == commMethodInfo.getCommType() && ContactMethodTypeCodeType.S == commMethodInfo.getMethodInd()) {
						return mdn.equals(commMethodInfo.getMethodValue());
					}
				}
			}
			return Boolean.FALSE;
		} catch (Faultmessage e) {
			LOGGER.error("SoapFault thrown in querySubscriberCdsProfile", e);
		}
		return Boolean.TRUE;
	}
}
