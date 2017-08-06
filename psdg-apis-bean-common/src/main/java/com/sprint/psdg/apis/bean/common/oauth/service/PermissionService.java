package com.sprint.psdg.apis.bean.common.oauth.service;

import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;

import java.util.List;

/**
 * Contains the business logic for validating permissions and scopes.
 */
public interface PermissionService {
    /**
     * Checks all of the clients allowed permissions to see if any of those permissions
     * are defined as permission groups.  If a permission group is found, it will check
     * if the actual requested permission is included under that permission group.
     *
     * @param requested OAuthPermission being requested
     * @param allowed List<OAuthPermission> representing clients allowed permissions
     * @return {@code true} if requested permission is found in a permission group in the allowed list,
     * {@code false} otherwise
     */
    public boolean checkForPermission(OAuthPermission requested, List<OAuthPermission> allowed);

    /**
     * Retrieve only the "app only" permissions from the list of provided
     * scopes.
     *
     * @param scopes
     * @return List<OAuthPermission>
     */
    public List<OAuthPermission> getAppOnlyPermissions(List<String> scopes);

    /**
     * Retrieve only the "authorized" permissions from the list of provided
     * scopes.
     *
     * @param scopes
     * @return List<OAuthPermission>
     */
    public List<OAuthPermission> getAuthorizedPermissions(List<String> scopes);

    /**
     * Returns the OAuthPermission that is one-to-one mapped with the passed in
     * scope.
     *
     * @param scope String representing the OAuthPermission
     * @return OAuthPermission matching the provided scope
     */
    public OAuthPermission getPermissionForScope(String scope);
}
