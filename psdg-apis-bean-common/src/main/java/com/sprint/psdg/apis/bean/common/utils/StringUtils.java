package com.sprint.psdg.apis.bean.common.utils;

public class StringUtils {
    /**
     * Check if a String is null or empty.
     *
     * @param check
     * @return {@code true} if empty or null, {@code false} otherwise
     */
    public static boolean isEmpty(String check) {
        return ((null == check) || check.isEmpty());
    }
}
