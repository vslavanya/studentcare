package com.sprint.psdg.apis.bean.common.oauth.service.impl;

import com.sprint.psdg.apis.bean.common.oauth.Permissions;
import com.sprint.psdg.apis.bean.common.oauth.service.PermissionService;
import com.sprint.psdg.apis.bean.common.utils.StringUtils;

import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private static final ArrayList<OAuthPermission> sAppOnlyPermissions = new ArrayList<OAuthPermission>();
    static {
        sAppOnlyPermissions.add(Permissions.ACCESS_DEVICE_INFORMATION);
        sAppOnlyPermissions.add(Permissions.ACCESS_SUBSCRIBER_INFORMATION);
        sAppOnlyPermissions.add(Permissions.ACCESS_DEG_INFORMATION);
    }

    private static final ArrayList<OAuthPermission> sAuthorizedPermissions = new ArrayList<OAuthPermission>();
    static {
        sAuthorizedPermissions.add(Permissions.MANAGE_SUBSCRIBER_INFORMATION);
    }

    public boolean checkForPermission(OAuthPermission requested, List<OAuthPermission> allowed) {
        if ((null == requested) || (null == allowed)) {
            return false;
        }

        for (OAuthPermission permission : allowed) {
            if (isPermissionGroup(permission)) {
                if (groupContainsPermission(permission, requested)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<OAuthPermission> getAppOnlyPermissions(List<String> scopes) {
        List<OAuthPermission> permissions = new ArrayList<OAuthPermission>();

        for (String scope : scopes) {
            for (OAuthPermission p : sAppOnlyPermissions) {
                if (p.getPermission().equals(scope)) {
                    permissions.add(p);
                }
            }
        }

        return permissions;
    }

    public List<OAuthPermission> getAuthorizedPermissions(List<String> scopes) {
        List<OAuthPermission> permissions = new ArrayList<OAuthPermission>();

        for (String scope : scopes) {
            for (OAuthPermission p : sAuthorizedPermissions) {
                if (p.getPermission().equals(scope)) {
                    permissions.add(p);
                }
            }
        }

        return permissions;
    }

    public OAuthPermission getPermissionForScope(String scope) {
        if (StringUtils.isEmpty(scope)) {
            return null;
        }

        for (OAuthPermission perm : sAppOnlyPermissions) {
            if (scope.equals(perm.getPermission())) {
                return perm;
            }
        }

        for (OAuthPermission perm : sAuthorizedPermissions) {
            if (scope.equals(perm.getPermission())) {
                return perm;
            }
        }

        return null;
    }

    /**
     * Checks to see if an OAuthPermission we define as a permission group contains
     * the requested permission.
     *
     * @param group OAuthPermission representing a permission group
     * @param requested OAuthPermission being requested
     * @return
     */
    private boolean groupContainsPermission(OAuthPermission group, OAuthPermission requested) {
        return false;
    }

    /**
     * Determines whether or not an OAuthPermission has been defined by us as a permission
     * group.
     *
     * @param permission OAuthPermission to check
     * @return {@code true} if it is a permission group, {@code false} otherwise
     */
    private boolean isPermissionGroup(OAuthPermission permission) {
        return false;
    }
}
