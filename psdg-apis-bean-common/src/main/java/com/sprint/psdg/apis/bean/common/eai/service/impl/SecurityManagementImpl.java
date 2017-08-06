package com.sprint.psdg.apis.bean.common.eai.service.impl;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sprint.integration.common.header.wsmessageheader.v2.BasicCredentialInfoType;
import com.sprint.integration.common.header.wsmessageheader.v2.SecurityMessageHeaderType;
import com.sprint.integration.common.header.wsmessageheader.v2.WsMessageHeaderType;
import com.sprint.integration.eai.services.securitymanagementservice.v1.securitymanagementservice_wsdl.SecurityManagementService;
import com.sprint.integration.eai.services.securitymanagementservice.v1.securitymanagementservice_wsdl.SecurityManagementServicePortType;
import com.sprint.integration.eai.services.securitymanagementservice.v1.securitymanagementservice_wsdl.SoapFault;
import com.sprint.integration.interfaces.authenticateuserlogin.v1.authenticateuserlogin.AuthenticateUserLoginType;
import com.sprint.integration.interfaces.queryuserprofileinfobyptn.v1.queryuserprofileinfobyptn.ProfilItemType;
import com.sprint.integration.interfaces.queryuserprofileinfobyptn.v1.queryuserprofileinfobyptn.QueryUserProfileInfoByPtnRequestType;
import com.sprint.integration.interfaces.queryuserprofileinfobyptn.v1.queryuserprofileinfobyptn.QueryUserProfileInfoByPtnResponseType;
import com.sprint.psdg.apis.bean.common.eai.service.SecurityManagement;
import com.sprint.psdg.apis.bean.common.utils.StringUtils;

@Service
public class SecurityManagementImpl extends BaseEaiService implements SecurityManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityManagementImpl.class);

    private static final String NAMESPACE_URI_SECURITY_MANAGEMENT = "http://integration.sprint.com/eai/services/SecurityManagementService/v1/SecurityManagementService.wsdl";
    private static final String SECURITY_MANAGEMENT_SERVICE = "SecurityManagementService";
    private static final QName SECURITY_MANAGEMENT_SERVICE_NAME = new QName(NAMESPACE_URI_SECURITY_MANAGEMENT, SECURITY_MANAGEMENT_SERVICE);

	@Value("${eai.queryUserProfileInfoByPtn.ptnId:HJS}")
	private String ptnId;

	@Value("${eai.queryUserProfileInfoByPtn.ptnPassword:HJS}")
	private String ptnPassword;

    private SecurityManagementServicePortType port;

	@Override
	protected String getWsdlServiceName() {
		return SECURITY_MANAGEMENT_SERVICE;
	}

	@Override
	protected void initializePort() {
		port = new SecurityManagementService(getWsdlLocation(), SECURITY_MANAGEMENT_SERVICE_NAME).getSecurityManagementServicePortType();		
	}

    @Override
    public boolean validateSprintLogin(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            LOGGER.error("Username or password was empty");
            return false;
        }

        AuthenticateUserLoginType request = new AuthenticateUserLoginType();
        request.setUserName(username);
        request.setPassword(password);

        try {
            port.authenticateUserLogin(request, getTrackingHeader());
            return true;
        } catch (SoapFault e) {
            LOGGER.error("querySubscriberBasicInfo failed: " + e.getMessage(), e);
        }

        return false;
    }

	@Override
	public ProfilItemType queryUserProfileInfoByPtn(String ptn) {
		final BasicCredentialInfoType credentialInfo = new BasicCredentialInfoType();
		credentialInfo.setId(ptnId);
		credentialInfo.setPassword(ptnPassword);
		final SecurityMessageHeaderType securityMessageHeader = new SecurityMessageHeaderType();
		securityMessageHeader.setBasicCredentialInfo(credentialInfo);
		final Holder<WsMessageHeaderType> trackingHeader = getTrackingHeader();
		trackingHeader.value.setSecurityMessageHeader(securityMessageHeader);

		try {
			final QueryUserProfileInfoByPtnRequestType request = new QueryUserProfileInfoByPtnRequestType();
			request.setPtn(ptn);
			QueryUserProfileInfoByPtnResponseType response = port.queryUserProfileInfoByPtn(request, trackingHeader);
			return response.getProfileItem();
		} catch (SoapFault e) {
			LOGGER.error("SoapFault caught: " + e.getMessage(), e);
		}
		
		return new ProfilItemType();
	}
}
