package work.gg3083.template.commom;

/**
 * @author Gimi
 * @date 2021-04-30 16:45
 */
public class AuthConst {

    public final static String REFRESH_TOKEN_URL_PRE = "/work/user/refreshToken";
    public final static String REFRESH_TOKEN_URL = "/user/refreshToken";

    public final static String[] NO_AUTH_RESOURCES = {
//            "/**/*",
            "/login",
            "/v2/api-docs",
            "/swagger*/**",
            "/**/*.js",
            "/**/*.css",
            "/**/*.png",
            "/favicon.ico",
            "/user/login",
            "/user/register",
            REFRESH_TOKEN_URL,
    };


}
