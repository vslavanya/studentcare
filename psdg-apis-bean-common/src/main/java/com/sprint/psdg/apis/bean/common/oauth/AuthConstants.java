package com.sprint.psdg.apis.bean.common.oauth;

/**
 * Contains a list of OAuth errors not covered by the standard error set located
 * in {@link org.apache.cxf.rs.security.oauth2.utils.OAuthConstants}.
 */
public class AuthConstants {
    public static final int TOKEN_CHOICE_BEARER = 0;
    public static final int TOKEN_CHOICE_HAWK = 1;
    public static final int TOKEN_CHOICE_SPWS = 2;

    public static final int TOKEN_TYPE_IN_USE = TOKEN_CHOICE_SPWS;

    public static final String TOKEN_TYPE_SPWS = "spws";

    public static final String PARAM_SPWS_SECRET = "secret";
    public static final String PARAM_SPWS_ALGORITHM = "algorithm";

    public static final String HMAC_ALGO_SHA_1 = "hmac-sha-1";
    public static final String HMAC_ALGO_SHA_256 = "hmac-sha-256";

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_QUERY_STRING = "query_params";

    public static final String FORM_AUTHORIZE = "oauth/authorize.html";
    public static final String FORM_DENY = "oauth/deny.html";
    public static final String FORM_LOGIN = "oauth/login.html";
    public static final String FORM_RETURN_AUTH_CODE = "oauth/return-code.html";

    public static final String REDIRECT_OOB = "oob";

    public static final long DEFAULT_TOKEN_EXPIRES = 60L * 60L * 24L * 30L; // 30 days
    public static final long DEFAULT_REFRESH_TOKEN_EXPIRES = DEFAULT_TOKEN_EXPIRES * 3;
    public static final long DEFAULT_CODE_EXPIRES = 60L * 60L * 1L; // 1 hour

    public static final int OAUTH_ERROR = 4200;

    public static final String CLIENT_DOES_NOT_EXIST = "client_does_not_exist";
    public static final String CLIENT_NOT_ALLOWED = "client_not_allowed";
    public static final String CODE_SAVE_FAILED = "code_store_failure";
    public static final String INVALID_TOKEN = "invalid_token";
    public static final String TOKEN_SAVE_FAILED = "token_store_failure";
    public static final String USER_AUTH_FAILED = "user_auth_failure";
    public static final String SESSION_AUTH_FAILED = "session_auth_failure";
    public static final String MISSING_REDIRECT_URI = "missing_redirect_uri";
    public static final String MISSING_CLIENT_ID = "missing_client_id";
    public static final String MISSING_RESPONSE_TYPE = "missing_response_type";

    public static final String MISSING_TOKEN = "missing_oauth_token";
    public static final String MISSING_GRANT_TYPE = "missing_grant_type";
    public static final String MISSING_AUTH_CODE = "missing_auth_code";
    public static final String MISSING_REFRESH_TOKEN = "missing_refresh_token";
    public static final String MISSING_USER_OR_PASSWORD = "missing_user_or_password";
    public static final String INVALID_TRANSACTION = "invalid_oauth_transaction";

    public static String algorithmToJavaName(String algorithm) {
        if (HMAC_ALGO_SHA_1.equals(algorithm)) {
            return "HmacSHA1";
        } else if (HMAC_ALGO_SHA_256.equals(algorithm)) {
            return "HmacSHA256";
        }

        return "";
    }
}
