package com.zubko.liker.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Getter
@AllArgsConstructor
public enum DribbbleMethods {

    GET_ACCESS_TOCKEN(HttpMethod.POST, "https://dribbble.com/oauth/token"),
    LIKE_SHOT(HttpMethod.POST, "https://api.dribbble.com/v1/shots/${ID}/like"),
    GET_CURRENT_USER(HttpMethod.GET, "https://api.dribbble.com/v1/user"),
    GET_USER_SHOTS(HttpMethod.GET, "https://api.dribbble.com/v1/users/${ID}/shots");

    private HttpMethod httpMethod;
    private String url;
}
