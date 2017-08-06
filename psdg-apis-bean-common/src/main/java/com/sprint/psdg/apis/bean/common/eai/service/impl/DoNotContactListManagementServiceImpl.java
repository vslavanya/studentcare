package com.sprint.psdg.apis.bean.common.eai.service.impl;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sprint.integration.common.header.wsmessageheader.v2.WsMessageHeaderType;
import com.sprint.integration.eai.services.contactpreferencemanagementservice.v1.contactpreferencemanagementservice_wsdl.ContactPreferenceManagementService;
import com.sprint.integration.eai.services.contactpreferencemanagementservice.v1.contactpreferencemanagementservice_wsdl.ContactPreferenceManagementServicePortType;
import com.sprint.integration.eai.services.contactpreferencemanagementservice.v1.contactpreferencemanagementservice_wsdl.SoapFault;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.CertificationInfoType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.ContactInfoType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.ContactMethodCodeType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.GlobalPreferenceInfoType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.GlobalPreferenceListType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.QueryContactGlobalPreferencesRequestType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.QueryContactGlobalPreferencesResponseType;
import com.sprint.integration.interfaces.querycontactglobalpreferences.v1.querycontactglobalpreferences.TextAddressInfoType;
import com.sprint.integration.interfaces.querysubscriberbasicinfo.v1.querysubscriberbasicinfo.SearchCriteriaType;
import com.sprint.integration.interfaces.updatecontactpreferenceinfo.v1.updatecontactpreferenceinfo.PreferenceInfoType;
import com.sprint.integration.interfaces.updatecontactpreferenceinfo.v1.updatecontactpreferenceinfo.PreferenceListType;
import com.sprint.integration.interfaces.updatecontactpreferenceinfo.v1.updatecontactpreferenceinfo.TacticValueInfoType;
import com.sprint.integration.interfaces.updatecontactpreferenceinfo.v1.updatecontactpreferenceinfo.UpdateContactPreferenceInfoRequestType;
import com.sprint.psdg.apis.bean.common.eai.service.DoNotContactListManagementService;

@Service
public class DoNotContactListManagementServiceImpl extends BaseEaiService implements DoNotContactListManagementService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoNotContactListManagementServiceImpl.class);
	private static final String CONTACT_PREF_MGMT_SERVICE = "ContactPreferenceManagementService";
	private static final String NAMESPACE_URI = "http://integration.sprint.com/eai/services/ContactPreferenceManagementService/v1/ContactPreferenceManagementService.wsdl";
    private static final QName SERVICE_NAME = new QName(NAMESPACE_URI, CONTACT_PREF_MGMT_SERVICE);
    
    private static final String TEXT_PREF_NAME = "SPRGPROM";
    private static final String CERTIFY_RULE_NAME = "SPRCFT07";
    private static final String SMS_CONTACT_TYPE = "sms";
    private static final String SPR_BRAND_CODE = "SPR";
    private static final String OPT_IN = "optin";
    private static final String OPT_OUT = "optout";
    private static final String SUCCESS = "Successful";
    
    private ContactPreferenceManagementServicePortType port;
	
	@Override
	protected void initializePort() {
		port = new ContactPreferenceManagementService(getWsdlLocation(), SERVICE_NAME).getContactPreferenceManagementServicePortType();
	}
	
	@Override
	protected String getWsdlServiceName() {
		return CONTACT_PREF_MGMT_SERVICE;
	}
	
	public boolean queryDoNotContactList(final String mdn) {
		try {
		    final QueryContactGlobalPreferencesResponseType response = queryContactGlobalPreferences(mdn);
		    List<CertificationInfoType> certificationInfoList = response.getCertificationList().getCertificationInfo();
		    for (CertificationInfoType certificationInfo : certificationInfoList) {
		        final ContactInfoType contactInfo = certificationInfo.getContactTypeValueInfo();
		        if (mdn.equals(contactInfo.getTextAddressInfo().getMdn())) {
		            return !Boolean.valueOf(certificationInfo.getCertificationSummaryInd());
		        }
		    }
		} catch (SoapFault e) {
			LOGGER.error("SoapFault thrown in queryContactGlobalPreferences call", e);
		}
		//TODO: How to handle EAI exceptions, handle failures.
		return Boolean.TRUE;
	}
	
	private QueryContactGlobalPreferencesResponseType queryContactGlobalPreferences(final String mdn) throws SoapFault {
		final SearchCriteriaType criteria = new SearchCriteriaType();
        criteria.setMdn(mdn);
        final QueryContactGlobalPreferencesRequestType request = new QueryContactGlobalPreferencesRequestType();
		
        final GlobalPreferenceListType globalPreferenceList = new GlobalPreferenceListType();
		request.setGlobalPreferenceList(globalPreferenceList);
		final GlobalPreferenceInfoType globalPreferenceInfo = new GlobalPreferenceInfoType();
		globalPreferenceList.getGlobalPreferenceInfo().add(globalPreferenceInfo);
		final ContactInfoType contactTypeValueInfo = new ContactInfoType();
		final TextAddressInfoType textAddressInfo = new TextAddressInfoType();
		textAddressInfo.setMdn(mdn);
		textAddressInfo.setPreferenceName(TEXT_PREF_NAME);
		contactTypeValueInfo.setTextAddressInfo(textAddressInfo);
		globalPreferenceInfo.setContactType(ContactMethodCodeType.TEXT);
		globalPreferenceInfo.setContactTypeValueInfo(contactTypeValueInfo);
		globalPreferenceInfo.setCertifyRuleName(CERTIFY_RULE_NAME);
//        trackingMessageHeader.setConsumerId(CONSUMER_ID);
//        trackingMessageHeader.setApplicationUserId(mdn);
		return port.queryContactGlobalPreferences(request, getTrackingHeader());
	}

	@Override
	public boolean updateDoNotContactList(String mdn, boolean doNotContact) {
		final UpdateContactPreferenceInfoRequestType request = new UpdateContactPreferenceInfoRequestType();
		final PreferenceListType preferenceList = new PreferenceListType();
		final PreferenceInfoType preferenceInfo = new PreferenceInfoType();
		preferenceInfo.setContactType(SMS_CONTACT_TYPE);
		TacticValueInfoType contactTypeValueInfo = new TacticValueInfoType();
		contactTypeValueInfo.setMdn(mdn);
		preferenceInfo.setContactTypeValueInfo(contactTypeValueInfo);
		preferenceInfo.setBrandCode(SPR_BRAND_CODE);
		if (doNotContact) {
			preferenceInfo.setPreference(OPT_OUT);
		} else {
			preferenceInfo.setPreference(OPT_IN);
		}
		Holder<WsMessageHeaderType> header = getTrackingHeader();
		preferenceInfo.setPromotion(TEXT_PREF_NAME);
		preferenceInfo.setRequestDateTime(header.value.getTrackingMessageHeader().getMessageDateTimeStamp());
		preferenceList.getPreferenceInfo().add(preferenceInfo);
		request.setPreferenceList(preferenceList);
		try {
		    final String success = port.updateContactPreferenceInfo(request, header).getSuccess();
			return SUCCESS.equalsIgnoreCase(success);
		} catch (SoapFault e) {
			LOGGER.error("SoapFault thrown in updateContactPreferenceInfo call", e);
		}
		return false;
	}
}
