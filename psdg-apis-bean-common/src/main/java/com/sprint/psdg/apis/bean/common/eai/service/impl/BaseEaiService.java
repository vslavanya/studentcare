package com.sprint.psdg.apis.bean.common.eai.service.impl;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.sprint.integration.common.header.wsmessageheader.v2.TrackingMessageHeaderType;
import com.sprint.integration.common.header.wsmessageheader.v2.WsMessageHeaderType;

public abstract class BaseEaiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEaiService.class);

    private static final String APPLICATION_ID = "QUV";
    private static final String APPLICATION_USER_ID = "DSI";
    private static final String MESSAGE_ID_PREFIX = "DSI-";
    private static final BigInteger TIME_TO_LIVE = new BigInteger("30");
    
	@Value("${eai.soapserviceurl.config}")
	private String soapServiceUrl;

    private URL wsdlLocation;

	@PostConstruct
	public void initialize(){
        final String wsdlUrl = soapServiceUrl + "/" + getWsdlServiceName() + "?wsdl";
        try {
        	wsdlLocation = new URL(wsdlUrl);
        } catch (MalformedURLException e) {
        	LOGGER.error(
                     "Can not initialize the default wsdl from " + wsdlUrl, e);
        }
        initializePort();  
    }
	
    /**
     * Retrieve the standard Sprint SOAP tracking header.
     *
     * @return Holder<WsMessageHeaderType> representing the SOAP tracking header
     */
    protected Holder<WsMessageHeaderType> getTrackingHeader() {
        Holder<WsMessageHeaderType> header = new Holder<WsMessageHeaderType>();
        header.value = new WsMessageHeaderType();

        TrackingMessageHeaderType trackingMessageHeader = new TrackingMessageHeaderType();
        trackingMessageHeader.setApplicationId(APPLICATION_ID);
        trackingMessageHeader.setApplicationUserId(APPLICATION_USER_ID);
        GregorianCalendar cal = new GregorianCalendar();
        trackingMessageHeader.setMessageId(MESSAGE_ID_PREFIX + cal.getTime());
        trackingMessageHeader.setTimeToLive(TIME_TO_LIVE);
        try {
            trackingMessageHeader.setMessageDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        } catch (DatatypeConfigurationException ex) {
            LOGGER.error("setMessageDateTimeStamp on soap header failed: " + ex.getMessage(), ex);
        }

        header.value.setTrackingMessageHeader(trackingMessageHeader);
        return header;
    }

    /**
     * Set the consumer ID variable inside the tracking header.
     *
     * @param header Holder<WsMessageHeaderType> representing the SOAP tracking header
     * @param consumer String representing the consumer ID
     */
    protected void setConsumerId(Holder<WsMessageHeaderType> header, String consumer) {
        TrackingMessageHeaderType tmh = header.value.getTrackingMessageHeader();
        tmh.setConsumerId(consumer);
        header.value.setTrackingMessageHeader(tmh);
    }
    
    protected URL getWsdlLocation() {
    	return wsdlLocation;
    }

	abstract protected String getWsdlServiceName();
	
	abstract protected void initializePort();
}
