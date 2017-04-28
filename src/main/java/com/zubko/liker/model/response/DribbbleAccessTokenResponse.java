package com.zubko.liker.model.response;

import lombok.Data;

/**
 * Created by zinoviyzubko on 27.04.17.
 */
@Data
public class DribbbleAccessTokenResponse {

    private String access_token;
    private String token_type;
    private String scope;
}
