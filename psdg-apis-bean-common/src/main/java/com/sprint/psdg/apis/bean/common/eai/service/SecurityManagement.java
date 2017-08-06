package com.sprint.psdg.apis.bean.common.eai.service;

import com.sprint.integration.interfaces.queryuserprofileinfobyptn.v1.queryuserprofileinfobyptn.ProfilItemType;


/**
 * Service that interfaces with the EAI SecurityManagementService.
 */
public interface SecurityManagement {
    /**
     * Validate the provided username and password against sprint.com's
     * username and password service.
     *
     * @param username String representing the username
     * @param password String representing the password
     * @return {@code true} if valid, {@code false} otherwise
     */
    boolean validateSprintLogin(String username, String password);
    
    /**
     * Retrieves sprint.com's profile for the provided ptn.
     *
     * @param ptn String representing the ptn
     * @return ProfilItemType
     */
    ProfilItemType queryUserProfileInfoByPtn(final String ptn);
}
