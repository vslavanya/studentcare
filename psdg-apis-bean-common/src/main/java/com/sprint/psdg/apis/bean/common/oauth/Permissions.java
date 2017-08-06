package com.sprint.psdg.apis.bean.common.oauth;

import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;

/**
 * A list of permissions we will use to guide access to Sprint back-office
 * data.
 */
public class Permissions {
    public static final OAuthPermission ACCESS_DEG_INFORMATION =
            new OAuthPermission("access_deg_info", "Access information about network services");
    public static final OAuthPermission ACCESS_DEVICE_INFORMATION =
            new OAuthPermission("access_device_info", "Access information about the device");
    public static final OAuthPermission ACCESS_SUBSCRIBER_INFORMATION =
            new OAuthPermission("access_sub_info", "Access information about the subscriber");
    public static final OAuthPermission MANAGE_SUBSCRIBER_INFORMATION =
            new OAuthPermission("manage_sub_info", "Manager information about the subscriber");
}
