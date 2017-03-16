package pl.wykop.config;

/**
 * Created by mariusz on 15.03.17.
 */
public final class Route {

    public static final String API_PREFIX = "/api";
    public static final String AUTHENTICATED_PATTERN = API_PREFIX;

    public static final String AUTH_URL = API_PREFIX + "/auth";
    public static final String USER = API_PREFIX + "/user";
    public static final String USER_BY_USERNAME = USER + "/{username}";

}
