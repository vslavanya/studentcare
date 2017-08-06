/*
 * Copyright (c) 2015 Sprint Corp. All rights reserved.
 * Sprint Corp Confidential and Proprietary information. Do not redistribute.
 */
package com.sprint.psdg.apis.bean.common.utils;

import org.apache.cxf.jaxrs.utils.ExceptionUtils;

/**
 * @author Lal V S
 *
 */
public class QUVExceptionUtils {
	public static String getStackTace(Exception e) {
		if (e != null && e.getCause()!=null)
			return ExceptionUtils.getStackTrace(e.getCause());
		return "";

	}

}
