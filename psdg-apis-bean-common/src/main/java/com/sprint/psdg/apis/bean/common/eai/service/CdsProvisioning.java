package com.sprint.psdg.apis.bean.common.eai.service;


public interface CdsProvisioning {
    /**
     * Check the CDS profile to determine if the provided mdn is the account holder.
     *
     * @param mdn String representing the mdn
     * @return {@code true} if mdn is the account holder, {@code false} otherwise
     */
    boolean isAccountHolder(final String mdn);
}
