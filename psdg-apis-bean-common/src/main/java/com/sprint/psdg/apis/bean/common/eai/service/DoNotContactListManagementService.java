package com.sprint.psdg.apis.bean.common.eai.service;


public interface DoNotContactListManagementService {
	/**
	 * Check the do not contact list to determine whether the provided mdn can be contacted for promo or not.
	 * @param mdn
	 * @return {@code true} if mdn can not be contacted, {@code false} otherwise
	 */
    boolean queryDoNotContactList(final String mdn);
    
    boolean updateDoNotContactList(final String mdn, final boolean doNotContact);
}
